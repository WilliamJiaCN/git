package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceManageReceiptListReqDTO implements Serializable{

    private static final long serialVersionUID = 6932410963088700455L;
    @Mapping
    @ApiModelProperty(value = "开始时间")
    private Long dateStart;
    @Mapping
    @ApiModelProperty(value = "结束时间")
    private Long dateEnd;
    @Mapping
    @ApiModelProperty(value = "收款网点ID")
    private List<Integer> deliveryNetworkIds;
    @Mapping
    @ApiModelProperty(value = "收银状态 1.未审核 2.未收银 3.部分收银 4.已收银")
    private Integer deliveryStatus;
    @Mapping
    @ApiModelProperty(value = "结算方式4、现付 5、欠付 6、月结 7、回单付 8、到付 9、货款扣 10、代收货款 11、代收货款手续费 ")
    private List<Integer> payWay;
    @Mapping
    @ApiModelProperty(value = "来源单号")
    private String ordersourceId;
    @Mapping
    @ApiModelProperty(value = "来源单号批量")
    private List<String> ordersourceIdList;
    @Mapping
    @ApiModelProperty(value = "发货网点ID")
    private List<Integer> invoiceOrgIds;
    @Mapping
    @ApiModelProperty(value = "目的网点ID")
    private List<Integer> destIds;
    @Mapping
    @ApiModelProperty(value = "发货公司名称")
    private String invoiceCompanyName;
    @Mapping
    @ApiModelProperty(value = "发货人姓名")
    private String invoiceUserName;
    @Mapping
    @ApiModelProperty("发货人手机号")
    private String invoiceUserMobile;
    @Mapping
    @ApiModelProperty("发货人电话")
    private String invoiceTelNo;
    @Mapping
    @ApiModelProperty("收货公司名称")
    private String receiptCompanyName;
    @Mapping
    @ApiModelProperty("收货人姓名")
    private String receiptUserName;
    @Mapping
    @ApiModelProperty("收货人手机号")
    private String receiptUserMobile;
    @Mapping
    @ApiModelProperty("收货人电话")
    private String receiptConsignorTelNo;
    @Mapping
    @ApiModelProperty("单据类型 1.运单  2.他其收入 ")
    private List<Integer> codeType;
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
