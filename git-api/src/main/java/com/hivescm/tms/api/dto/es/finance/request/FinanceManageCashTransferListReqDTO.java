package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceManageCashTransferListReqDTO implements Serializable {
    private static final long serialVersionUID = 4881893715324424707L;

    @Mapping
    @ApiModelProperty(value = "日期类型 0.制单时间 1.提交时间 2.进账时间 （必填）")
    @Required
     private Integer dateType;
    @Mapping
    @ApiModelProperty(value = "开始时间（必填）")
    @Required
    private Long dateStart;
    @Mapping
    @ApiModelProperty(value = "结束时间（必填）")
    @Required
    private Long dateEnd;
    @Mapping
    @ApiModelProperty("网点类型 0 转账网点 1 进账网点")
    private Integer belongNetworkType;
    @Mapping
    @ApiModelProperty("所属网点")
    private List<Integer> belongNetwork;
    @Mapping
    @ApiModelProperty("转账账户")
    private List<String> transferAccount;
    @Mapping
    @ApiModelProperty("进账账户")
    private List<String> incomeAccount;
    @Mapping
    @ApiModelProperty("转账单号")
    private String transferOrdersource;
    @Mapping
    @ApiModelProperty("转账单号集合")
    private List<String> transferOrdersourceList;
    @Mapping
    @ApiModelProperty("转账状态 1.未提交 2.已提交 3.已进账")
    private Integer transferStatus;
    @Mapping
	@ApiModelProperty(value = "公司ID")
	private Long companyId;


    /**
     * 分页 - 每页显示数
     */
    @Required
    private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 20;
    /**
     * 分页 - 当前页数
     */
    @Required
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
