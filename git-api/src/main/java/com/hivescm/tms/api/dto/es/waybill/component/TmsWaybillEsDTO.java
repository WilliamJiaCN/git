package com.hivescm.tms.api.dto.es.waybill.component;

import com.google.common.collect.Lists;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.dto.es.packageinfo.PackageDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * TMSWaybillEsDTO dto
 * 
 * @author zhenming.du
 * @date 2017年8月16日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class TmsWaybillEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 运单id
	 */
	@Mapping({ "id", "waybillId" })
	private @ApiModelProperty("运单id") @Logger Long id;
	/**
	 * 公司id
	 */
	@Mapping
	private @ApiModelProperty("公司id") Integer companyId;
	/**
	 * 公司名称
	 */
	@Mapping
	private @ApiModelProperty("公司名称") String companyName;
	
	@Mapping
	private @ApiModelProperty("集团id") Integer groupId;
	/**
	 * 货物明细
	 */
	private @ApiModelProperty("货物明细") List<WaybillGoodsEsDTO> waybillGoodsEsDtos;
	
	/**
	 * 运单库存明细
	 */
	private @ApiModelProperty("运单库存明细") List<WaybillStockDetailEsDTO> waybillStockDetailEsDTOs;
	/**
	 * 运单库存
	 */
	private @ApiModelProperty("运单库存") WaybillStockEsDTO waybillStockEsDto;
	/**
	 * 运单运费
	 */
	private @ApiModelProperty("运单运费") List<WaybillFeeEsDTO> waybillFeeEsDtos;
	/**
	 * 包裹信息
	 */
	private @ApiModelProperty("包裹信息") List<PackageDetailEsDTO> packageDetailEsDtos;
	
	/**
	 * 客户单号	
	 */
	private @Mapping  @ApiModelProperty("客户单号") String  customerCode;
	
	/**
	 * 运单号
	 */
	@Mapping({"waybillCode","code"})
	private @ApiModelProperty("运单号") @Logger String code;
	/**
	 * 订单编号	
	 */
	@Logger
	private @Mapping  @ApiModelProperty("订单编号") String orderCode ;
	/**
	 * 主订单编号	
	 */
	private @Mapping  @ApiModelProperty("主订单编号")String mainOrderCode ;
	/**
	 * 原订单号
	 */
	@Mapping
	private @ApiModelProperty("原订单号") String originalOrderCode;
	/**
	 * 原运单号
	 */
	@Mapping
	private @ApiModelProperty("原运单号") String originalWaybillCode;
	/**
	 * 发货网点ID
	 */
	@Mapping
	private @ApiModelProperty("发货网点ID	") Integer invoiceOrgId;
	/**
	 * 发货网点名称
	 */
	@Mapping
	private @ApiModelProperty("发货网点名称") String invoiceOrgName;
	/**
	 * 商品名称 ,“/”间隔
	 */
	@Mapping
	private @ApiModelProperty("商品名称") String goodsName;
	
	@Mapping
	@ApiModelProperty("货物类型名称")//冗余商品表类型，以"/"间隔
	private String goodsTypeName;
	/**
	 * 包装名称,“/”间隔
	 */
	private @Mapping @ApiModelProperty("包装名称") String packingName;
    /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型名称") String prodTypeName;
	/**
	 * 目的地级别
	 */
	@Mapping
	private @ApiModelProperty("目的地级别：国家为1，以此类推") Integer destLevel;
	/**
	 * 目的地id
	 */
	@Mapping
	private @ApiModelProperty("目的地id") String destId;
	/**
	 * 目的地名称
	 */
	@Mapping
	private @ApiModelProperty("目的地名称") String destName;
	/**
	 * 为前端回显目的地加的字段
	 */
	private @Mapping  @ApiModelProperty("目的地省id")Long destProvId;
	
	/**
	 * 为前端回显目的地加的字段
	 */
	private @Mapping  @ApiModelProperty("目的地省名称")String destProvName;
	/**
	 * 为前端回显目的地加的字段
	 */
	private @Mapping  @ApiModelProperty("目的地市id")Long destCityId;
	
	/**
	 * 为前端回显目的地加的字段
	 */
	private @Mapping  @ApiModelProperty("目的地市id")String destCityName;
	
	/**
	 * 为前端回显目的地加的字段
	 */
	private @Mapping  @ApiModelProperty("目的地区id")Long destDistrictId;
	
	/**
	 * 为前端回显目的地加的字段
	 */
	private @Mapping  @ApiModelProperty("目的地区id")String destDistrictName;
	/**
	 * 运输线路id
	 */
	@Mapping
	private @ApiModelProperty("运输线路id") Long lineId;
	/**
	 * 运输线路名称
	 */
	@Mapping
	private @ApiModelProperty("运输线路名称	") String lineName;
	/**
	 * 到达网点id
	 */
	@Mapping
	private @ApiModelProperty("到达网点id") Integer destOrgId;
	/**
	 * 到达网点名称
	 */
	@Mapping
	private @ApiModelProperty("到达网点名称") String destOrgName;
	/**
	 * 业务日期
	 */
	@Mapping
	private @ApiModelProperty("业务日期") Long bussTime;
	/**
	 * 下单日期
	 */
	@Mapping
	private @ApiModelProperty("下单日期") Long orderDate;
	/**
	 * 回单要求(名称)
	 */
	@Mapping
	private @ApiModelProperty("回单要求名称") String backType;
	
	@Mapping
	private @ApiModelProperty("回单要求") Integer backTypeValue;
	
	/**
	 * 回单份数
	 */
	@Mapping
	private @ApiModelProperty("回单份数") Integer backNum;
	/**
	 * 回单编号
	 */
	@Mapping
	private @ApiModelProperty("回单编号") String backCode;
	/**
	 * 发货方式
	 */
	@Mapping
	private @ApiModelProperty("发货方式") Integer deliveryType;
	
	/**
	 * 发货方式名称
	 */
	@Mapping
	private @ApiModelProperty("发货方式名称") String deliveryTypeName;
	/**
	 * 运输方式
	 */
	@Mapping
	private @ApiModelProperty("运输方式") Integer shippingType;
	
	/**
	 * 运输方式名称
	 */
	@Mapping
	private @ApiModelProperty("运输方式名称") String shippingTypeName;
	
	/**
	 * 配送方式
	 */
	@Mapping
	private @ApiModelProperty("配送方式") Integer distributionType;
	
	/**
	 * 配送方式名称
	 */
	@Mapping
	private @ApiModelProperty("配送方式名称") String distributionTypeName;
	/**
	 * 特殊要求
	 */
	@Mapping
	private @ApiModelProperty("特殊要求") String specialRequire;
	/**
	 * 是否开发票
	 */
	@Mapping({ "ibill", "waybillDO.isBill" })
	private @ApiModelProperty("是否开发票") Boolean ibill;
	/**
	 * 是否等通知
	 */
	@Mapping({ "iwaitNotice", "waybillDO.isWaitNotice" })
	private @ApiModelProperty("是否等通知") Boolean iwaitNotice;
	/**
	 * 是否整车
	 */
	@Mapping({ "itruckLoad", "waybillDO.isTruckLoad" })
	private @ApiModelProperty("是否整车") Boolean itruckLoad;
	/**
	 * 是否加急
	 */
	@Mapping({ "iemergency", "waybillDO.isEmergency" })
	private @ApiModelProperty("是否加急") Boolean iemergency;
	
	/**
	 * 预计到达日期
	 */
	@Mapping
	private @ApiModelProperty("预计到达日期") Date arrivalTime;
	/**
	 * 预约提货时间
	 */
	@Mapping({ "deliveryPickTime", "waybillDO.deliveryPickTime" })
	private @ApiModelProperty("预约提货时间") Long deliveryPickTime;
	/**
	 * 预约送货时间
	 */
	@Mapping({ "deliverySendTime", "waybillDO.deliverySendTime" })
	private @ApiModelProperty("预约送货时间") Long deliverySendTime;
	/**
	 * 入库员id
	 */
	@Mapping
	private @ApiModelProperty("入库员id") Integer storageUserId;
	/**
	 * 入库员姓名
	 */
	@Mapping
	private @ApiModelProperty("入库员姓名") String storageUserName;
	/**
	 * 业务员id
	 */
	@Mapping
	private @ApiModelProperty("业务员id") Integer salesmanId;
	/**
	 * 业务员姓名
	 */
	@Mapping
	private @ApiModelProperty("业务员姓名") String salesmanName;
	/**
	 * 备注
	 */
	@Mapping
	private @ApiModelProperty("备注") String remark;
	/**
	 * 总运费
	 */
	@Mapping
	private @ApiModelProperty("总运费") BigDecimal totalFee;
	/**
	 * 总体积
	 */
	@Mapping
	private @ApiModelProperty("总体积") BigDecimal volume;
	/**
	 * 总重量
	 */
	@Mapping
	private @ApiModelProperty("总重量") BigDecimal weight;
	/**
	 * 总件数
	 */
	@Mapping
	private @ApiModelProperty("总件数") Integer totalNum;
	/**
	 * 现付
	 */
	@Mapping
	private @ApiModelProperty("现付") BigDecimal cashPay;
	/**
	 * 月结
	 */
	@Mapping
	private @ApiModelProperty("月结") BigDecimal monthlyPay;
	/**
	 * 回单付
	 */
	@Mapping
	private @ApiModelProperty("回单付") BigDecimal receiptPay;
	/**
	 * 到付
	 */
	@Mapping
	private @ApiModelProperty("到付") BigDecimal cod;
	
	/**
	 * 回单付	
	 */
	private @Mapping  @ApiModelProperty("免费")BigDecimal forfree;
	/**
	 * 到付	
	 */
	private @Mapping  @ApiModelProperty("欠付")BigDecimal tardypay;
	/**
	 * 货款扣(运费)
	 */
	@Mapping
	private @ApiModelProperty("货款扣(运费)") BigDecimal goodsPaymentDeduction;

	/**
	 * 代收货款手续费
	 */
	@Mapping
	private @ApiModelProperty("代收货款手续费") BigDecimal deliveryCharge;

	/**
	 * 保价费
	 */
	@Mapping
	private @ApiModelProperty("保价费") BigDecimal protectFee;
	/**
	 * 运单状态
	 */
	@Mapping
	private @ApiModelProperty("运单状态") Integer status;
	
	/**
	 * 运单状态名称
	 */
	@Mapping
	private @ApiModelProperty("运单状态名称") String statusName;
	/**
	 * 是否审核
	 */
	@Mapping({ "ichecked", "waybillDO.isChecked" })
	private @ApiModelProperty("是否审核") Boolean ichecked;
	/**
	 * 是否打印
	 */
	@Mapping({ "iprint", "waybillDO.isPrint" })
	private @ApiModelProperty("是否打印") Boolean iprint;
	/**
	 * 打印次数
	 */
	private @Mapping @ApiModelProperty("打印次数") Integer printNum;
	/**
	 * 录单员ID
	 */
	@Mapping
	private @ApiModelProperty("录单员ID") Integer createUserId;
	/**
	 * 录单员姓名
	 */
	@Mapping
	private @ApiModelProperty("录单员姓名") String userName;
	/**
	 * 审核员ID
	 */
	@Mapping
	private @ApiModelProperty("审核员ID") Integer checkUserId;
	/**
	 * 审核员姓名
	 */
	@Mapping
	private @ApiModelProperty("审核员姓名") String checkUserName;
	/**
	 * 审核时间
	 */
	@Mapping
	private @ApiModelProperty("审核时间") Long checkTime;
	/**
	 * 取消审核员ID
	 */
	@Mapping
	private @ApiModelProperty("取消审核员ID") Long cancelCheckUserId;
	/**
	 * 取消审核员姓名
	 */
	@Mapping
	private @ApiModelProperty("取消审核员姓名") String cancelCheckUserName;
	/**
	 * 取消审核时间
	 */
	@Mapping
	private @ApiModelProperty("取消审核时间") Long cancelCheckTime;
	/**
	 * 作废类型
	 */
	@Mapping
	private @ApiModelProperty("作废类型") Integer discardType;

	private @Mapping  @ApiModelProperty("作废类型")String discardTypeName;

	/**
	 * 作废人
	 */
	private @Mapping  @ApiModelProperty("作废人")Integer discardUser;
	/**
	 * 作废人姓名
	 */
	private @Mapping  @ApiModelProperty("作废人姓名")String discardUserName;
	/**
	 * 签收图片
	 */
	@Mapping
	private @ApiModelProperty("签收图片") String signPic;
	/**
	 * 签收时间
	 */
	@Mapping
	private @ApiModelProperty("签收时间") Long signTime;
	/**
	 * 签收状态（签收单中的状态）	
	 */
	private  @ApiModelProperty("签收状态")Integer signStatus;
	
	/**
	 * 签收状态名称（签收单中的状态）	
	 */
	private  @ApiModelProperty("签收状态名称")String signStatusName;
	/**
	 * 作废原因
	 */
	@Mapping
	private @ApiModelProperty("作废原因") String discardReason;
	
	private @ApiModelProperty("作废时间") Long discardTime;
	/**
	 * 发货方Id
	 */
	@Mapping({ "invoiceCustomerId", "waybillInvoiceDO.customerId" })
	private @ApiModelProperty("发货方Id") Integer invoiceCustomerId;
	
	private @Mapping  @ApiModelProperty("发货会员Id")Integer invoiceCustomerVipId;
	/**
	 * 发货人
	 */
	@Mapping
	private @ApiModelProperty("发货人") String invoiceUser;
	/**
	 * 发货人id		
	 */
	private @Mapping  @ApiModelProperty("发货人id")Long invoiceUserId;
	/**
	 * 发货方
	 */
	@Mapping
	private @ApiModelProperty("发货单位") String invoiceCompany;
	/**
	 * 发货人微信号
	 */
	@Mapping({ "invoiceWxNo", "waybillInvoiceDO.wxNo" })
	private @ApiModelProperty("发货人微信号") String invoiceWxNo;

	@ApiModelProperty("发货人身份证号")
	@Mapping({ "invoiceIdentityCard", "waybillInvoiceDO.identityCard" })
	private String invoiceIdentityCard;
	/**
	 * 发货人电话
	 */
	@Mapping({ "invoiceTelNo", "waybillInvoiceDO.telNo" })
	private @ApiModelProperty("发货人电话") String invoiceTelNo;
	/**
	 * 发货人手机号码
	 */
	@Mapping({ "invoiceMobleNo", "waybillInvoiceDO.mobleNo" })
	private @ApiModelProperty("发货人手机号码") String invoiceMobleNo;
	/**
	 * 发货人国家ID
	 */
	@Mapping({ "invoiceCountryId", "waybillInvoiceDO.countryId" })
	private @ApiModelProperty("发货人国家ID") String invoiceCountryId;
	/**
	 * 发货人国家名称
	 */
	@Mapping
	private @ApiModelProperty("发货人国家名称") String invoiceCountryName;
	/**
	 * 发货人省ID
	 */
	@Mapping({ "invoiceProvId", "waybillInvoiceDO.provId" })
	private @ApiModelProperty("发货人省ID") String invoiceProvId;
	/**
	 * 发货人省名称
	 */
	@Mapping
	private @ApiModelProperty("发货人省名称") String invoiceProvName;

	@Mapping
	private @ApiModelProperty("发货人是否散户") Boolean invoiceRetail;

	/**
	 * 发货人城市ID
	 */
	@Mapping({ "invoiceCityId", "waybillInvoiceDO.cityId" })
	private @ApiModelProperty("发货人城市ID") String invoiceCityId;
	/**
	 * 发货人城市名称
	 */
	@Mapping
	private @ApiModelProperty("发货人城市名称") String invoiceCityName;
	/**
	 * 发货人地区ID
	 */
	@Mapping({ "invoiceDistrictId", "waybillInvoiceDO.districtId" })
	private @ApiModelProperty("发货人地区ID") String invoiceDistrictId;
	/**
	 * 发货人地区名称
	 */
	@Mapping
	private @ApiModelProperty("发货人地区名称") String invoiceDistrictName;
	/**
	 * 发货人街道ID
	 */
	@Mapping({ "invoiceStreetId", "waybillInvoiceDO.streetId" })
	private @ApiModelProperty("发货人街道ID") String invoiceStreetId;

	@Mapping({ "invoiceAddressId", "waybillInvoiceDO.addressId" })
	private @ApiModelProperty("临时客户发货方地址ID") Integer invoiceAddressId;

	/**
	 * 发货人街道名称
	 */
	@Mapping
	private @ApiModelProperty("发货人街道名称") String invoiceStreetName;
	/**
	 * 发货人详细地址
	 */
	@Mapping({ "invoiceAddress", "waybillInvoiceDO.address" })
	private @ApiModelProperty("发货人详细地址") String invoiceAddress;
	/**
	 * 收货人完整地址
	 */
	private @Mapping  @ApiModelProperty("收货人完整地址")String receiptFullAddress;
	/**
	 * 发货人完整地址
	 */
	private @Mapping  @ApiModelProperty("发货人完整地址")String invoiceFullAddress;
	/**
	 * 发货人身份，是否VIP客户，区分临时客户与客户档案，不持久到DB
	 */
	private @Mapping @ApiModelProperty("是否VIP客户") Boolean vip;
	/**
	 * 收货方ID
	 */
	@Mapping({ "receiptCustomerId", "waybillReceiptDO.customerId" })
	private @ApiModelProperty("收货方ID") Integer receiptCustomerId;
	
	private @Mapping  @ApiModelProperty("收货会员Id")Integer receiptCustomerVipId;
	
	@Mapping
    @ApiModelProperty("收货人Id")
    private Long receiptUserId;         //收货人Id
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

	@Mapping
	private @ApiModelProperty("收货人是否散户") Boolean receiptRetail;
	/**
	 * 收货人身份证号码
	 */
	@Mapping({ "receiptIdentityCard", "waybillReceiptDO.identityCard" })
	private @ApiModelProperty("收货人身份证号码") String receiptIdentityCard;
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
	 * 收货人国家ID
	 */
	@Mapping({ "receiptCountryId", "waybillReceiptDO.countryId" })
	private @ApiModelProperty("收货人国家ID") String receiptCountryId;
	/**
	 * 收货人国家名称
	 */
	@Mapping
	private @ApiModelProperty("收货人国家名称") String receiptCountryName;
	/**
	 * 收货人省ID
	 */
	@Mapping({ "receiptProvId", "waybillReceiptDO.provId" })
	private @ApiModelProperty("收货人省ID") String receiptProvId;
	/**
	 * 收货人省名称
	 */
	@Mapping
	private @ApiModelProperty("收货人省名称") String receiptProvName;
	/**
	 * 收货人城市ID
	 */
	@Mapping({ "receiptCityId", "waybillReceiptDO.cityId" })
	private @ApiModelProperty("收货人城市ID") String receiptCityId;
	/**
	 * 收货人城市名称
	 */
	@Mapping
	private @ApiModelProperty("收货人城市名称") String receiptCityName;
	/**
	 * 收货人地区ID
	 */
	@Mapping({ "receiptDistrictId", "waybillReceiptDO.districtId" })
	private @ApiModelProperty("收货人地区ID") String receiptDistrictId;
	/**
	 * 收货人地区名称
	 */
	@Mapping
	private @ApiModelProperty("收货人地区名称") String receiptDistrictName;
	/**
	 * 收货人街道ID
	 */
	@Mapping({ "receiptStreetId", "waybillReceiptDO.streetId" })
	private @ApiModelProperty("收货人街道ID") String receiptStreetId;

	@Mapping({ "receiptAddressId", "waybillReceiptDO.addressId" })
	private @ApiModelProperty("临时客户收货方地址ID") Integer receiptAddressId;

	/**
	 * 收货人街道名称
	 */
	@Mapping
	private @ApiModelProperty("收货人街道名称") String receiptStreetName;
	/**
	 * 收货人详细地址
	 */
	@Mapping({ "receiptAddress", "waybillReceiptDO.address" })
	private @ApiModelProperty("收货人详细地址") String receiptAddress;
	/**
	 * 代收款退货款方式
	 */
	@Mapping({ "deliveryRefundWay", "WaybillDeliveryCollectDO.refundWay" })
	private @ApiModelProperty("代收款退货款方式") Integer deliveryRefundWay;
	/**
	 * 代收款账户名称
	 */
	@Mapping({ "deliveryAccountName", "WaybillDeliveryCollectDO.accountName" })
	private @ApiModelProperty("代收款账户名称") String deliveryAccountName;
	/**
	 * 代收款开户银行
	 */
	@Mapping({ "deliveryBankName", "WaybillDeliveryCollectDO.bankName" })
	private @ApiModelProperty("代收款开户银行") String deliveryBankName;
	/**
	 * 代收款银行账号
	 */
	@Mapping({ "deliveryBankCard", "WaybillDeliveryCollectDO.bankCard" })
	private @ApiModelProperty("代收款银行账号") String deliveryBankCard;
	/**
	 * 代收款联系人
	 */
	@Mapping({ "deliveryContacts", "WaybillDeliveryCollectDO.contacts" })
	private @ApiModelProperty("代收款联系人") String deliveryContacts;
	/**
	 * 代收款手机号码
	 */
	@Mapping({ "deliveryMobile", "WaybillDeliveryCollectDO.mobile" })
	private @ApiModelProperty(" 代收款手机号码") String deliveryMobile;
	/**
	 * 代收款联系电话
	 */
	@Mapping({ "deliveryPhone", "WaybillDeliveryCollectDO.phone" })
	private @ApiModelProperty("代收款联系电话") String deliveryPhone;
	/**
	 * 代收款联系人身份证号码
	 */
	@Mapping({ "deliveryIdentityCard", "WaybillDeliveryCollectDO.identityCard" })
	private @ApiModelProperty("代收款联系人身份证号码	") String deliveryIdentityCard;
	/**
	 * 代收款备注
	 */
	@Mapping({ "deliveryRemark", "WaybillDeliveryCollectDO.remark" })
	private @ApiModelProperty("代收款备注") String deliveryRemark;
	/**
	 * 回单库存网点id
	 */
	@Mapping({ "backOrderOrgId", "waybillBackOrderStockDO.orgId" })
	private @ApiModelProperty("回单库存网点id") Integer backOrderOrgId;
	/**
	 * 回单收货方姓名
	 */
	@Mapping
	private @ApiModelProperty("回单收货方姓名") String backOrderReceiptName;
	/**
	 * 回单发货方ID
	 */
	@Mapping({ "backOrderInvoiceId", "waybillBackOrderStockDO.invoiceId" })
	private @ApiModelProperty("回单发货方ID") Long backOrderInvoiceId;
	/**
	 * 回单收货方ID
	 */
	@Mapping({ "backOrderReceiptId", "waybillBackOrderStockDO.receiptId" })
	private @ApiModelProperty("回单收货方ID") Long backOrderReceiptId;
	/**
	 * 回单发货方姓名
	 */
	@Mapping
	private @ApiModelProperty("回单发货方姓名") String backOrderInvoiceName;
	/**
	 * 回单库存网点名称
	 */
	@Mapping
	private @ApiModelProperty("回单库存网点名称") String backOrderOrgName;
	/**
	 * 回单数量
	 */
	@Mapping({ "backOrderNum", "waybillBackOrderStockDO.num" })
	private @ApiModelProperty("回单数量") Integer backOrderNum;
	/**
	 * 创建人
	 */
	@Mapping
	private @ApiModelProperty("创建人") Integer createUser;
	/**
	 * 创建人姓名
	 */
	@Mapping
	private @ApiModelProperty("创建人姓名") String createUserName;
	/**
	 * 创建时间
	 */
	@Mapping({"createTime","operTime"})
	private @ApiModelProperty("创建时间") Long createTime;
	/**
	 * 修改人
	 */
	@Mapping
	private @ApiModelProperty("修改人") Integer updateUser;
	/**
	 * 修改人姓名
	 */
	@Mapping
	private @ApiModelProperty("修改人姓名") String updateUserName;
	/**
	 * 修改时间
	 */
	@Mapping
	private @ApiModelProperty("修改时间") Long updateTime;
	
	/**
	 * 运单修改人-专门记录运单修改这一操作的信息	
	 */
	private @Mapping  @ApiModelProperty("运单修改人")Integer waybillUpdateUser;
	/**
	 * 运单修改人姓名-专门记录运单修改这一操作的信息	
	 */
	private @Mapping  @ApiModelProperty("运单修改人姓名")String waybillUpdateUserName;
	/**
	 * 运单修改时间-专门记录运单修改这一操作的信息		
	 */
	private @Mapping  @ApiModelProperty("运单修改时间")Long waybillUpdateTime;

	/**
	 * 付款方式
	 */
	@Mapping
	private @ApiModelProperty("付款方式") Integer payWay;
	
	/**
	 * 付款方式名称
	 */
	
	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;

	@Mapping
	@ApiModelProperty("是否为异常单")
	private Boolean exception;

	private @Mapping @ApiModelProperty("运输时效") Long timeEfficiency;

	/************************************ 需求新增字段 ******************************************/
	@Mapping
	@ApiModelProperty("商户ID")
	private Long merchantId;
	
	@Mapping
	@ApiModelProperty("商户名称")
	private String merchantName;// 冗余

	@Mapping
	@ApiModelProperty("仓储服务商ID")
	private Long warehouseServerId;
	
	@Mapping
	@ApiModelProperty("仓储服务商名称")
	private String warehouseServerName;// 冗余

	@Mapping
	@ApiModelProperty("外部订单编号")
	private String externalOrderCode;

	@Mapping
	@ApiModelProperty("经销商ID")
	private Long dealerId;
	
	@Mapping
	@ApiModelProperty("经销商名称")
	private String dealerName;// 冗余
	
	@Mapping
	@ApiModelProperty("承运商ID")
	private Long carrierId;
	
	@Mapping
	@ApiModelProperty("承运商名称")
	private String carrierName;// 冗余

	@Mapping
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;// 冗余

	/**
	 * 类型：1统仓统配、2统仓自配
	 */
	@Mapping
	@ApiModelProperty("仓配类型")
	private Integer warehouseDistributionType;
	
	/**
     * 类型：统仓统配、统仓自配
     */
	@Mapping
    @ApiModelProperty("仓配类型名称")
    private String warehouseDistributionName;

	/**
	 * 结算对象：仓储服务商、经销商
	 */
	@Mapping
	@ApiModelProperty("结算对象")
	private Integer settlementObject;
	
	/**
     * 结算对象：仓储服务商、经销商
     */
	@Mapping
    @ApiModelProperty("结算对象名称")
    private String settlementObjectName;
	
	/**
     * 结算对象：仓储服务商、经销商
     */
	@Mapping
    @ApiModelProperty("结算对象集团客户Id")
    private Integer settlementObjectGroupId;
	
	/**
     * 结算对象：仓储服务商、经销商
     */
	@Mapping
    @ApiModelProperty("结算对象集团客户名称")
    private String settlementObjectGroupName;

	@Mapping
	@ApiModelProperty("波次号")
	private String waveCode;

	@Mapping
	@ApiModelProperty("接收波次时间")
	private Long waveTime;

	/**
	 * 汇总运单号下所有包裹号的箱数
	 */
	@Mapping
	@ApiModelProperty("总箱数")
	private Integer boxNum;

	/**
	 * TMS自动接单时判断是否装车自动赋值（非必填）
	 */
	@Mapping
	@ApiModelProperty("是否装车")
	private Boolean loaded;
	
	@Mapping
	@ApiModelProperty("订单来源名称")
    private String orderSourceName;
	@Mapping
	@ApiModelProperty("订单来源")
    private Integer orderSource;

	/**
	 * 订单运输的类型，包括：销售订单、销退单；自动接收（非必填）
	 */
	@Mapping
	@ApiModelProperty("订单类型")
	private Integer orderType;
	
	/**
	 * 订单运输的类型，包括：销售订单、销退单；自动接收（非必填）
	 */
	@Mapping
	@ApiModelProperty("订单类型名称")
	private String orderTypeName;

	/**
	 * 运力分配接单时自动赋值（非必填）
	 */
	@Mapping
	@ApiModelProperty("销退类型")
	private Integer returnType;
	
	/**
	 * 运力分配接单时自动赋值（非必填）
	 */
	@Mapping
	@ApiModelProperty("销退类型名称")
	private String returnTypeName;

	/**
	 * 用于部分拒签时更新，接收OMS的更改单据金额后更新
	 */
	@Mapping
	@ApiModelProperty("销退审核")
	private Integer returnCheck;

	/**
	 * 用于司机APP上门提货（销退单类型等于“普通销退”）时装货确认时更新的数据
	 */
	@Mapping
	@ApiModelProperty("取货数量")
	private Integer deliveryNum;

	/**
	 * 用于司机APP上门提货（销退单类型等于“普通销退”）时装货确认时更新的数据
	 */
	@Mapping
	@ApiModelProperty("取货重量")
	private BigDecimal deliveryWeight;

	/**
	 * 用于司机APP上门提货（销退单类型等于“普通销退”）时装货确认时更新的数据
	 */
	@Mapping
	@ApiModelProperty("取货体积")
	private BigDecimal deliveryVolume;

	/**
	 * 该字段用于超时撤回或者手动撤回使用，目前一期不做所以该字段用不上
	 */
	@Mapping
	@ApiModelProperty("撤回类型")
	private Integer retractType;

	/**
	 * TMS接收OMS的审核更改单据而赋值（非必填）
	 */
	@Mapping
	@ApiModelProperty("实收代收货款")
	private BigDecimal goodsPayment;

	@Mapping
	@ApiModelProperty("是否生成派车单")
	private Boolean truckOrdered;

	@Mapping
	@ApiModelProperty("门店ID")
	private Long storeId;
	
	@Mapping
	@ApiModelProperty("门店名称")
	private String storeName;
	
	@Mapping
    @ApiModelProperty("装货时间")
    private Long freightTime; 
    
	@Mapping
    @ApiModelProperty("发车时间")
    private Long dispatcherTime; 
	
	@Mapping
	@ApiModelProperty("发车确认时间")
	private Long confirmTime;
    
	@Mapping
    @ApiModelProperty("配送时间")
    private Long deliveryTime; 
    
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;
    
	@Mapping
    @ApiModelProperty("分摊成本(四种分摊成本总合)")
    private BigDecimal cost;
    
	/**
	 * 司机姓名
	 */
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	
	/**
	 * 手机号码
	 */
	@Mapping
	@ApiModelProperty("司机手机号码")
	private String phoneNo;
	
	@Mapping
    @ApiModelProperty("未税总运费")
    private BigDecimal freightFeeNoTax;
	
	@Mapping
    @ApiModelProperty("税费")
    private BigDecimal tax;
    
	@Mapping
	@ApiModelProperty("拒收金额")
	private BigDecimal refuseAmount;
	
	@Mapping
	@ApiModelProperty("最大提货数")
	private Integer maxDelivery;
	
	@Mapping
	@ApiModelProperty("品种数")
	private Integer skuidCount ;
	
