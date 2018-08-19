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
public class HandlingorderFeeEsDTO implements Serializable{
	 private static final long serialVersionUID = 1779764710194959965L;
	    @Override  
	    public HandlingorderFeeEsDTO clone() {  
	    	HandlingorderFeeEsDTO order = null;  
	        try{  
	        	order = (HandlingorderFeeEsDTO)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return order;  
	    }
	    /**
	     * 主键
	     */
	    @Logger
	    @Mapping
	    @ApiModelProperty("主键")
	    private Long id;
	    /**
	     * 公司id
	     */
	    @Mapping
	    @ApiModelProperty("公司id")
	    private Long companyId;
	    @Mapping
		@ApiModelProperty("公司名称")
		private String companyName;
	    /**
	     * 派车单ID
	     */
	    @Logger
	    @Mapping("handlingorderId")
	    @ApiModelProperty("装卸单ID ")
	    private Long handlingorederId;
	    
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
	    
	    @ApiModelProperty("网点ID ， 动态查询")
	    private Integer branchId;
	    
	    @ApiModelProperty("网点名称 ， 动态查询")
	    private String branchName;
	    
	    @ApiModelProperty("装卸单号，动态查询")
	    private String handlingOrderCode;
}
