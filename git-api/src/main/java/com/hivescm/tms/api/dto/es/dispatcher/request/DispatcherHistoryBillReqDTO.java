package com.hivescm.tms.api.dto.es.dispatcher.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherHistorySearchConditionsEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class DispatcherHistoryBillReqDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("公司id")
	private Long companyId;
	 @Logger
	@ApiModelProperty("司机id")
	private Integer driverId;
	@ApiModelProperty("运单状态 进行中(HASBILLING已开单(物流公司接单初始状态),HASGOODS已揽货(司机APP装货确认)) 已完成(SIGNED已签收(客户签收),CANCEL已取消(作废)) 我的批次(ACCEPTED,LOADED,DEPARTED)")
	private List<WaybillStatusEnum> status;
	@ApiModelProperty("每页大小")
	private Integer pageSize;
	@ApiModelProperty("当前页")
	private Integer currentPage;
	@ApiModelProperty("开始时间(自定义时间传时间戳，其他随便传)")
	private Long startTime;
	@ApiModelProperty("结束时间")
	private Long endTime;
	@ApiModelProperty("时间筛选条件")
	private DispatcherHistorySearchConditionsEnum timeConditions;
}
