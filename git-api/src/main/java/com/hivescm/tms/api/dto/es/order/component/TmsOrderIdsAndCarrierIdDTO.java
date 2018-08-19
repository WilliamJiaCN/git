package com.hivescm.tms.api.dto.es.order.component;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TmsOrderIdsAndCarrierIdDTO {
	
	private  List<Long> waybillIds;
	
	private Integer carrierId;

}
