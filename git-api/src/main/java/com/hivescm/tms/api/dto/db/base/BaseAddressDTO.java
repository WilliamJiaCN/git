package com.hivescm.tms.api.dto.db.base;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseAddressDTO {

    @Mapping
    @ApiModelProperty("网点覆盖地址编码（省）")
    private String provCode;

    @Mapping
    @ApiModelProperty("网点覆盖地址编码（市）")
    private String cityCode;

    @Mapping
    @ApiModelProperty("网点覆盖地址编码（区县）")
    private String disCode;
    @Mapping
    @ApiModelProperty("网点覆盖地址名称（省）")
    private String provName;

    @Mapping
    @ApiModelProperty("网点覆盖地址名称（市）")
    private String cityName;

    @Mapping
    @ApiModelProperty("网点覆盖地址名称（区县）")
    private String disName;
}
