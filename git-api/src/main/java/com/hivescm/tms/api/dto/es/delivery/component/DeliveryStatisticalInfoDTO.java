package com.hivescm.tms.api.dto.es.delivery.component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
@Data
@ToString
public class DeliveryStatisticalInfoDTO {
	
	@ApiModelProperty(value = "单数")
	private List<AcceptDeliveryOrderStatistics> warehouseInfo;
	
	@ApiModelProperty(value = "单数")
    private Integer amount;
    
    @ApiModelProperty(value = "重量")
    private BigDecimal weight;
    
    @ApiModelProperty(value = "体积")
    private BigDecimal volume; 

}
