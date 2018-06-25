package com.hivescm.tms.finance.server.component.finance.impl;

import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.tms.api.dto.es.finance.component.SettleOrgDTO;
import com.hivescm.tms.api.dto.es.order.redundancy.WriteMqInfoDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceCommonService;
import com.hivescm.tms.intranet.gateway.api.dto.boss.*;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.CodeOpParam;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.SettleBillDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IWriteMqInfoService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossBizUnitApi;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossFinanceEntrusRelationService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.IBossFinanceNewReceiptPayApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨彭伟
 * @date 2018-01-22 20:45
 */
@Slf4j
@Service
public class FinanceCommonServiceImpl implements FinanceCommonService {

    @Autowired
    private BossFinanceEntrusRelationService bossFinanceEntrusRelationService;
    @Autowired
    private IBossFinanceNewReceiptPayApi bossFinanceNewReceiptPayApi;
    @Autowired
    private IWriteMqInfoService writeMqInfoService;
    @Autowired
    private BossBizUnitApi bossBizUnitApi;

    @Override
    public SettleOrgDTO getSettleOrg(Integer id, int type) {
        SettleOrgDTO settleOrgDTO = null;
        WriteMqInfoDTO dto = new WriteMqInfoDTO();

        if (type == 1) {
            // carrier
            ILogisticsOrgFuncInfoBean logisticsOrgFuncInfoBean = null;
            try {
                logisticsOrgFuncInfoBean = bossFinanceEntrusRelationService.queryCarrierFinanceOrg(id);
            } catch (Exception e) {
                log.error("carrier getSettleOrg", e);
            }
            if (logisticsOrgFuncInfoBean != null) {
                Integer settleOrgId = logisticsOrgFuncInfoBean.getSettleOrgId();
                String settleOrgName = logisticsOrgFuncInfoBean.getSettleOrgName();
                settleOrgDTO = new SettleOrgDTO(settleOrgId, settleOrgName);
            }
        } else {
            // dealer
            List<SaleEntrustRelationDto> saleEntrustRelationDtos = null;
            try {
                saleEntrustRelationDtos = bossFinanceEntrusRelationService.queryDealerFinanceOrg(id);
            } catch (Exception e) {
                log.error("dealer getSettleOrg", e);
            }
            if (saleEntrustRelationDtos != null && saleEntrustRelationDtos.size() > 0) {
                SaleEntrustRelationDto relationship = saleEntrustRelationDtos.get(0);
                Integer settleOrgId = relationship.getSettleOrgId();
                String settleOrgName = relationship.getSettleOrgName();
                settleOrgDTO = new SettleOrgDTO(settleOrgId, settleOrgName);
            }
        }
        dto.setOrderType("getSettleOrg");
        dto.setQueueName("结算组织");
        dto.setMainInfo("结算组织id:" + (settleOrgDTO == null ? "空的" : settleOrgDTO.getSettleOrgId()) + "结算组织名称:" + (settleOrgDTO == null ? "空的" : settleOrgDTO.getSettleOrgName()));
        writeMqInfoService.insert(dto);
        return settleOrgDTO;
    }

    @Override
    public LogisticsOrgFuncInfoBean getOrgId(Integer id) {
        try {
            QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
            queryByIdReqParam.setId(id);
            //调用BOSS接口获取业务单元信息
            DataResult<BizUnitWithFuncDetailVo> dr = bossBizUnitApi.queryBizUnitById(queryByIdReqParam);
            if (dr != null && dr.getResult() != null && dr.getResult().getLogistics() != null) {
                return dr.getResult().getLogistics();
            } else {
                Exception ex = new Exception(dr == null || dr.getResult() == null || dr.getResult().getLogistics() == null ? "返回值为空" : dr.getStatus().getStatusReason());
                log.error(ex.getMessage(), ex);
                throw ex;
            }
        } catch (Exception e) {
            TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_DEPENDENCY, "外部服务异常：获取业务单元信息失败，原因: BOSS服务调用失败 - " + e.getLocalizedMessage());
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public SettleBillDTO createPayInfo(SettleBillDTO settleParam) {
        try {
            DataResult<SettleBillDTO> dr = bossFinanceNewReceiptPayApi.createPayInfo(settleParam);
            if (dr != null && dr.getResult() != null) {
                return dr.getResult();
            } else {
                Exception ex = new Exception(dr == null ? "返回值为空" : dr.getStatus().getStatusReason());
                log.error(ex.getMessage(), ex);
                throw ex;
            }
        } catch (Exception e) {
            TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_DEPENDENCY,
                    "外部服务异常：创建付款单失败，原因：BOSS服务调用失败 - " + e.getLocalizedMessage());
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Boolean deleteBySource(CodeOpParam opParam) {
        try {
            DataResult<Boolean> dr = bossFinanceNewReceiptPayApi.deleteBySource(opParam);
            if (dr != null && dr.getResult() != null) {
                return true;
            } else {
                Exception ex = new Exception(dr == null ? "返回值为空" : dr.getStatus().getStatusReason());
                log.error(ex.getMessage(), ex);
                throw ex;
            }
        } catch (Exception e) {
            TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_DEPENDENCY,
                    "外部服务异常：删除付款单失败，原因：BOSS服务调用失败 - " + e.getLocalizedMessage());
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }
}
