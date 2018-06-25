package com.hivescm.tms.api.dto.es.distribution.request;
import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.dispatcher.DistributionVehicleStatusEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 车辆状态请求DTO
 * @author ke.huang
 * @date 2017年8月11日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionVehicleStatusReqDTO implements Serializable{
	private static final long serialVersionUID = 5538680216276469096L;
	private @Mapping @ApiModelProperty(notes="公司ID",required=true) Long companyId;
	private @Mapping @ApiModelProperty("车辆ID") Integer vehicleId;
	private @Mapping @ApiModelProperty("车辆状态枚举") DistributionVehicleStatusEnum status;
	private @Mapping("updateUser") @ApiModelProperty("修改人ID") Integer updateUserId;
	private @Mapping @ApiModelProperty("修改人姓名") String updateUserName;
	private @Mapping @ApiModelProperty("修改时间") Long updateTime;
	
}