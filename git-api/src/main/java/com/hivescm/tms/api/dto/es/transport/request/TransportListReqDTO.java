package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 查询运输批次列表请求对象
 * @author 张文龙
 * @since 2017/9/05 
 */
@Data
@ToString
public class TransportListReqDTO implements Serializable {
    private static final long serialVersionUID = -532151097077810783L;


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

    /**当前网点
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;


    /**
     * 运输批次ID
     */
    @ApiModelProperty("运输批次ID")
    private Long transportId;
    
    /**
     * 运输批次状态
     */
    @ApiModelProperty("运输批次状态")
    private Integer status;
    
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
     * 多选到达网点
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
    @ApiModelProperty("发车批次 ， 自动生成")
    private String departBatch;
    
    /**
     * 途径网点状态
     */
    @ApiModelProperty("途径网点状态")
    private Integer branchStatus;
    
    /**
     * 发车类型  1、干线 2、短途
     */
    @ApiModelProperty("发车类型  1、干线 2、短途")
    private Integer departType;
    
    
    @ApiModelProperty("app 端查询字段（司机姓名/发车网点/发车批次）")
    private String appFieldValues;
    
    
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
