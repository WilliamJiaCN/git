package com.hivescm.tms.api.dto.es.base.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WarehousePositionReqDTO extends PageQurey{

	@ApiModelProperty("库位编号")
	private Integer id;
	@ApiModelProperty("库位编号")
	private String warehousePositionCode;
	@ApiModelProperty("库位名称")
	private String warehousePositionName;
	@ApiModelProperty("备注")
	private String remarks;
	@ApiModelProperty("所属网点")
	private Integer belongOrg;
	private Integer warehouseAreaId;
	@ApiModelProperty("所属仓库")
	private Integer warehouseId;
	@ApiModelProperty("创建人")
	private Integer createUser;
	@ApiModelProperty("创建时间")
	private Long createTime;
	@ApiModelProperty("修改人")
	private Integer updateUser;
	@ApiModelProperty("修改时间")
	private Long updateTime;
	@ApiModelProperty("公司id")
	private Long companyId;
	@ApiModelProperty("库区名称")
	private String warehouseAreaName;
}
