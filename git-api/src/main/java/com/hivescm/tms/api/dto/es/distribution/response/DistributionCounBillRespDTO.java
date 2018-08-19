package com.hivescm.tms.api.dto.es.distribution.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class DistributionCounBillRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("待处理统计结果")
	private Integer arrivedCount;
	@ApiModelProperty("已指派统计结果")
	private Integer assignedCount;
	@ApiModelProperty("已接单统计结果")
	private Integer acceptedCount;
	@ApiModelProperty("已装货统计结果")
	private Integer loadedCount;
	@ApiModelProperty("已撤单统计结果")
	private Integer cancelCount;
	@ApiModelProperty("已发车统计结果")
	private Integer departedCount;
	
}
