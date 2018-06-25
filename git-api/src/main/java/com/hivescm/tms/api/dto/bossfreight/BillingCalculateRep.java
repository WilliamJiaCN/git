package com.hivescm.tms.api.dto.bossfreight;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class BillingCalculateRep {
	
	private BigDecimal deliveryCharge;//代收货款手续费
	
	private BigDecimal deliveryChargeNoTax;//代收货款手续费未税
	
	private BigDecimal taxOfDeliveryCharge;//代收货款手续费税额

	 /**
     * 计费价格
     */
    private @Mapping  @ApiModelProperty("计费价格") BigDecimal price;

    /**
     * 税率
     */
    private @Mapping  @ApiModelProperty("税率") String taxRate;

    /**
     * 税码
     */
    private @Mapping  @ApiModelProperty("税码") String taxCode;

    /**
     * 服务项
     */
    private @Mapping  @ApiModelProperty("服务项") String serviceName;

    /**
     * 是否含税
     */
    private @Mapping  @ApiModelProperty("是否含税") Boolean withTax;

    /**
     * 计量依据
     */
    private @Mapping  @ApiModelProperty("计量依据") String meteringBase;

    /**
     * 计量单位
     */
    private @Mapping  @ApiModelProperty("计量单位") Integer meteringUnit;

    /**
     * 货值
     */
    private @Mapping  @ApiModelProperty("货值") BigDecimal amout;
}
