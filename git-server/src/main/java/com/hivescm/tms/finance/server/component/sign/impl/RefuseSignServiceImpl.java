package com.hivescm.tms.finance.server.component.sign.impl;

import com.google.gson.Gson;
import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.PushMessageDto;
import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchAggregateExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchUpdateExecutor;
import com.hivescm.framework.elasticsearch.utils.OrderConditionUtils;
import com.hivescm.framework.elasticsearch.utils.PageConditionUtils;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherEsDTO;
import com.hivescm.tms.api.dto.es.order.redundancy.WriteMqInfoDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsGoodsDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.*;
import com.hivescm.tms.api.dto.es.sign.response.RefuseSignResultRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignBillEsRespDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.enums.biz.sign.SignGoodsTypeEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;
import com.hivescm.tms.constants.BusinessCodeConstants;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.finance.server.component.sign.GiveSignService;
import com.hivescm.tms.finance.server.component.sign.RefuseSignService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.dao.entity.sign.GoodsDetailsDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDOExample;
import com.hivescm.tms.finance.server.dao.mapper.sign.GoodsDetailsDOMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignRefuseDOMapper;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.sign.EsSignRefuseDetailService;
import com.hivescm.tms.finance.server.service.sign.EsSignRefuseService;
import com.hivescm.tms.intranet.gateway.api.dto.IdCode.IdCodeReqDTO;
import com.hivescm.tms.intranet.gateway.api.dto.message.AppMessageReqDTO;
import com.hivescm.tms.intranet.gateway.api.dto.oms.ISignRefuseDto;
import com.hivescm.tms.intranet.gateway.api.dto.oms.SignRefuseDetailDto;
import com.hivescm.tms.intranet.gateway.api.dto.oms.enums.ReturnReasonEnum;
import com.hivescm.tms.intranet.gateway.api.dto.oms.enums.ReturnTypeEnum;
import com.hivescm.tms.intranet.gateway.api.feign.IAppMessageService;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IWriteMqInfoService;
import com.hivescm.tms.intranet.gateway.api.feign.IdCodeService;
import com.hivescm.tms.intranet.gateway.api.feign.oms.IOmsService;
import com.hivescm.tms.utils.BeanMapUtils;
import com.hivescm.tms.utils.JsonUtil;
import com.hivescm.tms.utils.StringUtil;
import com.mogujie.distributed.transction.DynamicTransctionManagerFactory;
import com.mogujie.trade.utils.TransactionManagerUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 拒绝签收
 *
 * @author 杨彭伟
 * @since 2017/11/8 14:51
 */
@Service
public class RefuseSignServiceImpl implements RefuseSignService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RefuseSignServiceImpl.class);
    @Autowired
	private ESSearchService esSearchService;
	/**
	 * es统计服务
	 */
	@Autowired
	private ESStatisticService esStatisticService;
	@Autowired
	private IGeneratedIdService generatedIdService;
	@Autowired
	private IdCodeService idCodeService;
	@Autowired
	private SignRefuseDOMapper signRefuseDOMapper;
	@Autowired
	private GoodsDetailsDOMapper goodsDetailsDOMapper;
	@Autowired
	private DynamicTransctionManagerFactory dtmFactory;
	@Autowired
    private IOmsService omsService;
//	private ISalesReturnFeignService salesReturnFeignService;
//	@Autowired
//	private ReceiptControllerFeignService receiptControllerFeignService;
//	@Autowired
//	private PayControllerFeignService payControllerFeignService;
	@Autowired
	private IAppMessageService appMessageService;
	@Autowired
	private GiveSignService giveSignService;
