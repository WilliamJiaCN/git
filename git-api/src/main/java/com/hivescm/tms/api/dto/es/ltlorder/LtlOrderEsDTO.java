package com.hivescm.tms.api.dto.es.ltlorder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 订单主表
 * 
 * @author wenxiong.jia
 * @date 2018/4/8
 */
@Data
@ToString
public class LtlOrderEsDTO implements Serializable {
	private static final long serialVersionUID = -6757766739857855590L;

	@ApiModelProperty("商品列表")
	private List<LtlOrderGoodsEsDTO> goods;

	/**
	 * 公共属性
	 */
	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	@Mapping
	@ApiModelProperty("公司")
	private String companyName;
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;

	/**
	 * 订单主表信息
	 */
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Logger
	@Mapping
	@ApiModelProperty("订单编号")
	private String orderCode;
	@Mapping
	@ApiModelProperty("运单号")
	private String code;
	@Mapping
	@ApiModelProperty("货主ID")
	private Integer userId;
	@Mapping
	@ApiModelProperty("订单来源名称")
	private String orderSourceName;
	@Mapping
	@ApiModelProperty("订单来源")
	private Integer orderSource;
	@Mapping
	@ApiModelProperty("订单类型")
	private Integer orderType;
	@Mapping
	@ApiModelProperty("订单类型名称")
	private String orderTypeName;
	@Mapping
	@ApiModelProperty("运单状态")
	private Integer waybillStatus;
	@Mapping
	@ApiModelProperty("订单状态名称")
	private String statusName;
	@Mapping
	@ApiModelProperty("订单状态")
	private Integer status;
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	@Mapping
	@ApiModelProperty("手机号码")
	private String phoneNo;
	@Mapping
	@ApiModelProperty("车牌号码")
	private String vehicleNo;
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;
	@Mapping
	@ApiModelProperty("提货费")
	private BigDecimal pickupFee;
	@Mapping
	@ApiModelProperty("送货费")
	private BigDecimal deliveryFee;
	@Mapping
	@ApiModelProperty("预计运费")
	private BigDecimal estimateFee;
	@Mapping
	@ApiModelProperty("声明价值")
	private BigDecimal declareValue;
	@Mapping
	@ApiModelProperty("接收网点ID")
	private Integer receiveNetworkId;
	@Mapping
	@ApiModelProperty("接收网点名称")
	private String receiveNetworkName;
	@Mapping
	@ApiModelProperty("发货网点ID")
	private Integer deliveryNetworkId;
	@Mapping
	@ApiModelProperty("发货网点名称")
	private String deliveryNetworkName;
	@Mapping
	@ApiModelProperty("经度")
	private String lngs;
	@Mapping
	@ApiModelProperty("纬度")
	private String lats;
	@Mapping
	@ApiModelProperty("发货方式")
	private Integer deliveryType;
	@Mapping
	@ApiModelProperty("发货方式名称")
	private String deliveryTypeName;
	@Mapping
	@ApiModelProperty("预约提货时间")
	private Long appointDeliveryTime;
	@Mapping
	@ApiModelProperty("业务员ID")
	private Integer salesmanId;
	@Mapping
	@ApiModelProperty("业务员姓名")
	private String salesmanName;
	@Mapping
	@ApiModelProperty("回单要求")
	private Integer receiptType;
	@Mapping
	@ApiModelProperty("回单要求名称")
	private String receiptTypeName;
	@Mapping
	@ApiModelProperty("派送方式 ")
	private Integer sendType;
	@Mapping
	@ApiModelProperty("派送方式名称 ")
	private String sendTypeName;
	@Mapping
	@ApiModelProperty("接单人")
	private Integer receiveUser;
	@Mapping
	@ApiModelProperty("接单人姓名")
	private String receiveUserName;
	@Mapping
	@ApiModelProperty("接单时间")
	private Long receiveTime;
	@Mapping
	@ApiModelProperty("取消人")
	private Integer cancelUser;
	@Mapping
	@ApiModelProperty("取消人姓名")
	private String cancelUserName;
	@Mapping
	@ApiModelProperty("取消时间")
	private Long cancelTime;
	@Mapping
	@ApiModelProperty("取消原因")
	private String cancelReason;
	@Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal goodsPayment;
	@Mapping
	@ApiModelProperty("备注")
	private String remark;
	@Mapping
	@ApiModelProperty("是否加急")
	private Boolean isUrgent;
	@Mapping
	@ApiModelProperty("支付方式")
	private Integer paymentType;
	@Mapping
	@ApiModelProperty("支付方式名称")
	private String paymentTypeName;
	@Mapping
	@ApiModelProperty("是否等通知放货")
	private Boolean isNotice;

