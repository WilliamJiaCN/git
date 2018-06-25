package com.hivescm.tms.api.dto.es.outbill.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompanyListRespDTO {

	@ApiModelProperty("外发公司ID")
	private Long outCompanyId;
	
	@ApiModelProperty("外发公司名称")
	private String outCompanyName;
	
	@ApiModelProperty("联系人")
	private String contactUser;
	
	@ApiModelProperty("联系电话")
	private String contactMobileNum;
	
}
