package com.hivescm.tms.api.dto.es.waybill.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 承运商首页统计请求体 DTO
 * @company 蜂网供应链管理（上海）有限公司
 */
@ToString
@Data
public class CarrierBranchStatisticsReqDTO implements Serializable{
	private static final long serialVersionUID = -8623365026731916885L;
	@Logger
	@ApiModelProperty(value="网点ID",required=true)
	private Long branchId;
	@Logger
	@ApiModelProperty("承运商ID")
	private Long carrierId ;
	
	@ApiModelProperty("操作人")
	private Integer opUser;
	
	@ApiModelProperty("操作人姓名")
	private String opUserName;
	
}
