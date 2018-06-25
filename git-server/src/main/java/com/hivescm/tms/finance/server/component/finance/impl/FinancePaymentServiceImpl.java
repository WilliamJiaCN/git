package com.hivescm.tms.finance.server.component.finance.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchUpdateExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptConfirmDTO;
import com.hivescm.tms.api.dto.es.finance.PaymentGrantDTO;
import com.hivescm.tms.api.dto.es.finance.component.FinancePaymentESDTO;
import com.hivescm.tms.api.dto.es.finance.component.FinanceReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.component.SettleOrgDTO;
import com.hivescm.tms.api.dto.es.finance.request.PaymentAccountReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.PaymentAccountListRespDTO;
import com.hivescm.tms.api.dto.es.finance.response.PaymentAccountRespTDO;
import com.hivescm.tms.api.dto.es.order.redundancy.WriteMqInfoDTO;
import com.hivescm.tms.api.enums.biz.finance.GrantStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceCommonService;
import com.hivescm.tms.finance.server.component.finance.FinancePaymentService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.dao.entity.finance.FinancePayment;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinancePaymentMapper;
import com.hivescm.tms.finance.server.service.finance.constant.FinanceConstant;
import com.hivescm.tms.intranet.gateway.api.dto.boss.BossIBankAccountInfoDto;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.InteractObjectEnum;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.PayStatus;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.ReconciliationStatus;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.Status;
import com.hivescm.tms.intranet.gateway.api.dto.boss.pay.IPayInfoDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.pay.PayInfoDetailDTO;
import com.hivescm.tms.intranet.gateway.api.dto.oms.IOrderPayerStatusOMSDTO;
import com.hivescm.tms.intranet.gateway.api.dto.pay.IBankAccountInfoDto;
import com.hivescm.tms.intranet.gateway.api.dto.pay.IBankAccountQueryDto;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IWriteMqInfoService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossFinanceEntrusRelationService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossFinancePayService;
import com.hivescm.tms.intranet.gateway.api.feign.oms.IOmsService;
import com.hivescm.tms.intranet.gateway.api.feign.pay.IPayService;
import com.hivescm.tms.utils.JsonUtil;
import com.mogujie.distributed.transction.DynamicTransctionManagerFactory;
import com.mogujie.trade.utils.TransactionManagerUtils;


/**
 * 付款记录
 *
 * @author 杨彭伟
 * @date 2017-11-23 19:57
 */
@Service
public class FinancePaymentServiceImpl implements FinancePaymentService {
    @Autowired
    BossFinanceEntrusRelationService bossFinanceEntrusRelationService;
    private Logger logger = LoggerFactory.getLogger(FinancePaymentServiceImpl.class);
    @Autowired
    private ESSearchService esSearchService;
    @Autowired
    private DynamicTransctionManagerFactory dtmFactory;
    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private FinancePaymentMapper financePaymentMapper;
    @Autowired
    private FinanceCommonService financeCommonService;
    @Autowired
    private BossFinancePayService bossFinancePayService;
    @Autowired
    private IWriteMqInfoService writeMqInfoService;
    @Autowired
    private IOmsService omsService;
    @Autowired
    private IPayService payService;
    
    private ExecutorService executorService = Executors.newFixedThreadPool(6);

