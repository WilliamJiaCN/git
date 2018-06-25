package com.hivescm.tms.finance.server.service.sign.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchDeleteExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.sign.EsSignFeeService;

/**
 * 
 * @author boqiang.deng
 * @company 蜂网供应链(上海)管理有限公司
 * @version 2018年3月23日 下午5:08:46
 * 
 */
@Service
public class EsSignFeeServiceImpl implements EsSignFeeService {

	@Autowired
	private ESSearchService eSSearchService;
	@Override
	public Boolean insertSignFeeEsDTO(SignFeeEsDTO signFeeEsDTO) {
		return new DefaultAbstractSearchSaveExecutor<SignFeeEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signFee();
			}
		}.execute(signFeeEsDTO);
	}
	@Override
	public List<SignFeeEsDTO> querySignFeeEsDTOByWaybillId(Long waybillId) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL).end();
		List<SignFeeEsDTO> signFeeEsDTOs = 	new DefaultAbstractSearchQueryExecutor<SignFeeEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signFee();
            }
        }.list(scs);
		return signFeeEsDTOs;
	}
	@Override
	public Boolean deleteBySignIds(List<Long> signIds) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("signId", signIds.toArray(), ConditionExpressionEnum.IN).end();
		Boolean es = new DefaultAbstractSearchDeleteExecutor<SignFeeEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signGoodsDetail();
			}
		}.execute(scs);
		return es;
	}
	@Override
	public Boolean insertBatchSignFeeEs(List<SignFeeEsDTO> signFeeEsDTOs) {
		return new DefaultAbstractSearchSaveExecutor<SignFeeEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signFee();
			}
		}.execute(signFeeEsDTOs);
	}
	@Override
	public List<SignFeeEsDTO> querySignFeeEsDTOByWaybillId(List<Long> waybillId) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("waybillId", waybillId.toArray(), ConditionExpressionEnum.IN).end();
		List<SignFeeEsDTO> signFeeEsDTOs = 	new DefaultAbstractSearchQueryExecutor<SignFeeEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signFee();
            }
        }.list(scs);
		return signFeeEsDTOs;
	}
	@Override
	public SignFeeEsDTO querySignFeeEsDTOBySignId(Long signId) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("signId", signId, ConditionExpressionEnum.EQUAL).end();
		SignFeeEsDTO signFeeEsDTOs = 	new DefaultAbstractSearchQueryExecutor<SignFeeEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signFee();
            }
        }.get(scs);
		return signFeeEsDTOs;
	}
	@Override
	public Boolean deleteBySignId(Long signId) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("signId", signId, ConditionExpressionEnum.EQUAL).end();
		Boolean es = new DefaultAbstractSearchDeleteExecutor<SignFeeEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signGoodsDetail();
			}
		}.execute(scs);
		return es;
	}
}
