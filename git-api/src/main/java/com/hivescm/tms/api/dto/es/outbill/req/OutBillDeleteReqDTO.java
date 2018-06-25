package com.hivescm.tms.api.dto.es.outbill.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutBillDeleteReqDTO {

	@ApiModelProperty("外发单ID")
	private Long outbillId;
	
	@ApiModelProperty("公司ID")
    private Long companyId;
	
	@ApiModelProperty("用户ID")
	private Integer uesrId;
	
}
