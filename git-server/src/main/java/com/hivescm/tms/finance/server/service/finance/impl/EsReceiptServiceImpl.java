package com.hivescm.tms.finance.server.service.finance.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.domain.PagedList;
import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchAggregateExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.finance.component.FinanceReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceReceiptReqDTO;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.EsReceiptService;
import com.hivescm.tms.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨彭伟
 * @date 2018-01-03 17:09
 */
@Slf4j
@Service
public class EsReceiptServiceImpl implements EsReceiptService {
    @Autowired
    private ESSearchService esSearchService;
    @Autowired
    private ESStatisticService esStatisticService;

    @Override
    public PagedList<FinanceReceiptEsDTO> getReceiptList(FinanceReceiptReqDTO financeReceiptReqDTO) {
        PagedList<FinanceReceiptEsDTO> pagedList = new PagedList<>();
        List<SearchCondition> searchConditions = buildCondition(financeReceiptReqDTO);
        List<OrderCondition> orderConditions = buildOrderCondition();
        PageCondition pageCondition = buildPageCondition(financeReceiptReqDTO);
        Integer total = getTotal(financeReceiptReqDTO);
        List<FinanceReceiptEsDTO> list = getList(searchConditions, orderConditions, pageCondition);
        pagedList.setItems(list);
        pagedList.setTotalCount(total);
        pagedList.setCurrentPage(financeReceiptReqDTO.getCurrentPage());
        pagedList.setPageSize(financeReceiptReqDTO.getPageSize());
        return pagedList;
    }

    private List<FinanceReceiptEsDTO> getList(List<SearchCondition> searchConditions,
                                              List<OrderCondition> orderConditions,
                                              PageCondition pageCondition) {
        return new DefaultAbstractSearchQueryExecutor<FinanceReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.finance();
            }
        }.list(searchConditions, orderConditions, pageCondition);
    }

    private Integer getTotal(FinanceReceiptReqDTO financeReceiptReqDTO) {
        List<SearchCondition> searchConditions = buildCondition(financeReceiptReqDTO);
        return new DefaultAbstractSearchAggregateExecutor<FinanceReceiptEsDTO>(esStatisticService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.finance();
            }
        }.count(searchConditions);
    }

    private List<SearchCondition> buildCondition(FinanceReceiptReqDTO financeReceiptReqDTO){
        List<SearchCondition> list = new ArrayList<>();
        if (financeReceiptReqDTO.getTimeType() != null    //1.收款 2.签收 3.收银
                && financeReceiptReqDTO.getTimeType() != 1
                && financeReceiptReqDTO.getTimeType() != 2
                && financeReceiptReqDTO.getTimeType() != 3) {
            throw new SystemException(-1, "参数错误，时间类型必须是1/2/3中的一个");
        }
        if (null != financeReceiptReqDTO.getStartTime() && null != financeReceiptReqDTO.getEndTime()
                && financeReceiptReqDTO.getStartTime() > 0 && financeReceiptReqDTO.getEndTime() > 0) {
            String fieldName;
            if (financeReceiptReqDTO.getTimeType() == 1) {
                fieldName = "collectionTime";
            } else if (financeReceiptReqDTO.getTimeType() == 2) {
                fieldName = "signTime";
            } else {
                fieldName = "confirmReceiptTime";
            }
            SearchCondition startTimeCondition = SearchConditionUtils
                    .newSearchCondition(fieldName, financeReceiptReqDTO.getStartTime(), ConditionExpressionEnum.GREATER_OR_EQUAL);
            SearchCondition endTimeCondition = SearchConditionUtils
                    .newSearchCondition(fieldName, financeReceiptReqDTO.getEndTime(), ConditionExpressionEnum.LESSER_OR_EQUAL);
            list.add(startTimeCondition);
            list.add(endTimeCondition);
        }
        // 司机名称
        if (StringUtil.isNotBlank(financeReceiptReqDTO.getDriverName())) {
            SearchCondition driverNameCondition = SearchConditionUtils.newLikeCondition("driverName",
                    financeReceiptReqDTO.getDriverName());
            list.add(driverNameCondition);
        }
        // 是否确认收银
        if (financeReceiptReqDTO.getFinanceEnum() != null && financeReceiptReqDTO.getFinanceEnum() != 0) {
            SearchCondition confirmReceiptCondition = SearchConditionUtils.newEqualCondition("confirmReceipt",
                    financeReceiptReqDTO.getFinanceEnum());
            list.add(confirmReceiptCondition);
        }
        //签收单号
        if(StringUtil.isNotBlank(financeReceiptReqDTO.getSourceNumber())) {
        	SearchCondition sourceNumberCondition = SearchConditionUtils.newLikeCondition("sourceNumber",
                    financeReceiptReqDTO.getSourceNumber());
            list.add(sourceNumberCondition);
        }
        //收款单号
        if(StringUtil.isNotBlank(financeReceiptReqDTO.getReceiptCode())) {
        	SearchCondition receiptCodeCondition = SearchConditionUtils.newLikeCondition("receiptCode",
                    financeReceiptReqDTO.getReceiptCode());
            list.add(receiptCodeCondition);
        }
        //付款方
        if(StringUtil.isNotBlank(financeReceiptReqDTO.getPayObject())) {
        	SearchCondition payObjectCondition = SearchConditionUtils.newEqualCondition("payObject",
                    financeReceiptReqDTO.getPayObject());
            list.add(payObjectCondition);
        }
        //派车批次
        if(StringUtil.isNotBlank(financeReceiptReqDTO.getBatchCode())) {
        	SearchCondition batchCodeCondition = SearchConditionUtils.newLikeCondition("batchCode",
                    financeReceiptReqDTO.getBatchCode());
            list.add(batchCodeCondition);
        }
        
        //
        //公司
        if (null != financeReceiptReqDTO.getCompanyId() && financeReceiptReqDTO.getCompanyId() > 0) { // 公司ID
        	SearchCondition companyIdCondition = SearchConditionUtils.newEqualCondition("companyId",
                    financeReceiptReqDTO.getCompanyId());
            list.add(companyIdCondition);
		}
//
//		// 发货网点ID-单据网点
		if (null != financeReceiptReqDTO.getCurDotId() && financeReceiptReqDTO.getCurDotId()> 0) {
			SearchCondition invoiceOrgIdsCondition = SearchConditionUtils.newEqualCondition("invoiceOrgId",
                    financeReceiptReqDTO.getCurDotId());
			list.add(invoiceOrgIdsCondition);
		}
        return list;
    }

    private PageCondition buildPageCondition(FinanceReceiptReqDTO financeReceiptReqDTO) {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setCurrentPage(financeReceiptReqDTO.getCurrentPage() == null ? 1 : financeReceiptReqDTO.getCurrentPage());
        pageCondition.setPageSize(financeReceiptReqDTO.getPageSize() == null ? 10 : financeReceiptReqDTO.getPageSize());
        return pageCondition;
    }

    private List<OrderCondition> buildOrderCondition() {
        List<OrderCondition> orderConditionList = new ArrayList<OrderCondition>();
        OrderCondition orderCondition = new OrderCondition();
        orderCondition.setFieldName("createTime");
        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditionList.add(orderCondition);
        return orderConditionList;
    }
}
