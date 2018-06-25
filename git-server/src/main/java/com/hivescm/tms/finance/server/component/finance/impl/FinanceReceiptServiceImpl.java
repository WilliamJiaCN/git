package com.hivescm.tms.finance.server.component.finance.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchUpdateExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptConfirmDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptCreateDTO;
import com.hivescm.tms.api.dto.es.finance.component.FinanceReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.component.SettleOrgDTO;
import com.hivescm.tms.api.dto.es.order.redundancy.WriteMqInfoDTO;
import com.hivescm.tms.api.dto.es.sign.OrderPaymentInfoESDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;
import com.hivescm.tms.api.enums.biz.finance.ConfirmStatusEnum;
import com.hivescm.tms.api.enums.biz.finance.SettlementModeEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceCommonService;
import com.hivescm.tms.finance.server.component.finance.FinancePaymentService;
import com.hivescm.tms.finance.server.component.finance.FinanceReceiptService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceReceipt;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceReceiptMapper;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.finance.FinanceService;
import com.hivescm.tms.finance.server.service.finance.constant.FinanceConstant;
import com.hivescm.tms.finance.server.service.sign.EsSignPaymentService;
import com.hivescm.tms.intranet.gateway.api.dto.boss.BossIBankAccountInfoDto;
import com.hivescm.tms.intranet.gateway.api.dto.boss.IdDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.InteractObjectEnum;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.PayStatus;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.Status;
import com.hivescm.tms.intranet.gateway.api.dto.boss.receipt.IReceiptInfoDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.receipt.ReceiptInfoDetailDTO;
import com.hivescm.tms.intranet.gateway.api.dto.crm.PaymentCategoryDTO;
import com.hivescm.tms.intranet.gateway.api.dto.crm.SettlementModeDO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IWriteMqInfoService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossFinanceEntrusRelationService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossFinanceReceiptService;
import com.hivescm.tms.intranet.gateway.api.feign.crm.CrmBaseService;
import com.hivescm.tms.utils.JsonUtil;
import com.mogujie.distributed.transction.DynamicTransctionManagerFactory;
import com.mogujie.trade.utils.TransactionManagerUtils;

/**
 * 财务收款单
 *
 * @author 杨彭伟
 * @date 2017-11-21 15:58
 */
@Service
public class FinanceReceiptServiceImpl implements FinanceReceiptService {
	private Logger logger = LoggerFactory.getLogger(FinanceReceiptServiceImpl.class);

	@Autowired
	private ESSearchService esSearchService;
	@Autowired
	private DynamicTransctionManagerFactory dtmFactory;
	@Autowired
	private IGeneratedIdService generatedIdService;
	@Autowired
	private FinanceReceiptMapper financeReceiptMapper;

	@Autowired
	EsSignPaymentService signPaymentService;
	@Autowired
	BossFinanceEntrusRelationService bossFinanceEntrusRelationService;
	@Autowired
	FinanceCommonService financeCommonService;
	@Autowired
	private BossFinanceReceiptService bossFinanceReceiptService;
	@Autowired
	private FinancePaymentService financePaymentService;
	@Autowired
	private IWriteMqInfoService writeMqInfoService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private CrmBaseService crmBaseService;
    @Autowired
    private FinanceService financeService;

