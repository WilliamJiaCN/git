package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 到货确认查看请求体
 * @author 张文龙
 * @since 2017/9/13
 */
@Data
@ToString
public class GetTransportArriveListReqDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8748793261495040325L;


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
     * 到货网点
     */
    @ApiModelProperty(value = "网点ID")
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;

    @ApiModelProperty("运输批次ID")
    private Long transportId;

    @ApiModelProperty("到货批次ID")
    private Long arriveId;

    @ApiModelProperty("到货批次")
    private String arrivalBatch;

    @ApiModelProperty("发车批次")
    private String departBatch;
    
    @ApiModelProperty("起始到货时间")
    private Long arrivalTimeStart;
    
    @ApiModelProperty("结束到货时间")
    private Long arrivalTimeEnd;
    
    /**
     * 发车网点id
     */
    @ApiModelProperty("发车网点id")
    private List<Integer> departBranchId;

    /**
     * 目标网点编码
     */
    @ApiModelProperty("目标网点编码")
    private List<Integer> arrivalBranchId;

    @ApiModelProperty("分页大小")
    private Integer pageSize = 10;

    @ApiModelProperty("当前页数")
    private Integer currentPage = 1;

    @ApiModelProperty("状态:1:未确认;2:已确认")
    private Integer status;

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
