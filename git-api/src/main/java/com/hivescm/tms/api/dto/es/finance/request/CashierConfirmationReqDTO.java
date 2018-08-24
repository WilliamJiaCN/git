package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author sql
 * @Date 10:412018\5\11 0011
 */
@Data
@ToString
public class CashierConfirmationReqDTO {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;
    @Mapping
    @ApiModelProperty("本次收款确认运单数量")
    private Integer waybillCount;
    @Mapping
    @ApiModelProperty(value = "是否到付收款", required = true)
    private Boolean isToPayReceipt;
    @Mapping
    @ApiModelProperty("本次到付收款金额")
    private BigDecimal toPayReceiptAmount;
    @Mapping
    @ApiModelProperty(value = "是否代收货款收款", required = true)
    private Boolean isCollectionOnDeliveryReceipt;
    @Mapping
    @ApiModelProperty("本次代收货款收款金额")
    private BigDecimal collectionOnDeliveryReceiptAmount;
    @Mapping
    @ApiModelProperty("收款总金额")
    private BigDecimal totalReceiptAmount;
    @Mapping
    @ApiModelProperty(value = "付款方式", required = true)
    private String paymentMode;
    @Mapping
    @ApiModelProperty(value = "结算方式（来源于BOSS提供字典)")
    private Integer settlementMode;
    @Mapping
    @ApiModelProperty(value = "结算方式名称（来源于BOSS提供字典)")
    private String settlementModeName;
    @Mapping
    @ApiModelProperty(value = "支付渠道")
    private String payChannel;
    @Mapping
    @ApiModelProperty(value = "收银时间", required = true)
    private Long cashierTime;
    @Mapping
    @ApiModelProperty(value = "收款账户", required = true)
    private String receiveAccount;
    @Mapping
    @ApiModelProperty(value = "付款账户")
    private String payAccount;
    @Mapping
    @ApiModelProperty(value = "备注")
    private String comment;
    @Mapping
    @ApiModelProperty("操作用户ID")
    private Integer userId;
    @Required
    @ApiModelProperty(value = "当前网点ID", required = true)
    private Integer curentOrgId;
    @Mapping
    @ApiModelProperty("集团ID")
    private Long groupId;
    @Mapping
    @ApiModelProperty("运单号")
    private String waybillCode;
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;

}
