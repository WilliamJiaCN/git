package com.hivescm.tms.api.dto.es.dispatcher.redundancy;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 派车单明细冗余派车单信息
 *
 * @author 李洪春
 * @since 2017/9/11 14:41
 */
@Data
@ToString
public class DispatcherDetailDTO implements Serializable {

    private static final long serialVersionUID = -7861324268556793779L;
    /**
     * id
     */
    private Long id;

    /**
     * 司机ID
     *
     * @{link com.hivescm.tms.api.dto.base.BaseDriverDTO#id}
     */
    @Mapping
    @ApiModelProperty("司机ID")
    private Integer driverId;
    /**
     * 司机姓名
     */
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    /**
     * 司机姓名
     */
    @Mapping
    @ApiModelProperty("司机手机号码")
    private String phoneNo;
    /**
     * 发车时间
     */
    @Mapping
    @ApiModelProperty("发车时间")
    private Long dispatcherTime;
    /**
     * 派车批次
     */
    @Mapping
    @ApiModelProperty("派车批次")
    private String batchCode;
    /**
     * 派车网点ID
     */
    @Mapping
    @ApiModelProperty("派车网点ID")
    private Integer branchId;
    @Mapping
  	@ApiModelProperty("波次ID")
	private Long waveId;
    /**
     * 派车网点名称
     */
    @Mapping
    @ApiModelProperty("派车网点名称")
    private String branchName;
    /**
     * 订单运输的类型，包括：销售订单、销退单；自动接收（非必填）
     */
    @Mapping
    @ApiModelProperty("订单类型")
    private Integer orderType;
    
    @Mapping
	@ApiModelProperty("订单来源名称")
    private String orderSourceName;
	@Mapping
	@ApiModelProperty("订单来源")
    private Integer orderSource;
	@Mapping
	@ApiModelProperty("仓储服务商ID")
	private Long warehouseServerId;
	/**
     * 结算对象：仓储服务商、经销商
     */
	@Mapping
    @ApiModelProperty("结算对象集团客户Id")
    private Integer settlementObjectGroupId;
	
	/**
     * 结算对象：仓储服务商、经销商
     */
	@Mapping
    @ApiModelProperty("结算对象集团客户名称")
    private String settlementObjectGroupName;
	@Mapping
	@ApiModelProperty("仓储服务商名称")
	private String warehouseServerName;// 冗余
    /**
     * 车型名称
     */
    @Mapping
    @ApiModelProperty("车型名称")
    private Integer vehicleModelName;

    @Mapping
    @ApiModelProperty("执行任务名称")
    private String taskName;
    @Mapping()
	@ApiModelProperty("运单改配状态")
    private Integer changeDelivery;
    
	@Mapping()
	@ApiModelProperty("运单来源类型")
    private Integer waybillType;
	@Mapping()
	@ApiModelProperty("运单来源类型名称")
    private String waybillTypeName;
	
	/**
	 * true为开单直送
	 */
	@Mapping()
	@ApiModelProperty("是否开单直送")
    private Boolean directSend;
	
	@ApiModelProperty("改配状态")
	private Integer changeDispatcherType;
}
