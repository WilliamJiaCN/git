package com.hivescm.tms.api.dto.es.delivery;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class DeliveryAcceptCodeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649155665222709620L;
	
	@Mapping
	@ApiModelProperty("快递公司")
	private String provider;
	
	@Mapping
	@ApiModelProperty("运单号")
	private String mailNo;
	
	@Mapping
	@ApiModelProperty("订单号")
	private String orderId;
}
