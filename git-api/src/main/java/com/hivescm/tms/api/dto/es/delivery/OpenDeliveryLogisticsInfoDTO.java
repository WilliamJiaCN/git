package com.hivescm.tms.api.dto.es.delivery;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class OpenDeliveryLogisticsInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649155665222709620L;
	
	
	
	@Mapping({"DeliveryLogisticsInfoDTO.carrierName"})
	@ApiModelProperty("快递公司")
	private String provider;
	
	@Mapping
	@ApiModelProperty("路由编号")
	private String routeId;
	
	@Mapping
	@ApiModelProperty("运单号")
	private String mailNo;
	
	@Mapping
	@ApiModelProperty("订单号")
	private String orderId;
	
	@Mapping
	@ApiModelProperty("路由产生时间")
	private String acceptTime;
	
	@Mapping
	@ApiModelProperty("路由发生地点")
	private String acceptAddress;
	
	@Mapping
	@ApiModelProperty("路由说明")
	private String remark;
	
	@Mapping
	@ApiModelProperty("操作码")
	private String opcode;
	
}
