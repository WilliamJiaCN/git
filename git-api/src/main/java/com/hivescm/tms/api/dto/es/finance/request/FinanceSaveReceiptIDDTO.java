package com.hivescm.tms.api.dto.es.finance.request;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

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
