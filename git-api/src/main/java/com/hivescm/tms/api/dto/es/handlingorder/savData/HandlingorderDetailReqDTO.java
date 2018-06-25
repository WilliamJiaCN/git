package com.hivescm.tms.api.dto.es.handlingorder.savData;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 装卸单明细表
 * 
 * @author LUTINGTING
 *
 */
@Data
@ToString
public class HandlingorderDetailReqDTO implements Serializable {
	private static final long serialVersionUID = -5977815314456807789L;

	@Mapping
	@ApiModelProperty(value="运单id",required=true)
	private Long waybillId;
	@Mapping
	@ApiModelProperty(value="批次明细id（卸货时赋值）",required=true)
	private Long batchDetailId;
	@Mapping
	@ApiModelProperty(value="运单号",required=true)
	private String code;

	@Mapping
	@ApiModelProperty(value="货物名称",required=true)
	private String goodsName;

	@Mapping
	@ApiModelProperty(value="数量",required=true)
	private Integer packageNum;

	@Mapping
	@ApiModelProperty(value="体积",required=true)
	private BigDecimal weight;

	@Mapping
	@ApiModelProperty(value="重量",required=true)
	private BigDecimal volume;
	/**
	 * 总体积
	 */
	private @Mapping @ApiModelProperty("总体积") BigDecimal totalVolume;
	/**
	 * 总重量
	 */
	private @Mapping @ApiModelProperty("总重量") BigDecimal totalWeight;
	/**
	 * 总件数
	 */
	private @Mapping @ApiModelProperty("总件数") Integer totalNum;
	@Mapping
	@ApiModelProperty("成本")
	private BigDecimal cost;
	@Mapping
	@ApiModelProperty("运费")
	private BigDecimal freight;
	@Mapping
	@ApiModelProperty("产值")
	private BigDecimal outputValue;
	@Mapping
	@ApiModelProperty("业务费")
	private BigDecimal busFee;

}