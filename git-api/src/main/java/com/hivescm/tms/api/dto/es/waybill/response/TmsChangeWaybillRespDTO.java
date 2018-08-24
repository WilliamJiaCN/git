package com.hivescm.tms.api.dto.es.waybill.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 运单请求体
 * 
 * @author ke.huang
 * @date 2017年7月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class TmsChangeWaybillRespDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -8249634419618515835L;
	
	@ApiModelProperty("更改申请表的id")
    private Long id;
	
    @Mapping({"waybillId", "id"})
    @ApiModelProperty("运单ID")
    private Long waybillId;

	@Mapping({"waybillCode", "code"}) @Logger
	private  @ApiModelProperty("运单号") String code ;
	
	private @Mapping  @ApiModelProperty("目的地名称")String destName;
	
	private @Mapping  @ApiModelProperty("运输线路id")Long lineId;

	private @Mapping  @ApiModelProperty("运输线路名称	")String lineName;
	
	private @Mapping  @ApiModelProperty("到达网点id")Integer destOrgId;

	private @Mapping  @ApiModelProperty("到达网点名称	")String destOrgName;

	private @Mapping  @ApiModelProperty("发货方Id")Integer invoiceCustomerId;
	
	private @Mapping  @ApiModelProperty("发货会员Id")Integer invoiceCustomerVipId;
	
	private @Mapping  @ApiModelProperty("发货人")String invoiceUser;
	
	private @Mapping  @ApiModelProperty("发货人id")Long invoiceUserId;
	
	private @Mapping  @ApiModelProperty("发货方名称")String invoiceCompany;
	
	private @Mapping  @ApiModelProperty("发货人微信号	")String invoiceWxNo;

	@ApiModelProperty("发货人身份证号")
	@Mapping
	private String invoiceIdentityCard;
	
	private @Mapping  @ApiModelProperty("发货人电话")String invoiceTelNo;
	
	private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;
	
	private @Mapping  @ApiModelProperty("发货人国家ID")String invoiceCountryId;
	
	private @Mapping  @ApiModelProperty("发货人国家名称")String invoiceCountryName;
	
	private @Mapping  @ApiModelProperty("发货人省ID")String invoiceProvId;
	
	private @Mapping  @ApiModelProperty("发货人省名称")String invoiceProvName;
	
	private @Mapping  @ApiModelProperty("发货人城市ID")String invoiceCityId;
	
	@Mapping({ "invoiceAddressId", "waybillInvoiceDO.addressId" })
	private @ApiModelProperty("临时客户发货方地址ID") Integer invoiceAddressId;
	@Mapping
	private @ApiModelProperty("发货人是否散户") Boolean invoiceRetail;
	
	private @Mapping  @ApiModelProperty("发货人城市名称")String invoiceCityName;
	
	private @Mapping  @ApiModelProperty("发货人地区ID") String invoiceDistrictId;

	private @Mapping  @ApiModelProperty("发货人地区名称")String invoiceDistrictName;

	private @Mapping  @ApiModelProperty("发货人街道ID")String invoiceStreetId;
	
	private @Mapping  @ApiModelProperty("发货人街道名称")String invoiceStreetName;
	
	private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;
	
	private @Mapping  @ApiModelProperty("收货方ID")Integer receiptCustomerId;
	
	private @Mapping  @ApiModelProperty("收货会员Id")Integer receiptCustomerVipId;
	
	private @Mapping  @ApiModelProperty("收货人")String receiptUser;
	
	@Mapping
    @ApiModelProperty("收货人Id")
    private Long receiptUserId;         //收货人Id

	private @Mapping  @ApiModelProperty("收货方名称")String receiptCompany;

	private @Mapping  @ApiModelProperty("收货人身份证号码")String receiptIdentityCard;
	
	private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;
	
	private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;

	private @Mapping  @ApiModelProperty("收货人国家ID")String receiptCountryId;

	private @Mapping  @ApiModelProperty("收货人国家名称")String receiptCountryName;

	private @Mapping  @ApiModelProperty("收货人省ID")String receiptProvId;

	private @Mapping  @ApiModelProperty("收货人省名称")String receiptProvName;
	
	private @Mapping  @ApiModelProperty("收货人城市ID")String receiptCityId;
	
	private @Mapping  @ApiModelProperty("收货人城市名称")String receiptCityName;
	
	private @Mapping  @ApiModelProperty("收货人地区ID")String receiptDistrictId;
	
	private @Mapping  @ApiModelProperty("收货人地区名称")String receiptDistrictName;
	
	private @Mapping  @ApiModelProperty("收货人街道ID")String receiptStreetId;
	
	private @Mapping  @ApiModelProperty("收货人街道名称")String receiptStreetName;
	
	private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;
	
	@Mapping
	private @ApiModelProperty("回单要求名称") String backType;
	
	@Mapping
	private @ApiModelProperty("回单要求") Integer backTypeValue;

	private @Mapping  @ApiModelProperty("回单份数") Integer backNum;

	private @Mapping  @ApiModelProperty("回单编号")String backCode;
	
	@Mapping
	private @ApiModelProperty("发货方式") Integer deliveryType;
	
	@Mapping
	private @ApiModelProperty("发货方式名称") String deliveryTypeName;

	@Mapping
	private @ApiModelProperty("配送方式") Integer distributionType;

	@Mapping
	private @ApiModelProperty("配送方式名称") String distributionTypeName;

	@Mapping
	private @ApiModelProperty("运输方式") Integer shippingType;

	@Mapping
	private @ApiModelProperty("运输方式名称") String shippingTypeName;
	
	private @Mapping  @ApiModelProperty("业务日期") Long bussTime;
	
	private @Mapping  @ApiModelProperty("预计到达日期")Date arrivalTime;
	
	private @Mapping  @ApiModelProperty("入库员id")Integer storageUserId;

	private @Mapping  @ApiModelProperty("入库员姓名")String storageUserName;

	private @Mapping  @ApiModelProperty("业务员id")Integer salesmanId;

	private @Mapping  @ApiModelProperty("业务员姓名")String salesmanName;
	
	private @Mapping @ApiModelProperty("备注")String remark;
	
	private @Mapping  @ApiModelProperty("特殊要求")String specialRequire;
	
	private @Mapping  @ApiModelProperty("是否等通知")Boolean iwaitNotice;
	
	@Mapping()
	@ApiModelProperty("是否开票")
    private Boolean isBill;
	
	private @Mapping  @ApiModelProperty("是否整车") Boolean itruckLoad;
	
	private @Mapping  @ApiModelProperty("是否加急")Boolean iemergency;
	
	private @Mapping @ApiModelProperty("运费结算方式") Integer payWay;
	
	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;
	
	private @Mapping  @ApiModelProperty("现付")BigDecimal cashPay;

	private @Mapping  @ApiModelProperty("月结")BigDecimal monthlyPay;

	private @Mapping  @ApiModelProperty("回单付")BigDecimal receiptPay;

	private @Mapping  @ApiModelProperty("到付")BigDecimal cod;

	private @Mapping  @ApiModelProperty("免费")BigDecimal forfree;

	private @Mapping  @ApiModelProperty("欠付")BigDecimal tardypay;
	
	private @Mapping  @ApiModelProperty("货款扣(运费)")BigDecimal goodsPaymentDeduction;
	
    @ApiModelProperty("更改内容")
    private @Mapping String changeContent;
    
    @ApiModelProperty("更改备注")
    private @Mapping String changeRemark;
    
  	@Mapping
    @ApiModelProperty("税费")
    private BigDecimal tax;
  	
  	@ApiModelProperty("提货费")
	private BigDecimal pickupFee;

    @ApiModelProperty("基本运费")
	private BigDecimal baseFreight;

    @ApiModelProperty("送货费")
	private BigDecimal deliveryFee;

    @ApiModelProperty("上楼费")
	private BigDecimal upstairsFee;

    @ApiModelProperty("包装费")
	private BigDecimal packingCharges;

    @ApiModelProperty("声明价值")
	private BigDecimal declaredValue;

    @ApiModelProperty("信息费")
	private BigDecimal informationFee;

	@ApiModelProperty("加急费")
	private BigDecimal emergencyFee;

    @ApiModelProperty("垫付运费")
	private BigDecimal freightPayment;

    @ApiModelProperty("其他费用")
	private BigDecimal otherFee;

    @ApiModelProperty("业务费")
	private BigDecimal business;
    
    private @Mapping @ApiModelProperty("代收货款手续费") BigDecimal deliveryCharge;
    
	@Mapping
	@ApiModelProperty("代收货款")
    private BigDecimal orderGoodsPayment;
	
    private @Mapping @ApiModelProperty("保价费") BigDecimal protectFee;
    
    @ApiModelProperty("商品信息")
    private List<ChangeWaybillGoodsEsRespDTO> goodsList;
    
    @ApiModelProperty("变更新数据")
    private @Mapping String  newChangeData;
    
//    @ApiModelProperty("费用信息")
//    private List<ChangeWaybillFeeEsRespDTO> feeList;
}