    @Override
    public List<Long> saveFinancePayment(FinanceReceiptConfirmDTO financeReceiptConfirmDTO) {
        logger.debug("saveFinancePayment param：{}", financeReceiptConfirmDTO);
        // TODO 获取收款记录
        List<FinanceReceiptEsDTO> financeReceiptEsList = getFinanceReceiptEsList(financeReceiptConfirmDTO.getId());
        if (financeReceiptEsList == null || financeReceiptEsList.size() == 0) {
            throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "收款记录不可为空");
        }
        int size = financeReceiptConfirmDTO.getId().size();
        List<Long> ids = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ids.add(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_PAYMENT));
        }

        List<FinancePaymentESDTO> financePaymentESDTOList = buildPaymentESList(financeReceiptEsList, financeReceiptConfirmDTO, ids);
        List<FinancePayment> financePaymentList = buildPaymentDBList(financePaymentESDTOList);
        TransactionManagerUtils.TransactionProxy transactionProxy = getTransaction(financeReceiptConfirmDTO.getCompanyId());
        try {
            int line = financePaymentMapper.insertBatch(financePaymentList.get(0).getCompanyId(),financePaymentList);
            if (line < financePaymentList.size()) {
                throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "DB 收款记录保存失败");
            }

            boolean flag = new DefaultAbstractSearchSaveExecutor<FinancePaymentESDTO>(esSearchService) {
                @Override
                public EsConfig getConfig() {
                    return EsConfig.payment();
                }
            }.execute(financePaymentESDTOList);
            if (!flag) {
                throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "ES 收款记录保存失败");
            }

            transactionProxy.commit();
            return ids;
        } catch (SystemException se) {
            transactionProxy.rollback();
            logger.error("生成收款记录失败：", se);
            WriteMqInfoDTO record = new WriteMqInfoDTO();
            record.setOrderType("saveFinancePayment");
            record.setQueueName("saveFinancePayment");
            record.setMainInfo(JsonUtil.GsonString(financeReceiptConfirmDTO));
            record.setErrorReason(JsonUtil.GsonString(se));
            writeMqInfoService.insert(record);
            throw new SystemException(se.getErrorCode(), se.getErrorReason());
        } catch (Exception e) {
            transactionProxy.rollback();
            logger.error("生成收款记录失败：", e);
            WriteMqInfoDTO record = new WriteMqInfoDTO();
            record.setOrderType("saveFinancePayment");
            record.setQueueName("saveFinancePayment");
            record.setMainInfo(JsonUtil.GsonString(financeReceiptConfirmDTO));
            record.setErrorReason(JsonUtil.GsonString(e));
            writeMqInfoService.insert(record);
            throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
        }
    }

