package com.hivescm.tms.api.dto.es.regulation.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class GetWaybillStockForRegulationReq implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -2316391188623497579L;

	/**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID")
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;
    
    /**
	 * 仓库id
	 */
	@ApiModelProperty("仓库ID")
	private Integer warehouseId;
    
    /**
     * 入库日起开始
     */
    @ApiModelProperty(value = "入库日起开始")
    private Long createTimeStart;
    
    /**
     * 入库日起结束
     */
    @ApiModelProperty(value = "入库日起结束")
    private Long createTimeEnd;
    
}
