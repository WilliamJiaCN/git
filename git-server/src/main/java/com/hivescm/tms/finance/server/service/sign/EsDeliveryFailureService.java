package com.hivescm.tms.finance.server.service.sign;

import com.hivescm.common.domain.PagedList;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.tms.api.dto.es.sign.DeliveryFailureEsDTO;

import java.util.List;

public interface EsDeliveryFailureService {

	void deleteDeliveryFailureById(Long id);

	Boolean insertDeliveryFailureEsDTO(DeliveryFailureEsDTO deliveryFailureEsDTO);

	Boolean updateDeliveryFailureEsDTO(DeliveryFailureEsDTO deliveryFailureEsDTO);



	DeliveryFailureEsDTO queryByDetailId(Long dispatcherDetailId);

	PagedList<DeliveryFailureEsDTO> queryPage(String deliverySign, String like, List<SearchCondition> searchConditions, PageCondition pageCondition, List<OrderCondition> orderConditions);
}
