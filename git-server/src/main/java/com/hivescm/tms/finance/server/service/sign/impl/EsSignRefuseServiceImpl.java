package com.hivescm.tms.finance.server.service.sign.impl;

import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.conf.TypeIndexConfiguration;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchDeleteExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchUpdateExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.sign.EsSignRefuseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨彭伟
 * @date 2018-01-29 16:40
 */
@Slf4j
@Service
public class EsSignRefuseServiceImpl implements EsSignRefuseService {
    @Autowired
    private ESSearchService esSearchService;

    @Override
    public Boolean insertSignRefuseEsDTO(SignRefuseEsDTO SignRefuseEsDTO) {
        return new DefaultAbstractSearchSaveExecutor<SignRefuseEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.execute(SignRefuseEsDTO);
    }

    @Override
    public Boolean updateSignRefuseEsDTO(SignRefuseEsDTO SignRefuseEsDTO) {
        return new DefaultAbstractSearchUpdateExecutor<SignRefuseEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.execute(SignRefuseEsDTO);
    }

    @Override
    public Boolean deleteSignRefuseEsDTO(Long id) {
        return new DefaultAbstractSearchDeleteExecutor<SignRefuseEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.execute(id);
    }

    @Override
    public Boolean deleteSignRefuseEsDTOBySignId(Long signId){
        List<SearchCondition> conditions = SearchConditionUtils.start()
                .addEqualCondition("sign_id", signId).end();
        return new DefaultAbstractSearchDeleteExecutor<SignRefuseEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.execute(conditions);
    }

    @Override
    public Boolean deleteSignRefuseEsDTOBySignIds(List<Long> signIds){
        List<SearchCondition> conditions = SearchConditionUtils.start().addCondition("sign_id",signIds, ConditionExpressionEnum.IN).end();
        return new  DefaultAbstractSearchDeleteExecutor<SignRefuseEsDTO>(esSearchService){
            @Override
            public EsConfig getConfig(){ return EsConfig.refuseSign(); }
        }.execute(conditions);
    }

    @Override
    public SignRefuseEsDTO getSignRefuseEsDTO(Long id) {
    	 List<SearchCondition> conditions = SearchConditionUtils.start()
                 .addEqualCondition("waybillId", id).end();
        return new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.get(conditions);
    }

    @Override
    public SignRefuseEsDTO getSignRefuseEsDTOByWaybillId(Long waybillId) {
        List<SearchCondition> conditions = SearchConditionUtils.start()
                .addEqualCondition("waybillId", waybillId).end();
        return new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.get(conditions);
    }

    @Override
    public SignRefuseEsDTO getSignRefuseEsDTOByDispatcherDetailId(Long dispatcherDetailId) {
        List<SearchCondition> conditions = SearchConditionUtils.start()
                .addEqualCondition("dispatcherDetailId", dispatcherDetailId).end();
        return new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.get(conditions);

    }

	@Override
	public Boolean insertBatchSignRefuseEsDTO(List<SignRefuseEsDTO> SignRefuseEsDTOs) {
		 return new DefaultAbstractSearchSaveExecutor<SignRefuseEsDTO>(esSearchService) {
	            @Override
	            public EsConfig getConfig() {
	                return EsConfig.refuseSign();
	            }
	        }.execute(SignRefuseEsDTOs);
	}

	@Override
	public Boolean updateBatchSignRefuseEsDTO(List<SignRefuseEsDTO> signRefuseEsDTOs) {
		Boolean flag = new DefaultAbstractSearchUpdateExecutor<SignRefuseEsDTO>(esSearchService) {

			@Override
			public EsConfig getConfig() {
				return EsConfig.refuseSign();
			}
		}.execute(signRefuseEsDTOs);
		return flag;
	}

	@Override
	public List<SignRefuseEsDTO> queryRefuseSignBySignIds(List<Long> signIds) {
		 List<SearchCondition> conditions = SearchConditionUtils.start()
	                .addCondition("signId", signIds.toArray(),ConditionExpressionEnum.IN).end();
		 List<SignRefuseEsDTO> resp =  new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
	            @Override
	            public EsConfig getConfig() {
	                return EsConfig.refuseSign();
	            }
	        }.list(conditions);
		return resp;
	}

    @Override
    public SignRefuseEsDTO queryRefuseSignBySignId(Long signId) {
        List<SearchCondition> conditions = SearchConditionUtils.start()
                .addEqualCondition("signId", signId).end();
        return new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.get(conditions);
    }
}
