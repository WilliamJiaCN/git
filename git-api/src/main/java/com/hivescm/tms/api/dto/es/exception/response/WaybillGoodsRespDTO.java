package com.hivescm.tms.api.dto.es.exception.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillGoodsRespDTO {

	/**
     * 货物名称
     */
    private @Mapping  @ApiModelProperty("货物名称") String goodsName;
    
    /**
     * 件数(应该取货量)
     */
    private @Mapping  @ApiModelProperty("件数") Integer packageNum;
}
