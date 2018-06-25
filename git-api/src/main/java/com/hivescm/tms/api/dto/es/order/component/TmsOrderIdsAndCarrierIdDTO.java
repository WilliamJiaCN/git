package com.hivescm.tms.api.dto.es.order.component;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TmsOrderIdsAndCarrierIdDTO {
	
	private  List<Long> waybillIds;
	
	private Integer carrierId;

}