//	@Autowired
//	private SignGetAmountService signGetAmountService;

	@Autowired
	private EsSignRefuseDetailService refuseSignGoodsService;

	@Autowired
	private EsSignRefuseService signRefuseService;
	@Autowired
	private DispatcherService dispatcherService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private IWriteMqInfoService iWriteMqInfoService;

	@Override
	public DataResult test() {
		PushMessageDto pushMessageDto = new PushMessageDto();
		try {
			Map<String, Object> info = new HashMap<>();
			info.put("pay", "100");// 应收金额
			info.put("id", "10");// 拒收单ID
			info.put("waybillId", "");// 运单ID
			info.put("noticeType", "100");// ?
			info.put("signType", "");// 签收类型：全部拒收、部分签收
			info.put("approveResult", "");// 审核结果：1 通过、 2 不通过

            AppMessageReqDTO appMessageReqDTO = new AppMessageReqDTO();
            appMessageReqDTO.setDriverId(145);
            appMessageReqDTO.setTitle("任务");
            appMessageReqDTO.setContent("您有一个拒收单已经审核通过！");
            appMessageReqDTO.setInfo(info);
            appMessageService.pushAppMessage(appMessageReqDTO);
		} catch (Exception e) {
			log.error("消息推送失败，消息信息为：" + pushMessageDto, e);
			e.printStackTrace();
		}
		return new DataResult();
	}

	@Override
	public Integer insertRefuseSignNew(RefuseForSignReqDTO refuseForSignReqDTO) {
		Long result = 0L;
		Long companyId = 0L;
		try {
			// >>校验参数
			checkParam(refuseForSignReqDTO);
			companyId = refuseForSignReqDTO.getCompanyId();
            WaybillEsDTO tmsWaybillEsDTO = getTmsWaybillEsDTO(refuseForSignReqDTO.getWaybillId());
			if (tmsWaybillEsDTO.getStatus().equals(WaybillStatusEnum.HANDOVERCANCEL.getType())||
					tmsWaybillEsDTO.getStatus().equals(WaybillStatusEnum.NOTHANDOVERCANCEL.getType())) {
				throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "运单已取消，不可签收");
			}
			DispatcherEsDTO dispatcherEsDTO = getDispatcherEsDTO(refuseForSignReqDTO.getDispatcherId());
			List<WaybillGoodsEsDTO> waybillGoodsEsDTOList = getWaybillGoodsEsDTOList(
					refuseForSignReqDTO.getWaybillId());
			// TODO
			SignRefuseEsDTO signRefuseEsDTO = new SignRefuseEsDTO();
			List<TmsGoodsDetailsEsDTO> refuseGoodsDetailsEsList = new ArrayList<>();
			List<GoodsDetailsDO> refuseGoodsDetailsList = new ArrayList<>();
			ISignRefuseDto signRefuseOMSDto = new ISignRefuseDto();
			// >>校验是否允许做拒收
			checkIfAllowed(tmsWaybillEsDTO, dispatcherEsDTO, waybillGoodsEsDTOList);
			// >>赋值DTO
            signRefuseOMSDto.setOriginalWaybillCode(tmsWaybillEsDTO.getCode());
            setData(refuseForSignReqDTO, tmsWaybillEsDTO, dispatcherEsDTO, waybillGoodsEsDTOList, companyId,
					signRefuseEsDTO, refuseGoodsDetailsEsList, refuseGoodsDetailsList, signRefuseOMSDto);

			// 保存数据库
			result = this.insetDB(refuseForSignReqDTO, signRefuseEsDTO, refuseGoodsDetailsList, signRefuseOMSDto);

			// 保存ES
			insertEs(signRefuseEsDTO, refuseGoodsDetailsEsList);
		} catch (SystemException e) {
			rollBackData(result, companyId);
			log.error("生成拒收单失败， 拒收单信息为：" + refuseForSignReqDTO, e);
			WriteMqInfoDTO record = new WriteMqInfoDTO();
			record.setOrderType("refuse_sign");
			record.setQueueName("refuse_sign");
			record.setMainInfo(JsonUtil.GsonString(refuseForSignReqDTO));
			record.setErrorReason(JsonUtil.GsonString(e.getErrorReason()));
			iWriteMqInfoService.insert(record);
			throw new SystemException(e.getErrorCode(), e.getErrorReason());
		}
		return result.intValue();
	}

	private void rollBackData(Long refuseId, Long companyId) {
		if (refuseId == null || refuseId <= 0) {
			return;
		}
		// 尝试回滚数据库
		try {
			signRefuseDOMapper.deleteByPrimaryKey(companyId, refuseId);
			goodsDetailsDOMapper.deleteByRefuseId(companyId, refuseId);
			signRefuseService.deleteSignRefuseEsDTO(refuseId);
			refuseSignGoodsService.deleteTmsGoodsDetailsEsDTOByRefuseId(refuseId);
		} catch (Exception e) {
			log.error("尝试回滚数据库失败：refuseId = {}, companyId = {}, message = {}", refuseId, companyId,
					e.getLocalizedMessage());
		}
	}

	private void setData(RefuseForSignReqDTO refuseForSignReqDTO, WaybillEsDTO tmsWaybillEsDTO,
						 DispatcherEsDTO dispatcherEsDTO, List<WaybillGoodsEsDTO> list, Long companyId,
						 SignRefuseEsDTO signRefuseEsDTO, List<TmsGoodsDetailsEsDTO> refuseGoodsDetailsEsList,
						 List<GoodsDetailsDO> refuseGoodsDetailsList, ISignRefuseDto signRefuseOMSDto) {

		Date now = new Date();
		// ------------- 拒收单
		// TODO 构建拒收单 ES DTO, DO
		getSignRefuseEsDTO(signRefuseEsDTO, refuseForSignReqDTO, tmsWaybillEsDTO, dispatcherEsDTO, companyId, now);
		String Cid = String.valueOf(companyId);
		Long refuseId = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_REFUSE_SIGN);
		 DataResult<String>  refuseCode = idCodeService.generated(new IdCodeReqDTO(BusinessCodeConstants.REFUSE_SIGN_CODE, Cid, null));
		signRefuseEsDTO.setId(refuseId);
		signRefuseEsDTO.setRefuseCode(refuseCode.getResult());
		buildRefuseGoods(refuseGoodsDetailsList, refuseGoodsDetailsEsList, signRefuseOMSDto, refuseForSignReqDTO,
				tmsWaybillEsDTO, list, now, refuseId);

		// TODO 设置拒收单主表的重量和体积
		setSignRefuseWeightAndVolume(signRefuseEsDTO, refuseGoodsDetailsEsList);
		signRefuseEsDTO.setList(new ArrayList<>());
	}

	// TODO SAVE TO ES
	private void insertEs(SignRefuseEsDTO signRefuseEsDTO, List<TmsGoodsDetailsEsDTO> refuseGoodsDetailsEsList) {
		// TODO SAVE 拒收单 TO ES
		Boolean refuseSign = signRefuseService.insertSignRefuseEsDTO(signRefuseEsDTO);
		// TODO SAVE 拒收商品 TO ES
		Boolean refuseGoods = refuseSignGoodsService.insertTmsGoodsDetailsEsDTOList(refuseGoodsDetailsEsList);
	}

	private Long insetDB(RefuseForSignReqDTO refuseForSignReqDTO, SignRefuseEsDTO signRefuseEsDTO,
                         List<GoodsDetailsDO> refuseGoodsDetailsList, ISignRefuseDto signRefuseOMSDto) {
		TransactionManagerUtils.TransactionProxy transactionProxy = getTransaction(refuseForSignReqDTO.getCompanyId());
		try {
			SignRefuseDO signRefuseDO = EntityUtils.copyProperties(signRefuseEsDTO, SignRefuseDO.class);
			int signRefuseSaveLine = signRefuseDOMapper.insertSelective(signRefuseDO);

			if (signRefuseSaveLine != 1) {
				SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN_SAVE_FAIL,
						"getReceiptList :param={" + refuseForSignReqDTO + "},error={  拒收单 插入 数据库失败 }");
				log.error(ex.getMessage());
				throw ex;
			}
			// TODO SAVE 拒收商品 TO DB
			int goodsSaveLine = goodsDetailsDOMapper.insertBatch(refuseForSignReqDTO.getCompanyId(),
					refuseGoodsDetailsList);

			if (goodsSaveLine < 1) {
				SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN_SAVE_FAIL,
						"getReceiptList :param={" + refuseForSignReqDTO + "},error={ 拒收单商品详情 插入 数据库失败 }");
				log.error(ex.getMessage());
				throw ex;
			}

			// 推送信息给OMS
            if (!omsService.sendToOMS(signRefuseOMSDto)) {
                WriteMqInfoDTO record = new WriteMqInfoDTO();
                record.setOrderType("refuse_send_oms");
                record.setQueueName("拒收单推送信息给OMS");
                record.setMainInfo(JsonUtil.GsonString(refuseForSignReqDTO));
                record.setErrorReason(signRefuseOMSDto.toString());
                iWriteMqInfoService.insert(record);
                log.error("sendToOMS fail, param: {}, request:{}", signRefuseOMSDto, refuseForSignReqDTO);
                throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN_SAVE_FAIL, "拒收单通知OMS失败");
            }
			// 提交事务
			transactionProxy.commit();
			return signRefuseDO.getId();
		} catch (Exception e) {
			transactionProxy.rollback();

			throw new SystemException(ExceptionCodeConstants.ERROR_ORDRE_RECEIPT,
					String.format("拒收单新增失败，数据库异常:%s", e.getLocalizedMessage()));
		}
	}

	private void checkParam(RefuseForSignReqDTO refuseForSignReqDTO) {
		if (refuseForSignReqDTO.getSignStatus() == SignStatusEnum.PARTIAL_SIGN.getType()) {
			List<RejectPackage> rejectPackages = refuseForSignReqDTO.getRejectPackages();
			if (rejectPackages == null || rejectPackages.size() == 0) {
				throw new SystemException(-1, "参数错误，部分签收时拒收商品不可为空");
			}
			for (RejectPackage rejectPackage : rejectPackages) {
				if (rejectPackage.getAmount() == null) {
					throw new SystemException(-1, "参数错误，数量不可为空");
				}
				if (StringUtil.isBlank(rejectPackage.getSkuid())) {
					throw new SystemException(-1, "参数错误，skuid 不可为空");
				}
				if (!StringUtil.isNumber(rejectPackage.getSkuid())) {
					throw new SystemException(-1, "参数错误，skuid 必须是数字");
				}
				if (StringUtil.isBlank(rejectPackage.getUnit())) {
					throw new SystemException(-1, "参数错误，单位不可为空");
				}
			}
		} else if (refuseForSignReqDTO.getSignStatus() == SignStatusEnum.REFUSE_SIGN.getType()) {

		} else {
			throw new SystemException(-1, "参数错误，签收类型错误");
		}

	}

	private void checkIfAllowed(WaybillEsDTO tmsWaybillEsDTO, DispatcherEsDTO dispatcherEsDTO,
                                List<WaybillGoodsEsDTO> waybillGoodsEsDTOList) {

		// TODO 1 根据运单id查询 运单信息 TmsWaybillEsDTO
		if (tmsWaybillEsDTO == null) {
			throw new SystemException(-1, "运单不存在");
		}

		// TODO 2 根据派车单id查询 派车单信息 DispatcherEsDTO
		if (dispatcherEsDTO == null) {
			throw new SystemException(-1, "派车单信息不存在");
		}
		// TODO 3 根据运单id查询 运单商品列表信息

		if (waybillGoodsEsDTOList == null || waybillGoodsEsDTOList.size() == 0) {
			throw new SystemException(-1, "待签收的货物为空，不可签收");
		}
		// 查询拒签单看是否这运单号已存在
		SignRefuseEsDTO queryRefuseSignEsDTO = this.queryRefuseSignEsDTO(tmsWaybillEsDTO);
		if (queryRefuseSignEsDTO != null) {
			throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN_SAVE_FAIL, "已生成拒签单，不能重复生成");
		}
	}

	/**
	 * 查询拒签单
	 * 
	 * @param tmsWaybillEsDTO
	 * @return
	 */
	private SignRefuseEsDTO queryRefuseSignEsDTO(WaybillEsDTO tmsWaybillEsDTO) {
		// 查询拒签单
		List<SearchCondition> s = SearchConditionUtils.start()
				.addCondition("waybillId", tmsWaybillEsDTO.getId(), ConditionExpressionEnum.EQUAL).end();
		SignRefuseEsDTO signRefuseEsDTO = new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.refuseSign();
			}
		}.get(s);
		return signRefuseEsDTO;
	}

	private void setSignRefuseWeightAndVolume(SignRefuseEsDTO signRefuseEsDTO,
			List<TmsGoodsDetailsEsDTO> refuseGoodsDetailsEsList) {
		Integer refuseNum = 0;
		Integer signNum = 0;
		BigDecimal weight = BigDecimal.ZERO;
		BigDecimal volume = BigDecimal.ZERO;
		if (CollectionUtils.isNotEmpty(refuseGoodsDetailsEsList)) {
			for (TmsGoodsDetailsEsDTO goodsDetailsEsDTO : refuseGoodsDetailsEsList) {
				if (goodsDetailsEsDTO.getGoodsType() == SignGoodsTypeEnum.REFUSE_SIGN.getType()) {
					if (goodsDetailsEsDTO.getRefuseNum() != null) {
						refuseNum = refuseNum + goodsDetailsEsDTO.getRefuseNum();
					}
					if (goodsDetailsEsDTO.getSignNum() != null) {
						signNum = signNum + goodsDetailsEsDTO.getSignNum();
					}
					weight = weight.add(goodsDetailsEsDTO.getRefuseWeight() != null
							? goodsDetailsEsDTO.getRefuseWeight() : BigDecimal.ZERO);
					volume = volume.add(goodsDetailsEsDTO.getRefuseVolume() != null
							? goodsDetailsEsDTO.getRefuseVolume() : BigDecimal.ZERO);
				}

			}
		}
		signRefuseEsDTO.setRefuseNumber(refuseNum);
		signRefuseEsDTO.setSignNumber(signNum);
		signRefuseEsDTO.setWeight(weight);
		signRefuseEsDTO.setVolume(volume);
	}

	private void buildRefuseGoods(List<GoodsDetailsDO> refuseGoodsDetailsList,
                                  List<TmsGoodsDetailsEsDTO> refuseGoodsDetailsEsList, ISignRefuseDto signRefuseDto,
                                  RefuseForSignReqDTO refuseForSignReqDTO, WaybillEsDTO tmsWaybillEsDTO, List<WaybillGoodsEsDTO> list,
                                  Date now, Long refuseId) {
		Long companyId = refuseForSignReqDTO.getCompanyId();
		if (refuseForSignReqDTO.getSignStatus() == SignStatusEnum.PARTIAL_SIGN.getType()) {
			buildPartRefuseGoods(refuseGoodsDetailsList, refuseGoodsDetailsEsList, signRefuseDto, refuseForSignReqDTO,
					tmsWaybillEsDTO, list, now, refuseId, companyId);
		} else if (refuseForSignReqDTO.getSignStatus() == SignStatusEnum.REFUSE_SIGN.getType()) {
			buildAllRefuseGoods(refuseGoodsDetailsList, refuseGoodsDetailsEsList, signRefuseDto, refuseForSignReqDTO,
					tmsWaybillEsDTO, list, now, refuseId);
		}
        WriteMqInfoDTO record = new WriteMqInfoDTO();
        record.setOrderType("buildRefuseGoods total");
        record.setQueueName("buildRefuseGoods total");
        record.setMainInfo(JsonUtil.GsonString(refuseForSignReqDTO));
        record.setErrorReason(JsonUtil.GsonString(signRefuseDto));
        iWriteMqInfoService.insert(record);
	}

	private void buildAllRefuseGoods(List<GoodsDetailsDO> refuseGoodsDetailsList,
                                     List<TmsGoodsDetailsEsDTO> refuseGoodsDetailsEsList, ISignRefuseDto signRefuseDto,
                                     RefuseForSignReqDTO refuseForSignReqDTO, WaybillEsDTO tmsWaybillEsDTO, List<WaybillGoodsEsDTO> list,
                                     Date now, Long refuseId) {

		if (list != null && list.size() != 0) {
			list.forEach(item -> {
				GoodsDetailsDO goodsDetailsDO = new GoodsDetailsDO();
				TmsGoodsDetailsEsDTO tmsGoodsDetailsEsDTO = new TmsGoodsDetailsEsDTO();
				EntityUtils.copyProperties(item, goodsDetailsDO);
				goodsDetailsDO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_GOODS_DETAILS));
				goodsDetailsDO.setRefuseId(refuseId);
				goodsDetailsDO.setWaybillGoodsId(item.getGoodsId());
				goodsDetailsDO.setWaybillId(item.getWaybillId());
				goodsDetailsDO.setGoodsName(item.getGoodsName());
				goodsDetailsDO.setCompanyId(item.getCompanyId().longValue());
				goodsDetailsDO.setGoodsType(SignGoodsTypeEnum.REFUSE_SIGN.getType());
				goodsDetailsDO.setCreateNum(item.getPackageNum());
				goodsDetailsDO.setDispacherNum(item.getPackageNum());
				goodsDetailsDO.setRefuseNum(item.getPackageNum());
				goodsDetailsDO.setSignNum(0);
				goodsDetailsDO.setRefuseWeight(item.getWeight());
				goodsDetailsDO.setRefuseVolume(item.getVolume());
				goodsDetailsDO.setPackages(item.getPackingName());
				goodsDetailsDO.setRefuseCause(refuseForSignReqDTO.getRefuseCause());

				goodsDetailsDO.setCreateUser(refuseForSignReqDTO.getUserId());
				goodsDetailsDO.setCreateTime(now.getTime());
				goodsDetailsDO.setUpdateUser(refuseForSignReqDTO.getUserId());
				goodsDetailsDO.setUpdateTime(now.getTime());

				refuseGoodsDetailsList.add(goodsDetailsDO);

				EntityUtils.copyProperties(goodsDetailsDO, tmsGoodsDetailsEsDTO);
				tmsGoodsDetailsEsDTO.setProdType(item.getProdType());
				tmsGoodsDetailsEsDTO.setProdTypeName(item.getProdTypeName());
				tmsGoodsDetailsEsDTO.setCode(tmsWaybillEsDTO.getCode());
				refuseGoodsDetailsEsList.add(tmsGoodsDetailsEsDTO);
			});
		}

		// ------------- 将全部拒收信息通知给OMS
		signRefuseDto.setRefuseType(ReturnTypeEnum.ALL_REFUSE);
		signRefuseDto.setReturnReason(getReturnReason(refuseForSignReqDTO.getRefuseCause()));
		signRefuseDto.setIloading(refuseForSignReqDTO.getLoaded());
        signRefuseDto.setOriginalWaybillCode(tmsWaybillEsDTO.getCode());
		String orderCode = tmsWaybillEsDTO.getOrderCode();
		Boolean isNumber = StringUtil.isNumber(orderCode);
		if (isNumber) {
			signRefuseDto.setOrderId(Long.valueOf(orderCode));
		} else {
			signRefuseDto.setOrderId(0L);
		}
        WriteMqInfoDTO record = new WriteMqInfoDTO();
        record.setOrderType("buildRefuseGoods All");
        record.setQueueName("buildRefuseGoods All");
        record.setMainInfo(JsonUtil.GsonString(refuseForSignReqDTO));
        record.setErrorReason(JsonUtil.GsonString(signRefuseDto));
        iWriteMqInfoService.insert(record);
	}

	private void buildPartRefuseGoods(List<GoodsDetailsDO> refuseGoodsDetailsList,
                                      List<TmsGoodsDetailsEsDTO> refuseGoodsDetailsEsList, ISignRefuseDto signRefuseDto,
                                      RefuseForSignReqDTO refuseForSignReqDTO, WaybillEsDTO tmsWaybillEsDTO, List<WaybillGoodsEsDTO> list,
                                      Date now, Long refuseId, Long companyId) {
		List<RejectPackage> rejectPackages = refuseForSignReqDTO.getRejectPackages();
		if (rejectPackages == null || rejectPackages.size() == 0) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN_SAVE_FAIL,
					"getReceiptList :param={" + refuseForSignReqDTO + "},error={ 拒收商品信息为空 }");
			log.error(ex.getMessage());
			throw ex;
		}
		List<WaybillGoodsEsDTO> waybillGoodsEsDTOList = getWaybillGoodsEsDTOList(refuseForSignReqDTO.getWaybillId());
		signRefuseDto.setRefuseType(ReturnTypeEnum.PATH_REFUSE);
		signRefuseDto.setIloading(refuseForSignReqDTO.getLoaded());
		String orderCode = tmsWaybillEsDTO.getOrderCode();
		if (StringUtil.isNumber(orderCode)) {
			signRefuseDto.setOrderId(Long.valueOf(orderCode));
		} else {
			signRefuseDto.setOrderId(0L);
		}
        signRefuseDto.setOriginalWaybillCode(tmsWaybillEsDTO.getCode());
        WriteMqInfoDTO record = new WriteMqInfoDTO();
        record.setOrderType("buildRefuseGoods Part");
        record.setQueueName("buildRefuseGoods Part");
        record.setMainInfo(JsonUtil.GsonString(refuseForSignReqDTO));
        record.setErrorReason(JsonUtil.GsonString(signRefuseDto));
        iWriteMqInfoService.insert(record);
		// 合并运单货物
		List<WaybillGoodsEsDTO> mergedWaybillGoodsEsDTOList = mergeWaybillGoodsEsDTO(waybillGoodsEsDTOList);
		// 合并拒收货物
		List<RejectPackage> mergedPackageList = mergeRefuseSignGoods(rejectPackages);

		List<SignRefuseDetailDto> srdList = new ArrayList<>(list.size());
		// TODO 拒收商品
		for (WaybillGoodsEsDTO waybillGoodsEsDTO : mergedWaybillGoodsEsDTOList) {
			for (RejectPackage rejectPackage : mergedPackageList) {
				TmsGoodsDetailsEsDTO tmsGoodsDetailsEsDTO = new TmsGoodsDetailsEsDTO();
				Long uniqueId = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_GOODS_DETAILS);
				EntityUtils.copyProperties(waybillGoodsEsDTO, tmsGoodsDetailsEsDTO);
				tmsGoodsDetailsEsDTO.setId(uniqueId);
				String skuid = rejectPackage.getSkuid();
				if (skuid.equals(waybillGoodsEsDTO.getSkuid())) {
					// TODO 拒收
					tmsGoodsDetailsEsDTO.setGoodsType(SignGoodsTypeEnum.REFUSE_SIGN.getType());
					// 开单件数
					Integer createNum = waybillGoodsEsDTO.getPackageNum();
					// 签收件数
					Integer signNum = rejectPackage.getAmount();
					Integer rejectNum = createNum - signNum;
					if (rejectNum > createNum) {
						// TODO 拒收件数不能大于开单件数
						SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN_SAVE_FAIL,
								"getReceiptList :param={" + refuseForSignReqDTO + "},error={ 拒收件数不能大于开单件数 }");
						log.error(ex.getMessage());
						throw ex;
					}
					tmsGoodsDetailsEsDTO.setWaybillId(waybillGoodsEsDTO.getWaybillId());
					// 运单号
					tmsGoodsDetailsEsDTO.setCode(tmsWaybillEsDTO.getCode());
					tmsGoodsDetailsEsDTO.setDispacherNum(createNum);
					tmsGoodsDetailsEsDTO.setWaybillGoodsId(waybillGoodsEsDTO.getGoodsId());
					tmsGoodsDetailsEsDTO.setBoxNum(getBoxNumByPackageId(rejectPackage.getPackageId()));
					tmsGoodsDetailsEsDTO.setRefuseCause(rejectPackage.getRefuseCause());
					tmsGoodsDetailsEsDTO.setCreateNum(createNum);
					tmsGoodsDetailsEsDTO.setDispacherNum(createNum);
					tmsGoodsDetailsEsDTO.setSignNum(signNum);
					tmsGoodsDetailsEsDTO.setRefuseNum(rejectNum);
					tmsGoodsDetailsEsDTO.setRefuseId(refuseId);
					tmsGoodsDetailsEsDTO.setCompanyId(companyId);
					tmsGoodsDetailsEsDTO.setCreateTime(now.getTime());
					tmsGoodsDetailsEsDTO.setCreateUser(refuseForSignReqDTO.getUserId());
					tmsGoodsDetailsEsDTO.setUpdateTime(now.getTime());
					tmsGoodsDetailsEsDTO.setUpdateUser(refuseForSignReqDTO.getUserId());
					tmsGoodsDetailsEsDTO.setPackages(waybillGoodsEsDTO.getPackingName());

					// 商品类型
					tmsGoodsDetailsEsDTO.setProdType(waybillGoodsEsDTO.getProdType());
					tmsGoodsDetailsEsDTO.setProdTypeName(waybillGoodsEsDTO.getProdTypeName());
					tmsGoodsDetailsEsDTO.setRemark(waybillGoodsEsDTO.getRemark());

					BigDecimal refuseWeight, refuseVolume;
					if (waybillGoodsEsDTO.getWeight() != null) {
						refuseWeight = waybillGoodsEsDTO.getWeight().multiply(new BigDecimal(rejectNum))
								.divide(new BigDecimal(createNum), 2, RoundingMode.HALF_UP);
					} else {
						refuseWeight = BigDecimal.ZERO;
					}
					// 拒收物品总重量
					tmsGoodsDetailsEsDTO.setRefuseWeight(refuseWeight);

					if (waybillGoodsEsDTO.getVolume() != null) {
						refuseVolume = waybillGoodsEsDTO.getVolume().multiply(new BigDecimal(rejectNum))
								.divide(new BigDecimal(createNum), 2, RoundingMode.HALF_UP);
					} else {
						refuseVolume = BigDecimal.ZERO;
					}
					// 拒收物品总体积
					tmsGoodsDetailsEsDTO.setRefuseVolume(refuseVolume);
					// TODO OMS拒收商品详情
					SignRefuseDetailDto signRefuseDetailDto = new SignRefuseDetailDto();
					if (StringUtil.isNumber(skuid)) {
						signRefuseDetailDto.setSkuId(Long.valueOf(skuid));
					} else {
						signRefuseDetailDto.setSkuId(0L);
					}
					// TODO 设置拒收商品数量
					if (rejectNum != 0) {
						signRefuseDetailDto.setQty(rejectNum);
						signRefuseDetailDto.setReturnReason(getReturnReason(rejectPackage.getRefuseCause()));

						srdList.add(signRefuseDetailDto);
						signRefuseDto.setReturnReason(getReturnReason(rejectPackage.getRefuseCause()));

					}
				} else {
					continue;
				}
				refuseGoodsDetailsEsList.add(tmsGoodsDetailsEsDTO);
				GoodsDetailsDO goodsDetailsDO = new GoodsDetailsDO();
				EntityUtils.copyProperties(tmsGoodsDetailsEsDTO, goodsDetailsDO);
				refuseGoodsDetailsList.add(goodsDetailsDO);
			}
		}
		// TODO 签收商品
		for (WaybillGoodsEsDTO waybillGoodsEsDTO : mergedWaybillGoodsEsDTOList) {
			TmsGoodsDetailsEsDTO tmsGoodsDetailsEsDTO = new TmsGoodsDetailsEsDTO();
			// 开单件数
			Integer createNum = waybillGoodsEsDTO.getPackageNum();
			Long uniqueId = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_GOODS_DETAILS);
			EntityUtils.copyProperties(waybillGoodsEsDTO, tmsGoodsDetailsEsDTO);

			tmsGoodsDetailsEsDTO.setId(uniqueId);
			tmsGoodsDetailsEsDTO.setWaybillId(waybillGoodsEsDTO.getWaybillId());
			tmsGoodsDetailsEsDTO.setWaybillGoodsId(waybillGoodsEsDTO.getGoodsId());
			tmsGoodsDetailsEsDTO.setGoodsType(SignGoodsTypeEnum.SIGN.getType());
			tmsGoodsDetailsEsDTO.setCreateNum(createNum);
			tmsGoodsDetailsEsDTO.setDispacherNum(createNum);
			tmsGoodsDetailsEsDTO.setSignNum(createNum);
			tmsGoodsDetailsEsDTO.setRefuseNum(0);
			tmsGoodsDetailsEsDTO.setRefuseWeight(waybillGoodsEsDTO.getWeight());
			tmsGoodsDetailsEsDTO.setRefuseVolume(waybillGoodsEsDTO.getVolume());
			tmsGoodsDetailsEsDTO.setCompanyId(companyId);
			tmsGoodsDetailsEsDTO.setRemark(waybillGoodsEsDTO.getRemark());
			tmsGoodsDetailsEsDTO.setCreateTime(now.getTime());
			tmsGoodsDetailsEsDTO.setCreateUser(refuseForSignReqDTO.getUserId());
			tmsGoodsDetailsEsDTO.setUpdateTime(now.getTime());
			tmsGoodsDetailsEsDTO.setUpdateUser(refuseForSignReqDTO.getUserId());
            tmsGoodsDetailsEsDTO.setSkuid(waybillGoodsEsDTO.getSkuid());
            tmsGoodsDetailsEsDTO.setUnit(waybillGoodsEsDTO.getUnit());
			tmsGoodsDetailsEsDTO.setProdType(waybillGoodsEsDTO.getProdType());
			tmsGoodsDetailsEsDTO.setProdTypeName(waybillGoodsEsDTO.getProdTypeName());
			
			for (RejectPackage rejectPackage : mergedPackageList) {
				if (rejectPackage.getSkuid().equals(waybillGoodsEsDTO.getSkuid())) {
					// 拒收件数
					Integer signNum = rejectPackage.getAmount();
					Integer rejectNum = createNum - signNum;
					if (rejectNum > 0) {
						tmsGoodsDetailsEsDTO.setSignNum(signNum);
						tmsGoodsDetailsEsDTO.setRefuseNum(rejectNum);
						BigDecimal refuseWeight, refuseVolume;
						if (waybillGoodsEsDTO.getWeight() != null) {
							refuseWeight = waybillGoodsEsDTO.getWeight().multiply(new BigDecimal(signNum))
									.divide(new BigDecimal(createNum), 2, RoundingMode.HALF_UP);
						} else {
							refuseWeight = BigDecimal.ZERO;
						}
						// 签收物品总重量
						tmsGoodsDetailsEsDTO.setRefuseWeight(refuseWeight);

						if (waybillGoodsEsDTO.getVolume() != null) {
							refuseVolume = waybillGoodsEsDTO.getVolume().multiply(new BigDecimal(signNum))
									.divide(new BigDecimal(createNum), 2, RoundingMode.HALF_UP);
						} else {
							refuseVolume = BigDecimal.ZERO;
						}
						// 签收物品总体积
						tmsGoodsDetailsEsDTO.setRefuseVolume(refuseVolume);
						tmsGoodsDetailsEsDTO.setRefuseCause(rejectPackage.getRefuseCause());
					}
				}
			}
			refuseGoodsDetailsEsList.add(tmsGoodsDetailsEsDTO);
			GoodsDetailsDO goodsDetailsDO = new GoodsDetailsDO();
			EntityUtils.copyProperties(tmsGoodsDetailsEsDTO, goodsDetailsDO);
			refuseGoodsDetailsList.add(goodsDetailsDO);
		}

		if (refuseGoodsDetailsList.size() == 0) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN_SAVE_FAIL,
					"getReceiptList :param={" + refuseForSignReqDTO + "},error={ 部分拒收计算的货物为空 }");
			log.error(ex.getMessage());
			throw ex;
		}
		// ------------- 将部分拒收信息通知给OMS
		signRefuseDto.setSrdList(srdList);
	}

	/**
	 * 根据skuid 合并运单货物明细
	 * @param goodsEsDTOList
	 * @return
	 */
	private List<WaybillGoodsEsDTO> mergeWaybillGoodsEsDTO(List<WaybillGoodsEsDTO> goodsEsDTOList) {
		List<WaybillGoodsEsDTO> detailDtos = new ArrayList<>();
		Set<String> set = new HashSet<>();
		goodsEsDTOList.forEach(item-> set.add(item.getSkuid()));
		if (set.size() == goodsEsDTOList.size()) {
			return goodsEsDTOList;
		}
		Map<String, List<WaybillGoodsEsDTO>> listMap = goodsEsDTOList.stream()
				.collect(Collectors.groupingBy(WaybillGoodsEsDTO::getSkuid, Collectors.toList()));

		listMap.forEach((k, v) -> {
			int packageNum = v.stream()
					.filter(i -> i.getPackageNum() != null)
					.mapToInt(WaybillGoodsEsDTO::getPackageNum).sum();
			int deliveryNum = v.stream()
					.filter(i -> i.getDeliveryNum() != null)
					.mapToInt(WaybillGoodsEsDTO::getDeliveryNum).sum();
			BigDecimal volume = v.stream()
					.filter(i -> i.getVolume() != null)
					.map(WaybillGoodsEsDTO::getVolume)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal weight = v.stream()
					.filter(i -> i.getWeight() != null)
					.map(WaybillGoodsEsDTO::getWeight)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			WaybillGoodsEsDTO dto = EntityUtils.copyProperties(v.get(0), WaybillGoodsEsDTO.class);
			dto.setPackageNum(packageNum);
			dto.setDeliveryNum(deliveryNum);
			dto.setVolume(volume);
			dto.setWeight(weight);
			detailDtos.add(dto);
		});
        log.info("根据skuid 合并运单货物明细, {}", detailDtos);
		return detailDtos;
	}

	/**
	 * 根据skuid 合并签收货物
	 * @param goodsEsDTOList
	 * @return
	 */
	private List<RejectPackage> mergeRefuseSignGoods(List<RejectPackage> goodsEsDTOList) {
		List<RejectPackage> detailDtos = new ArrayList<>();
		Set<String> set = new HashSet<>();
		goodsEsDTOList.forEach(item-> set.add(item.getSkuid()));
		if (set.size() == goodsEsDTOList.size()) {
			return goodsEsDTOList;
		}
		Map<String, List<RejectPackage>> listMap = goodsEsDTOList.stream()
				.collect(Collectors.groupingBy(RejectPackage::getSkuid, Collectors.toList()));

		listMap.forEach((k, v) -> {
			int amount = v.stream().mapToInt(RejectPackage::getAmount).sum();
			RejectPackage dto = EntityUtils.copyProperties(v.get(0), RejectPackage.class);
			dto.setAmount(amount);
			detailDtos.add(dto);
		});
        log.info("根据skuid 合并签收货物, {}", detailDtos);
		return detailDtos;
	}

	private void getSignRefuseEsDTO(SignRefuseEsDTO signRefuseEsDTO, RefuseForSignReqDTO refuseForSignReqDTO,
                                    WaybillEsDTO tmsWaybillEsDTO, DispatcherEsDTO dispatcherEsDTO, Long companyId, Date now) {

		// 获取代收货款
        BigDecimal collectPayment = getCollectPayment(refuseForSignReqDTO.getWaybillId());
        signRefuseEsDTO.setCollectPayment(collectPayment);
		signRefuseEsDTO.setCompanyId(companyId);
		signRefuseEsDTO.setRefuseType(refuseForSignReqDTO.getSignStatus());
		signRefuseEsDTO.setSettlementMethod(refuseForSignReqDTO.getSettlementMethod());
		signRefuseEsDTO.setLoaded(refuseForSignReqDTO.getLoaded());
		signRefuseEsDTO.setRefuseTime(now);
		signRefuseEsDTO.setWaybillId(tmsWaybillEsDTO.getId());
		signRefuseEsDTO.setOrderCode(tmsWaybillEsDTO.getOrderCode());
		// 运单号
		signRefuseEsDTO.setCode(tmsWaybillEsDTO.getCode());
		// signRefuseEsDTO.setOrderPay(tmsWaybillEsDTO.getGoodsPayment());
		// 派车网点
		signRefuseEsDTO.setBranchName(dispatcherEsDTO.getBranchName());
		signRefuseEsDTO.setBranchId(dispatcherEsDTO.getBranchId());
		// 收货公司
		signRefuseEsDTO.setDestOrgName(tmsWaybillEsDTO.getReceiptCompany());
		// 收货人
		signRefuseEsDTO.setReceiptUser(tmsWaybillEsDTO.getReceiptUser());
		signRefuseEsDTO.setDistributionType(tmsWaybillEsDTO.getDistributionType());
		signRefuseEsDTO.setReceiptConsignorTelNo(tmsWaybillEsDTO.getReceiptConsignorTelNo());

		signRefuseEsDTO.setCarrierName(tmsWaybillEsDTO.getCarrierName());
		signRefuseEsDTO.setDestName(tmsWaybillEsDTO.getReceiptAddress());
		// signRefuseEsDTO.setDestOrgName(tmsWaybillEsDTO.getDestOrgName());
		// 拒收人
		signRefuseEsDTO.setRefusePeople(tmsWaybillEsDTO.getReceiptUser());
		signRefuseEsDTO.setRefuseCard(tmsWaybillEsDTO.getReceiptIdentityCard());
		signRefuseEsDTO.setRefusePhone(tmsWaybillEsDTO.getReceiptConsignorMobleNo());
		signRefuseEsDTO.setDispatcherId(dispatcherEsDTO.getId());
		signRefuseEsDTO.setDispacherCode(dispatcherEsDTO.getId());
		// 派车批次号
		signRefuseEsDTO.setBatchCode(dispatcherEsDTO.getBatchCode());
		signRefuseEsDTO.setDriverId(dispatcherEsDTO.getDriverId().longValue());
		signRefuseEsDTO.setCarName(dispatcherEsDTO.getDriverName());
		signRefuseEsDTO.setCarNumber(dispatcherEsDTO.getVehicleNo());
		signRefuseEsDTO.setCarPhone(dispatcherEsDTO.getPhoneNo());
		signRefuseEsDTO.setCarrierName(tmsWaybillEsDTO.getCarrierGlobalName());

		signRefuseEsDTO.setInvoiceCustomerId(tmsWaybillEsDTO.getInvoiceCustomerId());
		// 发货人
		signRefuseEsDTO.setInvoiceName(tmsWaybillEsDTO.getInvoiceUser());
		signRefuseEsDTO.setInvoiceNamePhone(tmsWaybillEsDTO.getInvoiceMobleNo());
		signRefuseEsDTO.setInvoicewayName(tmsWaybillEsDTO.getInvoiceOrgName());
		signRefuseEsDTO.setInvoiceAddress(tmsWaybillEsDTO.getInvoiceAddress());
		signRefuseEsDTO.setInvoiceCompany(tmsWaybillEsDTO.getInvoiceCompany());
		signRefuseEsDTO.setWarehouseName(tmsWaybillEsDTO.getWarehouseName());
		signRefuseEsDTO.setWarehouseServerName(tmsWaybillEsDTO.getWarehouseServerName());
		signRefuseEsDTO.setMerchantName(tmsWaybillEsDTO.getMerchantName());
		signRefuseEsDTO.setStoreName(tmsWaybillEsDTO.getStoreName());
		signRefuseEsDTO.setReceiptUser(tmsWaybillEsDTO.getReceiptUser());
		signRefuseEsDTO.setRefuseCard(tmsWaybillEsDTO.getReceiptIdentityCard());
		signRefuseEsDTO.setGoodsName(tmsWaybillEsDTO.getGoodsName());
		signRefuseEsDTO.setCreateNumber(tmsWaybillEsDTO.getTotalNum());
		signRefuseEsDTO.setDispatcherNumber(tmsWaybillEsDTO.getTotalNum());

		signRefuseEsDTO.setCreateTime(now.getTime());
		signRefuseEsDTO.setUpdateTime(now.getTime());
		signRefuseEsDTO.setCreateUser(refuseForSignReqDTO.getUserId());
		signRefuseEsDTO.setUpdateUser(refuseForSignReqDTO.getUserId());
		signRefuseEsDTO.setDispacherCode(dispatcherEsDTO.getId());

		signRefuseEsDTO.setReceiptConsignorMobleNo(tmsWaybillEsDTO.getReceiptConsignorMobleNo());
		signRefuseEsDTO.setReceiptAddress(tmsWaybillEsDTO.getReceiptAddress());
	}

	private ReturnReasonEnum getReturnReason(String value) {

		if (value==null){
			return ReturnReasonEnum.OTHER;
		}
		switch (value) {
		case "质量问题":
			return ReturnReasonEnum.QUALITY_PROBLEM;
		case "保质过期":
			return ReturnReasonEnum.EXPIRED;
		case "重复下单":
			return ReturnReasonEnum.DOUBLE_ORDERING;
		case "滞销":
			return ReturnReasonEnum.UNSALABLE;
		case "其他":
			return ReturnReasonEnum.OTHER;
		default:
			return ReturnReasonEnum.OTHER;
		}
	}

	/**
	 * 根据包裹id查询包裹号
	 * 
	 * @param packageId
	 * @return
	 */
	private String getBoxNumByPackageId(Long packageId) {
//		if (packageId == null) {
//			return null;
//		}
//		PackageDetailEsDTO packageDetailEsDTO = new DefaultAbstractSearchQueryExecutor<PackageDetailEsDTO>(
//				esSearchService) {
//			@Override
//			public EsConfig getConfig() {
//				return EsConfig.packageDetail();
//			}
//		}.get(packageId);
//		if (packageDetailEsDTO != null) {
//			return packageDetailEsDTO.getPackageCode();
//		} else {
			return null;
//		}
	}

	/**
	 * 接收OMS通知，修改拒收单 拒收商品金额
	 * 
	 * @param refuseSignNoticeReqDTO
	 * @return
	 */
	@SuppressWarnings("Duplicates")
	@Override
	public DataResult<Boolean> updateRefuseSign(RefuseSignNoticeReqDTO refuseSignNoticeReqDTO) {
		WriteMqInfoDTO record = new WriteMqInfoDTO();
		record.setOrderType("updateRefuseSign");
		record.setQueueName("拒收单receive notice");
		record.setMainInfo(JsonUtil.GsonString(refuseSignNoticeReqDTO));
		record.setErrorReason("1." + JsonUtil.GsonString(refuseSignNoticeReqDTO));
		iWriteMqInfoService.insert(record);
		if (refuseSignNoticeReqDTO.getRefusePay() == null) {
			return DataResult.faild(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "拒收金额不可为空");
		}
		if (BigDecimal.ZERO.compareTo(refuseSignNoticeReqDTO.getRefusePay()) > 0) {
			return DataResult.faild(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "拒收金额不可为负数");
		}
//        WaybillEsDTO waybillEsDTO = getTmsWaybillEsDTOByOrderCode
//				(refuseSignNoticeReqDTO.getCustomerOrderCode());
		//2018-06-09 zouhx，添加公司id
		WaybillEsDTO waybillEsDTO = getTmsWaybillEsDTOByWaybillCode(refuseSignNoticeReqDTO.getCode(),refuseSignNoticeReqDTO.getCompanyId());

		if (waybillEsDTO == null) {
			return DataResult.faild(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "运单信息不存在");
		}
		// TODO 1 根据原运单id 取到拒收单id
		List<SearchCondition> searchConditions = SearchConditionUtils.start()
				.addCondition("waybillId", waybillEsDTO.getId(), ConditionExpressionEnum.EQUAL)
				.addCondition("signId", null, ConditionExpressionEnum.NULL).end();

		List<SignEsDTO> list = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.sign();
			}
		}.list(searchConditions);
		if (CollectionUtils.isNotEmpty(list)) {
			return DataResult.faild(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "已经生成签收单不能重复签收！");
		}
		// if (list.size() > 1) {
		// // todo 缺少提示 拒收单数据出错，一个运单只能拒收一次
		// return DataResult.faild(ExceptionCodeConstants.ERROR_PACKAGE_EMPTY,
		// "拒收单数据出错，一个运单只能拒收一次");
		// }
		List<SignRefuseEsDTO> refuseList = new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.refuseSign();
			}
		}.list(searchConditions);
		BigDecimal refusePay = refuseSignNoticeReqDTO.getRefusePay();
		if (refuseList == null || refuseList.size() == 0) {
			return DataResult.faild(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "通过审核拒收单失败，拒收单不存在！");
		}
		SignRefuseEsDTO signRefuseEsDTO = refuseList.get(refuseList.size() - 1);
		SignRefuseEsDTO rollBackRefuseEsDTO = EntityUtils.copyProperties(signRefuseEsDTO, SignRefuseEsDTO.class);
		signRefuseEsDTO.setCompanyId(signRefuseEsDTO.getCompanyId().longValue());
		signRefuseEsDTO.setRefusePay(refusePay);
		signRefuseEsDTO.setSalesReturnId(refuseSignNoticeReqDTO.getSalesReturnId());
		Long id = signRefuseEsDTO.getId();

		// TODO 2 根据拒收单id 修改拒收金额
		SignRefuseDO signRefuseDO = new SignRefuseDO();
		signRefuseDO.setId(id);
		signRefuseDO.setRefuseWaybillId(refuseSignNoticeReqDTO.getRefuseSignWaybillId());
		signRefuseDO.setSalesReturnId(refuseSignNoticeReqDTO.getSalesReturnId());
		signRefuseDO.setRefusePay(refusePay);
		// 代收货款
		BigDecimal collectPayment = getCollectPayment(waybillEsDTO.getId());
		if (collectPayment == null) {
			return DataResult.faild(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "数据异常，代收货款不可为空");
		}
		BigDecimal orderPay = collectPayment.subtract(refusePay);
		if (orderPay.compareTo(BigDecimal.ZERO) < 0) {
			// return DataResult.faild(ExceptionCodeConstants.ERROR_REFUSE_SIGN,
			// "数据异常，实际代收货款不可为负");
			orderPay = BigDecimal.ZERO;
		}
		signRefuseDO.setOrderPay(orderPay);
		signRefuseDO.setCompanyId(signRefuseEsDTO.getCompanyId());
		// >> 开启事务
		TransactionManagerUtils.TransactionProxy transactionProxy = this.getTransaction(signRefuseEsDTO.getCompanyId());
		try {
			int i = signRefuseDOMapper.updateByPrimaryKeySelective(signRefuseDO);
			if (i != 1) {
				throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "DB 更新拒收单状态失败");
			}
			// 同步ES
			// -> ES
			SignRefuseEsDTO updateEsDTO = new SignRefuseEsDTO();
			updateEsDTO.setId(id);
			updateEsDTO.setRefuseWaybillId(refuseSignNoticeReqDTO.getRefuseSignWaybillId());
			updateEsDTO.setRefusePay(refuseSignNoticeReqDTO.getRefusePay());
			updateEsDTO.setOrderPay(orderPay);
			Boolean execute = new DefaultAbstractSearchUpdateExecutor<SignRefuseEsDTO>(esSearchService) {
				@Override
				public EsConfig getConfig() {
					return EsConfig.refuseSign();
				}
			}.execute(updateEsDTO);
			if (!execute) {
				throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "ES 更新拒收单状态失败");
			}
			// TODO 全部拒收
			if (signRefuseEsDTO.getRefuseType() == SignStatusEnum.REFUSE_SIGN.getType()) {
				refuseSignForPush(refuseSignNoticeReqDTO, signRefuseEsDTO, id, BigDecimal.ZERO);
			} else if (signRefuseEsDTO.getRefuseType() == SignStatusEnum.PARTIAL_SIGN.getType()) {
				if (orderPay.compareTo(BigDecimal.ZERO) < 0) {
					throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "实收代收货款不可为负数");
				}
				// TODO 部分签收
				if (orderPay.compareTo(BigDecimal.ZERO) == 0) {
					refuseSignForPush(refuseSignNoticeReqDTO, signRefuseEsDTO, id, orderPay);
				} else if (orderPay.compareTo(BigDecimal.ZERO) > 0) {
					// 不去签收，等待付款
					SignBillEsRespDTO signBillEsRespDTO = new SignBillEsRespDTO();
					signBillEsRespDTO.setLoaded(signRefuseEsDTO.getLoaded());
					signBillEsRespDTO.setSettlementMethod(signRefuseEsDTO.getSettlementMethod());
					signBillEsRespDTO.setSignType(signRefuseEsDTO.getRefuseType());
					signBillEsRespDTO.setWayBillId(signRefuseEsDTO.getWaybillId());
					// 装车
					if (signRefuseEsDTO.getLoaded()) {
						if (orderPay.compareTo(BigDecimal.ZERO) == 0) {
							signBillEsRespDTO.setFlag(23);
						} else {
							signBillEsRespDTO.setFlag(22);
						}
						if (refuseSignNoticeReqDTO.getDispatcherId() == null) {
							signBillEsRespDTO.setFlag(40);
						}
					} else {
						// 不装车
						if (orderPay.compareTo(BigDecimal.ZERO) == 0) {
							signBillEsRespDTO.setFlag(21);
						} else {
							signBillEsRespDTO.setFlag(20);
						}
					}

					Map<String, Object> info = BeanMapUtils.objectToMap(signBillEsRespDTO);
					info.put("pay", orderPay);
					info.put("driverId", signRefuseEsDTO.getDriverId());
					info.put("refuseWaybillId", refuseSignNoticeReqDTO.getRefuseSignWaybillId());
					pushRefuseSignMsgToApp(info);
				}
			}
			record = new WriteMqInfoDTO();
			record.setOrderType("updateRefuseSign");
			record.setQueueName("拒收单receive notice");
			record.setMainInfo(JsonUtil.GsonString(refuseSignNoticeReqDTO));
			record.setErrorReason("2." + JsonUtil.GsonString(updateEsDTO));
			iWriteMqInfoService.insert(record);
			transactionProxy.commit();
			return DataResult.success(true, Boolean.class);
		} catch (Exception e) {
			transactionProxy.rollback();
			record = new WriteMqInfoDTO();
			record.setOrderType("updateRefuseSign");
			record.setQueueName("拒收单receive notice");
			record.setMainInfo(JsonUtil.GsonString(refuseSignNoticeReqDTO));
			record.setErrorReason("3." + JsonUtil.GsonString(e.getLocalizedMessage()));
			iWriteMqInfoService.insert(record);
			rollBackRefuse(rollBackRefuseEsDTO);
			log.error("通知拒收单失败，通知拒收单参数={}", refuseSignNoticeReqDTO, e);
			return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
		}
	}



	private void rollBackRefuse(SignRefuseEsDTO signRefuseEsDTO) {
		try {
			// 尝试回滚拒收单ES
			new DefaultAbstractSearchUpdateExecutor<SignRefuseEsDTO>(esSearchService) {
				@Override
				public EsConfig getConfig() {
					return EsConfig.refuseSign();
				}
			}.executeWithNull(signRefuseEsDTO);
		} catch (SystemException e) {
			log.error("尝试回滚拒收单ES失败：{}", e.getErrorReason());
		}
	}

	private void refuseSignForPush(RefuseSignNoticeReqDTO refuseSignNoticeReqDTO, SignRefuseEsDTO signRefuseEsDTO,
                                   Long id, BigDecimal orderPay) throws Exception {
		SignBillEsRespDTO dto = saveSignForRefuse(signRefuseEsDTO);
		Long refuseSignWaybillId = refuseSignNoticeReqDTO.getRefuseSignWaybillId();

		if (signRefuseEsDTO.getLoaded() && refuseSignNoticeReqDTO.getDispatcherId() == null) {
			dto.setFlag(40);
		}

		Map<String, Object> info = BeanMapUtils.objectToMap(dto);
		info.put("driverId", signRefuseEsDTO.getDriverId());
		info.put("pay", orderPay);
		info.put("refuseWaybillId", refuseSignWaybillId);
		pushRefuseSignMsgToApp(info);
		Boolean execute = updateSignRefuseEsDTO(signRefuseEsDTO, id, dto.getSignId(), dto.getSignCode());
		if (!execute) {
			throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "ES 更新拒收单状态失败");
		}
	}

	/**
	 * 用拒收单信息保存签收单
	 */
	private SignBillEsRespDTO saveSignForRefuse(SignRefuseEsDTO signRefuseEsDTO) {
		SignForDetailsReqDTO signForDetailsReqDTO = new SignForDetailsReqDTO();
		signForDetailsReqDTO.setCompanyId(signRefuseEsDTO.getCompanyId());
		signForDetailsReqDTO.setDispatcherId(signRefuseEsDTO.getDispatcherId());
		signForDetailsReqDTO.setLoaded(signRefuseEsDTO.getLoaded());
		signForDetailsReqDTO.setSettlementMethod(signRefuseEsDTO.getSettlementMethod());
		signForDetailsReqDTO.setSignType(signRefuseEsDTO.getRefuseType());
		signForDetailsReqDTO.setUserId(signRefuseEsDTO.getCreateUser());
		signForDetailsReqDTO.setWaybillId(signRefuseEsDTO.getWaybillId());
		return giveSignService.insertSign(signForDetailsReqDTO);
	}

	/**
	 * 通知OMS成功，将签收单id回写到拒收单
	 * 
	 * @param signRefuseEsDTO
	 * @param id
	 * @param signId
	 * @return
	 */
	private Boolean updateSignRefuseEsDTO(SignRefuseEsDTO signRefuseEsDTO, Long id, Long signId, String signCode) {
		// -> ES
		SignRefuseEsDTO updateEsDTO = new SignRefuseEsDTO();
		updateEsDTO.setId(id);
		updateEsDTO.setSignId(signId);
		updateEsDTO.setSignCode(signCode);
		updateEsDTO.setCompanyId(signRefuseEsDTO.getCompanyId());
		SignRefuseDO updateDTO = new SignRefuseDO();
		EntityUtils.copyProperties(updateEsDTO, updateDTO);
		int i = signRefuseDOMapper.updateByPrimaryKeySelective(updateDTO);
		if (i != 1) {
			throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "DB 更新拒收单状态失败");
		}
		return new DefaultAbstractSearchUpdateExecutor<SignRefuseEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.refuseSign();
			}
		}.execute(updateEsDTO);
	}

	private void pushRefuseSignMsgToApp(Map<String, Object> info) {
		PushMessageDto pushMessageDto = new PushMessageDto();
		WriteMqInfoDTO record = new WriteMqInfoDTO();
		record.setOrderType("notice push");
		record.setQueueName("notice push");
		record.setMainInfo(JsonUtil.GsonString(info));
		try {
//			info.put("noticeType", AppMessageConstants.REFUSE_NOTICE);
			info.put("noticeType", 100);//马勇说 这个应该是100
			Gson gson = new Gson();
			log.error("pushRefuseSignMsgToApp: " + gson.toJson(info));
            AppMessageReqDTO appMessageReqDTO = new AppMessageReqDTO();
            appMessageReqDTO.setDriverId(Integer.valueOf(info.get("driverId").toString()));
            appMessageReqDTO.setTitle("任务");
            appMessageReqDTO.setContent("您有一个拒收单已经审核通过！");
            appMessageReqDTO.setInfo(info);
            appMessageService.pushAppMessage(appMessageReqDTO);
			record.setErrorReason(JsonUtil.GsonString(info));
			iWriteMqInfoService.insert(record);
		} catch (Exception e) {
			log.error("消息推送失败，消息信息为：" + pushMessageDto, e);
			record.setErrorReason(JsonUtil.GsonString(e.getLocalizedMessage()));
//			writeMqInfoService.insert(record);
		}
	}

	/**
	 * 获取运单货物明细
	 * 
	 * @param waybillId
	 *            运单id
	 * @return
	 */
	private List<WaybillGoodsEsDTO> getWaybillGoodsEsDTOList(Long waybillId) {
		return waybillService.queryWaybillGoodsEsDTOList(waybillId);
	}

	/**
	 * 获取派车单信息
	 * 
	 * @param dispatcherId
	 * @return
	 */
	private DispatcherEsDTO getDispatcherEsDTO(Long dispatcherId) {
        return dispatcherService.queryDispatcherEsDTO(dispatcherId);
	}

	/**
	 * 获取运单信息
	 * 
	 * @param waybillId
	 * @return
	 */
	private WaybillEsDTO getTmsWaybillEsDTO(Long waybillId) {
		return waybillService.queryWaybillEsDTO(waybillId);
	}

