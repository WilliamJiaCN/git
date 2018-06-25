package com.hivescm.tms.api.dto.es.outbill.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutSignCancelReqDTO {

	@ApiModelProperty("外发明细ID")
	private Long outbillDetailId;
	
	@ApiModelProperty("公司ID")
    private Long companyId;
	
	private Integer uesrId;
	
}
