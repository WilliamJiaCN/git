package com.hivescm.tms.api.dto.es.sign.request;

import java.io.Serializable;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * 支付结果通知请求DTO
 * @author lhf
 * @date 2017-11-22
 */
@Data
@ToString
public class PayResultNotifyReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    private @Mapping @ApiModelProperty("支付订单号") @Logger String orderNo;
    private @Mapping @ApiModelProperty("业务平台订单号") @Logger String businessOrderNo;
    /**
     * TRADE_SUCCESS:交易成功,TRADE_CLOSED:交易关闭,TRADE_CANCEL:交易撤销,TRADE_FAIL:交易失败
     */
    @ApiModelProperty("交易状态(TRADE_SUCCESS:交易成功,TRADE_CLOSED:交易关闭,TRADE_CANCEL:交易撤销,TRADE_FAIL:交易失败)")
    private @Mapping String tranStatus;
    private @Mapping @ApiModelProperty("业务平台退款号") @Logger String refundId;
    private @Mapping @ApiModelProperty("签名") String sign;

}
