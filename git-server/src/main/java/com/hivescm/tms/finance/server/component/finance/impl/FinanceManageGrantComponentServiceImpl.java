package com.hivescm.tms.finance.server.component.finance.impl;


import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.OrgInfoDTO;
import com.hivescm.tms.api.enums.finance.*;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceCommonService;
import com.hivescm.tms.finance.server.component.finance.FinanceManageGrantComponentService;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsGrantMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsRecycleMapper;
import com.hivescm.tms.finance.server.service.db.DbFinanceManageGrantService;
import com.hivescm.tms.finance.server.service.finance.*;
import com.hivescm.tms.finance.server.service.finance.constant.FinanceConstant;
import com.hivescm.tms.intranet.gateway.api.dto.boss.LogisticsOrgFuncInfoBean;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.InteractObjectEnum;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.ReconciliationStatus;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 货款管理-货款发放的Service实现
 *
 * @author wenxiong.jia
 * @date 2018/5/7
 */
@Slf4j
@Service
public class FinanceManageGrantComponentServiceImpl implements FinanceManageGrantComponentService {

    private static final DecimalFormat df = new DecimalFormat("#0.00");

    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private FinanceCommonService financeCommonService;
    @Autowired
    private DbFinanceManageGrantService dbFinanceManageGrantService;
    @Autowired
    private EsFinanceManageReceiptService esFinanceManageReceiptService;
    @Autowired
    private EsFinanceManageCashSerialService esFinanceManageCashSerialService;
    @Autowired
    private EsFinanceManageGoodsGrantService esFinanceManageGoodsGrantService;
    @Autowired
    private EsFinanceManageGoodsRecycleService esFinanceManageGoodsRecycleService;
    @Autowired
    private DbFinanceManageGoodsRecycleService dbFinanceManageGoodsRecycleService;

