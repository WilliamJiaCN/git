package com.hivescm.tms.api.dto.es.order.resp;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.order.OrderEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 订单列表resp dto
 * @author ke.huang
 * @date 2017年12月14日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderListRespDTO implements Serializable{
	private static final long serialVersionUID = -7166490580156413441L;
	@ApiModelProperty("总条数")
	private Integer totalNum;
	@ApiModelProperty("订单列表")
	private List<OrderEsDTO> orders;
}
