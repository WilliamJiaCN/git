package com.hivescm.tms.api.dto.es.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * 
* <p>Title:SignPackageDTO </p>
* <p>Description: 签收包裹表</p>
* <p>Company: 蜂网供应链（上海）有限公司</p> 
* @author 王小雪 
* @date 上午11:23:52
 */

@Data
@ToString
public class SignPackageDTO implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	    * 包裹ID
	    */
	   private @Mapping @ApiModelProperty("包裹ID") Long id;
	   /**
	    * 公司ID
	    */
	   private @Mapping @ApiModelProperty("公司ID") Long companyId;

	   /**
	    * 包裹号
	    */
	   private @Mapping @ApiModelProperty("包裹号") String packageCode;

	   /**
	    * 订单编号
	    */
	   private @Mapping @ApiModelProperty("订单编号") String orderCode;
		 /**
	     * 总箱数
	     */
	   private @Mapping @ApiModelProperty("总箱数") Integer boxNum;

}
