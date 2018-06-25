package com.hivescm.tms.api.dto.es.finance.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceDeleteReqDTO {

	@ApiModelProperty("公司ID")
	private Long companyId;
	
	@ApiModelProperty("来源单号")
	private String code;
	
	@ApiModelProperty("来源单号ID")
	private Long waybillId;
	
    @ApiModelProperty("中转id 中转时必填")
    private Long transitBillId;
	
	@ApiModelProperty("中转单号  中转时必填")
	private String transitCode;

	@ApiModelProperty("付款方式")
	private Integer payway;
}