//	private WaybillEsDTO getTmsWaybillEsDTOByOrderCode(String customerOrderCode) {
//        List<WaybillEsDTO> list = waybillService.getWaybillsByOrderCode(customerOrderCode);
//        if (list == null || list.size() == 0) {
//			return null;
//		} else if (list.size() > 1) {
//			throw new SystemException(-1, "订单编号：" + customerOrderCode + "在运单中有重复");
//		} else {
//			return list.get(0);
//		}
//	}
	private WaybillEsDTO getTmsWaybillEsDTOByWaybillCode(String code,Long companyId ) {
		WaybillEsDTO waybill = waybillService.getWaybillByCode(code,companyId.intValue());
		return waybill;
	}
	private BigDecimal getCollectPayment(Long waybillId) {
        WaybillFeeEsDTO queryParam = new WaybillFeeEsDTO();
        queryParam.setWaybillId(waybillId);
        queryParam.setAttrId(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType());
        List<WaybillFeeEsDTO> feeEsDTOList = waybillService.queryWaybillFeeEsDTOList(queryParam);
        if (CollectionUtils.isNotEmpty(feeEsDTOList)) {
			WaybillFeeEsDTO waybillFeeEsDTO = feeEsDTOList.get(0);
			return waybillFeeEsDTO.getFee();
		} else {
			return BigDecimal.ZERO;
		}
	}

	@Override
	public DataResult<RefuseSignResultRespDTO> getRefuseSignAmount(Long refuseId) {
		DataResult<RefuseSignResultRespDTO> dataResult = new DataResult<>();
		SignRefuseEsDTO signRefuseEsDTO = new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.refuseSign();
			}
		}.get(refuseId);
		RefuseSignResultRespDTO refuseSignResultRespDTO = new RefuseSignResultRespDTO();
		if (signRefuseEsDTO == null || signRefuseEsDTO.getRefusePay() == null) {
			refuseSignResultRespDTO.setRefusePay(new BigDecimal(-1));
			dataResult.setResult(refuseSignResultRespDTO);
		} else {
			refuseSignResultRespDTO.setRefusePay(signRefuseEsDTO.getRefusePay());
			refuseSignResultRespDTO.setRefuseSignWaybillId(signRefuseEsDTO.getRefuseWaybillId());
			dataResult.setResult(refuseSignResultRespDTO);
		}
		return dataResult;
	}

	@Override
	public List<SignRefuseEsDTO> getRefuseSignList(RefuseSignQueryReqDTO refuseSignQueryReqDTO) {
		refuseSignQueryReqDTO.initPage();// 分页信息初始化

		List<SearchCondition> searchConditions = buildSearchConditions(refuseSignQueryReqDTO);
		List<OrderCondition> orderConditionList = buildOrderConditionList();
		PageCondition pageCondition = buildPageCondition(refuseSignQueryReqDTO);

		return new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.refuseSign();
			}
		}.list(searchConditions, orderConditionList, pageCondition);
	}

	@Override
	public long getCount(RefuseSignQueryReqDTO refuseSignQueryReqDTO) {
		refuseSignQueryReqDTO.initPage();// 分页信息初始化

		List<SearchCondition> searchConditions = buildSearchConditions(refuseSignQueryReqDTO);

		long count = new DefaultAbstractSearchAggregateExecutor<SignRefuseEsDTO>(esStatisticService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.refuseSign();
			}
		}.count(searchConditions);
		return count;
	}

	private List<SearchCondition> buildSearchConditions(RefuseSignQueryReqDTO refuseSignQueryReqDTO) {
		List<SearchCondition> searchConditions = new ArrayList<>();
		if (StringUtils.isNotEmpty(refuseSignQueryReqDTO.getRefuseCode())) {
			SearchCondition idSearch = SearchConditionUtils.newLikeCondition("refuseCode",
					refuseSignQueryReqDTO.getRefuseCode());
			searchConditions.add(idSearch);
		}
		if (null != refuseSignQueryReqDTO.getRefuseType()) {
			SearchCondition refuseTypeSearch = SearchConditionUtils.newEqualCondition("refuseType",
					refuseSignQueryReqDTO.getRefuseType());
			searchConditions.add(refuseTypeSearch);
		}

		if (null != refuseSignQueryReqDTO.getCompanyId() || refuseSignQueryReqDTO.getCompanyId() > 0) {
			SearchCondition refuseTypeSearch = SearchConditionUtils.newEqualCondition("companyId",
					refuseSignQueryReqDTO.getCompanyId());
			searchConditions.add(refuseTypeSearch);
		}
	
		if (null != refuseSignQueryReqDTO.getBranchId() || refuseSignQueryReqDTO.getBranchId() > 0) {
			SearchCondition refuseTypeSearch = SearchConditionUtils.newEqualCondition("branchId",
					refuseSignQueryReqDTO.getBranchId());
			searchConditions.add(refuseTypeSearch);
		}
		Long startRefuseTime = refuseSignQueryReqDTO.getStartRefuseTime();
		Long endRefuseTime = refuseSignQueryReqDTO.getEndRefuseTime();
		if (startRefuseTime != null && endRefuseTime != null && endRefuseTime >= startRefuseTime) {
			SearchCondition startTime = SearchConditionUtils.newSearchCondition("refuseTime", startRefuseTime,
					ConditionExpressionEnum.GREATER_OR_EQUAL);
			SearchCondition endTime = SearchConditionUtils.newSearchCondition("refuseTime", endRefuseTime,
					ConditionExpressionEnum.LESSER_OR_EQUAL);
			searchConditions.add(startTime);
			searchConditions.add(endTime);
		}
		return searchConditions;
	}

	private PageCondition buildPageCondition(RefuseSignQueryReqDTO refuseSignQueryReqDTO) {
		int currentPage = refuseSignQueryReqDTO.getCurrentPage() == null ? 1 : refuseSignQueryReqDTO.getCurrentPage();
		int pageSize = refuseSignQueryReqDTO.getPageSize() == null ? 10 : refuseSignQueryReqDTO.getPageSize();
		return PageConditionUtils.create(pageSize, currentPage);
	}

	private List<OrderCondition> buildOrderConditionList() {
		return OrderConditionUtils.start().addCondition("createTime", SortEnum.DESC).end();
	}

	@Override
	public DataResult<List<TmsGoodsDetailsEsDTO>> getRefuseSignGoodsDetails(Long refuseId) {
		DataResult<List<TmsGoodsDetailsEsDTO>> dataResult = new DataResult<>();
		List<SearchCondition> searchConditions = new ArrayList<>();
		SearchCondition searchCondition = SearchConditionUtils.newEqualCondition("refuseId", refuseId);
		searchConditions.add(searchCondition);
		List<TmsGoodsDetailsEsDTO> list = new DefaultAbstractSearchQueryExecutor<TmsGoodsDetailsEsDTO>(
				esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.refuseSignGoodsDetails();
			}
		}.list(searchConditions);
		dataResult.setResult(list);
		return dataResult;
	}

	/**
	 *
	 * @param waybillCode
	 * @param companyId 没用-
	 * @return
	 */
	@Override
	public DataResult<Boolean> updateRefuseSignToCancel(String waybillCode,@Deprecated Long companyId) {
		DataResult<Boolean> dataResult = new DataResult<>();
		dataResult.setResult(false);
		WriteMqInfoDTO record = new WriteMqInfoDTO();
		record.setOrderType("cancel_refuse_sign");
		record.setQueueName("取消拒收单");
		record.setMainInfo(JsonUtil.GsonString(waybillCode));

		/**
		 * update: 获取运单通过waybillCode+CompanyID
		 * zouhx－公司id没有传过来，oms调用api传值不对
		 * 2018-06-09
		 */
		//WaybillEsDTO waybill = waybillService.getWaybillByCode(waybillCode);
		WaybillEsDTO waybill = waybillService.getWaybillByCode(waybillCode,companyId.intValue());

		if (waybill==null){
			throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "运单号不存在");
		}
		//Long companyId = waybill.getCompanyId().longValue();

		// >> 开启事务
		TransactionManagerUtils.TransactionProxy transactionProxy = dtmFactory.create()
				.addTransManager(SignRefuseDOMapper.class, companyId).build();
		try {
			Long waybillId = waybill.getId();
			SignRefuseDO signRefuseDO = new SignRefuseDO();
			signRefuseDO.setWaybillId(waybillId);
			signRefuseDO.setCompanyId(companyId);
			signRefuseDO.setRefuseType(SignStatusEnum.CANCEL_SIGN.getType());

			SignRefuseDOExample signRefuseDOExample = new SignRefuseDOExample();
			signRefuseDOExample.or().andWaybillIdEqualTo(waybillId).andCompanyIdEqualTo(companyId);

			int i = signRefuseDOMapper.updateByExampleSelective(signRefuseDO, signRefuseDOExample);

			if (i <= 0) {
                throw new SystemException(-1, "取消拒收单失败，DB更新失败");
			}
            SignRefuseEsDTO signRefuseEsDTO = EntityUtils.copyProperties(signRefuseDO, SignRefuseEsDTO.class);

            List<SearchCondition> searchConditions = SearchConditionUtils.start()
                    .addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL)
                    .addCondition("companyId", companyId, ConditionExpressionEnum.EQUAL).end();
            Boolean flag = new DefaultAbstractSearchUpdateExecutor<SignRefuseEsDTO>(esSearchService) {
                @Override
                public EsConfig getConfig() {
                    return EsConfig.refuseSign();
                }
            }.execute(signRefuseEsDTO, searchConditions);
            if (!flag) {
                throw new SystemException(-1, "取消拒收单失败，ES更新失败");
            }
            transactionProxy.commit();
            record.setErrorReason("success");
            iWriteMqInfoService.insert(record);
            dataResult.setResult(true);
			return dataResult;
		} catch (Exception e) {
			transactionProxy.rollback();
			log.error(e.getLocalizedMessage(), e);
            record.setErrorReason(JsonUtil.GsonString(e.getLocalizedMessage()));
            iWriteMqInfoService.insert(record);
			throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN, e.getLocalizedMessage());
		}
	}

	private TransactionManagerUtils.TransactionProxy getTransaction(Long companyId) {
		return dtmFactory.create().addTransManager(SignRefuseDOMapper.class, companyId)
				.addTransManager(GoodsDetailsDOMapper.class, companyId).build();
	}
}
