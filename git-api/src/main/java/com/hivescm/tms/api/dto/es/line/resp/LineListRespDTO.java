package com.hivescm.tms.api.dto.es.line.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class LineListRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("线路id")
	private Long id;
	
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	 
	@ApiModelProperty("线路名称")
	private String lineName;
}
