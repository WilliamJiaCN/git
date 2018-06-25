package com.hivescm.tms.api.dto.es.line.resp;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class WareHouseRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	private List<CarrierLineRespDTO> lines;
	@ApiModelProperty("站点id")
	private Long siteId;
	@ApiModelProperty("站点名称")
	private String siteName;
	
	public String groupBySiteName() {
		return this.siteName;
	}
}
