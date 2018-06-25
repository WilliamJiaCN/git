package com.hivescm.tms.api.dto.es.finance.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceManageListForReceiptReqDTO implements Serializable{

    private static final long serialVersionUID = 4527891484312255725L;

    @ApiModelProperty("单据类型 1.运单  8.代收货款 9.他其收入")
    private List<Integer> codeType;

    @ApiModelProperty(value = "结算方式 4、现付 5、欠付 6、月结 7、回单付 8、到付 9、货款扣 10、代收货款 11、代收货款手续费")
    private List<Integer> payWay;

    @ApiModelProperty(value = "来源单号")
    private String ordersourceId;

    @ApiModelProperty(value = "发货人姓名")
    private String invoiceUserName;

    @ApiModelProperty("收货人姓名")
    private String receiptUserName;

    @ApiModelProperty(value = "当前网点")
    private Integer orgId;
}
