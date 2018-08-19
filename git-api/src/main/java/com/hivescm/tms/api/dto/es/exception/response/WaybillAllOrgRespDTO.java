package com.hivescm.tms.api.dto.es.exception.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillAllOrgRespDTO {
	
	
	@Mapping
	@ApiModelProperty("网点ID")
	private Integer branchId;
	
	@Mapping
	@ApiModelProperty("网点名称")
	private String branchName;

}
