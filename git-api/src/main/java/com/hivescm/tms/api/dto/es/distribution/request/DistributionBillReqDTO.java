package com.hivescm.tms.api.dto.es.distribution.request;
/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherDetailStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class DistributionBillReqDTO extends DistributionPublicReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="接单开始日期",notes="接单列表日期字段")
	private Long startAcceptDate;
	@ApiModelProperty(value="接单结束日期",notes="接单列表日期字段")
	private Long endAcceptDate;
	@ApiModelProperty(value="装货开始日期",notes="装货列表日期字段")
	private Long startLoadingDate;
	@ApiModelProperty(value="装货结束日期",notes="装货列表日期字段")
	private Long endLoadingDate;
	@ApiModelProperty(value="发车开始日期",notes="发车列表日期字段")
	private Long startDepartDate;
	@ApiModelProperty(value="发车结束日期",notes="发车列表日期字段")
	private Long endDepartDate;
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	@Mapping
	@ApiModelProperty(value="列表页单据状态 已接单传(ACCEPTED),已装货传(LOADED),已发车传(DEPARTED)")
	private DispatcherDetailStatusEnum status;
}

