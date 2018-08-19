package com.hivescm.tms.api.dto.common;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class HomeStatisticsInfoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1450349466378592248L;
	//已承接订单
	private Integer validCount;
	//待指派运单
	private Integer hasBillingCount;
	//配送进行中
	private Integer enRounteCount;
	//已签收
	private Integer signedCount;
	//销退单
	private Integer returnOrderCount;
	
	//总运费
	private BigDecimal totalFee;
	
	//每天的费用
	private List<BranchDayTotalFeeDTO> dayFeeList;

}
