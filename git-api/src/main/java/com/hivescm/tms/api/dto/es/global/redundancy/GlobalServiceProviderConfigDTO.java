package com.hivescm.tms.api.dto.es.global.redundancy;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.capacity.global.LogisticsOrganizationEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GlobalServiceProviderConfigDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Mapping @Logger 
	@ApiModelProperty("主键")
	private Long id;
	@Mapping @Logger 
	@ApiModelProperty("仓储服务商ID")
    private Long providerId;
	@Mapping @Logger 
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	@Mapping @Logger 
	@ApiModelProperty("站点ID")
    private Long siteId;
	@Mapping
	@ApiModelProperty("是否使用统一物流组织")
    private Boolean iunify;
	@ApiModelProperty("统一物流组织类型")
    private LogisticsOrganizationEnum unifyType;
	@Mapping
	@ApiModelProperty("统一物流组织服务商ID")
    private Long unifyProviderId;
	@Mapping
	@ApiModelProperty("统一物流组织配送部门ID")
    private Long unifyProviderDepartId;
	@Mapping
	@ApiModelProperty("是否按线路指定物流组织")
    private Boolean iline;
	
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
	@ApiModelProperty("统一物流组织配送部门名称")
	private String unifyProviderDepartName;
	@Mapping
	@ApiModelProperty("统一物流组织服务商名称")
	private String unifyProviderName;
	@ApiModelProperty("统仓统配服务商线路配置信息")
	private List<GlobalServiceProviderLineConfigDTO> globalServiceProviderLineConfig;
}
