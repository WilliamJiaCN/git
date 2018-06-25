package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.enums.biz.dispatcher.DistributionBillTypeEnum;
import com.hivescm.tms.api.enums.biz.dispatcher.DistributionHistorySearchConditionsEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DistributionHistoryBillReqDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("公司id")
	private Long companyId;
	@ApiModelProperty("司机id")
	private Integer driverId;
	@ApiModelProperty(" 我的运单(ASSIGN_BILL,PLATFORM_BILL) 全部(ASSIGN_BILL,PLATFORM_BILL,INTELLIGENCE_BILL) 我的批次(INTELLIGENCE_BILL)")
	private List<DistributionBillTypeEnum> billTypes;
	
	@ApiModelProperty("开始时间(自定义时间传时间戳，其他随便传)")
	private Long startTime;
	
	@ApiModelProperty("结束时间")
	private Long endTime;
	
	@ApiModelProperty("时间筛选条件")
	private DistributionHistorySearchConditionsEnum timeConditions;

}
