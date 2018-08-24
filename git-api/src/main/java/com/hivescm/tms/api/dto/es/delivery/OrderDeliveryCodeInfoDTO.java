package com.hivescm.tms.api.dto.es.delivery;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class OrderDeliveryCodeInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649155665222709620L;
	
	@Mapping
	@ApiModelProperty("订单号")
	private String orderCode;
	
	@Mapping
	@ApiModelProperty("快递单号")
	private String deliveryCode;
	
}
