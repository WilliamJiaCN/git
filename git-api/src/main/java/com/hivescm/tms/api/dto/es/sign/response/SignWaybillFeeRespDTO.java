package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 签收-运单费用详情
 * zouhx 2018-06-12
 */
@Data
@ToString
public class SignWaybillFeeRespDTO {
    @Mapping
    @ApiModelProperty("代收货款")
    private BigDecimal orderGoodsPayment;

    @Mapping
    @ApiModelProperty("二次派送费")
    private BigDecimal secondDeliveryFee;

    @Mapping
    @ApiModelProperty("到站其他费用")
    private BigDecimal otherFeeStation;

    @Mapping
    @ApiModelProperty("到付送货费")
    private BigDecimal toPayDeliveryFee;

    @Mapping @ApiModelProperty("到付")
    private BigDecimal cod;


}
