package com.hivescm.tms.api.dto.es.exception.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.enums.biz.exception.WaybillExceptionPageTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 异常件查询请求对象
 *
 * @author 李洪春
 * @since 2017/9/26 17:59
 */
@Data
@ToString
public class QueryWaybillExceptionReqDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6574664493058869567L;

    @ApiModelProperty("公司ID")
    private Long companyId;
    
    /**
     * 异常ID
     */
    @ApiModelProperty("异常件ID")
    private Long exceptionId;

    /**
     * 反馈网点ID
     */
    @ApiModelProperty("反馈网点ID")
    private List<Integer> feedbackBranchId;

    /**
     * 反馈时间
     */
    @ApiModelProperty("反馈开始时间")
    private Long feedbackTimeBegin;

    /**
     * 反馈截止时间
     */
    @ApiModelProperty("反馈截止时间")
    private Long feedbackTimeEnd;

    /**
     * 异常状态
     */
    @ApiModelProperty("异常状态， 筛选条件，数据字典")
    private Integer status;

    /**
     * 页签类型
     */
    @ApiModelProperty("页签类型")
    private WaybillExceptionPageTypeEnum pageType;

    /**
     * 运单号
     */
    @ApiModelProperty("运单号")
    private String waybillCode;

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
