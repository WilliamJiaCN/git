package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceManageGrantListReqDTO implements Serializable{

    private static final long serialVersionUID = 6932410963088700455L;

    @Mapping
    @ApiModelProperty(value = "日期类型 0.录单日期 1.签收日期 2.回收日期 3.汇款日期 4 发放日期 5 进账日期")
    private Integer dateType;
    @Mapping
    @ApiModelProperty(value = "开始时间")
    private Long dateStart;
    @Mapping
    @ApiModelProperty(value = "结束时间")
    private Long dateEnd;
    @Mapping
    @ApiModelProperty(value = "付款网点")
    private List<Integer> payBranchId;
    @Mapping
    @ApiModelProperty(value = "发货网点")
    private List<Integer> invoiceOrgId;
    @Mapping
    @ApiModelProperty(value = "目的网点")
    private List<Integer> destOrgId;
    @Mapping
    @ApiModelProperty(value = "运单号")
    private String orderSourceCode;
    @Mapping
    @ApiModelProperty(value = "运单号集合")
    private List<String> orderSourceCodeList;
    @Mapping
    @ApiModelProperty(value = "签收状态 1.未签收 2.部分签收 3.全部签收 4.拒签")
    private Integer signStatus;
    @Mapping
    @ApiModelProperty(value = "发放状态 1.未发放 2.部分发放 3.已发放")
    private Integer sendStatus;
    @Mapping
    @ApiModelProperty(value = "进账状态 1.未进账 2.已进账")
    private Integer acountStatus;
    @Mapping
    @ApiModelProperty("收款方")
    private String receiveName;
    @Mapping
    @ApiModelProperty("发货方")
    private String invoiceCompanyName;
    @Mapping
    @ApiModelProperty(value = "发货人")
    private String invoiceUserName;
    @Mapping
    @ApiModelProperty(value = "发货人手机")
    private String invoiceUserMobile;
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
