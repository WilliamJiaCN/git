package com.hivescm.tms.api.dto.print.dispatcher;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 派车单明细打印信息
 * 
 * @author lifan
 *
 *         2017年11月20日
 *
 */
@Data
@ToString
public class DispatcherDetailPrintDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 公司id
	 */
	@ApiModelProperty("公司id")
	private Long companyId;

	/**
	 * 运单ID
	 */
	@ApiModelProperty("运单ID")
	private Long waybillId;

	/**
	 * 装货单ID
	 */
	@ApiModelProperty("装货单ID")
	private Long shippingId;

	/**
	 * 派车单ID
	 */
	@ApiModelProperty("派车单ID")
	private Long dispatcherId;

	/**
	 * 运单号
	 */
	@ApiModelProperty("运单号")
	private String code;

	/**
	 * 目的地名称
	 */
	@ApiModelProperty("目的地名称")
	private String destName;

	/**
	 * 录单时间
	 */
	@ApiModelProperty("录单时间")
	private Long waybillCreateTime;

	/**
	 * 录单员ID
	 */
	@ApiModelProperty("录单员ID")
	private Integer createUserId;

	/**
	 * 录单员姓名
	 */
	@ApiModelProperty("录单员姓名")
	private String userName;

	/**
	 * 发货网点ID
	 */
	@ApiModelProperty("发货网点ID")
	private Integer invoiceOrgId;

	/**
	 * 发货网点名称
	 */
	@ApiModelProperty("发货网点名称")
	private String invoiceOrgName;

	/**
	 * 到达网点id
	 */
	@ApiModelProperty("到达网点id")
	private Integer destOrgId;

	/**
	 * 到达网点名称
	 */
	@ApiModelProperty("到达网点名称")
	private String destOrgName;

	/**
	 * "商品名称"
	 */
	@ApiModelProperty("商品名称")
	private String goodsName;

	/**
	 * 总件数
	 */
	@ApiModelProperty("总件数")
	private Integer totalNum;

	/**
	 * 总体积
	 */
	@ApiModelProperty("总体积")
	private BigDecimal volume;
	/**
	 * 总重量
	 */
	@ApiModelProperty("总重量")
	private BigDecimal weight;

	/**
	 * 分摊成本(四种分摊成本总合)
	 */
	@ApiModelProperty("分摊成本(四种分摊成本总合)")
	private BigDecimal cost;

	/**
	 * 派车备注
	 */
	@ApiModelProperty("派车备注")
	private String remark;

	/**
	 * 预约提货时间
	 */
	@ApiModelProperty("预约提货时间")
	private Long deliveryPickTime;
	/**
	 * 预约送货时间
	 */
	@ApiModelProperty("预约送货时间")
	private Long deliverySendTime;

	/**
	 * 发货人
	 */
	@ApiModelProperty("发货人")
	private String invoiceUser;
	/**
	 * 发货单位
	 */
	@ApiModelProperty("发货公司")
	private String invoiceCompany;

	/**
	 * 发货人微信号
	 */
	@ApiModelProperty("发货人微信号")
	private String invoiceWxNo;
	/**
	 * 发货人电话
	 */
	@ApiModelProperty("发货人电话")
	private String invoiceTelNo;
	/**
	 * 发货人手机号码
	 */
	@ApiModelProperty("发货人手机号码")
	private String invoiceMobleNo;
	/**
	 * 发货人国家ID
	 */
	@ApiModelProperty("发货人国家ID")
	private String invoiceCountryId;
	/**
	 * 发货人国家名称
	 */
	@ApiModelProperty("发货人国家名称")
	private String invoiceCountryName;
	/**
	 * 发货人省ID
	 */
	@ApiModelProperty("发货人省ID")
	private String invoiceProvId;
	/**
	 * 发货人省名称
	 */
	@ApiModelProperty("发货人省名称")
	private String invoiceProvName;
	/**
	 * 发货人城市ID
	 */
	@ApiModelProperty("发货人城市ID")
	private String invoiceCityId;
	/**
	 * 发货人城市名称
	 */
	@ApiModelProperty("发货人城市名称")
	private String invoiceCityName;
	/**
	 * 发货人地区ID
	 */
	@ApiModelProperty("发货人地区ID")
	private String invoiceDistrictId;
	/**
	 * 发货人地区名称
	 */
	@ApiModelProperty("发货人地区名称")
	private String invoiceDistrictName;
	/**
	 * 发货人街道ID
	 */
	@ApiModelProperty("发货人街道ID")
	private String invoiceStreetId;
	/**
	 * 发货人街道名称
	 */
	@ApiModelProperty("发货人街道名称")
	private String invoiceStreetName;
	/**
	 * 发货人详细地址
	 */
	@ApiModelProperty("发货人详细地址")
	private String invoiceAddress;
	/**
	 * 收货方ID
	 */
	@ApiModelProperty("收货方ID")
	private Integer receiptCustomerId;
	/**
	 * 收货人
	 */
	@ApiModelProperty("收货人")
	private String receiptUser;
	/**
	 * 收货单位
	 */
	@ApiModelProperty("收货公司")
	private String receiptCompany;
	/**
	 * 收货人身份证号码
	 */
	@ApiModelProperty("收货人身份证号码")
	private String receiptIdentityCard;
	/**
	 * 收货人电话
	 */
	@ApiModelProperty("收货人电话")
	private String receiptConsignorTelNo;
	/**
	 * 收货人手机号码
	 */
	@ApiModelProperty("收货人手机号码")
	private String receiptConsignorMobleNo;
	/**
	 * 收货人国家ID
	 */
	@ApiModelProperty("收货人国家ID")
	private String receiptCountryId;
	/**
	 * 收货人国家名称
	 */
	@ApiModelProperty("收货人国家名称")
	private String receiptCountryName;
	/**
	 * 收货人省ID
	 */
	@ApiModelProperty("收货人省ID")
	private String receiptProvId;
	/**
	 * 收货人省名称
	 */
	@ApiModelProperty("收货人省名称	")
	private String receiptProvName;
	/**
	 * 收货人城市ID
	 */
	@ApiModelProperty("收货人城市ID")
	private String receiptCityId;
	/**
	 * 收货人城市名称
	 */
	@ApiModelProperty("收货人城市名称")
	private String receiptCityName;
	/**
	 * 收货人地区ID
	 */
	@ApiModelProperty("收货人地区ID")
	private String receiptDistrictId;
	/**
	 * 收货人地区名称
	 */
	@ApiModelProperty("收货人地区名称")
	private String receiptDistrictName;
	/**
	 * 收货人街道ID
	 */
	@ApiModelProperty("收货人街道ID")
	private String receiptStreetId;
	/**
	 * 收货人街道名称
	 */
	@ApiModelProperty("收货人街道名称")
	private String receiptStreetName;
	/**
	 * 收货人详细地址
	 */
	@ApiModelProperty("收货人详细地址")
	private String receiptAddress;

	/**
	 * 回单要求
	 */
	@ApiModelProperty("回单要求")
	private String backType;

	/**
	 * 回单数量
	 */
	@ApiModelProperty("回单份数")
	private Integer backOrderNum;

	/**
	 * 回单编号
	 */
	@ApiModelProperty("回单编号")
	private String backCode;

	/**
	 * 付款方式
	 */
	@ApiModelProperty("付款方式")
	private Integer payWay;

	/**
	 * 付款方式名称
	 */
	@ApiModelProperty("付款方式名称")
	private String payWayName;

	/**
	 * 实际运费
	 */
	@ApiModelProperty("总运费")
	private BigDecimal freight;

	@ApiModelProperty("代收货款")
	private String collectPaymentFee;

	/**
	 * 业务费
	 */
	@ApiModelProperty("业务费")
	private BigDecimal busFee;

	/**
	 * 是否等通知
	 */
	@ApiModelProperty("是否等通知")
	private Boolean iwaitNotice;

	/**
	 * 是否等通知名称
	 */
	@ApiModelProperty("是否等通知名称")
	private String iwaitNoticeName;

	/**
	 * 是否加急
	 */
	@ApiModelProperty("是否加急名称")
	private Boolean iemergency;

	/**
	 * 是否加急
	 */
	@ApiModelProperty("是否加急名称")
	private String iemergencyName;

	/********************************************* 所需的运单字段 *********************************************/
	/**
	 * 客户单号
	 */
	private @ApiModelProperty("客户单号") String orderCode;

	/**
	 * 是否整车
	 */
	private @ApiModelProperty("是否整车") Boolean itruckLoad;

	/**
	 * 是否整车名称
	 */
	private @ApiModelProperty("是否整车名称") String itruckLoadName;

	/**
	 * 备注
	 */
	private @ApiModelProperty("备注") String waybillRemark;

	/**
	 * 配送方式
	 */
	private @ApiModelProperty("配送方式") Integer distributionType;

	/**
	 * 配送方式名称
	 */
	private @ApiModelProperty("配送方式名称") String distributionTypeName;

	/**
	 * 是否开发票
	 */
	private @ApiModelProperty("是否开发票") Boolean ibill;
	/**
	 * 是否开发票名称
	 */
	private @ApiModelProperty("是否开发票名称") String ibillName;

	/*************************************** 需要在运单费用中查询的fee ***********************************/

	/**
	 * 送货费
	 */
	@ApiModelProperty("送货费")
	private String deliveryFee;

	/**
	 * 上楼费
	 */
	@ApiModelProperty("上楼费")
	private String upStairsFee;

	/**
	 * 声明价值
	 */
	@ApiModelProperty("声明价值")
	private String declearValueFee;

	@ApiModelProperty("总箱数")
	private Integer boxNum;

	@ApiModelProperty("线路顺序")
	private Integer lineSort;
	@Mapping
	@ApiModelProperty("执行任务名称")
	private String taskName;
	private @Mapping @ApiModelProperty("包装名称") String packingName;
	/**
	 * 装车件数(派车单总件数)
	 */
	@Mapping()
	@ApiModelProperty("装车件数(派车单总件数)")
	private Integer packageNum;

	/**
	 * 装车重量(派车单总重量)
	 */
	@Mapping()
	@ApiModelProperty("装车重量(派车单总重量)")
	private BigDecimal packageWeight;

	/**
	 * 装车体积(派车单总体积)
	 */
	@Mapping()
	@ApiModelProperty("装车体积(派车单总体积)")
	private BigDecimal packageVolume;

	@ApiModelProperty("发货人身份证号")
	@Mapping
	private String invoiceIdentityCard;
}
