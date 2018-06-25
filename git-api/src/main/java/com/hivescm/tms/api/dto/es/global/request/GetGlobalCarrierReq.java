package com.hivescm.tms.api.dto.es.global.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年12月12日 上午11:25:13
* 
*/
@Data
@ToString
public class GetGlobalCarrierReq implements Serializable{

	private static final long serialVersionUID = 1L;
	@Required @Logger 
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	@Required @Logger 
	@ApiModelProperty("线路id")
	private Long lineId;

}
