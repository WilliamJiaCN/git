package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class TransportWaybillListReqDTO {
	
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
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private Integer branchName;


    /**
     * 运输批次IDList
     */
    @ApiModelProperty("运输批次IDList")
    private List<Long> transportIdList;
    
    /**
     * 运输批次ID
     */
    @ApiModelProperty("运输批次ID")
    private Long transportId;
    
    /**
     * 查询列表发车起始时间
     */
    @ApiModelProperty("查询列表发车起始时间")
    private Long startDepartTime;
    
    /**
     * 查询列表发车结束时间
     */
    @ApiModelProperty("查询列表发车结束时间")
    private Long endDepartTime;
    
    /**
     * 查询列表发车起始时间
     */
    @ApiModelProperty("查询列表发车起始时间")
    private Long startArrivalTime;
    
    /**
     * 查询列表发车结束时间
     */
    @ApiModelProperty("查询列表发车结束时间")
    private Long endArrivalTime;
    
    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    private String vehicleNo;
    /**
     * 多选发车网点
     */
    @ApiModelProperty("发车网点Id")
    private List<Integer> oriBranchIds;
    
    /**
     *多选到达网点
     */
    @ApiModelProperty("到达网点Id")
    private List<Integer> destBranchIds;
    
    /**
     * 发车网点
     */
    @ApiModelProperty("发车网点Id")
    private Integer departBranchId;
    
    /**
     * 到达网点
     */
    @ApiModelProperty("到达网点Id")
    private Integer arrivalBranchId;
    
    /**
     * 发车批次 
     */
    @ApiModelProperty("发车批次 ")
    private String departBatch;
    
    /**
     * 承运商ID
     */
    @ApiModelProperty("承运商ID")
    private Integer carrierId;
    /**
     * 承运商名称
     */
    @ApiModelProperty("承运商名称")
    private String carrierName;

    /**
     * 发车网点名称
     */
    @ApiModelProperty("到车网点名称")
    private String departBranchName;
    
    /**
     * 到车网点名称
     */
    @ApiModelProperty("到车网点名称")
    private String arrivalBranchName;
    
    @ApiModelProperty("分页大小")
    private Integer pageSize = 10;

    @ApiModelProperty("当前页数")
    private Integer currentPage = 1;

    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize) {
            pageSize = 10;
        }
        if (null == currentPage) {
            currentPage = 1;
        }
    }
}
