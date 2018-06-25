package com.hivescm.tms.finance.server.service.sign.impl;

import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchDeleteExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.sign.EsSignDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2018年3月23日 下午5:02:31
* 
*/
@Service
public class EsSignDetailsServiceImpl implements EsSignDetailsService{

	@Autowired
	private ESSearchService eSSearchService;
	@Override
	 public Boolean insertSignDetailsEsDTO(List<SignDetailsEsDTO> signDetailsEsDTOList) {
        return new DefaultAbstractSearchSaveExecutor<SignDetailsEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signGoodsDetail();
            }
        }.execute(signDetailsEsDTOList);
    }
	@Override
	public List<SignDetailsEsDTO> querySignDetailsEsDTOByWaybillId(Long waybillId) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL).end();
		List<SignDetailsEsDTO> signDetailsEsDTOs = 	new DefaultAbstractSearchQueryExecutor<SignDetailsEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signGoodsDetail();
            }
        }.list(scs);
		return signDetailsEsDTOs;
	}
	@Override
	public Boolean deleteBySignIds(List<Long> signIds) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("signId", signIds.toArray(), ConditionExpressionEnum.IN).end();
		Boolean es = new DefaultAbstractSearchDeleteExecutor<SignDetailsEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signGoodsDetail();
			}
		}.execute(scs);
		return es;
	}
	@Override
	public List<SignDetailsEsDTO> queryBySignIds(Collection<Long> signIds) {
		List<SearchCondition> scs = new ArrayList<>();
		scs.add(SearchConditionUtils.newInCondition("signId", signIds.toArray())) ;
		List<SignDetailsEsDTO> signDetails = 	new DefaultAbstractSearchQueryExecutor<SignDetailsEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signGoodsDetail();
            }
        }.list(scs);
		return signDetails;
	}
	
	@Override
	public List<SignDetailsEsDTO> queryBySignId(Long signId) {
		List<SearchCondition> scs = new ArrayList<>();
		scs.add(SearchConditionUtils.newEqualCondition("signId", signId)) ;
		List<SignDetailsEsDTO> signDetails = 	new DefaultAbstractSearchQueryExecutor<SignDetailsEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signGoodsDetail();
            }
        }.list(scs);
		return signDetails;
	}
	@Override
	public Boolean deleteBySignId(Long signId) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("signId", signId, ConditionExpressionEnum.EQUAL).end();
		Boolean es = new DefaultAbstractSearchDeleteExecutor<SignDetailsEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signGoodsDetail();
			}
		}.execute(scs);
		return es;
	}
}