    @Override
    public Boolean grantConfirm(FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto) {
        //操作时间
        Long grantConfirmTime = Instant.now().toEpochMilli();

        FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto;
        List<FinanceManageGrantAmountReqDTO> financeManageGrantAmountReqDtoList = financeManageGrantConfirmReqDto.getFinanceManageGrantAmountReqDtoList();
        if (CollectionUtils.isNotEmpty(financeManageGrantAmountReqDtoList)) {
            FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO;
            FinanceManageGoodsGrantDO financeManageGoodsGrantDo;
            FinanceManageCashSerialDO financeManageCashSerialDo;
            FinanceManageReceiptDO financeManageReceiptDo;
            OrgInfoDTO orgInfoDto = new OrgInfoDTO();
            for (FinanceManageGrantAmountReqDTO financeManageGrantAmountReqDto : financeManageGrantAmountReqDtoList) {
                financeManageGoodsGrantEsDto = esFinanceManageGoodsGrantService.getById(financeManageGrantAmountReqDto.getId());
                if (financeManageGoodsGrantEsDto == null) {
                    log.error("信息不存在，请求的ID：" + financeManageGrantAmountReqDto.getId());
                    throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_GRANTCONFIRM, "货款发放确认异常，请求的信息不存在");
                }
                financeManageGoodsRecycleDO = new FinanceManageGoodsRecycleDO();
                financeManageGoodsGrantDo = new FinanceManageGoodsGrantDO();
                financeManageCashSerialDo = new FinanceManageCashSerialDO();
                financeManageReceiptDo = new FinanceManageReceiptDO();
                financeManageGoodsRecycleDO.setCompanyId(0L);
                financeManageGoodsGrantDo.setCompanyId(0L);//初始值，实际值从es中获取
                financeManageCashSerialDo.setCompanyId(0L);
                financeManageReceiptDo.setCompanyId(0L);
                //生成货款扣的收款单
                generateGoodsDeductReceipt(financeManageGoodsGrantDo, financeManageReceiptDo, financeManageCashSerialDo, financeManageGrantConfirmReqDto, financeManageGoodsGrantEsDto, financeManageGrantAmountReqDto, orgInfoDto, grantConfirmTime);
                //生成货款手续费的收款单
                generateGoodsFeeReceipt(financeManageGoodsGrantDo, financeManageCashSerialDo, financeManageGrantConfirmReqDto, financeManageGoodsGrantEsDto, financeManageGrantAmountReqDto, orgInfoDto, grantConfirmTime);
                //生成货款发放的付款单
                generateGrantPaymentSheet(financeManageGoodsGrantDo, financeManageCashSerialDo, financeManageGoodsRecycleDO, financeManageGrantConfirmReqDto, financeManageGoodsGrantEsDto, financeManageGrantAmountReqDto, orgInfoDto, grantConfirmTime);
            }
        }
        return true;
    }

    @Override
    @ChainedTransaction(mapper = {FinanceManageGoodsGrantMapper.class, FinanceManageCashSerialMapper.class, FinanceManageGoodsRecycleMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public Boolean cancelGrantPay(@RouteParam("FinanceManageGoodsGrantMapper.companyId") FinanceManageGoodsGrantDO financeManageGoodsGrantDo,
                                  @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO originalFinanceManageCashSerialDo,
                                  @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO newFinanceManageCashSerialDo,
                                  @RouteParam("FinanceManageGoodsRecycleMapper.companyId") FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO,
                                  FinanceManagePayCancelReqDTO financeManagePayCancelReqDto) {
        //操作时间
        Long payCancelTime = Instant.now().toEpochMilli();

        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto = esFinanceManageCashSerialService.getEs(financeManagePayCancelReqDto.getId());
        if (financeManageCashSerialEsDto == null) {
            log.error("资金流水信息不存在，请求的ID：" + financeManagePayCancelReqDto.getId());
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_GRANTCANCEL, "货款发放取消付款异常，资金流水信息不存在");
        }
        //获取付款单对应货款发放信息
        FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto = esFinanceManageGoodsGrantService.getById(financeManageCashSerialEsDto.getFinanceId());
        if (financeManageGoodsGrantEsDto == null) {
            log.error("货款发放信息不存在，请求的ID：" + financeManagePayCancelReqDto.getId());
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_GRANTCANCEL, "货款发放取消付款异常，货款发放信息不存在");
        }
        FinanceManageGoodsGrantEsDTO oldFinanceManageGoodsGrantEsDto = financeManageGoodsGrantEsDto.clone();
        //已发货款 -= 本次发放货款
        financeManageGoodsGrantEsDto.setSendAmount(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getSendAmount().doubleValue() - financeManageCashSerialEsDto.getPayAmount().doubleValue() + financeManageCashSerialEsDto.getServiceCharge().doubleValue())));
        //未发货款 = 代收货款 - 已发货款
        financeManageGoodsGrantEsDto.setUnsendAmount(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getDeliveryAmount().doubleValue() - financeManageGoodsGrantEsDto.getSendAmount().doubleValue())));
        //付款手续费 -= 本次付款分摊的手续费
        financeManageGoodsGrantEsDto.setPayFee(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getPayFee().doubleValue() - financeManageCashSerialEsDto.getServiceCharge().doubleValue())));
        //实付金额 = 已发货款 + 付款手续费 - 货款扣已结 - 货款手续费已结
        financeManageGoodsGrantEsDto.setActualAmount(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getSendAmount().doubleValue() + financeManageGoodsGrantEsDto.getPayFee().doubleValue() - financeManageGoodsGrantEsDto.getGoodsAmount().doubleValue() - financeManageGoodsGrantEsDto.getGoodsFee().doubleValue())));
        //设置发放状态 未发货款大于0对应部分发放状态 等于0对应已发放状态
        setGrantStatus(financeManageGoodsGrantEsDto);

        financeManageGoodsGrantEsDto.setUpdateUserId(financeManagePayCancelReqDto.getOprId());
        financeManageGoodsGrantEsDto.setUpdateUserName(financeManagePayCancelReqDto.getOprName());
        financeManageGoodsGrantEsDto.setUpdateTime(payCancelTime);

        //初始化货款发放信息DB实体
        initFinanceManageGoodsGrantDO(financeManageGoodsGrantEsDto, financeManageGoodsGrantDo, 1);
        //原资金流水变为已取消状态
        financeManageCashSerialEsDto.setStatus(FlowStatusEnum.CANCEL.getType());
        //初始化原资金流水DB实体
        originalFinanceManageCashSerialDo.setId(financeManageCashSerialEsDto.getId());
        originalFinanceManageCashSerialDo.setCompanyId(financeManageCashSerialEsDto.getCompanyId());
        originalFinanceManageCashSerialDo.setStatus(financeManageCashSerialEsDto.getStatus());

        FinanceManageCashSerialEsDTO newFinanceManageCashSerialEsDto = EntityUtils.copyProperties(financeManageCashSerialEsDto, FinanceManageCashSerialEsDTO.class);
        newFinanceManageCashSerialEsDto.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL));
        newFinanceManageCashSerialEsDto.setType(4);
        newFinanceManageCashSerialEsDto.setCodType(CalculationFeeEnum.DSHKFF.getType());
        newFinanceManageCashSerialEsDto.setCodTypeName(CalculationFeeEnum.DSHKFF.getName());
        newFinanceManageCashSerialEsDto.setPayAmount(new BigDecimal("-" + df.format(financeManageCashSerialEsDto.getPayAmount().doubleValue())));
        newFinanceManageCashSerialEsDto.setRemark("【" + financeManagePayCancelReqDto.getOprName() + "】取消付款");
        newFinanceManageCashSerialEsDto.setServiceCharge(new BigDecimal("-" + df.format(financeManageCashSerialEsDto.getServiceCharge().doubleValue())));
        newFinanceManageCashSerialEsDto.setCreateBillTime(payCancelTime);
        newFinanceManageCashSerialEsDto.setCreateTime(payCancelTime);
        newFinanceManageCashSerialEsDto.setCreateUserId(financeManagePayCancelReqDto.getOprId());
        newFinanceManageCashSerialEsDto.setCreateUserName(financeManagePayCancelReqDto.getOprName());
        newFinanceManageCashSerialEsDto.setStatus(FlowStatusEnum.CANCELLATION.getType());
        newFinanceManageCashSerialDo = EntityUtils.copyProperties(newFinanceManageCashSerialEsDto, FinanceManageCashSerialDO.class);
        //同步发放信息对应回收信息的发放状态
        FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO  = esFinanceManageGoodsRecycleService.findByWaybillId(financeManageGoodsGrantEsDto.getWaybillId());
        FinanceManageGoodsRecycleEsDTO oldFinanceManageGoodsRecycleEsDTO = financeManageGoodsRecycleEsDTO.clone();
        financeManageGoodsRecycleEsDTO.setSendStatus(financeManageGoodsGrantEsDto.getSendStatus());
        financeManageGoodsRecycleEsDTO.setSendStatusName(financeManageGoodsGrantEsDto.getSendStatusName());
        financeManageGoodsRecycleEsDTO.setUpdateTime(financeManageGoodsGrantEsDto.getUpdateTime());
        financeManageGoodsRecycleEsDTO.setUpdateUserId(financeManageGoodsGrantEsDto.getUpdateUserId());
        financeManageGoodsRecycleEsDTO.setUpdateUserName(financeManageGoodsGrantEsDto.getUpdateUserName());
        intFinanceManageGoodsRecycleDO(financeManageGoodsRecycleEsDTO, financeManageGoodsRecycleDO);
        try {
            //更新DB并修改原流水、记录新流水
            dbFinanceManageGrantService.oprGrantConfirmDB(financeManageGoodsGrantDo, originalFinanceManageCashSerialDo, newFinanceManageCashSerialDo);
            dbFinanceManageGoodsRecycleService.update(financeManageGoodsRecycleDO);
            //更新ES并修改原流水、记录新流水
            esFinanceManageCashSerialService.insert(newFinanceManageCashSerialEsDto);
            esFinanceManageGoodsGrantService.update(financeManageGoodsGrantEsDto);
            esFinanceManageCashSerialService.updateEs(financeManageCashSerialEsDto);
            esFinanceManageGoodsRecycleService.update(financeManageGoodsRecycleEsDTO);
            CodeOpParam opParam = new CodeOpParam();
            opParam.setTypeSeparate(2);
            opParam.setCode(financeManageCashSerialEsDto.getPayCode());
            opParam.setGroupId(financeManageCashSerialEsDto.getGroupId());
            opParam.setOpUser(financeManagePayCancelReqDto.getOprId());
            //调用BOSS接口取消付款单
            financeCommonService.deleteBySource(opParam);
            return true;
        } catch (Exception e) {
            log.error("取消付款单失败，来源单ID：" + financeManageGoodsGrantEsDto.getId());
            //回滚ES
            if (esFinanceManageCashSerialService.getEs(newFinanceManageCashSerialEsDto.getId()) != null) {
                esFinanceManageCashSerialService.deleteById(newFinanceManageCashSerialEsDto.getId());
                financeManageCashSerialEsDto.setStatus(FlowStatusEnum.NORMAL.getType());
                esFinanceManageCashSerialService.updateEs(financeManageCashSerialEsDto);
                esFinanceManageGoodsGrantService.update(oldFinanceManageGoodsGrantEsDto);
                esFinanceManageGoodsRecycleService.update(oldFinanceManageGoodsRecycleEsDTO);
            }
            throw e;
        }
    }

    @Override
    @ChainedTransaction(mapper = {FinanceManageGoodsGrantMapper.class, FinanceManageCashSerialMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public Boolean cancelGoodsFeeReceipt(@RouteParam("FinanceManageGoodsGrantMapper.companyId") FinanceManageGoodsGrantDO financeManageGoodsGrantDo,
                                         @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO originalFinanceManageCashSerialDo,
                                         @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO newFinanceManageCashSerialDo,
                                         FinanceManagePayCancelReqDTO financeManagePayCancelReqDto) {
        //操作时间
        Long receiptCancelTime = Instant.now().toEpochMilli();

        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto = esFinanceManageCashSerialService.getEs(financeManagePayCancelReqDto.getId());
        if (financeManageCashSerialEsDto == null) {
            log.error("资金流水信息不存在，请求的ID：" + financeManagePayCancelReqDto.getId());
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_GRANTCANCEL, "货款手续费取消收款异常，资金流水信息不存在");
        }
        //获取收款单对应货款发放信息
        FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto = esFinanceManageGoodsGrantService.getById(financeManageCashSerialEsDto.getFinanceId());
        if (financeManageGoodsGrantEsDto == null) {
            log.error("货款发放信息不存在，请求的ID：" + financeManagePayCancelReqDto.getId());
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_GRANTCANCEL, "货款手续费取消收款异常，货款发放信息不存在");
        }
        FinanceManageGoodsGrantEsDTO oldFinanceManageGoodsGrantEsDto = financeManageGoodsGrantEsDto.clone();
        //已结货款手续费 -= 本次实结货款手续费
        financeManageGoodsGrantEsDto.setGoodsFee(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getGoodsFee().doubleValue() - financeManageCashSerialEsDto.getReceiptAmount().doubleValue())));
        //未结货款手续费 = 货款手续费 - 已结货款手续费
        financeManageGoodsGrantEsDto.setUngoodsFee(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getDeliveryGoodsAmount().doubleValue() - financeManageGoodsGrantEsDto.getGoodsFee().doubleValue())));
        //实付金额 = 已发货款 + 付款手续费 - 货款扣已结 - 货款手续费已结
        financeManageGoodsGrantEsDto.setActualAmount(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getSendAmount().doubleValue() + financeManageGoodsGrantEsDto.getPayFee().doubleValue() - financeManageGoodsGrantEsDto.getGoodsAmount().doubleValue() - financeManageGoodsGrantEsDto.getGoodsFee().doubleValue())));

        financeManageGoodsGrantEsDto.setUpdateUserId(financeManagePayCancelReqDto.getOprId());
        financeManageGoodsGrantEsDto.setUpdateUserName(financeManagePayCancelReqDto.getOprName());
        financeManageGoodsGrantEsDto.setUpdateTime(receiptCancelTime);

        //初始化货款发放信息DB实体
        initFinanceManageGoodsGrantDO(financeManageGoodsGrantEsDto, financeManageGoodsGrantDo, 3);
        //原资金流水变为已取消状态
        financeManageCashSerialEsDto.setStatus(FlowStatusEnum.CANCEL.getType());
        //初始化原资金流水DB实体
        originalFinanceManageCashSerialDo.setId(financeManageCashSerialEsDto.getId());
        originalFinanceManageCashSerialDo.setCompanyId(financeManageCashSerialEsDto.getCompanyId());
        originalFinanceManageCashSerialDo.setStatus(financeManageCashSerialEsDto.getStatus());

        FinanceManageCashSerialEsDTO newFinanceManageCashSerialEsDto = EntityUtils.copyProperties(financeManageCashSerialEsDto, FinanceManageCashSerialEsDTO.class);
        newFinanceManageCashSerialEsDto.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL));
        newFinanceManageCashSerialEsDto.setType(4);
        newFinanceManageCashSerialEsDto.setReceiptAmount(new BigDecimal("-" + df.format(financeManageCashSerialEsDto.getReceiptAmount().doubleValue())));
        newFinanceManageCashSerialEsDto.setRemark("【" + financeManagePayCancelReqDto.getOprName() + "】取消收款");
        newFinanceManageCashSerialEsDto.setCreateBillTime(receiptCancelTime);
        newFinanceManageCashSerialEsDto.setCreateTime(receiptCancelTime);
        newFinanceManageCashSerialEsDto.setCreateUserId(financeManagePayCancelReqDto.getOprId());
        newFinanceManageCashSerialEsDto.setCreateUserName(financeManagePayCancelReqDto.getOprName());
        newFinanceManageCashSerialEsDto.setStatus(FlowStatusEnum.CANCELLATION.getType());
        newFinanceManageCashSerialDo = EntityUtils.copyProperties(newFinanceManageCashSerialEsDto, FinanceManageCashSerialDO.class);
        try {
            //更新DB并修改原流水、记录新流水
            dbFinanceManageGrantService.oprGrantConfirmDB(financeManageGoodsGrantDo, originalFinanceManageCashSerialDo, newFinanceManageCashSerialDo);
            //更新ES并修改原流水、记录新流水
            esFinanceManageCashSerialService.insert(newFinanceManageCashSerialEsDto);
            esFinanceManageGoodsGrantService.update(financeManageGoodsGrantEsDto);
            esFinanceManageCashSerialService.updateEs(financeManageCashSerialEsDto);
            CodeOpParam opParam = new CodeOpParam();
            opParam.setTypeSeparate(1);
            opParam.setCode(financeManageCashSerialEsDto.getReceiptCode());
            opParam.setGroupId(financeManageCashSerialEsDto.getGroupId());
            opParam.setOpUser(financeManagePayCancelReqDto.getOprId());
            //调用BOSS接口取消收款单
            financeCommonService.deleteBySource(opParam);
            return true;
        } catch (Exception e) {
            log.error("取消收款单失败，来源单ID：" + financeManageGoodsGrantEsDto.getId());
            //回滚ES
            if (esFinanceManageCashSerialService.getEs(newFinanceManageCashSerialEsDto.getId()) != null) {
                esFinanceManageCashSerialService.deleteById(newFinanceManageCashSerialEsDto.getId());
                financeManageCashSerialEsDto.setStatus(FlowStatusEnum.NORMAL.getType());
                esFinanceManageCashSerialService.updateEs(financeManageCashSerialEsDto);
                esFinanceManageGoodsGrantService.deleteById(oldFinanceManageGoodsGrantEsDto.getId());
                esFinanceManageGoodsGrantService.insert(oldFinanceManageGoodsGrantEsDto);
            }
            throw e;
        }
    }

    @Override
    public Boolean incomeConfirm(FinanceManageGrantCommonReqDTO financeManageGrantCommonReqDto) {
        //操作时间
        Long incomeConfirmTime = Instant.now().toEpochMilli();

        List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantEsDtoList = esFinanceManageGoodsGrantService.getEsList(financeManageGrantCommonReqDto.getIds());
        if (CollectionUtils.isEmpty(financeManageGoodsGrantEsDtoList)) {
            log.error("货款发放信息不存在，请求的ids：" + financeManageGrantCommonReqDto.getIds());
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_RECEIPTCONFIRM, "进账确认异常，货款发放信息不存在");
        }

        FinanceManageCashSerialDO financeManageCashSerialDo;
        FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto;
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto;
        List<Long> idList = new ArrayList<>();
        List<FinanceManageCashSerialDO> financeManageCashSerialDoList = new ArrayList<>();
        List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDtoList = new ArrayList<>();
        //批量生成分布式ID
        List<Long> ids = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL,
                financeManageGoodsGrantEsDtoList.size());
        LogisticsOrgFuncInfoBean logisticsOrgFuncInfoBean = financeCommonService.getOrgId(financeManageGrantCommonReqDto.getCurNetworkId());
        for (int i = 0; i < financeManageGoodsGrantEsDtoList.size(); i++) {
            financeManageGoodsGrantEsDto = financeManageGoodsGrantEsDtoList.get(i);
            financeManageGoodsGrantEsDto.setAcountStatus(ReceiptsStatusEnum.RECEIPTSED.getType());
            financeManageGoodsGrantEsDto.setAcountStatusName(ReceiptsStatusEnum.RECEIPTSED.getName());
            financeManageGoodsGrantEsDto.setConfirmAccountUserId(financeManageGrantCommonReqDto.getOprId());
            financeManageGoodsGrantEsDto.setConfirmAccountUserName(financeManageGrantCommonReqDto.getOprName());
            financeManageGoodsGrantEsDto.setConfirmAccountTime(incomeConfirmTime);
            financeManageGoodsGrantEsDto.setUpdateUserId(financeManageGrantCommonReqDto.getOprId());
            financeManageGoodsGrantEsDto.setUpdateUserName(financeManageGrantCommonReqDto.getOprName());
            financeManageGoodsGrantEsDto.setUpdateTime(incomeConfirmTime);
            idList.add(financeManageGoodsGrantEsDto.getId());
            financeManageCashSerialEsDto = initFinanceManageCashSerialEsDTO(ids.get(i), financeManageGoodsGrantEsDto, financeManageGrantCommonReqDto, logisticsOrgFuncInfoBean, incomeConfirmTime);
            financeManageCashSerialDo = EntityUtils.copyProperties(financeManageCashSerialEsDto, FinanceManageCashSerialDO.class);
            financeManageCashSerialEsDtoList.add(financeManageCashSerialEsDto);
            financeManageCashSerialDoList.add(financeManageCashSerialDo);
        }
        FinanceManageGoodsGrantDO financeManageGoodsGrantDo = new FinanceManageGoodsGrantDO();
        financeManageGoodsGrantDo.setIdList(idList);
        financeManageGoodsGrantDo.setCompanyId(financeManageGoodsGrantEsDtoList.get(0).getCompanyId());
        financeManageGoodsGrantDo.setAcountStatus(financeManageGoodsGrantEsDtoList.get(0).getAcountStatus());
        financeManageGoodsGrantDo.setConfirmAccountUserId(financeManageGoodsGrantEsDtoList.get(0).getConfirmAccountUserId());
        financeManageGoodsGrantDo.setConfirmAccountTime(financeManageGoodsGrantEsDtoList.get(0).getConfirmAccountTime());
        financeManageGoodsGrantDo.setUpdateUserId(financeManageGoodsGrantEsDtoList.get(0).getUpdateUserId());
        financeManageGoodsGrantDo.setUpdateTime(financeManageGoodsGrantEsDtoList.get(0).getUpdateTime());
        // 更新DB
        boolean db = dbFinanceManageGrantService.oprIncomeConfirmDB(financeManageGoodsGrantDo, financeManageCashSerialDoList);
        // 更新ES
        boolean es = esFinanceManageGoodsGrantService.updateBatchEs(financeManageGoodsGrantEsDtoList)
                && esFinanceManageCashSerialService.insertBatch(financeManageCashSerialEsDtoList);

        return db && es;
    }

    /**
     * 生成货款发放付款单
     * @param financeManageGoodsGrantDo
     * @param financeManageCashSerialDo
     * @param financeManageGoodsRecycleDO
     * @param financeManageGrantConfirmReqDto
     * @param financeManageGoodsGrantEsDto
     * @param financeManageGrantAmountReqDto
     * @param orgInfoDto
     * @param grantConfirmTime
     */
    @ChainedTransaction(mapper = {FinanceManageGoodsGrantMapper.class, FinanceManageCashSerialMapper.class, FinanceManageGoodsRecycleMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public void generateGrantPaymentSheet(@RouteParam("FinanceManageGoodsGrantMapper.companyId") FinanceManageGoodsGrantDO financeManageGoodsGrantDo,
                                          @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO financeManageCashSerialDo,
                                          @RouteParam("FinanceManageGoodsRecycleMapper.companyId") FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO,
                                          FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto,
                                          FinanceManageGrantAmountReqDTO financeManageGrantAmountReqDto, OrgInfoDTO orgInfoDto, Long grantConfirmTime) {
        SettleBillDTO settleBillDto = null;
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto = null;
        FinanceManageGoodsGrantEsDTO oldFinanceManageGoodsGrantEsDto = null;
        try {
            //本次发放货款金额大于0时生成货款发放的付款单
            if (financeManageGrantAmountReqDto.getThisSendAmount() != null && financeManageGrantAmountReqDto.getThisSendAmount().compareTo(BigDecimal.ZERO) > 0) {
                oldFinanceManageGoodsGrantEsDto = financeManageGoodsGrantEsDto.clone();
                //组装请求DTO
                settleBillDto = buildReqDTO(financeManageGrantConfirmReqDto, financeManageGoodsGrantEsDto, financeManageGrantAmountReqDto, orgInfoDto, grantConfirmTime);
                //调用BOSS接口生成付款单
                settleBillDto = financeCommonService.createPayInfo(settleBillDto);
                //初始化货款发放ES实体
                initFinanceManageGoodsGrantEsDTO(financeManageGrantConfirmReqDto, financeManageGrantAmountReqDto, financeManageGoodsGrantEsDto, grantConfirmTime, 1);
                //初始化货款发放DB实体
                initFinanceManageGoodsGrantDO(financeManageGoodsGrantEsDto, financeManageGoodsGrantDo, 1);
                //初始化资金流水ES实体
                financeManageCashSerialEsDto = initFinanceManageCashSerialEsDTO(settleBillDto, financeManageGrantConfirmReqDto, financeManageGrantAmountReqDto, financeManageGoodsGrantEsDto, orgInfoDto, grantConfirmTime, 1);
                //初始化资金流水DB实体
                financeManageCashSerialDo = EntityUtils.copyProperties(financeManageCashSerialEsDto, FinanceManageCashSerialDO.class);

                //同步发放信息对应回收信息的发放状态
                FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO  = esFinanceManageGoodsRecycleService.findByWaybillId(financeManageGoodsGrantEsDto.getWaybillId());
                financeManageGoodsRecycleEsDTO.setSendStatus(financeManageGoodsGrantEsDto.getSendStatus());
                financeManageGoodsRecycleEsDTO.setSendStatusName(financeManageGoodsGrantEsDto.getSendStatusName());
                financeManageGoodsRecycleEsDTO.setUpdateTime(financeManageGoodsGrantEsDto.getUpdateTime());
                financeManageGoodsRecycleEsDTO.setUpdateUserId(financeManageGoodsGrantEsDto.getUpdateUserId());
                financeManageGoodsRecycleEsDTO.setUpdateUserName(financeManageGoodsGrantEsDto.getUpdateUserName());
                intFinanceManageGoodsRecycleDO(financeManageGoodsRecycleEsDTO, financeManageGoodsRecycleDO);

                //更新货款发放信息DB并记录资金流水
                dbFinanceManageGrantService.oprGrantConfirmDB(financeManageGoodsGrantDo, financeManageCashSerialDo);
                dbFinanceManageGoodsRecycleService.update(financeManageGoodsRecycleDO);
                //更新货款发放信息ES并记录资金流水
                esFinanceManageCashSerialService.insert(financeManageCashSerialEsDto);
                esFinanceManageGoodsGrantService.update(financeManageGoodsGrantEsDto);
                esFinanceManageGoodsRecycleService.update(financeManageGoodsRecycleEsDTO);
            }
        } catch (Exception e) {
            log.error("创建付款单失败", e);
            //回滚ES
            if (financeManageCashSerialEsDto != null && financeManageCashSerialEsDto.getId() != null) {
                if (esFinanceManageCashSerialService.getEs(financeManageCashSerialEsDto.getId()) != null) {
                    esFinanceManageCashSerialService.deleteById(financeManageCashSerialEsDto.getId());
                    if (oldFinanceManageGoodsGrantEsDto != null) {
                        esFinanceManageGoodsGrantService.update(oldFinanceManageGoodsGrantEsDto);
                    }
                }
            }
            //为了保证与BOSS服务数据一致性，出现异常后调用BOSS服务删除付款单
            handleEx(settleBillDto, financeManageGoodsGrantEsDto, financeManageGrantConfirmReqDto, 2);
            throw e;
        }
    }

    /**
     * 生成货款扣的收款单
     * @param financeManageGoodsGrantDo
     * @param financeManageReceiptDo
     * @param financeManageCashSerialDo
     * @param financeManageGrantConfirmReqDto
     * @param financeManageGoodsGrantEsDto
     * @param financeManageGrantAmountReqDto
     * @param orgInfoDto
     * @param grantConfirmTime
     */
    @ChainedTransaction(mapper = {FinanceManageGoodsGrantMapper.class, FinanceManageCashSerialMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public void generateGoodsDeductReceipt(@RouteParam("FinanceManageGoodsGrantMapper.companyId") FinanceManageGoodsGrantDO financeManageGoodsGrantDo,
                                           @RouteParam("FinanceManageReceiptMapper.companyId") FinanceManageReceiptDO financeManageReceiptDo,
                                           @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO financeManageCashSerialDo,
                                           FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto,
                                           FinanceManageGrantAmountReqDTO financeManageGrantAmountReqDto, OrgInfoDTO orgInfoDto, Long grantConfirmTime) {
        SettleBillDTO settleBillDto = null;
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto = null;
        FinanceManageReceiptEsDTO oldFinanceManageReceiptEsDto = null;
        try {
            //本次实结货款扣金额大于0时生成货款扣的收款单
            if (financeManageGrantAmountReqDto.getThisActualGoodsAmount() != null && financeManageGrantAmountReqDto.getThisActualGoodsAmount().compareTo(BigDecimal.ZERO) > 0) {
                //组装请求DTO
                settleBillDto = buildReceiptReqDTO(financeManageGrantConfirmReqDto, financeManageGrantAmountReqDto, financeManageGoodsGrantEsDto, orgInfoDto, grantConfirmTime, 2);
                //调用BOSS接口生成收款单
                settleBillDto = financeCommonService.createPayInfo(settleBillDto);
                //初始化货款发放ES实体
                initFinanceManageGoodsGrantEsDTO(financeManageGrantConfirmReqDto, financeManageGrantAmountReqDto, financeManageGoodsGrantEsDto, grantConfirmTime, 2);
                //初始化货款发放DB实体
                initFinanceManageGoodsGrantDO(financeManageGoodsGrantEsDto, financeManageGoodsGrantDo, 2);
                //初始化资金流水ES实体
                financeManageCashSerialEsDto = initFinanceManageCashSerialEsDTO(settleBillDto, financeManageGrantConfirmReqDto, financeManageGrantAmountReqDto, financeManageGoodsGrantEsDto, orgInfoDto, grantConfirmTime, 2);
                //初始化资金流水DB实体
                financeManageCashSerialDo = EntityUtils.copyProperties(financeManageCashSerialEsDto, FinanceManageCashSerialDO.class);
                financeManageCashSerialDo.setCreateTime(financeManageCashSerialDo.getCreateBillTime());
                //货款扣关联应收信息，根据运单号和结算方式关联
                FinanceManageReceiptReqDTO financeManageReceiptReqDto = new FinanceManageReceiptReqDTO();
                financeManageReceiptReqDto.setCodeType(CodeTypeEnum.YD.getType());
                financeManageReceiptReqDto.setOrderSourceId(financeManageGoodsGrantEsDto.getOrderSourceCode());
                financeManageReceiptReqDto.setPayWay(PayWayEnum.PAYMENTDUCTION.getType());
                FinanceManageReceiptEsDTO financeManageReceiptEsDto = esFinanceManageReceiptService.findFinanceManageReceipt(financeManageReceiptReqDto);
                if (financeManageReceiptEsDto == null) {
                    log.error("货款扣关联的应收信息不存在，运单号：" + financeManageGoodsGrantEsDto.getOrderSourceCode());
                    throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_GRANTCONFIRM, "货款发放确认异常，货款扣关联的应收信息不存在");
                }
                oldFinanceManageReceiptEsDto = financeManageReceiptEsDto.clone();
                //应收信息的已收金额 += 本次实结货款扣
                financeManageReceiptEsDto.setReceiptedAmount(new BigDecimal(df.format(financeManageReceiptEsDto.getReceiptedAmount().doubleValue() + financeManageGrantAmountReqDto.getThisActualGoodsAmount().doubleValue())));
                //应收信息的未收金额 = 应收金额 - 已收金额
                financeManageReceiptEsDto.setUnreceiptAmount(new BigDecimal(df.format(financeManageReceiptEsDto.getReceiptAmount().doubleValue() - financeManageReceiptEsDto.getReceiptedAmount().doubleValue())));
                //设置应收信息状态
                setDeliveryStatus(financeManageReceiptEsDto);
                financeManageReceiptEsDto.setUpdateUserId(financeManageGrantConfirmReqDto.getOprId());
                financeManageReceiptEsDto.setUpdateTime(grantConfirmTime);
                //初始化应收信息DB实体
                initFinanceManageReceiptDO(financeManageReceiptEsDto, financeManageReceiptDo);
                //更新货款发放信息同时更新货款扣关联的应收信息DB并记录资金流水
                dbFinanceManageGrantService.oprGrantConfirmDB(financeManageGoodsGrantDo, financeManageReceiptDo, financeManageCashSerialDo);
                //更新货款发放信息同时更新货款扣关联的应收信息ES并记录资金流水
                esFinanceManageCashSerialService.insert(financeManageCashSerialEsDto);
                esFinanceManageReceiptService.updateEs(financeManageReceiptEsDto);
                esFinanceManageGoodsGrantService.update(financeManageGoodsGrantEsDto);
            }
        } catch (Exception e) {
            log.error("创建货款扣收款单失败", e);
            //回滚ES
            if (financeManageCashSerialEsDto != null && financeManageCashSerialEsDto.getId() != null) {
                if (esFinanceManageCashSerialService.getEs(financeManageCashSerialEsDto.getId()) != null) {
                    esFinanceManageCashSerialService.deleteById(financeManageCashSerialEsDto.getId());
                    esFinanceManageReceiptService.deleteById(oldFinanceManageReceiptEsDto.getId());
                    esFinanceManageReceiptService.insertFinanceManageReceiptEsDTO(oldFinanceManageReceiptEsDto);
                }
            }
            //为了保证与BOSS服务数据一致性，出现异常后调用BOSS服务删除付款单
            handleEx(settleBillDto, financeManageGoodsGrantEsDto, financeManageGrantConfirmReqDto, 1);
            throw e;
        }
    }

    /**
     * 生成货款手续费的收款单
     * @param financeManageGoodsGrantDo
     * @param financeManageCashSerialDo
     * @param financeManageGrantConfirmReqDto
     * @param financeManageGoodsGrantEsDto
     * @param financeManageGrantAmountReqDto
     * @param orgInfoDto
     * @param grantConfirmTime
     */
    @ChainedTransaction(mapper = {FinanceManageGoodsGrantMapper.class, FinanceManageCashSerialMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public void generateGoodsFeeReceipt(@RouteParam("FinanceManageGoodsGrantMapper.companyId") FinanceManageGoodsGrantDO financeManageGoodsGrantDo,
                                        @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO financeManageCashSerialDo,
                                        FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto,
                                        FinanceManageGrantAmountReqDTO financeManageGrantAmountReqDto, OrgInfoDTO orgInfoDto, Long grantConfirmTime) {
        SettleBillDTO settleBillDto = null;
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto = null;
        try {
            //本次实结货款手续费金额大于0时生成货款手续费的收款单
            if (financeManageGrantAmountReqDto.getThisActualGoodsFee() != null && financeManageGrantAmountReqDto.getThisActualGoodsFee().compareTo(BigDecimal.ZERO) > 0) {
                //组装请求DTO
                settleBillDto = buildReceiptReqDTO(financeManageGrantConfirmReqDto, financeManageGrantAmountReqDto, financeManageGoodsGrantEsDto, orgInfoDto, grantConfirmTime, 3);
                //调用BOSS接口生成货款手续费收款单
                settleBillDto = financeCommonService.createPayInfo(settleBillDto);
                //初始化货款发放ES实体
                initFinanceManageGoodsGrantEsDTO(financeManageGrantConfirmReqDto, financeManageGrantAmountReqDto, financeManageGoodsGrantEsDto, grantConfirmTime, 3);
                //初始化货款发放DB实体
                initFinanceManageGoodsGrantDO(financeManageGoodsGrantEsDto, financeManageGoodsGrantDo, 3);
                //初始化资金流水ES实体
                financeManageCashSerialEsDto = initFinanceManageCashSerialEsDTO(settleBillDto, financeManageGrantConfirmReqDto, financeManageGrantAmountReqDto, financeManageGoodsGrantEsDto, orgInfoDto, grantConfirmTime, 3);
                //初始化资金流水DB实体
                financeManageCashSerialDo = EntityUtils.copyProperties(financeManageCashSerialEsDto, FinanceManageCashSerialDO.class);
                financeManageCashSerialDo.setCreateTime(financeManageCashSerialDo.getCreateBillTime());
                //更新货款发放信息DB并记录资金流水
                dbFinanceManageGrantService.oprGrantConfirmDB(financeManageGoodsGrantDo, financeManageCashSerialDo);
                //更新货款发放信息ES并记录资金流水
                esFinanceManageCashSerialService.insert(financeManageCashSerialEsDto);
                esFinanceManageGoodsGrantService.update(financeManageGoodsGrantEsDto);
            }
        } catch (Exception e) {
            log.error("创建货款手续费收款单失败", e);
            //回滚ES
            if (financeManageCashSerialEsDto != null && financeManageCashSerialEsDto.getId() != null) {
                if (esFinanceManageCashSerialService.getEs(financeManageCashSerialEsDto.getId()) != null) {
                    esFinanceManageCashSerialService.deleteById(financeManageCashSerialEsDto.getId());
                }
            }
            //为了保证与BOSS服务数据一致性，出现异常后调用BOSS服务删除付款单
            handleEx(settleBillDto, financeManageGoodsGrantEsDto, financeManageGrantConfirmReqDto, 1);
            throw e;
        }
    }

    /**
     * 组装请求创建付款单的DTO
     * @param financeManageGrantConfirmReqDto
     * @param financeManageGoodsGrantEsDto
     * @param financeManageGrantAmountReqDto
     * @param orgInfoDto
     * @param grantConfirmTime
     * @return
     */
    private SettleBillDTO buildReqDTO(FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto,
                                      FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto,
                                      FinanceManageGrantAmountReqDTO financeManageGrantAmountReqDto,
                                      OrgInfoDTO orgInfoDto, Long grantConfirmTime) {
        //表头
        SettleBillDTO settleBillDto = new SettleBillDTO();
        //表体
        SettleDetailDTO settleDetailDto = new SettleDetailDTO();
        // 类型区分(1收款;2付款)
        settleBillDto.setTypeSeparate(2);
        // 集团 ID
        settleBillDto.setGroupId(financeManageGrantConfirmReqDto.getGroupId());
        // 付款单类型(10001 采购付款单;10002 销售退货付款单;10003 代付货款付款单;10004 其他付款单;10005 服务付款单)
        settleBillDto.setBillType(Long.valueOf(FinanceConstant.PAY_BILL_TYPE + ""));
        // 来源单据类型(数据字典 BOSS030)
        settleBillDto.setSourceBillType(CalculationFeeEnum.YD.getDocType());
        // 源头单据类型
        settleDetailDto.setOriginalBillType(CalculationFeeEnum.YD.getDocType());
        // 查询物流组织信息
        LogisticsOrgFuncInfoBean logisticsOrgFuncInfoBean = financeCommonService.getOrgId(financeManageGoodsGrantEsDto.getInvoiceOrgId());

        //获取核算组织信息
        orgInfoDto.setAccountOrgId(logisticsOrgFuncInfoBean.getAccountOrgId());
        orgInfoDto.setAccountOrgName(logisticsOrgFuncInfoBean.getAccountOrgName());

        // 往来对象类型
        settleBillDto.setCooperationType(InteractObjectEnum.CUSTOMNER.code());
        // 单据日期
        settleBillDto.setBillDate(grantConfirmTime);
        // 单据状态
        settleBillDto.setBillStatus(Status.SAVED);
        // 结算组织(收款/付款组织)
        settleBillDto.setSettleOrg(logisticsOrgFuncInfoBean.getSettleOrgId());
        // 结算组织账户(收款/付款组织账号)
        settleBillDto.setSettleOrgAccount(logisticsOrgFuncInfoBean.getSettleOrgName());
        // 结算方账户(收款/付款方账户)
        settleBillDto.setSettlePartyAccount(financeManageGrantConfirmReqDto.getPaymentAccountName());
        // 币种 ID (人民币为 1)
        settleBillDto.setCurrencyId(FinanceConstant.CHINA_CURRENCY);
        // 推单(true-推单,财务不可逆向操作;false-非推,财务可进行逆向操作)
        settleBillDto.setPushed(true);
        // 拉单(true-可拉单生成下游单据;false-不可拉单生成下游单据)
        settleBillDto.setPullable(false);
        // 总金额
        settleBillDto.setTotalAmmount(financeManageGrantAmountReqDto.getThisSendAmount());
        // 已结金额
        settleBillDto.setSettledAmount(new BigDecimal(df.format(financeManageGrantAmountReqDto.getThisSendAmount().doubleValue() + financeManageGrantAmountReqDto.getThisPayFee().doubleValue())));
        // 手续费
        settleBillDto.setServiceCharge(financeManageGrantAmountReqDto.getThisPayFee());
        // 创建人
        settleBillDto.setCreateUser(financeManageGrantConfirmReqDto.getOprId());
        // 创建时间
        settleBillDto.setCreateTime(grantConfirmTime);
        // 备注
        settleBillDto.setRemark(financeManageGrantConfirmReqDto.getRemark());
        // 集团ID
        settleDetailDto.setGroupId(financeManageGrantConfirmReqDto.getGroupId());
        // 来源单ID
        settleDetailDto.setSourceBillId(financeManageGrantAmountReqDto.getId());
        // 来源单编号
        settleDetailDto.setSourceBillCode(financeManageGoodsGrantEsDto.getOrderSourceCode());
        // 来源单行号
        settleDetailDto.setSourceDetailId(1L);
        // 来源单金额
        settleDetailDto.setSourceTaxedAmount(financeManageGoodsGrantEsDto.getDeliveryAmount());
        // 源头单ID
        settleDetailDto.setOriginalBillId(financeManageGoodsGrantEsDto.getWaybillId());
        // 源头单号
        settleDetailDto.setOriginalBillCode(financeManageGoodsGrantEsDto.getOrderSourceCode());
        // 源头单行号
        settleDetailDto.setOriginalDetailId(1L);
        // 总金额(收付款金额)->本次付款金额
        settleDetailDto.setTotalAmmount(financeManageGrantAmountReqDto.getThisSendAmount());
        // 已结金额 -> 实付金额 = 本次付款金额 + 分摊手续费
        settleDetailDto.setSettledAmount(new BigDecimal(df.format(financeManageGrantAmountReqDto.getThisSendAmount().doubleValue() + financeManageGrantAmountReqDto.getThisPayFee().doubleValue())));
        // 结算手续费 -> 分摊手续费
        settleDetailDto.setServiceCharge(financeManageGrantAmountReqDto.getThisPayFee());
        // 创建人
        settleBillDto.setCreateUser(financeManageGrantConfirmReqDto.getOprId());
        // 备注
        settleDetailDto.setRemark(financeManageGrantConfirmReqDto.getRemark());
        // 结算性质(数据字典码：BOSS028)
        settleDetailDto.setSettleCharactor(FinanceConstant.PAYABLES_PROPERTY_PAYABLE);
        // 核算方(应收/付方)
        settleDetailDto.setAccountParty(financeManageGoodsGrantEsDto.getInvoiceCustomerVipId() == null ? 0 : financeManageGoodsGrantEsDto.getInvoiceCustomerVipId());
        // 结算方(收款/付款方)(客商档案 ID)
        settleBillDto.setSettleParty(financeManageGoodsGrantEsDto.getShipperId() == null ? 0 : financeManageGoodsGrantEsDto.getShipperId());
        // 核算组织(应收/付组织)
        settleDetailDto.setAccountOrg(logisticsOrgFuncInfoBean.getAccountOrgId());
        // 结算类型(收/付款类型)(档案来源 CRM)
        settleDetailDto.setSettleType(FinanceConstant.RECEIPT_PAY_TYPE_SERVICE_ID);
        // 结算方式(来源于 CRM 档案)
        settleDetailDto.setSettleMode(financeManageGrantConfirmReqDto.getBillMethodId());
        // 支付方式（数据字典码:B2B002)
        settleDetailDto.setPayMode(financeManageGrantConfirmReqDto.getPaymentMethodName());
        // 收款账户
        settleDetailDto.setReceiveAccount(financeManageGrantConfirmReqDto.getReceiptAccountName());
        // 付款账户
        settleDetailDto.setPayAccount(financeManageGrantConfirmReqDto.getPaymentAccountName());
        // 创建时间
        settleBillDto.setCreateTime(grantConfirmTime);
        List<SettleDetailDTO> settleDetailDtoList = Arrays.asList(settleDetailDto);
        settleBillDto.setDetails(settleDetailDtoList);
        return settleBillDto;
    }

    /**
     * 初始化代收货款发放ES实体
     *
     * @param financeManageGrantConfirmReqDto
     * @param financeManageGrantAmountReqDto
     * @param financeManageGoodsGrantEsDto
     * @param grantConfirmTime
     * @param type
     */
    private void initFinanceManageGoodsGrantEsDTO(FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto, FinanceManageGrantAmountReqDTO financeManageGrantAmountReqDto, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto, Long grantConfirmTime, Integer type) {
        if (type == 1) {
            //已发货款 += 本次发放货款
            financeManageGoodsGrantEsDto.setSendAmount(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getSendAmount().doubleValue() + financeManageGrantAmountReqDto.getThisSendAmount().doubleValue())));
            //未发货款 = 代收货款 - 已发货款
            financeManageGoodsGrantEsDto.setUnsendAmount(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getDeliveryAmount().doubleValue() - financeManageGoodsGrantEsDto.getSendAmount().doubleValue())));
            //付款手续费 += 本次付款分摊的手续费
            financeManageGoodsGrantEsDto.setPayFee(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getPayFee().doubleValue() + financeManageGrantAmountReqDto.getThisPayFee().doubleValue())));
            //设置货款发放状态 未发放金额大于0对应部分发放状态 等于0对应已发放状态
            setGrantStatus(financeManageGoodsGrantEsDto);
        } else if (type == 2) {
            //已结货款扣 += 本次已结货款扣
            financeManageGoodsGrantEsDto.setGoodsAmount(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getGoodsAmount().doubleValue() + financeManageGrantAmountReqDto.getThisActualGoodsAmount().doubleValue())));
            //未结货款扣 = 货款扣 - 已结货款扣
            financeManageGoodsGrantEsDto.setUngoodsAmount(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getGoodsAmountFee().doubleValue() - financeManageGoodsGrantEsDto.getGoodsAmount().doubleValue())));
        } else if (type == 3) {
            //已结货款手续费 += 本次实结货款手续费
            financeManageGoodsGrantEsDto.setGoodsFee(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getGoodsFee().doubleValue() + financeManageGrantAmountReqDto.getThisActualGoodsFee().doubleValue())));
            //未结货款手续费 = 货款手续费 - 已结货款手续费
            financeManageGoodsGrantEsDto.setUngoodsFee(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getDeliveryGoodsAmount().doubleValue() - financeManageGoodsGrantEsDto.getGoodsFee().doubleValue())));
        }
        //实付金额 = 已发货款 + 付款手续费 - 货款扣已结 - 货款手续费已结
        financeManageGoodsGrantEsDto.setActualAmount(new BigDecimal(df.format(financeManageGoodsGrantEsDto.getSendAmount().doubleValue() + financeManageGoodsGrantEsDto.getPayFee().doubleValue() - financeManageGoodsGrantEsDto.getGoodsAmount().doubleValue() - financeManageGoodsGrantEsDto.getGoodsFee().doubleValue())));
        financeManageGoodsGrantEsDto.setSendUserId(financeManageGrantConfirmReqDto.getOprId());
        financeManageGoodsGrantEsDto.setSendUserName(financeManageGrantConfirmReqDto.getOprName());
        financeManageGoodsGrantEsDto.setSendTime(grantConfirmTime);
        financeManageGoodsGrantEsDto.setUpdateUserId(financeManageGrantConfirmReqDto.getOprId());
        financeManageGoodsGrantEsDto.setUpdateUserName(financeManageGrantConfirmReqDto.getOprName());
        financeManageGoodsGrantEsDto.setUpdateTime(grantConfirmTime);
    }

    /**
     * 设置收银状态
     *
     * @param financeManageReceiptEsDto
     */
    private void setDeliveryStatus(FinanceManageReceiptEsDTO financeManageReceiptEsDto) {
        if (financeManageReceiptEsDto.getReceiptedAmount().compareTo(financeManageReceiptEsDto.getReceiptAmount()) == 0) {
            financeManageReceiptEsDto.setDeliveryStatus(DeliveryStatusEnum.DELIVERY.getType());
            financeManageReceiptEsDto.setDeliveryStatusName(DeliveryStatusEnum.DELIVERY.getName());
        } else if (financeManageReceiptEsDto.getReceiptedAmount().compareTo(BigDecimal.ZERO) == 0) {
            financeManageReceiptEsDto.setDeliveryStatus(DeliveryStatusEnum.NO_DELIVERY.getType());
            financeManageReceiptEsDto.setDeliveryStatusName(DeliveryStatusEnum.NO_DELIVERY.getName());
        } else {
            financeManageReceiptEsDto.setDeliveryStatus(DeliveryStatusEnum.PARTLY_DELIVERY.getType());
            financeManageReceiptEsDto.setDeliveryStatusName(DeliveryStatusEnum.PARTLY_DELIVERY.getName());
        }
    }

    /**
     * 设置货款发放状态
     *
     * @param financeManageGoodsGrantEsDto
     */
    private void setGrantStatus(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto) {
        //1、已发货款等于代收货款则为已发放状态 2、已发货款为零则为未发放状态 3、已发货款小于代收货款则为部分发放状态
        if (financeManageGoodsGrantEsDto.getSendAmount().compareTo(financeManageGoodsGrantEsDto.getDeliveryAmount()) == 0) {
            financeManageGoodsGrantEsDto.setSendStatus(GrantStatusEnum.GRANTED.getType());
            financeManageGoodsGrantEsDto.setSendStatusName(GrantStatusEnum.GRANTED.getName());
        } else if (financeManageGoodsGrantEsDto.getSendAmount().compareTo(BigDecimal.ZERO) == 0) {
            financeManageGoodsGrantEsDto.setSendStatus(GrantStatusEnum.UNGRANT.getType());
            financeManageGoodsGrantEsDto.setSendStatusName(GrantStatusEnum.UNGRANT.getName());
        } else {
            financeManageGoodsGrantEsDto.setSendStatus(GrantStatusEnum.PART_GRANT.getType());
            financeManageGoodsGrantEsDto.setSendStatusName(GrantStatusEnum.PART_GRANT.getName());
        }
    }

    /**
     * 初始化代收货款发放DB实体
     *
     * @param financeManageGoodsGrantEsDo
     * @param financeManageGoodsGrantDo
     * @param type
     */
    private void initFinanceManageGoodsGrantDO(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDo, FinanceManageGoodsGrantDO financeManageGoodsGrantDo, Integer type) {
        financeManageGoodsGrantDo.setId(financeManageGoodsGrantEsDo.getId());
        financeManageGoodsGrantDo.setCompanyId(financeManageGoodsGrantEsDo.getCompanyId());
        if (type == 1) {
            financeManageGoodsGrantDo.setSendAmount(financeManageGoodsGrantEsDo.getSendAmount());
            financeManageGoodsGrantDo.setUnsendAmount(financeManageGoodsGrantEsDo.getUnsendAmount());
            financeManageGoodsGrantDo.setPayFee(financeManageGoodsGrantEsDo.getPayFee());
            financeManageGoodsGrantDo.setSendStatus(financeManageGoodsGrantEsDo.getSendStatus());
        } else if (type == 2) {
            financeManageGoodsGrantDo.setGoodsAmount(financeManageGoodsGrantEsDo.getGoodsAmount());
            financeManageGoodsGrantDo.setUngoodsAmount(financeManageGoodsGrantEsDo.getUngoodsAmount());
        } else if (type == 3) {
            financeManageGoodsGrantDo.setGoodsFee(financeManageGoodsGrantEsDo.getGoodsFee());
            financeManageGoodsGrantDo.setUngoodsFee(financeManageGoodsGrantEsDo.getUngoodsFee());
        }
        financeManageGoodsGrantDo.setActualAmount(financeManageGoodsGrantEsDo.getActualAmount());
        financeManageGoodsGrantDo.setSendUserId(financeManageGoodsGrantEsDo.getSendUserId());
        financeManageGoodsGrantDo.setSendTime(financeManageGoodsGrantEsDo.getSendTime());
        financeManageGoodsGrantDo.setUpdateUserId(financeManageGoodsGrantEsDo.getUpdateUserId());
        financeManageGoodsGrantDo.setUpdateTime(financeManageGoodsGrantEsDo.getUpdateTime());
    }

    /**
     * 初始化应收信息DB实体
     *
     * @param financeManageReceiptEsDto
     * @param financeManageReceiptDo
     */
    private void initFinanceManageReceiptDO(FinanceManageReceiptEsDTO financeManageReceiptEsDto, FinanceManageReceiptDO financeManageReceiptDo) {
        financeManageReceiptDo.setId(financeManageReceiptEsDto.getId());
        financeManageReceiptDo.setCompanyId(financeManageReceiptEsDto.getCompanyId());
        financeManageReceiptDo.setReceiptedAmount(financeManageReceiptEsDto.getReceiptedAmount());
        financeManageReceiptDo.setUnreceiptAmount(financeManageReceiptEsDto.getUnreceiptAmount());
        financeManageReceiptDo.setDeliveryStatus(financeManageReceiptEsDto.getDeliveryStatus());
        financeManageReceiptDo.setUpdateUserId(financeManageReceiptEsDto.getUpdateUserId());
        financeManageReceiptDo.setUpdateTime(financeManageReceiptEsDto.getUpdateTime());
    }

    /**
     * 初始化资金流水ES实体
     * @param settleBillDto
     * @param financeManageGrantConfirmReqDto
     * @param financeManageGrantAmountReqDto
     * @param financeManageGoodsGrantEsDto
     * @param orgInfoDto
     * @param grantConfirmTime
     * @param type
     * @return
     */
    private FinanceManageCashSerialEsDTO initFinanceManageCashSerialEsDTO(SettleBillDTO settleBillDto, FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto, FinanceManageGrantAmountReqDTO financeManageGrantAmountReqDto, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto, OrgInfoDTO orgInfoDto, Long grantConfirmTime, Integer type) {
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto = new FinanceManageCashSerialEsDTO();
        financeManageCashSerialEsDto.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL));
        financeManageCashSerialEsDto.setFinanceId(financeManageGoodsGrantEsDto.getId());
        financeManageCashSerialEsDto.setGroupId(financeManageGrantConfirmReqDto.getGroupId());
        financeManageCashSerialEsDto.setCompanyId(financeManageGoodsGrantEsDto.getCompanyId());
        financeManageCashSerialEsDto.setType(4);//货款发放
        financeManageCashSerialEsDto.setPayAccount(financeManageGrantConfirmReqDto.getPaymentAccountName());//付款账户
        financeManageCashSerialEsDto.setReceivableBankAccount(financeManageGrantConfirmReqDto.getReceiptAccountName());//收款账户
        if (type == 1) {//代收货款
            financeManageCashSerialEsDto.setFeeType(ExpensesEnum.COLLECTIONOFMONEY.getDocType());
            financeManageCashSerialEsDto.setFeeTypeName(ExpensesEnum.COLLECTIONOFMONEY.getName());
            financeManageCashSerialEsDto.setCodType(CalculationFeeEnum.DSHKFF.getType());
            financeManageCashSerialEsDto.setCodTypeName(CalculationFeeEnum.DSHKFF.getName());
            financeManageCashSerialEsDto.setPayCode(settleBillDto.getCode());
            financeManageCashSerialEsDto.setPayAmount(settleBillDto.getSettledAmount());
            financeManageCashSerialEsDto.setFundAccount(financeManageGrantConfirmReqDto.getPaymentAccountName());//付款账户->资金账户
            financeManageCashSerialEsDto.setReceiptAmount(BigDecimal.ZERO);
            financeManageCashSerialEsDto.setPayeeObject(financeManageGoodsGrantEsDto.getReceiveName());//收款方
            financeManageCashSerialEsDto.setServiceCharge(financeManageGrantAmountReqDto.getThisPayFee());
            financeManageCashSerialEsDto.setPaymentType(financeManageGrantConfirmReqDto.getPaymentMethodId());
            financeManageCashSerialEsDto.setPaymentTypeName(financeManageGrantConfirmReqDto.getPaymentMethodName());
        } else {
            financeManageCashSerialEsDto.setPayeeName(financeManageGoodsGrantEsDto.getReceiveName());//付款方
            financeManageCashSerialEsDto.setReceiptCode(settleBillDto.getCode());
            financeManageCashSerialEsDto.setFundAccount(financeManageGrantConfirmReqDto.getReceiptAccountName());//收款账户->资金账户
            financeManageCashSerialEsDto.setServiceCharge(BigDecimal.ZERO);
            if (type == 2) {//货款扣
                financeManageCashSerialEsDto.setFeeType(ExpensesEnum.COLLECTIONOFMONEY.getDocType());
                financeManageCashSerialEsDto.setFeeTypeName(ExpensesEnum.COLLECTIONOFMONEY.getName());
                financeManageCashSerialEsDto.setCodType(CalculationFeeEnum.YD.getType());
                financeManageCashSerialEsDto.setCodTypeName(CalculationFeeEnum.YD.getName());
                financeManageCashSerialEsDto.setReceiptAmount(financeManageGrantAmountReqDto.getThisActualGoodsAmount());
                financeManageCashSerialEsDto.setPaymentType(PayWayEnum.PAYMENTDUCTION.getType());
                financeManageCashSerialEsDto.setPaymentTypeName(PayWayEnum.PAYMENTDUCTION.getName());
            }
            if (type == 3) {//代收货款手续费
                financeManageCashSerialEsDto.setFeeType(ExpensesEnum.COLLECTIONFEE.getDocType());
                financeManageCashSerialEsDto.setFeeTypeName(ExpensesEnum.COLLECTIONFEE.getName());
                financeManageCashSerialEsDto.setCodType(CalculationFeeEnum.DSHKSXF.getType());
                financeManageCashSerialEsDto.setCodTypeName(CalculationFeeEnum.DSHKSXF.getName());
                financeManageCashSerialEsDto.setReceiptAmount(financeManageGrantAmountReqDto.getThisActualGoodsFee());
                financeManageCashSerialEsDto.setPaymentType(financeManageGrantConfirmReqDto.getPaymentMethodId());
                financeManageCashSerialEsDto.setPaymentTypeName(financeManageGrantConfirmReqDto.getPaymentMethodName());
            }
        }
        financeManageCashSerialEsDto.setOrdersourceId(financeManageGoodsGrantEsDto.getOrderSourceCode());
        financeManageCashSerialEsDto.setReceiptPayNetworkId(financeManageGoodsGrantEsDto.getPayBranchId());
        financeManageCashSerialEsDto.setReceiptPayNetworkName(financeManageGoodsGrantEsDto.getPayBranchName());
        financeManageCashSerialEsDto.setSettleNetworkId(orgInfoDto.getAccountOrgId());
        financeManageCashSerialEsDto.setSettleNetworkName(orgInfoDto.getAccountOrgName());
        financeManageCashSerialEsDto.setRemark(financeManageGrantConfirmReqDto.getRemark());
        financeManageCashSerialEsDto.setSettlementMethod(financeManageGrantConfirmReqDto.getBillMethodId());
        financeManageCashSerialEsDto.setSettlementMethodName(financeManageGrantConfirmReqDto.getBillMethodName());
        financeManageCashSerialEsDto.setPayMethod(financeManageGrantConfirmReqDto.getPaymentChannelId());//支付渠道
        financeManageCashSerialEsDto.setPayMethodName(financeManageGrantConfirmReqDto.getPaymentChannelName());
        financeManageCashSerialEsDto.setSubmitBillState(TransferAccountsStatusEnum.UNTRANSFER.getType());
        financeManageCashSerialEsDto.setSubmitBillStateName(TransferAccountsStatusEnum.UNTRANSFER.getName());
        financeManageCashSerialEsDto.setReceiptPayTime(financeManageGrantConfirmReqDto.getPaymentTime());
        financeManageCashSerialEsDto.setCreateBillTime(grantConfirmTime);
        financeManageCashSerialEsDto.setCreateTime(grantConfirmTime);
        financeManageCashSerialEsDto.setCreateUserId(financeManageGrantConfirmReqDto.getOprId());
        financeManageCashSerialEsDto.setCreateUserName(financeManageGrantConfirmReqDto.getOprName());
        financeManageCashSerialEsDto.setStatus(FlowStatusEnum.NORMAL.getType());
        return financeManageCashSerialEsDto;
    }

    /**
     * 初始化资金流水ES实体
     *
     * @param id
     * @param financeManageGoodsGrantEsDto
     * @param financeManageGrantCommonReqDto
     * @param logisticsOrgFuncInfoBean
     * @param incomeConfirmTime
     * @return
     */
    private FinanceManageCashSerialEsDTO initFinanceManageCashSerialEsDTO(Long id, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto,
                                                                          FinanceManageGrantCommonReqDTO financeManageGrantCommonReqDto,
                                                                          LogisticsOrgFuncInfoBean logisticsOrgFuncInfoBean, Long incomeConfirmTime) {
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDto = new FinanceManageCashSerialEsDTO();
        financeManageCashSerialEsDto.setId(id);
        financeManageCashSerialEsDto.setFinanceId(financeManageGoodsGrantEsDto.getId());
        financeManageCashSerialEsDto.setType(4);//发放
        financeManageCashSerialEsDto.setCompanyId(financeManageGoodsGrantEsDto.getCompanyId());
        financeManageCashSerialEsDto.setOrdersourceId(financeManageGoodsGrantEsDto.getOrderSourceCode());
        financeManageCashSerialEsDto.setFeeType(ExpensesEnum.COLLECTIONOFMONEY.getDocType());
        financeManageCashSerialEsDto.setFeeTypeName(ExpensesEnum.COLLECTIONOFMONEY.getName());
        financeManageCashSerialEsDto.setCodType(CalculationFeeEnum.DSHKJZ.getType());//单据类型
        financeManageCashSerialEsDto.setCodTypeName(CalculationFeeEnum.DSHKJZ.getName());
        financeManageCashSerialEsDto.setFundAccount(financeManageGoodsGrantEsDto.getRemitInAccount());//汇入账户->资金账号
        financeManageCashSerialEsDto.setReceiptAmount(financeManageGoodsGrantEsDto.getDeliveryAmount());//收入
        financeManageCashSerialEsDto.setPayAmount(BigDecimal.ZERO);//支出
        financeManageCashSerialEsDto.setReceiptPayNetworkId(financeManageGrantCommonReqDto.getCurNetworkId());//汇款对象进账网点->收支网点
        financeManageCashSerialEsDto.setReceiptPayNetworkName(financeManageGrantCommonReqDto.getCurNetworkName());
        financeManageCashSerialEsDto.setSettleNetworkId(logisticsOrgFuncInfoBean.getAccountOrgId());//汇款对象的核算组织->核算网点
        financeManageCashSerialEsDto.setSettleNetworkName(logisticsOrgFuncInfoBean.getAccountOrgName());
        financeManageCashSerialEsDto.setServiceCharge(BigDecimal.ZERO);
        financeManageCashSerialEsDto.setSubmitBillState(TransferAccountsStatusEnum.UNTRANSFER.getType());
        financeManageCashSerialEsDto.setSubmitBillStateName(TransferAccountsStatusEnum.UNTRANSFER.getName());
        financeManageCashSerialEsDto.setPayeeName(financeManageGoodsGrantEsDto.getRemitBranchName());//汇款网点->付款方
        financeManageCashSerialEsDto.setPayAccount(financeManageGoodsGrantEsDto.getRemitOutAccount());//汇出账户->付款账户
        financeManageCashSerialEsDto.setReceivableBankAccount(financeManageGoodsGrantEsDto.getRemitInAccount());//汇入账户->收款账户
        financeManageCashSerialEsDto.setReceiptPayTime(incomeConfirmTime);
        financeManageCashSerialEsDto.setCreateBillTime(incomeConfirmTime);
        financeManageCashSerialEsDto.setCreateTime(incomeConfirmTime);
        financeManageCashSerialEsDto.setCreateUserId(financeManageGrantCommonReqDto.getOprId());
        financeManageCashSerialEsDto.setCreateUserName(financeManageGrantCommonReqDto.getOprName());
        financeManageCashSerialEsDto.setStatus(FlowStatusEnum.NORMAL.getType());
        return financeManageCashSerialEsDto;
    }

    /**
     * 初始化货款回收DB实体
     *
     * @param financeManageGoodsRecycleEsDTO
     * @param financeManageGoodsRecycleDO
     */
    private void intFinanceManageGoodsRecycleDO(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO, FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO) {
        financeManageGoodsRecycleDO.setId(financeManageGoodsRecycleEsDTO.getId());
        financeManageGoodsRecycleDO.setCompanyId(financeManageGoodsRecycleEsDTO.getCompanyId());
        financeManageGoodsRecycleDO.setSendStatus(financeManageGoodsRecycleEsDTO.getSendStatus());
        financeManageGoodsRecycleDO.setUpdateTime(financeManageGoodsRecycleEsDTO.getUpdateTime());
        financeManageGoodsRecycleDO.setUpdateUserId(financeManageGoodsRecycleEsDTO.getUpdateUserId());
    }

    /**
     * 组装生成收款单的请求DTO
     * @param financeManageGrantConfirmReqDto
     * @param financeManageGrantAmountReqDto
     * @param financeManageGoodsGrantEsDto
     * @param orgInfoDto
     * @param grantConfirmTime
     * @param type
     * @return
     */
    private SettleBillDTO buildReceiptReqDTO(FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto, FinanceManageGrantAmountReqDTO financeManageGrantAmountReqDto, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto, OrgInfoDTO orgInfoDto, Long grantConfirmTime, Integer type) {
        SettleBillDTO settleBillDTO = new SettleBillDTO();
        SettleDetailDTO detailDto = new SettleDetailDTO();
        settleBillDTO.setTypeSeparate(1); // 类型区分(1收款;2付款)
        settleBillDTO.setGroupId(financeManageGrantConfirmReqDto.getGroupId()); // 集团 ID
        settleBillDTO.setBillType(FinanceConstant.RECEIPT_BILL_TYPE); // 收款单类型(10001 销售收款单;10002 采购退货收款单;10003 代收货款收款单;10004 其他收款单;10005 服务收款单)")
        settleBillDTO.setSourceBillType(FinanceConstant.SOURCE_BILL_TYPE); // 来源单据类型(数据字典 BOSS030)
        settleBillDTO.setBillDate(grantConfirmTime); // 单据日期
        settleBillDTO.setBillStatus(Status.APPROVED); // 单据状态-以保存
        settleBillDTO.setSettleOrgAccount(financeManageGrantConfirmReqDto.getReceiptAccountName()); // 结算组织账户(收款组织账号)
        settleBillDTO.setCooperationType(InteractObjectEnum.CUSTOMNER.code()); // 往来对象类型(1-供应商;2-客户;3-部门;4-人员;5-会员)")
        settleBillDTO.setCurrencyId(FinanceConstant.CHINA_CURRENCY);// 币种 ID (人民币为 1)
        settleBillDTO.setSettlePartyAccount(financeManageGrantConfirmReqDto.getPaymentAccountName());
        settleBillDTO.setPushed(true);
        settleBillDTO.setPullable(false);
        settleBillDTO.setCreateUser(financeManageGrantConfirmReqDto.getOprId());
        settleBillDTO.setCreateTime(grantConfirmTime);
        settleBillDTO.setRemark(financeManageGrantConfirmReqDto.getRemark());// 备注
        detailDto.setGroupId(financeManageGrantConfirmReqDto.getGroupId());
        detailDto.setSourceBillId(financeManageGoodsGrantEsDto.getId()); // 来源单ID--运单ID
        detailDto.setSourceBillCode(financeManageGoodsGrantEsDto.getOrderSourceCode()); // 来源单编号--运单号
        detailDto.setSourceDetailId(1L); // 来源单行号
        detailDto.setOriginalBillId(financeManageGoodsGrantEsDto.getWaybillId()); // 源头单ID--应收记录ID
        detailDto.setOriginalBillCode(financeManageGoodsGrantEsDto.getOrderSourceCode());//// 源头单编号
        detailDto.setOriginalBillType(FinanceConstant.SOURCE_BILL_TYPE); // 源头单类型
        detailDto.setOriginalDetailId(1L); // 源头单行号
        if (type == 2) {
            detailDto.setTotalAmmount(financeManageGrantAmountReqDto.getThisActualGoodsAmount());//总金额(收付款金额)->本次实结货款扣
            detailDto.setSourceTaxedAmount(financeManageGoodsGrantEsDto.getGoodsAmountFee()); // 来源单金额->货款扣
            //已结金额
            detailDto.setSettledAmount(financeManageGrantAmountReqDto.getThisActualGoodsAmount());
            settleBillDTO.setSettledAmount(financeManageGrantAmountReqDto.getThisActualGoodsAmount());

        }
        if (type == 3) {
            detailDto.setTotalAmmount(financeManageGrantAmountReqDto.getThisActualGoodsFee());//总金额(收付款金额)-本次实结货款手续费
            detailDto.setSourceTaxedAmount(financeManageGoodsGrantEsDto.getDeliveryGoodsAmount()); // 来源单金额->代收货款手续费
            //已结金额
            detailDto.setSettledAmount(financeManageGrantAmountReqDto.getThisActualGoodsFee());
            settleBillDTO.setSettledAmount(financeManageGrantAmountReqDto.getThisActualGoodsFee());
        }
        detailDto.setSettleType(FinanceConstant.RECEIPT_PAY_TYPE_SERVICE_ID); //收款类型 - 服务款id
        detailDto.setSettleCharactor(FinanceConstant.RECEIVABLES_PROPERTY_RECEIVABLE);//应收、预收
        detailDto.setPayMode(String.valueOf(PayWayEnum.COLLECTIONOFGOODS.getType()));
        detailDto.setSettleMode(financeManageGrantConfirmReqDto.getBillMethodId());
        detailDto.setRemark(financeManageGrantConfirmReqDto.getRemark());
        detailDto.setReceiveAccount(financeManageGrantConfirmReqDto.getReceiptAccountName());
        detailDto.setPayAccount(financeManageGrantConfirmReqDto.getPaymentAccountName());
        detailDto.setCheckStatus(ReconciliationStatus.RECONCILICATIONSUCCESS); //收款对账状态 - 对账成功
        Integer payObject = 0;//付款方、业务方、应付方
        //运单中收款方Id和收款会员Id只能有一个有值
        if (financeManageGoodsGrantEsDto.getReceiptCustomerId() != null) {
            payObject = financeManageGoodsGrantEsDto.getReceiptCustomerId();
        } else if (financeManageGoodsGrantEsDto.getReceiptCustomerVipId() != null) {
            payObject = financeManageGoodsGrantEsDto.getReceiptCustomerVipId().intValue();
        }
        LogisticsOrgFuncInfoBean logisticsOrgFuncInfoBean = financeCommonService.getOrgId(financeManageGoodsGrantEsDto.getDestOrgId());
        settleBillDTO.setSettleOrg(logisticsOrgFuncInfoBean.getSettleOrgId());//收款组织 - 目的网点的结算组织ID
        settleBillDTO.setSettleParty(payObject);//付款方--收货方在客商档案的客户ID 或者收货人在会员档案的会员ID
        detailDto.setAccountOrg(logisticsOrgFuncInfoBean.getAccountOrgId());//应收组织 - 目的网点的核算组织ID
        detailDto.setAccountParty(payObject);//应付方 - 收货方在客商档案的客户ID 或者收货人在会员档案的会员ID

        //获取核算组织信息
        orgInfoDto.setAccountOrgId(logisticsOrgFuncInfoBean.getAccountOrgId());
        orgInfoDto.setAccountOrgName(logisticsOrgFuncInfoBean.getAccountOrgName());

        List<SettleDetailDTO> details = Arrays.asList(detailDto);
        settleBillDTO.setDetails(details);
        return settleBillDTO;
    }

    /**
     * 处理异常情况，保证数据一致性
     *
     * @param settleBillDto
     * @param financeManageGoodsGrantEsDto
     * @param financeManageGrantConfirmReqDto
     * @param type
     */
    private void handleEx(SettleBillDTO settleBillDto, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto, FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto, int type) {
        if (settleBillDto != null && settleBillDto.getCode() != null) {
            CodeOpParam opParam = new CodeOpParam();
            opParam.setTypeSeparate(type);
            opParam.setCode(settleBillDto.getCode());
            opParam.setGroupId(financeManageGrantConfirmReqDto.getGroupId());
            opParam.setOpUser(financeManageGrantConfirmReqDto.getOprId());
            financeCommonService.deleteBySource(opParam);
            log.info("删除收/付款单成功，来源单ID：" + financeManageGoodsGrantEsDto.getId() + "收/付款单号：" + settleBillDto.getCode());
        }
    }
}

