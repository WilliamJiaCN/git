package com.hivescm.tms.api.dto.db.base;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 
@Talbe(name="vehicle_contact_driver")
 */
@Data
@ToString
public class TmsDriverVehicleRespDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private @Mapping @ApiModelProperty("司机ID") Integer driverId;
	
	private @Mapping @ApiModelProperty("司机手机号码")String phoneNo;
	
	private @Mapping @ApiModelProperty("车辆ID") Integer vehicleId;
	
	private @Mapping@ApiModelProperty("车牌号码") String vehicleNo;
	
	private @Mapping @ApiModelProperty("司机头像url") String headPic;
	
	private @Mapping @ApiModelProperty("司机姓名") String driverName;
	
	private @Mapping @ApiModelProperty("公司ID") Integer companyId;
}