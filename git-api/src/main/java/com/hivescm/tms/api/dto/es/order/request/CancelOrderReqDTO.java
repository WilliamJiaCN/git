package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 订单发送取消运单请求DTO
 * @author ke.huang
 * @date 2017年11月6日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class CancelOrderReqDTO implements Serializable{
	private static final long serialVersionUID = -2215517197400728758L;
	@Logger
	@ApiModelProperty("订单列表")
	private List<String> orderCodeList;
	@ApiModelProperty("操作人")
	private Integer operateUser;
	@ApiModelProperty("操作人姓名")
	private String operateUserName;
}
