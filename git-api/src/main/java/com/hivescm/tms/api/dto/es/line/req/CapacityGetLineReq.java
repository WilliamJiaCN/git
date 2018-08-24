package com.hivescm.tms.api.dto.es.line.req;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CapacityGetLineReq implements Serializable{

	private static final long serialVersionUID = 1L;

	@Required
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	@Required
	@ApiModelProperty("自配和统配标识")
	private Integer keyword;
}
