package com.hivescm.tms.finance.server.service.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.CollapseQueryObject;
import com.hivescm.escenter.common.ESDocument;
import com.hivescm.escenter.common.ESResponse;
import com.hivescm.escenter.common.conditions.InnerHitsCondition;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.elasticsearch.common.SearchAdaptee;
import com.hivescm.framework.elasticsearch.executor.SearchExecutor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/16
 */
public abstract class SingleFiledDistinctQueryExecutor extends SearchAdaptee<CollapseQueryObject> implements SearchExecutor {



    private ESStatisticService esStatisticService;

    public SingleFiledDistinctQueryExecutor(ESStatisticService esStatisticService) {
        this.esStatisticService = esStatisticService;
    }

    /**
     * 获得去重后的字符串集合
     *
     * todo number
     *
     * @param fieldName
     * @param searchConditions
     * @param orderConditions
     * @param pageCondition
     * @return
     * @throws SystemException
     */
    public Set<String> distinctStringSet(String fieldName, List<SearchCondition> searchConditions, List<OrderCondition> orderConditions,PageCondition pageCondition) throws SystemException {
        try {
            CollapseQueryObject obj = this.setConfig(this.getConfig(), CollapseQueryObject.class.newInstance());
            obj.setFieldName(fieldName);
            obj.setSearchConditions(searchConditions);
            obj.setOrderConditions(orderConditions);
            obj.setPageCondition(pageCondition);
            List<InnerHitsCondition> innerHitsConditions = new ArrayList();
            InnerHitsCondition inner = new InnerHitsCondition();
            inner.setHitName("hitName");
            inner.setFieldNames(Arrays.asList(fieldName));
            innerHitsConditions.add(inner);
            obj.setInnerHitsConditions(innerHitsConditions);
            DataResult<Map<String, ESResponse>> result = this.esStatisticService.collapse(obj);
            if (!result.isSuccess()) {
                throw new SystemException(2005, ToStringBuilder.reflectionToString(result));
            } else {
                if (null != result.getResult() && (result.getResult()).size() > 0) {
//                    List<String> list = new ArrayList();
                    Set<String> set = new TreeSet<>();
                    ESResponse response = (ESResponse)((Map)result.getResult()).get(inner.getHitName());
                    if (null != response.getEsDocuments() && response.getEsDocuments().size() > 0) {
                        List<ESDocument> docs = response.getEsDocuments();
                        Iterator var11 = docs.iterator();

                        while(var11.hasNext()) {
                            ESDocument doc = (ESDocument)var11.next();

                            set.add((String) doc.getDataMap().get(fieldName));
//                            list.add(ESSearchConvertor.map2Object(doc.getDataMap(), this.getGenericActualType(this.getClass())));
                        }

                        return set;
                    }
                }

                return null;
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            throw new SystemException(2005, "搜索引擎异常(根据条件查询列表): searchConditions={" + searchConditions + "}, error={" + var13.getLocalizedMessage() + "}");
        }
    }


//    public Integer count(List<SearchCondition> searchConditions) throws org.omg.CORBA.SystemException {
//        try {
//            CollapseQueryObject obj = this.setConfig(this.getConfig(), CollapseQueryObject.class.newInstance());
//            obj.setSearchConditions(searchConditions);
//            this.getClass();
//            obj.setFunctionConditions(this.getFunctionConditions("id", "count", SqlFunctionEnum.COUNT));
//            DataResult<Map<String, Number>> result = this.esStatisticService.statisticByConditions(obj);
//            if (result.isSuccess()) {
//                return ((Number)((Map)result.getResult()).get("count")).intValue();
//            } else {
//                throw new FrameworkException(2005, result.toJSON());
//            }
//        } catch (Exception var4) {
//            var4.printStackTrace();
//            throw new FrameworkException(2005, "搜索引擎异常(根据条件获得列表总数): searchConditions={" + searchConditions + "}, error={" + var4.getLocalizedMessage() + "}");
//        }
//    }


//    public Set<String> distinctNumberSet(String fieldName, List<SearchCondition> searchConditions, List<OrderCondition> orderConditions,PageCondition pageCondition) throws SystemException {
//        try {
//            CollapseQueryObject obj = (CollapseQueryObject)this.setConfig(this.getConfig(), CollapseQueryObject.class.newInstance());
//            obj.setFieldName(fieldName);
//            obj.setSearchConditions(searchConditions);
//            obj.setOrderConditions(orderConditions);
//            obj.setPageCondition(pageCondition);
//            List<InnerHitsCondition> innerHitsConditions = new ArrayList();
//            InnerHitsCondition inner = new InnerHitsCondition();
//            inner.setHitName("hitName");
//            inner.setFieldNames(Arrays.asList(fieldName));
//            innerHitsConditions.add(inner);
//            obj.setInnerHitsConditions(innerHitsConditions);
//            DataResult<Map<String, ESResponse>> result = this.esStatisticService.collapse(obj);
//            if (!result.isSuccess()) {
//                throw new SystemException(2005, ToStringBuilder.reflectionToString(result));
//            } else {
//                if (null != result.getResult() && ((Map)result.getResult()).size() > 0) {
////                    List<String> list = new ArrayList();
//                    Set<String> set = new HashSet<>();
//                    ESResponse response = (ESResponse)((Map)result.getResult()).get(inner.getHitName());
//                    if (null != response.getEsDocuments() && response.getEsDocuments().size() > 0) {
//                        List<ESDocument> docs = response.getEsDocuments();
//                        Iterator var11 = docs.iterator();
//
//                        while(var11.hasNext()) {
//                            ESDocument doc = (ESDocument)var11.next();
//
//                            set.add((String) doc.getDataMap().get(fieldName));
////                            list.add(ESSearchConvertor.map2Object(doc.getDataMap(), this.getGenericActualType(this.getClass())));
//                        }
//
//                        return set;
//                    }
//                }
//
//                return null;
//            }
//        } catch (Exception var13) {
//            var13.printStackTrace();
//            throw new SystemException(2005, "搜索引擎异常(根据条件查询列表): searchConditions={" + searchConditions + "}, error={" + var13.getLocalizedMessage() + "}");
//        }
//    }

}
