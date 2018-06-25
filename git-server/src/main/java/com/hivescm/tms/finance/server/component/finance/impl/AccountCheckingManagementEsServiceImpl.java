package com.hivescm.tms.finance.server.component.finance.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.tms.api.dto.es.sign.AccountCheckingManagementEsDTO;
import com.hivescm.tms.finance.server.component.finance.AccountCheckingManagementEsService;
import com.hivescm.tms.finance.server.config.EsConfig;
@Service
public class AccountCheckingManagementEsServiceImpl implements AccountCheckingManagementEsService {
	
	@Autowired
	private ESSearchService esSearchService;

	@Override
	public Boolean batchSave(List<AccountCheckingManagementEsDTO> list) throws Exception{
		boolean esResult = new DefaultAbstractSearchSaveExecutor<AccountCheckingManagementEsDTO>(esSearchService) {
			public EsConfig getConfig() {
				return EsConfig.accountChecking();
			}
		}.execute(list);
		return esResult;
	}
	
	
	@Override
	public List<AccountCheckingManagementEsDTO> getAllSelective(List<SearchCondition> searchConditions,
			List<OrderCondition> orderConditions, PageCondition pageCondition) throws Exception {
		List<AccountCheckingManagementEsDTO>  list = new ArrayList<AccountCheckingManagementEsDTO>();
		list = new DefaultAbstractSearchQueryExecutor<AccountCheckingManagementEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.accountChecking();
			}
		}.list(searchConditions, orderConditions, pageCondition);
		
		return list;
	}

}
