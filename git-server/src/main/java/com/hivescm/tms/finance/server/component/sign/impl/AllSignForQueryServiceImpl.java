package com.hivescm.tms.finance.server.component.sign.impl;

import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.request.DispatcherDetailForSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignQueryPageResponseDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignQueryResponseDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;
import com.hivescm.tms.finance.server.component.sign.AllSignForQueryService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 送货签收列表查询
 *
 * @author 杨彭伟
 * @date 2017-12-07 16:16
 */
@Service
public class AllSignForQueryServiceImpl implements AllSignForQueryService {
	private static Logger logger = LoggerFactory.getLogger(AllSignForQueryServiceImpl.class);

	@Autowired
	private ESSearchService esSearchService;
	@Autowired
	private ESStatisticService esStatisticService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private DispatcherService dispatcherService;

	@Override
	public SignQueryPageResponseDTO getSignPage(SignQueryReqDTO signQueryReqDTO) {
		logger.debug("查询参数：{}", signQueryReqDTO);
		try {
			SignQueryPageResponseDTO signQueryPageResponseDTO = new SignQueryPageResponseDTO();
			Integer signListTotalNum = getSignListTotalNum(signQueryReqDTO);
			if (signListTotalNum == 0) {
				signQueryPageResponseDTO.setList(new ArrayList<>());
				signQueryPageResponseDTO.setTotalNum(0);
				return signQueryPageResponseDTO;
			}
			signQueryPageResponseDTO.setTotalNum(signListTotalNum);
			signQueryPageResponseDTO.setList(getSignList(signQueryReqDTO));
			return signQueryPageResponseDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(-1, e.getLocalizedMessage());
		}
	}

	public List<SignQueryResponseDTO> getSignList(SignQueryReqDTO signQueryReqDTO) throws Exception {
		logger.debug("查询参数：{}", signQueryReqDTO);
		// todo 构建参数
		List<SearchCondition> searchConditions = buildSearchCondition(signQueryReqDTO);
		List<OrderCondition> orderConditions = buildOrderCondition();
		PageCondition pageCondition = buildPageCondition(signQueryReqDTO);
		DispatcherDetailForSignReqDTO signReqDTO = new DispatcherDetailForSignReqDTO();
		signReqDTO.setSearchConditions(searchConditions);
		signReqDTO.setOrderConditions(orderConditions);
		signReqDTO.setPageCondition(pageCondition);
		// todo 查询派车单明细
		List<SignQueryResponseDTO> list = getSignQueryResponseDTOList(signReqDTO);

		// todo 查询运单
		searchWaybillForDispatcher(list);

		// todo 查询签收单
		searchSignForDispatcher(list);

		return list;
	}

	/**
	 * 获取代收货款
	 * 
	 * @param waybillId
	 * @return
	 */
	private BigDecimal collectionPay(Long waybillId) {
		WaybillFeeEsDTO query = new WaybillFeeEsDTO();
		query.setWaybillId(waybillId);
		query.setAttrId(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType());
		List<WaybillFeeEsDTO> feeEsDTOList = waybillService.queryWaybillFeeEsDTOList(query);
		WaybillFeeEsDTO waybillFeeEsDTO = feeEsDTOList.get(0);
		if (waybillFeeEsDTO != null && waybillFeeEsDTO.getFee() != null) {
			return waybillFeeEsDTO.getFee();
		} else {
			return BigDecimal.ZERO;
		}
	}

	/**
	 * 列表查询总条数
	 * 
	 * @param signQueryReqDTO
	 *            查询条件
	 * @return
	 */
	private Integer getSignListTotalNum(SignQueryReqDTO signQueryReqDTO) {
		// todo 构建参数
		List<SearchCondition> searchConditions = buildSearchCondition(signQueryReqDTO);
		DispatcherDetailForSignReqDTO signReqDTO = new DispatcherDetailForSignReqDTO();
		signReqDTO.setSearchConditions(searchConditions);
		return dispatcherService.getDispatcherDetailTotalNum(signReqDTO);
	}

	/**
	 * 查询派车单明细
	 * 
	 * @param signQueryReqDTO
	 * @return
	 */
	private List<SignQueryResponseDTO> getSignQueryResponseDTOList(DispatcherDetailForSignReqDTO signQueryReqDTO)
			throws Exception {
		List<DispatcherDetailEsDTO> detailEsDTOS = dispatcherService.getDispatcherDetailEsList(signQueryReqDTO);
		List<SignQueryResponseDTO> list = new ArrayList<SignQueryResponseDTO>();
		if (CollectionUtils.isNotEmpty(detailEsDTOS)) {
			for (DispatcherDetailEsDTO dispatcherDetailEsDTO : detailEsDTOS) {
				SignQueryResponseDTO signQueryResponseDTO = new SignQueryResponseDTO();
				BeanUtils.copyProperties(dispatcherDetailEsDTO, signQueryResponseDTO);
				list.add(signQueryResponseDTO);
			}
		}
		return list;
	}