	@Override
	public Long saveReceipt(FinanceReceiptCreateDTO financeReceiptCreateDTO) {
		logger.debug("保存收款单参数：{}", financeReceiptCreateDTO);
		Integer settlementMode = financeReceiptCreateDTO.getSettlementMode();
		if (settlementMode != SettlementModeEnum.CASH.getType()
				&& settlementMode != SettlementModeEnum.QR_CODE.getType()) {
			throw new SystemException(ExceptionCodeConstants.ERROR_PARAM_SETTLEMENT_MODE, "结算方式参数错误");
		}
		// todo 获取运单信息
		TmsWaybillEsDTO tmsWaybillEsDTO = getTmsWaybillEsDTO(financeReceiptCreateDTO.getWaybillId());
		if (tmsWaybillEsDTO == null) {
			throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "运单不存在");
		}
		// TODO 获取签收信息
		SignEsDTO signEsTTO = financeReceiptCreateDTO.getSignEsDTO();
		if (signEsTTO == null) {
			throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "签收单不存在");
		}
		// 如果扫码判断订单支付信息
		OrderPaymentInfoESDTO signPayment = signPaymentService.getSignPayment(financeReceiptCreateDTO.getWaybillId());
		if (signPayment == null && settlementMode == SettlementModeEnum.QR_CODE.getType()) {
			throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "订单支付信息不存在");
		}
		if (signPayment != null) {
			financeReceiptCreateDTO.setPayChannel(signPayment.getPaymentMode());
		}
		TransactionManagerUtils.TransactionProxy transactionProxy = null;
		WriteMqInfoDTO record = new WriteMqInfoDTO();
		try {
			// todo 提交数据到boss
			logger.error("调用Boss接口生成收款单的参数：{}", financeReceiptCreateDTO);
			String receiptCode = bossFinanceReceiptService.getCode();
			// todo 构建RPC数据
			IReceiptInfoDTO dto = buildRPCData(financeReceiptCreateDTO, tmsWaybillEsDTO, signEsTTO, receiptCode);
			logger.error("dto", dto);

			IReceiptInfoDTO receiptInfoResponseDTO = bossFinanceReceiptService.createReceipt(dto);
			logger.error("receiptInfoResponseDTO", receiptInfoResponseDTO);

			FinanceReceiptEsDTO financeReceiptEsDTO = buildESData(financeReceiptCreateDTO, tmsWaybillEsDTO, signEsTTO);
			logger.error("financeReceiptEsDTO", financeReceiptEsDTO);
			// 扫描收款返回，不需要保存
			if (financeReceiptEsDTO == null) {
				return 0L;
			}
			financeReceiptEsDTO.setRiId(receiptInfoResponseDTO.getRiId());
			financeReceiptEsDTO.setReceiptCode(receiptInfoResponseDTO.getReceiptCode());
			FinanceReceipt financeReceipt = EntityUtils.copyProperties(financeReceiptEsDTO, FinanceReceipt.class);
			logger.error("financeReceipt", financeReceipt);
			// todo 保存到数据库、es
			transactionProxy = this.getTransaction(financeReceiptCreateDTO.getCompanyId());

			Long uniqueId = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_RECEIPT);
			financeReceipt.setId(uniqueId);
			financeReceiptEsDTO.setId(uniqueId);
			int i = financeReceiptMapper.insertSelective(financeReceipt);
			if (i != 1) {
				throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "DB 收款记录保存失败");
			}
			logger.error("financeReceipt-db-success", financeReceipt);
			Boolean flag = new DefaultAbstractSearchSaveExecutor<FinanceReceiptEsDTO>(esSearchService) {
				@Override
				public EsConfig getConfig() {
					return EsConfig.finance();
				}
			}.execute(financeReceiptEsDTO);
			logger.error("financeReceipt-es-success", financeReceipt);
			if (!flag) {
				// ES 保存成功提交事务
				throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "ES 收款记录保存失败");
			}
			transactionProxy.commit();
			return uniqueId;
		} catch (SystemException se) {
			if (transactionProxy != null) {
				transactionProxy.rollback();
			}
			record.setOrderType("saveReceipt");
			record.setQueueName("saveReceipt");
			record.setMainInfo(JsonUtil.GsonString(financeReceiptCreateDTO));
			record.setErrorReason(JsonUtil.GsonString(se.getErrorReason()));
			writeMqInfoService.insert(record);
			throw new SystemException(se.getErrorCode(), se.getErrorReason());
		} catch (Exception e) {
			if (transactionProxy != null) {
				transactionProxy.rollback();
			}
			record.setOrderType("saveReceipt");
			record.setQueueName("saveReceipt");
			record.setMainInfo(JsonUtil.GsonString(financeReceiptCreateDTO));
			record.setErrorReason(JsonUtil.GsonString(e.getLocalizedMessage()));
			writeMqInfoService.insert(record);
			logger.error(e.getLocalizedMessage(), e);
			throw new TmsBusinessException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "保存收款单失败", e);
		}
	}

	@Override
	public Boolean updateReceiptToConfirm(FinanceReceiptConfirmDTO financeReceiptConfirmDTO) {
		List<Long> idList = financeReceiptConfirmDTO.getId();
		TransactionManagerUtils.TransactionProxy transactionProxy = this
				.getTransaction(financeReceiptConfirmDTO.getCompanyId());
		try {
			// TODO 调用BOSS已审核接口
			IReceiptInfoDTO receiptInfoDTO = getReceiptInfoDTO(idList);
			DataResult<Boolean> dataResult = bossFinanceReceiptService.auditReceipt(receiptInfoDTO);
			// TODO 调用成功，修改 DB ES
			if (null != dataResult.getResult()) {
				if (dataResult.getResult()) { // 修改boss收款单成功
					FinanceReceipt record = new FinanceReceipt();
					record.setConfirmReceiptTime(System.currentTimeMillis());
					record.setConfirmReceipt(ConfirmStatusEnum.CONFIRMED.getType());
					record.setConfirmReceiptUser(financeReceiptConfirmDTO.getOperatingUser());
					record.setReceivablesBankAccount(financeReceiptConfirmDTO.getReceiptBankAccount());
					record.setStatus(Status.APPROVED.code());
					record.setCompanyId(financeReceiptConfirmDTO.getCompanyId());
					record.setUpdateTime(System.currentTimeMillis());
					record.setUpdateUser(financeReceiptConfirmDTO.getOperatingUserId());

					FinanceReceiptEsDTO financeReceiptEsDTO = new FinanceReceiptEsDTO();
					EntityUtils.copyProperties(record, financeReceiptEsDTO);

					int i = financeReceiptMapper.updateReceiptConfirm(record, idList);
					if (i <= 0) {
						throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "收款单 DB 修改失败");
					}
					List<SearchCondition> searchConditions = SearchConditionUtils.start()
							.addCondition("id", idList.toArray(), ConditionExpressionEnum.IN).end();
					Boolean flag = new DefaultAbstractSearchUpdateExecutor<FinanceReceiptEsDTO>(esSearchService) {
						@Override
						public EsConfig getConfig() {
							return EsConfig.finance();
						}
					}.execute(financeReceiptEsDTO, searchConditions);
					if (!flag) {
						throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "收款单 ES 修改失败");
					}
					transactionProxy.commit();

					List<Long> longList = financePaymentService.saveFinancePayment(financeReceiptConfirmDTO);
				}
			} else {
				com.hivescm.common.domain.Status status = dataResult.getStatus();
				throw new SystemException(ExceptionCodeConstants.ERROR_DEPENDENCY,
						status == null ? "修改外部签收单异常" : status.getStatusReason());
			}
			return true;
		} catch (SystemException e) {
			transactionProxy.rollback();
			WriteMqInfoDTO record = new WriteMqInfoDTO();
			record.setOrderType("updateReceiptToConfirm");
			record.setQueueName("updateReceiptToConfirm");
			record.setMainInfo(JsonUtil.GsonString(financeReceiptConfirmDTO));
			record.setErrorReason(JsonUtil.GsonString(e.getLocalizedMessage()));
			writeMqInfoService.insert(record);
			throw e;
		} catch (Exception e) {
			transactionProxy.rollback();
			e.printStackTrace();
			logger.error(e.getLocalizedMessage(), e);
			WriteMqInfoDTO record = new WriteMqInfoDTO();
			record.setOrderType("updateReceiptToConfirm");
			record.setQueueName("updateReceiptToConfirm");
			record.setMainInfo(JsonUtil.GsonString(financeReceiptConfirmDTO));
			record.setErrorReason(JsonUtil.GsonString(e.getLocalizedMessage()));
			writeMqInfoService.insert(record);
			throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
		}
	}
	
	@Override
	public List<Long> resolvePaymentData(Long receiptId){
		try{
			FinanceReceiptEsDTO receiptEs = financeService.getByID(receiptId);
			if(null==receiptEs){
				throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "收银记录为空");
			}
			FinanceReceiptConfirmDTO financeReceiptConfirmDTO = new FinanceReceiptConfirmDTO();
			List<Long> receiptIdList = new ArrayList<Long>(1);
			receiptIdList.add(receiptEs.getId());
			financeReceiptConfirmDTO.setId(receiptIdList);
			financeReceiptConfirmDTO.setCompanyId(receiptEs.getCompanyId());
			financeReceiptConfirmDTO.setOperatingUser(receiptEs.getConfirmReceiptUser());
			financeReceiptConfirmDTO.setOperatingUserId(receiptEs.getUpdateUser());
			financeReceiptConfirmDTO.setReceiptBankAccount(receiptEs.getReceivablesBankAccount());
			List<Long> longList = financePaymentService.saveFinancePayment(financeReceiptConfirmDTO);
			return longList;
		}catch(Exception e){
			logger.error("修复财务数据失败",e);
			throw e;
		}
	}
	private IReceiptInfoDTO getReceiptInfoDTO(List<Long> idList) {
		IReceiptInfoDTO receiptInfoDTO = new IReceiptInfoDTO();
		if (idList == null || idList.size() == 0) {
			throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "参数错误，待审核的收款单id为空");
		}
		List<FinanceReceiptEsDTO> receiptEsDTOList = getFinanceReceiptEsDTOS(idList);
		List<IdDTO> idDTOS = new ArrayList<>();
		receiptEsDTOList.forEach(receiptEsDTO -> {
			IdDTO idDTO = new IdDTO();
			if (receiptEsDTO != null) {
				idDTO.setId(receiptEsDTO.getRiId());
				idDTOS.add(idDTO);
			}
		});
		receiptInfoDTO.setRiIdList(idDTOS);
		return receiptInfoDTO;
	}

	private List<FinanceReceiptEsDTO> getFinanceReceiptEsDTOS(List<Long> idList) {
		List<SearchCondition> conditionList = new ArrayList<>();
		conditionList.add(SearchConditionUtils.newInCondition("id", idList.toArray()));
		return new DefaultAbstractSearchQueryExecutor<FinanceReceiptEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.finance();
			}
		}.list(conditionList);
	}

	@Override
	public List<BossIBankAccountInfoDto> getAccountList(String orgId) {
		SettleOrgDTO settleOrg = financeCommonService.getSettleOrg(Integer.valueOf(orgId), 1);
		if (settleOrg == null) {
			return new ArrayList<>();
		}
		List<String> ids = new ArrayList<>();
		ids.add(settleOrg.getSettleOrgId().toString());
		return bossFinanceEntrusRelationService.queryOrgAccount(ids);
	}

	private String getAccount(String dealerId) {
		List<BossIBankAccountInfoDto> list = getAccountList(dealerId);
		if (CollectionUtils.isNotEmpty(list)) {
			BossIBankAccountInfoDto bankAccountInfoDto = list.get(0);
			return bankAccountInfoDto.getAccountSn(); // 报错，先return null
		} else {
			return null;
		}
	}

	/**
	 * 构建boss提交数据
	 * 
	 * @param financeReceiptCreateDTO
	 * @param tmsWaybillEsDTO
	 * @return
	 */
	private IReceiptInfoDTO buildRPCData(FinanceReceiptCreateDTO financeReceiptCreateDTO,
			TmsWaybillEsDTO tmsWaybillEsDTO, SignEsDTO signEsDTO, String receiptCode) {
		// 收款单基本
		IReceiptInfoDTO dto = new IReceiptInfoDTO();
		// 收款明细
		List<ReceiptInfoDetailDTO> receiptInfoDetailDOList = new ArrayList<>();
		ReceiptInfoDetailDTO detail = new ReceiptInfoDetailDTO();
		// TODO 现金收款
		if (financeReceiptCreateDTO.getSettlementMode() == SettlementModeEnum.CASH.getType()) {
			dto.setStatus(Status.SUBMITTED);
			SettlementModeDO settlementModeDO = getSettlementModeByCode(FinanceConstant.SETTLE_MODE_CASH_CODE);
			if (settlementModeDO != null) {
				detail.setSettlementMode(settlementModeDO.getId());
				detail.setSettlementModeDisplay(settlementModeDO.getSettlementModeName());
			}

			// TODO 业务组织、业务方，应收组织、应收方，收款组织、付款方
			// TODO 业务组织 => 承运商网点
			Integer invoiceOrgId = tmsWaybillEsDTO.getInvoiceOrgId() == null ? null
					: tmsWaybillEsDTO.getInvoiceOrgId().intValue();
			String invoiceOrgIdString = tmsWaybillEsDTO.getInvoiceOrgId() == null ? null
					: tmsWaybillEsDTO.getInvoiceOrgId().toString();
			String invoiceOrgName = tmsWaybillEsDTO.getInvoiceOrgName();
			detail.setBusinessOrganization(invoiceOrgIdString);
			detail.setBusinessOrganizationDisplay(invoiceOrgName);
			// TODO 设置应收组织和收款组织
			setReceivableCarrierRPC(dto, detail, invoiceOrgId);
			// TODO 收款账户、付款账户 留空
		}
		// TODO 扫码收款
		else if (financeReceiptCreateDTO.getSettlementMode() == SettlementModeEnum.QR_CODE.getType()) {
			dto.setStatus(Status.APPROVED);
			SettlementModeDO settlementModeDO = getSettlementModeByCode(FinanceConstant.SETTLE_MODE_QR_CODE_CODE);
			if (settlementModeDO != null) {
				detail.setSettlementMode(settlementModeDO.getId());
				detail.setSettlementModeDisplay(settlementModeDO.getSettlementModeName());
			}
			// TODO 业务组织、业务方，应收组织、应收方，收款组织、付款方
			// 业务组织 => 经销商网点
			Integer dealerId = tmsWaybillEsDTO.getDealerId() == null ? null : tmsWaybillEsDTO.getDealerId().intValue();
			String dealerIdString = tmsWaybillEsDTO.getDealerId() == null ? null
					: tmsWaybillEsDTO.getDealerId().toString();
			String dealerName = tmsWaybillEsDTO.getDealerName();
			detail.setBusinessOrganization(dealerIdString);
			detail.setBusinessOrganizationDisplay(dealerName);
			// TODO 设置应收组织和收款组织
			setReceivableRPC(dto, detail, dealerId);

			// TODO 收款账户 -> 经销商网点的账户
			String receiptAccount = getAccount(dealerIdString);
			detail.setReceiveAccount(0);
			detail.setReceiveAccountDisplay(receiptAccount);
			// TODO 付款账户 留空
		}

		// TODO 业务方 => 商户集团客户名称
		Integer merchandGroupId = tmsWaybillEsDTO.getMerchandGroupId() == null ? null
				: tmsWaybillEsDTO.getMerchandGroupId().intValue();
		String merchandGroupName = tmsWaybillEsDTO.getMerchandGroupName();
		detail.setBusinessCustomer(merchandGroupId);
		detail.setBusinessCustomerDisplay(merchandGroupName);
		// TODO 应付方 -> 业务方
		dto.setPayObject(merchandGroupId);
		dto.setPayObjectDisplay(merchandGroupName);
		// TODO 付款方 -> 业务方
		detail.setReceivableCustomer(merchandGroupId);
		detail.setReceivableCustomerDisplay(merchandGroupName);

		dto.setReceiveAmount(signEsDTO.getTotalReceivable());
		dto.setReceiptType(FinanceConstant.RECEIPT_BILL_TYPE);
		dto.setReceiptCode(receiptCode);
		dto.setSourceBillType(FinanceConstant.RECEIPT_SOURCE_BILL_TYPE);
		dto.setSourceBillTypeDisplay(FinanceConstant.RECEIPT_SOURCE_BILL_TYPE_NAME);
		dto.setBillDate(signEsDTO.getSignTime());
		// 往来对象类型
		dto.setCurrentObjectType(InteractObjectEnum.CUSTOMNER);
		dto.setCurrentObjectTypeDisplay(InteractObjectEnum.CUSTOMNER.getDesc());
		if (financeReceiptCreateDTO.getUserId() != null) {
			dto.setCreateUser(Long.valueOf(financeReceiptCreateDTO.getUserId()));
			dto.setUpdateUser(Long.valueOf(financeReceiptCreateDTO.getUserId()));
		} else {
			dto.setCreateUser(1L);
			dto.setUpdateUser(1L);
		}
		dto.setCreateTime(System.currentTimeMillis());
		dto.setUpdateTime(System.currentTimeMillis());
		dto.setIsByPush(true);
		dto.setCurrency(FinanceConstant.CHINA_CURRENCY);

		detail.setReceivablesType(getReceivablesTypeId());
		detail.setReceivablesProperty(FinanceConstant.RECEIVABLES_PROPERTY_RECEIVABLE);
		detail.setPayStatus(PayStatus.PAYSUCCESS);
		detail.setReceivableMoney(tmsWaybillEsDTO.getOrderGoodsPayment());
		detail.setPaymentMode(FinanceConstant.COD_PAYMENT_MODE);
		detail.setSourceDocId(signEsDTO.getId());
		detail.setSourceNumber(signEsDTO.getSignBatchNumber());
		detail.setSourceDocAmount(signEsDTO.getTotalReceivable());
		String orderCode = tmsWaybillEsDTO.getOrderCode();
		Long orderId = null;
		try {
			// TODO oms orderCode 就是订单的id，此处强转
			orderId = Long.valueOf(orderCode);
		} catch (NumberFormatException e) {
			logger.error("订单id类型转换失败：orderCode = {}", orderCode);
			orderId = 0L;
		}
		detail.setOriginalDocId(orderId);
		detail.setOriginalBillNumber(orderCode);
		detail.setOriginalBillType(FinanceConstant.ORIGINAL_BILL_TYPE);
		detail.setOriginalBillTypeDisplay(FinanceConstant.ORIGINAL_BILL_TYPE_NAME);
		receiptInfoDetailDOList.add(detail);
		dto.setReceiptInfoDetailDTOList(receiptInfoDetailDOList);
		return dto;
	}

	/**
	 * 获取收款类型id
	 * 
	 * @return
	 */
	private Integer getReceivablesTypeId() {
		PaymentCategoryDTO paymentCategory = crmBaseService.getPaymentCategoryByCode("SFLX10");
		if (paymentCategory != null) {
			return paymentCategory.getId();
		} else {
			return 0;
		}
	}

	private SettlementModeDO getSettlementModeByCode(String code) {
		return crmBaseService.getSettlementModeByCode(code);
	}

	/**
	 * 设置应收组织和收款组织
	 * 
	 * @param carrierId
	 *            承运商id
	 */
	private void setReceivableCarrierRPC(IReceiptInfoDTO dto, ReceiptInfoDetailDTO detail, Integer carrierId) {
		SettleOrgDTO settleOrg = financeCommonService.getSettleOrg(carrierId, 1);
		setSettleOrg(dto, detail, settleOrg);
	}

	/**
	 * 设置应收组织和收款组织 RPC
	 * 
	 * @param dealerId
	 *            经销商id
	 */
	private void setReceivableRPC(IReceiptInfoDTO dto, ReceiptInfoDetailDTO detail, Integer dealerId) {
		SettleOrgDTO settleOrg = financeCommonService.getSettleOrg(dealerId, 2);
		setSettleOrg(dto, detail, settleOrg);
	}

	private void setSettleOrg(IReceiptInfoDTO dto, ReceiptInfoDetailDTO detail, SettleOrgDTO settleOrg) {
		if (settleOrg != null) {
			Integer settleOrgId = settleOrg.getSettleOrgId();
			String settleOrgName = settleOrg.getSettleOrgName();

			detail.setReceivableOrganization(settleOrgId);
			detail.setReceivableOrganizationDisplay(settleOrgName);

			dto.setReceivablesOrganization(settleOrgId);
			dto.setReceivablesOrganizationDisplay(settleOrgName);
		}
	}

	/**
	 * 构建ES保存数据
	 * 
	 * @param financeReceiptCreateDTO
	 *            收款单生成基础数据
	 * @param tmsWaybillEsDTO
	 *            运单ES数据
	 * @return
	 */
	private FinanceReceiptEsDTO buildESData(FinanceReceiptCreateDTO financeReceiptCreateDTO,
			TmsWaybillEsDTO tmsWaybillEsDTO, SignEsDTO signEsDTO) {
		FinanceReceiptEsDTO dto = new FinanceReceiptEsDTO();
		EntityUtils.copyProperties(tmsWaybillEsDTO, dto);
		dto.setCreateTime(signEsDTO.getCreateTime());
		// TODO 现金收款
		if (financeReceiptCreateDTO.getSettlementMode().equals(SettlementModeEnum.CASH.getType())) {
			dto.setStatus(Status.SUBMITTED.code());
			dto.setConfirmReceipt(ConfirmStatusEnum.UNCONFIRM.getType());
			dto.setSettlementMode(SettlementModeEnum.CASH.getType());
			// TODO 业务组织、业务方，应收组织、应收方，收款组织、付款方
			// TODO 业务组织 => 承运商网点
			Integer invoiceOrgId = tmsWaybillEsDTO.getInvoiceOrgId() == null ? null
					: tmsWaybillEsDTO.getInvoiceOrgId().intValue();
			String invoiceOrgIdString = tmsWaybillEsDTO.getInvoiceOrgId() == null ? null
					: tmsWaybillEsDTO.getInvoiceOrgId().toString();
			String invoiceOrgName = tmsWaybillEsDTO.getInvoiceOrgName();
			dto.setBusinessOrganization(invoiceOrgIdString);
			dto.setBusinessOrganizationDisplay(invoiceOrgName);

			// TODO 设置应收组织和收款组织
			setReceivablesCarrierForES(dto, invoiceOrgId);
			// 收款账户、付款账户都空
		}
		// TODO 扫码收款
		else if (financeReceiptCreateDTO.getSettlementMode().equals(SettlementModeEnum.QR_CODE.getType())) {
			return null;
			// dto.setStatus(Status.APPROVED.code());
			// dto.setConfirmReceipt(ConfirmStatusEnum.CONFIRMED.getType());
			// dto.setSettlementMode(SettlementModeEnum.QR_CODE.getType());
			// // TODO 业务组织、业务方，应收组织、应收方，收款组织、付款方
			// // 业务组织 => 经销商网点
			// Integer dealerId = tmsWaybillEsDTO.getDealerId() == null ? null :
			// tmsWaybillEsDTO.getDealerId().intValue();
			// String dealerIdString = tmsWaybillEsDTO.getDealerId() == null ?
			// null : tmsWaybillEsDTO.getDealerId().toString();
			// String dealerName = tmsWaybillEsDTO.getDealerName();
			// dto.setBusinessOrganization(dealerIdString);
			// dto.setBusinessOrganizationDisplay(dealerName);
			//
			// // TODO 设置应收组织和收款组织
			// setReceivablesDealerForES(dto, dealerId);
			//
			// // 收款账户 -> 经销商网点的账户
			// String receiptAccount = getAccount(dealerIdString);
			// dto.setReceiveAccount(0);
			// dto.setReceiveAccountDisplay(receiptAccount);
			// dto.setPaymentMode("微信");
		}
		// TODO 业务方 => 商户集团客户名称
		Integer merchandGroupId = tmsWaybillEsDTO.getMerchandGroupId() == null ? null
				: tmsWaybillEsDTO.getMerchandGroupId().intValue();
		String merchandGroupName = tmsWaybillEsDTO.getMerchandGroupName();
		dto.setBusinessCustomer(merchandGroupId);
		dto.setBusinessCustomerDisplay(merchandGroupName);
		// TODO 付款方 -> 业务方
		dto.setPayObject(merchandGroupName);
		// TODO 应付方 -> 业务方
		dto.setPayableObject(merchandGroupName);
		dto.setSourceNumber(signEsDTO.getSignBatchNumber());
		dto.setSourceBillType("签收单");
		dto.setOriginalBillNumber(tmsWaybillEsDTO.getOrderCode());
		dto.setOriginalBillType("销售订单");

		dto.setCompanyId(financeReceiptCreateDTO.getCompanyId());
		dto.setCurrency(FinanceConstant.CHINA_CURRENCY);
		dto.setPaymentMode(FinanceConstant.COD_PAYMENT_MODE_NAME);
		dto.setReceivablesProperty(FinanceConstant.RECEIVABLES_PROPERTY_OTHER_RECEIVABLE_NAME);
		dto.setReceivablesType(FinanceConstant.RECEIVABLES_TYPE);
		// TODO 运单相关赋值
		dto.setWaybillId(tmsWaybillEsDTO.getId());
		dto.setCode(tmsWaybillEsDTO.getCode());
		dto.setVehicleNo(tmsWaybillEsDTO.getVehicleNo());
		dto.setCollectPayment(tmsWaybillEsDTO.getOrderGoodsPayment());
		dto.setWaybillCreateTime(tmsWaybillEsDTO.getCreateTime());
		// TODO 签收单赋值
		dto.setBillDate(signEsDTO.getSignTime());
		dto.setSignStatus(signEsDTO.getSignStatus());
		dto.setSignTime(signEsDTO.getSignTime());
		dto.setCollectionTime(signEsDTO.getSignTime());
		dto.setTotalReceivable(signEsDTO.getTotalReceivable());
		dto.setReceivableMoney(signEsDTO.getTotalReceivable());
		return dto;
	}

	/**
	 * 设置应收组织和收款组织 ES
	 * 
	 * @param dto
	 * @param dealerId
	 *            经销商id
	 */
	private void setReceivablesDealerForES(FinanceReceiptEsDTO dto, Integer dealerId) {
		SettleOrgDTO settleOrgDTO = financeCommonService.getSettleOrg(dealerId, 2);
		setSettleOrg(dto, settleOrgDTO);
	}

	private void setReceivablesCarrierForES(FinanceReceiptEsDTO dto, Integer carrierId) {
		SettleOrgDTO settleOrgDTO = financeCommonService.getSettleOrg(carrierId, 1);
		setSettleOrg(dto, settleOrgDTO);
	}

	private void setSettleOrg(FinanceReceiptEsDTO dto, SettleOrgDTO settleOrgDTO) {
		if (settleOrgDTO != null) {
			Integer settleOrgId = settleOrgDTO.getSettleOrgId();
			String settleOrgName = settleOrgDTO.getSettleOrgName();
			dto.setReceivableOrganization(settleOrgId);
			dto.setReceivableOrganizationDisplay(settleOrgName);

			dto.setReceivablesOrganization(String.valueOf(settleOrgId));
			dto.setReceivablesOrganizationDisplay(settleOrgName);
		}
	}

	/**
	 * 获取运单信息
	 *
	 * @param waybillId
	 * @return
	 */
	private TmsWaybillEsDTO getTmsWaybillEsDTO(Long waybillId) {
		WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(waybillId);
		return EntityUtils.copyProperties(waybillEsDTO, TmsWaybillEsDTO.class);
	}

	private TransactionManagerUtils.TransactionProxy getTransaction(Long companyId) {
		return dtmFactory.create().addTransManager(FinanceReceiptMapper.class, companyId).build();
	}
}
