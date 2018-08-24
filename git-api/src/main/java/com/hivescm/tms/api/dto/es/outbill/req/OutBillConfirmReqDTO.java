package com.hivescm.tms.api.dto.es.outbill.req;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutBillConfirmReqDTO {

	@ApiModelProperty("外发单ID")
	private Long outbillId;
	
	@ApiModelProperty("公司ID")
    private Long companyId;
	
	@ApiModelProperty("公司名称")
    private String companyName;
	
	@ApiModelProperty("网点ID")
    private Long branchId;
	
	@ApiModelProperty("用户ID")
	private Integer uesrId;
	
	@ApiModelProperty("用户姓名")
	private String uesrName;
	
	@Mapping
	@ApiModelProperty("外发确认时间")
    private Long currentTime;
	
}
