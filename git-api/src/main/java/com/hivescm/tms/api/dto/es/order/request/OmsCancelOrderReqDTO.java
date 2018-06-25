package com.hivescm.tms.api.dto.es.order.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * OMS取消订单请求DTO
 * @author ke.huang
 * @date 2017年11月6日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OmsCancelOrderReqDTO implements Serializable{
	private static final long serialVersionUID = -386653063271978618L;
	@Logger
	@Required
	@ApiModelProperty("订单号")
	private List<String> orderCode ;
	@ApiModelProperty("操作人ID")
	private Integer operateUserId;
	@ApiModelProperty("操作人姓名")
	private String operateUserName;
}
