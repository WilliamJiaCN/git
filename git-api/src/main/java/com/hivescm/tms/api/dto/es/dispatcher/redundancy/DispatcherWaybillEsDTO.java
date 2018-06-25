package com.hivescm.tms.api.dto.es.dispatcher.redundancy;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 派车单明细冗余运单属性
 *
 * @author 李洪春
 * @since 2017/8/17 11:32
 */
@Data
@ToString
public class DispatcherWaybillEsDTO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6497329597770823937L;

	/**
	 * 运单ID
	 */
	@ApiModelProperty("运单ID")
	private Long id;

	/**
	 * 发货网点ID
	 */
//	@Mapping("branchId","invoiceOrgId")
	@Mapping({"branchId","invoiceOrgId"})
	@ApiModelProperty("发货网点ID")
	private Integer invoiceOrgId;

	/**
	 * 发货网点名称
	 */
	@Mapping({"branchName","invoiceOrgName"})
	@ApiModelProperty("发货网点名称")
	private String invoiceOrgName;
	@Mapping
	@ApiModelProperty("订单来源名称")
    private String orderSourceName;
	@Mapping
	@ApiModelProperty("订单来源")
    private Integer orderSource;
	/**
	 * 目的地级别
	 */
	@Mapping
	@ApiModelProperty("目的地级别")
	private Integer destLevel;
	@ApiModelProperty("发货人身份证号")
	@Mapping
	private String invoiceIdentityCard;
	/**
	 * 目的地id
	 */
	@Mapping
	@ApiModelProperty("目的地id")
	private Long destId;
	/**
	 * 目的地名称
	 */
	@Mapping
	@ApiModelProperty("目的地名称")
	private String destName;
	/**
	 * 运输线路id
	 */
	@Mapping({"waybillLineId","lineId"})
	@ApiModelProperty("运输线路id")
	private Long lineId;
	/**
	 * 运输线路名称
	 */
	@Mapping({"waybillLineName","lineName"})
	@ApiModelProperty("运输线路名称")
	private String lineName;
	/**
	 * 到达网点id
	 */

	@Mapping
	@ApiModelProperty("到达网点id")
	private Integer destOrgId;

	/**
	 * 订单运输的类型，包括：销售订单、销退单；自动接收（非必填）
	 */
	@Mapping
	@ApiModelProperty("订单类型")
	private Integer orderType;

	/**
	 * 到达网点名称
	 */
	@Mapping
	@ApiModelProperty("到达网点名称")
	private String destOrgName;
	/**
	 * 总运费
	 */
	@Mapping("totalFee")
	@ApiModelProperty("总运费")
	private BigDecimal totalFee;
	/**
	 * 运单编码
	 */
	@Mapping
	@ApiModelProperty("运单编码")
	private String waybillCode;

	/**
	 * 客户单号
	 */
	@Mapping
	@ApiModelProperty("客户单号")
	private String orderCode;

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
	@Mapping
	@ApiModelProperty("最大提货数")
	private Integer maxDelivery;
	@Mapping
	@ApiModelProperty("声明价值")
	private BigDecimal collectVaule;

	/**
	 * 代收货款手续费
	 */
	private @Mapping @ApiModelProperty("代收货款手续费") BigDecimal deliveryCharge;

	/**
	 * 运单运输线路id
	 */
	@Mapping
	@ApiModelProperty("运单运输线路id")
	private Long waybillLineId;

	/**
	 * 运单运输线路名称
	 */

	/**
	 * 预约提货时间
	 */
	@Mapping
	@ApiModelProperty("预约提货时间")
	private Long deliveryPickTime;
	/**
	 * 预约送货时间
	 */
	@Mapping
	@ApiModelProperty("预约送货时间")
	private Long deliverySendTime;
	/**
	 * 商品名称 ,“/”间隔
	 */
	private @Mapping @ApiModelProperty("商品名称") String goodsName;


	@Mapping
	@ApiModelProperty("仓储服务商ID")
	private Long warehouseServerId;

	@Mapping
	@ApiModelProperty("仓储服务商名称")
	private String warehouseServerName;// 冗余

	@Mapping
	@ApiModelProperty("外部订单编号")
	private String externalOrderCode;
	
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

	/**
	 * 运力分配接单时自动赋值（非必填）
	 */
	@Mapping
	@ApiModelProperty("销退单类型名称")
	private String returnTypeName;

	/**
	 * 付款方式名称
	 */

	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;

	/**
	 * 派送方式名称
	 */
	@Mapping
	private @ApiModelProperty("配送方式名称") String distributionTypeName;

	/**
	 * 类型：统仓统配、统仓自配
	 */
	@Mapping
	@ApiModelProperty("仓配类型")
	private Integer warehouseDistributionType;

	/**
	 * 结算对象：仓储服务商、经销商
	 */
	@Mapping
	@ApiModelProperty("结算对象")
	private Integer settlementObject;

	/**
	 * 主订单编号
	 */
	private @Mapping @ApiModelProperty("主订单编号") String mainOrderCode;

	/**
	 * 汇总运单号下所有包裹号的箱数
	 */
	@Mapping
	@ApiModelProperty("总箱数")
	private Integer boxNum;
	
	@Mapping
	@ApiModelProperty("运单号")
	private String code;
	@Mapping
  	@ApiModelProperty("波次ID")
	private Long waveId;

	/**
	 * TMS自动接单时判断是否装车自动赋值（非必填）
	 */
	@Mapping
	@ApiModelProperty("是否装车")
	private Boolean loaded;

	/**
	 * 运力分配接单时自动赋值（非必填）
	 */
	@Mapping
	@ApiModelProperty("销退单类型")
	private Integer returnType;

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
	private Boolean truckOrdered;

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
	@ApiModelProperty("波次号")
	private String waveCode;

	/**
	 * 装货时间
	 */
	@Mapping
	@ApiModelProperty("装货时间")
	private Long freightTime;
	/**
	 * 发货人
	 */
	@Mapping
	@ApiModelProperty("发货人")
	private String invoiceUser;
	/**
	 * 发货单位
	 */
	@Mapping
	@ApiModelProperty("发货公司")
	private String invoiceCompany;

	/**
	 * 发货人微信号
	 */
	@Mapping
	@ApiModelProperty("发货人微信号	")
	private String invoiceWxNo;
	/**
	 * 发货人电话
	 */
	@Mapping
	@ApiModelProperty("发货人电话")
	private String invoiceTelNo;
	/**
	 * 发货人手机号码
	 */
	@Mapping
	@ApiModelProperty("发货人手机号码")
	private String invoiceMobleNo;
	/**
	 * 发货人国家ID
	 */
	@Mapping
	@ApiModelProperty("发货人国家ID")
	private String invoiceCountryId;
	/**
	 * 发货人国家名称
	 */
	@Mapping
	@ApiModelProperty("发货人国家名称")
	private String invoiceCountryName;
	/**
	 * 发货人省ID
	 */
	@Mapping
	@ApiModelProperty("发货人省ID")
	private String invoiceProvId;
	/**
	 * 发货人省名称
	 */
	@Mapping
	@ApiModelProperty("发货人省名称")
	private String invoiceProvName;
	/**
	 * 发货人城市ID
	 */
	@Mapping
	@ApiModelProperty("发货人城市ID")
	private String invoiceCityId;
	/**
	 * 发货人城市名称
	 */
	@Mapping
	@ApiModelProperty("发货人城市名称")
	private String invoiceCityName;
	/**
	 * 发货人地区ID
	 */
	@Mapping
	@ApiModelProperty("发货人地区ID")
	private String invoiceDistrictId;
	/**
	 * 发货人地区名称
	 */
	@Mapping
	@ApiModelProperty("发货人地区名称")
	private String invoiceDistrictName;
	/**
	 * 发货人街道ID
	 */
	@Mapping
	@ApiModelProperty("发货人街道ID")
	private String invoiceStreetId;
	/**
	 * 发货人街道名称
	 */
	@Mapping
	@ApiModelProperty("发货人街道名称")
	private String invoiceStreetName;
	/**
	 * 发货人详细地址
	 */
	@Mapping
	@ApiModelProperty("发货人详细地址")
	private String invoiceAddress;
	/**
	 * 收货方ID
	 */
	@Mapping
	@ApiModelProperty("收货方ID")
	private Integer receiptCustomerId;
	/**
	 * 收货人
	 */
	@Mapping
	@ApiModelProperty("收货人")
	private String receiptUser;
	/**
	 * 收货单位
	 */
	@Mapping
	@ApiModelProperty("收货单位")
	private String receiptCompany;
	/**
	 * 收货人身份证号码
	 */
	@Mapping
	@ApiModelProperty("收货人身份证号码")
	private String receiptIdentityCard;
	/**
	 * 收货人电话
	 */
	@Mapping
	@ApiModelProperty("收货人电话")
	private String receiptConsignorTelNo;
	/**
	 * 收货人手机号码
	 */
	@Mapping
	@ApiModelProperty("收货人手机号码")
	private String receiptConsignorMobleNo;
	/**
	 * 收货人国家ID
	 */
	@Mapping
	@ApiModelProperty("收货人国家ID")
	private String receiptCountryId;
	/**
	 * 收货人国家名称
	 */
	@Mapping
	@ApiModelProperty("收货人国家名称")
	private String receiptCountryName;
	/**
	 * 收货人省ID
	 */
	@Mapping
	@ApiModelProperty("收货人省ID")
	private String receiptProvId;
	/**
	 * 收货人省名称
	 */
	@Mapping
	@ApiModelProperty("收货人省名称	")
	private String receiptProvName;
	/**
	 * 收货人城市ID
	 */
	@Mapping
	@ApiModelProperty("收货人城市ID")
	private String receiptCityId;
	/**
	 * 收货人城市名称
	 */
	@Mapping
	@ApiModelProperty("收货人城市名称")
	private String receiptCityName;
	/**
	 * 收货人地区ID
	 */
	@Mapping
	@ApiModelProperty("收货人地区ID")
	private String receiptDistrictId;
	/**
	 * 收货人地区名称
	 */
	@Mapping
	@ApiModelProperty("收货人地区名称")
	private String receiptDistrictName;
	/**
	 * 收货人街道ID
	 */
	@Mapping
	@ApiModelProperty("收货人街道ID")
	private String receiptStreetId;
	/**
	 * 收货人街道名称
	 */
	@Mapping
	@ApiModelProperty("收货人街道名称")
	private String receiptStreetName;
	/**
	 * 收货人详细地址
	 */
	@Mapping
	@ApiModelProperty("收货人详细地址")
	private String receiptAddress;

	/**
	 * 业务日期
	 */
	@Mapping
	@ApiModelProperty("业务日期")
	private Long bussTime;

	/**
	 * 下单日期
	 */
	@Mapping
	@ApiModelProperty("下单日期")
	private Long orderDate;

	/**
	 * 接单时间
	 */
	@Mapping
	@ApiModelProperty("接单日期")
	private Long acceptDate;

	/**
	 * 代收货款
	 */
	@Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal collectPayment;

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
	 * 运单状态
	 */
	@Mapping("waybillStatus")
	@ApiModelProperty("运单状态")
	private Integer status;

	/**
	 * 声明价值
	 */
	@Mapping
	@ApiModelProperty("声明价值")
	private BigDecimal declaredValue;

	/**
	 * 配送方式
	 */
	@Mapping
	@ApiModelProperty("配送方式")
	private Integer distributionType;

	/**
	 * 特殊要求
	 */
	@Mapping
	@ApiModelProperty("特殊要求")
	private String specialRequire;

	/**
	 * 运单备注
	 */
	@Mapping("waybillRemark")
	@ApiModelProperty("备注")
	private String remark;

	/**
	 * 回单要求（名称）
	 */
	@Mapping
	private @ApiModelProperty("回单要求") String backType;
	
	@Mapping
	private @ApiModelProperty("回单要求") Integer backTypeValue;

	/**
	 * 回单数量
	 */
	@Mapping
	@ApiModelProperty("回单份数")
	private Integer backOrderNum;

	/**
	 * 回单编号
	 */
	@Mapping
	@ApiModelProperty("回单编号")
	private String backCode;

	/**
	 * 录单员ID
	 */
	@Mapping
	@ApiModelProperty("录单员ID")
	private Integer createUserId;

	/**
	 * 录单员姓名
	 */
	@Mapping
	@ApiModelProperty("录单员姓名")
	private String userName;

	/**
	 * 录单
	 */
	@Mapping({ "waybillCreateTime", "orderTime" })
	@ApiModelProperty("录单时间")
	private Long createTime;

	/**
	 * 付款方式
	 */
	@Mapping
	@ApiModelProperty("付款方式")
	private Integer payWay;

	/**
	 * 是否等通知
	 */
	@Mapping
	@ApiModelProperty("是否等通知")
	private Boolean iwaitNotice;

	/**
	 * 是否加急
	 */
	@Mapping
	@ApiModelProperty("是否加急")
	private Boolean iemergency;
	@Mapping
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;// 冗余

	@Mapping
	@ApiModelProperty("接收波次时间")
	private Long waveTime;

	/**
	 * 发货人是否为VIP
	 */
	private @Mapping @ApiModelProperty("是否VIP客户") Boolean vip;
	/**
	 * 指定配送时间为
	 */
	@Mapping
	@ApiModelProperty("指定日期")
	private String appointDate;
	@Mapping
	@ApiModelProperty("指定开始时间")
	private String appointStartTime;
	@Mapping
	@ApiModelProperty("指定结束时间")
	private String appointEndTime;
	
	@Mapping
	@ApiModelProperty("代收货款")
    private BigDecimal orderGoodsPayment;
	
	@Mapping()
	@ApiModelProperty("运单改配状态")
    private Integer changeDelivery;
	
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
//	@ApiModelProperty("改配状态")
//	private Integer changeDispatcherType;
	/**
	 * 包装名称 ,“/”间隔
	 */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
	@Mapping
	@ApiModelProperty("改配单类型")
    private Integer changeDispatcherType;
	private @Mapping  @ApiModelProperty("是否拆单") Boolean isDismantling;
	@Mapping
	@ApiModelProperty("是否为异常单")
	private  Boolean exception;
	private @Mapping  @ApiModelProperty("是否修改") Boolean isUpdate;
	/**
	 * 回单份数	
	 */
	private @Mapping  @ApiModelProperty("回单份数") Integer backNum;
}