//	@Mapping()
//	@ApiModelProperty("原司机ID")
//    private Integer originalDriverId;
	
	/**
	 *仓库线路id	
	 */
	private @Mapping  @ApiModelProperty("仓库线路id")Long warehouseLineId;
	/**
	 * 仓库线路名称	
	 */
	private @Mapping  @ApiModelProperty("仓库线路名称	")String warehouseLineName;

	@Mapping()
	@ApiModelProperty("商户全局客户ID")//订单下来
	private Long merchandGlobalId;
	@Mapping()
	@ApiModelProperty("商户全局客户名称")//订单下来
	private String merchandGlobalName;
	
	@Mapping()
	@ApiModelProperty("商户集团客户ID")
	private Long merchandGroupId;
	@Mapping()
	@ApiModelProperty("商户集团客户名称")
	private String merchandGroupName;
	
	@Mapping()
	@ApiModelProperty("仓储服务商全局客户ID")//订单下来
	private Long warehouseServerGlobalId;
	@Mapping()
	@ApiModelProperty("仓储服务商全局客户名称")//订单下来
	private String warehouseServerGlobalName;
	
	@Mapping()
	@ApiModelProperty("仓储服务商集团客户ID")
	private Long warehouseServerGroupId;
	@Mapping()
	@ApiModelProperty("仓储服务商集团客户名称")
	private String warehouseServerGroupName;
	
	@Mapping()
	@ApiModelProperty("经销商全局客户ID")//订单下来
	private Long dealerGlobalId;
	@Mapping()
	@ApiModelProperty("经销商全局客户名称")//订单下来
	private String dealerGlobalName;
	
	@Mapping()
	@ApiModelProperty("经销商集团客户ID")
	private Long dealerGroupId;
	@Mapping()
	@ApiModelProperty("经销商集团客户名称")
	private String dealerGroupName;
	
	@Mapping()
	@ApiModelProperty("承运商全局客户ID")//通过承运商Id(业务单元Id)获取全局客户Id-
									//这个字段没有用，目前取的是承运商id:CarrierId,而承运商id取的是发货网点id
	private Long carrierGlobalId;
	@Mapping()
	@ApiModelProperty("承运商全局客户名称")//通过承运商Id(业务单元Id)获取全局客户Id
	private String carrierGlobalName;
	
	@Mapping
	@ApiModelProperty("支付渠道名称(1=现金支付,2=二维码支付)")
	private String settlementMethodName;
	
	@Mapping
	@ApiModelProperty("代收货款")
    private BigDecimal orderGoodsPayment;
	@Mapping
	@ApiModelProperty("车牌号码")
	private String vehicleNo;
	
	@Mapping()
	@ApiModelProperty("订单运输类型")
    private Integer transportType;
	
	@Mapping()
	@ApiModelProperty("订单运输类型名称")
	private String transportTypeName;//冗余
	
	@Mapping()
	@ApiModelProperty("指定日期")
    private String appointDate;
	@Mapping()
	@ApiModelProperty("指定开始时间")
    private String appointStartTime;
	@Mapping()
	@ApiModelProperty("指定结束时间")
    private String appointEndTime;
	
	@Mapping()
	@ApiModelProperty("运单来源类型")
    private Integer waybillType;
	
	@Mapping()
	@ApiModelProperty("运单来源类型名称")
    private String waybillTypeName;
	
	/**
	 * true为开单直送
	 */
	@Mapping()
	@ApiModelProperty("是否开单直送")
    private Boolean directSend;
	
