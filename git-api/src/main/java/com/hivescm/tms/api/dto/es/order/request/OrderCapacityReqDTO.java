package com.hivescm.tms.api.dto.es.order.request;

import java.io.Serializable;

import com.hivescm.tms.api.dto.es.order.OrderCapacityEsDTO;
import com.hivescm.tms.api.dto.es.order.OrderEsDTO;

import lombok.Data;
import lombok.ToString;

/**
 * 承运商接单、拒接、超时未接外部调用
 * @author ke.huang
 * @date 2017年12月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderCapacityReqDTO implements Serializable{
	private static final long serialVersionUID = 6753791171039486101L;
	private OrderCapacityResultReqDTO orderCapacityResultReqDTO;
	private OrderEsDTO order;
	private OrderCapacityEsDTO capacity;
}
