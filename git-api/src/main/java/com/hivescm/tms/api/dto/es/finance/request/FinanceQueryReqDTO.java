package com.hivescm.tms.api.dto.es.finance.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceQueryReqDTO {

	@ApiModelProperty("公司ID")
	private Long companyId;

	@ApiModelProperty("批次号")
	private String batchCode;
}
