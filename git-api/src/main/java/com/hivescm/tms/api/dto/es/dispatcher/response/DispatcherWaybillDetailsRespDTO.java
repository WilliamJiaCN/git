package com.hivescm.tms.api.dto.es.dispatcher.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class DispatcherWaybillDetailsRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 派车单明细ID
	 */
	private @Mapping @ApiModelProperty("派车单明细ID") Long id;
	/**
	 * 运单ID
	 */
	private @Mapping @ApiModelProperty("运单ID") Long waybillId;

	/**
	 * 派车单ID
	 */
	private @Mapping @ApiModelProperty("派车单ID") Long dispatcherId;
	/**
	 * 公司Id
	 */
	private @Mapping @ApiModelProperty("公司Id") Long companyId;
	/**
	 * 运单号
	 */
	private @Mapping @ApiModelProperty("运单号，已作废") String waybillCode;

	/**
	 * 运单号
	 */
	private @Mapping @ApiModelProperty("运单号") String code;

	/**
	 * 订单运输的类型，包括：1销售订单、2销退单；自动接收（非必填）
	 */
	private @Mapping @ApiModelProperty("订单类型") Integer orderType;
	/**
	 * 是否为销退单:ture是 false否
	 */
	private @Mapping @ApiModelProperty("订单类型") Boolean isXTOrder;
	/**
	 * 客户单号
	 */
	private @Mapping @ApiModelProperty("客户单号（订单号）") String orderCode;

	/**
	 * 派车单明细状态
	 */
	private @Mapping @ApiModelProperty("派车单明细状态	") Integer status;

	/**
	 * 运单状态
	 */
	private @Mapping @ApiModelProperty("运单状态") Integer waybillStatus;

	/**
	 * 发布状态
	 */
	private @Mapping @ApiModelProperty("发布状态") Boolean releaseStatus;

	/**
	 * 发货人
	 */
	private @Mapping @ApiModelProperty("发货人") String invoiceUser;
	/**
	 * 发货人手机号码
	 */
	private @Mapping @ApiModelProperty("发货人手机号码") String invoiceMobleNo;
	/**
	 * 发货人详细地址
	 */
	private @Mapping @ApiModelProperty("发货人详细地址") String invoiceAddress;
	/**
	 * 收货人
	 */
	private @Mapping @ApiModelProperty("收货人") String receiptUser;
	/**
	 * 收货人手机号码
	 */
	private @Mapping @ApiModelProperty("收货人手机号码") String receiptConsignorMobleNo;
	/**
	 * 收货人详细地址
	 */
	private @Mapping @ApiModelProperty("收货人详细地址") String receiptAddress;
	/**
	 * 回单要求
	 */
	private @Mapping @ApiModelProperty("回单要求") String backType;
	/**
	 * 特殊要求
	 */
	private @Mapping @ApiModelProperty("特殊要求") String specialRequire;
	
	/**
	 * 特殊要求
	 */
	private @Mapping @ApiModelProperty("拒签原因") String refuseReason;
	
	/**
	 * 特殊要求
	 */
	private @Mapping @ApiModelProperty("是否装车") Boolean loaded;
	
	/**
	 * 总运费
	 */
	private @Mapping @ApiModelProperty("总运费") BigDecimal totalFee;
	
	private @Mapping @ApiModelProperty("付款方式") Integer payWay;
	/**
	 * 到付运费
	 */
	private @Mapping @ApiModelProperty("到付运费") String cod;
	/**
	 * 代收货款
	 */
	private @Mapping @ApiModelProperty("代收货款") BigDecimal collectPayment;
	/**
	 * 应收总金额
	 */
	private @Mapping @ApiModelProperty("应收总金额(到付运费+代收货款)") Double aggregateAmount;
	/**
	 * 发布时间
	 */
	private @Mapping @ApiModelProperty("发布时间") Long releaseTime;
	/**
	 * 是否整车
	 */
	private @Mapping @ApiModelProperty("是否整车(接单类型)(如果为true是整车,false拼车)") Boolean itruckLoad;
	/**
	 * 是否包裹
	 */
	private @Mapping @ApiModelProperty("是否包裹(如果是包裹true,false货物)") Boolean isPackageInfo;

	/**
	 * 预约提货时间
	 */
	private @Mapping @ApiModelProperty("预约提货时间") Long deliveryPickTime;
	/**
	 * 包裹信息
	 */
	private @Mapping @ApiModelProperty("包裹信息") List<DispatcherPackageInfoRespDTO> dispatcherPackageInfoDTO;
	/**
	 * 包裹号下的物品详细信息
	 */
	private @Mapping @ApiModelProperty("包裹号下的详细信息") List<DispatcherPackageDetailsRespDTO> dispatcherPackageDetailDTO;

	/**
	 * 签收时间
	 */
	@Mapping
	@ApiModelProperty("签收时间 ")
	private Long signTime;

	/**
	 * 运单状态
	 */
	private @Mapping @ApiModelProperty("签收方式") Integer signType;

	/**
	 * 运单状态
	 */
    @ApiModelProperty("收款方式(1=现金支付,2=二维码支付)")
    private @Mapping Integer settlementMethod;

    @Mapping
    @ApiModelProperty("销退单类型")
    private Integer returnType;

    @Mapping
    @ApiModelProperty("销退单类型名称")
    private String returnTypeName;
    
	@Mapping
	@ApiModelProperty("订单来源名称")
    private String orderSourceName;
	@Mapping
	@ApiModelProperty("订单来源")
    private Integer orderSource;


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
	/**
	 * 指定配送时间是上面三个拼接的
	 */
	@Mapping
	@ApiModelProperty("指定配送时间")
	private String appointDispatchTime;


	@Mapping
	@ApiModelProperty("门店名称")
	private String storeName;

	/**
	 * 是否加急
	 */
	@Mapping
	@ApiModelProperty("是否加急")
	private Boolean iemergency;

	/**
	 * 派车备注
	 */
	@Mapping
	@ApiModelProperty("派车备注")
	private String remark;

	@Mapping()
	@ApiModelProperty("运单备注")
	private String waybillRemark;
	@Mapping
	@ApiModelProperty("改配状态(1.改配审核中2.改配审核通过3.改配不通过)")
	private Integer changeSendStatus;

	@Mapping
	@ApiModelProperty("改配单类型")
	private Integer changeDispatcherType;

}
