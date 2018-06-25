package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  lhf
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/11/12
*/
@Data
@ToString
public class DispatcherDetailReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Logger
	private @Mapping @ApiModelProperty("派车单id") Long dipatcherId;
//	@ApiModelProperty("每页大小")
//	private Integer pageSize;
//	@ApiModelProperty("当前页")
//	private Integer currentPage;

}

