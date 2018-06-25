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
 * @since   2017/11/27
*/
@Data
@ToString
public class DispatcherTaskTotalOrdersReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;
	  @Logger
	@Mapping
	@ApiModelProperty("司机id")
	private Integer driverId;
    @Mapping
    @ApiModelProperty("发布状态 目前取值true")
    private Boolean releaseStatus;
	
}

