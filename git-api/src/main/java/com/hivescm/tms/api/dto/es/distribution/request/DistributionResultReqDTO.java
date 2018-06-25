package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.dispatcher.DistributionBillTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DistributionResultReqDTO implements Serializable {

    private static final long serialVersionUID = -7005789045763907442L;
    @ApiModelProperty("单据id")
    private @Mapping
    Long id;


    @ApiModelProperty("单据类型")
    private DistributionBillTypeEnum billType;


    @ApiModelProperty("司机id")
    private Integer driverId;
}
