package com.hivescm.tms.api.dto.es.stock;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Data
@ToString
public class SignStockOperating implements Serializable {


    /**
     * 库存主表ID
     */
    private @Mapping
    @ApiModelProperty("库存主表ID") Long stockId;

    private @Mapping
    @ApiModelProperty("明细参数")
    List<SignStockDetail> signStockDetails;
}
