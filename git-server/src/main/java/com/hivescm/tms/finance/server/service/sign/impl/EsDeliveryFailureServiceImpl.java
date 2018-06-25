package com.hivescm.tms.finance.server.service.sign.impl;

import com.hivescm.common.domain.PagedList;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.elasticsearch.conf.TypeIndexConfiguration;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchDeleteExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchUpdateExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.sign.DeliveryFailureEsDTO;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.sign.EsDeliveryFailureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EsDeliveryFailureServiceImpl implements EsDeliveryFailureService {
	@Autowired
	private ESSearchService eSSearchService;

	@Autowired
	private ESStatisticService esStatisticService;

	@Override
	public void deleteDeliveryFailureById(Long id) {
		 new DefaultAbstractSearchDeleteExecutor<DeliveryFailureEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.deliveryFailure();
			}
		}.execute(id);
	}

	@Override
	public Boolean insertDeliveryFailureEsDTO(DeliveryFailureEsDTO deliveryFailureEsDTO){
		return new DefaultAbstractSearchSaveExecutor<DeliveryFailureEsDTO>(eSSearchService){
			@Override
			public EsConfig getConfig(){ return EsConfig.deliveryFailure(); }
		}.execute(deliveryFailureEsDTO);
	}

	@Override
	public Boolean updateDeliveryFailureEsDTO(DeliveryFailureEsDTO deliveryFailureEsDTO) {
		return new DefaultAbstractSearchUpdateExecutor<DeliveryFailureEsDTO>(eSSearchService){
			@Override
			public EsConfig getConfig(){ return EsConfig.deliveryFailure(); }
		}.execute(deliveryFailureEsDTO);
	}

	@Override
	public DeliveryFailureEsDTO queryByDetailId(Long dispatcherDetailId) {
		List<SearchCondition> s = SearchConditionUtils.start().addEqualCondition("dispatcherDetailId", dispatcherDetailId).end();


		return new DefaultAbstractSearchQueryExecutor<DeliveryFailureEsDTO>(eSSearchService) {
			@Override
			public TypeIndexConfiguration getConfig() {
				return EsConfig.deliveryFailure();
			}
		}.get(s);


	}

	@Override
	public PagedList<DeliveryFailureEsDTO> queryPage(String cascade, String like, List<SearchCondition> searchConditions, PageCondition pageCondition, List<OrderCondition> orderConditions) {
//
//		List<DeliveryFailureEsDTO> list = new DefaultAbstractSearchQueryExecutor<DeliveryFailureEsDTO>(eSSearchService) {
//			@Override
//			public TypeIndexConfiguration getConfig() {
//				return EsConfig.deliveryFailure();
//			}
//		}.cascade(cascade, like, searchConditions, orderConditions, pageCondition);

        List<DeliveryFailureEsDTO> list = new DefaultAbstractSearchQueryExecutor<DeliveryFailureEsDTO>(eSSearchService) {
			@Override
			public TypeIndexConfiguration getConfig() {
				return EsConfig.deliveryFailure();
			}
		}.list( searchConditions, orderConditions, pageCondition);


		if(list==null||list.isEmpty()){

			return PagedList.createInstance(pageCondition.getCurrentPage(),pageCondition.getPageSize(),pageCondition.getTotalDocs().intValue(), Collections.EMPTY_LIST);
		}

		return PagedList.createInstance(pageCondition.getCurrentPage(),pageCondition.getPageSize(),pageCondition.getTotalDocs().intValue(), list);
	}

}
