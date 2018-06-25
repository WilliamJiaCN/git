package com.hivescm.tms.api.dto.es.order.redundancy;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * OMS销售订单商品明细
 * @author ke.huang
 * @date 2017年10月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OmsSaleOrderGoodsDTO implements Serializable{
	private static final long serialVersionUID = -1362559098612486678L;
	@Required @Mapping
	@ApiModelProperty("商品名称")
    private String goodsName;
	@Required @Mapping
	@ApiModelProperty("商品类型")
    private String goodsType;//多个"/"间隔
	@Required @Mapping
	@ApiModelProperty("商品编码")
    private String goodsCode;
	@Required @Mapping
	@ApiModelProperty("商品单价")
    private BigDecimal unitPrice;
	@Required @Mapping
	@ApiModelProperty("包装")
    private String packing;
	@Required @Mapping
	@ApiModelProperty("数量(件数)")
    private Integer amount;
	@Required @Mapping
	@ApiModelProperty("长")
    private BigDecimal goodsLength;
	@Required @Mapping
	@ApiModelProperty("宽")
    private BigDecimal goodsWidth;
	@Required @Mapping
	@ApiModelProperty("高")
    private BigDecimal goodsHeight;
	@Required @Mapping
	@ApiModelProperty("订单明细ID")
    private Long orderDetailId;
	@Required @Mapping
	@ApiModelProperty("单位重量")
    private BigDecimal unitWeight;
	@Required @Mapping
	@ApiModelProperty("单位体积")
    private BigDecimal unitVolume;
	@Mapping
	@ApiModelProperty("规格")
    private String specifications;
	@Required @Mapping
	@ApiModelProperty("单位")
    private String unit;
	@Required @Mapping
	@ApiModelProperty("SKUID")
	private String skuId;
	@Mapping
	@ApiModelProperty("商品备注，售货机货位")
	private String remark;
}