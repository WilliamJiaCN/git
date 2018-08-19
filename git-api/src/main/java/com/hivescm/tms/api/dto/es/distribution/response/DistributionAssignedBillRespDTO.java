package com.hivescm.tms.api.dto.es.distribution.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionAssignedBillRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/************************************************************** 运单数据  ***********************************************************/
	/**
	 * 此处冗余运单数据与待处理相似所以直接使用待处理的运单冗余数据
	 */
	private DistributionPendingBillRespDTO waybill;
	
	@ApiModelProperty("指派时间")
	private @Mapping Long pushTime;
	private @Mapping @ApiModelProperty("指派网点Id") String pushDotId;
	private @Mapping @ApiModelProperty("指派类型") String pushType;
	private @Mapping @ApiModelProperty("入库时间") Long storageTime;
	private @Mapping @ApiModelProperty("入库时长") Integer storageHours;
	private @Mapping @ApiModelProperty("接单时间") Long acceptDate;
	private @Mapping @ApiModelProperty("指派备注") String assignRemarks;

	private @Mapping @ApiModelProperty("运单ID") Long waybillId;
	
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

