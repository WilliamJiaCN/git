package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 反馈智能分配请求
 * @author ke.huang
 * @date 2017年12月13日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderFeedbackIntelligentAllocateReqDTO implements Serializable{
	private static final long serialVersionUID = 9001079305180551906L;
	@Logger
	@Required
	@ApiModelProperty("反馈标识")
	private List<String> strs;
}
