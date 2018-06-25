package com.hivescm.tms.api.dto.es.finance.component;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 财务管理-付款记录
 * 
 * @author 杨彭伟
 * @date 2017-11-23 17:58
 */
@Data
@ToString
public class FinancePaymentESDTO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 付款记录id
	 */
	@Mapping
	private Long id;

	/**
	 * 发放状态 1.未发放 2.已发放
	 */
	@Mapping
	@ApiModelProperty("发放状态")
	private Integer grantStatus;

	/**
	 * 发放时间
	 */
	@Mapping
	@ApiModelProperty("发放时间")
	private Long grantTime;

    /**
     * 货款发放人
     */
    @Mapping
    @ApiModelProperty("货款发放人")
    private String grantUser;

    /**
     * 确认收银时间
     */
    @Mapping
    @ApiModelProperty("确认收银时间")
    private Long confirmReceiptTime;
	/**
	 * 付款单id
	 */
	@Mapping
    @ApiModelProperty("付款单id")
    private Long piId;

	/**
	 * 付款单编号
	 */
	@Mapping
    @ApiModelProperty("付款单编号")
    private String payCode;

	/**
	 * 付款单类型
	 */
	@Mapping
	@ApiModelProperty("付款单类型")
	private Integer payBillType;

	/**
	 * 付款单对应的收款记录id
	 */
	@Mapping
    @ApiModelProperty("付款单对应的收款记录id")
	private Long receiptId;

	/**
	 * 付款单对应的收款单id
	 */
	@Mapping
    @ApiModelProperty("付款单对应的收款单id")
	private Long riId;

	/**
	 * 付款单对应的收款单单号
	 */
	@Mapping
    @ApiModelProperty("付款单对应的收款单单号")
	private String receiptCode;

	/**
	 * 来源单据类型（订单类型）
	 */
	@Mapping
	@ApiModelProperty("来源单据类型（订单类型）")
	private String sourceBillType;

	/**
	 * 来源单号（运单号）
	 */
	@Mapping({"sourceCode","code"})
    @ApiModelProperty("来源单号（运单号）")
	private String sourceCode;   //实际上目前代码中的赋值是SignEsDTO中的签收批次号：SignBatchNumber

	/**
	 * 单据状态
	 */
	@Mapping
	@ApiModelProperty(" 单据状态")
	private Integer billStatus;

	/**
	 * 单据日期
	 */
	@Mapping
	@ApiModelProperty(" 单据日期")
	private Long billTime;

	/**
	 * 付款组织
	 */
	@Mapping
	@ApiModelProperty("付款组织")
	private String payOriginazation;
	
	/**
	 * 付款组织
	 */
	@Mapping
	@ApiModelProperty("付款组织id")
	private Integer payOriginazationId;

	/**
	 * 往来对象类型
	 */
	@Mapping
	@ApiModelProperty("往来对象类型")
	private Integer currentObjectType;

	/**
	 * 收款方
	 */
	@Mapping
	@ApiModelProperty("收款方")
	private Integer payeeObjectId;
	
	/**
	 * 收款方
	 */
	@Mapping
	@ApiModelProperty("收款方名称")
	private String payeeObjectName;

	/**
	 * 币种
	 */
	@Mapping
	@ApiModelProperty("币种")
	private Integer currency;

	/**
	 * 付款金额(实收代收货款汇总)
	 */
	@Mapping
	@ApiModelProperty("付款金额(实收代收货款汇总)")
	private BigDecimal payMoney;

	/**
	 * 付款手续费(分摊付款手续费)
	 */
	@Mapping
	@ApiModelProperty("付款手续费(分摊付款手续费)")
	private BigDecimal serviceCharge;

	/**
	 * 实付金额(实收代收货款汇总+分摊付款手续费)
	 */
	@Mapping
	@ApiModelProperty("实付金额(实收代收货款汇总+分摊付款手续费)")
	private BigDecimal amountPaid;

	/**
	 * 付款类型
	 */
	@Mapping
	@ApiModelProperty("付款类型")
	private Integer payType;

	/**
	 * 付款性质
	 */
	@Mapping
	@ApiModelProperty("付款性质")
	private String payCharacter;

	/**
	 * 支付方式
	 */
	@Mapping
	@ApiModelProperty("支付方式")
	private String paymentMode;

	/**
	 * 结算方式
	 */
	@Mapping
	@ApiModelProperty(" 结算方式")
	private Integer settlementMode;

	/**
	 * 结算方式
	 */
	@Mapping
	@ApiModelProperty(" 结算方式")
	private String settlementModeName;

	/**
	 * 支付渠道
	 */
	@Mapping
	@ApiModelProperty("支付渠道")
	private String payChannel;

	/**
	 * 付款账户
	 */
	@Mapping
	@ApiModelProperty("付款账户")
	private String payBankAccount;

	/**
	 * 收款账户
	 */
	@Mapping
	@ApiModelProperty(" 收款账户")
	private String receivableBankAccount;

	/**
	 * 支付状态 (1.支付成功)
	 */
	@Mapping
	@ApiModelProperty("支付状态")
	private Integer payStatus;

	/**
	 * 付款对账状态(对账成功)
	 */
	@Mapping
	@ApiModelProperty("付款对账状态(对账成功)")
	private Integer checkingStatus;

	/**
	 * 业务组织
	 */
	@Mapping
	@ApiModelProperty("业务组织")
	private String businessGroup;

	/**
	 * 应付组织
	 */
	@Mapping
	@ApiModelProperty("应付组织")
	private String payGroup;

	/**
	 * 业务方
	 */
	@Mapping
	@ApiModelProperty("业务方")
	private String orderSupplier;

	/**
	 * 应收方
	 */
	@Mapping
	@ApiModelProperty("应收方")
	private String paySupplier;

	/**
	 * 业务员
	 */
	@Mapping
	@ApiModelProperty(" 业务员")
	private String subordinateClert;

	/**
	 * 业务部门
	 */
	@Mapping
	@ApiModelProperty("业务部门")
	private String department;

	/**
	 * 源头单号（主订单编号）
	 */
	@Mapping
	@ApiModelProperty("源头单号（主订单编号）")
	private String sourceBillNum;

	/**
	 * 备注
	 */
	@Mapping
    @ApiModelProperty("备注")
	private String remark;

	/**
	 * 公司id
	 */
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;

	/**
	 * 创建人
	 */
	@Mapping
	private Integer createUser;

	/**
	 * 创建时间
	 */
	@Mapping
	private Long createTime;

	/**
	 * 修改人
	 */
	@Mapping
	private Integer updateUser;

	/**
	 * 修改时间
	 */
	@Mapping
	private Long updateTime;

	/*****************************拓展字段******************************************/

	/** 订单 **/
    @Mapping
    @ApiModelProperty("订单编号")
    private String orderCode;
    @Mapping
    @ApiModelProperty("主订单编号")
    private String mainOrderCode;
    @Mapping
    @ApiModelProperty("原订单号")
    private String originalOrderCode;
    @Mapping
	@ApiModelProperty("外部订单编号")
	private String externalOrderCode;
    /** 订单 **/


    /** 签收单 */
    @Mapping
    @ApiModelProperty("签收时间")
    private Long signTime;
    @Mapping
    @ApiModelProperty("签收状态")
    private Integer signStatus;
	@Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal collectPayment;
    @Mapping
    @ApiModelProperty("实收代收货款")
    private BigDecimal totalReceivable;
    /** 签收单 */


	/** 运单 **/
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;
    @Mapping
    @ApiModelProperty("运单号")
    private String code;
    @Mapping
    @ApiModelProperty("发货网点ID	")
    private Integer invoiceOrgId;
    @Mapping
    @ApiModelProperty("发货网点名称	")
    private String invoiceOrgName;
	@Mapping
	@ApiModelProperty("结算对象")
	private Integer settlementObject;
	@Mapping
	@ApiModelProperty("结算对象名称")
	private String settlementObjectName;
	@Mapping
	@ApiModelProperty("结算对象集团客户Id")
	private Integer settlementObjectGroupId;
	@Mapping
	@ApiModelProperty("结算对象集团客户名称")
	private String settlementObjectGroupName;
	@Mapping
	@ApiModelProperty("仓储服务商ID")
	private Long warehouseServerId;
	@Mapping
	@ApiModelProperty("仓储服务商名称")
	private String warehouseServerName;
    @Mapping
    @ApiModelProperty("仓储服务商全局客户ID")
    private Long warehouseServerGlobalId;
    @Mapping
    @ApiModelProperty("仓储服务商全局客户名称")
    private String warehouseServerGlobalName;
    @Mapping
    @ApiModelProperty("仓储服务商集团客户ID")
    private Long warehouseServerGroupId;
    @Mapping
    @ApiModelProperty("仓储服务商集团客户名称")
    private String warehouseServerGroupName;
	@Mapping
	@ApiModelProperty("经销商ID")
	private Long dealerId;
	@Mapping
	@ApiModelProperty("经销商名称")
	private String dealerName;
    @Mapping
    @ApiModelProperty("经销商全局客户ID")
    private Long dealerGlobalId;
    @Mapping
    @ApiModelProperty("经销商全局客户名称")
    private String dealerGlobalName;
    @Mapping
    @ApiModelProperty("经销商集团客户ID")
    private Long dealerGroupId;
    @Mapping
    @ApiModelProperty("经销商集团客户名称")
    private String dealerGroupName;
	@Mapping
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@Mapping
	@ApiModelProperty("商户ID")
	private Long merchantId;
	@Mapping
	@ApiModelProperty("商户名称")
	private String merchantName;
    @Mapping
    @ApiModelProperty("收货方ID")
    private Integer receiptCustomerId;
    @Mapping
    @ApiModelProperty("收货人")
    private String receiptUser;
    @Mapping
    @ApiModelProperty("收货方名称")
    private String receiptCompany;
    @Mapping
    @ApiModelProperty("收货人电话")
    private String receiptConsignorTelNo;
    @Mapping
    @ApiModelProperty("收货人手机号码")
    private String receiptConsignorMobleNo;
    @Mapping
    @ApiModelProperty("收货人详细地址")
    private String receiptAddress;
	@Mapping
	@ApiModelProperty("录单时间")
	private Long waybillCreateTime;
    /** 运单 **/
}