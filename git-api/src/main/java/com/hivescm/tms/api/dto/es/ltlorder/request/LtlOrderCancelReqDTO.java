package com.hivescm.tms.api.dto.es.ltlorder.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 零担订单取消请求DTO
 * 
 * @author wenxiong.jia
 * @date 2018/4/11
 */
@Data
@ToString
public class LtlOrderCancelReqDTO implements Serializable {
	private static final long serialVersionUID = -4027341409621865013L;

	@Required
	@ApiModelProperty("订单号")
	private List<String> orderCodes;

	@ApiModelProperty("取消人ID")
	private Integer cancelUser;

	@ApiModelProperty("取消人姓名")
	private String cancelUserName;

	@ApiModelProperty("取消原因")
	private String cancelReason;
}
