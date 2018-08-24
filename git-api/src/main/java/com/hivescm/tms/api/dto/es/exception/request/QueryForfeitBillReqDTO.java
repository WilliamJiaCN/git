package com.hivescm.tms.api.dto.es.exception.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 查询罚款单请求对象
 *
 * @author 李洪春
 * @since 2017/10/10 14:30
 */
@Data
@ToString
public class QueryForfeitBillReqDTO implements Serializable {

    private static final long serialVersionUID = -6496395191824864983L;

    @ApiModelProperty(value = "公司ID", example = "2")
    private Long companyId;

    @ApiModelProperty("发起开始时间")
    private Long createTimeBegin;

    @ApiModelProperty("发起开始时间")
    private Long createTimeEnd;

    @ApiModelProperty("发起人姓名")
    private String createUserName;

    @ApiModelProperty("付款方姓名")
    private String payerName;

    @ApiModelProperty("收款方名称")
    private String payeeName;
    @ApiModelProperty("运单编码")
    private String waybillCode;

    @ApiModelProperty("状态条件 等于")
    private Integer status;

    @ApiModelProperty("状态条件 不等于")
    private Integer neStatus;

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
