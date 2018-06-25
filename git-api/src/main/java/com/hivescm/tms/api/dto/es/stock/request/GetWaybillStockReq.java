package com.hivescm.tms.api.dto.es.stock.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetWaybillStockReq {
	
	 @ApiModelProperty(value = "库存网点ID")
	 private Integer orgId;
	 @ApiModelProperty(value = "运单号")
	 private String code;
}
