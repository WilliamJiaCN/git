package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 城配车辆载重增加/减少请求接口
 * 用于司机接单、签收对车辆载重状态变更场景
 * @author ke.huang
 * @date 2017年9月14日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionVehicleLoadedReqDTO implements Serializable{
	private static final long serialVersionUID = -5559945247143767336L;
	private @Mapping() @ApiModelProperty(notes="公司ID",required=true) Long companyId;
	private @Mapping(value="updateUser")  @ApiModelProperty("修改人ID") Integer updateUserId;
	private @Mapping @ApiModelProperty("修改人姓名") String updateUserName;
	private @Mapping @ApiModelProperty("修改时间") Long updateTime;
	private @Mapping @ApiModelProperty("车辆ID") Integer vehicleId;
	private @Mapping @ApiModelProperty("增加(正)/减少(负)的体积") BigDecimal volume;
	private @Mapping @ApiModelProperty("增加(正)/减少(负)的重量") BigDecimal weight;
	
	public Boolean isValid(){
		return null != companyId && null != vehicleId && null != volume && null != weight && ((volume.doubleValue() >= 0 && weight.doubleValue() >= 0 ) || (volume.doubleValue()<=0 && weight.doubleValue() <= 0));
	}
	
	/**
	 * 是否减载重
	 * @return
	 */
	public Boolean isReduce(){
		return volume.doubleValue()<0 && weight.doubleValue() < 0;
	}
}
