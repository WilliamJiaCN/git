package com.hivescm.tms.api.dto.es.sign.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.logger.api.annotation.Logger;

/**
 * 通知拒签结果请求实体
 *
 * @author 杨彭伟
 * @date 2017-11-14 14:35
 */
@Data
@ToString
public class RefuseSignNoticeReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Logger
    @ApiModelProperty("原运单号")
    private String code;
    @Logger
    @ApiModelProperty("原订单号")
    private String customerOrderCode;
    @Logger
    @ApiModelProperty("销退单对应的运单id")
    private Long refuseSignWaybillId;
    @Logger
    @ApiModelProperty("销退单id") 
    private Long salesReturnId;
    @ApiModelProperty("销退单(拒签)金额")
    private BigDecimal refusePay;
    @Logger
    private Long dispatcherId;
    @Logger
    @ApiModelProperty("公司id")
    private Long companyId;


}
