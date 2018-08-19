package com.hivescm.tms.api.dto.es.ltlorder.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 零担订单接单请求DTO
 * 
 * @author wenxiong.jia
 * @date 2018/4/11
 */
@Data
@ToString
public class LtlOrderUpdateBatchReqDTO implements Serializable {
	private static final long serialVersionUID = 6531734412591161161L;
	@Required
	@ApiModelProperty("订单号")
	private List<String> orderCodes;
	@ApiModelProperty("操作人ID")
	private Integer oprUser;
	@ApiModelProperty("操作人姓名")
	private String oprUserName;
}
