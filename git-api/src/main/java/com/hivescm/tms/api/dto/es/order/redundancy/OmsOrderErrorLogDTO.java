package com.hivescm.tms.api.dto.es.order.redundancy;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * OMS订单错误日志
 * @author ke.huang
 * @date 2017年12月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OmsOrderErrorLogDTO implements Serializable{
	private static final long serialVersionUID = 2646557661184904871L;
	private Long id;
	private String orderType;
	private String orderCode;
	private String queueName;
	private String orderData;
	private String errorReason;
	private String createTime;
}
