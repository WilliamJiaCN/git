package com.hivescm.tms.api.dto.es.regulation.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class GetWaybillStockOfInStockReq implements Serializable {
	
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
     * 网点ID(操作网点)
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;
    
    /**
     * 库存网点ID(查询条件，可复选)
     */
    @ApiModelProperty(value = "网点ID")
    private List<Integer> branchIdList;
    
//    /**
//     * 网点ID(用户所拥有权限查看的网点)
//     */
//    @ApiModelProperty(value = "网点ID")
//    private List<Integer> permitBranchIdList;
    
    /**
	 * 仓库id
	 */
	@ApiModelProperty("仓库ID")
	private Integer warehouseId;

    /**
     * 库区id
     */
    @ApiModelProperty("库区id")
    private Integer warehouseAreaId;
    /**
     * 库位id
     */
    @ApiModelProperty("库位id")
    private Integer warehousePositionId;
    
    /**
     * 发货网点Id
     */
    @ApiModelProperty(value = "发货网点Id")
    private List<Integer> departBranchIds;
    
    /**
     * 目的网点Id
     */
    @ApiModelProperty(value = "目的网点Id")
    private List<Integer> arrivalBranchIds;
    
    /**
     * 运单号
     */
    @ApiModelProperty(value = "运单号")
    private String waybillCode;

    /**
     * 分页 - 每页显示数
     */
    private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 30;
    /**
     * 分页 - 当前页数
     */
    private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;


    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize || pageSize == 0) {
            pageSize = 30;
        }
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
    }

}
