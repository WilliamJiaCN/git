package com.hivescm.tms.finance.server.service.pcsign.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.framework.elasticsearch.utils.OrderConditionUtils;
import com.hivescm.framework.elasticsearch.utils.PageConditionUtils;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.AppQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.QuerySignBySignBatchNumberReq;
import com.hivescm.tms.api.dto.es.sign.request.QueryWaybillConditionsReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignInitializeWayBillReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignQueryWaybillReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.NoSignWaybillRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SelfSignRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignListRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SimpleWaybill4SignRespDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillTypeEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.pcsign.SelfDeliverySignService;
import com.hivescm.tms.finance.server.service.sign.EsSignDetailsService;
import com.hivescm.tms.finance.server.service.sign.EsSignFeeService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;

/**
 * 自提签收列表接口实现类
 * 
 * @author boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
@Service
public class SelfDeliverySignServiceImpl implements SelfDeliverySignService {

	private static Logger logger = LoggerFactory.getLogger(SelfDeliverySignServiceImpl.class);

	@Autowired
	private EsSignService esSignService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private EsSignDetailsService esSignDetailsService;
	@Autowired
	private EsSignFeeService esSignFeeService;

	@Override
	public SignListRespDTO getNoSign(PCSignReq pcSignReq) {
		try {
			SignListRespDTO respDTO = new SignListRespDTO();
			// ->组装查询条件
			QueryWaybillConditionsReqDTO req = this.getSearchCondition(pcSignReq);
			SignQueryWaybillReqDTO queryReq = new SignQueryWaybillReqDTO();
			queryReq.setSearchCondition(req.getSearchCondition());
			queryReq.setOrderCondition(req.getOrderCondition());
			queryReq.setPageCondition(req.getPageCondition());
			queryReq.setOrgId(pcSignReq.getCurrentDotId());
			queryReq.setCompanyId(pcSignReq.getCompanyId().intValue());
			// ->查询运单
			NoSignWaybillRespDTO waybills = waybillService.getWaybillNoSign(queryReq).getResult();
			if(null != waybills){
				List<SelfSignRespDTO> resp = compNoSignWaybill(waybills.getWaybills());
				respDTO.setSignRespDTO(resp);
				respDTO.setTotalCount(waybills.getTotalCount());
			}
			return respDTO;
		} catch (Exception e) {
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SEARCH_SIGN, e,
					"查询未签收运单失败,PCSignReq:%s", pcSignReq.toString());
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}

	/**
	 * 组装未签收返回数据
	 * @param waybills
	 * @return
	 */
	private List<SelfSignRespDTO>  compNoSignWaybill(List<TmsWaybillEsDTO> waybills){
		if(CollectionUtils.isEmpty(waybills)){
			return Collections.EMPTY_LIST;
		}
		List<SelfSignRespDTO> resp = new ArrayList<>();
		waybills.forEach(item -> {
			SelfSignRespDTO sign = new SelfSignRespDTO();
			SimpleWaybill4SignRespDTO waybill = EntityUtils.copyProperties(item, SimpleWaybill4SignRespDTO.class);

			sign.setWaybill(waybill);
			sign.getWaybill().setId(item.getId());
			sign.setWaybillId(item.getId());
			sign.setDeclarationsValue(null);// 声明价值
			sign.setStaySignWeight(item.getWaybillStockEsDto().getWeight()==null?new BigDecimal(0):item.getWaybillStockEsDto().getWeight());// 在库重量
			sign.setStaySignVolume(item.getWaybillStockEsDto().getVolume()==null?new BigDecimal(0):item.getWaybillStockEsDto().getVolume());// 在库体积
			sign.setActualCost(item.getCost()==null?new BigDecimal(0):item.getCost()
					.multiply((new BigDecimal(item.getWaybillStockEsDto().getPackageNum() / item.getTotalNum())))
					.setScale(2, BigDecimal.ROUND_HALF_UP));//实际费用计算
			sign.setStaySignNumber(item.getTotalNum()-(waybill.getTotalRefuseSignNumber()==null?0:waybill.getTotalRefuseSignNumber())-(waybill.getTotalSignNumber()==null?0:waybill.getTotalSignNumber()));
			if(!CollectionUtils.isEmpty(item.getWaybillFeeEsDtos())){
				Map<Integer,WaybillFeeEsDTO> map = item.getWaybillFeeEsDtos().stream().collect(Collectors.toMap(WaybillFeeEsDTO::getAttrId, a->a));
				sign.setCollectPayment(map.get(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType())==null?new BigDecimal(0):map.get(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType()).getFee());
			}
			resp.add(sign);
		});
		return resp;
		
	}
	@Override
	public SignListRespDTO getSignedAndBillPartialSignBill(PCSignReq pcSignReq) {
		try {
			SignListRespDTO respDTO = new SignListRespDTO();
			pcSignReq.setDeliveryType(WaybillDistributionTypeEnum.GET.getType());
			respDTO.setTotalCount(esSignService.countSignBills(pcSignReq));
			List<SignEsDTO> signs = esSignService.querySignEsByConditions(pcSignReq);
			if (CollectionUtils.isEmpty(signs)) {
				return respDTO;
			}
			respDTO.setSignRespDTO(this.compRespData(signs));
			return respDTO;
		} catch (Exception e) {
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SEARCH_SIGN, e,
					"查询已签收，部分签收，或者拒签失败,PCSignReq:%s", pcSignReq.toString());
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}

	@Override
	public TmsSignEsDTO getSignBill(QuerySignBySignBatchNumberReq req) {
		// -> 查询结果
		try {
			
			SignEsDTO signs = esSignService.querySignBySignBatchNumber(req.getSignCode(),req.getCompanyId());
			TmsSignEsDTO resp = compSignsData(signs);
			return resp;
		} catch (Exception e) {
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SEARCH_SIGN, e,
					"获取签收信息失败,waybillCode:%s", req.getSignCode());
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}

	@Override
	public TmsSignEsDTO addInitialize(SignInitializeWayBillReqDTO req) {
		// -> 查询结果
		try {
			TmsWaybillEsDTO signQueryWaybillResp = waybillService.queryWaybillDteails(req).getResult();
			TmsSignEsDTO tmsSignEsDTO = getWaybillByWaybillCode(signQueryWaybillResp);
			return tmsSignEsDTO;
		} catch (Exception e) {
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SEARCH_SIGN, e,
					"pc初始化签收信息失败,SignInitializeWayBillReqDTO:%s", req.toString());
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}

	/**
	 * 当查询条件中签收单号不为空时直接查询签收表 当查询条件中签收单号不为空时先查运单再查签收
	 */
	@Override
	public SignListRespDTO getAllBill(PCSignReq pcSignReq) {
		try {
			SignListRespDTO respDTO = new SignListRespDTO();
			//List<SignRespDTO> resp = new ArrayList<>();
			if ((null != pcSignReq.getStartTime() && pcSignReq.getStartTime() > 0
					&& null != pcSignReq.getEndTime() && pcSignReq.getEndTime() > 0)
					|| StringUtils.isNotBlank(pcSignReq.getSignBatchNumber())) {
				List<SignEsDTO> signs = esSignService.querySignEsByConditions(pcSignReq);
				// ->查询结果
				if (CollectionUtils.isEmpty(signs)) {
					return null;
				}
				respDTO.setSignRespDTO(this.compRespData(signs));
				respDTO.setTotalCount(this.esSignService.countSignBills(pcSignReq));
			} else {
				// ->组装查询条件
				// TODO
				//QueryWaybillConditionsReqDTO req = this.getSearchCondition(pcSignReq);
				// ->查询运单
				//List<WaybillEsDTO> signRespDTO = waybillService.queryWaybillByConditions(req);
				// getSignInfoByWaybillCode(signRespDTO);
			}
			return respDTO;
		} catch (Exception e) {
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SEARCH_SIGN, e,
					"自提签收全部列表查询失败,PCSignReq:%s", pcSignReq.toString());
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}


	/**
	 * 组合查询条件
	 * 
	 * @param
	 * @param
	 * @return
	 */
	private QueryWaybillConditionsReqDTO getSearchCondition(PCSignReq pcSignReq) {
		QueryWaybillConditionsReqDTO reqConditions = new QueryWaybillConditionsReqDTO();
		List<SearchCondition> scs = new ArrayList<>();
		// -> 目的网点
		if (!CollectionUtils.isEmpty(pcSignReq.getDestOrgId())) {
			SearchCondition scDest = new SearchCondition.Builder().setFieldName("destOrgId")
					.setConditionExpression(ConditionExpressionEnum.IN)
					.setFeldValues(pcSignReq.getDestOrgId().toArray()).build();
			scs.add(scDest);
		}
		//业务调整不需要此条件作为数据限制
//		if (null != pcSignReq.getCurrentDotId() && pcSignReq.getCurrentDotId() > 0) {
//			SearchCondition currDotid = new SearchCondition.Builder().setFieldName("destOrgId")
//					.setConditionExpression(ConditionExpressionEnum.EQUAL)
//					.setSingleValue(pcSignReq.getCurrentDotId().toString()).build();
//			scs.add(currDotid);
//		}
		// ->发货网点
		if (!CollectionUtils.isEmpty(pcSignReq.getInvoiceOrgId())) {
			SearchCondition scInvoice = new SearchCondition.Builder().setFieldName("invoiceOrgId")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setFeldValues(pcSignReq.getInvoiceOrgId().toArray()).build();
			scs.add(scInvoice);
		}

		// -> 收货人
		if (StringUtils.isNotBlank(pcSignReq.getReceiptUser())) {
			SearchCondition scReceipt = new SearchCondition.Builder().setFieldName("receiptUser")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(pcSignReq.getReceiptUser()).build();
			scs.add(scReceipt);
		}

		// ->运单号
		if (StringUtils.isNotBlank(pcSignReq.getWaybillCode())) {
			SearchCondition scWaybillCode = new SearchCondition.Builder().setFieldName("code")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(pcSignReq.getWaybillCode()).build();
			scs.add(scWaybillCode);
		}

		// ->录单日期
		if (null != pcSignReq.getStartTime() && null != pcSignReq.getEndTime()
				&& pcSignReq.getStartTime() > 0 && pcSignReq.getEndTime() > 0) {
			SearchCondition scInvoice = new SearchCondition.Builder().setFieldName("createTime")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(pcSignReq.getStartTime().toString())
					.setMxValue(pcSignReq.getEndTime().toString()).build();
			scs.add(scInvoice);
		}
		// 等通知不显示iwaitNotice
		SearchCondition iwaitNotice = new SearchCondition.Builder().setFieldName("iwaitNotice")
				.setConditionExpression(ConditionExpressionEnum.EQUAL)
				.setSingleValue(false).build();
		scs.add(iwaitNotice);
		//不同公司单号可能重复
		SearchCondition companyId = new SearchCondition.Builder().setFieldName("companyId")
				.setConditionExpression(ConditionExpressionEnum.EQUAL)
				.setSingleValue(pcSignReq.getCompanyId()).build();
		scs.add(companyId);
		// 默认查询零担运单
		List<Integer> wabillttype = new ArrayList<>();
		wabillttype.add(WaybillTypeEnum.SELFENTRANCE.getType());
		wabillttype.add(WaybillTypeEnum.APP.getType());
		SearchCondition waybillType = new SearchCondition.Builder().setFieldName("waybillType")
				.setConditionExpression(ConditionExpressionEnum.IN)
				.setFeldValues(wabillttype.toArray()).build();
		scs.add(waybillType);
		// 默认查自提的运单
		SearchCondition distributionType = new SearchCondition.Builder().setFieldName("distributionType")
				.setConditionExpression(ConditionExpressionEnum.EQUAL)
				.setSingleValue(WaybillDistributionTypeEnum.GET.getType()).build();
		scs.add(distributionType);
		//业务调整 不需要状态做条件
//		SearchCondition scStatus = new SearchCondition.Builder().setFieldName("status")
//				.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(WaybillStatusEnum.ARRIVED.getType()).build();
//		scs.add(scStatus);
		List<OrderCondition> orderCondition = OrderConditionUtils.start().addCondition("createTime", SortEnum.DESC)
				.end();
		PageCondition page = PageConditionUtils.create(pcSignReq.getPageSize(), pcSignReq.getCurrentPage());
		reqConditions.setSearchCondition(scs);
		reqConditions.setOrderCondition(orderCondition);
		reqConditions.setPageCondition(page);
		return reqConditions;
	}

	/**
	 * 根据运单号查询运单信息
	 * 
	 * @param
	 * @return
	 */
	private TmsSignEsDTO getWaybillByWaybillCode(TmsWaybillEsDTO signQueryWaybillResp) {
		TmsSignEsDTO resp = new TmsSignEsDTO();
		if (null == signQueryWaybillResp) {
			return null;
		}
		SignEsDTO signEsDTO = compwaybillData(signQueryWaybillResp);
		// -->组装货物明细信息
		if (null != signQueryWaybillResp.getWaybillStockDetailEsDTOs() && signQueryWaybillResp.getWaybillStockDetailEsDTOs().size() > 0) {
			List<SignDetailsEsDTO> list = new ArrayList<>();
			signQueryWaybillResp.getWaybillStockDetailEsDTOs().forEach(item -> {
				SignDetailsEsDTO details = new SignDetailsEsDTO();
				// TODO->补全库存件数
				details.setWaybillStockDetailId(item.getId());
				details.setWaybillGoodsId(item.getGoodsId());
				details.setGoodsName(item.getGoodsName());
				details.setPackages(item.getPackingName());
				details.setDispatcherNumber(item.getPackageNum());
				details.setCreateNumber(item.getTotalNum());
				list.add(details);
			});
			resp.setSignEsDTO(signEsDTO);
			resp.setSignDetailsEsDTO(list);
		}
		// ->查询费用项
		
		resp.setSignFeeEsDTO(compFee(signQueryWaybillResp.getWaybillFeeEsDtos()));
		return resp;
	}
	private SignEsDTO compwaybillData(TmsWaybillEsDTO signQueryWaybillResp){
		SignEsDTO signEsDTO = EntityUtils.copyProperties(signQueryWaybillResp, SignEsDTO.class);
		signEsDTO.setTotalRefuseSignNumber(signQueryWaybillResp.getTotalRefuseSignNumber());
		signEsDTO.setTotalSignNumber(signQueryWaybillResp.getTotalSignNumber());
		signEsDTO.setCreateNumber(signQueryWaybillResp.getTotalNum());
		signEsDTO.setOrderDate(signQueryWaybillResp.getCreateTime());
		signEsDTO.setInvoiceMobleNo(signQueryWaybillResp.getInvoiceMobleNo());
		signEsDTO.setReceiptConsignorMobleNo(signQueryWaybillResp.getReceiptConsignorMobleNo());
		signEsDTO.setInvoiceAddress(signQueryWaybillResp.getInvoiceAddress());
		signEsDTO.setUnsignedNumber(signQueryWaybillResp.getTotalNum()-(signQueryWaybillResp.getTotalSignNumber()==null?0:signQueryWaybillResp.getTotalSignNumber())-(signQueryWaybillResp.getTotalRefuseSignNumber()==null?0:signQueryWaybillResp.getTotalRefuseSignNumber()));
		return signEsDTO;
	}
	private SignFeeEsDTO compFee(List<WaybillFeeEsDTO> waybillFeeEsDtos){
		SignFeeEsDTO fee = new SignFeeEsDTO();
		if(CollectionUtils.isEmpty(waybillFeeEsDtos)){
			return fee;
		}
		Map<Integer, WaybillFeeEsDTO> waybillFee = waybillFeeEsDtos.stream()
				.collect(Collectors.toMap(WaybillFeeEsDTO::getAttrId, a -> a));
		// TODO费用
		fee.setCollectPayment(waybillFee.get(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType()) == null ? new BigDecimal(0)
				: waybillFee.get(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType()).getFee());
		return fee;
		
	}

	/**
	 * 组装返回数据
	 * 
	 * @param signs
	 * @return
	 */
	private List<SelfSignRespDTO> compRespData(List<SignEsDTO> signs) {
		List<Long> waybillIds = signs.stream().map(a -> a.getWaybillId()).distinct().filter(a->a!=null).collect(Collectors.toList());
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("id", waybillIds.toArray(), ConditionExpressionEnum.IN).end();
		QueryWaybillConditionsReqDTO req = new QueryWaybillConditionsReqDTO();
		req.setSearchCondition(scs);
		List<WaybillEsDTO> waybills = waybillService.queryWaybillByConditions(req);
		Map<Long, WaybillEsDTO> waybillMap = waybills.stream().collect(Collectors.toMap(WaybillEsDTO::getId, a -> a));
		List<SignFeeEsDTO> signFeeEsDTOs = this.esSignFeeService.querySignFeeEsDTOByWaybillId(waybillIds);
		Map<Long, SignFeeEsDTO> signFeeMaps = signFeeEsDTOs.stream().collect(Collectors.toMap(SignFeeEsDTO::getSignId,a->a));
		// ->查询结果
		List<SelfSignRespDTO> resp = new ArrayList<>();
		signs.forEach(a -> {
			SelfSignRespDTO sign = EntityUtils.copyProperties(a, SelfSignRespDTO.class);
			if(null != waybillMap.get(a.getWaybillId())){
				SimpleWaybill4SignRespDTO waybill = EntityUtils.copyProperties(waybillMap.get(a.getWaybillId()), SimpleWaybill4SignRespDTO.class);
				sign.setWaybill(waybill);
				sign.setStaySignNumber(sign.getWaybill().getTotalNum()-(sign.getWaybill().getTotalRefuseSignNumber()==null?0:sign.getWaybill().getTotalRefuseSignNumber())-(sign.getWaybill().getTotalSignNumber()==null?0:sign.getWaybill().getTotalSignNumber()));
				sign.setGoodsName(sign.getWaybill().getGoodsName());
			}
			SignFeeEsDTO fee = signFeeMaps.get(a.getId());
			sign.setDeliveryFee(fee.getDeliveryFee());
			sign.setToPay(fee.getToPay());
			sign.setCollectPayment(fee.getCollectPayment());
			resp.add(sign);
		});
		return resp;
	}

	/**
	 * 签收明细
	 * 
	 * @param signs
	 * @return
	 */
	private TmsSignEsDTO compSignsData(SignEsDTO signs) {
		if (null == signs) {
			return null;
		}
		WaybillEsDTO waybill = waybillService.queryWaybillEsDTO(signs.getWaybillId());
		List<SignDetailsEsDTO> details = esSignDetailsService.queryBySignId(signs.getId());
		SignFeeEsDTO fees = esSignFeeService.querySignFeeEsDTOBySignId(signs.getId());
		EntityUtils.copyProperties(waybill, signs);
		signs.setOrderDate(waybill.getCreateTime());
		signs.setTotalRefuseSignNumber(waybill.getTotalRefuseSignNumber());
		signs.setTotalSignNumber(waybill.getTotalSignNumber());
		signs.setUnsignedNumber(waybill.getTotalNum()-(waybill.getTotalSignNumber()==null?0:waybill.getTotalSignNumber())-(waybill.getTotalRefuseSignNumber()==null?0:waybill.getTotalRefuseSignNumber()));
		TmsSignEsDTO tmsSignEsDTO = new TmsSignEsDTO();
		tmsSignEsDTO.setSignEsDTO(signs);
		tmsSignEsDTO.setSignDetailsEsDTO(details);
		//一条签收单只有一条费用
		tmsSignEsDTO.setSignFeeEsDTO(fees);
		return tmsSignEsDTO;

	}

	@Override
	public SignListRespDTO getAppList(AppQueryReqDTO appQueryReqDTO) {
		SignListRespDTO respDTO = new SignListRespDTO();
		//未签收
		if(SignStatusEnum.NO_SIGN.getType() == appQueryReqDTO.getSignStatus().getType()){
			NoSignWaybillRespDTO appWaybillNoSign = waybillService.getAppWaybillNoSign(appQueryReqDTO).getResult();
			if(null != appWaybillNoSign){
				List<SelfSignRespDTO> resp = compNoSignWaybill(appWaybillNoSign.getWaybills());
				respDTO.setSignRespDTO(resp);
				respDTO.setTotalCount(appWaybillNoSign.getTotalCount());
			}
		}else{
			// 已签收，部分签收，拒签列表
			respDTO = getAppSignedAndBillPartialSignBill(appQueryReqDTO);
		}
		return respDTO;
	}
	/**
	 * app已签收，部分签收，拒签列表
	 * @param appQueryReqDTO
	 * @return
	 */
	private SignListRespDTO getAppSignedAndBillPartialSignBill(AppQueryReqDTO appQueryReqDTO){
		List<SignEsDTO> signs = new ArrayList<>();
		SignListRespDTO signListRespDTO = new SignListRespDTO();
		List<SearchCondition> condition = getCondition(appQueryReqDTO);
		List<OrderCondition> orderCondition = OrderConditionUtils.start().addCondition("createTime", SortEnum.DESC)
				.end();
		List<SearchCondition> searchConditioncode = SearchConditionUtils.start()
				.addCondition("code", appQueryReqDTO.getParam(),ConditionExpressionEnum.LIKE).end();
		searchConditioncode.addAll(condition);
		List<SignEsDTO> code = this.esSignService.querySignEsByConditions(searchConditioncode, orderCondition);
		if(!CollectionUtils.isEmpty(code)){
			signs.addAll(code);
		}
		List<SearchCondition> searchConditioncodeReceipt = SearchConditionUtils.start()
				.addCondition("receiptUser", appQueryReqDTO.getParam(),ConditionExpressionEnum.LIKE).end();
		searchConditioncodeReceipt.addAll(condition);
		List<SignEsDTO> receipt = this.esSignService.querySignEsByConditions(searchConditioncodeReceipt, orderCondition);
		if(!CollectionUtils.isEmpty(receipt)){
			signs.addAll(receipt);
		}
		List<SearchCondition> searchConditioncodeInviocer = SearchConditionUtils.start()
				.addCondition("invoiceUser", appQueryReqDTO.getParam(),ConditionExpressionEnum.LIKE).end();
		searchConditioncodeInviocer.addAll(condition);
		List<SignEsDTO> inviocer = this.esSignService.querySignEsByConditions(searchConditioncodeInviocer, orderCondition);
		if(!CollectionUtils.isEmpty(inviocer)){
			signs.addAll(inviocer);
		}
		if(CollectionUtils.isEmpty(signs)){
			return signListRespDTO;
		}
		List<SignEsDTO> signsDistinct = signs.stream().collect( Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getId()))),ArrayList<SignEsDTO>::new));
		List<SelfSignRespDTO> compRespData = this.compRespData(signsDistinct);
		List<SelfSignRespDTO> resp = new ArrayList<>();
		if(compRespData.size()>=appQueryReqDTO.getCurrentPage().intValue()*appQueryReqDTO.getPageSize().intValue()) {
			resp = compRespData.subList((appQueryReqDTO.getCurrentPage()-1)*appQueryReqDTO.getPageSize(), appQueryReqDTO.getCurrentPage()*appQueryReqDTO.getPageSize());
		}
		if(compRespData.size()<appQueryReqDTO.getCurrentPage().intValue()*appQueryReqDTO.getPageSize().intValue()) {
			resp = compRespData.subList((appQueryReqDTO.getCurrentPage()-1)*appQueryReqDTO.getPageSize(), compRespData.size());
		}
		signListRespDTO.setSignRespDTO(resp);
		signListRespDTO.setTotalCount(signsDistinct.size());
		return signListRespDTO;
	}
	/**
	 * 组装条件
	 * @param appQueryReqDTO
	 * @return
	 */
	private List<SearchCondition> getCondition(AppQueryReqDTO appQueryReqDTO){
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addEqualCondition("companyId", appQueryReqDTO.getCompanyId())
				.addEqualCondition("signStatus", appQueryReqDTO.getSignStatus().getType())
				.addEqualCondition("signOrgId",  appQueryReqDTO.getOrgId())
				.addEqualCondition("deliveryType", WaybillDistributionTypeEnum.GET.getType())
				.end();
		return scs;
	}
}
