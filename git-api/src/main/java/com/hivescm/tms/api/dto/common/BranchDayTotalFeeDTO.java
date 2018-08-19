package com.hivescm.tms.api.dto.common;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class BranchDayTotalFeeDTO {

	private String date;
	
	private BigDecimal fee;
}
