package com.hivescm.tms.api.dto.es.order.component;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
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
