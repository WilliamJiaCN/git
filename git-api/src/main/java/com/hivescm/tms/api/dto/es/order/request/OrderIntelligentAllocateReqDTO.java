package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 订单智能分配请求DTO
 * @author ke.huang
 * @date 2017年11月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderIntelligentAllocateReqDTO implements Serializable{
	private static final long serialVersionUID = 7789311337204711709L;
	@Logger
	@Required
	@ApiModelProperty("订单编号")
	private List<String> orderCodes;

}
