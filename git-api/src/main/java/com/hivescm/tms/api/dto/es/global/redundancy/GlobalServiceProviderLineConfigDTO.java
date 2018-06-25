package com.hivescm.tms.api.dto.es.global.redundancy;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.capacity.global.LogisticsOrganizationEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class GlobalServiceProviderLineConfigDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("主键")
    private Long id;
	@Mapping
	@ApiModelProperty("仓储服务商ID")
    private Long providerId;
	@Mapping
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	@Mapping
	@ApiModelProperty("线路ID")
    private Long lineId;
	@ApiModelProperty("物流组织类型")
    private LogisticsOrganizationEnum unifyType;
	@Mapping
	@ApiModelProperty("物流组织服务商ID")
    private Long unifyProviderId;
	@Mapping
	@ApiModelProperty("物流组织配送部门ID")
    private Long unifyProviderDepartId;
	
	@Mapping
	@ApiModelProperty("仓储服务商ID")
    private String providerName;
	@Mapping
	@ApiModelProperty("仓库名称")
    private String warehouseName;
	@Mapping
	@ApiModelProperty("站点名称")
    private String siteName;
	@Mapping
	@ApiModelProperty("物流组织配送部门名称")
    private String unifyProviderDepartName;
	@Mapping
	@ApiModelProperty("物流组织服务商名称")
    private String unifyProviderName;
	@Mapping
	@ApiModelProperty("线路名称")
    private String lineName;
}
