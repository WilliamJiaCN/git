package com.hivescm.tms.api.dto.es.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
/**
 * 
* <p>Title:SignPackageDetailsDTO </p>
* <p>Description:签收包裹明细表 </p>
* <p>Company: 蜂网供应链（上海）有限公司</p> 
* @author 王小雪 
* @date 上午11:24:39
 */

@ToString
@Data
public class SignPackageDetailsDTO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	    * 主键
	    */
	   private @Mapping @ApiModelProperty("主键") Long id;
	   /**
	    * SKUID
	    */
	   private @Mapping @ApiModelProperty("SKUID") String skuid;

	   /**
	    * 商品名称
	    */
	   private @Mapping @ApiModelProperty("商品名称") String goodsName;

	   /**
	    * 包裹id
	    */
	   private @Mapping @ApiModelProperty("包裹id") Long packageId;

	   /**
	    * 数量
	    */
	   private @Mapping @ApiModelProperty("数量") Integer amount;

	   /**
	    * 单位
	    */
	   private @Mapping @ApiModelProperty("单位") String unit;

	   /**
	    * 主单位数量
	    */
	   private @Mapping @ApiModelProperty("主单位数量") Integer mainUnitNum;

	   /**
	    * 主单位
	    */
	   private @Mapping @ApiModelProperty("主单位") String mainUnit;
	   
	   /**
	    * 实际取货量
	    */
	   private @Mapping @ApiModelProperty("实际取货量") Integer deliveryNum;

	   /**
	    * 三级单位
	    */
	   private @Mapping @ApiModelProperty("三级单位") List<UnitLevelDTO> unitLevelDTOS;

}
