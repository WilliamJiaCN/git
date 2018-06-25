package com.hivescm.tms.api.dto.es.sign.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class RefuseSignReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Logger
	@ApiModelProperty("运单id")
	private Long waybillId;
	@ApiModelProperty("公司id")
	private Long companyId;
}
