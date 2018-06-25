package com.hivescm.tms.finance.server.component.finance.impl;


import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManagePayAmountReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManagePayCancelReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManagePayConfirmReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.OrgInfoDTO;
import com.hivescm.tms.api.enums.finance.*;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceCommonService;
import com.hivescm.tms.finance.server.component.finance.FinanceManagePayComponentService;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManagePayMapper;
import com.hivescm.tms.finance.server.service.db.DbFinanceManagePayService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageCashSerialService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManagePayService;
import com.hivescm.tms.finance.server.service.finance.constant.FinanceConstant;
import com.hivescm.tms.intranet.gateway.api.dto.boss.LogisticsOrgFuncInfoBean;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.InteractObjectEnum;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.Status;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.CodeOpParam;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.SettleBillDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.SettleDetailDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * 财务管理-应付表的Service实现
 *
 * @author wenxiong.jia
 * @date 2018/4/27
 */
@Slf4j
@Service
public class FinanceManagePayComponentServiceImpl implements FinanceManagePayComponentService {

    private static final DecimalFormat df = new DecimalFormat("#0.00");

    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private FinanceCommonService financeCommonService;
    @Autowired
    private EsFinanceManagePayService esFinanceManagePayService;
    @Autowired
    private DbFinanceManagePayService dbFinanceManagePayService;
    @Autowired
    private EsFinanceManageCashSerialService esFinanceManageCashSerialService;

