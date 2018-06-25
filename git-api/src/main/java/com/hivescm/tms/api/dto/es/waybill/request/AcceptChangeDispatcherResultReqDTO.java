package com.hivescm.tms.api.dto.es.waybill.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 承运商首页统计请求体 DTO
 * @company 蜂网供应链管理（上海）有限公司
 */
@ToString
@Data
public class AcceptChangeDispatcherResultReqDTO implements Serializable{
	
	private static final long serialVersionUID = -8623365026731916885L;
	
	@ApiModelProperty("运单号")
	private String waybillCode;
	
	@ApiModelProperty("运单ID")
	private Long waybillId;
	
	@ApiModelProperty("审核状态")
    private Integer status;
	
	@Mapping
	@ApiModelProperty("责任方")
    private Integer responsibleObject;
	
	@ApiModelProperty("改配单号")
    private String changeDistributionCode;
	
	@Mapping
	@ApiModelProperty("改配原因")
    private Integer changeDispatcherReason;
	
	@Mapping
	@ApiModelProperty("改配日期")
    private String changeDispatcherDate;

	@Mapping
	@ApiModelProperty("改配开始时间")
    private String startTime;

	@Mapping
	@ApiModelProperty("改配结束时间")
    private String endTime;
	
}
