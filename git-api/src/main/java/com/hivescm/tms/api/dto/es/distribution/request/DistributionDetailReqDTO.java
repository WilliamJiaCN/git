package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class DistributionDetailReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping 
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@Mapping
	@ApiModelProperty(value="司机ID",required=true)
	private Integer driverId;
}
