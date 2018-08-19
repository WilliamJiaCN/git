package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class FinanceSaveReceiptIDDTO {

	@Mapping
	@ApiModelProperty("ID")
	private Long id;
	
	/**
     * 本次回收
     */
    @Mapping
    @ApiModelProperty("本次回收")
    private BigDecimal receiptAmount;
}
