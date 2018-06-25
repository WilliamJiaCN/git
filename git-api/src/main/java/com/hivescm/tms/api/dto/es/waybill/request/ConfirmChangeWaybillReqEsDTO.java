package com.hivescm.tms.api.dto.es.waybill.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConfirmChangeWaybillReqEsDTO implements Serializable {

	private static final long serialVersionUID = -7270371287389467420L;

	/**
	 * 公司Id
	 */

	@ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
	private Long companyId;

	/**
	 * 操作用户ID
	 */

	@ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
	private Long opUserId;

	/**
	 * 操作用户名称
	 */

	@ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
	private String opUserName;
	
	
	/**
	 * 运单ID
	 */

	@ApiModelProperty(value = "运单ID")
	private Long waybillId;




}
