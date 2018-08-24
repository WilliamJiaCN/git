package com.hivescm.tms.api.dto.es.orderrule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@ToString
public class CarrierOrderRuleEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8255331378831517481L;
	
	/**
     * 主键
     */
	@Mapping
	@ApiModelProperty("主键")
    private Integer id;
	
	/**
     * 承运商ID
     */
	@Mapping
	@ApiModelProperty("承运商ID")
    private Integer carrierId;
	
	/**
     * 网点ID
     */
	@Mapping
	@ApiModelProperty("网点ID")
    private Integer branchId;
    
	/**
     * 商品类型
     */
	@Mapping
	@ApiModelProperty("商品类型")
    private String goodsTypes;

    /**
     * 商品最大长度（cm）
     */
	@Mapping
	@ApiModelProperty("商品最大长度")
    private Integer maxLength;

    /**
     * 商品最大宽度（cm）
     */
	@Mapping
	@ApiModelProperty("商品最大宽度")
    private Integer maxWidth;

    /**
     * 商品最大高度（cm）
     */
	@Mapping
	@ApiModelProperty("商品最大高度")
    private Integer maxHeight;

    /**
     * 每日最大接单数 0 表示无限制
     */
	@Mapping
	@ApiModelProperty("每日最大接单数")
    private Integer maxOrder;

    /**
     * 每日最大接单重量 0 表示无限制
     */
	@Mapping
	@ApiModelProperty("每日最大接单重量")
    private BigDecimal maxWeight;

    /**
     * 每日接单最大体积 0 表示无限制
     */
	@Mapping
	@ApiModelProperty("每日接单最大体积")
    private BigDecimal maxVolume;
	
	/**
     * 创建人
     */
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;

    /**
     * 创建时间
     */
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 修改人
     */
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;

    /**
     * 修改时间
     */
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
}
