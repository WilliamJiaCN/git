package com.hivescm.tms.api.dto.es.ltlorder.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 更新订单派车批次请求DTO
 * 
 * @author wenxiong.jia
 * @date 2018/4/11
 */
@Data
@ToString
public class LtlOrderDispatcherReqDTO implements Serializable {
	private static final long serialVersionUID = -2440499175835449470L;

	@ApiModelProperty("订单号")
	private List<String> orderCodes;

	@ApiModelProperty("派车批次")
	private String batchCode;

	@ApiModelProperty("司机姓名")
	private String driverName;

	@ApiModelProperty("手机号码")
	private String phoneNo;

	@ApiModelProperty("车牌号码")
	private String vehicleNo;

	@ApiModelProperty("修改人")
	private Integer updateUser;

	@ApiModelProperty("修改人姓名")
	private String updateUserName;
}
