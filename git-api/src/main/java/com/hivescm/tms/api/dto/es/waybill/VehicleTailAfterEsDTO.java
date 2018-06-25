package com.hivescm.tms.api.dto.es.waybill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class VehicleTailAfterEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4582050987890549222L;
	/**
	 * 主键
	 */
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	/**
	 * 公司id
	 */
	@Mapping
	@Required
	@Logger
	@ApiModelProperty("公司id")
	private Integer companyId;
	/**
	 * 公司名称
	 */
	@ApiModelProperty("公司名称")
	private String companyName;
	/**
	 * 运单id
	 */
	@Mapping
	@Required
	@Logger
	@ApiModelProperty("运单id")
	private Long waybillId;
	/**
	 * 运单号
	 */
	@Mapping
	@ApiModelProperty("运单号")
	private String waybillCode;
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
	 * 司机手机号
	 */
	@ApiModelProperty("司机手机号")
	private String driverPhone;
	/**
	 * 状态
	 */
	@Mapping
	@ApiModelProperty("运单状态：1开单2揽收3派送4签收5发车6到达目的地")
	private Integer status;
	/**
	 * 状态
	 */
	@Mapping
	@ApiModelProperty("运单状态：1开单2揽收3派送4签收5发车6到达目的地")
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
	@ApiModelProperty(hidden = true)
	private Integer createUser;

	/**
	 * 创建人姓名
	 */
	@Mapping
	@ApiModelProperty("操作人")
	private  String createUserName;
	/**
	 * 创建时间
	 */
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;

	/**
	 * 车牌号码
	 */
	@Mapping
	@ApiModelProperty("车牌号码")
	private String vehicleNo;

	/**
	 * 当前操作网点ID
	 */
	@Required
	@Mapping
	@ApiModelProperty("当前操作网点ID")
	private Integer invoiceOrgId;
	/**
	 * 当前操作网点名称
	 */
	@Mapping
	@ApiModelProperty("当前操作网点名称	")
	private String invoiceOrgName;
	/**
	 * 灯泡类型
	 * */
	@Mapping
	@ApiModelProperty("是否可见1：可见：0：不可见  默认可见")
	private Integer lightbulbType;
	@Mapping
	@ApiModelProperty("当前网点城市名称")
	private String orgCityName;
	@Mapping
	@ApiModelProperty("当前网点街道名称")
	private String orgStreetName;
	@Mapping
	@ApiModelProperty("当前网点address")
	private String orgAddress;
	@Mapping
	@ApiModelProperty("是否人工录入true 是  false否")
	private Boolean artificial;
	@Mapping
    @ApiModelProperty("定位信息，是否定位失败")
    private String locateMessage;
    @Mapping
    @ApiModelProperty("签收人姓名")
    private String signUserName;

}
