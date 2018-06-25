package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceForUpdateWaybill {

	@Mapping
	@ApiModelProperty("来源单号")
	private String code;
	
	@Mapping
	@ApiModelProperty("来源单号ID 中转时必填")
	private Long waybillId;
	
	@ApiModelProperty("修改类型 1发货人 2收货人")
	private Integer type;
}
