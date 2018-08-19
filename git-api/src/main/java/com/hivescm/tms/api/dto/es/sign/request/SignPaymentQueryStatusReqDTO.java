package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author lhf
 * @date 2017年11月21日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SignPaymentQueryStatusReqDTO implements Serializable{
	private static final long serialVersionUID = 8419116825387590650L;
	/**
	 * 运单id
	 */
	private @Mapping@ApiModelProperty("运单id") @Logger Long waybillId;
	/**
	 * 公司id
	 */
    private @Mapping @ApiModelProperty("公司id") Long companyId;
    /**
   	 * 订单金额(以分为单位)
   	 */
    private @Mapping @ApiModelProperty("订单金额(以分为单位)") BigDecimal amountMoney;
    /**
	 * 支付订单号(支付系统的id)
	 */
	private @Mapping @ApiModelProperty("支付订单号(支付系统的id)") String queryOrderNo;
	/**
	 * tms业务平台编号 :默认4000
	 */
	private @Mapping @ApiModelProperty("tms业务平台编号 :默认4000") String businessCode;
	/**
	 * tms业务平台订单号(获取二维码时候返回的)
	 */
	private @Mapping @ApiModelProperty("tms业务平台订单号(获取二维码时候返回的)") String queryBusinessOrderNo;
    
}
