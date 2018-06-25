package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.biz.dispatcher.ChangeDispatcherStatusEnum;

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
public class DispatcherDetailForChangeReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	 @Logger
	private @Mapping @ApiModelProperty("派车单id") Long dipatcherDetailId;

	 @Logger
	 @Required
	 private @ApiModelProperty("运单id") Long waybilllId;
	 @Required
	 private ChangeDispatcherStatusEnum changeStatus;
}

