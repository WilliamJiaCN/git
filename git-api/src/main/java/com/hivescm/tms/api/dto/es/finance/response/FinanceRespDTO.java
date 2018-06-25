package com.hivescm.tms.api.dto.es.finance.response;

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
 * Description: 返回结果
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

public class FinanceRespDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 来源单号(签收单号)
	 */
	@Mapping
	@ApiModelProperty("来源单号(签收单号)")
	private String sourceNumber;

	/**
	 * 收银状态
	 */
	@Mapping
	@ApiModelProperty("收银状态")
	private Integer collectStatus;
	/**
	 * 收银确认时间
	 */
	@Mapping
	@ApiModelProperty("收银确认时间")
	private Long collectTime;
	/**
	 * 贷款收银人
	 */
	@Mapping
	@ApiModelProperty(" 贷款收银人")
	private String loansPerson;

	/**
	 * 签收状态
	 */
	@Mapping
	@ApiModelProperty("签收类型")
	private Integer signType;
	/**
	 * 运单号
	 */
	@Mapping
	@ApiModelProperty("运单号")
	private Long waybillId;

	/**
	 * 订单编号
	 */
	@Mapping
	@ApiModelProperty("订单编号")
	private Long orderId;

	/**
	 * 源头单号
	 */
	@Mapping
	@ApiModelProperty("源头单号")
	private String originalBillNumber;

	/**
	 * 主订单编号
	 */
	@Mapping
	@ApiModelProperty("主订单编号")
	private Long mainorderId;

	/**
	 * 收款单号
	 */
	@Mapping
	@ApiModelProperty(value = "收款单主键")
	private Long riId;
	/**
	 * 收款单状态(已审批 2)
	 */
	@Mapping
	@ApiModelProperty("状态(已审批 2)")
	private Integer status;

	/**
	 * 收款时间
	 */
	@Mapping
	@ApiModelProperty("收款时间")
	private Long collectionTime;

	/**
	 * 付款方
	 */
	@Mapping
	@ApiModelProperty("付款方")
	private String payObject;

	/**
	 * 收款组织
	 */
	@Mapping
	@ApiModelProperty("收款组织")
	private String receivablesOrganization;

	/**
	 * 收款金额(实收代收货款)
	 */
	@Mapping
	@ApiModelProperty("收款金额(实收代收货款)")
	private BigDecimal receivableMoney;
	@Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal collectPayment;

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
	 * 支付渠道
	 */
	@Mapping
	@ApiModelProperty("支付渠道(支付宝、微信)")
	private Integer settlementMethod;

	/**
	 * 收款账户
	 */
	@Mapping
	@ApiModelProperty("收款账户")
	private String receivablesBankAccount;

	/**
	 * 付款账户
	 */
	@Mapping
	@ApiModelProperty(" 付款账户")
	private String payBankAccount;
	/**
	 * 币种
	 */
	@Mapping
	@ApiModelProperty("币种")
	private String currency;
	/**
	 * 发货网点名称
	 */
	@Mapping
	private @ApiModelProperty("发货网点名称	") String invoiceOrgName;
	// 王小雪
	/**
	 * 司机姓名
	 */
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	/**
	 * 手机号码
	 */
	// 王小雪
	@Mapping
	@ApiModelProperty("司机手机号码")
	private String phoneNo;
	// 王小雪
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;
	@Mapping
	@ApiModelProperty("经销商名称")
	private String dealerName;// 冗余
	@Mapping
	@ApiModelProperty("商户名称")
	private String merchantName;// 冗余
	@Mapping
	@ApiModelProperty("商户ID")
	private Long merchantId;

	@Mapping
	@ApiModelProperty("仓储服务商ID")
	private Long warehouseServerId;

	@Mapping
	@ApiModelProperty("仓储服务商名称")
	private String warehouseServerName;// 冗余

	@Mapping
	@ApiModelProperty("外部订单编号")
	private Long externalOrderCode;
	@Mapping
	@ApiModelProperty("仓库ID")
	private Long warehouseId;

	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;// 冗余
	/**
	 * 车牌号码
	 */
	@Mapping
	@ApiModelProperty("车牌号码")
	private String vehicleNo;

	/**
	 * 收货方ID
	 */
	@Mapping({ "receiptCustomerId", "waybillReceiptDO.customerId" })
	private @ApiModelProperty("收货方ID") Integer receiptCustomerId;
	/**
	 * 收货人
	 */
	@Mapping
	private @ApiModelProperty("收货人") String receiptUser;
	/**
	 * 收货单位
	 */
	@Mapping
	private @ApiModelProperty("收货单位") String receiptCompany;

	/**
	 * 收货人电话
	 */
	@Mapping({ "receiptConsignorTelNo", "waybillReceiptDO.consignorTelNo" })
	private @ApiModelProperty("收货人电话") String receiptConsignorTelNo;
	/**
	 * 收货人手机号码
	 */
	@Mapping({ "receiptConsignorMobleNo", "waybillReceiptDO.consignorMobleNo" })
	private @ApiModelProperty("收货人手机号码") String receiptConsignorMobleNo;
	/**
	 * 收货人详细地址
	 */
	@Mapping({ "receiptAddress", "waybillReceiptDO.address" })
	private @ApiModelProperty("收货人详细地址") String receiptAddress;
	

	/**
	 * 单据日期(签收时间)
	 */
	@Mapping
	@ApiModelProperty("单据日期(签收时间)")
	private Long billDate;

	
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

}