//    @Async
//    @Override
//    public Future<List<Long>> savePayment(FinanceReceiptConfirmDTO financeReceiptConfirmDTO) {
//        List<Long> longs = saveFinancePayment(financeReceiptConfirmDTO);
//        return new AsyncResult<>(longs);
//    }

    @Override
    public Boolean updatePaymentForGrant(PaymentGrantDTO paymentGrantDTO) {
        List<Long> ids = paymentGrantDTO.getIds();

        if (ids == null) {
            throw new SystemException(-1, "待付款记录id不可为空");
        }
        List<FinancePaymentESDTO> financePaymentESDTOList = new ArrayList<>(ids.size());
        List<FinancePayment> financePaymentList = new ArrayList<>(ids.size());
        TransactionManagerUtils.TransactionProxy transactionProxy = getTransaction(paymentGrantDTO.getCompanyId());
        try {
        	 // TODO send to boss
            String payCode = bossFinancePayService.getCode();
            logger.error("---Grant----" + paymentGrantDTO.toString());
            logger.error("---Grant----" + payCode);
            IPayInfoDTO payInfoDTO = getFinancePaymentRPC(paymentGrantDTO, payCode);
            logger.error("---Grant----" + payInfoDTO.toString());
            IPayInfoDTO pay = bossFinancePayService.createPay(payInfoDTO);
            logger.error("---pay----" + pay.toString());
        ids.forEach(id -> {
            FinancePaymentESDTO financePaymentESDTO = getFinancePaymentESDTO(paymentGrantDTO, pay, id);
            FinancePayment financePayment = EntityUtils.copyProperties(financePaymentESDTO, FinancePayment.class);
            financePaymentESDTOList.add(financePaymentESDTO);
            financePaymentList.add(financePayment);
        });

            int line = 0;
            for (FinancePayment payment : financePaymentList) {
                line += financePaymentMapper.updateByPrimaryKeySelective(payment);
            }
//            if (line != financePaymentList.size()) {
//                throw new SystemException(-1, " DB 更新收款记录失败");
//            }

            Boolean flag = new DefaultAbstractSearchUpdateExecutor<FinancePaymentESDTO>(esSearchService) {
                @Override
                public EsConfig getConfig() {
                    return EsConfig.payment();
                }
            }.execute(financePaymentESDTOList);
            if (!flag) {
                throw new SystemException(-1, " ES 更新收款记录失败");
            }
            transactionProxy.commit();
            
          //推送给oms
            executorService.execute(new Runnable(){
				@Override
				public void run() {
					  Object[] longs = ids.toArray();
				        List<SearchCondition> searchConditions = SearchConditionUtils.start()
				                .addCondition("id", longs, ConditionExpressionEnum.IN).end();
				        List<FinancePaymentESDTO> financeDtos = new DefaultAbstractSearchQueryExecutor<FinancePaymentESDTO>(esSearchService) {
				            @Override
				            public EsConfig getConfig() {
				                return EsConfig.payment();
				            }
				        }.list(searchConditions);
					List<IOrderPayerStatusOMSDTO> orderPayerList = new ArrayList<IOrderPayerStatusOMSDTO>();
					for(FinancePaymentESDTO financePaymentESDTO : financeDtos){
						IOrderPayerStatusOMSDTO orderPayerStatusOMSDto = new IOrderPayerStatusOMSDTO();
						orderPayerStatusOMSDto.setExternalOrderId(financePaymentESDTO.getOrderCode());  //取我们的订单号
						orderPayerStatusOMSDto.setPayerStatus(financePaymentESDTO.getGrantStatus()); //货款发放状态
						orderPayerStatusOMSDto.setPayerAmount(financePaymentESDTO.getPayMoney()); //付款金额
						orderPayerStatusOMSDto.setPayerTime(financePaymentESDTO.getGrantTime()); //发放时间
						orderPayerStatusOMSDto.setWarehouseId(financePaymentESDTO.getWarehouseId());//仓库id
						orderPayerList.add(orderPayerStatusOMSDto);
					}
		            omsService.updateOrderPayerStatusList(orderPayerList);
				}
            	
            });
            return true;
        } catch (Exception e) {
            transactionProxy.rollback();
            e.printStackTrace();
            logger.error(e.getLocalizedMessage(), e);
            WriteMqInfoDTO record = new WriteMqInfoDTO();
            record.setOrderType("updatePaymentForGrant");
            record.setQueueName("updatePaymentForGrant");
            record.setMainInfo(JsonUtil.GsonString(paymentGrantDTO));
            record.setErrorReason(JsonUtil.GsonString(e.getLocalizedMessage()));
            writeMqInfoService.insert(record);
            throw new SystemException(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    public PaymentAccountListRespDTO getAccountList(PaymentAccountReqDTO paymentAccountReqDTO) {
        PaymentAccountListRespDTO paymentAccountListRespTDO = new PaymentAccountListRespDTO();
        try {
        	// TODO 根据承运商id(当前网点id)的财务组织 查询付款组织账户
            List<PaymentAccountRespTDO> payAccountList = new ArrayList<>();
            Long curDotId = paymentAccountReqDTO.getCurDotId();
            SettleOrgDTO paySettleOrg = financeCommonService.getSettleOrg(curDotId.intValue(), 1);
            if (paySettleOrg != null) {
                List<String> payId = new ArrayList<>();
                payId.add(paySettleOrg.getSettleOrgId().toString());
                List<BossIBankAccountInfoDto> payAccounts = bossFinanceEntrusRelationService.queryOrgAccount(payId);
                if (CollectionUtils.isNotEmpty(payAccounts)) {
                    payAccounts.forEach(payAccount ->
                            payAccountList.add(new PaymentAccountRespTDO(payAccount.getId(), payAccount.getAccountSn())));
                }
            }
            paymentAccountListRespTDO.setPayAccounts(payAccountList);

            // TODO 根据经销商id的财务组织 查询收款方账户
            List<PaymentAccountRespTDO> receiptAccountList = new ArrayList<>();
            Long dearId = paymentAccountReqDTO.getDearId();
            IBankAccountQueryDto bankAccountQueryDto = new IBankAccountQueryDto();
            bankAccountQueryDto.setSubjectType("2");
            bankAccountQueryDto.setUserSn(dearId.toString());
            List<IBankAccountInfoDto> receiptAccounts = payService.queryAccount(bankAccountQueryDto);
            if (CollectionUtils.isNotEmpty(receiptAccounts)) {
                receiptAccounts.forEach(receiptAccount ->
                        receiptAccountList.add(new PaymentAccountRespTDO(receiptAccount.getId(), receiptAccount.getAccountSn())));
            }
//            SettleOrgDTO payeeSettleOrg = financeCommonService.getSettleOrg(dearId.intValue(), 2);
//            if (payeeSettleOrg != null) {
//                List<String> payeeId = new ArrayList<>();
//                payeeId.add(payeeSettleOrg.getSettleOrgId().toString());
//                List<BankAccountInfoDto> receiptAccounts = bossFinanceEntrusRelationService.queryOrgAccount(payeeId);
//                if (CollectionUtils.isNotEmpty(receiptAccounts)) {
//                    receiptAccounts.forEach(receiptAccount ->
//                            payAccountList.add(new PaymentAccountRespTDO(receiptAccount.getId(), receiptAccount.getAccountName())));
//                }
//            }
            paymentAccountListRespTDO.setReceiptAccounts(receiptAccountList);
        } catch (SystemException e) {
            throw e;
        } catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
        
        return paymentAccountListRespTDO;
    }

    private FinancePaymentESDTO getFinancePaymentESDTO(PaymentGrantDTO paymentGrantDTO, IPayInfoDTO pay, Long id) {
        FinancePaymentESDTO paymentESDTO = new FinancePaymentESDTO();
        paymentESDTO.setId(id);
        paymentESDTO.setPiId(pay.getId());
        paymentESDTO.setPayCode(pay.getPayCode());
        paymentESDTO.setCompanyId(paymentGrantDTO.getCompanyId());
        paymentESDTO.setPayBankAccount(paymentGrantDTO.getPayBankAccount());
        paymentESDTO.setSettlementMode(paymentGrantDTO.getSettlementMode());
        paymentESDTO.setSettlementModeName(paymentGrantDTO.getSettlementModeName());
        paymentESDTO.setPaymentMode(paymentGrantDTO.getPaymentMode());
        paymentESDTO.setPayChannel(paymentGrantDTO.getPayChannelName());
        paymentESDTO.setPayBankAccount(paymentGrantDTO.getPayBankAccount());
        paymentESDTO.setReceivableBankAccount(paymentGrantDTO.getReceivableBankAccount());
        paymentESDTO.setServiceCharge(paymentGrantDTO.getServiceCharge());
        paymentESDTO.setGrantStatus(GrantStatusEnum.GRANTED.getType());
        paymentESDTO.setGrantTime(System.currentTimeMillis());
        paymentESDTO.setGrantUser(paymentGrantDTO.getUserName());
        paymentESDTO.setUpdateUser(paymentGrantDTO.getUserId());
        paymentESDTO.setUpdateTime(System.currentTimeMillis());
        paymentESDTO.setRemark(paymentGrantDTO.getRemark());
        return paymentESDTO;
    }

    private IPayInfoDTO getFinancePaymentRPC(PaymentGrantDTO paymentGrantDTO, String payCode) {
        IPayInfoDTO payInfoDTO = new IPayInfoDTO();
        List<Long> ids = paymentGrantDTO.getIds();
        BigDecimal serviceCharge = paymentGrantDTO.getServiceCharge();
        List<FinancePaymentESDTO> financePayEsList = getFinancePayEsList(ids);
        System.out.println("getFinancePaymentRPC" + financePayEsList.toString());
        // TODO 计算总付款金额
        BigDecimal totalMoney = BigDecimal.ZERO;
        for (FinancePaymentESDTO financePaymentES : financePayEsList) {
            BigDecimal payMoney = financePaymentES.getPayMoney();
//            BigDecimal payMoney = new BigDecimal(100);
            if (payMoney != null) {
                totalMoney = totalMoney.add(payMoney);
            }
        }
        System.out.println("getFinancePaymentRPC 计算总付款金额" + totalMoney);
        // TODO 实际付款金额
        BigDecimal amountPaid = totalMoney.add(serviceCharge);
        List<PayInfoDetailDTO> payInfoDetailDTOList = new ArrayList<>();
        for (FinancePaymentESDTO financePaymentES : financePayEsList) {
            PayInfoDetailDTO payInfoDetailDTO = new PayInfoDetailDTO();
            BigDecimal payMoney = financePaymentES.getPayMoney();
            Integer payOriginazationId = financePaymentES.getPayOriginazationId();
            // TODO 分摊手续费计算 公式：单笔付款金额 / 总付款金额 * 总手续费
            BigDecimal brokerage = BigDecimal.ZERO;
            if (payMoney != null) {
                brokerage = payMoney.multiply(serviceCharge)
                        .divide(totalMoney, MathContext.DECIMAL64);
            }
            payInfoDetailDTO.setPayBankAccountDisplay(paymentGrantDTO.getPayBankAccount());
            payInfoDetailDTO.setPaymentMode(paymentGrantDTO.getPaymentMode());
            payInfoDetailDTO.setPayChannel(paymentGrantDTO.getPayChannelName());
            payInfoDetailDTO.setReceivableBankAccountDisplay(paymentGrantDTO.getReceivableBankAccount());
            payInfoDetailDTO.setWithSource(true);
            payInfoDetailDTO.setPayMoney(payMoney);
            payInfoDetailDTO.setBrokerage(brokerage);
            payInfoDetailDTO.setPayStatus(PayStatus.PAYSUCCESS);
            payInfoDetailDTO.setCheckStatus(ReconciliationStatus.RECONCILICATIONSUCCESS);
            payInfoDetailDTO.setSettlementMode(paymentGrantDTO.getSettlementMode());
            payInfoDetailDTO.setSettlementModeDisplay(paymentGrantDTO.getSettlementModeName());
            payInfoDetailDTO.setPayType(1);
            //付款性质
            payInfoDetailDTO.setPayCharacter(FinanceConstant.RECEIVABLES_PROPERTY_RECEIVABLE);
            //应付组织
            payInfoDetailDTO.setPayGroup(payOriginazationId);
            //应收方
            payInfoDetailDTO.setPaySupplier(financePaymentES.getReceiptCustomerId());
            //业务员
            payInfoDetailDTO.setOwnedSalesman(1);
            //源头单
            payInfoDetailDTO.setOriginalDocType(FinanceConstant.ORIGINAL_BILL_TYPE);
            payInfoDetailDTO.setOriginalDocTypeDisplay(FinanceConstant.ORIGINAL_BILL_TYPE_NAME);
            String orderCode = financePaymentES.getMainOrderCode();
            Long orderId = null;
            try {
                orderId = Long.valueOf(orderCode);
            } catch (NumberFormatException e) {
                logger.error("订单id类型转换失败：orderCode = {}", orderCode);
                orderId = 0L;
            }
            payInfoDetailDTO.setOriginalDocId(orderId);
            payInfoDetailDTO.setOriginalDocCode(orderCode);

            //来源单ID 运单id
            payInfoDetailDTO.setSourceDocId(financePaymentES.getWaybillId());
            //来源单号 运单号
            payInfoDetailDTO.setSourceCode(financePaymentES.getCode());
            //TODO 来源单 运单中的代收货款
            payInfoDetailDTO.setSourceDocAmount(new BigDecimal(100));
            payInfoDetailDTOList.add(payInfoDetailDTO);

            payInfoDTO.setPayOriginazation(payOriginazationId);
            payInfoDTO.setPayeeObject(financePaymentES.getPayeeObjectId());
        }
        payInfoDTO.setPayInfoDetailDTOList(payInfoDetailDTOList);

        payInfoDTO.setPayCode(payCode);
        payInfoDTO.setSourceBillType(FinanceConstant.SOURCE_BILL_TYPE);
        payInfoDTO.setCurrentObjectType(InteractObjectEnum.CUSTOMNER);
        payInfoDTO.setIsByPush(true);
        //付款单类型
        payInfoDTO.setPayBillType(FinanceConstant.PAY_BILL_TYPE);
        payInfoDTO.setCurrency(FinanceConstant.CHINA_CURRENCY);
        payInfoDTO.setServiceCharge(serviceCharge);
        payInfoDTO.setPayMoney(totalMoney);
        payInfoDTO.setAmountPaid(amountPaid);
        payInfoDTO.setBillStatus(Status.APPROVED);
        payInfoDTO.setBillTime(System.currentTimeMillis());
        payInfoDTO.setCreateUser(Long.parseLong(paymentGrantDTO.getUserId().toString()));

        return payInfoDTO;
    }

    private List<FinancePaymentESDTO> buildPaymentESList(List<FinanceReceiptEsDTO> financeReceiptEsList,
                                                         FinanceReceiptConfirmDTO financeReceiptConfirmDTO, List<Long> ids) {
        List<FinancePaymentESDTO> paymentESDTOList = new ArrayList<>(financeReceiptEsList.size());
        for (int i = 0; i < financeReceiptEsList.size(); i++) {
            FinancePaymentESDTO paymentESDTO = new FinancePaymentESDTO();
            FinanceReceiptEsDTO receiptESDTO = financeReceiptEsList.get(i);
            EntityUtils.copyProperties(receiptESDTO, paymentESDTO);
            Long id = ids.get(i);

            paymentESDTO.setId(id);
            paymentESDTO.setCompanyId(receiptESDTO.getCompanyId());
            paymentESDTO.setReceiptId(receiptESDTO.getId());
            paymentESDTO.setGrantStatus(GrantStatusEnum.NOT_GRANT.getType());
            paymentESDTO.setReceivableBankAccount(receiptESDTO.getReceivablesBankAccount());
            paymentESDTO.setPayBankAccount(receiptESDTO.getPayBankAccount());
            paymentESDTO.setAmountPaid(receiptESDTO.getReceivableMoney());
            paymentESDTO.setPayMoney(receiptESDTO.getReceivableMoney());
            paymentESDTO.setPayOriginazation(receiptESDTO.getReceivableOrganizationDisplay());
            paymentESDTO.setPayOriginazationId(receiptESDTO.getReceivableOrganization());
            paymentESDTO.setPayeeObjectId(receiptESDTO.getSettlementObjectGroupId().intValue());
            paymentESDTO.setPayeeObjectName(receiptESDTO.getSettlementObjectGroupName());
            paymentESDTO.setPayCharacter(FinanceConstant.RECEIVABLES_PROPERTY_RECEIVABLE_NAME);
            paymentESDTO.setSourceBillType("销售订单");
            paymentESDTO.setSourceCode(receiptESDTO.getMainOrderCode());
            paymentESDTO.setCreateTime(System.currentTimeMillis());
            paymentESDTO.setCreateUser(financeReceiptConfirmDTO.getOperatingUserId());
            paymentESDTO.setUpdateTime(System.currentTimeMillis());
            paymentESDTO.setUpdateUser(financeReceiptConfirmDTO.getOperatingUserId());

            paymentESDTOList.add(paymentESDTO);
        }
        return paymentESDTOList;
    }

    private List<FinancePayment> buildPaymentDBList(List<FinancePaymentESDTO> financeReceiptEsList) {
        List<FinancePayment> paymentList = new ArrayList<>(financeReceiptEsList.size());
        for (FinancePaymentESDTO financePaymentESDTO : financeReceiptEsList) {
            FinancePayment payment = EntityUtils.copyProperties(financePaymentESDTO, FinancePayment.class);
            paymentList.add(payment);
        }
        return paymentList;
    }

    /**
     * 获取收款记录
     *
     * @param ids
     * @return
     */
    private List<FinanceReceiptEsDTO> getFinanceReceiptEsList(List<Long> ids) {
        Object[] longs = ids.toArray();
        List<SearchCondition> searchConditions = SearchConditionUtils.start()
                .addCondition("id", longs, ConditionExpressionEnum.IN).end();
        return new DefaultAbstractSearchQueryExecutor<FinanceReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.finance();
            }
        }.list(searchConditions);
    }

    /**
     * 获取收款记录
     *
     * @param ids
     * @return
     */
    private List<FinancePaymentESDTO> getFinancePayEsList(List<Long> ids) {
        Object[] longs = ids.toArray();
        List<SearchCondition> searchConditions = SearchConditionUtils.start()
                .addCondition("id", longs, ConditionExpressionEnum.IN).end();
        return new DefaultAbstractSearchQueryExecutor<FinancePaymentESDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.payment();
            }
        }.list(searchConditions);
    }

    private TransactionManagerUtils.TransactionProxy getTransaction(Long companyId) {
        return dtmFactory.create()
                .addTransManager(FinancePaymentMapper.class, companyId)
                .build();
    }
}
