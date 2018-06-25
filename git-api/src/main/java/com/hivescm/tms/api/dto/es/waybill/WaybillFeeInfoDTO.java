package com.hivescm.tms.api.dto.es.waybill;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class WaybillFeeInfoDTO {
	
	@ApiModelProperty("提货费-52")
	private BigDecimal pickupFee;

    @ApiModelProperty("基本运费-51")
	private BigDecimal baseFreight;

    @ApiModelProperty("送货费-53")
	private BigDecimal deliveryFee;

    @ApiModelProperty("上楼费-54")
	private BigDecimal upstairsFee;

    @ApiModelProperty("包装费-55")
	private BigDecimal packingCharges;
    
    @ApiModelProperty("信息费-56")
	private BigDecimal informationFee;
    
    @ApiModelProperty("代收货款手续费-57")
    private @Mapping  BigDecimal deliveryCharge;
    
	@ApiModelProperty("代收货款-58")
    private @Mapping BigDecimal orderGoodsPayment;

	private @Mapping @ApiModelProperty("保价费-59") BigDecimal protectFee;
	
	@ApiModelProperty("加急费-60")
	private BigDecimal emergencyFee;
	
	@Mapping
    @ApiModelProperty("税费-61")
    private BigDecimal tax;
	
	@ApiModelProperty("业务费-69")
	private BigDecimal business;
	
	@ApiModelProperty("垫付运费-66")
	private BigDecimal freightPayment;
	
    @ApiModelProperty("声明价值-68")
	private BigDecimal declaredValue;

    @ApiModelProperty("其他费用-70")
	private BigDecimal otherFee;
   
}
