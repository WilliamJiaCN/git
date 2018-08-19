package com.hivescm.tms.api.dto.es.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 发放货款
 * @author 杨彭伟
 * @date 2017-11-24 20:58
 */
@Data
@ToString
public class PaymentGrantDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Mapping
    @ApiModelProperty(value = "操作人", required = true)
    private Integer userId;
    @Mapping
    @ApiModelProperty(value = "操作人名称", required = true)
    private String userName;
    @Mapping
    @ApiModelProperty(value = "支付方式", required = true)
    private String paymentMode;
    @Mapping
    @ApiModelProperty("结算方式")
    private Integer settlementMode;
    @Mapping
    @ApiModelProperty("结算方式")
    private String settlementModeName;
    @Mapping
    @ApiModelProperty("支付渠道")
    private Integer payChannel;
    @Mapping
    @ApiModelProperty("支付渠道")
    private String payChannelName;
    @Mapping
    @ApiModelProperty(" 付款手续费")
    private BigDecimal serviceCharge;
    @Mapping
    @ApiModelProperty(value = "付款账户", required = true)
    private String payBankAccount;
    @Mapping
    @ApiModelProperty(value = " 收款账户", required = true)
    private String receivableBankAccount;
    @Mapping
    @ApiModelProperty("备注")
    private String remark;
    @Mapping
    @ApiModelProperty(value = "待付款记录id", required = true)
    private List<Long> ids;
    @Mapping
    @ApiModelProperty(value = "公司id", required = true)
    private Long companyId;
}
