package com.hivescm.tms.finance.server.service.sign.impl;

import com.google.common.collect.Lists;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchDeleteExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsGoodsDetailsEsDTO;
import com.hivescm.tms.api.enums.biz.sign.SignGoodsTypeEnum;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.sign.EsSignRefuseDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 拒收单详情ES服务
 * @author 杨彭伟
 * @date 2018-01-30 11:54
 */
@Slf4j
@Service
public class EsSignRefuseDetailServiceImpl implements EsSignRefuseDetailService {
    @Autowired
    private ESSearchService esSearchService;

    @Override
    public List<TmsGoodsDetailsEsDTO> queryRefuseSignGoodsEsDTOList(Long waybillId) {
        List<SearchCondition> searchConditions = SearchConditionUtils.start()
                .addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL).end();
        return new DefaultAbstractSearchQueryExecutor<TmsGoodsDetailsEsDTO>(
                esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSignGoodsDetails();
            }
        }.list(searchConditions);
    }

    @Override
    public List<TmsGoodsDetailsEsDTO> queryRefuseSignGoodsEsDTOList(Long waybillId, SignEsDTO signEsDTO) {
        List<SearchCondition> searchConditions = SearchConditionUtils.start()
                .addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL).end();
        List<TmsGoodsDetailsEsDTO> goods = new DefaultAbstractSearchQueryExecutor<TmsGoodsDetailsEsDTO>(
                esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSignGoodsDetails();
            }
        }.list(searchConditions);
        int refuseNumber = 0, signNumber = 0;
        if (goods != null && goods.size() > 0) {
            // 计算拒收货物总件数
            refuseNumber = goods.stream()
                    .filter(item -> item.getRefuseNum() != null
                            && item.getGoodsType() == SignGoodsTypeEnum.REFUSE_SIGN.getType())
                    .mapToInt(TmsGoodsDetailsEsDTO::getRefuseNum).sum();
            // 计算签收货物总件数
            signNumber = goods.stream()
                    .filter(item -> item.getSignNum() != null
                            && item.getGoodsType() == SignGoodsTypeEnum.SIGN.getType())
                    .mapToInt(TmsGoodsDetailsEsDTO::getSignNum).sum();
        }
        signEsDTO.setSignNumber(signNumber);
        signEsDTO.setRefuseNumber(refuseNumber);
        return goods;
    }

    @Override
    public Boolean deleteTmsGoodsDetailsEsDTOByRefuseId(Long refuseId) {
        List<SearchCondition> searchConditions = Lists.newArrayList();
        SearchCondition condition = new SearchCondition.Builder()
                .setFieldName("refuseId")
                .setConditionExpression(ConditionExpressionEnum.EQUAL)
                .setSingleValue(String.valueOf(refuseId)).build();
        searchConditions.add(condition);
        return new DefaultAbstractSearchDeleteExecutor<TmsGoodsDetailsEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSignGoodsDetails();
            }
        }.execute(searchConditions);
    }

    @Override
    public Boolean insertTmsGoodsDetailsEsDTOList(List<TmsGoodsDetailsEsDTO> tmsGoodsDetailsEsDTOList) {
        return new DefaultAbstractSearchSaveExecutor<TmsGoodsDetailsEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSignGoodsDetails();
            }
        }.execute(tmsGoodsDetailsEsDTOList);
    }
}
