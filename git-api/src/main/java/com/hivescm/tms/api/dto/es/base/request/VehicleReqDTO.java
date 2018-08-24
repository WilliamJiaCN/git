package com.hivescm.tms.api.dto.es.base.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class VehicleReqDTO extends PageQurey {

	private Integer id;
	@Logger
	private String vehicleNo;
	private Integer vehicleType;
	private Integer vehicleModels;
	private Integer status;
	private Integer checkStatus;
	private Integer carrierId;
	private Integer createUser;
	private Integer updateUser;
	private String startTime;
	private String carrierName;
	private String endTime;
	private Long companyId;
	@Mapping
	@ApiModelProperty(value="操作网点ID",required=true)
	private List<Long> branchId;

}
