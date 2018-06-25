package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherStatusEnum;
import com.hivescm.tms.api.enums.biz.dispatcher.DistributionBillTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionTaskReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("公司id")
	private Long companyId;
	@ApiModelProperty("司机id")
	private Integer driverId;
	
	@ApiModelProperty("派车单状态 进行中(ACCEPTED,DEPARTED) 已完成(SIGNED) 我的批次(ACCEPTED,LOADED,DEPARTED)")
	private List<DispatcherStatusEnum> status;
	@ApiModelProperty("城配单类型 进行中(ASSIGN_BILL,PLATFORM_BILL) 已完成(不需要传) 我的批次(INTELLIGENCE_BILL)")
	private List<DistributionBillTypeEnum> billTypes;
	@ApiModelProperty("每页大小")
	private Integer pageSize;
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
}

