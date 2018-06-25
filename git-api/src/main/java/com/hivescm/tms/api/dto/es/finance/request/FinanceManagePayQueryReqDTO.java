package com.hivescm.tms.api.dto.es.finance.request;


import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceManagePayQueryReqDTO {
	@ApiModelProperty("来源单ID")
	private Long sheetId;

	@ApiModelProperty("来源单号")
	private String dataSourceCode;

	@ApiModelProperty("费用类型")
	private String feeType;
	
	@ApiModelProperty(value = "单据类型 1.运单 2.派车单 3.发车配载单 4.外发中转单 5 装货单 6 卸货单 7 到货确认单")
	private Integer sheetType;
}
