package com.hivescm.tms.api.dto.es.order.component;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class TmsOrderWaybillInfoDTO {
	
	private @Mapping({"OrderWaybillDO.orderId","OrderEsDTO.id"}) Long orderId;
	
	private @Mapping Long warehouseId;
	
	private @Mapping Integer carrierId;
	
	private @Mapping Integer branchId;
	
	private @Mapping String branchName;
	
	private @Mapping String fromId;
	
	private @Mapping String destId;
	
	private @Mapping BigDecimal freight;
	
	private @Mapping Long effectiveness;
	
	private @Mapping Long waybillId;
	
	private @Mapping String waybillCode;
}
