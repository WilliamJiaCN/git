package com.hivescm.tms.api.dto.es.ltlorder;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VehicleTailAfterOrderEsDto implements Serializable{

	private static final long serialVersionUID = 1853895013183723022L;

	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	/**
	 * 公司id
	 */
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;
	/**
	 * 公司名称
	 */
	@ApiModelProperty("公司名称")
	private String companyName;
	/**
	 * 运单id
	 */
	@Mapping
	@ApiModelProperty("订单id")
	private Long orderId;
	/**
	 * 运单id
	 */
	@Mapping
	@ApiModelProperty("订单Code")
	private String orderCode;
	/**
	 * 司机ID
	 *
	 */
	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;
	/**
	 * 司机姓名
	 */
	@ApiModelProperty("司机姓名")
	private String driverName;
	/**
	 * 司机姓名
	 */
	@ApiModelProperty("司机手机号")
	private String driverPhone;
	/**
	 * 状态
	 */
	@Mapping
	@ApiModelProperty("状态")
	private Integer status;
	/**
	 * 状态
	 */
	@Mapping
	@ApiModelProperty("状态名称")
	private String statusName;
	/**
	 * 经度
	 */
	@Mapping
	@ApiModelProperty("经度")
	private String lngs;
	/**
	 * 纬度
	 */
	@Mapping
	@ApiModelProperty("纬度")
	private String lats;
	/**
	 * 跟踪消息
	 */
	@Mapping
	@ApiModelProperty("跟踪消息")
	private String informat;

	/**
	 * 创建人
	 */
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;

	/**
	 * 创建人姓名
	 */
	@Mapping
	@ApiModelProperty("创建人姓名")
	private  String createUserName;
	/**
	 * 创建时间
	 */
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;

	/**
	 * 修改人
	 */
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	/**
	 * 修改时间
	 */
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;

	
	@Mapping
	@ApiModelProperty("车辆ID")
	private Integer vehicleId;
	/**
	 * 车牌号码
	 */
	@Mapping
	@ApiModelProperty("车牌号")
	private String vehicleNo;

	/**
	 * 发货网点ID
	 */
	@Mapping
	@ApiModelProperty("网点ID")
	private Integer orgId;
	/**
	 * 网点名称
	 */
	@Mapping
	@ApiModelProperty(value="网点名称")
	private String orgName;
}
