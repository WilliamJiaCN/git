package com.hivescm.tms.api.dto.es.storage.common;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class StorageDetailDTO {
	
    @ApiModelProperty("外发单明细ID")
    private Long outbillWaybillId;
	
    @ApiModelProperty("运单ID")
    private Long waybillId;
    
    @ApiModelProperty("运单号")
    private String waybillCode;
    
    @ApiModelProperty("商品名称")
    private String goodsName;

    @Mapping
    @ApiModelProperty("仓库id")
    private Long warehouseId;
    
    @Mapping
    @ApiModelProperty("仓库名称")
    private String warehouseName;
    
    @Mapping
    @ApiModelProperty("入库备注")
    private String storageRamark;
    
    
	@ApiModelProperty("运费")
    private BigDecimal freightFee;
    
    
	@ApiModelProperty("产值")
    private BigDecimal productFee;
	
	
	@ApiModelProperty("业务费")
    private BigDecimal businessFee;
	
    private List<StorageGoodsDTO> goods;
    
}