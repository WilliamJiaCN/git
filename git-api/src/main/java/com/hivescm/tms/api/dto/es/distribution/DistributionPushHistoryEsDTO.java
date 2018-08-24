package com.hivescm.tms.api.dto.es.distribution;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 城配推送表
 *
 * @author
 */
@Data
@ToString
public class DistributionPushHistoryEsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键")
	private @Mapping Long id;

	@ApiModelProperty("公司ID")
	private @Mapping Long companyId;

	@ApiModelProperty("公司名称")
	private @Mapping String companyName;

	@ApiModelProperty("运单ID")
	private @Mapping({"id","waybillId"}) Long waybillId;

	@ApiModelProperty("预约提货时间")
	private @Mapping Long deliveryPickTime;

	@ApiModelProperty("预约送货时间")
	private Long deliverySendTime;

	@ApiModelProperty("起始地")
	private @Mapping String invoiceAddress;

	@ApiModelProperty("目的地")
	private @Mapping String receiptAddress;

	@ApiModelProperty("运输类型")
	private @Mapping String transportType;

	@ApiModelProperty("发布时间")
	private @Mapping("createTime") Long pushHistoryTime;

	/**
	 * 总体积
	 */
	@ApiModelProperty("总体积")
	private @Mapping String volume;

	/**
	 * 总重量
	 */
	@ApiModelProperty("总重量")
	private @Mapping String weight;

	@ApiModelProperty("总件数")
	private @Mapping Integer totalNum;

	@ApiModelProperty("商品名称")
	private @Mapping String goodsName;
	/**
	 * 是否整车
	 */
	@ApiModelProperty("是否整车")
	private @Mapping Boolean truckLoad;

	@ApiModelProperty("特殊要求")
	private @Mapping String specialRequire;

	@ApiModelProperty("代收费用")
	private @Mapping String totalFee;

	@ApiModelProperty("代收货款")
	private @Mapping String deliveryFee;

	@ApiModelProperty("司机ID")
	private @Mapping Integer driverId;

	@ApiModelProperty("司机姓名")
	private @Mapping String driverName;

	@ApiModelProperty("司机手机号")
	private @Mapping String driverTelNumber;

	@ApiModelProperty("车辆ID")
	private @Mapping Integer vehicleId;

	@ApiModelProperty("车辆号码")
	private @Mapping String vehicleName;

	@ApiModelProperty("车队ID")
	private @Mapping Integer fleetId;

	@ApiModelProperty("车队名称")
	private @Mapping String fleetName;

	@ApiModelProperty("推送状态")
	private @Mapping Boolean pushStatus;

	@ApiModelProperty("推送时间")
	private @Mapping Long pushTime;

	@ApiModelProperty("是否给司机发送指派提醒")
	private @Mapping(value = "isRemind") Boolean remind;

	@ApiModelProperty("是否司机确认")
	private @Mapping Boolean isConfirm;

	/**
	 * 接单状态
	 */
	@ApiModelProperty("接单状态 1=未接单 2=已忽略 3=已接单 4=已失效")
	private @Mapping Integer taskStatus;
	/**
	 * 接单状态名称
	 */
	@ApiModelProperty("接单状态 1=未接单 2=已忽略 3=已接单 4=已失效")
	private @Mapping("releaseTypeName") String taskStatusName;

	@ApiModelProperty("接单时间")
	private @Mapping("acceptDate") Long taskTime;

	@ApiModelProperty("单据类型（1=指派单 2=调度单）")
	private @Mapping Integer billType;

	@ApiModelProperty("操作类型（1=指派司机、2=指派车队、3=地图指派 4=智能调度）")
	private @Mapping Integer operateType;

	@ApiModelProperty("操作类型（1=指派司机、2=指派车队、3=地图指派 4=智能调度）")
	private @Mapping String operateName;

	@ApiModelProperty("创建人")
	private @Mapping Integer createUser;

	@ApiModelProperty("创建人姓名")
	private @Mapping String createUserName;

	@ApiModelProperty("创建时间")
	private @Mapping("billTime") Long createTime;

	@ApiModelProperty("修改人")
	private @Mapping Integer updateUser;

	@ApiModelProperty("修改人姓名")
	private @Mapping String updateUserName;

	@ApiModelProperty("修改时间")
	private @Mapping("cancelReleaseTime") Long updateTime;

	@ApiModelProperty("发货地经度")
	private @Mapping Double invoiceLng;

	@ApiModelProperty("发货地纬度")
	private @Mapping Double invoiceLat;

	@ApiModelProperty("收货地经度")
	private @Mapping Double receiptLng;

	@ApiModelProperty("收货地纬度")
	private @Mapping Double receiptLat;
	@ApiModelProperty("运单号")
	private @Mapping String waybillCode;
	private @Mapping @ApiModelProperty("收货人省名称") String receiptProvName;

	private @Mapping @ApiModelProperty("收货人城市名称") String receiptCityName;

	private @Mapping @ApiModelProperty("收货人地区名称") String receiptDistrictName;

	private @Mapping @ApiModelProperty("收货人街道名称") String receiptStreetName;

	private @Mapping @ApiModelProperty("指派网点Id") Integer branchId;

	private @Mapping @ApiModelProperty("指派网点名称") String branchName;

	private @Mapping @ApiModelProperty("执行任务id") Integer taskId;

	private @Mapping @ApiModelProperty("执行任务名称") String taskName;

}
