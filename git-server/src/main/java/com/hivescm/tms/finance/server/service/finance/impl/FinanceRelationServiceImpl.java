package com.hivescm.tms.finance.server.service.finance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.service.finance.FinanceRelationService;
import com.hivescm.tms.intranet.gateway.api.dto.boss.IFinanceEntrustRelationInfoDto;
import com.hivescm.tms.intranet.gateway.api.dto.boss.IQueryFinanceEntrustRelationParam;
import com.hivescm.tms.intranet.gateway.api.feign.count.CountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FinanceRelationServiceImpl implements FinanceRelationService {
    @Autowired
    private CountService countService;

    @Override
    public List<IFinanceEntrustRelationInfoDto> getRelationship(Integer orgId) {
        try {
            IQueryFinanceEntrustRelationParam queryFinanceEntrustRelationParam = new IQueryFinanceEntrustRelationParam();
            queryFinanceEntrustRelationParam.setBizOrgId(orgId);
            DataResult<List<IFinanceEntrustRelationInfoDto>> listDataResult = countService.queryFinanceEntrustRelation(queryFinanceEntrustRelationParam);
            if (listDataResult.isSuccess()) {
                return listDataResult.getResult();
            } else {
                SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_FINANCE_SEARCH,
                        "getRelationship has error:param={" + orgId + "},error={ 查询业务组织的结算组织和核算组织,调用失败 }");
                throw ex;
            }
        } catch (Exception e) {
            e.printStackTrace();
            SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_FINANCE_SEARCH,
                    "getRelationship has error:param={" + orgId + "},error={" + e.getLocalizedMessage() + "}");
            log.error(ex.getMessage(), e);
            throw ex;
        }
    }
}
