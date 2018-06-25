package com.hivescm.tms.api.dto.es.outbill.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetCompanyListReqDTO {

	@ApiModelProperty("网点ID")
	private Long branchId;
	
	@ApiModelProperty("用户ID")
	private Integer uesrId;
	
	@ApiModelProperty("用于检索")
	private String name;
	
}
