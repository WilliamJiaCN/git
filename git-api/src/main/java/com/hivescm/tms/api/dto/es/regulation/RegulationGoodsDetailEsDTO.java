package com.hivescm.tms.api.dto.es.regulation;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 调拨货物明细
 *
 * @author 张文龙
 */
@Data
@ToString
public class RegulationGoodsDetailEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7869020627199024741L;

	/**
     * 主键
     */
	@Mapping
	@ApiModelProperty("主键")
    private Long id;

    /**
     * 公司ID
     */
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 调拨批次ID
     */
	@Mapping
	@ApiModelProperty("调拨批次ID")
    private Long regulationId;

    /**
     * 商品ID
     */
	@Mapping
	@ApiModelProperty("商品ID")
    private Long goodsId;

    /**
     * 调拨件数
     */
	@Mapping
	@ApiModelProperty("调拨件数")
    private Integer regulationAmount;

    /**
     * 调拨重量
     */
	@Mapping
	@ApiModelProperty("调拨重量")
    private BigDecimal regulationWeight;

    /**
     * 调拨体积
     */
	@Mapping
	@ApiModelProperty("调拨体积")
    private BigDecimal regulationVolume;

    /**
     * 运单ID
     */
	@Mapping
	@ApiModelProperty("运单ID")
    private Long waybillId;

    /**
     * 库存明细ID
     */
	@Mapping
	@ApiModelProperty("库存明细ID")
    private Long waybillStockDetailId;

    /**
     * 调拨批次运单明细ID
     */
	@Mapping
	@ApiModelProperty("调拨批次运单明细ID")
    private Long regulationWaybillDetailId;

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


	////////////////////////////////冗余字段////////////////////////////////
	/**
	 * 运单编码
	 */
	@Mapping
	@ApiModelProperty("运单编码")
	private String code;

	/**
	 * 包装
	 */
	@Mapping
	@ApiModelProperty("包装")
	private String packageName;

	/**
	 * 包装
	 */
	@Mapping
	@ApiModelProperty("货物名称")
	private String goodsName;

	/**
	 * 调拨备注信息
	 */
	@Mapping
	@ApiModelProperty("调拨备注信息")
	private String remark;


	/////////////////////////库存明细冗余字段///////////////////////



	/**
	 * 调拨件数
	 */
	@Mapping
	@ApiModelProperty("可调拨件数")
	private Integer stockAmount;

	/**
	 * 调拨重量
	 */
	@Mapping
	@ApiModelProperty("可调拨重量")
	private BigDecimal stockWeight;

	/**
	 * 调拨体积
	 */
	@Mapping
	@ApiModelProperty("可调拨体积")
	private BigDecimal stockVolume;

	@Mapping
	@ApiModelProperty("开单件数")
	private Integer totalNum;


}