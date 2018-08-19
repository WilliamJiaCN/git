package com.hivescm.tms.api.dto.db.pre;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class WmsWareHouseRespDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@ApiModelProperty("站点id")
	private Long siteId;
	@ApiModelProperty("站点名称")
	private String siteName;
	@ApiModelProperty("所属组织")
	private String subordinateOrganizationName;
	@ApiModelProperty("所属组织")
	private Long subordinateOrganizationId;
	@ApiModelProperty("城市code")
	private Long cityCode;
	
	public WmsWareHouseRespDTO(Long warehouseId, String warehouseName, Long siteId, String siteName,
			String subordinateOrganizationName, Long subordinateOrganizationId) {
		super();
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.siteId = siteId;
		this.siteName = siteName;
		this.subordinateOrganizationName = subordinateOrganizationName;
		this.subordinateOrganizationId = subordinateOrganizationId;
	}
	public WmsWareHouseRespDTO() {
		super();
	}
}
