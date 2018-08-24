package com.hivescm.tms.api.dto.print.waybill;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@ToString
public class WaybillFeePrintDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3367479618188321399L;

	/**
	 * 主键	
	 */
    private @ApiModelProperty("主键ID") Long id;
    
    private @ApiModelProperty("运单id") Long waybillId;
    
    /**
     * 属性id
     */
    private @ApiModelProperty("属性id") Integer attrId;
	 /**
     * 属性名称
     */
    private @ApiModelProperty("属性名称") String attrName;
    /**
     * 费用
     */
    private @ApiModelProperty("费用") BigDecimal fee;
}
