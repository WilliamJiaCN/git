package com.hivescm.tms.api.dto.es.distribution.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 城配司机个人中心统计响应DTO
 * @author ke.huang
 * @date 2017年8月28日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverCenterRespEsDTO implements Serializable{
	private static final long serialVersionUID = -987955070363065316L;
	@ApiModelProperty("司机ID")
	private Integer driverId;
	@ApiModelProperty("司机手机号码")
	private String phoneNo;
	@ApiModelProperty("车辆ID")
	private Integer vehicleId;
	@ApiModelProperty("车牌号码")
	private String vehicleNo;
	@ApiModelProperty("总接单数")
	private Integer totalSingular;
	@ApiModelProperty("服务评价")
	private BigDecimal evaluation;
	@ApiModelProperty("总里程数")
	private BigDecimal totalMileage;
	@ApiModelProperty("司机头像")
	private String headPic;
}
