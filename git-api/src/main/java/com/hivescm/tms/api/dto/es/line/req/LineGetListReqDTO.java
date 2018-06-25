package com.hivescm.tms.api.dto.es.line.req;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class LineGetListReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Required
	@ApiModelProperty("仓库ids")
	private List<Long> warehouseIds;
	@ApiModelProperty("关键词-有模糊需要时传其他时一定不传")
	private String keyWord;
}
