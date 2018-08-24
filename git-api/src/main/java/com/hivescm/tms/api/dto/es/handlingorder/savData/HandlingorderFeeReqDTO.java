package com.hivescm.tms.api.dto.es.handlingorder.savData;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@ToString
public class HandlingorderFeeReqDTO implements Serializable{
	 private static final long serialVersionUID = 1779764710194959965L;
	    @Override  
	    public HandlingorderFeeReqDTO clone() {  
	    	HandlingorderFeeReqDTO order = null;  
	        try{  
	        	order = (HandlingorderFeeReqDTO)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return order;  
	    }
	    
	    /**
	     * 费用类型ID(数据字典)
	     */
	    @Mapping
	    @ApiModelProperty("费用类型ID(数据字典)")
	    private String feeType;
	    
	    @Mapping
	    @ApiModelProperty("费用类型ID(数据字典)")
	    private String feeTypeName;

	    /**
	     * 费用金额
	     */
	    @Mapping
	    @ApiModelProperty("费用金额")
	    private BigDecimal amount;

	    /**
	     * 付款方ID
	     */
	    @Mapping
	    @ApiModelProperty("付款方ID")
	    private Integer payerId;

	    /**
	     * 收款方ID
	     */
	    @Mapping
	    @ApiModelProperty("收款方ID")
	    private Integer payeeId;

	    /**
	     * 费用说明
	     */
	    @Mapping
	    @ApiModelProperty("费用说明")
	    private String remark;


	    /*******冗余字段******************/

	    /**
	     * 付款方名称
	     */
	    @Mapping
	    @ApiModelProperty("付款方名")
	    private String payerName;

	    /**
	     * 收款方名称
	     */
	    @Mapping
	    @ApiModelProperty("收款方名臣")
	    private String payeeName;
}
