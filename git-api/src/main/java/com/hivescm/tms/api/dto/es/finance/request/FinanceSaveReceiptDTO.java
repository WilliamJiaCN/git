package com.hivescm.tms.api.dto.es.finance.request;

import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceSaveReceiptDTO {

	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	
	@Mapping
	@ApiModelProperty("本次收款确认单数量")
	private Integer receiptCount;
	@Mapping
	@ApiModelProperty("收款总金额")
	private BigDecimal totalReceiptAmount;
	@Mapping
	@ApiModelProperty(value = "结算方式（来源于BOSS提供字典)")
	private Integer settlementMode;
	@Mapping
	@ApiModelProperty(value = "结算方式名称（来源于BOSS提供字典)")
	private String settlementModeName;
	@Mapping
	@ApiModelProperty(value = "支付渠道")
	private String payChannel;
	@Mapping
	@ApiModelProperty(value = "支付渠道名称")
	private String payChannelName;
	@Mapping
	@ApiModelProperty(value = "支付方式")
	private Integer payMode;
	@Mapping
	@ApiModelProperty(value = "支付方式名称")
	private String payModeName;
	@Mapping
	@ApiModelProperty(value = "收银时间", required = true)
	private Long cashierTime;
	@Mapping
	@ApiModelProperty(value = "收款账户", required = true)
	private String receiveAccount;
	@Mapping
	@ApiModelProperty(value = "付款账户")
	private String payAccount;
	@Mapping
	@ApiModelProperty(value = "备注")
	private String comment;
	@Mapping
	@ApiModelProperty("操作用户ID")
	private Integer userId;
	@Mapping
	@ApiModelProperty("集团ID")
	private Integer groupId;
	

	@Mapping
	@ApiModelProperty("操作用户名称")
	private String userName;
	@Required
	@ApiModelProperty(value = "当前网点ID", required = true)
	private Integer curentOrgId;
	
	@ApiModelProperty("应收列表")
	private List<FinanceManageReceiptEsDTO> list;
}
