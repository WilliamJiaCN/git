package com.hivescm.tms.api.dto.es.regulation.request;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.stock.WaybillStockEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class CreatePositionTransReq implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2842147249563617274L;

	/**
     * 公司Id
     */
	@Required
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
     * 运单库存信息
     */
    @ApiModelProperty(value = "运单库存信息")
    private List<WaybillStockEsDTO> waybillStockEsDTOList;
    /**
	 * 备注信息
	 */
	@ApiModelProperty("备注信息")
	private String remark;

	// 转移说明
	@ApiModelProperty("转移说明")
	private String transferRemark;
	// 转移人
	@ApiModelProperty("转移人")
	private String transferName;
	// 转移时间
	@ApiModelProperty("转移时间")
	private String transferTime;


	/**
	 * 仓库id
	 */
	@ApiModelProperty("仓库ID")
	private Integer warehouseId;

	/**
	 * 库区id
	 */
	@ApiModelProperty("库区ID")
	private Integer warehouseAreaId;

	/**
	 * 库位id
	 */
	@ApiModelProperty("库位ID")
	private Integer warehousePositionId;

	/**
	 * 目标仓库id
	 */
	@ApiModelProperty("目标仓库ID")
	private Integer destWarehouseId;

	/**
	 * 目标库区id
	 */
	@ApiModelProperty("目标库区ID")
	private Integer destWarehouseAreaId;

	/**
	 * 目标库位id
	 */
	@ApiModelProperty("目标库位ID")
	private Integer destWarehousePositionId;
	
	/**
	 * 目标仓库名字
	 */
	@ApiModelProperty("目标仓库名字")
	private String destWarehouseName;
	
	/**
	 * 目标库区名字
	 */
	@ApiModelProperty("目标库区名字")
	private String destWarehouseAreaName;
	
	/**
	 * 目标库位名字
	 */
	@ApiModelProperty("目标库位名字")
	private String destWarehousePositionName;

}
