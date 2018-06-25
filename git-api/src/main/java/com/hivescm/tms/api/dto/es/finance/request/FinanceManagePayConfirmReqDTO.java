package com.hivescm.tms.api.dto.es.finance.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 付款确认 数据传输类
 * 
 * @author wenxiong.jia
 * @date 2018/5/2
 */
@Data
@ToString
public class FinanceManagePayConfirmReqDTO implements Serializable{

	private static final long serialVersionUID = 5630600641019686131L;

	/**
	 *金额列表 包含本次付款金额、分摊手续费、实付金额
	 *
	 */
	@ApiModelProperty(value = "金额列表")
	List<FinanceManagePayAmountReqDTO> financeManagePayAmountReqDtoList;
	/**
	 * 集团ID
	 */
	@ApiModelProperty(value = "集团ID")
	private Integer groupId;
	/**
	 * 付款时间
	 */
	@ApiModelProperty(value = "付款时间")
	private Long paymentTime;
	/**
	 * 支付方式ID
	 */
	@ApiModelProperty(value = "支付方式ID")
	private Integer paymentMethodId;
	/**
	 * 支付方式名称
	 */
	@ApiModelProperty(value = "支付方式名称")
	private String paymentMethodName;
	/**
	 * 结算方式ID
	 */
	@ApiModelProperty(value = "结算方式ID")
	private Integer billMethodId;
	/**
	 * 结算方式名称
	 */
	@ApiModelProperty(value = "结算方式名称")
	private String billMethodName;
	/**
	 * 支付渠道ID
	 */
	@ApiModelProperty(value = "支付渠道ID")
	private Integer paymentChannelId;
	/**
	 * 支付渠道名称
	 */
	@ApiModelProperty(value = "支付渠道名称")
	private String paymentChannelName;
	/**
	 * 付款手续费
	 */
	@ApiModelProperty(value = "付款手续费")
	private BigDecimal paymentTotalFee;
	/**
	 * 分摊方式ID
	 */
	@ApiModelProperty(value = "分摊方式ID")
	private Integer apportionMethodId;
	/**
	 * 分摊方式名称
	 *
 	 */
	@ApiModelProperty(value = "分摊方式名称")
	private String apportionMethodName;
	/**
	 * 付款总金额
	 */
	@ApiModelProperty(value = "付款总金额")
	private BigDecimal paymentTotalAmount;
	/**
	 * 实付总金额
	 */
	@ApiModelProperty(value = "实付总金额")
	private BigDecimal actualTotalAmount;
	/**
	 * 付款账户ID
	 */
	@ApiModelProperty(value = "付款账户ID")
	private Long paymentAccountId;
	/**
	 * 付款账户名称
	 */
	@ApiModelProperty(value = "付款账户名称")
	private String paymentAccountName;
	/**
	 * 收款账户ID
	 */
	@ApiModelProperty(value = "收款账户ID")
	private Integer receiptAccountId;
	/**
	 * 收款账户名称
	 */
	@ApiModelProperty(value = "收款账户名称")
	private String receiptAccountName;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	 * 操作人ID
	 */
	@ApiModelProperty(value = "操作人ID")
	private Integer oprId;
	/**
	 * 操作人姓名
	 */
	@ApiModelProperty(value = "操作人姓名")
	private String oprName;
}

