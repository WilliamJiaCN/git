package com.hivescm.tms.api.dto.es.waybill;

import com.hivescm.framework.elasticsearch.annotation.Cascade;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TMSWaybillEsDTO
 * 
 * @company 蜂网供应链管理
 */
@Data
@ToString
@Cascade(value = "waybill",fields = {"code","invoiceMobleNo","receiptConsignorMobleNo","invoiceUser","receiptUser"})
public class WaybillEsDTO implements Serializable,Comparable<WaybillEsDTO>{
	
	
	public WaybillEsDTO() {
	}
	
	
	public WaybillEsDTO(Long id,Integer companyId, Boolean ichecked) {
		this.id = id;
		this.ichecked = ichecked;
		this.companyId = companyId;
	}


	public WaybillEsDTO(Long id, Integer status) {
		super();
		this.id = id;
		this.status = status;
	}
	public WaybillEsDTO(Long id, Boolean iwaitNotice) {
		super();
		this.id = id;
		this.iwaitNotice = iwaitNotice;
	}
	
	public WaybillEsDTO(Long id,Integer discardType,String discardReason,Integer status){
    	this.id = id;
    	this.discardType = discardType;
    	this.discardReason = discardReason;
    	this.status = status;
    }

	/**
	 * 根据仓库线路id分组
	 * @return
	 */
	public Long groupKeyByLineId() {
		return this.lineId;
	}
	
	/**
	 * 根据街道id分组
	 * @return
	 */
	public String groupKeyByreceiptStreetName() {
		return this.receiptStreetId;
	}
	
