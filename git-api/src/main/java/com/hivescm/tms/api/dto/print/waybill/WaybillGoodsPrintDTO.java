package com.hivescm.tms.api.dto.print.waybill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WaybillGoodsPrintDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5542866163722666773L;

	/**
	 * 主键	
	 */
    private @ApiModelProperty("主键ID") Long id;
    /**
     * 运单id
     */
    private @ApiModelProperty("运单id") Long waybillId;

    /**
     * 货物名称
     */
    private @Mapping  @ApiModelProperty("货物名称") String goodsName;
    /**
     * 包装名称
     */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;

    private @Mapping  @ApiModelProperty("货物类型名称") String goodsTypeName;
    /**
     * 件数
     */
    private @Mapping  @ApiModelProperty("件数") Integer packageNum;
    /**
     * 总体积
     */
    private @Mapping  @ApiModelProperty("总体积") BigDecimal volume;
    /**
     * 总重量
     */
    private @Mapping  @ApiModelProperty("总重量") BigDecimal weight;
    /**
     * 单价
     */
    private @Mapping  @ApiModelProperty("单价") BigDecimal unitPrice;
    /**
     * 运费
     */
    private @Mapping  @ApiModelProperty("运费") BigDecimal freight;
}