	/**
	 * 查询签收单
	 * 
	 * @param list
	 */
	private void searchSignForDispatcher(List<SignQueryResponseDTO> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		list.forEach(dispatcherDetail -> {
			Long signId = dispatcherDetail.getSignId();
			if (signId != null) {
				SignEsDTO signEsDTO = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(esSearchService) {
					@Override
					public EsConfig getConfig() {
						return EsConfig.sign();
					}
				}.get(signId);
				if (signEsDTO != null) {
					dispatcherDetail.setSignStatus(signEsDTO.getSignStatus());
					dispatcherDetail.setSignTime(signEsDTO.getSignTime());
					dispatcherDetail.setTotalReceivable(signEsDTO.getTotalReceivable());
					dispatcherDetail.setSignPic(signEsDTO.getSignPic());
					dispatcherDetail.setSignPeople(signEsDTO.getSignPeople());
					dispatcherDetail.setPhoneNumber(signEsDTO.getPhoneNumber());
					dispatcherDetail.setIdCard(signEsDTO.getIdCard());
					dispatcherDetail.setSignNumber(signEsDTO.getSignNumber());
					dispatcherDetail.setUnsignedNumber(signEsDTO.getUnsignedNumber());
					dispatcherDetail.setCreateNumber(signEsDTO.getCreateNumber());
					dispatcherDetail.setRefuseNumber(signEsDTO.getRefuseNumber());
					dispatcherDetail.setSigningInstructions(signEsDTO.getSigningInstructions());
					dispatcherDetail.setPhoneNo(signEsDTO.getPhoneNo());
				}
			}
		});
	}

	/**
	 * 查询运单
	 * 
	 * @param list
	 */
	private void searchWaybillForDispatcher(List<SignQueryResponseDTO> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		list.forEach(dispatcherDetail -> {
			Long waybillId = dispatcherDetail.getWaybillId();
			if (waybillId != null) {
				dispatcherDetail.setCollectPayment(collectionPay(waybillId));
				WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(waybillId);
				if (waybillEsDTO != null) {
					dispatcherDetail.setDeliveryTime(waybillEsDTO.getDeliveryTime());
					dispatcherDetail.setPhoneNo(waybillEsDTO.getPhoneNo());
					dispatcherDetail.setDestName(waybillEsDTO.getDestName());
					dispatcherDetail.setWarehouseName(waybillEsDTO.getWarehouseName());
					dispatcherDetail.setWarehouseServerGroupName(waybillEsDTO.getWarehouseServerGroupName());
					dispatcherDetail.setMerchantName(waybillEsDTO.getMerchantName());
					dispatcherDetail.setMerchandGroupName(waybillEsDTO.getMerchandGroupName());
					dispatcherDetail.setTotalSignNumber(waybillEsDTO.getTotalSignNumber());
					dispatcherDetail.setDestOrgName(waybillEsDTO.getDestOrgName());
					dispatcherDetail.setStoreName(waybillEsDTO.getStoreName());
					dispatcherDetail.setBackCode(waybillEsDTO.getBackCode());
					dispatcherDetail.setBackOrderNum(waybillEsDTO.getBackOrderNum());
					dispatcherDetail.setBackType(waybillEsDTO.getBackType());
					dispatcherDetail.setWaybillStatus(waybillEsDTO.getStatus());
					dispatcherDetail.setWaybillStatusName(waybillEsDTO.getStatusName());
					// dispatcherDetail.setSignStatusName(waybillEsDTO.getSignStatusName());
					dispatcherDetail.setWaybillCreateTime(waybillEsDTO.getCreateTime());
				}
			}
		});
	}

