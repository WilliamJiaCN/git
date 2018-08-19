package com.hivescm.tms.api.dto.es.distribution.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionAssignedBillReqDTO extends DistributionPublicReqDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="开始日期")
	private Long startAcceptDate;
	@ApiModelProperty(value="结束日期")
	private Long endAcceptDate;
	@ApiModelProperty(value = "收货区域",notes = "格式例如：陕西省/西安市/未央区/张家堡")
	private String receivingArea;
}

