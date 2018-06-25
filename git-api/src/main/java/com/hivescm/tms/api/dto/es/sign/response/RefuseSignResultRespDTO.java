package com.hivescm.tms.api.dto.es.sign.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 拒收单审核结果
 * @author 杨彭伟
 * @date 2017-11-17 11:51
 */
@Data
@ToString
public class RefuseSignResultRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("销退单对应的运单id")
    private Long refuseSignWaybillId;
    @ApiModelProperty("销退单(拒签)金额")
    private BigDecimal refusePay;
}