	/**
	 * 构建搜索条件
	 *
	 * @param signQueryReqDTO
	 * @return
	 */
	private List<SearchCondition> buildSearchCondition(SignQueryReqDTO signQueryReqDTO) {
		List<SearchCondition> list = new ArrayList<>();
		List<String> clientList = signQueryReqDTO.getClient();

		if (CollectionUtils.isNotEmpty(clientList)) {
			SearchCondition invoiceCustomerId = SearchConditionUtils.newInCondition("invoiceCustomerId",
					clientList.toArray(new String[0]));
			list.add(invoiceCustomerId);
		}
		// 签收时间 or 派送时间
		if (null != signQueryReqDTO.getStartTime() && null != signQueryReqDTO.getEndTime()
				&& signQueryReqDTO.getStartTime() > 0 && signQueryReqDTO.getEndTime() > 0) {
			String fieldName;
			if (signQueryReqDTO.getTimeType() == 1) {
				fieldName = "sendTime";
			} else {
				fieldName = "signTime";
			}
			SearchCondition time = SearchConditionUtils.newBetweenAndCondition(fieldName,
					signQueryReqDTO.getStartTime(), signQueryReqDTO.getEndTime());
			list.add(time);
		}
		// 签收单号
		if (StringUtils.isNotBlank(signQueryReqDTO.getSignBatchNumber())) {
			SearchCondition signBatchNumber = SearchConditionUtils.newLikeCondition("signBatchNumber",
					signQueryReqDTO.getSignBatchNumber());
			list.add(signBatchNumber);
		}
		// 派送批次号
		if (StringUtils.isNotBlank(signQueryReqDTO.getBatchCode())) {
			SearchCondition batchCode = SearchConditionUtils.newLikeCondition("batchCode",
					signQueryReqDTO.getBatchCode());
			list.add(batchCode);
		}
		// 车牌号
		if (StringUtils.isNotBlank(signQueryReqDTO.getVehicleNo())) {
			SearchCondition vehicleNo = SearchConditionUtils.newSearchCondition("vehicleNo",
					signQueryReqDTO.getVehicleNo(), ConditionExpressionEnum.LIKE);
			list.add(vehicleNo);
		}
		// 运单号
		if (StringUtils.isNotBlank(signQueryReqDTO.getWaybillCode())) {
			SearchCondition waybillCode = SearchConditionUtils.newLikeCondition("code",
					signQueryReqDTO.getWaybillCode());
			list.add(waybillCode);
		}
		// 当前网点->>运单中的发货网点
		if (null != signQueryReqDTO.getCurDotId() && signQueryReqDTO.getCurDotId() > 0) {
			SearchCondition branchId = SearchConditionUtils.newEqualCondition("invoiceOrgId",
					signQueryReqDTO.getCurDotId());
			list.add(branchId);
		}

		if (null != signQueryReqDTO.getCompanyId() && signQueryReqDTO.getCompanyId() > 0) {
			SearchCondition companyId = SearchConditionUtils.newEqualCondition("companyId",
					signQueryReqDTO.getCompanyId());
			list.add(companyId);
		}
		// 签收状态
		if (signQueryReqDTO.getSignStatus() != null) {
			SearchCondition signStatus = SearchConditionUtils.newEqualCondition("signType",
					signQueryReqDTO.getSignStatus().getType());
			list.add(signStatus);
		} else {
			SearchCondition signStatus = SearchConditionUtils.newSearchCondition("signType", "",
					ConditionExpressionEnum.NOT_NULL);
			list.add(signStatus);
		}
		// 排除 已交接取消、未交接取消
		SearchCondition handovercancel = SearchConditionUtils.newSearchCondition("waybillStatus",
				WaybillStatusEnum.HANDOVERCANCEL.getType(), ConditionExpressionEnum.UNEQUAL);
		SearchCondition nothandovercancel = SearchConditionUtils.newSearchCondition("waybillStatus",
				WaybillStatusEnum.NOTHANDOVERCANCEL.getType(), ConditionExpressionEnum.UNEQUAL);
		list.add(handovercancel);
		list.add(nothandovercancel);
		return list;
	}

	/**
	 * 构建分页条件
	 *
	 * @param signQueryReqDTO
	 * @return
	 */
	private PageCondition buildPageCondition(SignQueryReqDTO signQueryReqDTO) {
		PageCondition pageCondition = new PageCondition();
		pageCondition.setCurrentPage(signQueryReqDTO.getCurrentPage() == null ? 1 : signQueryReqDTO.getCurrentPage());
		pageCondition.setPageSize(signQueryReqDTO.getPageSize() == null ? 10 : signQueryReqDTO.getPageSize());
		return pageCondition;
	}

	/**
	 * 构建排序条件
	 *
	 * @return
	 */
	private List<OrderCondition> buildOrderCondition() {
		List<OrderCondition> orderConditionList = new ArrayList<>();
		OrderCondition orderCondition = new OrderCondition();
		orderCondition.setFieldName("updateTime");
		orderCondition.setOrderCondition(SortEnum.DESC);
		orderConditionList.add(orderCondition);
		return orderConditionList;
	}
}
