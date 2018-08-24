package com.hivescm.tms.api.dto.db.base;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
@ToString
public class BaseVehicleDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Mapping("vehicleId")
	private Integer id;
    /**
     * 公司ID
     */
	@Mapping
    @ApiModelProperty("公司名称")
    private Long companyId;
    @Mapping
    @ApiModelProperty("公司名称")
    private String companyName;
    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("网点ID")
    private Long branchId;
    @Mapping
    @ApiModelProperty("网点名称")
    private String branchName;
	/**
	 * 车牌号码
	 */
    @Logger
    @Mapping
	@ApiModelProperty("车牌号码")
	private String vehicleNo;

	/**
	 * 车牌用途
	 */
    @Mapping
	@ApiModelProperty("车牌用途")
	private Integer vehicleUse;

	/**
	 * 车辆类型
	 */
    @Mapping
	@ApiModelProperty("车辆类型")
	private Integer vehicleType;
	
	

	/**
	 * 车型
	 */
    @Mapping
	@ApiModelProperty("车型")
	private Integer vehicleModels;

	/**
	 * 车辆长度(M)
	 */
    @Mapping
	@ApiModelProperty("车辆长度")
	private BigDecimal vehicleLength;

	/**
	 * 车辆宽度(M)
	 */
    @Mapping
	@ApiModelProperty("车辆宽度")
	private BigDecimal vehicleWidth;

	/**
	 * 车辆高度(M)
	 */
    @Mapping
	@ApiModelProperty("车辆高度")
	private BigDecimal vehicleHeight;

	/**
	 * 行驶证号
	 */
    @Mapping
	@ApiModelProperty("行驶证号")
	private String drivingNo;

	/**
	 * 车架号码
	 */
    @Mapping
	@ApiModelProperty("车架号码")
	private String vin;

	/**
	 * 挂车号码
	 */
    @Mapping
	@ApiModelProperty("挂车号码")
	private String trailerNo;

	/**
	 * 发动机号
	 */
    @Mapping
	@ApiModelProperty("发动机号")
	private String engineNo;

	/**
	 * 运营证号
	 */
    @Mapping
	@ApiModelProperty("运营证号")
	private String operateNo;

	/**
	 * 附加费证号
	 */
    @Mapping
	@ApiModelProperty("附加费证号")
	private String surchargeNo;

	/**
	 * 核载质量（KG）
	 */
    @Mapping
	@ApiModelProperty("核载重量")
	private BigDecimal checkQuality;

	/**
	 * 核载体积（M3）
	 */
    @Mapping
	@ApiModelProperty("核载体积")
	private BigDecimal checkVolume;

	/**
	 * 车辆皮重（KG）
	 */
    @Mapping
	@ApiModelProperty("车辆皮重")
	private BigDecimal vehicleWeight;

	/**
	 * 百公里油耗（L）
	 */
    @Mapping
	@ApiModelProperty("百公里油耗")
	private BigDecimal oilCost;

	/**
	 * 档案编号
	 */
    @Mapping
	@ApiModelProperty("档案编码")
	private String archivesCode;

	/**
	 * 自车编号
	 */
    @Mapping
	@ApiModelProperty("自车编号")
	private String selfCarCode;

	/**
	 * 是否停用
	 */
	@ApiModelProperty("是否停用")
	private Integer status;

	/**
	 * 承运商名称
	 */
	 @Mapping
	@ApiModelProperty("承运商ID")
	private Integer carrierId;
	

	/**
	 * 承运商名称
	 */
	 @Mapping
	@ApiModelProperty("承运商名称")
	private String carrierName;


	/**
	 * 备注
	 */
	 @Mapping
	@ApiModelProperty("备注")
	private String remarks;

	/**
	 * 购买日期
	 */
	@ApiModelProperty("购买日期")
	private Date buyTime;

	/**
	 * 附件
	 */
	@ApiModelProperty("附件")
	private String attachmentUrl;

	/**
	 * 审核人
	 */
	@ApiModelProperty("审核人")
	private Integer checkUser;
	
	@ApiModelProperty("审核人")
	private String checkUserName;

	/**
	 * 审核时间
	 */
	@ApiModelProperty("审核时间")
	private Long checkTime;

	/**
	 * 取消审核人
	 */
	@ApiModelProperty("取消审核人")
	private Integer cancelCheckUser;
	@ApiModelProperty("取消审核人")
	private String cancelCheckUserName;

	/**
	 * 取消审核时间
	 */
	@ApiModelProperty("取消审核时间")
	private Long cancelCheckTime;
	 @Mapping
	private Integer createUser;
	 @Mapping
	    @ApiModelProperty(hidden = true)
	    private String createUserName;
	private Long createTime;
	 @Mapping
	private Integer updateUser;
	 @Mapping
	private String updateUserName;
	 @Mapping
	private Long updateTime;
	
	@ApiModelProperty("审核状态")
	private Integer checkStatus;
    
    /************扩展属性，用于页面显示******************/
   	/**
        * 所属车两类型描述
        */
    private String vehicleTypeDesc;
    
    /**
     * 所属车型描述
     * 
     * 
     */
    private String vehicleModelsDesc;
    
    /**
     * 状态描述
     */
    private String statusDesc;
    
    /**
     * 审核状态描述
     */
    private String checkStatusDesc;



    
}
