package com.hivescm.tms.finance.server.service.sign.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.sign.OrderPaymentInfoESDTO;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.sign.EsSignPaymentService;

/**
 * @author 杨彭伟
 * @date 2018-01-17 23:12
 */
@Service
public class EsSignPaymentServiceImpl implements EsSignPaymentService {
    @Autowired
    private ESSearchService esSearchService;

    @Override
    public OrderPaymentInfoESDTO getSignPayment(Long waybillId) {
        List<SearchCondition> searchConditions = SearchConditionUtils
            .start()
                .addEqualCondition("waybillId", waybillId)
                .addEqualCondition("orderStatus", "TRADE_SUCCESS")
            .end();
        return new DefaultAbstractSearchQueryExecutor<OrderPaymentInfoESDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.orderPaymentInfo();
            }
        }.get(searchConditions);

    }
}
