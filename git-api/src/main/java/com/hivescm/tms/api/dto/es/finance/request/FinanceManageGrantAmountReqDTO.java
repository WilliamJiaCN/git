package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 发放确认 数据传输类
 * 
 * @author wenxiong.jia
 * @date 2018/5/7
 */
@Data
@ToString
public class FinanceManageGrantAmountReqDTO implements Serializable{

	private static final long serialVersionUID = -9204368424126134695L;

	/**
	 * 主键
	 */
	@Logger
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	/**
	 * 本次发放货款
	 */
	@ApiModelProperty(value = "本次发放货款", required = true)
	private BigDecimal thisSendAmount;
	/**
	 * 本次实结货款扣
	 */
	@ApiModelProperty(value = "本次实结货款扣", required = true)
	private BigDecimal thisActualGoodsAmount;
	/**
	 * 本次实结货款手续费
	 */
	@ApiModelProperty(value = "本次实结货款手续费", required = true)
	private BigDecimal thisActualGoodsFee;
	/**
	 * 本次付款手续费
	 */
	@ApiModelProperty(value = "本次付款手续费", required = true)
	private BigDecimal thisPayFee;
	/**
	 * 本次实付金额
	 */
	@ApiModelProperty(value = "本次实付金额")
	private BigDecimal thisActualAmount;
	/**
	 * 付款手续费（累计）
	 */
	@ApiModelProperty(value = "付款手续费", hidden = true)
	private BigDecimal totalPayFee;
	/**
	 * 实付金额（累计）
	 */
	@ApiModelProperty(value = "实付金额", hidden = true)
	private BigDecimal totalActualAmount;

}