//	/**
//	 * true为开票
//	 */
//	@Mapping()
//	@ApiModelProperty("是否开票")
//    private Boolean isBill;

	/**
	 * 累计签收件数（一个运单多次签收，签收件数总和）
	 */
	private @Mapping  @ApiModelProperty("累计签收件数")Integer totalSignNumber;
	
	/**
	 * 累计拒签件数（一个运单多次拒签，累计拒签件数）
	 */
	private @Mapping  @ApiModelProperty("累计拒签件数")Integer totalRefuseSignNumber;
	
    @Mapping()
	@ApiModelProperty("单据类型   1、单据领用    2、企业自动编号")
    private Integer codeType;

    @Mapping()
	@ApiModelProperty("运单创建类型   1、完整版    2、简单版")
    private Integer billCreateType;
    
	@Mapping
	private @ApiModelProperty("app或是pc端手动开单") Integer pcOrApp; //1、pc端    2、app端
	
	@Mapping
	private @ApiModelProperty("app或是pc端手动开单-名称") String pcOrAppName; //pc手动开单    ，，app手动开单
	
	/**
	 * 添加商品
	 *
	 * @param waybillGoodsEsDTO
	 *            商品
	 */
	public void addWaybillGoods(WaybillGoodsEsDTO waybillGoodsEsDTO) {
		if (null == waybillGoodsEsDtos) {
			waybillGoodsEsDtos = Lists.newArrayList();
		}
		waybillGoodsEsDtos.add(waybillGoodsEsDTO);
	}

	/**
	 * 添加费用项
	 *
	 * @param waybillFeeEsDTO
	 *            费用项
	 */
	public void addWaybillFee(WaybillFeeEsDTO waybillFeeEsDTO) {
		if (null == waybillFeeEsDtos) {
			waybillFeeEsDtos = Lists.newArrayList();
		}
		waybillFeeEsDtos.add(waybillFeeEsDTO);
	}
	
	/**
	 * 添加费用项
	 *
	 * @param
	 *            费用项
	 */
	public void addWaybillStockDetail(WaybillStockDetailEsDTO waybillStockDetailEsDTO) {
		if (null == waybillStockDetailEsDTOs) {
			waybillStockDetailEsDTOs = Lists.newArrayList();
		}
		waybillStockDetailEsDTOs.add(waybillStockDetailEsDTO);
	}
	/**
	 * 包裹明细
	 *
	 * @param packageDetailEsDto
	 *            包裹明细项
	 */
	public void addPackageDetailEsDto(PackageDetailEsDTO packageDetailEsDto) {
		if (null == packageDetailEsDtos) {
			packageDetailEsDtos = Lists.newArrayList();
		}
		packageDetailEsDtos.add(packageDetailEsDto);
	}
	@Mapping
	@ApiModelProperty("改配单号")
    private String changeDispatcherCode;
	
	@Mapping
	@ApiModelProperty("改配单类型")
    private Integer changeDispatcherType;
	
	@Mapping
	@ApiModelProperty("改配单类型名称")
    private String changeDispatcherTypeName;

	@Mapping
	private @ApiModelProperty("app端的模糊检索条件") String combinationStr;

}

