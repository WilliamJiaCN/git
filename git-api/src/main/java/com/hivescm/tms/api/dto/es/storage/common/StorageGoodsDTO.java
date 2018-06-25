package com.hivescm.tms.api.dto.es.storage.common;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class StorageGoodsDTO {

    @ApiModelProperty("外发单商品ID")
    private Long outbillGoodsId;
    
    @ApiModelProperty("运单商品ID")
    private Long goodsId;
    
    @ApiModelProperty("运单商品名称")
    private String goodsName;
    
    @ApiModelProperty("公司ID")
    private Long companyId;

    @Mapping
    @ApiModelProperty("入库件数")
    private Integer realPackageNum;

    @Mapping
    @ApiModelProperty("入库体积")
    private BigDecimal realVolume;

    @Mapping
    @ApiModelProperty("入库重量")
    private BigDecimal realWeight;
}