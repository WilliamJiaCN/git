package com.hivescm.tms.api.dto.es.handlingorder.savData;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class HandlingorderGoodsReqDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Override  
	    public HandlingorderGoodsReqDTO clone() {  
		 HandlingorderGoodsReqDTO order = null;  
	        try{  
	        	order = (HandlingorderGoodsReqDTO)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return order;  
	    }
	 
	 
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
	    @ApiModelProperty("装卸件数")
		private Integer packageNum;

		/**
		 * 重量
		 */
	    @Mapping
	    @ApiModelProperty("装卸重量")
		private BigDecimal weight;

		/**
		 * 体积
		 */
	    @Mapping
	    @ApiModelProperty("装卸体积")
		private BigDecimal volume;
	    /**
		 * 件数
		 */
	    @Mapping
	    @ApiModelProperty("总件数")
		private Integer totalNum;

		/**
		 * 重量
		 */
	    @Mapping
	    @ApiModelProperty("总重量")
		private BigDecimal totalWeight;

		/**
		 * 体积
		 */
	    @Mapping
	    @ApiModelProperty("总体积")
		private BigDecimal totalVolume;


		/**
		 * 货物库存ID
		 */
	    @Mapping
	    @ApiModelProperty("库存ID")
		private Long waybillStockDetailId;

	

}