	/**
	 * 根据详细地址id分组
	 * @return
	 */
	public String groupKeyByreceiptAddressName() {
		return this.receiptAddress;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键	
	 */
    @Mapping({"waybillId", "id"})
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 公司id
	 */
	private @Mapping  @ApiModelProperty("公司id")Integer companyId ;
	/**
	 * 公司名称	
	 */
	private @Mapping  @ApiModelProperty("公司名称") String companyName ;
	
	/**
	 * 客户单号	
	 */
	private @Mapping  @ApiModelProperty("客户单号") String  customerCode;
	
	
	/**
	 * 运单号	
	 */
	@Mapping({"waybillCode", "code"}) @Logger
	private  @ApiModelProperty("运单号") String code ;
	/**
	 * 订单编号	
	 */
	private @Mapping  @ApiModelProperty("订单编号") @Logger String orderCode ;
	/**
	 * 主订单编号	
	 */
	private @Mapping  @ApiModelProperty("主订单编号")String mainOrderCode ;
	/**
	 * 原订单号	
	 */
	private @Mapping  @ApiModelProperty("原订单号") String originalOrderCode;
	/**
	 * 发货网点ID	
	 */
	private @Mapping  @ApiModelProperty("发货网点ID") Integer invoiceOrgId;
	/**
	 * 发货网点名称	
	 */
	private @Mapping  @ApiModelProperty("发货网点名称")String invoiceOrgName;
	/**
	 * 商品名称 ,“/”间隔
	 */
	private @Mapping @ApiModelProperty("商品名称") String goodsName;
	
	@Mapping
	@ApiModelProperty("货物类型名称")//冗余商品表类型，以"/"间隔
	private String goodsTypeName;
	
    /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型名称") String prodTypeName;
	/**
	 * 包装名称 ,“/”间隔
	 */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
	/**
	 * 目的地级别
	 */
	private @Mapping  @ApiModelProperty("目的地级别")Integer destLevel;
	/**
	 * 目的地id
	 */
	private @Mapping  @ApiModelProperty("目的地id")String destId;
	/**
	 * 目的地名称	
	 */
	private @Mapping  @ApiModelProperty("目的地名称")String destName;
	
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
	private @Mapping  @ApiModelProperty("运输线路id")Long lineId;
	/**
	 * 运输线路名称	
	 */
	private @Mapping  @ApiModelProperty("运输线路名称")String lineName;
	/**
	 * 到达网点id	
	 */
	private @Mapping  @ApiModelProperty("到达网点id")Integer destOrgId;
	/**
	 * 到达网点名称	
	 */
	private @Mapping  @ApiModelProperty("到达网点名称")String destOrgName;
	/**
	 * 业务日期	
	 */
	private @Mapping  @ApiModelProperty("业务日期") Long bussTime;
	/**
	 * 下单日期	
	 */
	private @Mapping  @ApiModelProperty("下单日期") Long orderDate;
	/**
	 * 回单要求（名称）
	 */
	@Mapping
	private @ApiModelProperty("回单要求名称") String backType;
	
	@Mapping
	private @ApiModelProperty("回单要求") Integer backTypeValue;
	
	/**
	 * 回单份数	
	 */
	private @Mapping  @ApiModelProperty("回单份数") Integer backNum;
	/**
	 * 回单编号	
	 */
	private @Mapping  @ApiModelProperty("回单编号")String backCode;
	/**
	 * 特殊要求
	 */
	private @Mapping  @ApiModelProperty("特殊要求")String specialRequire;
	/**
	 * 是否开发票	
	 */
	private @Mapping({ "ibill", "waybillDO.isBill" })  @ApiModelProperty("是否开发票")Boolean ibill;
	/**
	 * 是否等通知	
	 */
	private @Mapping  @ApiModelProperty("是否等通知")Boolean iwaitNotice;
	/**
	 * 是否整车	
	 */
	private @Mapping  @ApiModelProperty("是否整车") Boolean itruckLoad;
	/**
	 * 是否加急	
	 */
	private @Mapping  @ApiModelProperty("是否加急")Boolean iemergency;
	/**
	 * 预计到达日期	
	 */
	private @Mapping  @ApiModelProperty("预计到达日期")Date arrivalTime;
	/**
     * 预约提货时间
     */
    private @Mapping  @ApiModelProperty("预约提货时间") Long deliveryPickTime;
    /**
     * 预约送货时间
     */
    private @Mapping  @ApiModelProperty("预约送货时间") Long deliverySendTime;
	/**
	 * 入库员id	
	 */
	private @Mapping  @ApiModelProperty("入库员id")Integer storageUserId;
	/**
	 * 入库员姓名
	 */
	private @Mapping  @ApiModelProperty("入库员姓名")String storageUserName;
	/**
	 * 业务员id	
	 */
	private @Mapping  @ApiModelProperty("业务员id")Integer salesmanId;
	/**
	 * 业务员姓名	
	 */
	private @Mapping  @ApiModelProperty("业务员姓名")String salesmanName;
	/**
	 * 备注	
	 */
	private @Mapping({"DispatcherDetailPrintDTO.waybillRemark","TmsWaybillEsDTO.remark","DispatcherWaybillEsDTO.waybillRemark"}) @ApiModelProperty("备注")String remark;
	/**
	 * 总运费	
	 */
	private @Mapping  @ApiModelProperty("总运费")BigDecimal totalFee;
	
	 /**
     * 代收货款手续费
     */
    private @Mapping @ApiModelProperty("代收货款手续费") BigDecimal deliveryCharge;
    
    /**
     *保价费
     */
    private @Mapping @ApiModelProperty("保价费") BigDecimal protectFee;
	
	/**
     * 总体积
     */
    private @Mapping  @ApiModelProperty("总体积") BigDecimal volume;
    /**
     * 总重量
     */
    private @Mapping  @ApiModelProperty("总重量") BigDecimal weight;
    /**
     * 总件数
     */
    private @Mapping  @ApiModelProperty("总件数") Integer totalNum;
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
	 * 派送方式
	 */
	@Mapping
	private @ApiModelProperty("配送方式") Integer distributionType;
	
	/**
	 * 派送方式名称
	 */
	@Mapping
	private @ApiModelProperty("配送方式名称") String distributionTypeName;
	/**
	 * 现付	
	 */
	private @Mapping  @ApiModelProperty("现付")BigDecimal cashPay;
	/**
	 * 月结	
	 */
	private @Mapping  @ApiModelProperty("月结")BigDecimal monthlyPay;
	/**
	 * 回单付	
	 */
	private @Mapping  @ApiModelProperty("回单付")BigDecimal receiptPay;
	/**
	 * 到付	
	 */
	private @Mapping  @ApiModelProperty("到付")BigDecimal cod;

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
	private @Mapping  @ApiModelProperty("货款扣(运费)")BigDecimal goodsPaymentDeduction;
	/**
	 * 运单状态	
	 */
	private @Mapping({"waybillStatus","status"}) @ApiModelProperty("运单状态")Integer status;
	
	/**
	 * 状态名称
	 */
	@Mapping
	private @ApiModelProperty("运单状态名称") String statusName;
	
	/**
	 * 是否审核	
	 */
	private @Mapping({"ichecked","waybillDO.isChecked"})  @ApiModelProperty("是否审核")Boolean ichecked;
	/**
	 * 是否打印	
	 */
	private @Mapping({"iprint","waybillDO.isPrint"})  @ApiModelProperty("是否打印")Boolean iprint;
	/**
	 * 打印次数	
	 */
	private @Mapping  @ApiModelProperty("打印次数")Integer printNum;
	
	/**
	 * 录单员ID	
	 */
	private @Mapping  @ApiModelProperty("录单员ID")Integer createUserId;
	/**
	 * 录单员姓名	
	 */
	private @Mapping  @ApiModelProperty("录单员姓名")String userName;
	/**
	 * 审核员ID	
	 */
	private @Mapping  @ApiModelProperty("审核员ID")Integer checkUserId;
	/**
	 * 审核员姓名	
	 */
	private @Mapping  @ApiModelProperty("审核员姓名")String checkUserName;
	/**
	 * 审核时间	
	 */
	private @Mapping  @ApiModelProperty("审核时间")Long checkTime;
	/**
	 * 取消审核员ID	
	 */
	private @Mapping  @ApiModelProperty("取消审核员ID")Long cancelCheckUserId;
	/**
	 * 取消审核员姓名	
	 */
	private @Mapping  @ApiModelProperty("取消审核员姓名")String cancelCheckUserName;
	/**
	 * 取消审核时间	
	 */
	private @Mapping  @ApiModelProperty("取消审核时间	")Long cancelCheckTime;
	/**
	 * 作废类型	
	 */
	private @Mapping  @ApiModelProperty("作废类型")Integer discardType;

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
	private @Mapping  @ApiModelProperty("签收图片")String signPic;
	/**
	 * 签收时间	
	 */
	private @Mapping  @ApiModelProperty("签收时间")Long signTime;
	/**
	 * 签收状态（签收单中的状态）	
	 */
	private @Mapping  @ApiModelProperty("签收状态")Integer signStatus;
	
	/**
	 * 签收状态名称（签收单中的状态）	
	 */
	private @Mapping  @ApiModelProperty("签收状态名称")String signStatusName;

	/**
	 * 累计签收件数（一个运单多次签收，签收件数总和）
	 */
	private @Mapping  @ApiModelProperty("累计签收件数")Integer totalSignNumber;
	
	/**
	 * 累计拒签件数（一个运单多次拒签，累计拒签件数）
	 */
	private @Mapping  @ApiModelProperty("累计拒签件数")Integer totalRefuseSignNumber;
	
	/**
	 * 作废原因	
	 */
	private @Mapping  @ApiModelProperty("作废原因")String discardReason;
	/**
	 * 作废时间	
	 */
	private @Mapping  @ApiModelProperty("作废时间")Long discardTime;
	
	/**
	 * 发货方Id		
	 */
	private @Mapping  @ApiModelProperty("发货方Id")Integer invoiceCustomerId;
	
	
	private @Mapping  @ApiModelProperty("发货会员Id")Integer invoiceCustomerVipId;
	/**
	 * 发货人		
	 */
	private @Mapping  @ApiModelProperty("发货人")String invoiceUser;
	/**
	 * 发货人id		
	 */
	private @Mapping  @ApiModelProperty("发货人id")Long invoiceUserId;
	/**
	 * 发货方名称		
	 */
	private @Mapping  @ApiModelProperty("发货方名称")String invoiceCompany;
	
	
	/**
	 * 发货人微信号		
	 */
	private @Mapping  @ApiModelProperty("发货人微信号	")String invoiceWxNo;

	@ApiModelProperty("发货人身份证号")
	@Mapping
	private String invoiceIdentityCard;
	/**
	 * 发货人电话		
	 */
	private @Mapping  @ApiModelProperty("发货人电话")String invoiceTelNo;
	/**
	 * 发货人手机号码	
	 */
	private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;
	/**
	 * 发货人国家ID		
	 */
	private @Mapping  @ApiModelProperty("发货人国家ID")String invoiceCountryId;
	/**
	 * 发货人国家名称		
	 */
	private @Mapping  @ApiModelProperty("发货人国家名称")String invoiceCountryName;
	/**
	 * 发货人省ID	
	 */
	private @Mapping  @ApiModelProperty("发货人省ID")String invoiceProvId;
	/**
	 * 发货人省名称		
	 */
	private @Mapping  @ApiModelProperty("发货人省名称")String invoiceProvName;
	/**
	 * 	发货人城市ID	
	 */
	private @Mapping  @ApiModelProperty("发货人城市ID")String invoiceCityId;
	
	@Mapping({ "invoiceAddressId", "waybillInvoiceDO.addressId" })
	private @ApiModelProperty("临时客户发货方地址ID") Integer invoiceAddressId;
	@Mapping
	private @ApiModelProperty("发货人是否散户") Boolean invoiceRetail;
	/**
	 * 发货人城市名称		
	 */
	private @Mapping  @ApiModelProperty("发货人城市名称")String invoiceCityName;
	/**
	 * 发货人地区ID		
	 */
	private @Mapping  @ApiModelProperty("发货人地区ID") String invoiceDistrictId;
	/**
	 * 发货人地区名称	
	 */
	private @Mapping  @ApiModelProperty("发货人地区名称")String invoiceDistrictName;
	/**
	 * 发货人街道ID		
	 */
	private @Mapping  @ApiModelProperty("发货人街道ID")String invoiceStreetId;
	/**
	 * 发货人街道名称	
	 */
	private @Mapping  @ApiModelProperty("发货人街道名称")String invoiceStreetName;
	/**
	 * 发货人详细地址	 	
	 */
	private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;

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
	private @Mapping  @ApiModelProperty("收货方ID")Integer receiptCustomerId;
	
	private @Mapping  @ApiModelProperty("收货会员Id")Integer receiptCustomerVipId;
	@Mapping
	private @ApiModelProperty("收货人是否散户") Boolean receiptRetail;
	
	@Mapping({ "receiptAddressId", "waybillReceiptDO.addressId" })
	private @ApiModelProperty("临时客户收货方地址ID") Integer receiptAddressId;
	/**
	 * 收货人		
	 */
	private @Mapping  @ApiModelProperty("收货人")String receiptUser;
	
	@Mapping
    @ApiModelProperty("收货人Id")
    private Long receiptUserId;         //收货人Id
	/**
	 * 收货方名称	
	 */
	private @Mapping  @ApiModelProperty("收货方名称")String receiptCompany;
	/**
	 * 收货人身份证号码		
	 */
	private @Mapping  @ApiModelProperty("收货人身份证号码")String receiptIdentityCard;
	/**
	 * 收货人电话		
	 */
	private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;
	/**
	 * 收货人手机号码		
	 */
	private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
	/**
	 * 收货人国家ID		
	 */
	private @Mapping  @ApiModelProperty("收货人国家ID")String receiptCountryId;
	/**
	 * 收货人国家名称		
	 */
	private @Mapping  @ApiModelProperty("收货人国家名称")String receiptCountryName;
	/**
	 * 收货人省ID		
	 */
	private @Mapping  @ApiModelProperty("收货人省ID")String receiptProvId;
	/**
	 * 收货人省名称		
	 */
	private @Mapping  @ApiModelProperty("收货人省名称")String receiptProvName;
	/**
	 * 收货人城市ID		
	 */
	private @Mapping  @ApiModelProperty("收货人城市ID")String receiptCityId;
	/**
	 * 收货人城市名称		
	 */
	private @Mapping  @ApiModelProperty("收货人城市名称")String receiptCityName;
	/**
	 * 收货人地区ID		
	 */
	private @Mapping  @ApiModelProperty("收货人地区ID")String receiptDistrictId;
	/**
	 * 收货人地区名称		
	 */
	private @Mapping  @ApiModelProperty("收货人地区名称")String receiptDistrictName;
	/**
	 * 收货人街道ID		
	 */
	private @Mapping  @ApiModelProperty("收货人街道ID")String receiptStreetId;
	/**
	 * 收货人街道名称		
	 */
	private @Mapping  @ApiModelProperty("收货人街道名称")String receiptStreetName;
	/**
	 * 收货人详细地址		
	 */
	private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;
	/**
	 * 收货人完整地址
	 */
	private @Mapping  @ApiModelProperty("收货人完整地址")String receiptFullAddress;
	/**
	 * 代收款退货款方式		
	 */
	private @Mapping  @ApiModelProperty("代收款退货款方式")Integer deliveryRefundWay;
	/**
	 * 代收款账户名称		
	 */
	private @Mapping  @ApiModelProperty("代收款账户名称")String deliveryAccountName;
	/**
	 * 代收款开户银行		
	 */
	private @Mapping  @ApiModelProperty("代收款开户银行")String deliveryBankName;
	/**
	 * 代收款银行账号		
	 */
	private @Mapping  @ApiModelProperty("代收款银行账号")String deliveryBankCard;
	/**
	 * 代收款联系人		
	 */
	private @Mapping  @ApiModelProperty("代收款联系人	")String deliveryContacts;
	/**
	 * 代收款手机号码		
	 */
	private @Mapping  @ApiModelProperty(" 代收款手机号码")String deliveryMobile;
	/**
	 * 代收款联系电话		
	 */
	private @Mapping  @ApiModelProperty("代收款联系电话")String deliveryPhone;
	/**
	 * 代收款联系人身份证号码		
	 */
	private @Mapping  @ApiModelProperty("代收款联系人身份证号码	")String deliveryIdentityCard;
	/**
	 * 代收款备注		
	 */
	private @Mapping  @ApiModelProperty("代收款备注	")String deliveryRemark;
	/**
	 * 回单库存网点id		
	 */
	private @Mapping  @ApiModelProperty("回单库存网点id	")Integer backOrderOrgId;
	/**
	 * 回单收货方姓名		
	 */
	private @Mapping  @ApiModelProperty("回单收货方姓名")String backOrderReceiptName;
	/**
	 * 回单发货方ID		
	 */
	private @Mapping  @ApiModelProperty("回单发货方ID")Long backOrderInvoiceId;
	/**
	 * 回单收货方ID		
	 */
	private @Mapping  @ApiModelProperty("回单收货方ID")Long backOrderReceiptId;
	/**
	 * 回单发货方姓名		
	 */
	private @Mapping  @ApiModelProperty("回单发货方姓名")String backOrderInvoiceName;
	/**
	 * 回单库存网点名称		
	 */
	private @Mapping  @ApiModelProperty("回单库存网点名称")String backOrderOrgName;
	/**
	 * 回单数量		
	 */
	private @Mapping  @ApiModelProperty("回单数量")Integer backOrderNum;
	/**
	 * 创建人		
	 */
	private @Mapping  @ApiModelProperty("创建人	")Integer createUser;
	/**
	 * 创建人姓名		
	 */
	private @Mapping  @ApiModelProperty("创建人姓名")String createUserName;
	/**
	 * 创建时间		
	 */
	private @Mapping  @ApiModelProperty("创建时间") Long createTime;
	/**
	 * 修改人		
	 */
	private @Mapping  @ApiModelProperty("修改人")Integer updateUser;
	/**
	 * 修改人姓名		
	 */
	private @Mapping  @ApiModelProperty("修改人姓名")String updateUserName;
	/**
	 * 修改时间		
	 */
	private @Mapping  @ApiModelProperty("修改时间")Long updateTime;
	
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
	 * 运费结算方式
	 */
	
	private @Mapping @ApiModelProperty("运费结算方式") Integer payWay;
	
	/**
	 * 付款方式名称
	 */
	
	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;

	@Mapping("isException,exception")
	@ApiModelProperty("是否为异常单")
	private  Boolean exception;
	
	
	private @Mapping @ApiModelProperty("运输时效") Long timeEfficiency;
	
	//支付渠道-就是签收的收款方式，对应枚举：SettlementModeEnum
	@Mapping
	@ApiModelProperty("支付渠道(1=现金支付,2=二维码支付)")
	private Integer settlementMethod;
	
	@Mapping
	@ApiModelProperty("支付渠道名称(1=现金支付,2=二维码支付)")
	private String settlementMethodName;
	
	
 /************************************需求新增字段******************************************/
    
	@Mapping
    @ApiModelProperty("仓储服务商ID")
    private Long warehouseServerId;
	
	@Mapping
    @ApiModelProperty("仓储服务商名称")
	private String warehouseServerName;//冗余
    
	@Mapping
    @ApiModelProperty("外部订单编号")
	private String externalOrderCode;
    
	@Mapping
    @ApiModelProperty("经销商ID")
    private Long dealerId;
	
	@Mapping
	@ApiModelProperty("经销商名称")
	private String dealerName;//冗余
	
	@Mapping
	@ApiModelProperty("承运商ID")
    private Long carrierId;
	
	@Mapping
	@ApiModelProperty("承运商名称")
    private String carrierName;//冗余
	
	@Mapping
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;//冗余
	
    /**
     * 类型：统仓统配、统仓自配
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
    @ApiModelProperty("销退单类型")
    private Integer returnType;
   
	/**
     * 运力分配接单时自动赋值（非必填）
     */
	@Mapping
    @ApiModelProperty("销退单类型名称")
    private String returnTypeName;
    
    /**
     * 用于部分拒签时更新，接收OMS的更改单据金额后更新
     */
	@Mapping
    @ApiModelProperty("销退审核")
    private Integer returnCheck;
    
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
    private Boolean truckOrdered ;
    
	@Mapping
    @ApiModelProperty("门店ID")
    private Long storeId;
	
	@Mapping
    @ApiModelProperty("门店名称")
    private String storeName;
	
	
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
  	@ApiModelProperty("波次ID")
	private Long waveId;
    
    /**
     * 装货时间
     */
    @Mapping
    @ApiModelProperty("装货时间")
    private Long freightTime;
    
    @Mapping
    @ApiModelProperty("最大提货数")
	private Integer maxDelivery;
    
    @Mapping
    @ApiModelProperty("品种数")
	private Integer skuidCount;
    
    //后面 要存放到数据库  目前只在Es冗余 TODO
    @Mapping()
	@ApiModelProperty("原司机ID")
    private Integer originalDriverId;
    
    /**
	 *仓库线路id	
	 */
	private @Mapping  @ApiModelProperty("仓库线路id")Long warehouseLineId;
	/**
	 * 仓库线路名称	
	 */
	private @Mapping  @ApiModelProperty("仓库线路名称")String warehouseLineName;
	
	@Mapping
    @ApiModelProperty("商户ID")
	private Long merchantId;
	@Mapping
    @ApiModelProperty("商户名称")
	private String merchantName;//冗余
	
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
	@ApiModelProperty("承运商全局客户ID")//通过承运商Id(承运商集团Id)获取全局客户Id
	private Long carrierGlobalId;
	@Mapping()
	@ApiModelProperty("承运商全局客户名称")//通过承运商Id(承运商集团Id)获取全局客户名称
	private String carrierGlobalName;
	
	@Mapping()
	@ApiModelProperty("订单运输类型")
    private Integer transportType;
	
	@Mapping()
	@ApiModelProperty("订单运输类型名称")
	private String transportTypeName;//冗余
	@Mapping()
	@ApiModelProperty("运单改配状态")
    private Integer changeDelivery;
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
	
	/****************************冗余运单费用字段*********************************/
	@Mapping
	@ApiModelProperty("代收货款")
    private BigDecimal orderGoodsPayment;
    /****************************冗余派车单字段*********************************/
    /**
     * 发车时间
     */
    @Mapping
    @ApiModelProperty("发车时间")
    private Long dispatcherTime;  //该字段不用
    
    
    /**
     * 配送时间
     */
    @Mapping
    @ApiModelProperty("配送时间")
    private Long deliveryTime; 
    
    /**
	 * 派车批次
	 */
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;
    
	/**
     * 分摊成本(四种分摊成本总合)
     */
    @Mapping
    @ApiModelProperty("分摊成本(四种分摊成本总合)")
    private BigDecimal cost;
    
	/**
	 * 司机姓名
	 */
    @Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
    
    @Mapping()
	@ApiModelProperty("司机ID")
    private Integer driverId;
	
	/**
	 * 手机号码
	 */
    @Mapping
	@ApiModelProperty("司机手机号码")
	private String phoneNo;
    
	/**
	 * 发车确认时间
	 */
	@Mapping
	@ApiModelProperty("发车确认时间")
	private Long confirmTime;
	
	/**
	 * 车牌号码
	 */
	@Mapping
	@ApiModelProperty("车牌号码")
	private String vehicleNo;
	@Mapping
    @ApiModelProperty("提货费")
	private BigDecimal pickupFee;
	@Mapping
    @ApiModelProperty("基本运费")
	private BigDecimal baseFreight;
	@Mapping
    @ApiModelProperty("送货费")
	private BigDecimal deliveryFee;
	@Mapping
    @ApiModelProperty("上楼费")
	private BigDecimal upstairsFee;
	@Mapping
    @ApiModelProperty("包装费")
	private BigDecimal packingCharges;
	@Mapping
    @ApiModelProperty("声明价值")
	private BigDecimal declaredValue;
	@Mapping
    @ApiModelProperty("信息费")
	private BigDecimal informationFee;
	@Mapping
	@ApiModelProperty("加急费")
	private BigDecimal emergencyFee;
	@Mapping
    @ApiModelProperty("垫付运费")
	private BigDecimal freightAdvanced;
	@Mapping
	private @ApiModelProperty("垫付货款") BigDecimal freightPayment;
	@Mapping
    @ApiModelProperty("其他费用")
	private BigDecimal otherFee;
	@Mapping
    @ApiModelProperty("业务费")
	private BigDecimal business;
	@Mapping
    @ApiModelProperty("到付送货费")
    private BigDecimal toPayDeliveryFee;
    
    @Mapping()
	@ApiModelProperty("单据类型   1、单据领用    2、企业自动编号")
    private Integer codeType;

    @Mapping()
	@ApiModelProperty("运单创建类型   1、完整版    2、简单版")
    private Integer billCreateType;
    
    @Mapping
	@ApiModelProperty("改配单号")
    private String changeDispatcherCode;
	
	@Mapping
	@ApiModelProperty("改配单类型")
    private Integer changeDispatcherType;
	
	@Mapping
	@ApiModelProperty("改配单类型名称")
    private String changeDispatcherTypeName;


	private @Mapping  @ApiModelProperty("是否修改") Boolean isUpdate;
	private @Mapping  @ApiModelProperty("是否拆单") Boolean isDismantling;
	
	@Mapping
	private @ApiModelProperty("集团id") Integer groupId;
	
	@Mapping
	private @ApiModelProperty("app或是pc端手动开单") Integer pcOrApp; //1、pc端    2、app端
	
	@Mapping
	private @ApiModelProperty("app或是pc端手动开单-名称") String pcOrAppName; //pc手动开单    ，，app手动开单

	@Mapping
	private @ApiModelProperty("app端的模糊检索条件") String combinationStr;

	@Override
	public int compareTo(WaybillEsDTO o) {
		if(o.createTime.longValue() > this.createTime.longValue()) {
			return 1;
		}else if(o.createTime.longValue() < this.createTime.longValue()) {
			return -1;
		}
		return 0;
	}
}
