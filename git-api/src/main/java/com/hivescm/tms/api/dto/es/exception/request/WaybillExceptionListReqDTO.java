package com.hivescm.tms.api.dto.es.exception.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class WaybillExceptionListReqDTO implements Serializable{
    @Mapping
    @ApiModelProperty(value = "开始时间（必填）")
    private Long dateStart;

    @Mapping
    @ApiModelProperty(value = "结束时间（必填）")
    private Long dateEnd;

    @Mapping
    @ApiModelProperty("反馈网点ID  默认为登记网点")
    private List<Integer> feedbackBranchId;

    @Mapping
    @ApiModelProperty("异常类型 数据字典 1.破损、2.少货、3.多货、4.超重超方 5.拒签等--数据字典 tms-yclx")
    private Integer exceptionType;

    @Mapping
    @ApiModelProperty("运单号")
    private String code ;
    
    @Mapping
    @ApiModelProperty("异常状态 1.未提交 2.未处理 3.处理中 4.已办结")
    private Integer waybillExceptionStatus;
    
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;
    
    @Mapping
    @ApiModelProperty("网点ID")
    private Integer orgId;

    /**
     * 分页 - 每页显示数
     */
    @Required
    private @ApiModelProperty("分页 - 每页显示数(必填)") Integer pageSize = 20;
    /**
     * 分页 - 当前页数
     */
    @Required
    private @ApiModelProperty("分页 - 当前页数（必填）") Integer currentPage = 1;

    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize || pageSize == 0) {
            pageSize = 20;
        }
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
    }
}