    @Override
    public Boolean payConfirm(FinanceManagePayConfirmReqDTO financeManagePayConfirmReqDto) {
        //操作时间
        Long payConfirmTime = Instant.now().toEpochMilli();

        SettleBillDTO settleBillDto;
        FinanceManagePayDO financeManagePayDo;
        FinanceManageCashSerialDO financeManageCashSerialDo;
        FinanceManagePayEsDTO financeManagePayEsDto;
        OrgInfoDTO orgInfoDto = new OrgInfoDTO();
        List<FinanceManagePayAmountReqDTO> financeManagePayAmountReqDtoList = financeManagePayConfirmReqDto.getFinanceManagePayAmountReqDtoList();
        if (CollectionUtils.isNotEmpty(financeManagePayAmountReqDtoList)) {
            for (FinanceManagePayAmountReqDTO financeManagePayAmountReqDto : financeManagePayAmountReqDtoList) {
                financeManagePayEsDto = esFinanceManagePayService.getEs(financeManagePayAmountReqDto.getId());
                if (financeManagePayEsDto == null) {
                    log.error("信息不存在，请求的ID：" + financeManagePayAmountReqDto.getId());
                    throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_PAY_PAYCONFIRM, "付款确认异常，请求的信息不存在");
                }
                //组装请求DTO
                settleBillDto = buildReqDTO(financeManagePayConfirmReqDto, financeManagePayEsDto, financeManagePayAmountReqDto, orgInfoDto, payConfirmTime);
                financeManagePayDo = new FinanceManagePayDO();
                financeManageCashSerialDo = new FinanceManageCashSerialDO();
                financeManagePayDo.setCompanyId(0L);//初始值，实际值从es中获取
                financeManageCashSerialDo.setCompanyId(0L);
                //生成付款单
                generatePaymentSheet(financeManagePayDo, financeManageCashSerialDo, financeManagePayConfirmReqDto, financeManagePayEsDto, financeManagePayAmountReqDto, settleBillDto, orgInfoDto, payConfirmTime);
            }
        }
        return true;
    }

    @Override
    @ChainedTransaction(mapper = {FinanceManagePayMapper.class, FinanceManageCashSerialMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public Boolean cancelPay(@RouteParam("FinanceManagePayMapper.companyId") FinanceManagePayDO financeManagePayDo,
                             @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO originalFinanceManageCashSerialDo,
                             @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO newFinanceManageCashSerialDo,
                             FinanceManagePayCancelReqDTO financeManagePayCancelReqDto) {
        //操作时间
        Long payCancelTime = Instant.now().toEpochMilli();

        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto = esFinanceManageCashSerialService.getEs(financeManagePayCancelReqDto.getId());
        if (financeManageCashSerialEsDto == null) {
            log.error("资金流水信息不存在，请求的ID：" + financeManagePayCancelReqDto.getId());
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_PAY_PAYCANCEL, "取消付款异常，资金流水信息不存在");
        }
        //获取付款单对应应付信息
        FinanceManagePayEsDTO financeManagePayEsDto = esFinanceManagePayService.getEs(financeManageCashSerialEsDto.getFinanceId());
        if (financeManagePayEsDto == null) {
            log.error("应付信息不存在，请求的ID：" + financeManagePayCancelReqDto.getId());
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_PAY_PAYCANCEL, "取消付款异常，应付信息不存在");
        }
        FinanceManagePayEsDTO oldFinanceManagePayEsDto = financeManagePayEsDto.clone();
        //已付金额 -= 本次付款
        financeManagePayEsDto.setPaidAmount(new BigDecimal(df.format(financeManagePayEsDto.getPaidAmount().doubleValue() - financeManageCashSerialEsDto.getPayAmount().doubleValue() + financeManageCashSerialEsDto.getServiceCharge().doubleValue())));
        //未付金额 = 应付金额 - 已付金额
        financeManagePayEsDto.setUnpaidAmount(new BigDecimal(df.format(financeManagePayEsDto.getPayableAmount().doubleValue() - financeManagePayEsDto.getPaidAmount().doubleValue())));
        //付款手续费 -= 本次付款分摊的手续费
        financeManagePayEsDto.setPaymentFee(new BigDecimal(df.format(financeManagePayEsDto.getPaymentFee().doubleValue() - financeManageCashSerialEsDto.getServiceCharge().doubleValue())));
        //实付金额 = 已付金额 + 付款手续费
        financeManagePayEsDto.setActualPaidAmount(new BigDecimal(df.format(financeManagePayEsDto.getPaidAmount().doubleValue() + financeManagePayEsDto.getPaymentFee().doubleValue())));
        //设置付款状态 未付金额大于0对应部分付款状态 等于0对应已付款状态
        setPaymentStatus(financeManagePayEsDto);

        financeManagePayEsDto.setUpdateUserId(financeManagePayCancelReqDto.getOprId());
        financeManagePayEsDto.setUpdateUserName(financeManagePayCancelReqDto.getOprName());
        financeManagePayEsDto.setUpdateTime(payCancelTime);

        //初始化应付信息DB实体
        initFinanceManagePayDO(financeManagePayEsDto, financeManagePayDo);
        //原资金流水变为已取消状态
        financeManageCashSerialEsDto.setStatus(FlowStatusEnum.CANCEL.getType());
        financeManageCashSerialEsDto.setUpdateUserId(financeManagePayCancelReqDto.getOprId());
        financeManageCashSerialEsDto.setUpdateTime(payCancelTime);
        //初始化原资金流水DB实体
        originalFinanceManageCashSerialDo.setId(financeManageCashSerialEsDto.getId());
        originalFinanceManageCashSerialDo.setCompanyId(financeManageCashSerialEsDto.getCompanyId());
        originalFinanceManageCashSerialDo.setStatus(financeManageCashSerialEsDto.getStatus());
        originalFinanceManageCashSerialDo.setUpdateUserId(financeManagePayCancelReqDto.getOprId());
        originalFinanceManageCashSerialDo.setUpdateTime(payCancelTime);

        FinanceManageCashSerialEsDTO newFinanceManageCashSerialEsDto = EntityUtils.copyProperties(financeManageCashSerialEsDto, FinanceManageCashSerialEsDTO.class);
        newFinanceManageCashSerialEsDto.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL));
        newFinanceManageCashSerialEsDto.setType(2);
        newFinanceManageCashSerialEsDto.setPayAmount(new BigDecimal("-" + df.format(financeManageCashSerialEsDto.getPayAmount().doubleValue())));
        newFinanceManageCashSerialEsDto.setRemark("【" + financeManagePayCancelReqDto.getOprName() + "】取消付款");
        newFinanceManageCashSerialEsDto.setServiceCharge(new BigDecimal("-" + df.format(financeManageCashSerialEsDto.getServiceCharge().doubleValue())));
        newFinanceManageCashSerialEsDto.setCreateBillTime(payCancelTime);
        newFinanceManageCashSerialEsDto.setCreateTime(payCancelTime);
        newFinanceManageCashSerialEsDto.setCreateUserId(financeManagePayCancelReqDto.getOprId());
        newFinanceManageCashSerialEsDto.setCreateUserName(financeManagePayCancelReqDto.getOprName());
        newFinanceManageCashSerialEsDto.setStatus(FlowStatusEnum.CANCELLATION.getType());
        newFinanceManageCashSerialEsDto.setUpdateUserId(null);
        newFinanceManageCashSerialEsDto.setUpdateTime(null);
        newFinanceManageCashSerialDo = EntityUtils.copyProperties(newFinanceManageCashSerialEsDto, FinanceManageCashSerialDO.class);
        try {
            //更新DB并修改原流水、记录新流水
            dbFinanceManagePayService.oprPayCancelDB(financeManagePayDo, originalFinanceManageCashSerialDo, newFinanceManageCashSerialDo);
            //更新ES并修改原流水、记录新流水
            esFinanceManageCashSerialService.insert(newFinanceManageCashSerialEsDto);
            esFinanceManagePayService.updateEs(financeManagePayEsDto);
            esFinanceManageCashSerialService.updateEs(financeManageCashSerialEsDto);
            CodeOpParam opParam = new CodeOpParam();
            opParam.setTypeSeparate(2);
            opParam.setCode(financeManageCashSerialEsDto.getPayCode());
            opParam.setGroupId(financeManageCashSerialEsDto.getGroupId());
            opParam.setOpUser(financeManagePayCancelReqDto.getOprId());
            opParam.setOpTime(payCancelTime);
            //调用BOSS接口取消付款单
            financeCommonService.deleteBySource(opParam);
            return true;
        } catch (Exception e) {
            log.error("取消付款单失败，来源单ID：" + financeManagePayEsDto.getId());
            //回滚ES
            if (esFinanceManageCashSerialService.getEs(newFinanceManageCashSerialEsDto.getId()) != null) {
                esFinanceManageCashSerialService.deleteById(newFinanceManageCashSerialEsDto.getId());
                financeManageCashSerialEsDto.setStatus(FlowStatusEnum.NORMAL.getType());
                financeManageCashSerialEsDto.setUpdateUserId(null);
                financeManageCashSerialEsDto.setUpdateTime(null);
                esFinanceManageCashSerialService.updateEsWithNull(financeManageCashSerialEsDto);
                esFinanceManagePayService.deleteById(financeManagePayEsDto.getId());
                esFinanceManagePayService.insertEs(oldFinanceManagePayEsDto);
            }
            throw e;
        }
    }

    /**
     * 组装请求创建付款单的DTO
     * @param financeManagePayConfirmReqDto
     * @param financeManagePayEsDto
     * @param financeManagePayAmountReqDto
     * @param orgInfoDto
     * @param payConfirmTime
     * @return
     */
    private SettleBillDTO buildReqDTO(FinanceManagePayConfirmReqDTO financeManagePayConfirmReqDto,
                                      FinanceManagePayEsDTO financeManagePayEsDto,
                                      FinanceManagePayAmountReqDTO financeManagePayAmountReqDto,
                                      OrgInfoDTO orgInfoDto,
                                      Long payConfirmTime) {
        //表头
        SettleBillDTO settleBillDto = new SettleBillDTO();
        //表体
        SettleDetailDTO settleDetailDto = new SettleDetailDTO();
        // 类型区分(1收款;2付款)
        settleBillDto.setTypeSeparate(2);
        // 集团 ID
        settleBillDto.setGroupId(financeManagePayConfirmReqDto.getGroupId());
        // 付款单类型(10001 采购付款单;10002 销售退货付款单;10003 代付货款付款单;10004 其他付款单;10005 服务付款单)
        settleBillDto.setBillType(FinanceConstant.PAY_SERVICE_TYPE);
        // 来源单据类型(数据字典 BOSS030)
        if (financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.YD.getType())) {//运单
            //来源单据类型
            settleBillDto.setSourceBillType(CalculationFeeEnum.YD.getDocType());
            //源头单据类型
            settleDetailDto.setOriginalBillType(CalculationFeeEnum.YD.getDocType());
        } else if (financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.PCD.getType())) {//派车单
            //来源单据类型
            settleBillDto.setSourceBillType(CalculationFeeEnum.PCD.getDocType());
            //源头单据类型
            settleDetailDto.setOriginalBillType(CalculationFeeEnum.PCD.getDocType());
        } else if (financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.FCPZD.getType())) {//发车配载单
            //来源单据类型
            settleBillDto.setSourceBillType(CalculationFeeEnum.FCPZD.getDocType());
            //源头单据类型
            settleDetailDto.setOriginalBillType(CalculationFeeEnum.FCPZD.getDocType());
        } else if (financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.WFZZD.getType())) {//外发中转单
            //来源单据类型
            settleBillDto.setSourceBillType(CalculationFeeEnum.WFZZD.getDocType());
            //源头单据类型
            settleDetailDto.setOriginalBillType(CalculationFeeEnum.WFZZD.getDocType());
        } else if (financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.ZHD.getType())) {//装货单
            //来源单据类型
            settleBillDto.setSourceBillType(CalculationFeeEnum.ZHD.getDocType());
            //源头单据类型
            settleDetailDto.setOriginalBillType(CalculationFeeEnum.ZHD.getDocType());
        } else if (financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.XDD.getType())) {//卸货单
            //来源单据类型
            settleBillDto.setSourceBillType(CalculationFeeEnum.XDD.getDocType());
            //源头单据类型
            settleDetailDto.setOriginalBillType(CalculationFeeEnum.XDD.getDocType());
        } else if (financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.DHQRD.getType())) {//到货确认单
            //来源单据类型
            settleBillDto.setSourceBillType(CalculationFeeEnum.DHQRD.getDocType());
            //源头单据类型
            settleDetailDto.setOriginalBillType(CalculationFeeEnum.DHQRD.getDocType());
        } else if (financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.YCHD.getType())) {//异常单
            //来源单据类型
            settleBillDto.setSourceBillType(CalculationFeeEnum.YCHD.getDocType());
            //源头单据类型
            settleDetailDto.setOriginalBillType(CalculationFeeEnum.YCHD.getDocType());
        }
        LogisticsOrgFuncInfoBean logisticsOrgFuncInfoBean;
        if (financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.YD.getType())) {//运单
            logisticsOrgFuncInfoBean = financeCommonService.getOrgId(financeManagePayEsDto.getBusinessNetworkId());
            //往来对象类型
            settleBillDto.setCooperationType(InteractObjectEnum.CUSTOMNER.code());
        } else if(financeManagePayEsDto.getSheetType() == Integer.valueOf(CalculationFeeEnum.YCHD.getType())) {//异常单
            logisticsOrgFuncInfoBean = financeCommonService.getOrgId(financeManagePayEsDto.getPaymentNetworkId());
            //往来对象类型
            settleBillDto.setCooperationType(InteractObjectEnum.CUSTOMNER.code());
        } else {
            //其他单据
            logisticsOrgFuncInfoBean = financeCommonService.getOrgId(financeManagePayEsDto.getPaymentNetworkId());
            settleBillDto.setCooperationType(InteractObjectEnum.SUPLIER.code());
        }
        orgInfoDto.setAccountOrgId(logisticsOrgFuncInfoBean.getAccountOrgId());
        orgInfoDto.setAccountOrgName(logisticsOrgFuncInfoBean.getAccountOrgName());
        // 单据日期
        settleBillDto.setBillDate(payConfirmTime);
        // 单据状态
        settleBillDto.setBillStatus(Status.APPROVED);
        // 结算组织(收款/付款组织)
        settleBillDto.setSettleOrg(logisticsOrgFuncInfoBean.getSettleOrgId());
        // 结算组织账户(收款/付款组织账号)
        settleBillDto.setSettleOrgAccount(logisticsOrgFuncInfoBean.getSettleOrgName());
        // 结算方账户(收款/付款方账户)
        settleBillDto.setSettlePartyAccount(financeManagePayConfirmReqDto.getPaymentAccountName());
        // 币种 ID (人民币为 1)
        settleBillDto.setCurrencyId(FinanceConstant.CHINA_CURRENCY);
        // 推单(true-推单,财务不可逆向操作;false-非推,财务可进行逆向操作)
        settleBillDto.setPushed(true);
        // 拉单(true-可拉单生成下游单据;false-不可拉单生成下游单据)
        settleBillDto.setPullable(false);
        // 总金额
        settleBillDto.setTotalAmmount(financeManagePayAmountReqDto.getThisPayment());
        // 已结金额
        settleBillDto.setSettledAmount(new BigDecimal(df.format(financeManagePayAmountReqDto.getThisPayment().doubleValue() + financeManagePayAmountReqDto.getApportionFee().doubleValue())));
        // 手续费
        settleBillDto.setServiceCharge(financeManagePayAmountReqDto.getApportionFee());
        // 创建人
        settleBillDto.setCreateUser(financeManagePayConfirmReqDto.getOprId());
        // 创建时间
        settleBillDto.setCreateTime(payConfirmTime);
        // 备注
        settleBillDto.setRemark(financeManagePayConfirmReqDto.getRemark());
        // 集团ID
        settleDetailDto.setGroupId(financeManagePayConfirmReqDto.getGroupId());
        // 来源单ID
        settleDetailDto.setSourceBillId(financeManagePayAmountReqDto.getId());
        // 来源单编号
        settleDetailDto.setSourceBillCode(financeManagePayEsDto.getDataSourceSheetId());
        // 来源单行号
        settleDetailDto.setSourceDetailId(1L);
        // 来源单金额
        settleDetailDto.setSourceTaxedAmount(financeManagePayEsDto.getPayableAmount());
        // 源头单ID
        settleDetailDto.setOriginalBillId(financeManagePayEsDto.getSheetId());
        // 源头单号
        settleDetailDto.setOriginalBillCode(financeManagePayEsDto.getDataSourceSheetId());
        // 源头单行号
        settleDetailDto.setOriginalDetailId(1L);
        // 总金额(收付款金额)->本次付款金额
        settleDetailDto.setTotalAmmount(financeManagePayAmountReqDto.getThisPayment());
        // 已结金额 -> 实付金额 = 本次付款金额 + 分摊手续费
        settleDetailDto.setSettledAmount(new BigDecimal(df.format(financeManagePayAmountReqDto.getThisPayment().doubleValue() + financeManagePayAmountReqDto.getApportionFee().doubleValue())));
        // 结算手续费 -> 分摊手续费
        settleDetailDto.setServiceCharge(financeManagePayAmountReqDto.getApportionFee());
        // 核算方(应收/付方)、结算方(收款/付款方)(客商档案 ID)
        if (ExpensesEnum.BUSINESS.getDocType().equals(financeManagePayEsDto.getFeeType())) {//业务费
            // 发货人ID
            settleDetailDto.setAccountParty(financeManagePayEsDto.getShipperId() == null ? 0 : financeManagePayEsDto.getShipperId());
            settleBillDto.setSettleParty(financeManagePayEsDto.getShipperId() == null ? 0 : financeManagePayEsDto.getShipperId());
            // 备注
            settleDetailDto.setRemark(financeManagePayEsDto.getInvoiceUserName());
            // 结算性质(数据字典码：BOSS028)/付款性质
            settleDetailDto.setSettleCharactor(FinanceConstant.PAYABLES_PROPERTY_OTHER_PAYABLE);
        } else {
            // 备注
            settleDetailDto.setRemark(financeManagePayConfirmReqDto.getRemark());
            // 结算性质(数据字典码：BOSS028)
            settleDetailDto.setSettleCharactor(FinanceConstant.PAYABLES_PROPERTY_PAYABLE);
            if (ExpensesEnum.FERIGHTADVANCED.getDocType().equals(financeManagePayEsDto.getFeeType())) {//垫付费
                // 发货方ID
                settleDetailDto.setAccountParty(financeManagePayEsDto.getShipperId() == null ? 0 : financeManagePayEsDto.getShipperId());
                settleBillDto.setSettleParty(financeManagePayEsDto.getShipperId() == null ? 0 : financeManagePayEsDto.getShipperId());
            } else {//其他费用
                // 收款方ID
                settleDetailDto.setAccountParty(financeManagePayEsDto.getPayeeId() == null ? 0 : financeManagePayEsDto.getPayeeId());
                settleBillDto.setSettleParty(financeManagePayEsDto.getPayeeId() == null ? 0 : financeManagePayEsDto.getPayeeId());
            }
        }
        // 核算组织(应收/付组织)
        settleDetailDto.setAccountOrg(logisticsOrgFuncInfoBean.getAccountOrgId());
        // 结算类型(收/付款类型)(档案来源 CRM)
        settleDetailDto.setSettleType(FinanceConstant.RECEIPT_PAY_TYPE_SERVICE_ID);
        // 结算方式(来源于 CRM 档案)
        settleDetailDto.setSettleMode(financeManagePayConfirmReqDto.getBillMethodId());
        // 支付方式（数据字典码:B2B002)
        settleDetailDto.setPayMode(financeManagePayConfirmReqDto.getPaymentMethodName());
        // 收款账户
        settleDetailDto.setReceiveAccount(financeManagePayConfirmReqDto.getReceiptAccountName());
        // 付款账户
        settleDetailDto.setPayAccount(financeManagePayConfirmReqDto.getPaymentAccountName());
        // 创建人
        settleBillDto.setCreateUser(financeManagePayConfirmReqDto.getOprId());
        // 创建时间
        settleBillDto.setCreateTime(payConfirmTime);
        List<SettleDetailDTO> settleDetailDtoList = Arrays.asList(settleDetailDto);
        settleBillDto.setDetails(settleDetailDtoList);
        return settleBillDto;
    }

    /**
     * 生成付款单
     * @param financeManagePayDo
     * @param financeManageCashSerialDo
     * @param financeManagePayConfirmReqDto
     * @param financeManagePayEsDto
     * @param financeManagePayAmountReqDto
     * @param settleBillDto
     * @param orgInfoDto
     * @param payConfirmTime
     */
    @ChainedTransaction(mapper = {FinanceManagePayMapper.class, FinanceManageCashSerialMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public void generatePaymentSheet(@RouteParam("FinanceManagePayMapper.companyId") FinanceManagePayDO financeManagePayDo,
                                     @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO financeManageCashSerialDo,
                                     FinanceManagePayConfirmReqDTO financeManagePayConfirmReqDto, FinanceManagePayEsDTO financeManagePayEsDto,
                                     FinanceManagePayAmountReqDTO financeManagePayAmountReqDto, SettleBillDTO settleBillDto, OrgInfoDTO orgInfoDto, Long payConfirmTime) {
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto = null;
        try {
            //调用BOSS接口生成付款单
            settleBillDto = financeCommonService.createPayInfo(settleBillDto);
            //已付金额 += 本次付款
            financeManagePayEsDto.setPaidAmount(new BigDecimal(df.format(financeManagePayEsDto.getPaidAmount().doubleValue() + financeManagePayAmountReqDto.getThisPayment().doubleValue())));
            //未付金额 = 应付金额 - 已付金额
            financeManagePayEsDto.setUnpaidAmount(new BigDecimal(df.format(financeManagePayEsDto.getPayableAmount().doubleValue() - financeManagePayEsDto.getPaidAmount().doubleValue())));
            //付款手续费 += 本次付款分摊的手续费
            financeManagePayEsDto.setPaymentFee(new BigDecimal(df.format(financeManagePayEsDto.getPaymentFee().doubleValue() + financeManagePayAmountReqDto.getApportionFee().doubleValue())));
            //实付金额 = 已付金额 + 付款手续费
            financeManagePayEsDto.setActualPaidAmount(new BigDecimal(df.format(financeManagePayEsDto.getPaidAmount().doubleValue() + financeManagePayEsDto.getPaymentFee().doubleValue())));
            //设置付款状态 未付金额大于0对应部分付款状态 等于0对应已付款状态
            setPaymentStatus(financeManagePayEsDto);

            financeManagePayEsDto.setUpdateUserId(financeManagePayConfirmReqDto.getOprId());
            financeManagePayEsDto.setUpdateUserName(financeManagePayConfirmReqDto.getOprName());
            financeManagePayEsDto.setUpdateTime(payConfirmTime);

            //初始化应付DB实体
            initFinanceManagePayDO(financeManagePayEsDto, financeManagePayDo);

            //初始化资金流水ES实体
            financeManageCashSerialEsDto = new FinanceManageCashSerialEsDTO();
            financeManageCashSerialEsDto.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL));
            financeManageCashSerialEsDto.setFinanceId(financeManagePayEsDto.getId());
            financeManageCashSerialEsDto.setGroupId(financeManagePayConfirmReqDto.getGroupId());//集团ID
            financeManageCashSerialEsDto.setType(2);//应付
            financeManageCashSerialEsDto.setCompanyId(financeManagePayEsDto.getCompanyId());
            financeManageCashSerialEsDto.setOrdersourceId(financeManagePayEsDto.getDataSourceSheetId());
            financeManageCashSerialEsDto.setCodType(financeManagePayEsDto.getSheetType());//单据类型
            financeManageCashSerialEsDto.setCodTypeName(financeManagePayEsDto.getSheetTypeName());
            financeManageCashSerialEsDto.setPaymentType(financeManagePayConfirmReqDto.getPaymentMethodId());
            financeManageCashSerialEsDto.setPaymentTypeName(financeManagePayConfirmReqDto.getPaymentMethodName());
            financeManageCashSerialEsDto.setFeeType(financeManagePayEsDto.getFeeType());
            financeManageCashSerialEsDto.setFeeTypeName(financeManagePayEsDto.getFeeTypeName());
            financeManageCashSerialEsDto.setFundAccount(financeManagePayConfirmReqDto.getPaymentAccountName());//资金账号
            financeManageCashSerialEsDto.setPayAccount(financeManagePayConfirmReqDto.getPaymentAccountName());//付款账户
            financeManageCashSerialEsDto.setReceivableBankAccount(financeManagePayConfirmReqDto.getReceiptAccountName());//收款账户
            financeManageCashSerialEsDto.setReceiptAmount(BigDecimal.ZERO);
            financeManageCashSerialEsDto.setPayeeObject(financeManagePayEsDto.getPayee());//收款方
            financeManageCashSerialEsDto.setPayAmount(settleBillDto.getSettledAmount());
            financeManageCashSerialEsDto.setReceiptPayNetworkId(financeManagePayEsDto.getPaymentNetworkId());
            financeManageCashSerialEsDto.setReceiptPayNetworkName(financeManagePayEsDto.getPaymentNetworkName());
            financeManageCashSerialEsDto.setSettleNetworkId(orgInfoDto.getAccountOrgId());//核算网点->核算组织
            financeManageCashSerialEsDto.setSettleNetworkName(orgInfoDto.getAccountOrgName());
            financeManageCashSerialEsDto.setRemark(financeManagePayConfirmReqDto.getRemark());
            financeManageCashSerialEsDto.setServiceCharge(financeManagePayAmountReqDto.getApportionFee());
            financeManageCashSerialEsDto.setSettlementMethod(financeManagePayConfirmReqDto.getBillMethodId());
            financeManageCashSerialEsDto.setSettlementMethodName(financeManagePayConfirmReqDto.getBillMethodName());
            financeManageCashSerialEsDto.setPayMethod(financeManagePayConfirmReqDto.getPaymentChannelId());
            financeManageCashSerialEsDto.setPayMethodName(financeManagePayConfirmReqDto.getPaymentChannelName());
            financeManageCashSerialEsDto.setPayCode(settleBillDto.getCode());
            financeManageCashSerialEsDto.setSubmitBillState(TransferAccountsStatusEnum.UNTRANSFER.getType());
            financeManageCashSerialEsDto.setSubmitBillStateName(TransferAccountsStatusEnum.UNTRANSFER.getName());
            financeManageCashSerialEsDto.setReceiptPayTime(financeManagePayConfirmReqDto.getPaymentTime());
            financeManageCashSerialEsDto.setCreateBillTime(payConfirmTime);
            financeManageCashSerialEsDto.setCreateTime(payConfirmTime);
            financeManageCashSerialEsDto.setCreateUserId(financeManagePayConfirmReqDto.getOprId());
            financeManageCashSerialEsDto.setCreateUserName(financeManagePayConfirmReqDto.getOprName());
            financeManageCashSerialEsDto.setStatus(FlowStatusEnum.NORMAL.getType());
            //初始化资金流水DB实体
            financeManageCashSerialDo = EntityUtils.copyProperties(financeManageCashSerialEsDto, FinanceManageCashSerialDO.class);
            //更新应付信息DB并记录资金流水
            dbFinanceManagePayService.oprPayConfirmDB(financeManagePayDo, financeManageCashSerialDo);
            //更新应付信息ES并记录资金流水
            esFinanceManageCashSerialService.insert(financeManageCashSerialEsDto);
            esFinanceManagePayService.updateEs(financeManagePayEsDto);
        } catch (Exception e) {
            log.error("创建付款单失败", e);
            //回滚ES
            if (financeManageCashSerialEsDto != null && financeManageCashSerialEsDto.getId() != null) {
                if (esFinanceManageCashSerialService.getEs(financeManageCashSerialEsDto.getId()) != null) {
                    esFinanceManageCashSerialService.deleteById(financeManageCashSerialEsDto.getId());
                }
            }
            //为了保证与BOSS服务数据一致性，出现异常后调用BOSS服务删除付款单
            handleEx(settleBillDto, financeManagePayEsDto, financeManagePayConfirmReqDto);
            throw e;
        }
    }

    /**
     * 设置付款状态
     *
     * @param financeManagePayEsDto
     */
    private void setPaymentStatus(FinanceManagePayEsDTO financeManagePayEsDto) {
        if (financeManagePayEsDto.getPaidAmount().compareTo(financeManagePayEsDto.getPayableAmount()) == 0) {
            financeManagePayEsDto.setPaymentStatus(PaymentStatusEnum.ALL_PAYMENT.getType());
            financeManagePayEsDto.setPaymentStatusName(PaymentStatusEnum.ALL_PAYMENT.getName());
        } else if (financeManagePayEsDto.getPaidAmount().compareTo(BigDecimal.ZERO) == 0) {
            financeManagePayEsDto.setPaymentStatus(PaymentStatusEnum.NO_PAYMENT.getType());
            financeManagePayEsDto.setPaymentStatusName(PaymentStatusEnum.NO_PAYMENT.getName());
        } else {
            financeManagePayEsDto.setPaymentStatus(PaymentStatusEnum.PARTLY_PAYMENT.getType());
            financeManagePayEsDto.setPaymentStatusName(PaymentStatusEnum.PARTLY_PAYMENT.getName());
        }
    }

    /**
     * 初始化应付信息DB
     *
     * @param financeManagePayEsDto
     * @param financeManagePayDo
     */
    private void initFinanceManagePayDO(FinanceManagePayEsDTO financeManagePayEsDto, FinanceManagePayDO financeManagePayDo) {
        financeManagePayDo.setId(financeManagePayEsDto.getId());
        financeManagePayDo.setCompanyId(financeManagePayEsDto.getCompanyId());
        financeManagePayDo.setPaidAmount(financeManagePayEsDto.getPaidAmount());
        financeManagePayDo.setUnpaidAmount(financeManagePayEsDto.getUnpaidAmount());
        financeManagePayDo.setPaymentFee(financeManagePayEsDto.getPaymentFee());
        financeManagePayDo.setActualPaidAmount(financeManagePayEsDto.getActualPaidAmount());
        financeManagePayDo.setPaymentStatus(financeManagePayEsDto.getPaymentStatus());
        financeManagePayDo.setUpdateUserId(financeManagePayEsDto.getUpdateUserId());
        financeManagePayDo.setUpdateTime(financeManagePayEsDto.getUpdateTime());
    }

    /**
     * 处理异常情况，保证数据一致性
     *
     * @param settleBillDto
     * @param financeManagePayEsDto
     * @param financeManagePayConfirmReqDto
     */
    private void handleEx(SettleBillDTO settleBillDto, FinanceManagePayEsDTO financeManagePayEsDto, FinanceManagePayConfirmReqDTO financeManagePayConfirmReqDto) {
        if (settleBillDto != null && settleBillDto.getCode() != null) {
            CodeOpParam opParam = new CodeOpParam();
            opParam.setTypeSeparate(2);
            opParam.setCode(settleBillDto.getCode());
            opParam.setGroupId(financeManagePayConfirmReqDto.getGroupId());
            opParam.setOpUser(financeManagePayConfirmReqDto.getOprId());
            financeCommonService.deleteBySource(opParam);
            log.info("删除付款单成功，来源单ID：" + financeManagePayEsDto.getId() + "付款单号：" + settleBillDto.getCode());
        }
    }
}
