package com.hivescm.tms.finance.server.service.finance.impl;


import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.fare.request.BillInfoReqDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageListRespDTO;
import com.hivescm.tms.api.enums.finance.PaymentStatusEnum;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManagePayMapper;
import com.hivescm.tms.finance.server.feign.waybill.WaybillForBossSerivce;
import com.hivescm.tms.finance.server.service.db.DbFinanceManagePayService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManagePayService;
import com.hivescm.tms.finance.server.service.finance.FinanceManagePayService;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 财务管理-应付表的Service实现
 *
 * @author wenxiong.jia
 * @date 2018/4/27
 */
@Slf4j
@Service
public class FinanceManagePayServiceImpl implements FinanceManagePayService {

    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private FinanceManagePayMapper financeManagePayMapper;
    @Autowired
    private EsFinanceManagePayService esFinanceManagePayService;
    @Autowired
    private DbFinanceManagePayService dbFinanceManagePayService;
    @Autowired
    private WaybillForBossSerivce waybillForBossSerivce;

    @Override
    public Boolean createPayInfo(FinanceManagePayReqDTO financeManagePayReqDto) {
        if (financeManagePayReqDto != null) {
            FinanceManagePayEsDTO financeManagePayEsDto = EntityUtils.copyProperties(financeManagePayReqDto, FinanceManagePayEsDTO.class);
            // 设置主键
            financeManagePayEsDto.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_PAY));
            FinanceManagePayDO financeManagePayDo = EntityUtils.copyProperties(financeManagePayEsDto, FinanceManagePayDO.class);
            // 保存DB
            int db = financeManagePayMapper.insertSelective(financeManagePayDo);
            // 保存ES
            boolean es = esFinanceManagePayService.insertEs(financeManagePayEsDto);
            return db > 0 && es;
        }
        return false;
    }

    @Override
    public Boolean createBatchPayInfo(List<FinanceManagePayReqDTO> financeManagePayReqDtoList) {
        if (CollectionUtils.isEmpty(financeManagePayReqDtoList)) {
            return false;
        }
        FinanceManagePayEsDTO financeManagePayEsDto = null;
        FinanceManagePayDO financeManagePayDo = null;
        List<FinanceManagePayEsDTO> financeManagePayEsDtoList = new ArrayList<>();
        List<FinanceManagePayDO> financeManagePayDoList = new ArrayList<>();
        for (FinanceManagePayReqDTO financeManagePayReqDto : financeManagePayReqDtoList) {
            financeManagePayEsDto = EntityUtils.copyProperties(financeManagePayReqDto, FinanceManagePayEsDTO.class);
            // 设置主键
            financeManagePayEsDto.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_PAY));
            financeManagePayEsDtoList.add(financeManagePayEsDto);
            financeManagePayDo = EntityUtils.copyProperties(financeManagePayEsDto, FinanceManagePayDO.class);
            financeManagePayDoList.add(financeManagePayDo);
        }
        // 保存DB
        boolean db = dbFinanceManagePayService.insertBatchPayInfo(financeManagePayDoList);
        // 保存ES
        boolean es = esFinanceManagePayService.insertBatchEs(financeManagePayEsDtoList);

        return db && es;
    }


    @Override
    public FinanceManageListRespDTO getEsList(FinanceManageListReqDTO financeManageListReqDTO) {
        FinanceManageListRespDTO resp = new FinanceManageListRespDTO();
        List<FinanceManagePayEsDTO> esList = esFinanceManagePayService.getEsList(financeManageListReqDTO);
        Integer esListCount = esFinanceManagePayService.getEsListCount(financeManageListReqDTO);
        resp.setList(esList);
        resp.setTotalNum(esListCount);
        return resp;
    }

    @Override
    public List<FinanceManagePayEsDTO> getEsListForPay(FinanceManageListForPayReqDTO financeManageListForPayReqDTO) {
        return esFinanceManagePayService.getEsListForPay(financeManageListForPayReqDTO);
    }

    @Override
    public Boolean cancleCheckFee(FinanceCheckReqDTO financeCheckReqDTO) {
        financeCheckReqDTO.setPaymentStatus(PaymentStatusEnum.NO_CHECK.getType());
        financeCheckReqDTO.setTime(System.currentTimeMillis());
        List<Long> ids = financeCheckReqDTO.getIds();
        List<FinanceManagePayEsDTO> esFinance = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            ids.forEach(id -> {
                FinanceManagePayEsDTO dTO = new FinanceManagePayEsDTO();
                dTO.setId(id);
                dTO.setPaymentStatus(PaymentStatusEnum.NO_CHECK.getType());
                dTO.setPaymentStatusName(PaymentStatusEnum.NO_CHECK.getName());
                dTO.setCancelCheckUserId(financeCheckReqDTO.getUser());
                dTO.setCancelCheckUserName(financeCheckReqDTO.getUserName());
                dTO.setCancelCheckTime(financeCheckReqDTO.getTime());
                dTO.setUpdateUserId(financeCheckReqDTO.getUser());
                dTO.setUpdateUserName(financeCheckReqDTO.getUserName());
                dTO.setUpdateTime(financeCheckReqDTO.getTime());
                esFinance.add(dTO);
            });
        }
        boolean updateDB = financeManagePayMapper.updateCancleCheckStatusBatch(financeCheckReqDTO) > 0;
        Boolean updateEs = esFinanceManagePayService.checkFeeBatch(esFinance);
        return updateDB && updateEs;
    }

    @Override
    public Boolean getPayStatusByCode(FinanceDeleteReqDTO financeDeleteReqDTO) {
        List<FinanceManagePayEsDTO> esFinance = esFinanceManagePayService.getPayStatusByCode(financeDeleteReqDTO);
        if (CollectionUtils.isEmpty(esFinance)) {
            return true;
        }
        return false;
    }

    @Override
    public List<FinanceManagePayEsDTO> getCodeForPay(FinanceSheetCodeReqDTO financeSheetCodeReqDTO) {
        return esFinanceManagePayService.getCodeForPay(financeSheetCodeReqDTO);
    }

    @Override
    public List<FinanceManagePayEsDTO> getEsBySheetCode(Long id) {
        return esFinanceManagePayService.getEsBySheetCode(id);
    }

    @Override
    public Boolean checkFee(FinanceCheckReqDTO financeCheckReqDTO) {
        financeCheckReqDTO.setPaymentStatus(PaymentStatusEnum.NO_PAYMENT.getType());
        financeCheckReqDTO.setTime(System.currentTimeMillis());
        List<Long> ids = financeCheckReqDTO.getIds();
        List<FinanceManagePayEsDTO> esFinance = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            ids.forEach(id -> {
                FinanceManagePayEsDTO dTO = new FinanceManagePayEsDTO();
                dTO.setId(id);
                dTO.setPaymentStatus(PaymentStatusEnum.NO_PAYMENT.getType());
                dTO.setPaymentStatusName(PaymentStatusEnum.NO_PAYMENT.getName());
                dTO.setCheckUserId(financeCheckReqDTO.getUser());
                dTO.setCheckUserName(financeCheckReqDTO.getUserName());
                dTO.setCheckTime(financeCheckReqDTO.getTime());
                dTO.setUpdateUserId(financeCheckReqDTO.getUser());
                dTO.setUpdateUserName(financeCheckReqDTO.getUserName());
                dTO.setUpdateTime(financeCheckReqDTO.getTime());
                esFinance.add(dTO);
            });
        }
        boolean updateDB = financeManagePayMapper.updateCheckStatusBatch(financeCheckReqDTO) > 0;
        Boolean updateEs = esFinanceManagePayService.checkFeeBatch(esFinance);
        return updateDB && updateEs;
    }

    @Override
    public Boolean postConfirme(FinanceCheckReqDTO financeCheckReqDTO) {
        financeCheckReqDTO.setTime(System.currentTimeMillis());
        List<Long> ids = financeCheckReqDTO.getIds();
        List<FinanceManagePayEsDTO> esFinance = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            ids.forEach(id -> {
                FinanceManagePayEsDTO dTO = new FinanceManagePayEsDTO();
                dTO.setId(id);
                dTO.setPostConfirmerId(financeCheckReqDTO.getUser());
                dTO.setPostConfirmerName(financeCheckReqDTO.getUserName());
                dTO.setPostTime(financeCheckReqDTO.getTime());
                dTO.setUpdateUserId(financeCheckReqDTO.getUser());
                dTO.setUpdateUserName(financeCheckReqDTO.getUserName());
                dTO.setUpdateTime(financeCheckReqDTO.getTime());
                esFinance.add(dTO);
            });
        }
        //TODO 调用计费接口
        List<FinanceManagePayEsDTO> listByIds = esFinanceManagePayService.getEsListByIds(ids);
        waybillForBossSerivce.sendFeeInfoToBoss(listByIds);
        boolean updateDB = financeManagePayMapper.updatePostConfirmeBatch(financeCheckReqDTO) > 0;
        Boolean updateEs = esFinanceManagePayService.checkFeeBatch(esFinance);
        return updateDB && updateEs;
    }
    @Override
    @ChainedTransaction(mapper = {FinanceManagePayMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public Boolean deletePayInfo(@RouteParam("FinanceManagePayMapper.companyId") FinanceManagePayDO financeManagePayDo, FinanceManagePayQueryReqDTO financeManagePayQueryReqDto) {
        try {
            financeManagePayMapper.deleteBySheetIdFeeType(financeManagePayDo);
            esFinanceManagePayService.deleteEsBySheetIdFeeType(financeManagePayQueryReqDto);
            return true;
        } catch (Exception e) {
            log.error("操作数据库或ES异常", e);
            throw e;
        }
    }

    @Override
    public Boolean checkPaymentStatusIsNoCheck(FinanceCancelReqDTO financeCancelReqDto) {
        List<FinanceManagePayEsDTO> financeManagePayEsDtoList = esFinanceManagePayService.getEsListBySheetIdListAndDataSourceCode(financeCancelReqDto);
        if (CollectionUtils.isNotEmpty(financeManagePayEsDtoList)) {
            for (FinanceManagePayEsDTO financeManagePayEsDto : financeManagePayEsDtoList) {
                if (financeManagePayEsDto.getPaymentStatus() != PaymentStatusEnum.NO_CHECK.getType()) {
                    return false;
                }
            }
        }
        return true;
    }

	@Override
    public List<FinanceManagePayEsDTO> getListForFeeUpdate(BillInfoReqDTO reqDTO) {
        return esFinanceManagePayService.getListForFeeUpdate(reqDTO);
    }
}
