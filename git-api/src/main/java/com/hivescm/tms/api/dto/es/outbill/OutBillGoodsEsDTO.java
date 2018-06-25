package com.hivescm.tms.api.dto.es.outbill;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class OutBillGoodsEsDTO {

	@Mapping
	private Long id;
	@Mapping
    private Long companyId;
	
	@Mapping
    @ApiModelProperty("外发单ID")
    private Long outbillId;
	
	@Mapping
    @ApiModelProperty("运单商品ID")
    private Long goodsId;
	
	@Mapping
	@ApiModelProperty("外发单运单id")
    private Long outbillWaybillId;
	
	@Mapping
    @ApiModelProperty("外发网点库存明细ID")
    private Long stockDetailId;
    
	@Mapping
    private String goodsName;
    @Mapping
    private Integer packageNum;
    @Mapping
    private BigDecimal volume;
    @Mapping
    private BigDecimal weight;
    
    @Mapping
    @ApiModelProperty("入库件数")
    private Integer realPackageNum;

    @Mapping
    @ApiModelProperty("入库体积")
    private BigDecimal realVolume;

    @Mapping
    @ApiModelProperty("入库重量")
    private BigDecimal realWeight;
    
    @Mapping
    @ApiModelProperty("签收件数")
    private Integer signPackageNum;

    @Mapping
    @ApiModelProperty("签收体积")
    private BigDecimal signVolume;

    @Mapping
    @ApiModelProperty("签收重量")
    private BigDecimal signWeight;
    
    @Mapping
    private Integer createUser;
	@Mapping
    private String createUserName;
	@Mapping
    private Long createTime;
	@Mapping
    private String updateUserName;
	@Mapping
    private Integer updateUser;
	@Mapping
    private Long updateTime;
    
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
    
    private @Mapping  @ApiModelProperty("备注") String remark;
    
    private @Mapping  @ApiModelProperty("开单件数") Integer billPackageNum;
    
    private @Mapping  @ApiModelProperty("库存件数") Integer stockPackageNum;
    
    private @Mapping @ApiModelProperty("拒签原因") String refuseSignReasonName;
    
    private @Mapping @ApiModelProperty("拒签原因") Integer refuseSignReason;
	
    private @Mapping @ApiModelProperty("签收备注") String signRemark;
    
}
