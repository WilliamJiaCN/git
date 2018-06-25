package com.hivescm.tms.api.dto.es.distribution.request;
import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 城配车辆可载量请求DTO
 * @author ke.huang
 * @date 2017年8月9日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionVehicleAvailableLoadReqDTO implements Serializable{
	private static final long serialVersionUID = -2801125059760228371L;
	private @Mapping() @ApiModelProperty(notes="公司ID",required=true) Long companyId;
	private @Mapping(value="updateUser")  @ApiModelProperty("修改人ID") Integer updateUserId;
	private @Mapping @ApiModelProperty("修改人姓名") String updateUserName;
	private @Mapping @ApiModelProperty("修改时间") Long updateTime;
	private @Mapping @ApiModelProperty("车辆ID") Integer vehicleId;
	private @Mapping @ApiModelProperty("可载体积") BigDecimal availableVolume;
	private @Mapping @ApiModelProperty("可载重量") BigDecimal availableWeight;
	
	public Boolean isValid(){
		return null != companyId && null != vehicleId && null != availableVolume && null != availableWeight;
	}
	
}