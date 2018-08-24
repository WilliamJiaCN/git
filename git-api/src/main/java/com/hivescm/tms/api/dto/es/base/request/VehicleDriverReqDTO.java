package com.hivescm.tms.api.dto.es.base.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class VehicleDriverReqDTO{

	@Logger
	private String vehicleNo;
	@Required
	private Long companyId;
	@Mapping
	@ApiModelProperty(value="操作网点ID",required=true)
	@Required
	private List<Long> branchId;
	private Integer type;

}