	// 商品冗余信息
	@ApiModelProperty("商品名称") // 冗余商品表名称，以"/"间隔
	private String goodsNames;
	@ApiModelProperty("商品包装") // 冗余商品表包装，以"/"间隔
	private String packing;
	@ApiModelProperty("总数")
	private Integer totalCount;// 冗余商品表总数
	@ApiModelProperty("总重量")
	private BigDecimal totalWeight;// 冗余商品表总重量
	@ApiModelProperty("总体积")
	private BigDecimal totalVolume;// 冗余商品表总体积

	/**
	 * 发货人
	 */
	@Mapping
	@ApiModelProperty("发货人主键")
	private Long invoiceId;
	@Mapping
	@ApiModelProperty("发货公司ID")
	private Long invoiceCompanyId;
	@Mapping
	@ApiModelProperty("发货人公司名称")
	private String invoiceCompanyName;
	@Mapping
	@ApiModelProperty("发货人ID")
	private Integer invoiceUserId;
	@Mapping
	@ApiModelProperty("发货人姓名")
	private String invoiceUserName;
	@Mapping
	@ApiModelProperty("发货人手机号")
	private String invoiceUserMobile;
	@Mapping
	@ApiModelProperty("发货人电话号")
	private String invoiceUserTel;
	@Mapping
	@ApiModelProperty("发货人省ID")
	private String invoiceUserProvinceId;
	@Mapping
	@ApiModelProperty("发货人省名称")
	private String invoiceUserProvinceName;
	@Mapping
	@ApiModelProperty("发货人市ID")
	private String invoiceUserCityId;
	@Mapping
	@ApiModelProperty("发货人省名称")
	private String invoiceUserCityName;
	@Mapping
	@ApiModelProperty("发货人区ID")
	private String invoiceUserDistrictId;
	@Mapping
	@ApiModelProperty("发货人区名称")
	private String invoiceUserDistrictName;
	@Mapping
	@ApiModelProperty("发货人街道ID")
	private String invoiceUserStreetId;
	@Mapping
	@ApiModelProperty("发货人街道名称")
	private String invoiceUserStreetName;
	@Mapping
	@ApiModelProperty("发货地址")
	private String invoiceAddress;

	/**
	 * 收货人
	 */
	@Mapping
	@ApiModelProperty("收货人主键")
	private Long receiptId;
	@Mapping
	@ApiModelProperty("收货公司ID")
	private Long receiptCompanyId;
	@Mapping
	@ApiModelProperty("收货公司名称")
	private String receiptCompanyName;
	@Mapping
	@ApiModelProperty("收货人ID")
	private Integer receiptUserId;
	@Mapping
	@ApiModelProperty("收货人姓名")
	private String receiptUserName;
	@Mapping
	@ApiModelProperty("收货人手机号")
	private String receiptUserMobile;
	@Mapping
	@ApiModelProperty("收货人电话号")
	private String receiptUserTel;
	@Mapping
	@ApiModelProperty("收货人省ID")
	private String receiptUserProvinceId;
	@Mapping
	@ApiModelProperty("收货人省名称")
	private String receiptUserProvinceName;
	@Mapping
	@ApiModelProperty("收货人市ID")
	private String receiptUserCityId;
	@Mapping
	@ApiModelProperty("收货人市名称")
	private String receiptUserCityName;
	@Mapping
	@ApiModelProperty("收货人区ID")
	private String receiptUserDistrictId;
	@Mapping
	@ApiModelProperty("收货人区名称")
	private String receiptUserDistrictName;
	@Mapping
	@ApiModelProperty("收货人街道ID")
	private String receiptUserStreetId;
	@Mapping
	@ApiModelProperty("收货人街道名称")
	private String receiptUserStreetName;
	@Mapping
	@ApiModelProperty("收货地址")
	private String receiptAddress;

	/**
	 * 为前端回显目的地加的字段
	 */
	@Mapping
	@ApiModelProperty("目的地省id")
	private Long destProvId;

	/**
	 * 为前端回显目的地加的字段
	 */
	@Mapping
	@ApiModelProperty("目的地省名称")
	private String destProvName;

	@Mapping({ "LtlOrderEsDTO.endCityId" })
	@ApiModelProperty("目的城市ID")
	private String endCityId;

	@Mapping({ "LtlOrderEsDTO.endCityName" })
	@ApiModelProperty("目的城市名称")
	private String endCityName;

	/**
	 * 为前端回显目的地加的字段
	 */
	@Mapping
	@ApiModelProperty("目的地区id")
	private Long destDistrictId;

	/**
	 * 为前端回显目的地加的字段
	 */
	@Mapping
	@ApiModelProperty("目的地区名称")
	private String destDistrictName;
}