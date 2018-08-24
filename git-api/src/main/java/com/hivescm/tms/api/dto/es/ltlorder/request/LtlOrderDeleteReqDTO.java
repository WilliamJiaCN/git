package com.hivescm.tms.api.dto.es.ltlorder.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 删除订单请求DTO
 * 
 * @author wenxiong.jia
 * @date 2018/4/19
 */
@Data
@ToString
public class LtlOrderDeleteReqDTO implements Serializable {
	private static final long serialVersionUID = -1189872044230746217L;

	@ApiModelProperty("订单ID")
	private Long orderId;

	@ApiModelProperty("操作人ID")
	private Integer oprId;

	@ApiModelProperty("操作人姓名")
	private String oprName;
}