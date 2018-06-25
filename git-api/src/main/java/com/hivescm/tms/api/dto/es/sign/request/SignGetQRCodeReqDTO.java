package com.hivescm.tms.api.dto.es.sign.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 签收获取二维码DTO
 * @author lhf
 * @date 2017年11月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SignGetQRCodeReqDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 运单id
	 */
	private @Mapping @ApiModelProperty("运单id") @Logger Long waybillId;
	/**
	 * 用户编号
	 */
	private @Mapping @ApiModelProperty("用户编号") @Logger String userNumber;
	/**
	 * 公司id
	 */
    private @Mapping @ApiModelProperty("公司id")  Long companyId;
    /**
     * 支付方式 :账户余额：SOOPAY,微信主扫：WXINITIATIVE,微信被扫：WXPASSIVE,微信公众号：WXOTHER ,支付宝主扫：ZFBINITIATIVE,
     *        支付宝被扫：ZFBPASSIVE 支付宝立码支付：ZFBOTHER, 支付宝SDK支付：ZFBSDK 微信SDK:
     */
    private @Mapping @ApiModelProperty("支付方式(微信主扫:WXINITIATIVE,支付宝主扫：ZFBINITIATIVE)") String paymentMode;
    /**
	 * 订单金额(以分为单位)
	 */
    private @Mapping @ApiModelProperty("订单金额(以分为单位)") BigDecimal amountMoney;

	private @ApiModelProperty("是否装车") Boolean loaded;

	private @ApiModelProperty("是否装车") Integer signType;
}
