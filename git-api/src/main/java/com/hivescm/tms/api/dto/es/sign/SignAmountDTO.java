package com.hivescm.tms.api.dto.es.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 计算即将要签收的金额
 */
@Data
@ToString
public class SignAmountDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("公司id")
    private Long companyId;
	
	@Mapping
	@ApiModelProperty("运单ID")
    private Long waybillId;

	@Mapping
	@ApiModelProperty("总付费")
	private BigDecimal amount;

	@Mapping
	@ApiModelProperty("到付运费")
    private BigDecimal toPay;

	@Mapping
	@ApiModelProperty("代收货款")
    private BigDecimal collectPayment;

	@Mapping
	@ApiModelProperty("拒收销退单金额")
	private BigDecimal RefusePay;

	@Mapping
	@ApiModelProperty("到付送货费")
    private BigDecimal deliveryFee;

}
