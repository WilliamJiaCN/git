package com.hivescm.tms.api.dto.es.exception.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillAllHandingRespDTO implements Serializable{

	private static final long serialVersionUID = 4862861938072055681L;

	@Mapping
    @ApiModelProperty("来源单号")
    private String sourceCode;
    @Mapping
    @ApiModelProperty("来源单号ID")
    private Long sourceId;
    @Mapping
    @ApiModelProperty("装卸类型 （1，装货 2卸货）")
    private Integer handlingType;
    @Mapping
    @ApiModelProperty("装卸类型名称")
    private String handlingTypeName;
    @Mapping
    @ApiModelProperty("装卸队ID")
    private Long handlingTeamId;
    @Mapping
    @ApiModelProperty("装卸队名称")
    private String handlingTeamName;
}
