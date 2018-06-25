package com.hivescm.tms.finance.server.listener;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.tms.api.dto.es.alteration.response.AlterationDeliveryChangeInfoRespDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherFeeEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.component.TmsDispatcherEsDTO;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionCloseEsDTO;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionEsDTO;
import com.hivescm.tms.api.dto.es.exception.response.WaybillExceptionGetByIDRespDTO;
import com.hivescm.tms.api.dto.es.finance.*;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderFeeEsDTO;
import com.hivescm.tms.api.dto.es.outbill.OutBillCompanyEsDTO;
import com.hivescm.tms.api.dto.es.outbill.OutBillDetailEsDTO;
import com.hivescm.tms.api.dto.es.outbill.req.OutBillAddReqDTO;
import com.hivescm.tms.api.dto.es.transport.TransportArrivalCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportArrivalInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.component.TmsTransportArriveDTO;
import com.hivescm.tms.api.dto.es.transport.request.ArrivalCostReqDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.api.enums.finance.*;
import com.hivescm.tms.api.enums.waybillexception.ExceptionObjectEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;
import com.hivescm.tms.finance.server.feign.billManager.IAlterationDeliveryService;
import com.hivescm.tms.finance.server.feign.outbill.OutbillService;
import com.hivescm.tms.finance.server.feign.waybill.TransportArriveService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillFeeService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.finance.*;
import com.hivescm.tms.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 财务数据来源监听队列
 * 
 * @author wangqianqian
 *
 */
@Component
@RabbitListener(queues = "${tms.finance.data.source}")
public class FinanceDataSourceListener {

	private static final Logger logger = LoggerFactory.getLogger(FinanceDataSourceListener.class);

	@Autowired
	private WaybillService waybillService;
	@Autowired
	private OutbillService outbillService;
	@Autowired
	private EsFinanceManageReceiptService esFinanceManageReceiptService;
	@Autowired
	private FinanceManageReceiptService financeManageReceiptService;
	@Autowired
	private TransportArriveService transportArriveService;
	@Autowired
	private FinanceManagePayService financeManagePayService;
	@Autowired
	private WaybillFeeService waybillFeeService;
	@Autowired
	private EsFinanceManageGoodsRecycleService esFinanceManageGoodsRecycleService;
	@Autowired
	private FinanceManageGoodsRecycleService financeManageGoodsRecycleService;
	@Autowired
	private EsFinanceManagePayService esFinanceManagePayService;
	@Autowired
	private IAlterationDeliveryService alterationDeliveryService;
	@Autowired
	private EsFinanceErrorLogService financeErrorLogService;
	@Value("${tms.finance.data.source}")
	private String queueName;

	@RabbitHandler
	public void handler(String obj) {
		FinanceSendMqDTO o = null;
		try {
			logger.info("MQ：财务数据来源监听:{}", obj);
			o = (FinanceSendMqDTO) JSON.parseObject(obj, FinanceSendMqDTO.class);
			// 0 新增 1 修改 2 删除
			Integer operateType = o.getOperateType();
			switch (operateType) {
			case 0:
				initInsert(o);
				break;
			case 1:
				initUpdate(o);
				break;
			case 2:
				initDelete(o);
				break;
			}
			if(o.getBusinessType()==CalculationFeeEnum.SIGN.getType()) {
				updateSignStatus(o.getIds());
			}
		} catch (TmsBusinessException e) {
			logger.error(String.format("MQ：财务数据来源接收失败,原数据 : %s  失败原因 : %s", obj, e.getErrorMsg()), e);
			financeErrorLogService.insert(createErrorDto(obj,e.getErrorMsg()));
		} catch (Throwable e) {
			logger.error(String.format("MQ：财务数据来源接收失败,原数据 : %s  失败原因 : 系统级异常", obj), e);
			financeErrorLogService.insert(createErrorDto(obj,e.getLocalizedMessage()));
		}
	}

	private FinanceErrorLogDTO createErrorDto(String obj,String errorReason) {
		FinanceErrorLogDTO dto = new FinanceErrorLogDTO();
		dto.setId(System.currentTimeMillis() + UUID.randomUUID().hashCode());
		dto.setFinanceData(obj);
		dto.setQueueName(queueName);
		dto.setErrorReason(errorReason);
		dto.setCreateTime(DateUtils.getCurrentDate());
		return dto;
	}

	private void initDelete(FinanceSendMqDTO o) {
		Integer businessType = o.getBusinessType();
		// 1 运单 2派车单 3发车配载单 4外发中转单 5装卸单 7到货确认单 20签收 21自提改配送 22 派车单费项更改 23 到货确认单费项更改
		switch (businessType) {
		case 1:
			deleteWaybill(o.getIds());
			break;
		case 2:
			deleteDispatch(o.getIds());
			break;
		case 3:
			deleteDepart(o.getIds());
			break;
		case 4:
			deleteTransit(o.getIds());
			break;
		case 5:
			deleteHandlingOrder(o.getIds());
			break;
		case 7:
			deleteArriveGoods(o.getIds());
			break;
		case 20:
			deleteSign(o.getIds());
			break;
		case 22:
			deleteChangeDispatch(o.getIds());
			break;
		case 23:
			deleteChangeArriveGoods(o.getIds());
			break;
		}
	}

	private void initUpdate(FinanceSendMqDTO o) {
		Integer businessType = o.getBusinessType();
		// 1 运单 2派车单 3发车配载单 4外发中转单 5装货单 6 卸货单 7 到货确认单 20 签收21自提改配送 22 派车单费项更改 23 到货确认单费项更改
		switch (businessType) {
		case 1:
			updateWaybill(o.getIds());
			break;
		case 2:
			updateDispatch(o.getIds());
			break;
		case 3:
			updateDepart(o.getIds());
			break;
		case 4:
			updateTransit(o.getIds());
			break;
		case 5:
		case 6:
		case 7:
			updateArriveGoods(o.getIds());
			break;
		case 20:
			updateSign(o.getIds());
			break;
		case 22:
			updateChangeDispatch(o.getIds());
			break;
		case 23:
			updateChangeArriveGoods(o.getIds());
			break;
		}
	}

	private void initInsert(FinanceSendMqDTO o) {
		Integer businessType = o.getBusinessType();
		/*
		 * 1运单 2派车单 3发车配载单 4外发中转单 5装卸单 7到货确认单 20签收 21自提改配送 22派车单费项更改 23到货确认单费项更改
		   24异常单办结理赔 25异常单办结罚款 26异常单办结理赔并罚款
		 */
		switch (businessType) {
		case 1:
			initWaybillInsert(o.getIds());
			break;
		case 2:
			initDispatchInsert(o.getIds());
			break;
		case 3:
			initDepartInsert(o.getIds());
			break;
		case 4:
			initTransitInsert(o.getIds());
			break;
		case 5:
			initHandlingOrderInsert(o.getIds());
			break;
		case 7:
			initArriveGoodsInsert(o.getIds());
			break;
		case 20:
			initSignInsert(o.getIds());
			break;
		case 21:
			initChangeSend(o.getIds());
			break;
		case 22:
			initChangeDispatchInsert(o.getIds());
			break;
		case 23:
			initChangeArriveGoodsInsert(o.getIds());
			break;
		case 24:
			initExpCloseClaimInsert(o.getIds());
			break;
		case 25:
			initExpCloseForfeitInsert(o.getIds());
			break;
		case 26:
			initExpCloseClaimAndForfeitInsert(o.getIds());
			break;
		}
	}

	// =========================================================新增=========================================================
	/**
	 * 根据异常单的理赔和罚款信息生成应付和应收信息
	 * @param ids
	 */
	private void initExpCloseClaimAndForfeitInsert(List<Long> ids) {
		initExpCloseForfeitInsert(ids);
		initExpCloseClaimInsert(ids);
	}

	/**
	 * 根据异常单的罚款信息生成应收信息
	 * @param ids
	 */
	private void initExpCloseForfeitInsert(List<Long> ids) {
		WaybillExceptionGetByIDRespDTO waybillExceptionGetByIDRespDTO = getWaybillExceptionGetByIDRespDTO(ids);
		SaveFinanceManageReceiptReqDTO saveFinanceManageReceiptReqDTO = initSaveFinanceManageReceiptReqDTO(waybillExceptionGetByIDRespDTO);
		financeManageReceiptService.insertBatchForExpClose(saveFinanceManageReceiptReqDTO);
	}

	/**
	 * 根据异常单的理赔信息生成应付信息
	 * @param ids
	 */
	private void initExpCloseClaimInsert(List<Long> ids) {
		WaybillExceptionGetByIDRespDTO waybillExceptionGetByIDRespDTO = getWaybillExceptionGetByIDRespDTO(ids);
		FinanceManagePayReqDTO financeManagePayReqDto = initFinanceManagePayReqDTO(waybillExceptionGetByIDRespDTO);
		financeManagePayService.createPayInfo(financeManagePayReqDto);
	}

