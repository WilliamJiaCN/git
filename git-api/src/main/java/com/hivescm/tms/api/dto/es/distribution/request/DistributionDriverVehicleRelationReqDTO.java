package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 司机关联车辆变更请求DTO 
 * @author ke.huang
 * @date 2017年9月14日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverVehicleRelationReqDTO implements Serializable{
	private static final long serialVersionUID = 5943394540023117951L;
	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;
	@Mapping
	@ApiModelProperty("车辆ID")
	private Integer vehicleId;
	@Mapping
	@ApiModelProperty("车牌号")
	private String vehicleNo;
	@Mapping
	@ApiModelProperty("更新人")
	private Integer updateUser;
	@Mapping
	@ApiModelProperty("更新人姓名")
	private String updateUserName;
	@Mapping
	@ApiModelProperty("更新时间")
	private Long updateTime;
	
	public Boolean isValid(){
		return null != this.companyId && null != this.driverId && null != this.vehicleNo && null != this.vehicleId;
	}
}
