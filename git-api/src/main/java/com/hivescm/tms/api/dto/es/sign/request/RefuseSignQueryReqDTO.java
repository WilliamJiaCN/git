package com.hivescm.tms.api.dto.es.sign.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import com.hivescm.framework.logger.api.annotation.Logger;

/**
 * 拒收单查询请求实体
 * @author 杨彭伟
 * @date 2017-11-16 11:49
 */
@Data
@ToString
public class RefuseSignQueryReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("拒收日期-开始时间")
    private Long startRefuseTime;
    @ApiModelProperty("拒收日期-结束时间")
    private Long endRefuseTime;
    @ApiModelProperty("拒收类型")
    private Integer refuseType;
    @Logger
    @ApiModelProperty("拒收单号")
    private String refuseCode;
    @ApiModelProperty("分页 - 每页显示数")
    private Integer pageSize;
    @ApiModelProperty("分页 - 当前页数")
    private Integer currentPage;
    
    @ApiModelProperty("公司Id")
    private Long companyId;
    
    @ApiModelProperty("网点Id")
    private Integer branchId;
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
