package com.hivescm.tms.api.dto.es.outbill.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BranchListRespDTO {

	@ApiModelProperty("网点ID")
	private Integer branchId;
	
	@ApiModelProperty("网点名称")
	private String branchName;
	
	@ApiModelProperty("网点联系人")
	private String contactUser;
	
	@ApiModelProperty("联系电话")
	private String contactMobileNum;
	
}