	/**
	 * 调用异常管理服务获取异常单信息
	 * @param ids
	 * @return
	 */
	private WaybillExceptionGetByIDRespDTO getWaybillExceptionGetByIDRespDTO(List<Long> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			DataResult<WaybillExceptionGetByIDRespDTO> dataResult = alterationDeliveryService.getByID(ids.get(0));
			if (dataResult == null || dataResult.isFailed()) {
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_DEPENDENCY, "调用异常管理服务异常");
			}
			return dataResult.getResult();
		}
		return null;
	}

	// 签收
	private void initSignInsert(List<Long> ids) {
		// 1.根据运单ID查询运单详情
		// 2.判断是否有到付
		// 3.有的话，查询应收表里面是否已经有该运单的到付费用
		// 4.没有就新增应收表里一条记录，有就不做操作
		// 5.判断是否有代收货款
		// 6.有的话，查询货款回收表里是否有该运单的代收货款
		// 7.没有新增货款回收表里一条记录，有就不做操作
		FinanceManageReceiptReqDTO financeManageReceiptReqDto = null;
		for (Long id : ids) {
			WaybillEsDTO waybillEsDto = waybillService.queryWaybillEsDTO(id);
			if (waybillEsDto != null) {
				//判断到付金额
				if (waybillEsDto.getCod() != null && waybillEsDto.getCod().compareTo(BigDecimal.ZERO) > 0) {
					financeManageReceiptReqDto = new FinanceManageReceiptReqDTO();
					financeManageReceiptReqDto.setWaybillId(waybillEsDto.getId());
					financeManageReceiptReqDto.setPayWay(PayWayEnum.TOPAY.getType());
					FinanceManageReceiptEsDTO financeManageReceiptEsDto = esFinanceManageReceiptService.findFinanceManageReceipt(financeManageReceiptReqDto);
					if (financeManageReceiptEsDto != null) {
						logger.info("已有该到付应收信息");
					} else {
						//新增应收信息
						financeManageReceiptEsDto = new FinanceManageReceiptEsDTO();
						financeManageReceiptEsDto.setPayWay(PayWayEnum.TOPAY.getType());
						financeManageReceiptEsDto.setPayWayName(PayWayEnum.TOPAY.getName());
						financeManageReceiptEsDto.setReceiptAmount(waybillEsDto.getCod());
						financeManageReceiptEsDto.setIsTransit(false);
						financeManageReceiptEsDto.setCodeType(CodeTypeEnum.YD.getType());
						financeManageReceiptEsDto.setCodeTypeName(CodeTypeEnum.YD.getName());
						financeManageReceiptService.insert(waybillEsDto, financeManageReceiptEsDto);
					}
				}
				//判断代收货款
				List<WaybillFeeEsDTO> waybillFeeEsDTOList=waybillFeeService.getWaybillFeeByWaybillId(id);
				BigDecimal deliveryAmount = BigDecimal.ZERO;
				BigDecimal deliveryAmountTax = BigDecimal.ZERO;
				if(CollectionUtils.isNotEmpty(waybillFeeEsDTOList)){
					for(WaybillFeeEsDTO waybillFeeEsDTO :waybillFeeEsDTOList){
						if(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType()==waybillFeeEsDTO.getAttrId()){//代收货款
							deliveryAmount = waybillFeeEsDTO.getFee();
						}else if(WaybillFeeTypeEnum.COLLECT_PAYMENT_SERVICE.getType()==waybillFeeEsDTO.getAttrId()){//代收货款手续费
							deliveryAmountTax = waybillFeeEsDTO.getFee();
						}
					}
				}
				if(deliveryAmount.compareTo(BigDecimal.ZERO)>0){
					FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO = esFinanceManageGoodsRecycleService.findByWaybillId(waybillEsDto.getId());
					if (financeManageGoodsRecycleEsDTO != null) {
						logger.info("已有该货款回收信息");
					} else {
						//新增货款回收信息
						financeManageGoodsRecycleEsDTO = createGoodsRecyle(waybillEsDto);
						financeManageGoodsRecycleEsDTO.setUnreceiptAmount(deliveryAmount);
						financeManageGoodsRecycleEsDTO.setDeliveryAmount(deliveryAmount);
						financeManageGoodsRecycleEsDTO.setDeliveryGoodsAmount(deliveryAmountTax);
						financeManageGoodsRecycleService.insert(financeManageGoodsRecycleEsDTO);
					}
				}
				
			}
		}
	}


	private FinanceManageGoodsRecycleEsDTO createGoodsRecyle(WaybillEsDTO waybillEsDto) {
		FinanceManageGoodsRecycleEsDTO dto = new FinanceManageGoodsRecycleEsDTO();
		
		dto.setReceiveBranchId(waybillEsDto.getDestOrgId());
		dto.setDeliveryNetworkName(waybillEsDto.getDestOrgName());
		dto.setPayeeName(StringUtils.isNotBlank(waybillEsDto.getReceiptCompany())?waybillEsDto.getReceiptCompany():waybillEsDto.getReceiptUser());
		
		
		dto.setSendStatus(GrantStatusEnum.UNGRANT.getType());
		dto.setSendStatusName(GrantStatusEnum.UNGRANT.getName());
		dto.setCompanyId(waybillEsDto.getCompanyId().longValue());
		dto.setRecycleStatus(RecycleStatusEnum.UNRECYCLE.getType());
		dto.setRecycleStatusName(RecycleStatusEnum.UNRECYCLE.getName());
		dto.setRemitStatus(RemittanceStatusEnum.UNREMITTANCE.getType()); 
		dto.setRemitStatusName(RemittanceStatusEnum.UNREMITTANCE.getName());
		dto.setWaybillId(waybillEsDto.getId());
		dto.setOrderSourceCode(waybillEsDto.getCode());
		dto.setReceiptedAmount(new BigDecimal(0));
		dto.setCreateUserId(waybillEsDto.getUpdateUser());
		dto.setCreateTime(System.currentTimeMillis());
		//录单时间--运单开单时间
		dto.setWaybillCreateTime(waybillEsDto.getCreateTime());
		dto.setStatus(waybillEsDto.getStatus());
		dto.setStatusName(waybillEsDto.getStatusName());
		dto.setSignStatus(waybillEsDto.getSignStatus());
		dto.setSignStatusName(waybillEsDto.getSignStatusName());
		dto.setSignTime(waybillEsDto.getSignTime());
		dto.setGoodsAmount(waybillEsDto.getGoodsPaymentDeduction());
		dto.setInvoiceCompanyId(waybillEsDto.getInvoiceCustomerId());
		dto.setInvoiceCompanyName(waybillEsDto.getInvoiceCompany());
		dto.setInvoiceUserName(waybillEsDto.getInvoiceUser());
		dto.setInvoiceUserMobile(waybillEsDto.getInvoiceMobleNo());
		dto.setReceiptCompanyId(waybillEsDto.getReceiptCustomerId());
		dto.setReceiptCompanyName(waybillEsDto.getReceiptCompany());
		dto.setReceiptUserName(waybillEsDto.getReceiptUser());
		dto.setReceiptUserMobile(waybillEsDto.getReceiptConsignorMobleNo());
		dto.setInvoiceOrgId(waybillEsDto.getInvoiceOrgId());
		dto.setInvoiceOrgName(waybillEsDto.getInvoiceOrgName());
		dto.setDestName(waybillEsDto.getDestName());
		dto.setDestId(waybillEsDto.getDestId());
		dto.setDestOrgId(waybillEsDto.getDestOrgId());
		dto.setDestOrgName(waybillEsDto.getDestOrgName());
		//始发地信息
		dto.setStarOrgId(waybillEsDto.getInvoiceOrgId());
		dto.setStarOrgName(waybillEsDto.getInvoiceOrgName());
		dto.setStarId(waybillEsDto.getInvoiceCityId());
		dto.setStarName(waybillEsDto.getInvoiceCityName());
		dto.setRemark(waybillEsDto.getRemark());
		dto.setBusinessTime(waybillEsDto.getBussTime());
		dto.setSalesmanId(waybillEsDto.getSalesmanId());
		dto.setReceiptCustomerId(waybillEsDto.getReceiptCustomerId());
		dto.setReceiptCustomerVipId(waybillEsDto.getReceiptCustomerVipId());
		dto.setSalesMan(waybillEsDto.getSalesmanId());
		dto.setInvoiceTelNo(waybillEsDto.getInvoiceTelNo());
		dto.setReceiptConsignorTelNo(waybillEsDto.getReceiptConsignorTelNo());
		return dto;
	}

	// 到货确认单
	private void initArriveGoodsInsert(List<Long> ids) {
		// 1.根据到货确认单ID查询到货确认单详情
		for(Long id:ids){
			DataResult<TmsTransportArriveDTO> dataResult=transportArriveService.getArrivalInfoAndCostById(id);
			if(dataResult.isSuccess()){
				TmsTransportArriveDTO tmsTransportArriveDTO=dataResult.getResult();
				if(null!=tmsTransportArriveDTO){
					if(CollectionUtils.isNotEmpty(tmsTransportArriveDTO.getTransportArrivalCostDetailList())){
                        List<FinanceManagePayReqDTO> financeManagePayReqDTOList= Lists.newArrayList();
						tmsTransportArriveDTO.getTransportArrivalCostDetailList().forEach(transportArrivalCostDetailEsDTO -> {
                            FinanceManagePayReqDTO financeManagePayEsDTO=initFinanceManagePayReqDTO(transportArrivalCostDetailEsDTO,tmsTransportArriveDTO.getTransportArrivalInfo(), tmsTransportArriveDTO.getTransportInfo());
 							financeManagePayReqDTOList.add(financeManagePayEsDTO);
						});
                        financeManagePayService.createBatchPayInfo(financeManagePayReqDTOList);
					}
				}
			}
		}
		// 2.新增费用信息到应付表

	}

    /**
     * 初始化应付-到货
     * @param transportArrivalCostDetailEsDTO
     * @param transportArrivalInfoEsDTO
     * @return
     */
    private FinanceManagePayReqDTO initFinanceManagePayReqDTO(TransportArrivalCostDetailEsDTO transportArrivalCostDetailEsDTO, TransportArrivalInfoEsDTO transportArrivalInfoEsDTO, TransportInfoEsDTO transportInfo) {
        FinanceManagePayReqDTO financeManagePayReqDTO=new FinanceManagePayReqDTO();
        financeManagePayReqDTO.setSheetId(transportArrivalCostDetailEsDTO.getId());
		financeManagePayReqDTO.setDataSourceSheetId(transportArrivalInfoEsDTO.getArrivalBatch());
        financeManagePayReqDTO.setCompanyId(transportArrivalCostDetailEsDTO.getCompanyId());
        financeManagePayReqDTO.setSheetType(CalculationFeeEnum.DHQRD.getType());
        financeManagePayReqDTO.setSheetTypeName(CalculationFeeEnum.DHQRD.getName());
		if(null!=transportArrivalCostDetailEsDTO.getFeeType()){
			financeManagePayReqDTO.setFeeType(transportArrivalCostDetailEsDTO.getFeeType());
		}
        financeManagePayReqDTO.setFeeTypeName(transportArrivalCostDetailEsDTO.getFeeTypeName());
        financeManagePayReqDTO.setPaymentStatus(PaymentStatusEnum.NO_CHECK.getType());
        financeManagePayReqDTO.setPaymentStatusName(PaymentStatusEnum.NO_CHECK.getName());
        financeManagePayReqDTO.setBusinessNetworkId(transportArrivalInfoEsDTO.getBranchId());
        financeManagePayReqDTO.setBusinessNetworkName(transportArrivalInfoEsDTO.getBranchName());
        financeManagePayReqDTO.setPaymentNetworkId(transportArrivalCostDetailEsDTO.getPayBranchId());
        financeManagePayReqDTO.setPaymentNetworkName(transportArrivalCostDetailEsDTO.getPayBranchName());
        if(null!=transportArrivalCostDetailEsDTO.getPayeeId()){
			financeManagePayReqDTO.setPayeeId(Integer.parseInt(transportArrivalCostDetailEsDTO.getPayeeId()));
		}
        financeManagePayReqDTO.setPayee(transportArrivalCostDetailEsDTO.getPayeeName());
        financeManagePayReqDTO.setFeeRemark(transportArrivalCostDetailEsDTO.getCostDesc());
        financeManagePayReqDTO.setBusinessDate(transportArrivalCostDetailEsDTO.getCreateTime());
        financeManagePayReqDTO.setPayableAmount(transportArrivalCostDetailEsDTO.getAmount());//应付
        financeManagePayReqDTO.setUnpaidAmount(transportArrivalCostDetailEsDTO.getAmount());//未付
		financeManagePayReqDTO.setPaidAmount(new BigDecimal(0));//已付
		financeManagePayReqDTO.setPaymentFee(new BigDecimal(0));//付款手续费
		financeManagePayReqDTO.setActualPaidAmount(new BigDecimal(0));//实付金额
        financeManagePayReqDTO.setCreateTime(System.currentTimeMillis());
        return financeManagePayReqDTO;
    }

	/**
	 * 初始化应付-异常单办结理赔
	 * @param waybillExceptionGetByIDRespDTO
	 * @return
	 */
	private FinanceManagePayReqDTO initFinanceManagePayReqDTO(WaybillExceptionGetByIDRespDTO waybillExceptionGetByIDRespDTO) {
		WaybillExceptionEsDTO waybillExceptionEsDTO = waybillExceptionGetByIDRespDTO.getWaybillExceptionEsDTO();
		FinanceManagePayReqDTO financeManagePayReqDTO=new FinanceManagePayReqDTO();
		financeManagePayReqDTO.setSheetId(waybillExceptionEsDTO.getId());
		financeManagePayReqDTO.setDataSourceSheetId(waybillExceptionEsDTO.getExceptionCode());
		financeManagePayReqDTO.setCompanyId(waybillExceptionEsDTO.getCompanyId());
		financeManagePayReqDTO.setSheetType(CalculationFeeEnum.YCHD.getType());//单据类型
		financeManagePayReqDTO.setSheetTypeName(CalculationFeeEnum.YCHD.getName());
		financeManagePayReqDTO.setFeeType(ExpensesEnum.CLAIM.getDocType());
		financeManagePayReqDTO.setFeeTypeName(ExpensesEnum.CLAIM.getName());
		financeManagePayReqDTO.setPaymentStatus(PaymentStatusEnum.NO_CHECK.getType());
		financeManagePayReqDTO.setPaymentStatusName(PaymentStatusEnum.NO_CHECK.getName());
		financeManagePayReqDTO.setBusinessNetworkId(waybillExceptionEsDTO.getFeedbackBranchId());
		financeManagePayReqDTO.setBusinessNetworkName(waybillExceptionEsDTO.getFeedbackBranchName());
		financeManagePayReqDTO.setPaymentNetworkId(waybillExceptionEsDTO.getPayBranchId());
		financeManagePayReqDTO.setPaymentNetworkName(waybillExceptionEsDTO.getPayBranchName());
		financeManagePayReqDTO.setPayeeId(waybillExceptionEsDTO.getClaimTargetId());
		financeManagePayReqDTO.setPayee(waybillExceptionEsDTO.getClaimTarget());
		financeManagePayReqDTO.setFeeRemark(waybillExceptionEsDTO.getHandResultJudgeAdvise());
		financeManagePayReqDTO.setBusinessDate(waybillExceptionEsDTO.getCloseTime());//业务日期
		financeManagePayReqDTO.setPayableAmount(waybillExceptionEsDTO.getClaimAmount());//应付
		financeManagePayReqDTO.setUnpaidAmount(waybillExceptionEsDTO.getClaimAmount());//未付
		financeManagePayReqDTO.setPaidAmount(new BigDecimal(0));//已付
		financeManagePayReqDTO.setPaymentFee(new BigDecimal(0));//付款手续费
		financeManagePayReqDTO.setActualPaidAmount(new BigDecimal(0));//实付金额
		financeManagePayReqDTO.setCreateUserId(waybillExceptionEsDTO.getCloseUserId());
		financeManagePayReqDTO.setCreateUserName(waybillExceptionEsDTO.getCloseUserName());
		financeManagePayReqDTO.setCreateTime(System.currentTimeMillis());
		return financeManagePayReqDTO;
	}

	/**
	 * 初始化应收-异常单办结罚款
	 * @param waybillExceptionGetByIDRespDTO
	 * @return
	 */
	private SaveFinanceManageReceiptReqDTO initSaveFinanceManageReceiptReqDTO(WaybillExceptionGetByIDRespDTO waybillExceptionGetByIDRespDTO) {
		Long creatTime = Instant.now().toEpochMilli();
		FinanceManageReceiptEsDTO financeManageReceiptEsDTO = null;
		List<FinanceManageReceiptEsDTO> financeManageReceiptEsDTOList = new ArrayList<>();
		WaybillExceptionEsDTO waybillExceptionEsDTO = waybillExceptionGetByIDRespDTO.getWaybillExceptionEsDTO();
		List<WaybillExceptionCloseEsDTO> waybillExceptionCloseEsDTOwList = waybillExceptionGetByIDRespDTO.getCloseList();
		for (WaybillExceptionCloseEsDTO waybillExceptionCloseEsDTO : waybillExceptionCloseEsDTOwList) {
			financeManageReceiptEsDTO = new FinanceManageReceiptEsDTO();
			financeManageReceiptEsDTO.setCompanyId(waybillExceptionCloseEsDTO.getCompanyId());
			financeManageReceiptEsDTO.setWaybillId(waybillExceptionCloseEsDTO.getId());//应该是来源单ID
			financeManageReceiptEsDTO.setOrderSourceCode(waybillExceptionEsDTO.getExceptionCode());//来源单号
			financeManageReceiptEsDTO.setPayWay(PayWayEnum.OTHER.getType());
			financeManageReceiptEsDTO.setPayWayName(PayWayEnum.OTHER.getName());
			financeManageReceiptEsDTO.setCodeType(CalculationFeeEnum.YCHD.getType());//单据类型
			financeManageReceiptEsDTO.setCodeTypeName(CalculationFeeEnum.YCHD.getName());
			financeManageReceiptEsDTO.setDeliveryStatus(DeliveryStatusEnum.NO_CHECK.getType());//收银状态
			financeManageReceiptEsDTO.setDeliveryStatusName(DeliveryStatusEnum.NO_CHECK.getName());
			financeManageReceiptEsDTO.setReceiptAmount(waybillExceptionCloseEsDTO.getForfeitAmount());
			financeManageReceiptEsDTO.setReceiptedAmount(new BigDecimal(0));
			financeManageReceiptEsDTO.setUnreceiptAmount(waybillExceptionCloseEsDTO.getForfeitAmount());
			financeManageReceiptEsDTO.setDeliveryNetworkId(waybillExceptionCloseEsDTO.getReceiveBranchId());
			financeManageReceiptEsDTO.setDeliveryNetworkName(waybillExceptionCloseEsDTO.getReceiveBranchName());
			if (waybillExceptionCloseEsDTO.getExceptionObject() == Integer.valueOf(ExceptionObjectEnum.HANDLINGTEAM.getType())) {
				financeManageReceiptEsDTO.setPayeeId(waybillExceptionCloseEsDTO.getHandlingTeamId());
				financeManageReceiptEsDTO.setPayeeName(waybillExceptionCloseEsDTO.getHandlingTeamName());
			} else {
				financeManageReceiptEsDTO.setPayeeId(waybillExceptionCloseEsDTO.getCarrierId());
				financeManageReceiptEsDTO.setPayeeName(waybillExceptionCloseEsDTO.getCarrierName());
			}
			financeManageReceiptEsDTO.setRemark(waybillExceptionEsDTO.getHandResultJudgeAdvise());
			financeManageReceiptEsDTO.setBusinessTime(waybillExceptionEsDTO.getCloseTime());
			financeManageReceiptEsDTO.setCreateUserId(waybillExceptionEsDTO.getCloseUserId());
			financeManageReceiptEsDTO.setCreateUserName(waybillExceptionEsDTO.getCloseUserName());
			financeManageReceiptEsDTO.setCreateTime(creatTime);
			financeManageReceiptEsDTOList.add(financeManageReceiptEsDTO);
		}
		SaveFinanceManageReceiptReqDTO saveFinanceManageReceiptReqDTO = new SaveFinanceManageReceiptReqDTO();
		saveFinanceManageReceiptReqDTO.setFinanceManageReceiptEsDTOList(financeManageReceiptEsDTOList);
		return saveFinanceManageReceiptReqDTO;
	}
	private void initHandlingOrderInsert(List<Long> ids) {
		// 根据装卸单主表ID查询费用明细
		if (CollectionUtils.isNotEmpty(ids)) {
			DataResult<List<HandlingorderFeeEsDTO>> dataResult = null;
			for (Long id : ids) {
				dataResult = transportArriveService.getHandlingFeeList(id);
				if (dataResult != null && dataResult.getResult() != null) {
					List<HandlingorderFeeEsDTO> handlingorderFeeEsDtoList = dataResult.getResult();
					if (CollectionUtils.isNotEmpty(handlingorderFeeEsDtoList)) {
						List<FinanceManagePayReqDTO> financeManagePayReqDTOList = Lists.newArrayList();
						for (HandlingorderFeeEsDTO handlingorderFeeEsDto : handlingorderFeeEsDtoList) {
							FinanceManagePayReqDTO financeManagePayReqDTO=initHandlingOrderFinaceManagePayReqDTO(handlingorderFeeEsDto);
							financeManagePayReqDTOList.add(financeManagePayReqDTO);
							financeManagePayService.createBatchPayInfo(financeManagePayReqDTOList);
						}
					}
				}
			}
		}
	}

	/**
	 * 初始化应付-装卸单
	 *
	 * @param handlingorderFeeEsDto
	 * @return
	 */
	private FinanceManagePayReqDTO initHandlingOrderFinaceManagePayReqDTO(HandlingorderFeeEsDTO handlingorderFeeEsDto) {
		FinanceManagePayReqDTO financeManagePayReqDTO=new FinanceManagePayReqDTO();
		financeManagePayReqDTO.setSheetId(handlingorderFeeEsDto.getId());
		financeManagePayReqDTO.setDataSourceSheetId(handlingorderFeeEsDto.getHandlingOrderCode());//装卸单批次号
		financeManagePayReqDTO.setCompanyId(handlingorderFeeEsDto.getCompanyId());
		financeManagePayReqDTO.setSheetType(CalculationFeeEnum.ZHD.getType());
		financeManagePayReqDTO.setSheetTypeName(CalculationFeeEnum.ZHD.getName());
		financeManagePayReqDTO.setFeeType(handlingorderFeeEsDto.getFeeType());
		financeManagePayReqDTO.setFeeTypeName(handlingorderFeeEsDto.getFeeTypeName());
		financeManagePayReqDTO.setPaymentStatus(PaymentStatusEnum.NO_CHECK.getType());
		financeManagePayReqDTO.setPaymentStatusName(PaymentStatusEnum.NO_CHECK.getName());
		financeManagePayReqDTO.setBusinessNetworkId(handlingorderFeeEsDto.getBranchId());
		financeManagePayReqDTO.setBusinessNetworkName(handlingorderFeeEsDto.getBranchName());
		financeManagePayReqDTO.setPaymentNetworkId(handlingorderFeeEsDto.getPayerId());
		financeManagePayReqDTO.setPaymentNetworkName(handlingorderFeeEsDto.getPayerName());
		financeManagePayReqDTO.setPayeeId(handlingorderFeeEsDto.getPayeeId());
		financeManagePayReqDTO.setPayee(handlingorderFeeEsDto.getPayeeName());
		financeManagePayReqDTO.setFeeRemark(handlingorderFeeEsDto.getRemark());
		financeManagePayReqDTO.setBusinessDate(handlingorderFeeEsDto.getCreateTime());
		financeManagePayReqDTO.setPayableAmount(handlingorderFeeEsDto.getAmount());//应付
		financeManagePayReqDTO.setUnpaidAmount(handlingorderFeeEsDto.getAmount());//未付
		financeManagePayReqDTO.setPaidAmount(new BigDecimal(0));//已付
		financeManagePayReqDTO.setPaymentFee(new BigDecimal(0));//付款手续费
		financeManagePayReqDTO.setActualPaidAmount(new BigDecimal(0));//实付金额
		financeManagePayReqDTO.setCreateTime(System.currentTimeMillis());
		financeManagePayReqDTO.setCreateUserId(handlingorderFeeEsDto.getCreateUser());
		return financeManagePayReqDTO;
	}

    // 外发中转单
	private void initTransitInsert(List<Long> ids) {
		// 1.根据中转ID查询中转详情，根据中转详情的运单id查询运单详情，通过这2个实体赋值
		// 2.判断是否有到付
		// 3.有新增应收表里一条记录
		// 4.判断是否有代收货款
		// 5.有新增货款回收表里一条记录
		for(Long id :ids) {
			OutBillAddReqDTO result = outbillService.queryOutBill(id).getResult();
			if(result!=null) {
				List<OutBillDetailEsDTO> outBillDetails = result.getOutBillDetails();
				List<OutBillCompanyEsDTO> outBillCompanys = result.getOutBillCompanys();
				Long transitCompanyId = 0L;
				if(CollectionUtils.isNotEmpty(outBillCompanys)) {
					//取序列ID为1的公司ID
					transitCompanyId = outBillCompanys.get(0).getOutCompanyId();
				}
				if(CollectionUtils.isNotEmpty(outBillDetails)) {
					FinanceManageReceiptEsDTO financeManageReceiptEsDto = new FinanceManageReceiptEsDTO();
					for(OutBillDetailEsDTO detail : outBillDetails) {
						WaybillEsDTO waybillEsDto = waybillService.queryWaybillEsDTO(detail.getWaybillId());
						if (waybillEsDto != null) {
							if(!result.getOutBillInfoEsDTO().getInStorage()) {
								//判断到付金额
								if (waybillEsDto.getCod() != null && waybillEsDto.getCod().compareTo(BigDecimal.ZERO) > 0) {
									financeManageReceiptEsDto = new FinanceManageReceiptEsDTO();
									financeManageReceiptEsDto.setPayWay(PayWayEnum.TOPAY.getType());
									financeManageReceiptEsDto.setPayWayName(PayWayEnum.TOPAY.getName());
									financeManageReceiptEsDto.setReceiptAmount(waybillEsDto.getCod());
									financeManageReceiptEsDto.setIsTransit(true);
									financeManageReceiptEsDto.setTransitCompanyId(transitCompanyId.intValue());
									financeManageReceiptEsDto.setTransitCompanyName(detail.getOutCompanyName());
									financeManageReceiptEsDto.setTransitBillCode(detail.getOutbillCode());
									financeManageReceiptEsDto.setTransitNetworkId(detail.getOutBranchId());
									financeManageReceiptEsDto.setTransitNetworkName(detail.getOutBranchName());
									financeManageReceiptEsDto.setTransitTime(detail.getCreateTime());
									financeManageReceiptEsDto.setTransitBillId(detail.getOutbillId());
									financeManageReceiptEsDto.setCodeType(CodeTypeEnum.YD.getType());
									financeManageReceiptEsDto.setCodeTypeName(CodeTypeEnum.YD.getName());
									financeManageReceiptService.insert(waybillEsDto, financeManageReceiptEsDto);
								}
								//判断代收货款
								List<WaybillFeeEsDTO> waybillFeeEsDTOList=waybillFeeService.getWaybillFeeByWaybillId(detail.getWaybillId());
								BigDecimal deliveryAmount = BigDecimal.ZERO;
								BigDecimal deliveryAmountTax = BigDecimal.ZERO;
								if(CollectionUtils.isNotEmpty(waybillFeeEsDTOList)){
									for(WaybillFeeEsDTO waybillFeeEsDTO :waybillFeeEsDTOList){
										if(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType()==waybillFeeEsDTO.getAttrId()){//代收货款
											deliveryAmount = waybillFeeEsDTO.getFee();
										}else if(WaybillFeeTypeEnum.COLLECT_PAYMENT_SERVICE.getType()==waybillFeeEsDTO.getAttrId()){//代收货款手续费
											deliveryAmountTax = waybillFeeEsDTO.getFee();
										}
									}
								}
								if(deliveryAmount.compareTo(BigDecimal.ZERO)>0){
									FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO = esFinanceManageGoodsRecycleService.findByWaybillId(waybillEsDto.getId());
									if (financeManageGoodsRecycleEsDTO != null) {
										logger.info("已有该货款回收信息");
									} else {
										//新增货款回收信息
										financeManageGoodsRecycleEsDTO = createGoodsRecyle(waybillEsDto);
										financeManageGoodsRecycleEsDTO.setIsTransit(true);
										financeManageGoodsRecycleEsDTO.setReceiveBranchId(detail.getOutBranchId());
										financeManageGoodsRecycleEsDTO.setDeliveryNetworkName(detail.getOutBranchName());
										financeManageGoodsRecycleEsDTO.setPayeeName(detail.getOutCompanyName());
										financeManageGoodsRecycleEsDTO.setTransitBillId(detail.getOutbillId());
										financeManageGoodsRecycleEsDTO.setTransitCompanyId(transitCompanyId.intValue());
										financeManageGoodsRecycleEsDTO.setTransitCompanyName(detail.getOutCompanyName());
										financeManageGoodsRecycleEsDTO.setTransitBillCode(detail.getOutbillCode());
										financeManageGoodsRecycleEsDTO.setTransitNetworkId(detail.getOutBranchId());
										financeManageGoodsRecycleEsDTO.setTransitNetworkName(detail.getOutBranchName());
										financeManageGoodsRecycleEsDTO.setTransitNetworkName(detail.getOutBranchName());
										financeManageGoodsRecycleEsDTO.setUnreceiptAmount(deliveryAmount);
										financeManageGoodsRecycleEsDTO.setDeliveryAmount(deliveryAmount);
										financeManageGoodsRecycleEsDTO.setDeliveryGoodsAmount(deliveryAmountTax);
										financeManageGoodsRecycleService.insert(financeManageGoodsRecycleEsDTO);
									}
								}
							}
							if(detail.getTransitAll()!=null&&detail.getTransitAll().compareTo(BigDecimal.ZERO)>0) {
								FinanceManagePayReqDTO financeManagePayReqDTO=copyProperties(waybillEsDto,detail.getTransitAll(),ExpensesEnum.TERMINALCHARGE);
								financeManagePayReqDTO.setFeeRemark(detail.getDetailRemark());//外发备注->费用备注
								financeManagePayReqDTO.setPaymentType(detail.getPaymentType());
								financeManagePayReqDTO.setPaymentTypeName(detail.getPaymentTypeName());
								financeManagePayReqDTO.setBusinessNetworkId(detail.getOutBranchId());
								financeManagePayReqDTO.setPaymentNetworkId(detail.getOutBranchId());
								financeManagePayReqDTO.setPaymentNetworkName(detail.getOutBranchName());
								financeManagePayReqDTO.setBusinessNetworkName(detail.getOutBranchName());
								financeManagePayReqDTO.setBusinessDate(detail.getCreateTime());
								financeManagePayReqDTO.setPayeeId(transitCompanyId.intValue());
								financeManagePayReqDTO.setPayee(detail.getOutCompanyName());
								financeManagePayReqDTO.setTransitBillId(detail.getOutbillId());
								financeManagePayReqDTO.setTransitBillCode(detail.getOutbillCode());
								financeManagePayReqDTO.setSheetType(CalculationFeeEnum.WFZZD.getType());//业务单据类型
								financeManagePayReqDTO.setSheetTypeName(CalculationFeeEnum.WFZZD.getName());
								financeManagePayService.createPayInfo(financeManagePayReqDTO);
							}
						}
					}
				}
			}
		}
	}

	// 发车配载
	private void initDepartInsert(List<Long> ids) {
        FinanceSendMqDTO financeSendMqDTO=new FinanceSendMqDTO();
        financeSendMqDTO.setIds(ids);
        DataResult<List<TransportCostDetailEsDTO>> dataResult=transportArriveService.getTransportCost(financeSendMqDTO);
        if(dataResult.isSuccess()){
			List<TransportCostDetailEsDTO> detailDTOList = dataResult.getResult();
            if(CollectionUtils.isNotEmpty(detailDTOList)){
				TransportInfoEsDTO transportInfoEsDTO = transportArriveService.getTransportInfoById(detailDTOList.get(0).getTransportId()).getResult();
				List<FinanceManagePayReqDTO> financeManagePayReqDTOList= Lists.newArrayList();
				for (TransportCostDetailEsDTO transportCostDetailEsDTO : detailDTOList) {
					FinanceManagePayReqDTO financeManagePayReqDTO=initDepartFinanceManagePayReqDTO(transportInfoEsDTO, transportCostDetailEsDTO);
					financeManagePayReqDTOList.add(financeManagePayReqDTO);
				}
				financeManagePayService.createBatchPayInfo(financeManagePayReqDTOList);
            }
        }
	}

	private FinanceManagePayReqDTO initDepartFinanceManagePayReqDTO(TransportInfoEsDTO transportInfoEsDTO, TransportCostDetailEsDTO transportCostDetailEsDTO) {
		FinanceManagePayReqDTO financeManagePayReqDTO=new FinanceManagePayReqDTO();
		financeManagePayReqDTO.setSheetId(transportCostDetailEsDTO .getId());
		financeManagePayReqDTO.setDataSourceSheetId(transportInfoEsDTO.getDepartBatch());
		financeManagePayReqDTO.setCompanyId(transportCostDetailEsDTO.getCompanyId());
		financeManagePayReqDTO.setSheetType(CalculationFeeEnum.FCPZD.getType());
		financeManagePayReqDTO.setSheetTypeName(CalculationFeeEnum.FCPZD.getName());
		if(null!=transportCostDetailEsDTO.getFeeType()){
			financeManagePayReqDTO.setFeeType(transportCostDetailEsDTO.getFeeType()+"");
		}
		financeManagePayReqDTO.setFeeTypeName(transportCostDetailEsDTO.getFeeTypeName());
		financeManagePayReqDTO.setPaymentStatus(PaymentStatusEnum.NO_CHECK.getType());
		financeManagePayReqDTO.setPaymentStatusName(PaymentStatusEnum.NO_CHECK.getName());
		financeManagePayReqDTO.setBusinessNetworkId(transportCostDetailEsDTO.getCreateBranchId());
		financeManagePayReqDTO.setBusinessNetworkName(transportCostDetailEsDTO.getCreateBranchName());
		financeManagePayReqDTO.setPaymentNetworkId(transportCostDetailEsDTO.getPayBranchId());
		financeManagePayReqDTO.setPaymentNetworkName(transportCostDetailEsDTO.getPayBranchName());
		if(null!=transportCostDetailEsDTO.getPayeeId()){
			financeManagePayReqDTO.setPayeeId(Integer.parseInt(transportCostDetailEsDTO.getPayeeId()));
		}
		financeManagePayReqDTO.setPayee(transportCostDetailEsDTO.getPayeeName());
		financeManagePayReqDTO.setFeeRemark(transportCostDetailEsDTO.getCostDesc());
		financeManagePayReqDTO.setBusinessDate(transportCostDetailEsDTO.getCreateTime()==null?transportCostDetailEsDTO.getUpdateTime():transportCostDetailEsDTO.getCreateTime());
		financeManagePayReqDTO.setVehicleNo(transportInfoEsDTO.getVehicleNo());//车牌号
		financeManagePayReqDTO.setDriverName(transportInfoEsDTO.getDriverName());//司机姓名
		financeManagePayReqDTO.setPhoneNo(transportInfoEsDTO.getDriverPhoneNo());//司机电话
		financeManagePayReqDTO.setPayableAmount(transportCostDetailEsDTO.getAmount());//应付
		financeManagePayReqDTO.setUnpaidAmount(transportCostDetailEsDTO.getAmount());//未付
		financeManagePayReqDTO.setPaidAmount(new BigDecimal(0));//已付
		financeManagePayReqDTO.setPaymentFee(new BigDecimal(0));//付款手续费
		financeManagePayReqDTO.setActualPaidAmount(new BigDecimal(0));//实付金额
		financeManagePayReqDTO.setCreateTime(System.currentTimeMillis());
		return financeManagePayReqDTO;
	}

	// 派车单
	private void initDispatchInsert(List<Long> ids) {
		// 1.根据到货确认单ID查询到货确认单详情
		for(Long id:ids){
			//根据派车单Id查询派车单费用信息
			DataResult<TmsDispatcherEsDTO> dataResult=transportArriveService.getDispatcher(id);
			if(dataResult.isSuccess()){
				List<DispatcherFeeEsDTO> dispatcherFeeEsDTOList=dataResult.getResult().getDispatcherFeeList();
				if(CollectionUtils.isNotEmpty(dispatcherFeeEsDTOList)){
					DispatcherEsDTO dispatcherEsDTO=dataResult.getResult().getDispatcher();
					List<FinanceManagePayReqDTO> financeManagePayReqDTOList= Lists.newArrayList();
					for (DispatcherFeeEsDTO dispatcherFeeEsDTO : dispatcherFeeEsDTOList) {
						FinanceManagePayReqDTO financeManagePayReqDTO=initDispatcherFinanceManagePayReqDTO(dispatcherEsDTO,dispatcherFeeEsDTO);
						financeManagePayReqDTOList.add(financeManagePayReqDTO);
					}
					financeManagePayService.createBatchPayInfo(financeManagePayReqDTOList);
				}

			}

		}
		// 2.新增费用信息到应付表
	}

	private FinanceManagePayReqDTO initDispatcherFinanceManagePayReqDTO(DispatcherEsDTO dispatcherEsDTO, DispatcherFeeEsDTO dispatcherFeeEsDTO) {
		FinanceManagePayReqDTO financeManagePayReqDTO=new FinanceManagePayReqDTO();
		financeManagePayReqDTO.setSheetId(dispatcherFeeEsDTO.getId());
		financeManagePayReqDTO.setDataSourceSheetId(dispatcherEsDTO.getBatchCode());
		financeManagePayReqDTO.setCompanyId(dispatcherFeeEsDTO.getCompanyId());
		financeManagePayReqDTO.setSheetType(CalculationFeeEnum.PCD.getType());
		financeManagePayReqDTO.setSheetTypeName(CalculationFeeEnum.PCD.getName());
		if(null!=dispatcherFeeEsDTO.getFeeType()){
			financeManagePayReqDTO.setFeeType(dispatcherFeeEsDTO.getFeeType());
		}
		financeManagePayReqDTO.setFeeTypeName(dispatcherFeeEsDTO.getFeeTypeName());
		financeManagePayReqDTO.setPaymentStatus(PaymentStatusEnum.NO_CHECK.getType());
		financeManagePayReqDTO.setPaymentStatusName(PaymentStatusEnum.NO_CHECK.getName());
		financeManagePayReqDTO.setBusinessNetworkId(dispatcherEsDTO.getBranchId());
		financeManagePayReqDTO.setBusinessNetworkName(dispatcherEsDTO.getBranchName());
		financeManagePayReqDTO.setPaymentNetworkId(dispatcherFeeEsDTO.getPayerId());
		financeManagePayReqDTO.setPaymentNetworkName(dispatcherFeeEsDTO.getPayerName());
		financeManagePayReqDTO.setPayeeId(dispatcherFeeEsDTO.getPayeeId());
		financeManagePayReqDTO.setPayee(dispatcherFeeEsDTO.getPayeeName());
		financeManagePayReqDTO.setFeeRemark(dispatcherFeeEsDTO.getRemark());
		financeManagePayReqDTO.setBusinessDate(dispatcherFeeEsDTO.getCreateTime());
		financeManagePayReqDTO.setVehicleNo(dispatcherEsDTO.getVehicleNo());//车牌号
		financeManagePayReqDTO.setDriverName(dispatcherEsDTO.getDriverName());//司机姓名
		financeManagePayReqDTO.setPhoneNo(dispatcherEsDTO.getPhoneNo());//司机电话
		financeManagePayReqDTO.setPayableAmount(dispatcherFeeEsDTO.getAmount());//应付
		financeManagePayReqDTO.setUnpaidAmount(dispatcherFeeEsDTO.getAmount());//未付
		financeManagePayReqDTO.setPaidAmount(new BigDecimal(0));//已付
		financeManagePayReqDTO.setPaymentFee(new BigDecimal(0));//付款手续费
		financeManagePayReqDTO.setActualPaidAmount(new BigDecimal(0));//实付金额
		financeManagePayReqDTO.setCreateTime(System.currentTimeMillis());
		return financeManagePayReqDTO;
	}

	private void initWaybillInsert(List<Long> ids) {
		for (Long id : ids) {
			WaybillEsDTO waybillEsDto = waybillService.queryWaybillEsDTO(id);
			if (waybillEsDto != null) {
				//现付
			    saveFinanceManageReceiptEsDTO(waybillEsDto,waybillEsDto.getCashPay(),PayWayEnum.NOWPAY);
			    //月结
			    saveFinanceManageReceiptEsDTO(waybillEsDto,waybillEsDto.getMonthlyPay(),PayWayEnum.MONTHPAY);
			    //回单付
			    saveFinanceManageReceiptEsDTO(waybillEsDto,waybillEsDto.getReceiptPay(),PayWayEnum.BACKPAY);
			    //欠付
			    saveFinanceManageReceiptEsDTO(waybillEsDto,waybillEsDto.getTardypay(),PayWayEnum.OWEPAY);
			    //货款扣
			    saveFinanceManageReceiptEsDTO(waybillEsDto,waybillEsDto.getGoodsPaymentDeduction(),PayWayEnum.PAYMENTDUCTION);
			    //根据运单ID查询费用信息
				List<WaybillFeeEsDTO> waybillFeeEsDTOList=waybillFeeService.getWaybillFeeByWaybillId(id);
				if (CollectionUtils.isNotEmpty(waybillFeeEsDTOList)) {
					for (WaybillFeeEsDTO waybillFeeEsDTO : waybillFeeEsDTOList) {
						if (WaybillFeeTypeEnum.FREIGHT_ADVANCED.getType() == waybillFeeEsDTO.getAttrId()) {//垫付费
							saveFinanceManagePayEsDTO(waybillEsDto, waybillFeeEsDTO.getFee(), ExpensesEnum.FERIGHTADVANCED);
						}
						if (WaybillFeeTypeEnum.BUSINESS.getType() == waybillFeeEsDTO.getAttrId()) {//业务费
							saveFinanceManagePayEsDTO(waybillEsDto, waybillFeeEsDTO.getFee(), ExpensesEnum.BUSINESS);
						}
					}
				}
			}
		}
	}

	private void saveFinanceManagePayEsDTO(WaybillEsDTO waybillEsDto,BigDecimal freightPayment, ExpensesEnum expensesEnum) {
		FinanceManagePayReqDTO financeManagePayReqDTO=copyProperties(waybillEsDto,freightPayment,expensesEnum);
		financeManagePayService.createPayInfo(financeManagePayReqDTO);
	}

	private void saveFinanceManageReceiptEsDTO(WaybillEsDTO waybillEsDTO,BigDecimal money,PayWayEnum payWayEnum){
	  if (money != null && money.compareTo(BigDecimal.ZERO) > 0) {
		FinanceManageReceiptReqDTO financeManageReceiptReqDto =initFinanceManageReceiptReqDTO(waybillEsDTO.getId(),payWayEnum);
		FinanceManageReceiptEsDTO financeManageReceiptEsDto = esFinanceManageReceiptService.findFinanceManageReceipt(financeManageReceiptReqDto);
		 if(null==financeManageReceiptEsDto) {
		    financeManageReceiptEsDto = new FinanceManageReceiptEsDTO();
		    financeManageReceiptEsDto.setPayWay(payWayEnum.getType());
		    financeManageReceiptEsDto.setPayWayName(payWayEnum.getName());
		    financeManageReceiptEsDto.setReceiptAmount(money);
		    financeManageReceiptEsDto.setUnreceiptAmount(money);
		    financeManageReceiptEsDto.setCodeType(CodeTypeEnum.YD.getType());
		    financeManageReceiptEsDto.setCodeTypeName(CodeTypeEnum.YD.getName());
		    if(payWayEnum==PayWayEnum.BACKPAY) {
			    financeManageReceiptEsDto.setBackStatus(BackStatusEnum.UNBACK.getType());
			    financeManageReceiptEsDto.setBackStatusName(BackStatusEnum.BACK.getName());
		    }
		    financeManageReceiptService.insert(waybillEsDTO, financeManageReceiptEsDto);
		}
	  }
    }

    private FinanceManageReceiptReqDTO initFinanceManageReceiptReqDTO(long id,PayWayEnum payWayEnum){
	  FinanceManageReceiptReqDTO financeManageReceiptReqDTO=new FinanceManageReceiptReqDTO();
	  financeManageReceiptReqDTO.setWaybillId(id);
	  financeManageReceiptReqDTO.setPayWay(payWayEnum.getType());
	  return financeManageReceiptReqDTO;
    }
    
    private void initChangeSend(List<Long> ids) {
    	//TODO 自提改配送
    	try {
    		for(Long id:ids) {
        		AlterationDeliveryChangeInfoRespDTO result = alterationDeliveryService.getChangeInfo(id).getResult();
        		if(result!=null&&result.getDeliveryFee()!=null&&result.getDeliveryFee().compareTo(BigDecimal.ZERO)>0) {
        			WaybillEsDTO waybillEsDto = waybillService.queryWaybillEsDTO(result.getWaybillId());
        			FinanceManageReceiptReqDTO financeManageReceiptReqDto =initFinanceManageReceiptReqDTO(waybillEsDto.getId(),PayWayEnum.CHANGESENF);
        			FinanceManageReceiptEsDTO financeManageReceiptEsDto = esFinanceManageReceiptService.findFinanceManageReceipt(financeManageReceiptReqDto);
        			if(financeManageReceiptEsDto==null) {
            			financeManageReceiptEsDto = new FinanceManageReceiptEsDTO();
            		    financeManageReceiptEsDto.setPayWay(PayWayEnum.CHANGESENF.getType());
            		    financeManageReceiptEsDto.setPayWayName(PayWayEnum.CHANGESENF.getName());
            		    financeManageReceiptEsDto.setReceiptAmount(result.getDeliveryFee());
            		    financeManageReceiptEsDto.setUnreceiptAmount(result.getDeliveryFee());
            		    financeManageReceiptEsDto.setCodeType(CodeTypeEnum.YD.getType());
            		    financeManageReceiptEsDto.setCodeTypeName(CodeTypeEnum.YD.getName());
            		    financeManageReceiptEsDto.setDeliveryNetworkId(result.getReceiveWay());
            		    financeManageReceiptEsDto.setDeliveryNetworkName(result.getReceiveWayName()); //收款网点
            		    financeManageReceiptEsDto.setPayeeName(result.getPayUserName()); //付款方
            		    financeManageReceiptService.insert(waybillEsDto, financeManageReceiptEsDto);
        			}
        		}
        	}
    	}catch(Exception e) {
    		throw e;
    	}
    	
	}

	// =========================================================删除=========================================================
	private void deleteWaybill(List<Long> ids) {
		//删除应收表里（除到付）和应付表里的来源单为运单的信息
		FinanceDeleteReqDTO financeDeleteReqDTO = new FinanceDeleteReqDTO();
		for(Long id:ids) {
			WaybillEsDTO waybillEsDto = waybillService.queryWaybillEsDTO(id);
			if(waybillEsDto!=null) {
				financeDeleteReqDTO.setWaybillId(id);
				financeDeleteReqDTO.setCompanyId(Long.parseLong(waybillEsDto.getCompanyId().toString()));
				financeDeleteReqDTO.setCode(waybillEsDto.getCode());
				financeDeleteReqDTO.setPayway(PayWayEnum.TOPAY.getType());//删除不等于到付的应收信息
				financeManageReceiptService.deleteByCode(financeDeleteReqDTO);
			}
		}
	}
	private void deleteDispatch(List<Long> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			FinanceManagePayQueryReqDTO financeManagePayQueryReqDto;
			FinanceManagePayDO financeManagePayDo;
			for (Long id : ids) {
				//根据派车单ID查询派车单费用信息
				DataResult<TmsDispatcherEsDTO> dataResult = transportArriveService.getDispatcher(id);
				if (dataResult != null && dataResult.isSuccess()) {
					TmsDispatcherEsDTO tmsDispatcherEsDto = dataResult.getResult();
					if (tmsDispatcherEsDto != null) {
						List<DispatcherFeeEsDTO> dispatcherFeeEsDtoList = tmsDispatcherEsDto.getDispatcherFeeList();
						if (CollectionUtils.isNotEmpty(dispatcherFeeEsDtoList)) {
							for (DispatcherFeeEsDTO dispatcherFeeEsDTO : dispatcherFeeEsDtoList) {
								financeManagePayQueryReqDto = new FinanceManagePayQueryReqDTO();
								financeManagePayQueryReqDto.setSheetId(dispatcherFeeEsDTO.getId());//来源单ID
								financeManagePayQueryReqDto.setFeeType(dispatcherFeeEsDTO.getFeeType());//费用类型
								financeManagePayQueryReqDto.setDataSourceCode(tmsDispatcherEsDto.getDispatcher().getBatchCode());//派车批次
								FinanceManagePayEsDTO financeManagePayEsDto = esFinanceManagePayService.getEsBySheetIdFeeType(financeManagePayQueryReqDto);
								if (financeManagePayEsDto != null) {
									financeManagePayDo = new FinanceManagePayDO();
									financeManagePayDo.setSheetId(dispatcherFeeEsDTO.getId());
									financeManagePayDo.setCompanyId(dispatcherFeeEsDTO.getCompanyId());
									financeManagePayDo.setFeeType(dispatcherFeeEsDTO.getFeeType());
									financeManagePayDo.setDataSourceSheetId(financeManagePayQueryReqDto.getDataSourceCode());
									financeManagePayService.deletePayInfo(financeManagePayDo, financeManagePayQueryReqDto);
								}else {
									logger.error("删除的数据不存在，ID：" + dispatcherFeeEsDTO.getId() + ", 费用类型：" + dispatcherFeeEsDTO.getFeeType());
								}
							}
						}
					}
				}
			}
		}
	}
	private void deleteDepart(List<Long> ids) {
    	if (CollectionUtils.isNotEmpty(ids)) {
			FinanceManagePayQueryReqDTO financeManagePayQueryReqDto;
			FinanceManagePayDO financeManagePayDo;
			for (Long id : ids) {
				financeManagePayQueryReqDto = new FinanceManagePayQueryReqDTO();
				financeManagePayQueryReqDto.setSheetId(id);//来源单ID
				financeManagePayQueryReqDto.setSheetType(CalculationFeeEnum.FCPZD.getType());//单据类型
				FinanceManagePayEsDTO financeManagePayEsDto = esFinanceManagePayService.getEsBySheetIdFeeType(financeManagePayQueryReqDto);
				if (financeManagePayEsDto != null) {
					financeManagePayDo = new FinanceManagePayDO();
					financeManagePayDo.setSheetId(financeManagePayEsDto.getSheetId());
					financeManagePayDo.setCompanyId(financeManagePayEsDto.getCompanyId());
					financeManagePayDo.setFeeType(financeManagePayEsDto.getFeeType());
					financeManagePayDo.setSheetType(CalculationFeeEnum.FCPZD.getType());//单据类型
					financeManagePayService.deletePayInfo(financeManagePayDo, financeManagePayQueryReqDto);
				}else {
					logger.error("删除的数据不存在，ID：" + id);
				}
			}
		}
	}

	private void deleteHandlingOrder(List<Long> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			FinanceManagePayQueryReqDTO financeManagePayQueryReqDto;
			FinanceManagePayDO financeManagePayDo;
			for (Long id : ids) {
				//根据派车单ID查询派车单费用信息
				DataResult<List<HandlingorderFeeEsDTO>> dataResult = transportArriveService.getHandlingFeeList(id);
				if (dataResult != null && dataResult.isSuccess()) {
					List<HandlingorderFeeEsDTO> handlingorderFeeEsDtoList = dataResult.getResult();
					if (CollectionUtils.isNotEmpty(handlingorderFeeEsDtoList)) {
						for (HandlingorderFeeEsDTO handlingorderFeeEsDto : handlingorderFeeEsDtoList) {
							financeManagePayQueryReqDto = new FinanceManagePayQueryReqDTO();
							financeManagePayQueryReqDto.setSheetId(handlingorderFeeEsDto.getId());//来源单ID
							financeManagePayQueryReqDto.setFeeType(handlingorderFeeEsDto.getFeeType());//费用类型
							financeManagePayQueryReqDto.setDataSourceCode(handlingorderFeeEsDto.getHandlingOrderCode());//批次号
							FinanceManagePayEsDTO financeManagePayEsDto = esFinanceManagePayService.getEsBySheetIdFeeType(financeManagePayQueryReqDto);
							if (financeManagePayEsDto != null) {
								financeManagePayDo = new FinanceManagePayDO();
								financeManagePayDo.setSheetId(handlingorderFeeEsDto.getId());
								financeManagePayDo.setCompanyId(handlingorderFeeEsDto.getCompanyId());
								financeManagePayDo.setFeeType(handlingorderFeeEsDto.getFeeType());
								financeManagePayDo.setDataSourceSheetId(financeManagePayQueryReqDto.getDataSourceCode());
								financeManagePayService.deletePayInfo(financeManagePayDo, financeManagePayQueryReqDto);
							}else {
								logger.error("删除的数据不存在，ID：" + handlingorderFeeEsDto.getId() + ", 费用类型：" + handlingorderFeeEsDto.getFeeType());
							}
						}
					}
				}
			}
		}
	}

	private void deleteArriveGoods(List<Long> ids) {
		//根据到货确认单ID查询到货确认单详情
		if (CollectionUtils.isNotEmpty(ids)) {
			FinanceManagePayQueryReqDTO financeManagePayQueryReqDto;
			FinanceManagePayDO financeManagePayDo;
			for (Long id : ids) {
				DataResult<TmsTransportArriveDTO> dataResult = transportArriveService.getArrivalInfoAndCostById(id);
				if (dataResult != null && dataResult.isSuccess()) {
					TmsTransportArriveDTO tmsTransportArriveDto = dataResult.getResult();
					if (tmsTransportArriveDto != null) {
						List<TransportArrivalCostDetailEsDTO> transportArrivalCostDetailEsDtoList = tmsTransportArriveDto.getTransportArrivalCostDetailList();
						if (CollectionUtils.isNotEmpty(transportArrivalCostDetailEsDtoList)) {
							for (TransportArrivalCostDetailEsDTO transportArrivalCostDetailEsDto : transportArrivalCostDetailEsDtoList) {
								financeManagePayQueryReqDto = new FinanceManagePayQueryReqDTO();
								financeManagePayQueryReqDto.setSheetId(transportArrivalCostDetailEsDto.getId());//来源单ID
								financeManagePayQueryReqDto.setFeeType(transportArrivalCostDetailEsDto.getFeeType());//费用类型
								financeManagePayQueryReqDto.setDataSourceCode(tmsTransportArriveDto.getTransportArrivalInfo().getArrivalBatch());//批次号
								FinanceManagePayEsDTO financeManagePayEsDto = esFinanceManagePayService.getEsBySheetIdFeeType(financeManagePayQueryReqDto);
								if (financeManagePayEsDto != null) {
									financeManagePayDo = new FinanceManagePayDO();
									financeManagePayDo.setSheetId(transportArrivalCostDetailEsDto.getId());
									financeManagePayDo.setCompanyId(transportArrivalCostDetailEsDto.getCompanyId());
									financeManagePayDo.setFeeType(transportArrivalCostDetailEsDto.getFeeType());
									financeManagePayDo.setDataSourceSheetId(financeManagePayQueryReqDto.getDataSourceCode());
									financeManagePayService.deletePayInfo(financeManagePayDo, financeManagePayQueryReqDto);
								}else {
									logger.error("删除的数据不存在，ID：" + transportArrivalCostDetailEsDto.getId() + ", 费用类型：" + transportArrivalCostDetailEsDto.getFeeType());
								}
							}
						}
					}
				}
			}
		}
	}
	private void deleteSign(List<Long> ids) {
		// 1.删除应收表里的该运单的到付信息
		// 2.删除货款回收表里的该运单的代收货款信息
		FinanceDeleteReqDTO financeDeleteReqDTO;
		FinanceManageGoodsRecycleDO financeManageGoodsRecycleDo;
		for (Long id : ids) {
			WaybillEsDTO waybillEsDto = waybillService.queryWaybillEsDTO(id);
			if (waybillEsDto != null) {
				if(waybillEsDto.getSignStatus()!=null&&SignStatusEnum.NO_SIGN.getType()==waybillEsDto.getSignStatus()) {
					financeDeleteReqDTO = new FinanceDeleteReqDTO();
					financeDeleteReqDTO.setWaybillId(id);
					financeDeleteReqDTO.setCompanyId(Long.parseLong(waybillEsDto.getCompanyId().toString()));
					financeDeleteReqDTO.setCode(waybillEsDto.getCode());
					financeDeleteReqDTO.setPayway(PayWayEnum.TOPAY.getType());//删除等于到付的应收信息
					financeManageReceiptService.deleteReceiptInfo(financeDeleteReqDTO);
					FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDto = esFinanceManageGoodsRecycleService.findByWaybillId(waybillEsDto.getId());
					if (financeManageGoodsRecycleEsDto != null) {
						financeManageGoodsRecycleDo = new FinanceManageGoodsRecycleDO();
						financeManageGoodsRecycleDo.setCompanyId(Long.valueOf(waybillEsDto.getCompanyId() + ""));
						financeManageGoodsRecycleDo.setWaybillId(waybillEsDto.getId());//运单ID
						financeManageGoodsRecycleService.deleteGoodsRecycleInfo(financeManageGoodsRecycleDo, waybillEsDto.getId());
					}else {
						logger.error("删除的信息不存在，运单ID：" + waybillEsDto.getId());
					}
				}
			}
		}
	}

	private void deleteTransit(List<Long> ids) {
		// 1.删除应收表里的该运单的到付信息
		// 2.删除货款回收表里的该运单的代收货款信息
		// 3.删除应付表里的中转费信息
		FinanceDeleteReqDTO financeDeleteReqDTO;
		FinanceManageGoodsRecycleDO financeManageGoodsRecycleDo;
		FinanceManagePayQueryReqDTO financeManagePayQueryReqDto;
		FinanceManagePayDO financeManagePayDo;
		for (Long id : ids) {
			DataResult<OutBillAddReqDTO> dr = outbillService.queryOutBill(id);
			if (dr == null || dr.getResult() == null) {
				logger.error("外发服务调用失败，原因：" + dr == null ? null : dr.getStatus().getStatusReason());
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_DEPENDENCY, "外发服务异常");
			}
			List<OutBillDetailEsDTO> outBillDetails = dr.getResult().getOutBillDetails();
			if (CollectionUtils.isNotEmpty(outBillDetails)) {
				for (OutBillDetailEsDTO outBillDetailEsDto : outBillDetails) {
					financeDeleteReqDTO = new FinanceDeleteReqDTO();
					financeDeleteReqDTO.setWaybillId(outBillDetailEsDto.getWaybillId());
					financeDeleteReqDTO.setCompanyId(Long.parseLong(outBillDetailEsDto.getCompanyId().toString()));
					financeDeleteReqDTO.setCode(outBillDetailEsDto.getWaybillCode());
					financeDeleteReqDTO.setPayway(PayWayEnum.TOPAY.getType());//删除等于到付的应收信息
					financeManageReceiptService.deleteReceiptInfo(financeDeleteReqDTO);
					FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDto = esFinanceManageGoodsRecycleService.findByWaybillId(outBillDetailEsDto.getWaybillId());
					if (financeManageGoodsRecycleEsDto != null) {
						financeManageGoodsRecycleDo = new FinanceManageGoodsRecycleDO();
						financeManageGoodsRecycleDo.setCompanyId(outBillDetailEsDto.getCompanyId());
						financeManageGoodsRecycleDo.setWaybillId(outBillDetailEsDto.getWaybillId());//运单ID
						financeManageGoodsRecycleService.deleteGoodsRecycleInfo(financeManageGoodsRecycleDo, outBillDetailEsDto.getWaybillId());
					}else {
						logger.error("删除的信息不存在，运单ID：" + outBillDetailEsDto.getWaybillId());
					}
					financeManagePayQueryReqDto = new FinanceManagePayQueryReqDTO();
					financeManagePayQueryReqDto.setSheetId(outBillDetailEsDto.getWaybillId());//来源单ID
					financeManagePayQueryReqDto.setFeeType(ExpensesEnum.TERMINALCHARGE.getDocType());//费用类型
					financeManagePayQueryReqDto.setDataSourceCode(outBillDetailEsDto.getWaybillCode());//来源单号
					FinanceManagePayEsDTO financeManagePayEsDto = esFinanceManagePayService.getEsBySheetIdFeeType(financeManagePayQueryReqDto);
					if (financeManagePayEsDto != null) {
						financeManagePayDo = new FinanceManagePayDO();
						financeManagePayDo.setSheetId(outBillDetailEsDto.getWaybillId());
						financeManagePayDo.setCompanyId(outBillDetailEsDto.getCompanyId());
						financeManagePayDo.setFeeType(ExpensesEnum.TERMINALCHARGE.getDocType());
						financeManagePayDo.setDataSourceSheetId(financeManagePayQueryReqDto.getDataSourceCode());
						financeManagePayService.deletePayInfo(financeManagePayDo, financeManagePayQueryReqDto);
					}
				}
			}
		}
	}

	// =========================================================修改=========================================================

	private void updateSign(List<Long> ids) {
		// 1.删除
		// 2.新增
		deleteSign(ids);
		initSignInsert(ids);

	}
	private void updateWaybill(List<Long> ids) {
		// 1.删除
		// 2.新增
		deleteWaybill(ids);
		initWaybillInsert(ids);
	}
	private void updateDispatch(List<Long> ids) {
		// 1.删除
		// 2.新增
		deleteDispatch(ids);
		initDispatchInsert(ids);
	}

	private void updateTransit(List<Long> ids) {
		// 1.删除
		// 2.新增
		deleteTransit(ids);
		initTransitInsert(ids);

	}
	private void updateDepart(List<Long> ids) {
		// 1.删除
		// 2.新增
		deleteDepart(ids);
		initDepartInsert(ids);
	}
	private void updateArriveGoods(List<Long> ids) {
		// 1.删除
		// 2.新增
		deleteArriveGoods(ids);
		initArriveGoodsInsert(ids);
	}
	private FinanceManagePayReqDTO copyProperties(WaybillEsDTO waybillEsDTO, BigDecimal money, ExpensesEnum expensesEnum) {
		FinanceManagePayReqDTO financeManagePayReqDTO=new FinanceManagePayReqDTO();
		financeManagePayReqDTO.setSheetId(waybillEsDTO.getId());//单据ID
		financeManagePayReqDTO.setSheetType(CalculationFeeEnum.YD.getType());//业务单据类型
		financeManagePayReqDTO.setSheetTypeName(CalculationFeeEnum.YD.getName());
		financeManagePayReqDTO.setDataSourceSheetId(waybillEsDTO.getCode());//来源单号
		financeManagePayReqDTO.setPaymentType(waybillEsDTO.getPayWay());//支付方式
		financeManagePayReqDTO.setPaymentTypeName(waybillEsDTO.getPayWayName());//支付方式名称
		financeManagePayReqDTO.setCompanyId(waybillEsDTO.getCompanyId().longValue());//公司ID
		financeManagePayReqDTO.setPaymentStatus(PaymentStatusEnum.NO_CHECK.getType());//付款状态
		financeManagePayReqDTO.setPaymentStatusName(PaymentStatusEnum.NO_CHECK.getName());
		financeManagePayReqDTO.setFeeType(expensesEnum.getDocType());//费用类型
		financeManagePayReqDTO.setFeeTypeName(expensesEnum.getName());
		if(expensesEnum==ExpensesEnum.BUSINESS) {
			if (StringUtils.isNotBlank(waybillEsDTO.getInvoiceUser())) {
				financeManagePayReqDTO.setPayeeId(waybillEsDTO.getInvoiceCustomerVipId());
				financeManagePayReqDTO.setPayee(waybillEsDTO.getInvoiceUser());
			}else {
				financeManagePayReqDTO.setPayeeId(waybillEsDTO.getInvoiceCustomerId());
				financeManagePayReqDTO.setPayee(waybillEsDTO.getInvoiceCompany());
			}
		}else {
			if (StringUtils.isNotBlank(waybillEsDTO.getInvoiceCompany())) {
				financeManagePayReqDTO.setPayeeId(waybillEsDTO.getInvoiceCustomerId());
				financeManagePayReqDTO.setPayee(waybillEsDTO.getInvoiceCompany());
			}else {
				financeManagePayReqDTO.setPayeeId(waybillEsDTO.getInvoiceCustomerVipId());
				financeManagePayReqDTO.setPayee(waybillEsDTO.getInvoiceUser());
			}
		}
		if(null!=waybillEsDTO.getInvoiceOrgId()){//付款网点ID
			financeManagePayReqDTO.setPaymentNetworkId(waybillEsDTO.getInvoiceOrgId());
			financeManagePayReqDTO.setBusinessNetworkId(waybillEsDTO.getInvoiceOrgId());
		}
		if(null!=waybillEsDTO.getInvoiceOrgName()){
			financeManagePayReqDTO.setPaymentNetworkName(waybillEsDTO.getInvoiceOrgName());
			financeManagePayReqDTO.setBusinessNetworkName(waybillEsDTO.getInvoiceOrgName());
		}
		if(null!=waybillEsDTO.getInvoiceCustomerId()){//发货方ID
			financeManagePayReqDTO.setShipperId(waybillEsDTO.getInvoiceCustomerId());
		}
		if(StringUtils.isNotBlank(waybillEsDTO.getInvoiceCompany())){//发货方名称
			financeManagePayReqDTO.setShipperName(waybillEsDTO.getInvoiceCompany());
		}
		if(waybillEsDTO.getInvoiceUserId() != null){//发货人ID
			financeManagePayReqDTO.setInvoiceUserId(waybillEsDTO.getInvoiceUserId().intValue());
		}
		if(StringUtils.isNotBlank(waybillEsDTO.getInvoiceUser())){//发货人
			financeManagePayReqDTO.setInvoiceUserName(waybillEsDTO.getInvoiceUser());
		}
		if(null!=waybillEsDTO.getInvoiceCustomerVipId()){//发货人vipID
			financeManagePayReqDTO.setInvoiceCustomerVipId(waybillEsDTO.getInvoiceCustomerVipId().longValue());
		}
		if(null!=waybillEsDTO.getInvoiceCustomerId()){
			financeManagePayReqDTO.setInvoiceCompanyId(waybillEsDTO.getInvoiceCustomerId().longValue());//发货公司id=发货方id
		}
		financeManagePayReqDTO.setInvoiceCompanyName(waybillEsDTO.getInvoiceCompany());
		if(null!=waybillEsDTO.getInvoiceMobleNo()){//发货人手机
			financeManagePayReqDTO.setInvoiceUserMobile(waybillEsDTO.getInvoiceMobleNo());
		}
		if(null!=waybillEsDTO.getInvoiceTelNo()){//发货人电话
			financeManagePayReqDTO.setInvoiceUserTel(waybillEsDTO.getInvoiceTelNo());
		}
		if(null!=waybillEsDTO.getRemark()){//运单备注
			financeManagePayReqDTO.setFeeRemark(waybillEsDTO.getRemark());
		}
		if(null!=waybillEsDTO.getCreateTime()){//业务时间
			financeManagePayReqDTO.setBusinessDate(waybillEsDTO.getCreateTime());
		}
		if(null!=waybillEsDTO.getSalesmanId()){//业务员ID
			financeManagePayReqDTO.setSalesmanId(waybillEsDTO.getSalesmanId());
		}
		if(null!=waybillEsDTO.getSalesmanName()){//业务员
			financeManagePayReqDTO.setSalesmanName(waybillEsDTO.getSalesmanName());
		}
		financeManagePayReqDTO.setPayableAmount(money);//应付
		financeManagePayReqDTO.setPaidAmount(new BigDecimal(0));//已付
		financeManagePayReqDTO.setPaymentFee(new BigDecimal(0));//付款手续费
		financeManagePayReqDTO.setActualPaidAmount(new BigDecimal(0));//实付金额
		financeManagePayReqDTO.setUnpaidAmount(money);//未付
		if(null!=waybillEsDTO.getReceiptCustomerId()){
			financeManagePayReqDTO.setReceiverId(waybillEsDTO.getReceiptCustomerId()); //收货方ID
		}
		if(null!=waybillEsDTO.getReceiptCompany()){
			financeManagePayReqDTO.setReceiverName(waybillEsDTO.getReceiptCompany()); //收货方名称
		}
		if(null!=waybillEsDTO.getReceiptUserId()){
			financeManagePayReqDTO.setReceiptUserId(waybillEsDTO.getReceiptUserId().intValue()); //收货人ID
		}
		if(null!=waybillEsDTO.getReceiptUser()){
			financeManagePayReqDTO.setReceiptUserName(waybillEsDTO.getReceiptUser()); //收货人
		}
		if(null!=waybillEsDTO.getReceiptConsignorMobleNo()){
			financeManagePayReqDTO.setReceiptUserMobile(waybillEsDTO.getReceiptConsignorMobleNo()); //收货人手机
		}
		if(null!=waybillEsDTO.getReceiptConsignorTelNo()){
			financeManagePayReqDTO.setReceiptUserTel(waybillEsDTO.getReceiptConsignorTelNo()); //收货方电话
		}
		financeManagePayReqDTO.setReceiptCompanyName(waybillEsDTO.getReceiptCompany()==null?"":waybillEsDTO.getReceiptCompany());
		financeManagePayReqDTO.setReceiptUserName(waybillEsDTO.getReceiptUser()==null?"":waybillEsDTO.getReceiptUser());
		financeManagePayReqDTO.setReceiptUserMobile(waybillEsDTO.getReceiptConsignorMobleNo()==null?"":waybillEsDTO.getReceiptConsignorMobleNo());
		financeManagePayReqDTO.setCreateUserId(waybillEsDTO.getCreateUserId());
		financeManagePayReqDTO.setCreateTime(waybillEsDTO.getCreateTime());
		financeManagePayReqDTO.setCreateUserName(waybillEsDTO.getCreateUserName());
		return  financeManagePayReqDTO;
	}
	
	private void updateSignStatus(List<Long> ids) {
		for(Long id:ids) {
			WaybillEsDTO waybillEsDto = waybillService.queryWaybillEsDTO(id);
			if(waybillEsDto!=null) {
				FinanceReceiptStatusUpdateDTO financeReceiptStatusUpdateDTO = new FinanceReceiptStatusUpdateDTO();
				financeReceiptStatusUpdateDTO.setCompanyId(waybillEsDto.getCompanyId().longValue());
				financeReceiptStatusUpdateDTO.setSignStatus(waybillEsDto.getSignStatus());
				financeReceiptStatusUpdateDTO.setSignStatusName(waybillEsDto.getSignStatusName());
				financeReceiptStatusUpdateDTO.setSignTime(System.currentTimeMillis());
				financeReceiptStatusUpdateDTO.setIds(ids);
				financeManageReceiptService.updateSignStatus(financeReceiptStatusUpdateDTO);
				break;
			}
		}
	}
	

	private void initChangeArriveGoodsInsert(List<Long> ids) {
		// 1.根据到货确认单ID查询到货确认单详情
		ArrivalCostReqDTO arrivalCostReqDTO = new ArrivalCostReqDTO();
		arrivalCostReqDTO.setIdList(ids);
        DataResult<List<TransportArrivalCostDetailEsDTO>> dataResult=transportArriveService.getArrivalCostByIds(arrivalCostReqDTO);
        if(dataResult.isSuccess()){
			List<TransportArrivalCostDetailEsDTO> detailDTOList = dataResult.getResult();
            if(CollectionUtils.isNotEmpty(detailDTOList)){
            	TmsTransportArriveDTO arriverInfoEsDTO = transportArriveService.getArrivalInfoAndCostById(detailDTOList.get(0).getArrivalId()).getResult();
				List<FinanceManagePayReqDTO> financeManagePayReqDTOList= Lists.newArrayList();
				for (TransportArrivalCostDetailEsDTO transportCostDetailEsDTO : detailDTOList) {
					FinanceManagePayReqDTO financeManagePayReqDTO=initFinanceManagePayReqDTO(transportCostDetailEsDTO,arriverInfoEsDTO.getTransportArrivalInfo(),arriverInfoEsDTO.getTransportInfo());
					financeManagePayReqDTOList.add(financeManagePayReqDTO);
				}
				financeManagePayService.createBatchPayInfo(financeManagePayReqDTOList);
            }
        }
	}

	private void initChangeDispatchInsert(List<Long> ids) {
    	if (CollectionUtils.isNotEmpty(ids)) {
			DataResult<List<DispatcherFeeEsDTO>> dataResult=transportArriveService.getDispatcherFeeListByIds(ids);
			if(dataResult.isSuccess()){
				List<DispatcherFeeEsDTO> dispatcherFeeEsDTOList = dataResult.getResult();
				if(CollectionUtils.isNotEmpty(dispatcherFeeEsDTOList)){
					TmsDispatcherEsDTO tmsDispatcherEsDTO = transportArriveService.getDispatcher(dispatcherFeeEsDTOList.get(0).getDispatcherId()).getResult();
					List<FinanceManagePayReqDTO> financeManagePayReqDTOList= Lists.newArrayList();
					for (DispatcherFeeEsDTO dispatcherFeeEsDTO : dispatcherFeeEsDTOList) {
						FinanceManagePayReqDTO financeManagePayReqDTO=initDispatcherFinanceManagePayReqDTO(tmsDispatcherEsDTO.getDispatcher(), dispatcherFeeEsDTO);
						financeManagePayReqDTOList.add(financeManagePayReqDTO);
					}
					financeManagePayService.createBatchPayInfo(financeManagePayReqDTOList);
				}
			}
		}
	}
	

	private void deleteChangeArriveGoods(List<Long> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			FinanceManagePayQueryReqDTO financeManagePayQueryReqDto;
			FinanceManagePayDO financeManagePayDo;
			for (Long id : ids) {
				financeManagePayQueryReqDto = new FinanceManagePayQueryReqDTO();
				financeManagePayQueryReqDto.setSheetId(id);//来源单ID
				financeManagePayQueryReqDto.setSheetType(CalculationFeeEnum.DHQRD.getType());
				FinanceManagePayEsDTO financeManagePayEsDto = esFinanceManagePayService.getEsBySheetIdFeeType(financeManagePayQueryReqDto);
				if (financeManagePayEsDto != null) {
					financeManagePayDo = new FinanceManagePayDO();
					financeManagePayDo.setSheetId(financeManagePayEsDto.getSheetId());
					financeManagePayDo.setCompanyId(financeManagePayEsDto.getCompanyId());
					financeManagePayDo.setFeeType(financeManagePayEsDto.getFeeType());
					financeManagePayDo.setSheetType(CalculationFeeEnum.DHQRD.getType());
					financeManagePayService.deletePayInfo(financeManagePayDo, financeManagePayQueryReqDto);
				}else {
					logger.error("删除的数据不存在，ID：" + id );
				}
			}
		}
	}

	private void deleteChangeDispatch(List<Long> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			FinanceManagePayQueryReqDTO financeManagePayQueryReqDto;
			FinanceManagePayDO financeManagePayDo;
			for (Long id : ids) {
				financeManagePayQueryReqDto = new FinanceManagePayQueryReqDTO();
				financeManagePayQueryReqDto.setSheetId(id);//来源单ID
				financeManagePayQueryReqDto.setSheetType(CalculationFeeEnum.PCD.getType());
				FinanceManagePayEsDTO financeManagePayEsDto = esFinanceManagePayService.getEsBySheetIdFeeType(financeManagePayQueryReqDto);
				if (financeManagePayEsDto != null) {
					financeManagePayDo = new FinanceManagePayDO();
					financeManagePayDo.setSheetId(financeManagePayEsDto.getSheetId());
					financeManagePayDo.setCompanyId(financeManagePayEsDto.getCompanyId());
					financeManagePayDo.setFeeType(financeManagePayEsDto.getFeeType());
					financeManagePayDo.setSheetType(CalculationFeeEnum.PCD.getType());
					financeManagePayService.deletePayInfo(financeManagePayDo, financeManagePayQueryReqDto);
				}else {
					logger.error("删除的数据不存在，ID：" + id);
				}
			}
		}
	}
	
	private void updateChangeArriveGoods(List<Long> ids) {
		deleteChangeArriveGoods(ids);
		initChangeArriveGoodsInsert(ids);
		
	}

	private void updateChangeDispatch(List<Long> ids) {
		deleteChangeDispatch(ids);
		initChangeDispatchInsert(ids);
		
	}
}
