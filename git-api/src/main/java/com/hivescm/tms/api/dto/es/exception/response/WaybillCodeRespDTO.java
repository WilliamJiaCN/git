package com.hivescm.tms.api.dto.es.exception.response;


import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillCodeRespDTO {

	@Mapping
	@ApiModelProperty("主键ID")
	private Long id;
	
	@Mapping
	@ApiModelProperty("运单号")
	private String code;
	
	@Mapping  
	@ApiModelProperty(value="公司id",hidden=true)
	private Long companyId ;
}
