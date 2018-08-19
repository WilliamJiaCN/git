package com.hivescm.tms.api.dto.es.ltlorder.redundancy;

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
public class LtlOrderGoodsDTO implements Serializable{	
	
	private static final long serialVersionUID = -4030315120154595861L;
	
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
	 * 规格
	 */
	@ApiModelProperty(value = "规格")
	private String specifications;
	/**
	 * 运费
	 */
	@ApiModelProperty(value = "运费")
	private BigDecimal freight;
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private Integer createUser;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人")
	private Integer updateUser;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Long updateTime;
	/**
	 * 备注 售货机类型订单货位信息
	 */
	@ApiModelProperty(value = "备注 售货机类型订单货位信息")
	private String remark;


}

