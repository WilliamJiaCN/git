package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceManageRecycleListReqDTO implements Serializable{
    private static final long serialVersionUID = -2519757067886032354L;
    @Mapping
    @ApiModelProperty(value = "日期类型 0制单时间 1签收时间 2回收时间 3汇款时间")
    private Integer dateType;
    @Mapping
    @ApiModelProperty(value = "开始时间")
    private Long dateStart;
    @Mapping
    @ApiModelProperty(value = "结束时间")
    private Long dateEnd;
    @Mapping
    @ApiModelProperty("收款网点ID")
    private List<Integer> receiveBranchId;
    @Mapping
    @ApiModelProperty("发货网点ID")
    private List<Integer> invoiceOrgId;
    @Mapping
    @ApiModelProperty("目的地网点ID")
    private List<Integer> destOrgId;
    @Mapping
    @ApiModelProperty("运单号")
    private String orderSourceCode;
    @Mapping
    @ApiModelProperty("运单号集合")
    private List<String> orderSourceCodeList;
    @Mapping
    @ApiModelProperty("签收状态")
    private Integer signStatus;
    @Mapping
    @ApiModelProperty("回收状态")
    private Integer recycleStatus;
    @Mapping
    @ApiModelProperty("汇款状态")
    private Integer remitStatus;
    @Mapping
    @ApiModelProperty("付款方")
    private String payeeName;
    @Mapping
    @ApiModelProperty("收货方")
    private String receiptCompanyName;
    @Mapping
    @ApiModelProperty("收货人名称")
    private String receiptUserName;
    @Mapping
    @ApiModelProperty("收货人手机号")
    private String receiptUserMobile;
    @Mapping
    @ApiModelProperty("中转公司")
    private String transitCompanyName;
    @Mapping
	@ApiModelProperty(value = "公司ID")
	private Long companyId;

    /**
     * 分页 - 每页显示数
     */
    private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 20;
    /**
     * 分页 - 当前页数
     */
    private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;

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
