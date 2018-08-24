package com.hivescm.tms.api.dto.es.storage;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@ToString
public class StorageInfoEsDTO {

    @Mapping({"OutBillDetailEsDTO.storageId","StorageInfoDO.id","OutBillGoodsDO.storageId","StorageInfoForPrintDTO.id"})
    @ApiModelProperty("主键ID")
    private Long id;

    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    @Mapping
    @ApiModelProperty("外发公司id")
    private Long outCompanyId;

    @Mapping
    @ApiModelProperty("入库类型")
    private Integer storageType;
    
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
    @ApiModelProperty("入库网点")
    private Integer inBranchId;
    
    @Mapping
    @ApiModelProperty("入库网点名称")
    private String inBranchIdName;

    @Mapping
    @ApiModelProperty("费用分摊类型")
    private Integer feeShareType;
    
    @Mapping
    @ApiModelProperty("费用分摊类型名称")
    private String feeShareTypeName;

    @Mapping
    @ApiModelProperty("入库成本")
    private BigDecimal storageCost;

    @ApiModelProperty("总票数")
    private Integer count;

    @Mapping
    @ApiModelProperty("总运费")
    private BigDecimal totalFreightFee;

    @Mapping
    @ApiModelProperty("总产值")
    private BigDecimal totalProductFee;

    @Mapping
    @ApiModelProperty("总业务费")
    private BigDecimal totalBusinessFee;

    @Mapping
    @ApiModelProperty("总件数")
    private Integer packageNum;

    @Mapping
    @ApiModelProperty("总体积")
    private BigDecimal volume;

    @Mapping
    @ApiModelProperty("总重量")
    private BigDecimal weight;

    @Mapping
    @ApiModelProperty("备注信息")
    private String ramark;

    @Mapping
    @ApiModelProperty("入库状态")
    private Integer storageStatus;

    @Mapping
    private Integer createUser;
    
    @Mapping
    private String createUserName;

    @Mapping
    private Long createTime;

    @Mapping
    private Integer updateUser;

    @Mapping
    private Long updateTime;
}