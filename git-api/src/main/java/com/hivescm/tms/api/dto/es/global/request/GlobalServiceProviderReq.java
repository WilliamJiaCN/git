package com.hivescm.tms.api.dto.es.global.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.global.redundancy.GlobalServiceProviderConfigDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class GlobalServiceProviderReq implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("统仓统配服务商配置信息")
	private GlobalServiceProviderConfigDTO globalServiceProviderConfig;
	@Required @Logger 
	@ApiModelProperty("仓储服务商ID")
    private Long providerId;
	@Required @Logger 
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	@Mapping
    @ApiModelProperty("创建人名称")
    private String createUserName;
	@Mapping
	@ApiModelProperty("修改人名称")
    private String updateUserName;
}
