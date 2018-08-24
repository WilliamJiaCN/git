package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Data
public class SyncWaybillStatusToOmsReq {
	private Integer waybillStatus;
	private @Logger Long waybillId;
	private String opUser;
	
	/*****************************揽货相关********************************/
	/**
     * 装货时间
     */
    @Mapping
    @ApiModelProperty("装货时间")
    private Long freightTime;
    
    //以下三个字段  是普通销退  实际装货数量 和 单子数量 不想等时才更新
    private @ApiModelProperty("总运费") @Mapping BigDecimal totalFee;
    
    private @ApiModelProperty("未税总运费") @Mapping BigDecimal freightFeeNoTax;
    
    private @ApiModelProperty("税费") @Mapping BigDecimal tax;
    
    /*****************************配送相关********************************/
    private  @Mapping @ApiModelProperty("配送时间") Long deliveryTime; 
    
    private @Mapping @ApiModelProperty("车牌号") String vehicleNo; 
    
    /*****************************签收相关********************************/
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
	 * 累计签收件数
	 */
	private @Mapping  @ApiModelProperty("累计签收件数")Integer totalSignNumber;
	/**
	 * 支付渠道
	 */
	private @Mapping  @ApiModelProperty("支付渠道") Integer settlementMethod;
	
	/**
	 * 支付渠道名称
	 */
	private @Mapping  @ApiModelProperty("支付渠道名称") String settlementMethodName;
	
	/**
	 * 实收代收货款
	 */
	private @Mapping  @ApiModelProperty("实收代收货款") BigDecimal goodsPayment;
	
	/**
	 * 代收货款手续费
	 */
	private @Mapping  @ApiModelProperty("代收货款手续费") BigDecimal deliveryCharge;
	
    /*****************************取消相关********************************/
	/**
	 * 作废类型	
	 */
	private @Mapping  @ApiModelProperty("作废类型")Integer discardType;
	/**
	 * 作废原因	
	 */
	private @Mapping  @ApiModelProperty("作废原因")String discardReason;
	/**
	 * 作废时间	
	 */
	private @Mapping  @ApiModelProperty("作废时间")Long discardTime;
}
