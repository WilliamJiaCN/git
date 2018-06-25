package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class FinanceManageCashTransferAddRespDTO implements Serializable{

    @Mapping
    @ApiModelProperty("主键ID")
    private Long id;

    @Mapping
    @ApiModelProperty("来源单号")
    private String ordersourceId;

    @Mapping
    @ApiModelProperty("单据类型 1.运单 2.代收货款 3. 派车单 4.发车单 5.到货单 6.装卸单 7.其他收入 8.其他支出 9.现金转账")
    private Integer codType;

    @Mapping
    @ApiModelProperty("单据类型名称 1.运单 2.代收货款 3. 派车单 4.发车单 5.到货单 6.装卸单 7.其他收入 8.其他支出 9.现金转账")
    private String codTypeName;

    @Mapping
    @ApiModelProperty("费用类型")
    private String feeTypeName;

    @Mapping
    @ApiModelProperty("支付方式")
    private Integer paymentType;

    @Mapping
    @ApiModelProperty("支付方式名称")
    private String paymentTypeName;

    @Mapping
    @ApiModelProperty("资金账号")
    private String fundAccount;

    @Mapping
    @ApiModelProperty("收入")
    private BigDecimal receiptAmount;

    @Mapping
    @ApiModelProperty("支出")
    private BigDecimal payAmount;

    @Mapping
    @ApiModelProperty("收支网点ID")
    private Integer receiptPayNetworkId;

    @Mapping
    @ApiModelProperty("收支网点名称")
    private String receiptPayNetworkName;

    @Mapping
    @ApiModelProperty("核算网点ID")
    private Integer settleNetworkId;

    @Mapping
    @ApiModelProperty("核算网点名称")
    private String settleNetworkName;

    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    @Mapping
    @ApiModelProperty("发生时间")
    private Long receiptPayTime;

    @Mapping
    @ApiModelProperty("制单时间")
    private Long createBillTime;

}
