package com.hivescm.tms.api.dto.es.orderrule;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class CarrierCapacityConfigEsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7651197497242234016L;
	
	/**
     * 主键
     */
	@Mapping
	@ApiModelProperty("主键ID")
    private Long id;

    /**
     * 承运商ID
     */
	@Mapping
	@ApiModelProperty("承运商ID")
    private Integer carrierId;
	
	/**
     * 仓库ID
     */
	@Mapping
	@ApiModelProperty("仓库ID")
    private Integer warehouseId;

    /**
     * 类型 同城、干线
     */
	@Mapping
	@ApiModelProperty("运力类型")
    private Integer type;

    /**
     * 配置日期
     */
	@Mapping
	@ApiModelProperty("配置日期")
    private String configDate;

    /**
     * 承运商线路ID
     */
	@Mapping
	@ApiModelProperty("承运商线路ID")
    private Long carrierLineId;
    
    /**
     * 承运商网点ID
     */
	@Mapping
	@ApiModelProperty("承运商网点ID")
    private Integer branchId;

    /**
     * 服务ID   （根据服务获取配送时间、提货时间、截单时间）
     */
	@Mapping
	@ApiModelProperty("服务ID")
    private Long serviceId;

    /**
     * 时效（提货完成到配送）
     */
	@Mapping
	@ApiModelProperty("配送时效")
    private BigDecimal effectiveness;

    /**
     * 最大单数
     */
	@Mapping
	@ApiModelProperty("最大单数")
    private Integer maxOrder;

    /**
     * 最大重量
     */
	@Mapping
	@ApiModelProperty("最大重量")
    private BigDecimal maxWeight;

    /**
     * 最大体积
     */
	@Mapping
	@ApiModelProperty("最大体积")
    private BigDecimal maxVolume;

    /**
     * 状态  1、未提交 2、已提交 3、已过期
     */
	@Mapping
	@ApiModelProperty("运力状态")
    private Integer status;

    /**
     * 已分配重量
     */
	@Mapping
	@ApiModelProperty("已分配重量")
    private BigDecimal distributionWeight;

    /**
     * 已分配体积
     */
	@Mapping
	@ApiModelProperty("已分配体积")
    private BigDecimal distributionVolume;

    /**
     * 已分配单数
     */
	@Mapping
	@ApiModelProperty("已分配单数")
    private Integer distributionOrder;

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
	
	// ********************************冗余可分配体积  重量  单数  信息***********************************

	 /**
     * 可分配重量
     */
	@Mapping
	@ApiModelProperty("可分配重量")
    private BigDecimal vaildWeight;

    /**
     * 可分配体积
     */
	@Mapping
	@ApiModelProperty("可分配体积")
    private BigDecimal vaildVolume;

    /**
     * 可分配单数
     */
	@Mapping
	@ApiModelProperty("已分配单数")
    private Integer vaildOrder;
}
