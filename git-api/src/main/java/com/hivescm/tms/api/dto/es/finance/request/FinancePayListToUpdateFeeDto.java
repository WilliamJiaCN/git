package com.hivescm.tms.api.dto.es.finance.request;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinancePayListToUpdateFeeDto {

	@ApiModelProperty(value = "单据类型 1.运单 2.派车单 3.发车配载单 4.外发中转单 5 装货单 6 卸货单 7 到货确认单")
	private Integer sheetType;
	
	@ApiModelProperty(value = "费用类型")
	private String feeType;
	
	@Mapping
	@ApiModelProperty(value = "单据ID(批次号ID)")
	private Long sheetId;
}
