package com.hivescm.tms.api.dto.print.waybill;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class WaybillPrintDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private @ApiModelProperty("主键ID") Long id;

	@Mapping({"waybillCode", "code"}) @Logger
	private  @ApiModelProperty("运单号") String code ;

	private @Mapping  @ApiModelProperty("发货网点名称")String invoiceOrgName;

	private @Mapping  @ApiModelProperty("目的地名称")String destName;

	private @Mapping  @ApiModelProperty("运输线路名称	")String lineName;

	private @Mapping  @ApiModelProperty("到达网点名称	")String destOrgName;

	private @Mapping  @ApiModelProperty("发货方名称")String invoiceCompany;

	private @Mapping  @ApiModelProperty("发货人")String invoiceUser;

	private @Mapping  @ApiModelProperty("发货人电话")String invoiceTelNo;

	private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;

	private @Mapping  @ApiModelProperty("发货人微信号	")String invoiceWxNo;

	private @Mapping @ApiModelProperty("发货人身份证号") String invoiceIdentityCard;

	private @Mapping  @ApiModelProperty("发货人省名称")String invoiceProvName;

	private @Mapping  @ApiModelProperty("发货人城市名称")String invoiceCityName;

	private @Mapping  @ApiModelProperty("发货人地区名称")String invoiceDistrictName;

	private @Mapping  @ApiModelProperty("发货人街道名称")String invoiceStreetName;

	private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;

	private @Mapping  @ApiModelProperty("发货人完整地址")String invoiceFullAddress;

	private @Mapping @ApiModelProperty("回单要求名称") String backType;

	private @Mapping  @ApiModelProperty("回单份数") Integer backNum;

	private @Mapping  @ApiModelProperty("回单编号")String backCode;

	private @Mapping  @ApiModelProperty("收货方名称")String receiptCompany;

	private @Mapping  @ApiModelProperty("收货人身份证号码")String receiptIdentityCard;

	private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;

	private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;

	private @Mapping  @ApiModelProperty("收货人国家名称")String receiptCountryName;

	private @Mapping  @ApiModelProperty("收货人省名称")String receiptProvName;

	private @Mapping  @ApiModelProperty("收货人城市名称")String receiptCityName;

	private @Mapping  @ApiModelProperty("收货人地区名称")String receiptDistrictName;

	private @Mapping  @ApiModelProperty("收货人街道名称")String receiptStreetName;

	private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;

	private @Mapping  @ApiModelProperty("收货人完整地址")String receiptFullAddress;

	private @Mapping @ApiModelProperty("配送方式名称") String distributionTypeName;

	private @Mapping() @ApiModelProperty("是否开单直送") Boolean directSend;

	private @Mapping @ApiModelProperty("运输方式名称") String shippingTypeName;

	private @Mapping  @ApiModelProperty("创建时间") Long createTime;

	private @Mapping  @ApiModelProperty("业务日期") Long bussTime;

	private @Mapping({"DispatcherDetailPrintDTO.waybillRemark","TmsWaybillEsDTO.remark","DispatcherWaybillEsDTO.waybillRemark"}) @ApiModelProperty("备注")String remark;

	private @Mapping  @ApiModelProperty("是否等通知")Boolean iwaitNotice;

	private @Mapping({ "ibill", "waybillDO.isBill" })  @ApiModelProperty("是否开发票")Boolean ibill;

	private @Mapping  @ApiModelProperty("是否整车") Boolean itruckLoad;

	private @Mapping  @ApiModelProperty("是否加急")Boolean iemergency;

	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;

	@ApiModelProperty("提货费")
	private String pickupFee;

	@ApiModelProperty("基本运费")
	private String baseFreight;

	@ApiModelProperty("送货费")
	private String deliveryFee;

	@ApiModelProperty("上楼费")
	private String upstairsFee;

	@ApiModelProperty("包装费")
	private String packingCharges;

	@ApiModelProperty("声明价值")
	private String declaredValue;

	@ApiModelProperty("信息费")
	private String informationFee;

	@ApiModelProperty("加急费")
	private String emergencyFee;

	@ApiModelProperty("垫付运费")
	private String freightAdvanced;

	@ApiModelProperty("其他费用")
	private String otherFee;

	@ApiModelProperty("业务费")
	private String business;

	@ApiModelProperty("中转费")
	private String terminalcharges;

	@ApiModelProperty("装卸费")
	private String handingcharge;

	@ApiModelProperty("仓储费")
	private String warehousefee;

	@ApiModelProperty("税费")
	private String tax;

	private @ApiModelProperty("代收货款手续费") String deliveryCharge;

	private @ApiModelProperty("代收货款") String deliveryPayment;

	private @ApiModelProperty("保价费") String protectFee;

	private @ApiModelProperty("叉车费") String forkliftFee;

	private @ApiModelProperty("垫付货款") String freightPayment;

	private @ApiModelProperty("订单编号") @Logger String orderCode ;

	private @ApiModelProperty("创建人姓名")String createUserName;

	private @ApiModelProperty("特殊要求")String specialRequire;

	private @ApiModelProperty("收货人")String receiptUser;

	private @ApiModelProperty("现付")BigDecimal cashPay;

	private @ApiModelProperty("月结")BigDecimal monthlyPay;

	private @ApiModelProperty("回单付")BigDecimal receiptPay;

	private @ApiModelProperty("到付")BigDecimal cod;

	private @ApiModelProperty("免费")BigDecimal forfree;

	private @ApiModelProperty("欠付")BigDecimal tardypay;

	private @ApiModelProperty("货款扣(运费)")BigDecimal goodsPaymentDeduction;

	private @ApiModelProperty("业务员姓名")String salesmanName;

	private @ApiModelProperty("商品名称") String goodsName;

	private @ApiModelProperty("包装名称") String packingName;

	private @ApiModelProperty("总件数") Integer totalNum;

	private @ApiModelProperty("总体积") BigDecimal volume;

	private @ApiModelProperty("总重量") BigDecimal weight;

}
