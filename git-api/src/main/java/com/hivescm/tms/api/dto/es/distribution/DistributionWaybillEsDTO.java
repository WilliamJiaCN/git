package com.hivescm.tms.api.dto.es.distribution;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class DistributionWaybillEsDTO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@ApiModelProperty("运单ID")
		private @Mapping Long waybillId;
		@ApiModelProperty("公司ID")
	    private @Mapping Long companyId;
		
		@ApiModelProperty("公司名称")
	    private @Mapping String companyName;
		
		@ApiModelProperty("运单号")
	    private @Mapping String waybillCode;
		
		
		@ApiModelProperty("客户单号")
	    private @Mapping String customerOrderCode;
		
		@ApiModelProperty("原运单号")
	    private @Mapping String originalOrderCode;
		
		@ApiModelProperty("发货网点ID")
	    private @Mapping Long invoiceOrgId;
		
		@ApiModelProperty("发货网点名称")
	    private @Mapping String invoiceOrgName;
		
		@ApiModelProperty("目的地级别")
	    private @Mapping Integer destLevel;
		
		@ApiModelProperty("目的地ID")
	    private @Mapping Long destId;
		
		@ApiModelProperty("目的地名称")
	    private @Mapping String destName;
		
		@ApiModelProperty("运输线路ID")
	    private @Mapping Long lineId;
		
		@ApiModelProperty("运输线路名称")
	    private @Mapping String lineName;
		
		@ApiModelProperty("到达网点ID")
	    private @Mapping Long destOrgId;
		
		@ApiModelProperty("到达网点名称")
	    private @Mapping String destOrgName;
		
		@ApiModelProperty("业务日期")
	    private @Mapping Long bussTime;
		
		@ApiModelProperty("回单要求")
	    private @Mapping String backType;
		
		@ApiModelProperty("回单份数")
	    private @Mapping Integer backNum;
		
		@ApiModelProperty("回单编号")
	    private @Mapping String backCode;
		
		@ApiModelProperty("发货方式")
	    private @Mapping Integer deliveryType;
		
		
		@ApiModelProperty("特殊要求")
	    private @Mapping String specialRequire;
		
		@ApiModelProperty("是否开发票")
	    private @Mapping boolean isBill;
		
		@ApiModelProperty("是否等通知")
	    private @Mapping boolean isWaitNotice;
		
		@ApiModelProperty("是否整车")
	    private @Mapping boolean isTruckLoad;
		
		@ApiModelProperty("是否加急")
	    private @Mapping boolean isEmergency;
		
		@ApiModelProperty("运输方式")
	    private @Mapping Integer shippingType;
		
		@ApiModelProperty("预计到达日期")
	    private @Mapping Date arrivalTime;
		
		@ApiModelProperty("入库员ID")
	    private @Mapping Integer storageUserId;
		
		@ApiModelProperty("入库员姓名")
	    private @Mapping String storageUserName;
		
		@ApiModelProperty("业务员ID")
	    private @Mapping Integer salesmanId;
		
		@ApiModelProperty("业务员姓名")
	    private @Mapping String salesmanName;
		
		@ApiModelProperty("备注")
	    private @Mapping String remark;
		
		@ApiModelProperty("总运费")
	    private @Mapping String totalFee;
		
		@ApiModelProperty("付款方式")
	    private @Mapping Integer payWay;
		
		@ApiModelProperty("配送方式")
	    private @Mapping Integer distributionType;
		
		@ApiModelProperty("现付")
	    private @Mapping String cashPay;
		
		@ApiModelProperty("月结")
	    private @Mapping String monthlyPay;
		
		@ApiModelProperty("回单付")
	    private @Mapping String receiptPay;
		
		@ApiModelProperty("到付")
	    private @Mapping String cod;
		
		@ApiModelProperty("货款扣(运费)")
	    private @Mapping String goodsPaymentDeduction;
		
		@ApiModelProperty("运单状态")
	    private @Mapping Integer status;
		
		@ApiModelProperty("是否审核")
	    private @Mapping Boolean isChecked;
		
		@ApiModelProperty("是否打印")
	    private @Mapping Boolean isPrint;
		
		@ApiModelProperty("录单员ID")
	    private @Mapping Integer createUserId;
		
		@ApiModelProperty("录单员姓名")
	    private @Mapping String createUserName;
		
		@ApiModelProperty("审核员ID")
	    private @Mapping Integer checkUserId;
		
		@ApiModelProperty("审核员姓名")
	    private @Mapping String checkUserName;
		
		@ApiModelProperty("审核时间")
	    private @Mapping Long checkTime;
		
		
		
		
		@ApiModelProperty("取消审核员ID")
	    private @Mapping Integer cancelCheckUserId;
		
		@ApiModelProperty("取消审核员姓名")
	    private @Mapping String cancelCheckUserName;
		
		@ApiModelProperty("取消审核时间")
	    private @Mapping Long cancelCheckTime;
		
		@ApiModelProperty("作废类型")
	    private @Mapping Integer discardType;
		
		@ApiModelProperty("签收图片")
	    private @Mapping String signPic;
		
		@ApiModelProperty("签收时间")
	    private @Mapping Long signTime;
		
		@ApiModelProperty("作废原因")
	    private @Mapping String discardReason;
		
		@ApiModelProperty("发货方Id")
	    private @Mapping Integer invoiceCustomerId;
		
		@ApiModelProperty("发货人")
	    private @Mapping String invoiceUser;
		
		@ApiModelProperty("发货单位")
	    private @Mapping String invoiceCompany;
		
		@ApiModelProperty("发货人微信号")
	    private @Mapping String invoiceWxNo;
		
		@ApiModelProperty("发货人电话")
	    private @Mapping String invoiceTelNo;
		
		@ApiModelProperty("发货人手机号码")
	    private @Mapping String invoiceMobleNo;
		
		@ApiModelProperty("发货人国家ID")
	    private @Mapping String invoiceCountryId;
		
		@ApiModelProperty("发货人国家名称")
	    private @Mapping String invoiceCountryName;
		
		
		@ApiModelProperty("发货人省ID")
	    private @Mapping String invoiceProvId;
		
		@ApiModelProperty("发货人省名称")
	    private @Mapping String invoiceProvName;
		
		@ApiModelProperty("发货人城市ID")
	    private @Mapping String invoiceCityId;
		
		@ApiModelProperty("发货人城市名称")
	    private @Mapping String invoiceCityName;
		
		@ApiModelProperty("发货人地区ID")
	    private @Mapping String invoiceDistrictId;
		
		@ApiModelProperty("发货人地区名称")
	    private @Mapping String invoiceDistrictName;
		
		@ApiModelProperty("发货人街道ID")
	    private @Mapping String invoiceStreetId;
		
		@ApiModelProperty("发货人街道名称")
	    private @Mapping String invoiceStreetName;
		
		
		@ApiModelProperty("发货人详细地址")
	    private @Mapping String invoiceAddress;
		
		@ApiModelProperty("收货方ID")
	    private @Mapping Integer receiptCustomerId;
		
		@ApiModelProperty("收货人")
	    private @Mapping String receiptUser;
		
		@ApiModelProperty("收货单位")
	    private @Mapping String receiptCompany;
		
		@ApiModelProperty("收货人身份证号码")
	    private @Mapping String receiptIdentityCard;
		
		@ApiModelProperty("收货人电话")
	    private @Mapping String receiptConsignorTelNo;
		
		@ApiModelProperty("收货人手机号码")
	    private @Mapping String receiptConsignorMobleNo;
		
		
		@ApiModelProperty("收货人国家ID")
	    private @Mapping String receiptCountryId;
		
		@ApiModelProperty("收货人国家名称")
	    private @Mapping String receiptCountryName;
		
		@ApiModelProperty("收货人省ID")
	    private @Mapping String receiptProvId;
		
		@ApiModelProperty("收货人省名称")
	    private @Mapping String receiptProvName;
		
		@ApiModelProperty("收货人城市ID")
	    private @Mapping String receiptCityId;
		
		@ApiModelProperty("收货人城市名称")
	    private @Mapping String receiptCityName;
		
		@ApiModelProperty("收货人地区ID")
	    private @Mapping String receiptDistrictId;
		
		@ApiModelProperty("收货人地区名称")
	    private @Mapping String receiptDistrictName;
		
		
		@ApiModelProperty("收货人街道ID")
	    private @Mapping String receiptStreetId;
		
		@ApiModelProperty("收货人街道名称")
	    private @Mapping String receiptStreetName;
		
		@ApiModelProperty("收货人详细地址")
	    private @Mapping String receiptAdress;
		
		@ApiModelProperty("代收款退货款方式")
	    private @Mapping Integer deliveryRefundWay;
		
		@ApiModelProperty("代收款账户名称")
	    private @Mapping String deliveryAccountName;
		
		@ApiModelProperty("代收款开户银行")
	    private @Mapping String deliveryBankName;
		
		
		@ApiModelProperty("代收款银行账号")
	    private @Mapping String deliveryBankCard;
		
		@ApiModelProperty("代收款联系人")
	    private @Mapping String deliveryContacts;
		
		@ApiModelProperty("代收款手机号码")
	    private @Mapping String deliveryMobile;
		
		@ApiModelProperty("代收款联系电话")
	    private @Mapping String deliveryPhone;
		
		@ApiModelProperty("代收款联系人身份证号码")
	    private @Mapping String deliveryIdentityCard;
		
		@ApiModelProperty("代收款备注")
	    private @Mapping String deliveryRemark;
		
		@ApiModelProperty("回单库存网点id")
	    private @Mapping Integer backOrderOrgId;
		
		
		@ApiModelProperty("回单收货方姓名")
	    private @Mapping String backOrderReceiptName;
		
		
		@ApiModelProperty("回单发货方ID")
	    private @Mapping Integer backOrderInvoiceId;
		
		
		@ApiModelProperty("回单收货方ID")
	    private @Mapping Integer backOrderReceiptId;
		
		
		
		@ApiModelProperty("回单发货方姓名")
	    private @Mapping String backOrderInvoiceName;
		
		@ApiModelProperty("回单库存网点名称")
	    private @Mapping String backOrderOrgName;
		
		
		@ApiModelProperty("回单数量")
	    private @Mapping Integer backOrderNum;
		
		 @ApiModelProperty("创建人")
	    private @Mapping Integer createUser;
	    
	    @ApiModelProperty("创建人姓名")
	    private @Mapping  String createUserName2;
	    
	    @ApiModelProperty("创建时间")
	    private @Mapping Long createTime;
	    
	    @ApiModelProperty("修改人")
	    private @Mapping Integer updateUser;
	    
	    @ApiModelProperty("修改人姓名")
	    private @Mapping  String updateUserName;
	    
	    @ApiModelProperty("修改时间")
	    private @Mapping Long updateTime;

		

		
		
	    
	    
}
