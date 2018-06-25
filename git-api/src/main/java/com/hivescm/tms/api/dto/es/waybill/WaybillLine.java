package com.hivescm.tms.api.dto.es.waybill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WaybillLine implements Serializable {
    private static final long serialVersionUID = -1763778148001866463L;
    /**
     * 运输线路id
     */
    private @Mapping
    @ApiModelProperty("运输线路id")Long lineId;
    /**
     * 运输线路名称
     */
    private @Mapping  @ApiModelProperty("运输线路名称	")String lineName;
}
