package com.hivescm.tms.api.dto.es.line.req;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class LineListReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Logger
	@Required
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	@ApiModelProperty("关键词-有模糊需要时传其他时一定不传")
	private String keyWord;
}
