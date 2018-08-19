package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceManageCashSerialListReqDTO implements Serializable{

    private static final long serialVersionUID = 6932410963088700455L;

    @Mapping
    @ApiModelProperty(value = "日期类型 0:制单时间 1：发生时间")
    private Integer dateType;
    @Mapping
    @ApiModelProperty(value = "开始时间")
    private Long dateStart;
    @Mapping
    @ApiModelProperty(value = "结束时间")
    private Long dateEnd;
    @Mapping
    @ApiModelProperty(value = "收支网点")
    private List<Integer> receiptPayNetworkIds;
    @Mapping
    @ApiModelProperty(value = "资金账号")
    private List<String> fundAccount;
    @Mapping
    @ApiModelProperty(value = "来源单号")
    private String ordersourceId;
    @Mapping
	@ApiModelProperty("来源单号")
	private List<String> ordersourceIdList;
    @Mapping
    @ApiModelProperty(value = "付款单号")
    private String payCode;
    @Mapping
    @ApiModelProperty(value = "收款单号")
    private String receiptCode;
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
