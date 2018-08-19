package com.hivescm.tms.api.dto.es.distribution;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 司机状态ES DTO
 * @author ke.huang
 * @date 2017年8月28日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverStatusEsDTO implements Serializable{
	private static final long serialVersionUID = 6708134160447860080L;

	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
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
	@Mapping // >> 不记录数据库
	@ApiModelProperty("是否在线")
	private Boolean online;
	@Mapping
	@ApiModelProperty("车辆ID")
    private Integer vehicleId;
	@Mapping
	@ApiModelProperty("车牌号")
	private String vehicleNo;
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
	@Mapping
	@ApiModelProperty("司机头像")
	private String headPic;
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("更新人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("更新人姓名")
	private String updateUserName;
	@Mapping
	@ApiModelProperty("更新时间")
    private Long updateTime;
	
	

}