package com.hivescm.tms.api.dto.common;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class CalculateGoodsDetailDTO {
	
	private @ApiModelProperty("商品id") Long detailId;
	 /**
     * 商品类型
     */
    private @ApiModelProperty("商品类型") String prodType;
    /**
     * 规格
     */
    private @ApiModelProperty("规格") String specifications;
    /**
     * 包装类型
     */
    private @ApiModelProperty("包装类型") Integer packingType;
    /**
     * 长（m）
     */
    private @ApiModelProperty("长（m）") BigDecimal lasting;
    /**
     * 宽（m）
     */
    private @ApiModelProperty("宽（m）") BigDecimal width;
    /**
     * 高（m）
     */
    private @ApiModelProperty("高（m）") BigDecimal height;
    /**
     * 包装件数
     */
    private @ApiModelProperty("包装件数") Integer packingNum;
    /**
     * 整箱数
     */
    private @ApiModelProperty("整箱数") Integer boxNum;
    /**
     * 散件数
     */
    private @ApiModelProperty("散件数") Integer sparePartNum;
    /**
     * 件数
     */
    private @ApiModelProperty("件数") Integer packageNum;
    /**
     * 单件重量 = 总重量/件数
     */
    private @ApiModelProperty("单件重量") BigDecimal oneWeight;
    /**
     * 单件体积 = 总体积/件数
     */
    private @ApiModelProperty("单件体积") BigDecimal oneVolume;
    /**
     * 总体积
     */
    private @ApiModelProperty("总体积") BigDecimal volume;
    /**
     * 总重量
     */
    private @ApiModelProperty("总重量") BigDecimal weight;
    
    /**
     * 单价
     */
    private @ApiModelProperty("单价") BigDecimal unitPrice;
    
	
    private @ApiModelProperty("单位") String unit;
    
    private @ApiModelProperty("单位Id") String unitId;
    
    private @ApiModelProperty("重量单位") String weightUnit;//默认6
    
    private @ApiModelProperty("体积单位") String volumeUnit;//默认 79
    
    
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
    /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型名称") String prodTypeName;
    
    /**
     * 运费
     */
    private @Mapping  @ApiModelProperty("运费") BigDecimal freight;
    /**
     * 未税运费
     */
    private @Mapping  @ApiModelProperty("未税运费") BigDecimal freightNoTax;
    
    /**
     * 税费
     */
    private @Mapping  @ApiModelProperty("税费") BigDecimal tax;
    
    /**
     * 计费价格
     */
    private @Mapping  @ApiModelProperty("计费价格") BigDecimal price;
    
    /**
     * 税率
     */
    private @Mapping  @ApiModelProperty("税率") String taxRate;
    
    /**
     * 税码
     */
    private @Mapping  @ApiModelProperty("税码") String taxCode;
    
    /**
     * 服务项
     */
    private @Mapping  @ApiModelProperty("服务项") String serviceName;
    
    /**
     * 是否含税
     */
    private @Mapping  @ApiModelProperty("是否含税") Boolean withTax;
    
    /**
     * 计量依据
     */
    private @Mapping  @ApiModelProperty("计量依据") String meteringBase;
    
    /**
     * 计量单位
     */
    private @Mapping  @ApiModelProperty("计量单位") Integer meteringUnit;
    
    /**
     * 货值
     */
    private @Mapping  @ApiModelProperty("货值") BigDecimal amout;
    
    /**
     * 编码
     */
    private @Mapping  @ApiModelProperty("编码") String skuid;
    
    /**
     * 包装名称
     */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
    
}
