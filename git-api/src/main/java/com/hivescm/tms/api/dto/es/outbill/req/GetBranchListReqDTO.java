package com.hivescm.tms.api.dto.es.outbill.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetBranchListReqDTO {

	@ApiModelProperty("外发公司ID")
	private Long outCompanyId;
	
	@ApiModelProperty("网点ID")
	private Integer branchId;
	
	@ApiModelProperty("用户ID")
	private Integer uesrId;
	
}
