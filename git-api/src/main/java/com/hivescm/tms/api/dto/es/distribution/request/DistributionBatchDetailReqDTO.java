package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;

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
public class DistributionBatchDetailReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("派车单id")
	private Long dipatcherId;
	@ApiModelProperty("每页大小")
	private Integer pageSize;
	@ApiModelProperty("当前页")
	private Integer currentPage;

}

