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
public class VehicleQueryDTO {

	@Logger
	@ApiModelProperty(value="车牌号")
	private String vehicleNo;
	@ApiModelProperty(value="车辆类型")
	private Integer vehicleType;
	@ApiModelProperty(value="车型")
	private Integer vehicleModels;
//	private Integer carrierId;
//	private String carrierName;
	@Required
	private Long companyId;
	@Mapping
	@Required
	@ApiModelProperty(value="操作网点ID",required=true)
	private List<Long> branchId;

}
