package com.hivescm.tms.api.dto.es.line.resp;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class GlobalWarehouseLineRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Mapping
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@Mapping
	@ApiModelProperty("线路ID")
	private Integer lineId;
	@Mapping
	@ApiModelProperty("线路名称")
	private String lineName;
}
