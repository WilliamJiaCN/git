package com.hivescm.tms.finance.server.component.pcsign.impl;

import com.google.common.collect.Lists;
import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.PagedList;
import com.hivescm.common.exception.SystemException;
import com.hivescm.common.utils.Assert;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.query.FinanceManageReceiptQueryDTO;
import com.hivescm.tms.api.dto.es.finance.query.FinanceManageRecyleQueryDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.CapitalAccountResDTO;
import com.hivescm.tms.api.dto.es.finance.response.CashierConfirmationResDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;
import com.hivescm.tms.api.enums.finance.CodeTypeEnum;
import com.hivescm.tms.api.enums.finance.DeliveryStatusEnum;
import com.hivescm.tms.api.enums.finance.PayWayEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceManageGoodsRecycleComponentService;
import com.hivescm.tms.finance.server.component.finance.FinanceManageReceiptComponentService;
import com.hivescm.tms.finance.server.component.pcsign.CashierConfirmationService;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignMapper;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageGoodsRecycleService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageReceiptService;
import com.hivescm.tms.finance.server.service.finance.FinanceManageGoodsRecycleService;
import com.hivescm.tms.finance.server.service.finance.FinanceManageReceiptService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.intranet.gateway.api.dto.boss.BankAccountVo;
import com.hivescm.tms.intranet.gateway.api.dto.boss.BizUnitWithFuncDetailVo;
import com.hivescm.tms.intranet.gateway.api.dto.boss.QueryByIdReqParam;
import com.hivescm.tms.intranet.gateway.api.dto.boss.receipt.IReceiptInfoDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.receipt.ReceiptInfoDetailDTO;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossBizUnitApi;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossFinanceReceiptService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.IBossFinanceNewReceiptPayApi;
import com.hivescm.tms.intranet.gateway.api.feign.crm.CrmBaseService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import org.apache.xmlbeans.impl.piccolo.util.FactoryServiceFinder;
import org.elasticsearch.search.DocValueFormat;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

import static com.hivescm.common.utils.Assert.isFalse;
import static com.hivescm.common.utils.Assert.isNotNull;

@Service
public class CashierConfirmationServiceImpl implements CashierConfirmationService {

    private static final String MSG_PREFIX = "待新增收款单:";

    private Logger logger = LoggerFactory.getLogger(CashierConfirmationServiceImpl.class);

    @Autowired
    private EsSignService esSignService;
    @Autowired
    private WaybillService waybillService;
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private BossFinanceReceiptService bossFinanceReceiptService;
    @Autowired
    private CrmBaseService crmBaseService;
    @Autowired
    private IBossFinanceNewReceiptPayApi iBossFinanceNewReceiptPayApi;
    @Autowired
    private FinanceManageReceiptService financeManageReceiptService;
    @Autowired
    private FinanceManageGoodsRecycleService financeManageGoodsRecycleService;
    @Autowired
    private EsFinanceManageGoodsRecycleService esFinanceManageGoodsRecycleService;
    @Autowired
    private FinanceManageGoodsRecycleComponentService financeManageGoodsRecycleComponentService;
    @Autowired
    private EsFinanceManageReceiptService esFinanceManageReceiptService;
    @Autowired
    private BossBizUnitApi bossBizUnitApi;
    @Autowired
    private FinanceManageReceiptComponentService financeManageReceiptComponentService;


