package com.hivescm.tms.api.dto.es.distribution.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
@Data
@ToString
public class DistributionBillRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/*************************************************************
	 * 运单数据
	 ***********************************************************/
	/**
	 * 此处冗余运单数据与待处理相似所以直接使用待处理的运单冗余数据
	 */
	private DistributionPendingBillRespDTO waybill;
	/***********************************************************
	 * 冗余车辆信息
	 **********************************************************/
	@Mapping
	@ApiModelProperty("车辆号码")
	private String vehicleName;
	@Mapping
	@ApiModelProperty("车型名称")
	private String vehicleModelName;

	/***********************************************************
	 * 司机信息
	 **********************************************************/
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	@Mapping
	@ApiModelProperty("司机手机号码")
	private String phoneNo;

	/***********************************************************
	 * 城配派车单信息
	 **********************************************************/
	@Mapping
	@ApiModelProperty("指派网点")
	private Integer dotId;
    @Mapping
    @ApiModelProperty("指派网点名称")
    private String dotName;
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;
	@Mapping
	@ApiModelProperty("派车单成本")
	private String cost;
	@Mapping
	@ApiModelProperty("指派时间")
	private Long billTime;
	@Mapping
	@ApiModelProperty("接单时间")
	private Long acceptDate;
	@Mapping
	@ApiModelProperty("发车时间")
	private Long dispatcherTime;
	@Mapping
	@ApiModelProperty("指派备注/派车备注")
	private String assignRemarks;

	/***********************************************************
	 * 城配派车单明细信息
	 **********************************************************/
	@Mapping
	@ApiModelProperty("装货时间")
	private Long shippingTime;
	@Mapping
	@ApiModelProperty("派车单明细状态")
	private Integer status;
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;
	@Mapping
    @ApiModelProperty("派车单明细id")
    private Long id;
	
	/************************************************************** 后加需要做另外查询  ***********************************************************/
	@Mapping
	private @ApiModelProperty("声明价值") String declaredValue;
	
	@Mapping
	private @ApiModelProperty("包装") String packages;
	
	@Mapping
	private @ApiModelProperty("执行任务") String taskName;
	
	@Mapping
	private @ApiModelProperty("送货费") String deliveryCharges;
	
	@Mapping
	private @ApiModelProperty("代收货款") String collectPayment;
	
}
