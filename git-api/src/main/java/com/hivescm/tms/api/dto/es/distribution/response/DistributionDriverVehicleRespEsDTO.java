package com.hivescm.tms.api.dto.es.distribution.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.dispatcher.DistributionVehicleStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 城配司机车辆Response DTO
 * @author ke.huang
 * @date 2017年8月31日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverVehicleRespEsDTO implements Serializable{
	private static final long serialVersionUID = 5515857463193520270L;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	@Mapping
	@ApiModelProperty("公司名称")
    private String companyName;

	/**
	 * driver
	 */
	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	@Mapping
	@ApiModelProperty("手机号码")
	private String phoneNo;
	@Mapping
	@ApiModelProperty("是否上班")
    private Boolean isWork;
	@Mapping
	@ApiModelProperty("是否在线")
	private Boolean online;
	@Mapping
	@ApiModelProperty("是否接收整车")
	private Boolean receiveAlone; // >> 
	@Mapping
	@ApiModelProperty("是否接收零担拼车")
    private Boolean receiveSplit; // >> 
	@Mapping
	@ApiModelProperty("接收距离（KM）")
    private Integer receiveDistance; // >> 
	@Mapping
	@ApiModelProperty("目的地区域 逗号间隔 全区=-1")
    private String destId; // >> 
	@Mapping
	@ApiModelProperty("目的地区域名称 逗号间隔")
    private String destName; // >> 
	@Mapping
	@ApiModelProperty("待命区域 逗号间隔 全区=-1")
    private String standbyId; // >> 
	@Mapping
	@ApiModelProperty("待命区域名称 逗号间隔")
    private String standbyName; // >> 
	@Mapping
	@ApiModelProperty("经度")
	private BigDecimal lon;
	@Mapping
	@ApiModelProperty("纬度")
	private BigDecimal lat;
	@Mapping
	@ApiModelProperty("定位地址")
	private String locationAddress;
	@Mapping
	@ApiModelProperty("定位时间")
	private Long locationTime;
	@Mapping
	@ApiModelProperty("智能调度是否可用")
	private Boolean used;
	/**
	 * vehicle
	 */
	@Mapping
	@ApiModelProperty("车辆ID")
    private Integer vehicleId;
	@Mapping
	@ApiModelProperty("车牌号码") 
    private String vehicleNum;
	@Mapping
	@ApiModelProperty("可载体积") 
    private BigDecimal availableVolume;
	@Mapping
	@ApiModelProperty("可载重量") 
    private BigDecimal availableWeight;
	@Mapping
	@ApiModelProperty("核载体积（M³）") 
    private BigDecimal checkVolume;
	@Mapping
	@ApiModelProperty("核载质量（KG）") 
    private Integer checkQuality;
	@Mapping
    @ApiModelProperty("车辆载重状态(NO_LOAD=空载 HALF_LOAD=半载 FULL_LOAD=满载)")
    private DistributionVehicleStatusEnum status;
	@Mapping
	@ApiModelProperty("车辆类型ID")
    private Integer vehicleType;
	@Mapping
    @ApiModelProperty("车辆类型")
    private String vehicleTypeName;
}
