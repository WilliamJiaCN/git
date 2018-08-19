package com.hivescm.tms.api.dto.es.dispatcher.request;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author  lhf
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/11/12
*/
@Data
@ToString
public class DispatcherDetailForSignReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	 @Logger
	private @Mapping @ApiModelProperty("派车单id") Long dipatcherId;
//	@ApiModelProperty("每页大小")
//	private Integer pageSize;
//	@ApiModelProperty("当前页")
//	private Integer currentPage;
	private List<SearchCondition> searchConditions;
	private List<OrderCondition> orderConditions;
	private PageCondition pageCondition;
}

