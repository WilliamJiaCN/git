package com.hivescm.tms.api.dto.es.distribution.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 城配指派司机请求DTO
 * @author lutingting
 * @date 2017年8月9日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionTransDTO implements Serializable{
	private static final long serialVersionUID = -2801125059760228371L;
	
    @ApiModelProperty("id")
    private Long id;
	 /**
     * 公司id
     */
    @ApiModelProperty("公司ID")
    private Long companyId;
	
	@ApiModelProperty("公司名称")
    private String companyName;

	@ApiModelProperty("运单ID")
    private Long waybillId;

	@ApiModelProperty("司机ID")
    private Integer driverId;

	@ApiModelProperty("司机姓名")
    private String driverName;
	
	@ApiModelProperty("司机手机号")
    private String driverTelNumber;
	
	@ApiModelProperty("车对ID")
    private Integer fleetId;

	@ApiModelProperty("车队名称")
    private String fleetName;
	@ApiModelProperty("车辆ID")
    private Integer vehicleId;

	@ApiModelProperty("车辆名称")
    private String vehicleName;
	
	@ApiModelProperty("单据类型（1=指派单 2=调度单）")
	private  Integer billType;
	
	@ApiModelProperty("操作类型（1=指派司机、2=指派车队、3=地图指派 4=智能调度）")
	private Integer operateType;
	
	
	@ApiModelProperty("操作类型（1=指派司机、2=指派车队、3=地图指派 4=智能调度）")
	private   String operateName;
	
	
	private Long pushTime;
}
