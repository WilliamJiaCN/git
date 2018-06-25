package com.hivescm.tms.api.dto.es.finance.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 付款确认 数据传输类
 * 
 * @author wenxiong.jia
 * @date 2018/5/2
 */
@Data
@ToString
public class FinanceManagePayAmountReqDTO implements Serializable{

	private static final long serialVersionUID = 9123752921323367848L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号")
	private Long lineNumber;
	/**
	 * 本次付款
	 */
	@ApiModelProperty(value = "本次付款")
	private BigDecimal thisPayment;
	/**
	 * 分摊手续费
	 */
	@ApiModelProperty(value = "分摊手续费")
	private BigDecimal apportionFee;
	/**
	 * 实付金额
	 */
	@ApiModelProperty(value = "实付金额")
	private BigDecimal actualAmount;
}

