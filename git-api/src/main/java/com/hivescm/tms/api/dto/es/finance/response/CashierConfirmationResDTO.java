package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author sql
 * @Date 20:062018\5\11 0011
 */
@Data
@ToString
public class CashierConfirmationResDTO {
    @Mapping
    @ApiModelProperty("支付类型:8、到付 10、代收货款")
    private Integer payWay;
    @Mapping
    @ApiModelProperty("未收货款")
    private BigDecimal unreceipt_amount;
    @Mapping
    @ApiModelProperty("应收货款")
    private BigDecimal receiptAmount;
    @Mapping
    @ApiModelProperty("运单号")
    private String waybillCode;

}
