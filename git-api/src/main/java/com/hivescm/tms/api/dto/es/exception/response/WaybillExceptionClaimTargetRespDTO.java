package com.hivescm.tms.api.dto.es.exception.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillExceptionClaimTargetRespDTO {


    @Mapping
    @ApiModelProperty("理赔对象id")
    private Integer claimTargetId;
    
    @Mapping
    @ApiModelProperty("理赔对象")
    private String claimTarget;
    

    @Mapping
    @ApiModelProperty("理赔对象是否VIP")
    private Boolean claimTargetIsVip;
}
