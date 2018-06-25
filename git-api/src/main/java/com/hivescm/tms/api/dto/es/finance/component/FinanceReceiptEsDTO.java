package com.hivescm.tms.api.dto.es.finance.component;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * <p>
 * Title:FinanceReceiptEsDTO
 * </p>
 * <p>
 * Description: 收款单es
 * </p>
 * <p>
 * Company: 蜂网供应链（上海）有限公司
 * </p>
 * 
 * @author 王小雪
 * @date 下午1:51:08
 */
@Data
@ToString
public class FinanceReceiptEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 收款记录id
	 */
	@Mapping
	@ApiModelProperty("收款记录id")
	private Long id;

	/**
	 * 收款单id boss
	 */
	@Mapping
	@ApiModelProperty("收款单id boss")
	private Long riId;

	/**
	 * 收款单单编号 boss
	 */
	@Mapping
	@ApiModelProperty("收款单单编号 boss")
	private String receiptCode;

	/**
	 * 是否确认收银 1.未确认 2.已确认
	 */
	@Mapping
	@ApiModelProperty("是否确认收银 1.未确认 2.已确认")
	private Integer confirmReceipt;

	/**
	 * 确认收银时间
	 */
	@Mapping
	@ApiModelProperty("确认收银时间")
	private Long confirmReceiptTime;

	/**
	 * 确认收银人
	 */
	@Mapping
	@ApiModelProperty("确认收银人")
	private String confirmReceiptUser;

	/**
	 * 收款单类型
	 */
	@Mapping
	@ApiModelProperty("收款单类型")
	private Integer receiptType;

	/**
	 * 来源单据类型(签收单)
	 */
	@Mapping
	@ApiModelProperty("来源单据类型(签收单)")
	private String sourceBillType;

	/**
	 * 来源单号(签收单号)
	 */
	@Mapping
	@ApiModelProperty("来源单号(签收单号)")
	private String sourceNumber;     //实际上目前代码中的赋值是SignEsDTO中的签收批次号：SignBatchNumber

	/**
	 * 状态(已审批 2)
	 */
	@Mapping
	@ApiModelProperty("状态(已审批 2)")
	private Integer status;

	/**
	 * 单据状态(推式生单 4)
	 */
	@Mapping
	@ApiModelProperty("单据状态(推式生单 4)")
	private Integer billState;

	/**
	 * 单据日期(签收时间)
	 */
	@Mapping
	@ApiModelProperty("单据日期(签收时间)")
	private Long billDate;

	/**
	 * 收款组织(承运商财务)
	 */
	@Mapping
	@ApiModelProperty("收款组织(承运商财务)")
	private String receivablesOrganization;
	/**
	 * 收款组织(承运商财务)
	 */
	@Mapping
	@ApiModelProperty("收款组织(承运商财务)")
	private String receivablesOrganizationDisplay;

    @Mapping
    @ApiModelProperty("收款组织(承运商财务)")
    private String receivableCustomer;
	/**
	 * 往来对象类型
	 */
	@Mapping
	@ApiModelProperty("往来对象类型")
	private Integer currentObjectType;

	/**
	 * 业务方(商户在承运商集团中的客户ID)
	 */
	@Mapping
	@ApiModelProperty("业务方(商户在承运商集团中的客户ID)")
	private String businessObject;

	/**
	 * 付款方(商户在承运商集团中的客户ID)
	 */
	@Mapping
	@ApiModelProperty("付款方(商户在承运商集团中的客户ID)")
	private String payObject;

	/**
	 * 应付方(商户在承运商集团中的客户ID)
	 */
	@Mapping
	@ApiModelProperty("应付方(商户在承运商集团中的客户ID)")
	private String payableObject;

	/**
	 * 币种 (人民币 1)
	 */
	@Mapping
	@ApiModelProperty("币种")
	private Integer currency;

	/**
	 * 收款金额(实收代收货款)
	 */
	@Mapping
	@ApiModelProperty("收款金额(实收代收货款)")
	private BigDecimal receivableMoney;

	/**
	 * 收款手续费
	 */
	@Mapping
	@ApiModelProperty("收款手续费")
	private BigDecimal brokerage;

	/**
	 * 到账金额(实收代收货款+收款手续费)
	 */
	@Mapping
	@ApiModelProperty("到账金额(实收代收货款+收款手续费)")
	private BigDecimal arrivalAmountMoney;

	/**
	 * 收款类型
	 */
	@Mapping
	@ApiModelProperty("收款类型")
	private String receivablesType;

	/**
	 * 收款性质
	 */
	@Mapping
	@ApiModelProperty("收款性质")
	private String receivablesProperty;

	/**
	 * 支付方式
	 */
	@Mapping
	@ApiModelProperty("支付方式")
	private String paymentMode;

	/**
	 * 结算方式(现金、扫码、转账)
	 */
	@Mapping
	@ApiModelProperty("结算方式(现金、扫码、转账)")
	private Integer settlementMode;

	/**
	 * 收款账户
	 */
	@Mapping
	@ApiModelProperty("收款账户")
	private String receivablesBankAccount;
	/**
	 * 收款账户 new
	 */
	@Mapping
	@ApiModelProperty("收款账户")
	private Integer receiveAccount;

	/**
	 * 收款账户 new
	 */
	@Mapping
	@ApiModelProperty("收款账户")
	private String receiveAccountDisplay;

	/**
	 * 付款账户
	 */
	@Mapping
	@ApiModelProperty(" 付款账户")
	private String payBankAccount;
    /**
     * 付款账户 new
     */
	@Mapping
    @ApiModelProperty(value = "付款账户")
    private Integer payAccount;
    /**
     * 付款账户 new
     */
	@Mapping
    @ApiModelProperty(value = "付款账户显示")
    private String payAccountDisplay;

	/**
	 * 支付状态(支付成功 1)
	 */
	@Mapping
	@ApiModelProperty("支付状态(支付成功 1)")
	private Integer payStatus;

	/**
	 * 业务组织(承运商网点)
	 */
	@Mapping
	@ApiModelProperty("业务组织")
	private String businessOrganization;

	@Mapping
	@ApiModelProperty("业务组织")
	private String businessOrganizationDisplay;

	@Mapping
	@ApiModelProperty("业务方")
	private Integer businessCustomer;

    @Mapping
    @ApiModelProperty("业务方")
    private String businessCustomerDisplay;

	/**
	 * 应收组织(承运商财务)
	 */
	@Mapping
	@ApiModelProperty("应收组织")
	private Integer receivableOrganization;

	@Mapping
	@ApiModelProperty("应收组织")
	private String receivableOrganizationDisplay;

	/**
	 * 业务员
	 */
	@Mapping
	@ApiModelProperty("业务员")
	private String salesman;

	/**
	 * 业务部门
	 */
	@Mapping
	@ApiModelProperty("业务部门")
	private String department;

	/**
	 * 源头单号
	 */
	@Mapping
	@ApiModelProperty("源头单号")
	private String originalBillNumber;

	/**
	 * 源头单类型
	 */
	@Mapping
	@ApiModelProperty("源头单类型")
	private String originalBillType;

	/**
	 * 备注
	 */
	@Mapping
	@ApiModelProperty("备注")
	private String comment;

	/**
	 * 公司ID
	 */
	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;

	/**
	 * 创建人
	 */
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;

	/**
	 * 创建时间
	 */
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;

	/**
	 * 修改人
	 */
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;

	/**
	 * 修改时间
	 */
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;
	
	@Mapping
	@ApiModelProperty("收款时间")
	private Long collectionTime;

	/***************************** 拓展字段 ******************************************/

	/************ 签收单 ************/
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
	@ApiModelProperty("应收合计(实代收货款)")
	private BigDecimal totalReceivable;
	/************ 签收单 ************/

	/************ 运单 WaybillId ************/
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
	@ApiModelProperty("发货网点名称")
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
    @ApiModelProperty("车牌号码")
    private String vehicleNo;
    /************ 订单 ************/
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
    /************ 订单 ************/

	/************ 运单 ************/

	/************ 派车单 ************/
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	@Mapping
	@ApiModelProperty("司机手机号码")
	private String phoneNo;
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;
	/************ 派车单 ************/

	@Mapping
	@ApiModelProperty("录单时间")
	private Long waybillCreateTime;
    /************ 运单 ************/


}
