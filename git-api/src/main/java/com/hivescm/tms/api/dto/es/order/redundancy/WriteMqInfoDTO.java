package com.hivescm.tms.api.dto.es.order.redundancy;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 给OMS反馈运力信息
 * @author 
 * @date 2017年12月25日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WriteMqInfoDTO implements Serializable{
	private static final long serialVersionUID = 2646557661184904871L;
	private Long id;
	private String orderType;
	private String orderCode;
	private String queueName;
	private String mainInfo;
	private String errorReason;
	private String createTime;
}
