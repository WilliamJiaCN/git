package com.hivescm.tms.api.dto.es.dispatcher;

import com.hivescm.framework.elasticsearch.annotation.Cascade;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 派车单明细信息
 *
 * @author 李洪春
 * @since 2017/8/9 16:04
 */
@Data
@ToString
@Cascade(value = "deliverySign", fields = { "code", "receiptUser", "invoiceUser" })
public class DispatcherDetailEsDTO implements Serializable, Cloneable {

	@Override
	public DispatcherDetailEsDTO clone() {
		DispatcherDetailEsDTO order = null;
		try {
			order = (DispatcherDetailEsDTO) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return order;
	}

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Logger
	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 公司id
	 */
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;

	/**
	 * 运单ID
	 */
	@Logger
	@Mapping
	@ApiModelProperty("运单ID")
	private Long waybillId;

	/**
	 * 装货单ID
	 */
	@Mapping
	@ApiModelProperty("装货单ID")
	private Long shippingId;

	/**
	 * 派车单ID
	 */
	@Logger
	@Mapping
	@ApiModelProperty("派车单ID")
	private Long dispatcherId;

	/**
	 * 执行任务ID(数据字典)
	 */
	@Mapping
	@ApiModelProperty("执行任务ID(数据字典)")
	private Integer taskId;

	/**
	 * 派车备注
	 */
	@Mapping(value = "assignRemarks")
	@ApiModelProperty("派车备注")
	private String remark;

	@Mapping()
	@ApiModelProperty("运单备注")
	private String waybillRemark;
	@Mapping
	@ApiModelProperty("经销商ID")
	private Long dealerId;

	@Mapping
	@ApiModelProperty("经销商名称")
	private String dealerName;// 冗余
	/**
	 * 装车件数(派车单总件数)
	 */
	@Mapping("DispatcherDetailDO.packageNum")
	@ApiModelProperty("装车件数(派车单总件数)")
	private Integer packageNum;

	/**
	 * 装车重量(派车单总重量)
	 */
	@Mapping("DispatcherDetailDO.packageWeight")
	@ApiModelProperty("装车重量(派车单总重量)")
	private BigDecimal packageWeight;

	/**
	 * 装车体积(派车单总体积)
	 */
	@Mapping("DispatcherDetailDO.packageVolume")
	@ApiModelProperty("装车体积(派车单总体积)")
	private BigDecimal packageVolume;

	/**
	 * 总体积
	 */
	private @Mapping @ApiModelProperty("总体积") BigDecimal volume;
	/**
	 * 总重量
	 */
	private @Mapping @ApiModelProperty("总重量") BigDecimal weight;
	/**
	 * 总件数 (运单)
	 */

	private @Mapping @ApiModelProperty("总件数") Integer totalNum;

	@Mapping
	@ApiModelProperty("品种数")
	private Integer skuidCount;

	/**
	 * 分摊成本(四种分摊成本总合)
	 */
	@Mapping
	@ApiModelProperty("分摊成本(四种分摊成本总合)")
	private BigDecimal cost;

	/**
	 * 装货批次
	 */
	@Mapping
	@ApiModelProperty("装货批次")
	private String shippingCode;

	/**
	 * 装货成本
	 */
	@Mapping
	@ApiModelProperty("装货成本")
	private BigDecimal shippingCost;

	/**
	 * 装货时间
	 */
	@Mapping("freightTime")
	@ApiModelProperty("装货时间")
	private Long shippingTime;

	/**
	 * 卸货时间
	 */
	@Mapping
	@ApiModelProperty("卸货时间")
	private Long dischargedTime;

	/**
	 * 卸货批次
	 */
	@ApiModelProperty("卸货批次")
	private String dischargedCode;

	/**
	 * 卸货成本
	 */
	@Mapping
	@ApiModelProperty("卸货成本")
	private BigDecimal dischargedCost;

	/**
	 * 签收时间
	 */
	@Mapping
	@ApiModelProperty("签收时间 ")
	private Long signTime;

	@Mapping
	@ApiModelProperty("配送时间")
	private Long deliveryTime;

	@Mapping
	@ApiModelProperty("明细状态（1已完成 2未完成）")
	private Integer detailStatus;
	@Mapping
	@ApiModelProperty("商户ID")
	private Long merchantId;
	@Mapping
	@ApiModelProperty("商户名称")
	private String merchantName;// 冗余

	@Mapping()
	@ApiModelProperty("商户全局客户ID") // 订单下来
	private Long merchandGlobalId;
	@Mapping()
	@ApiModelProperty("商户全局客户名称") // 订单下来
	private String merchandGlobalName;

	@Mapping()
	@ApiModelProperty("商户集团客户ID")
	private Long merchandGroupId;
	@Mapping()
	@ApiModelProperty("商户集团客户名称")
	private String merchandGroupName;

	@Mapping()
	@ApiModelProperty("仓储服务商全局客户ID") // 订单下来
	private Long warehouseServerGlobalId;
	@Mapping()
	@ApiModelProperty("仓储服务商全局客户名称") // 订单下来
	private String warehouseServerGlobalName;
	@Mapping()
	@ApiModelProperty("仓储服务商集团客户ID")
	private Long warehouseServerGroupId;
	@Mapping()
	@ApiModelProperty("仓储服务商集团客户名称")
	private String warehouseServerGroupName;
	@Mapping
	@ApiModelProperty("仓储服务商ID")
	private Long warehouseServerId;

	@Mapping
	@ApiModelProperty("仓储服务商名称")
	private String warehouseServerName;// 冗余
	@Mapping()
	@ApiModelProperty("经销商全局客户ID") // 订单下来
	private Long dealerGlobalId;
	@Mapping()
	@ApiModelProperty("经销商全局客户名称") // 订单下来
	private String dealerGlobalName;

	@Mapping()
	@ApiModelProperty("经销商集团客户ID")
	private Long dealerGroupId;
	@Mapping()
	@ApiModelProperty("经销商集团客户名称")
	private String dealerGroupName;

	@Mapping()
	@ApiModelProperty("承运商全局客户ID") // 通过承运商Id(承运商集团Id)获取全局客户Id
	private Long carrierGlobalId;
	@Mapping()
	@ApiModelProperty("承运商全局客户名称") // 通过承运商Id(承运商集团Id)获取全局客户名称
	private String carrierGlobalName;
	@Mapping
	@ApiModelProperty("最大提货数")
	private Integer maxDelivery;

	@Mapping
	@ApiModelProperty("门店ID")
	private Long storeId;

	@Mapping
	@ApiModelProperty("门店名称")
	private String storeName;
	/**
	 * 实际运费
	 */
	@Mapping({ "freight", "totalFee" })
	@ApiModelProperty("总运费")
	private BigDecimal totalFee;

	// @Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal collectPayment;
	@Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal orderGoodsPayment;
	// @Mapping
	@ApiModelProperty("声明价值")
	private BigDecimal collectVaule;

	/**
	 * 代收货款手续费
	 */
	private @Mapping @ApiModelProperty("代收货款手续费") BigDecimal deliveryCharge;

	/**
	 * 产值
	 */
	@Mapping
	@ApiModelProperty("产值")
	private BigDecimal outputValue;

	/**
	 * 业务费
	 */
	@Mapping
	@ApiModelProperty("业务费")
	private BigDecimal busFee;

	/**
	 * 线路顺序
	 */
	@Mapping
	@ApiModelProperty("线路顺序")
	private Integer lineSort;

	/**
	 * 运输线路ID
	 */
	@Mapping
	@ApiModelProperty("运输线路ID")
	private Integer lineId;

	/**
	 * 派车单明细状态
	 */
	@Mapping
	@ApiModelProperty("派车单明细状态")
	private Integer status;

	/**
	 * 运单是否拆单
	 */
	@Mapping
	@ApiModelProperty("运单是否拆单")
	private Boolean waybillSplit;

	/**
	 * 签收验证码发送次数
	 */
	@Mapping
	@ApiModelProperty("签收验证码发送次数")
	private Integer signCodeSendTimes;

	/**
	 * 签收验证码
	 */
	@Mapping
	@ApiModelProperty("签收验证码")
	private String signCode;

	/**
	 * 创建人
	 */
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;

	@Mapping
	@ApiModelProperty("创建人")
	private String createUserName;

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

	@ApiModelProperty("修改人姓名")
	private String updateUserName;

	/*********************************************** 冗余派车单信息 *******************************/
	/**
	 * 司机ID
	 *
	 * @{link com.hivescm.tms.api.dto.base.BaseDriverDTO#id}
	 */
	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;
	/**
	 * 司机姓名
	 */
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	/**
	 * 司机姓名
	 */
	@Mapping
	@ApiModelProperty("司机手机号码")
	private String phoneNo;

	/**
	 * 订单运输的类型，包括：销售订单、销退单；自动接收（非必填）
	 */
	@Mapping
	@ApiModelProperty("订单类型")
	private Integer orderType;
	/**
	 * 发车时间
	 */
	@Mapping
	@ApiModelProperty("发车时间")
	private Long dispatcherTime;
	/**
	 * 特殊要求
	 */
	private @Mapping @ApiModelProperty("特殊要求") String specialRequire;
	/**
	 * 派车批次
	 */
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;
	/**
	 * 商品名称 ,“/”间隔
	 */
	private @Mapping @ApiModelProperty("商品名称") String goodsName;

	@Mapping
	@ApiModelProperty("总箱数")
	private Integer boxNum;
	/**
	 * 派车网点ID
	 */
	@Mapping
	@ApiModelProperty("派车网点ID")
	private Integer branchId;
	/**
	 * 派车网点名称
	 */
	@Mapping
	@ApiModelProperty("派车网点名称")
	private String branchName;

	@Mapping
	@ApiModelProperty("订单来源名称")
	private String orderSourceName;
	@Mapping
	@ApiModelProperty("订单来源")
	private Integer orderSource;

	/**
	 * 车型名称
	 */
	@Mapping
	@ApiModelProperty("车型名称")
	private Integer vehicleModelName;

	@Mapping
	@ApiModelProperty("执行任务名称")
	private String taskName;
	/**
	 * 取消发布时间
	 */
	/**
	 * 发布时间
	 */
	@Mapping
	@ApiModelProperty("发布时间")
	private Long releaseTime;
	@Mapping
	@ApiModelProperty("取消发布时间")
	private Long cancelReleaseTime;

	/**
	 * 取消发布人
	 */
	@Mapping
	@ApiModelProperty("取消发布人")
	private Integer cancelReleaseUser;

	/**
	 * 取消发布人姓名
	 */
	@Mapping
	@ApiModelProperty("取消发布人姓名")
	private String cancelReleaseUserName;

	/**
	 * 发布状态
	 */
	@Mapping
	@ApiModelProperty("发布状态")
	private Boolean releaseStatus;

	@Mapping
	@ApiModelProperty(value = "撤回类型", notes = "取该枚举类DistributionCancelBillTypeEnum")
	private Integer releaseType;

	@Mapping
	@ApiModelProperty(value = "撤回类型名称", notes = "取该枚举类DistributionCancelBillTypeEnum")
	private Integer releaseTypeName;

	@ApiModelProperty("车辆ID")
	private Integer vehicleId;
	@ApiModelProperty("车牌号码")
	private String vehicleNo;

	@ApiModelProperty("承运商ID")
	private Integer carrierId;
	@ApiModelProperty("承运商名称")
	private String carrierName;

	@Mapping
	@ApiModelProperty("配送时间")
	private Long sendTime;
	/************************************************ 冗余签收单信息 *******************************/
	@Mapping
	@ApiModelProperty("签收单id")
	private Long signId;
	@Mapping
	@ApiModelProperty("签收批次号")
	private String signBatchNumber;

	@Mapping
	@ApiModelProperty("签收类型")
	private Integer signType;
	/**
	 * 签收状态（签收单中的状态）
	 */
	private @Mapping @ApiModelProperty("签收状态") Integer signStatus;

	/**
	 * 签收状态名称（签收单中的状态）
	 */
	private @Mapping @ApiModelProperty("签收状态名称") String signStatusName;

	/************************************************ 冗余运单信息 *******************************/

	/**
	 * 发货网点ID
	 */
	@Mapping
	@ApiModelProperty("发货网点ID")
	private Integer invoiceOrgId;

	/**
	 * 发货网点名称
	 */
	@Mapping
	@ApiModelProperty("发货网点名称")
	private String invoiceOrgName;
	/**
	 * 目的地级别
	 */
	@Mapping
	@ApiModelProperty("目的地级别")
	private Integer destLevel;
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
	 * 运单运输线路id
	 */
	@Mapping
	@ApiModelProperty("运单运输线路id")
	private Long waybillLineId;

	/**
	 * 运单运输线路名称
	 */
	@Mapping
	@ApiModelProperty("运单运输线路名称")
	private String lineName;
	/**
	 * 到达网点id
	 */
	@Mapping
	@ApiModelProperty("到达网点id")
	private Integer destOrgId;

	/**
	 * 到达网点名称
	 */
	@Mapping
	@ApiModelProperty("到达网点名称")
	private String destOrgName;

	/**
	 * 运单号
	 */
	@Mapping
	@ApiModelProperty("运单号")
	private String code;

	/**
	 * 客户单号
	 */
	@Mapping
	@ApiModelProperty("客户单号（订单号）")
	private String orderCode;

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
	@ApiModelProperty("发货人身份证号")
	@Mapping
	private String invoiceIdentityCard;
	/**
	 * 收货 方ID
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
	@ApiModelProperty("收货公司")
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
	 * 运单状态
	 */
	@Mapping
	@ApiModelProperty("运单状态")
	private Integer waybillStatus;

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
	@ApiModelProperty("回单数量")
	private Integer backOrderNum;
	
	/**
	 * 回单份数	
	 */
	private @Mapping  @ApiModelProperty("回单份数") Integer backNum;

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
	 * 录单时间
	 */
	@Mapping
	@ApiModelProperty("录单时间")
	private Long waybillCreateTime;

	@Mapping
	@ApiModelProperty("下单时间")
	private Long orderTime;
	@Mapping
	@ApiModelProperty("作废人时间")
	private Long discardTime;
	/**
	 * 付款方式
	 */
	@Mapping
	@ApiModelProperty("付款方式")
	private Integer payWay;

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
	 * TMS自动接单时判断是否装车自动赋值（非必填）
	 */
	@Mapping
	@ApiModelProperty("是否装车")
	private Boolean loaded;

	/**
	 * 付款方式名称
	 */

	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;

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

	/**
	 * 发货人是否为Vip
	 */
	@Mapping
	@ApiModelProperty("是否VIP客户")
	private Boolean vip;

	@Mapping
	@ApiModelProperty("仓库ID")
	private Long warehouseId;

	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;// 冗余

	@Mapping
	@ApiModelProperty("波次号")
	private String waveCode;

	@Mapping
	@ApiModelProperty("波次ID")
	private Long waveId;

	@Mapping
	@ApiModelProperty("接收波次时间")
	private Long waveTime;

	@Mapping
	@ApiModelProperty("总订单号（原订单号）（外部订单编号）")
	private String externalOrderCode;

	/**
	 * 主订单编号
	 */
	private @Mapping @ApiModelProperty("主订单编号") String mainOrderCode;

	/**
	 * 结算对象：仓储服务商、经销商
	 */
	@Mapping
	@ApiModelProperty("结算对象集团客户Id")
	private Integer settlementObjectGroupId;
	/**
	 * 包装名称 ,“/”间隔
	 */
	private @Mapping @ApiModelProperty("包装名称") String packingName;
	/**
	 * 结算对象：仓储服务商、经销商
	 */
	@Mapping
	@ApiModelProperty("结算对象集团客户名称")
	private String settlementObjectGroupName;
	@Mapping()
	@ApiModelProperty("运单改配状态")
	private Integer changeDelivery;
	public List<DispatcherGoodsEsDTO> dispatcherGoodsEsDTOList;

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

	/**
	 * true为开单直送
	 */
	@Mapping
	@ApiModelProperty("是否返回入库 暂时不用")
	private Boolean istorage;
	@Mapping
	@ApiModelProperty("是否返回入库")
	private Boolean storage;
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
	@Mapping("isDelete")
	@ApiModelProperty("是否删除")
	private Boolean idelete;

	@Mapping
	@ApiModelProperty("改配状态")
	private Integer changeSendStatus;
	@Mapping
	@ApiModelProperty("改配单类型")
	private Integer changeDispatcherType;
	private @Mapping @ApiModelProperty("是否拆单") Boolean isDismantling;
	@Mapping
	@ApiModelProperty("是否为异常单")
	private  Boolean exception;
	private @Mapping  @ApiModelProperty("是否修改") Boolean isUpdate;
	
	private @Mapping  @ApiModelProperty("是否卸货(装卸单更新赋值)") Boolean  unLoad;
	
	private @Mapping  @ApiModelProperty("签收关键字搜索") String  keyword;
	
}