    @ChainedTransaction(mapper = {SignMapper.class},timeout= TransactionConstants.TIME_OUT)
    @Override
    public Boolean cashierConfirmation(@RouteParam("SignMapper.companyId") CashierConfirmationReqDTO cashierConfirmationReqDTO) {
        Boolean dbResult = false;
        Boolean esResult = false;
        boolean deliverStatusFlag = false;
        Long companyId = cashierConfirmationReqDTO.getCompanyId();
        Assert.isNotNull(companyId,"公司ID为空!");
        String waybillCode = cashierConfirmationReqDTO.getWaybillCode();
        Long waybillId = cashierConfirmationReqDTO.getWaybillId();
        // 1.请求参数合法性校验
        checkDataWhenCashierConfirmation(cashierConfirmationReqDTO);
        // 2.根据运单id 查询运单信息，判断运单信息"运单状态"是否符合"收银确认"。
        WaybillEsDTO waybillEsDTO = checkWayBillStatusWhenCashierConfirmation(waybillCode,companyId);
        // 3.根据运单号查询应收表对应的所有的应收数据
        FinanceManageReceiptQueryDTO financeManageReceiptQueryDTO = new FinanceManageReceiptQueryDTO();
        financeManageReceiptQueryDTO.setIsQueryPayWay(false);
        financeManageReceiptQueryDTO.setWaybillCode(waybillCode);
        financeManageReceiptQueryDTO.setCompanyId(companyId);
        List<FinanceManageReceiptEsDTO> financeManageReceiptEsDTOList = esFinanceManageReceiptService.getEsForReceipt(financeManageReceiptQueryDTO);
        // 4.判断应收表的收银状态，只要有一条数据为未收银的就可以收银。
        if(financeManageReceiptEsDTOList!=null){
            for (FinanceManageReceiptEsDTO financeManageReceiptEsDTO:financeManageReceiptEsDTOList) {
                Integer deliveryStatus = financeManageReceiptEsDTO.getDeliveryStatus();
                if(DeliveryStatusEnum.DELIVERY.getType()!=deliveryStatus){
                    deliverStatusFlag=true;
                    break;
                }
            }
            if(!deliverStatusFlag){
                throw new SystemException(ExceptionCodeConstants.ERROR_WAYBILL_ATTR_QR, "该运单下的所有应收单都已经收银确认，不能重复收银！");
            }
        }

        //5. 根据运单id 和费用种类(到付和代收货款) 查询费用信息集。
        Map<String, Object> toPayAndCollectionOnDeliveryMap = getToPayAndCollectionOnDelivery(companyId, cashierConfirmationReqDTO.getCurentOrgId(), waybillCode, cashierConfirmationReqDTO.getIsToPayReceipt(), cashierConfirmationReqDTO.getIsCollectionOnDeliveryReceipt(),waybillId);
        FinanceManageReceiptEsDTO toPayFinanceManageReceiptEsDTO = (FinanceManageReceiptEsDTO) toPayAndCollectionOnDeliveryMap.get("toPay");
        FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO = (FinanceManageGoodsRecycleEsDTO) toPayAndCollectionOnDeliveryMap.get("collectionOnDelivery");
        // 填充financeSaveReceiptDTO实体,并调用保存接口
        transferFinanceSaveReceiptDTO(toPayFinanceManageReceiptEsDTO, cashierConfirmationReqDTO, companyId);
        // 填充financeSaveRecycleDTO实体,并调用保存接口
        transferFinanceSaveRecycleDTO(financeManageGoodsRecycleEsDTO, cashierConfirmationReqDTO,companyId);

        List<SignEsDTO> signEsDTOList = esSignService.querySignEsByWaybillCode(waybillCode);
        if(signEsDTOList==null){
            logger.info("收银确认接口，根据运单号没有查询到签收信息");
            return true;
        }
        //7.1 》》 更新签收单‘收银确认’db数据
        List<Long> signDOIds = Lists.newArrayList();
        SignDO record = new SignDO();
        for (SignEsDTO signEsDTO:signEsDTOList) {
            signEsDTO.setIcashierConfirm(true);

            record.setId(signEsDTO.getId());
            record.setCompanyId(signEsDTO.getCompanyId());
            record.setIsCashierConfirm(true);
            record.setSignStatus(signEsDTO.getSignStatus());
            record.setUpdateTime(signEsDTO.getUpdateTime());
            record.setUpdateUser(signEsDTO.getUpdateUser());
            signDOIds.add(signEsDTO.getId());
        }

        dbResult = signMapper.updateBatchSignCashierConfirmation(companyId,record,signDOIds) > 0 ? true : false;
        //7.2 》》 更新签收单‘收银确认’es数据
        if (dbResult) {

            try {
                esResult = esSignService.updateSignEsBatchById(signEsDTOList);
            } catch (Exception e) {
                signMapper.deleteBatcheByPrimaryKey(companyId,signDOIds);
                logger.error("收银确认接口修改签收信息失败",e.getMessage(),e);
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_UPDATE_SIGN, "修改签收信息失败：" + e.getMessage());
            }

        }
        return dbResult && esResult;

    }

    @Override
    public List<CashierConfirmationResDTO> cashierConfirmationAmount(CashierConfirmationReqDTO cashierConfirmationDTO) {
        // 1. 获取运单号
        String waybillCode =  cashierConfirmationDTO.getWaybillCode();
        // 2. 根据运单号查询相应的应收信息
        FinanceManageReceiptQueryDTO financeManageReceiptQueryDTO = new FinanceManageReceiptQueryDTO();
        financeManageReceiptQueryDTO.setIsQueryPayWay(true);
        financeManageReceiptQueryDTO.setWaybillCode(waybillCode);
        financeManageReceiptQueryDTO.setCompanyId(cashierConfirmationDTO.getCompanyId());
        List<FinanceManageReceiptEsDTO> financeManageReceiptEsDTOList = esFinanceManageReceiptService.getEsForReceipt(financeManageReceiptQueryDTO);
        List<CashierConfirmationResDTO> confirmationResDTOList = Lists.newArrayList();
        // 3. 对查询结果进行判断
        if(financeManageReceiptEsDTOList!=null&&financeManageReceiptEsDTOList.size()>0){
            for (FinanceManageReceiptEsDTO financeManageReceiptEsDTO:financeManageReceiptEsDTOList) {
                CashierConfirmationResDTO cashierConfirmationResDTO = new CashierConfirmationResDTO();
                cashierConfirmationResDTO.setPayWay(financeManageReceiptEsDTO.getPayWay());
                cashierConfirmationResDTO.setUnreceipt_amount(financeManageReceiptEsDTO.getUnreceiptAmount());
                cashierConfirmationResDTO.setWaybillCode(financeManageReceiptEsDTO.getOrderSourceCode());
                cashierConfirmationResDTO.setReceiptAmount(financeManageReceiptEsDTO.getReceiptAmount());
                confirmationResDTOList.add(cashierConfirmationResDTO);
            }

        }
        //4.去货款回收表查询相应的代收货款数据
        FinanceManageRecyleQueryDTO financeManageRecyleQueryDTO = new FinanceManageRecyleQueryDTO();
        financeManageRecyleQueryDTO.setWaybillCode(waybillCode);
        financeManageRecyleQueryDTO.setCompanyId(cashierConfirmationDTO.getCompanyId());
        FinanceManageGoodsRecycleEsDTO recycleByWaybillCode = esFinanceManageGoodsRecycleService.getRecycleByWaybillCode(financeManageRecyleQueryDTO);


        if(recycleByWaybillCode !=null){
            CashierConfirmationResDTO cashierConfirmationResDTO = new CashierConfirmationResDTO();
            cashierConfirmationResDTO.setPayWay(PayWayEnum.COLLECTIONOFGOODS.getType());
            cashierConfirmationResDTO.setUnreceipt_amount(recycleByWaybillCode.getUnreceiptAmount());
            cashierConfirmationResDTO.setWaybillCode(recycleByWaybillCode.getOrderSourceCode());
            cashierConfirmationResDTO.setReceiptAmount(recycleByWaybillCode.getDeliveryAmount());
            confirmationResDTOList.add(cashierConfirmationResDTO);
        }

        return confirmationResDTOList;
    }
    /**
     *@author sql
     *@Date 2018\5\12 0012
     *@param
     *@Describe 资金账户接口
     */
    @Override
    public List<CapitalAccountResDTO> capitalAccount(CapitalAccountReqDTO capitalAccountReqDTO) {
        List<CapitalAccountResDTO> capitalAccountResDTOS = Lists.newArrayList();
        // 1.调取boss接口,获取资金账户信息
        DataResult<PagedList<BankAccountVo>> result =  iBossFinanceNewReceiptPayApi.queryBankAccount(capitalAccountReqDTO);
        // 2.判空
        Assert.isTrue(result != null, "调用Boss资金账户接口获取返回值为null");
        PagedList<BankAccountVo> pagedList = result.getResult();
        Assert.isTrue(result != null, "调用Boss资金账户接口获取 PagedList<BankAccountVo>为null");
        List<BankAccountVo> bankAccountVoList = pagedList.getItems();
        Assert.isTrue(bankAccountVoList != null, "调用Boss资金账户接口获取 List<BankAccountVo>为null");
        // 3.循环赋值
        for (BankAccountVo bankAccountVo:bankAccountVoList) {
            CapitalAccountResDTO capitalAccountResDTO = new CapitalAccountResDTO();
            capitalAccountResDTO.setFundAccount(bankAccountVo.getAccountSn());
            capitalAccountResDTOS.add(capitalAccountResDTO);
        }

        return capitalAccountResDTOS;
    }

    /**
     *@author sql
     *@Date 2018\5\15 0015
     *@param
     *@Describe 填充financeSaveRecycleDTO实体
     */
    private FinanceSaveRecycleDTO transferFinanceSaveRecycleDTO(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO, CashierConfirmationReqDTO cashierConfirmationDTO
            , Long companyId) {

        FinanceSaveRecycleDTO financeSaveRecycleDTO = new FinanceSaveRecycleDTO();
        EntityUtils.copyProperties(cashierConfirmationDTO, financeSaveRecycleDTO);
        financeSaveRecycleDTO.setCompanyId(companyId);
        List<FinanceSaveReceiptIDDTO> financeManageReceiptEsDTOList = Lists.newArrayList();

        if (financeManageGoodsRecycleEsDTO != null) {
            // 将应收金额的值赋值给本次收款金额 
        	FinanceSaveReceiptIDDTO dto = new FinanceSaveReceiptIDDTO();
            dto.setId(financeManageGoodsRecycleEsDTO.getId());
            dto.setReceiptAmount(financeManageGoodsRecycleEsDTO.getDeliveryAmount());
            financeManageReceiptEsDTOList.add(dto);
            financeSaveRecycleDTO.setList(financeManageReceiptEsDTOList);
            financeManageGoodsRecycleComponentService.saveRecycle(financeSaveRecycleDTO);
        }



        return financeSaveRecycleDTO;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\10 0010
     * @Describe 填充FinanceSaveReceiptDTO实体
     */
    private FinanceSaveReceiptDTO transferFinanceSaveReceiptDTO(FinanceManageReceiptEsDTO toPayFinanceManageReceiptEsDTO, CashierConfirmationReqDTO cashierConfirmationDTO
            , Long companyId) {

        FinanceSaveReceiptDTO financeSaveReceiptDTO = new FinanceSaveReceiptDTO();
        EntityUtils.copyProperties(cashierConfirmationDTO, financeSaveReceiptDTO);
        financeSaveReceiptDTO.setCompanyId(companyId);
        List<FinanceManageReceiptEsDTO> financeManageReceiptEsDTOList = Lists.newArrayList();
        if (toPayFinanceManageReceiptEsDTO != null) {
            //将应收金额的值赋值给本次收款金额
            toPayFinanceManageReceiptEsDTO.setCurrentReceiptedAmount(toPayFinanceManageReceiptEsDTO.getReceiptAmount());

            financeManageReceiptEsDTOList.add(toPayFinanceManageReceiptEsDTO);
            financeSaveReceiptDTO.setList(financeManageReceiptEsDTOList);
            //6.1 调用保存到付接口接口
            financeManageReceiptComponentService.saveReceipt(financeSaveReceiptDTO);
        }



        return financeSaveReceiptDTO;
    }


    /**
     * 收银确认时，校验请求参数合法性
     *
     * @param cashierConfirmationDTO
     */
    private void checkDataWhenCashierConfirmation(CashierConfirmationReqDTO cashierConfirmationDTO) {
        Assert.isNotNull(cashierConfirmationDTO.getPaymentMode(), "支付方式不可为空！");
        Assert.isNotNull(cashierConfirmationDTO.getCashierTime(), "收银时间不可为空！");
        Assert.isNotNull(cashierConfirmationDTO.getIsCollectionOnDeliveryReceipt(), "是否代收货款收款不可为空！");
        Assert.isNotNull(cashierConfirmationDTO.getIsToPayReceipt(), "是否到付收款不可为空！");
        Assert.isNotNull(cashierConfirmationDTO.getReceiveAccount(), "收款账户不可为空！");
        Assert.isNotNull(cashierConfirmationDTO.getWaybillCode(), "运单号不可为空");
        Assert.isTrue(cashierConfirmationDTO.getIsToPayReceipt() || cashierConfirmationDTO.getIsCollectionOnDeliveryReceipt(), "至少需要有一个收款项！");

    }

    /**
     * 根据运单号 查询运单信息，判断运单信息"运单状态"是否符合"收银确认"。
     *
     * @param waybillCode
     */
    private WaybillEsDTO checkWayBillStatusWhenCashierConfirmation(String waybillCode,Long companyId) {
        WaybillEsDTO waybillEsDTO = new WaybillEsDTO();
        int intCompanyId = companyId.intValue();
        try {
            waybillEsDTO = waybillService.getWaybillByCode(waybillCode,intCompanyId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SIGN_NOT_EXIST, "调用运单接口(queryWaybillEsDTO)异常：" + e.getMessage());
        }
        if (waybillEsDTO == null) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SIGN_NOT_EXIST, "运单ID：" + waybillCode + " 未查询到运单信息！");
        } else if (waybillEsDTO.getStatus() != WaybillStatusEnum.SIGNED.getType()) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SIGN_NOT_EXIST, "运单ID：" + waybillCode + "的运单状态(" + waybillEsDTO.getStatus() + ")非‘已签收’，不能进行‘收银确认’！");
        }
        return waybillEsDTO;
    }

    /**
     * 从[收款管理]中获取 【到付】和【代收货款】
     *
     * @param companyId                     公司id
     * @param currentOrgId                  当前操作的组织id
     * @param waybillCode                   运单号
     * @param isToPayReceipt                是否收费到付
     * @param isCollectionOnDeliveryReceipt 是否收费代收款款
     * @return
     */
    private Map<String, Object> getToPayAndCollectionOnDelivery(Long companyId, Integer currentOrgId, String waybillCode, Boolean isToPayReceipt, Boolean isCollectionOnDeliveryReceipt,Long wayBillId) {
        Map<String, Object> result = new HashMap<>();
        if (isToPayReceipt) {
            FinanceManageReceiptEsDTO toPayFinanceManageReceiptEsDTO = new FinanceManageReceiptEsDTO();// [到付]
            FinanceManageReceiptReqDTO toPayReqDTO = new FinanceManageReceiptReqDTO();// [到付]请求体
            toPayReqDTO.setCompanyId(companyId);//公司ID
            toPayReqDTO.setCodeType(CodeTypeEnum.YD.getType());//单据类型1.运单  8.代收货款 9.他其收入
            toPayReqDTO.setOrderSourceId(waybillCode);//来源单号
            toPayReqDTO.setPayWay(PayWayEnum.TOPAY.getType());//结算方式4、现付 5、欠付 6、月结 7、回单付 8、到付 9、货款扣
            try {
                toPayFinanceManageReceiptEsDTO = financeManageReceiptService.findFinanceManageReceipt(toPayReqDTO);
            } catch (Exception e) {
                logger.error("收银确认接口查询应付异常{}",e.getMessage(),e);
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_QUERY, " 收银确认接口根据运单号：" + waybillCode + "查询应收异常");
            }
            if(toPayFinanceManageReceiptEsDTO == null){
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_QUERY, "根据运单号：" + waybillCode + " 没有查询到相应应收数据");
            }

            if (toPayFinanceManageReceiptEsDTO.getReceiptAmount() == null || toPayFinanceManageReceiptEsDTO.getReceiptAmount().compareTo(BigDecimal.ZERO) != 1
                    || toPayFinanceManageReceiptEsDTO.getUnreceiptAmount() == null || toPayFinanceManageReceiptEsDTO.getUnreceiptAmount().compareTo(BigDecimal.ZERO) != 1) {
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_QUERY, "根据运单号：" + waybillCode + " 查询应收金额(或未收金额)为空或者为0！");
            }
            result.put("toPay", toPayFinanceManageReceiptEsDTO);
        }
        if (isCollectionOnDeliveryReceipt) {

            FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO = new FinanceManageGoodsRecycleEsDTO();// [代收货款]
            try {
                financeManageGoodsRecycleEsDTO = financeManageGoodsRecycleService.getEsByWaybillIdForRecycle(wayBillId);
            } catch (Exception e) {
                e.printStackTrace();
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_VERITY, "根据运单号：" + waybillCode + " 查询代收货款金额异常：" + e.getMessage());
            }
            if(financeManageGoodsRecycleEsDTO == null || financeManageGoodsRecycleEsDTO.getId() == null||financeManageGoodsRecycleEsDTO.getRecycleStatus()==3){
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_VERITY, "根据运单号：" + waybillCode + " 查询代收货款数据为空或该数据状态为已回收");
            }
            if (financeManageGoodsRecycleEsDTO.getUnreceiptAmount() == null || financeManageGoodsRecycleEsDTO.getUnreceiptAmount().compareTo(BigDecimal.ZERO) != 1) {
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_VERITY, "根据运单号：" + waybillCode + " 查询代收货款未收金额为0或为空！");
            }
            result.put("collectionOnDelivery", financeManageGoodsRecycleEsDTO);
        }
        return result;
    }







    /**
     * 依据有源属性校验收款单
     *
     * @param receiptInfo 收款单信息
     */
    private void checkReceiptInfo(IReceiptInfoDTO receiptInfo) {
        checkReceiptHeadInfo(receiptInfo);

        final List<ReceiptInfoDetailDTO> receiptInfoDetails = receiptInfo.getReceiptInfoDetailDTOList();
        batchCheckReceiptBody(receiptInfoDetails);
    }

    /**
     * 检查收款单表体
     *
     * @param receiptHeadInfo 收款单表体
     */
    private void checkReceiptHeadInfo(IReceiptInfoDTO receiptHeadInfo) {
        isNotNull(receiptHeadInfo, MSG_PREFIX + "参数为空");
        isNotNull(receiptHeadInfo.getReceiptType(), MSG_PREFIX + "收款单类型不能为空");
        isNotNull(receiptHeadInfo.getReceivablesOrganization(), MSG_PREFIX + "收款组织不能为空");
        isNotNull(receiptHeadInfo.getCurrentObjectType(), MSG_PREFIX + "往来对象类型不能为空");
        isNotNull(receiptHeadInfo.getPayObject(), MSG_PREFIX + "付款方不能为空");
        isNotNull(receiptHeadInfo.getCurrency(), MSG_PREFIX + "币种不能为空");
        isNotNull(receiptHeadInfo.getSourceBillType(), MSG_PREFIX + "来源单据类型不能为空");
        isNotNull(receiptHeadInfo.getBillDate(), MSG_PREFIX + "单据日期不能为空");
        isNotNull(receiptHeadInfo.getCreateUser(), MSG_PREFIX + "新建人不能为空");
        List<ReceiptInfoDetailDTO> receiptBody = receiptHeadInfo.getReceiptInfoDetailDTOList();
        isFalse(CollectionUtils.isEmpty(receiptBody), MSG_PREFIX + "收款明细不能为空");
    }

    /**
     * 批量检查收款单表体信息集
     *
     * @param receiptBodies 收款单表体信息集
     */
    private void batchCheckReceiptBody(final List<ReceiptInfoDetailDTO> receiptBodies) {
        receiptBodies.forEach(body -> checkSingleReceiptBody(body));
    }

    /**
     * 检查单个收款单表体信息
     *
     * @param receiptInfoDetail 收款单表体信息
     */
    private void checkSingleReceiptBody(ReceiptInfoDetailDTO receiptInfoDetail) {
        isNotNull(receiptInfoDetail.getReceivableMoney(), MSG_PREFIX + "收款金额不能为空");
        isNotNull(receiptInfoDetail.getReceivableOrganization(), MSG_PREFIX + "应收组织不能为空");
        isNotNull(receiptInfoDetail.getReceivableCustomer(), MSG_PREFIX + "应付方不能为空");
        isNotNull(receiptInfoDetail.getPaymentMode(), MSG_PREFIX + "支付方式不能为空");
        isNotNull(receiptInfoDetail.getSettlementMode(), MSG_PREFIX + "结算方式不能为空");
        isNotNull(receiptInfoDetail.getReceivablesProperty(), MSG_PREFIX + "收款性质不能为空");
        isNotNull(receiptInfoDetail.getReceivablesType(), MSG_PREFIX + "收款类型不能为空");
        isNotNull(receiptInfoDetail.getSourceDocId(), MSG_PREFIX + "来源单ID不能为空");
        isNotNull(receiptInfoDetail.getSourceNumber(), MSG_PREFIX + "来源单号不能为空");
        isNotNull(receiptInfoDetail.getSourceDocAmount(), MSG_PREFIX + "来源单金额不能为空");
        isNotNull(receiptInfoDetail.getOriginalDocId(), MSG_PREFIX + "源头单ID不能为空");
        isNotNull(receiptInfoDetail.getOriginalBillType(), MSG_PREFIX + "源头单类型不能为空");
        isNotNull(receiptInfoDetail.getOriginalBillNumber(), MSG_PREFIX + "源头单号不能为空");

    }

    //核算组织
    private Integer setCheckObjectId(Integer id) {
        if (id != null) {
            QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
            BizUnitWithFuncDetailVo result = bossBizUnitApi.queryBizUnitById(queryByIdReqParam).getResult();
            if (result != null && result.getLogistics() != null) {
                return result.getLogistics().getAccountOrgId();
            }
        }
        return null;
    }

    //结算组织
    private Integer setSettleObjectId(Integer id) {
        if (id != null) {
            QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
            BizUnitWithFuncDetailVo result = bossBizUnitApi.queryBizUnitById(queryByIdReqParam).getResult();
            if (result != null && result.getLogistics() != null) {
                return result.getLogistics().getSettleOrgId();
            }
        }
        return null;
    }


    /**
     * 财务收银后回写签收收银状态
     * @param reqDTO 收银状态相关参数
     * 2018-06-10
     * zouhx
     * @return
     */

    @Override
    public boolean updateSignCashierStatus(WayBillCashierConfirmationReqDTO reqDTO) {

        Assert.isNotNull(reqDTO.getWaybillIds(), "收银运单id不能为空！");
        Assert.isNotNull(reqDTO.getCompanyId(), "收银公司id不能为空");
        Assert.isNotNull(reqDTO.getCashierConfirmStatus(),"收银状态不能为空");
        Assert.between(reqDTO.getCashierConfirmStatus(),1,2,"收银状态传入值不正确");

        Boolean dbResult = false;
        Boolean esResult = false;
        boolean cashierStatus= reqDTO.getCashierConfirmStatus()==1?false:true;
        Long companyId=reqDTO.getCompanyId();

        //更新签收单‘收银确认’es,db
        List<SignEsDTO> signEsDTOList = esSignService.querySignEsByWaybillIds(reqDTO.getWaybillIds());
        if(signEsDTOList==null){
            logger.info("更新收银确认状态接口，根据运单号没有查询到签收信息");
//            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_UPDATE_SIGN, "更新收银确认状态接口:根据运单号没有查询到签收信息");
            return true;
        }
        //db数据
        List<Long> signDOIds = Lists.newArrayList();
        SignDO record = new SignDO();
        for (SignEsDTO signEsDTO:signEsDTOList){
            record.setId(signEsDTO.getId());
            record.setCompanyId(signEsDTO.getCompanyId());
            record.setIsCashierConfirm(cashierStatus);
            record.setUpdateTime(new Date().getTime());
            record.setUpdateUser(signEsDTO.getUpdateUser());
            signDOIds.add(signEsDTO.getId());
            signEsDTO.setIcashierConfirm(cashierStatus);
        }
        dbResult = signMapper.updateBatchSignCashierConfirmation(companyId,record,signDOIds) > 0 ? true : false;
        //更新签收单‘收银确认’状态,es数据
        if (dbResult){
            try {
                esResult = esSignService.updateSignEsBatchById(signEsDTOList);
            } catch (Exception e) {
                signMapper.deleteBatcheByPrimaryKey(companyId,signDOIds);
                logger.error("运单收银，调用收银修改收银状态接口信息失败",e.getMessage(),e);
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_UPDATE_SIGN, "修改签收信息失败：" + e.getMessage());
            }
        }
        return dbResult && esResult;
    }



}
