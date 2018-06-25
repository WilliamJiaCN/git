package com.hivescm.tms.api.dto.es.exception.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillAllTransRespDTO implements Serializable{

	private static final long serialVersionUID = 8964223594188156677L;


	@Mapping
    @ApiModelProperty("来源单号")
    private String sourceCode;
    @Mapping
    @ApiModelProperty("来源单号ID")
    private Long sourceId;
    @Mapping
    @ApiModelProperty("承运商ID")
    private Integer carrierId;
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carrierName;
}
