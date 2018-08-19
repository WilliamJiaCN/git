package com.hivescm.tms.api.dto.es.waybill.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 改配列表请求DTO
 * @company 蜂网供应链管理（上海）有限公司
 */
@ToString
@Data
public class WaybillChangeDispatcherListReqDTO implements Serializable{
	
	private static final long serialVersionUID = -8623365026731916885L;
	
	@ApiModelProperty("申请开始日期")
	private Long applicateStartDate;
	
	@ApiModelProperty("申请结束日期")
	private Long applicateEndDate;
	
	@ApiModelProperty("审核开始日期")
	private Long auditStartDate;
	
	@ApiModelProperty("审核结束日期")
	private Long auditEndDate;
	
	@ApiModelProperty("运单号")
	private String waybillCode;
	
	@ApiModelProperty("改配单号")
	private String changeDistributionCode;

	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	@ApiModelProperty("每页条数")
	private Integer pageSize;
	
}
