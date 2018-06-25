package com.hivescm.tms.api.dto.common;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BranchDayTotalFeeDTO {

	private String date;
	
	private BigDecimal fee;
}
