package com.hivescm.tms.api.dto.es.handlingorder;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@ToString
public class HandlingorderGoodsEsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Override  
	    public HandlingorderGoodsEsDTO clone() {  
		 HandlingorderGoodsEsDTO order = null;  
	        try{  
	        	order = (HandlingorderGoodsEsDTO)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return order;  
	    }


	public Long groupByHandlingOrderDetailId(){
		return this.handlingorderDetailId;
	}
	 /**
		 * 主键
		 */
	    @Logger
	    @Mapping
	    @ApiModelProperty("主键")
		private Long id;

		/**
		 * 裝卸单ID
		 */
	    @Logger
	    @Mapping
	    @ApiModelProperty("装卸单ID")
		private Long handlingorderId;
		/**
		 * 货物库存ID
		 */
	    @Mapping
	    @ApiModelProperty("装卸单明细ID")
		private Long handlingorderDetailId;
		/**
		 * 公司id
		 */
	    @Mapping
	    @ApiModelProperty("装卸单ID")
		private Long companyId;
	    @Mapping
		@ApiModelProperty("公司名称")
		private String companyName;
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
		 * 创建人
		 */
	    @Mapping
	    @ApiModelProperty("创建人")
		private Integer createUser;
	    @Mapping
	    @ApiModelProperty("创建人")
		private String createUserName;

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

	    @Mapping
	    @ApiModelProperty("修改人")
		private Integer updateUserName;
		/**
		 * 修改时间
		 */
	    @Mapping
	    @ApiModelProperty("修改时间")
		private Long updateTime;

		/**
		 * 货物库存ID
		 */
	    @Mapping
	    @ApiModelProperty("库存ID")
		private Long waybillStockDetailId;

	

}
