package com.hivescm.tms.api.dto.es.ltlorder.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 零担订单运单请求DTO
 * 
 * @author wenxiong.jia
 * @date 2018/4/11
 */
@Data
@ToString
public class LtlOrderCapacityReqDTO implements Serializable {

	private static final long serialVersionUID = -2384591977727470685L;

	@Required
	@ApiModelProperty("订单号")
	private List<String> orderCodes;
	@ApiModelProperty("订单号->运单号")
	private Map<String, String> map;
	@ApiModelProperty("订单状态")
	private Integer status;
	@ApiModelProperty("订单状态名称")
	private String statusName;
	@ApiModelProperty("操作人ID")
	private Integer oprUser;
	@ApiModelProperty("操作人姓名")
	private String oprUserName;
	@ApiModelProperty("备注")
	private String remark;
}
