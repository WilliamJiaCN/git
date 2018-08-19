package com.hivescm.tms.api.dto.es.waybill.response;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@ToString
public class ChangeWaybillGoodsEsRespDTO implements Serializable{

	private @Mapping  static final long serialVersionUID = -6722187689707934496L;

    private @ApiModelProperty("ES主键") Long id;

    private @Mapping({"goodsId","WaybillGoodsDO.id","CalculateGoodsDetailDTO.detailId"}) @ApiModelProperty("商品明细ID") Long goodsId;
    /**
     * 运单id
     */
    private @Mapping  @ApiModelProperty("运单id") Long waybillId;
    /**
     * 货物名称
     */
    private @Mapping  @ApiModelProperty("货物名称") String goodsName;
    /**
     * 货物类型
     */
    private @Mapping  @ApiModelProperty("货物类型") Integer goodsType;
    /**
     * 货物类型
     */
    private @Mapping  @ApiModelProperty("货物类型名称") String goodsTypeName;
 
    private @Mapping  @ApiModelProperty("件数") Integer packageNum;
    /**
     * 总重量
     */
    private @Mapping  @ApiModelProperty("总重量") BigDecimal weight;
    /**
     * 总体积
     */
    private @Mapping  @ApiModelProperty("总体积") BigDecimal volume;
    /**
     * 单价
     */
    private @Mapping  @ApiModelProperty("单价") BigDecimal unitPrice;
    /**
     * 运费
     */
    private @Mapping  @ApiModelProperty("运费") BigDecimal freight;
    
    /**
     * 包装类型
     */
    private @Mapping  @ApiModelProperty("包装类型") Integer packingType;
    /**
     * 包装名称
     */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;

}