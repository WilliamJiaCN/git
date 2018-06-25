package com.hivescm.tms.finance.server.service.finance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.conf.TypeIndexConfiguration;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.tms.api.dto.es.finance.FinanceErrorLogDTO;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.EsFinanceErrorLogService;

@Service
public class EsFinanceErrorLogServiceImpl implements EsFinanceErrorLogService {

	@Autowired
	private ESSearchService esSearchService;
	
	@Override
	public Boolean insert(FinanceErrorLogDTO financeErrorLogDTO) {
		return new DefaultAbstractSearchSaveExecutor<FinanceErrorLogDTO>(esSearchService) {

			@Override
			public TypeIndexConfiguration getConfig() {
				return EsConfig.financeErrorLog();
			}
			
		}.execute(financeErrorLogDTO);
	}

}
