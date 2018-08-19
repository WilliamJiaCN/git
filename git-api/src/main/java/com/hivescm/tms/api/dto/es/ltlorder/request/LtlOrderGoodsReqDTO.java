package com.hivescm.tms.api.dto.es.ltlorder.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 零担订单商品信息
 * 
 * @author wenxiong.jia
 * @date 2018/4/9
 */
@Data
@ToString
public class LtlOrderGoodsReqDTO implements Serializable{

	private static final long serialVersionUID = 2277108806635557111L;

	@Mapping
	private Long id;
	/**
	 * 商品名称
	 */
    @Mapping @Required
	@ApiModelProperty(value = "商品名称")
	private String goodsName;
	/**
	 * 商品包装
	 */
    @Mapping
	@ApiModelProperty(value = "商品包装")
	private String packing;
	/**
	 * 数量(件数)
	 */
    @Mapping @Required
	@ApiModelProperty(value = "数量(件数)")
	private Integer amount;
	/**
	 * 规格
	 */
	@Mapping
	@ApiModelProperty(value = "规格")
	private String specifications;
	/**
	 * 重量
	 */
    @Mapping
	@ApiModelProperty(value = "重量")
	private BigDecimal weight;
	/**
	 * 体积
	 */
    @Mapping
	@ApiModelProperty(value = "体积")
	private BigDecimal volume;
	/**
	 * 运费
	 */
    @Mapping
	@ApiModelProperty(value = "运费")
	private BigDecimal freight;

}

