package com.hivescm.tms.api.dto.es.line.req;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class LineNameCheckRepeatReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Logger
	@Required
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	@Required
	@ApiModelProperty("线路名称")
	private String lineName;
}
