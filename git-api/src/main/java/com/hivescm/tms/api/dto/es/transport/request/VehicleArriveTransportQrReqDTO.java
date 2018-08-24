package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 到车管理运输批次查询条件
 *
 * @author 李洪春
 * @since 2017/9/6 11:00
 */
@Data
@ToString
public class VehicleArriveTransportQrReqDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6422413886685453262L;

    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传",hidden = true)
    private Long companyId;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传",hidden = true)
    private Integer branchId;

    /**
     * 发车类型  1、干线 2、短途
     */
    @ApiModelProperty(value = "发车类型  1、干线 2、短途",notes = "前端调用时不传")
    private Integer departType;

    /**
     * 到车状态
     */
    @ApiModelProperty(value = "状态",notes = "未达到:5,已到达:2")
    private Integer status;

    /**
     * 发车批次
     */
    @ApiModelProperty("发车批次")
    private String departBatch;

    /**
     * 发车网点ID
     */
    @ApiModelProperty("发车网点ID")
    private List<Integer> departBranchId;

    /**
     * 到达网点ID
     */
    @ApiModelProperty("到达网点ID")
    private List<Integer> arrivalBranchId;

    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    /**
     * 发车开始时间
     */
    @ApiModelProperty("发车开始时间")
    private Long departBeginTime;

    /**
     * 发车结束时间
     */
    @ApiModelProperty("发车截止时间")
    private Long departEndTime;


    /**
     * 到达时间开始时间
     */
    @ApiModelProperty("到达时间开始时间")
    private Long arriveBeginTime;


    /**
     * 到达时间截止时间
     */
    @ApiModelProperty("到达时间截止时间")
    private Long arriveEndTime;

    /**
     * 网点类型
     */
    @ApiModelProperty(value = "网点类型， 1、始发网点  2、途经网点  3、目的网点",notes = "前端不用传")
    private Integer branchType;

    /**
     * 当前页数
     */
    @Required
    @ApiModelProperty(value = "当前页数",required = true)
    private Integer currentPage;

    /**
     * 每页显示条数
     */
    @Required
    @ApiModelProperty(value = "每页显示条数",required = true)
    private Integer pageSize;

    @ApiModelProperty("/多条件查询参数")
    private String info;


}
