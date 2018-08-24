package com.hivescm.tms.api.dto.es.order.component;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.order.OrderCapacityEsDTO;
import com.hivescm.tms.api.dto.es.order.OrderEsDTO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TmsOrderInfoDTO {
	@Required
	private OrderEsDTO orderEsDTO;
	
	@Required
	private OrderCapacityEsDTO orderCapacityEsDTO;

}
