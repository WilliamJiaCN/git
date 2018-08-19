package com.hivescm.tms.api.dto.es.order.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 订单智能分配 resp DTO
 * @author ke.huang
 * @date 2017年11月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderIntelligentAllocateRespDTO implements Serializable{
	private static final long serialVersionUID = -4845672587869444299L;
	
	@ApiModelProperty("订单编号")
	private String orderCode;
	@ApiModelProperty("分配结果")
	private Boolean result;
}
