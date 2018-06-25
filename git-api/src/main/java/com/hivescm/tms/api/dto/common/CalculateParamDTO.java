package com.hivescm.tms.api.dto.common;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@ToString
@Data
public class CalculateParamDTO {

	/**
	 * 客户类型（运单计算运费值为 1）		
	 */
	private Integer targetType; //客户类型
	
	private Integer orgId;//3PL承运商业务单元ID
	
	private Integer settlementObjectGroupId;//客户对象ID
	
    private @ApiModelProperty("结算对象集团客户名称") String settlementObjectGroupName;
    
	private Long validDate;//业务日期
	/**
	 * 费项ID 销售运费值为100016
	 *       销退运费值为100017
	 *       代收货款手续费 是100018）
     *
     *       零担费项125625967808135168
     */
	private Long costItemId;//
	
	private String docType;//单据类型（运单运费 和代收货款手续费 都是 TMS202）
	
	private String applicationName;//系统名
	
	private String itemDetail;//商品详情  json串
	
	private String currency;//商品详情  json串

	
	
	/**
	 * 发货人省ID	
	 */
	private  @ApiModelProperty("发货人省ID")String invoiceProvId;
	/**
	 * 	发货人城市ID	
	 */
	private  @ApiModelProperty("发货人城市ID")String invoiceCityId;
	/**
	 * 发货人地区ID		
	 */
	private  @ApiModelProperty("发货人地区ID") String invoiceDistrictId;
	/**
	 * 发货人街道ID		
	 */
	private  @ApiModelProperty("发货人街道ID")String invoiceStreetId;
	/**
	 * 发货人详细地址	 	
	 */
	private  @ApiModelProperty("发货人详细地址")String invoiceAddress;
	
	/**
	 * 收货人省ID		
	 */
	private  @ApiModelProperty("收货人省ID")String receiptProvId;
	/**
	 * 收货人城市ID		
	 */
	private  @ApiModelProperty("收货人城市ID")String receiptCityId;
	/**
	 * 收货人地区ID		
	 */
	private  @ApiModelProperty("收货人地区ID")String receiptDistrictId;
	/**
	 * 收货人街道ID		
	 */
	private @ApiModelProperty("收货人街道ID")String receiptStreetId;
	/**
	 * 收货人详细地址		
	 */
	private  @ApiModelProperty("收货人详细地址")String receiptAddress;
	
	
	/***********************************以下是计费所需*******************************************/
	/**
	 *仓库线路id	
	 */
	private @Mapping  @ApiModelProperty("仓库线路id")Long warehouseLineId;
	/**
	 * 签收时间	
	 */
	private   @ApiModelProperty("签收时间")Long signTime;
	
	/**
	 * 取消时间	
	 */
	private   @ApiModelProperty("取消时间")Long discardTime;
	
	/**
	 * 录单时间		
	 */
	private   @ApiModelProperty("录单时间") Long createTime;
	
	/**
     * 装货时间
     */
    
    @ApiModelProperty("装货时间")
    private Long freightTime;
	
    /**
     * TMS接收OMS的审核更改单据而赋值（非必填）
     */
	
    @ApiModelProperty("实收代收货款")
    private BigDecimal goodsPayment;
	
    /**
	 * 总运费	
	 */
	private   @ApiModelProperty("总运费")BigDecimal totalFee;
	
	@ApiModelProperty("未税总运费")
	private BigDecimal freightFeeNoTax;
	
	@ApiModelProperty("税费")
	private BigDecimal tax;
	
	 /**
     * 代收货款手续费
     */
    private  @ApiModelProperty("代收货款手续费") BigDecimal deliveryCharge;
	
	private @ApiModelProperty("代收货款") BigDecimal collectPayment;
	
    private @ApiModelProperty("订单类型") Integer orderType;
    
    private @ApiModelProperty("订单类型名称") String orderTypeName;
    
    private @ApiModelProperty("订单编号")String orderCode ;
    
    private @ApiModelProperty("运单ID")Long waybillId ;
    
    private @ApiModelProperty("运单编号")String waybillCode ;
    
    private @ApiModelProperty("代收货款手续费未税")BigDecimal deliveryChargeNoTax;
    
    private @ApiModelProperty("代收货款手续费税额")BigDecimal taxOfDeliveryCharge ;
    
	private @ApiModelProperty("运单状态")Integer status;
	
	//二期计费新增字段
	/**
     * 总体积
     */
	@ApiModelProperty("总体积") 
    private BigDecimal totalVolume;
    /**
     * 总重量
     */
	@ApiModelProperty("总重量")
    private BigDecimal totalWeight;
    /**
     * 总件数
     */
	@ApiModelProperty("总件数") 
    private Integer totalNum;
	/**
	 * 体积单位
	 */
	@ApiModelProperty("体积单位") 
	private String unitVolume;
	/**
	 * 重量单位
	 */
	@ApiModelProperty("重量单位")
	private String unitWeight;
	/**
	 * 件数单位
	 */
	@ApiModelProperty("件数单位") 
	private String unitTotalNum;
	
//	@ApiModelProperty("业务费")
//    private BigDecimal business;
//	
//	@ApiModelProperty("送货费")
//	private BigDecimal deliveryFee;
//	
//	@ApiModelProperty("垫付费")
//	private BigDecimal freightAdvanced;
//	
//	@ApiModelProperty("装卸费")
//	private BigDecimal handingcharge;
//	
//	@ApiModelProperty("叉车费")
//	private BigDecimal forkliftcharge;
//	
//	@ApiModelProperty("其他费")
//	private BigDecimal otherFee;
//	
//	@ApiModelProperty("车费")
//	private BigDecimal carFee;
//	
//	@ApiModelProperty("中转费")
//	private BigDecimal terminalcharge;
//	
//	@ApiModelProperty("装货费")
//	private BigDecimal loadingFee;
//	
//	@ApiModelProperty("卸货费")
//	private BigDecimal unloadingFee;

    @ApiModelProperty("声明价值")
	private BigDecimal declaredValue;
	
	@ApiModelProperty("单据类型名称")
	private String targetTypeName;
	
	@ApiModelProperty("订单来源：0-城配，1-零担")
	private Integer orderSource;
	
	@ApiModelProperty("订单操作：0-新增，1-修改，2-删除")
	private Integer orderBussType;
	
	@ApiModelProperty("核算对象")
	private Integer checkObjectGroupId;
	
	@ApiModelProperty("核算对象集团客户名称")
    private String checkObjectGroupName;
	
	@ApiModelProperty("结算对象")
	private Integer settleAccountId;//结算对象ID
	
	@ApiModelProperty("结算对象名称")
	private String settleAccountName;
	
	@ApiModelProperty("客户对象类型 0客户 1 会员")
	private Integer customerType;
	
	@ApiModelProperty("Id")
	private Long id;
	
	@ApiModelProperty("编码")
	private String code;
	

	@ApiModelProperty("费用类型")
	private String feeType;
	
	@ApiModelProperty("费用金额")
    private BigDecimal feeAmount;
	
	
	@ApiModelProperty("费用是否现算 0-是 1-否")
	private Integer presentCalculation;
	

	@Mapping
	@ApiModelProperty("改配单类型")
    private Integer changeDispatcherType;
}
