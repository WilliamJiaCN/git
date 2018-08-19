package com.hivescm.tms.api.dto.es.dispatcher.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author  lhf
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/11/9
*/
@Data
@ToString
public class DispatcherTaskReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("公司id")
	private Long companyId;
	  @Logger
	@ApiModelProperty("司机id")
	private Integer driverId;
	@ApiModelProperty("运单状态 进行中(HASBILLING已开单(物流公司接单初始状态),HASGOODS已揽货(司机APP装货确认)) ENROUTE(已配送), 已完成(SIGNED已签收(客户签收),CANCEL已取消(作废)) 我的批次(ACCEPTED,LOADED,DEPARTED)")
	private List<WaybillStatusEnum> status;
	@ApiModelProperty("派车单状态 我的批次(NOT_DEPARTING未发车,DEPARTED已发车,COMMITED已交接,SEND已配送)")
	private List<DispatcherStatusEnum> dispatcherStatus;
	@ApiModelProperty("每页大小")
	private Integer pageSize;
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
}

