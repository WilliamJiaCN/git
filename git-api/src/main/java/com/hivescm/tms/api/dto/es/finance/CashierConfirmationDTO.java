package com.hivescm.tms.api.dto.es.finance;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 收银确认
 * @author songj
 * @Date: 2018-04-08
 */
@Data
@ToString
public class CashierConfirmationDTO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("本次收款确认运单数量")
	private Integer waybillCount;
	@Mapping
	@ApiModelProperty(value = "是否到付收款", required = true)
	private Boolean isToPayReceipt;
	@Mapping
	@ApiModelProperty("本次到付收款金额")
	private BigDecimal toPayReceiptAmount;
	@Mapping
	@ApiModelProperty(value = "是否代收货款收款", required = true)
	private Boolean isCollectionOnDeliveryReceipt;
	@Mapping
	@ApiModelProperty("本次代收货款收款金额")
	private BigDecimal collectionOnDeliveryReceiptAmount;
	@Mapping
	@ApiModelProperty("收款总金额")
	private BigDecimal totalReceiptAmount;
	@Mapping
	@ApiModelProperty(value = "支付方式", required = true)
	private String paymentMode;
	@Mapping
	@ApiModelProperty(value = "结算方式（来源于BOSS提供字典)")
	private Integer settlementMode;
	@Mapping
	@ApiModelProperty(value = "支付渠道")
	private String payChannel;
	@Mapping
	@ApiModelProperty(value = "收银时间", required = true)
	private Long cashierTime;
	@Mapping
	@ApiModelProperty(value = "收款账户", required = true)
	private Integer receiveAccount;
	@Mapping
	@ApiModelProperty(value = "付款账户")
	private Integer payAccount;
	@Mapping
	@ApiModelProperty(value = "备注")
	private String comment;
	@Mapping
	@ApiModelProperty(value = "签收单List(包含签收单Id，运单Id, 运单号, 公司id等)")
	private List<SignEsDTO> signEsDTOList;
	@Mapping
	@ApiModelProperty("操作用户ID")
	private Integer userId;
	@Required
	@ApiModelProperty(value = "当前网点ID", required = true)
	private Integer curentOrgId;

}
