package com.hivescm.tms.api.dto.print.outbill;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class StorageInfoForPrintDTO {

    @Mapping({"OutBillDetailEsDTO.storageId","StorageInfoDO.id","OutBillGoodsDO.storageId"})
    @ApiModelProperty("主键ID")
    private Long id;
    
    @Mapping
    @ApiModelProperty("入库类型名称")
    private String storageTypeName;

    @Mapping
    @ApiModelProperty("入库批次")
    private String outStorageCode;

    @Mapping
    @ApiModelProperty("入库时间")
    private Long outStorageTime;

    @Mapping
    @ApiModelProperty("入库成本")
    private BigDecimal storageCost;
    
    @Mapping
    @ApiModelProperty("入库网点名称")
    private String inBranchIdName;

    @Mapping
    @ApiModelProperty("备注信息")
    private String ramark;
    
    @Mapping
    @ApiModelProperty("总件数")
    private Integer packageNum;

    @Mapping
    @ApiModelProperty("总体积")
    private BigDecimal volume;

    @Mapping
    @ApiModelProperty("总重量")
    private BigDecimal weight;
    
}