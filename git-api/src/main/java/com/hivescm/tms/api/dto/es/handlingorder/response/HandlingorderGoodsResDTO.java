package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@ToString
public class HandlingorderGoodsResDTO implements Serializable{


	private static final long serialVersionUID = 1L;

	 
	    /**
		 * 主键
		 */
	    @Logger
	    @Mapping
	    @ApiModelProperty("主键")
		private Long id;
		/**
		 * 运单货物明细ID
		 */
	    @Mapping
	    @ApiModelProperty("商品ID")
		private Long waybillGoodsId;

		/**
		 * 货物名称
		 */
	    @Mapping
	    @ApiModelProperty("货物名称")
		private String goodsName;

		/**
		 * 包装
		 */
	    @Mapping
	    @ApiModelProperty("包装")
		private String packages;

		/**
		 * 件数
		 */
	    @Mapping
	    @ApiModelProperty("件数")
		private Integer packageNum;

		/**
		 * 重量
		 */
	    @Mapping
	    @ApiModelProperty("重量")
		private BigDecimal weight;

		/**
		 * 体积
		 */
	    @Mapping
	    @ApiModelProperty("体积")
		private BigDecimal volume;


		/**
		 * 货物库存ID
		 */
	    @Mapping
	    @ApiModelProperty("库存ID")
		private Long waybillStockDetailId;

	

}
