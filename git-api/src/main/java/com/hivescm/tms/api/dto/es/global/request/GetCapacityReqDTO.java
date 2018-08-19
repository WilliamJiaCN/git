package com.hivescm.tms.api.dto.es.global.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GetCapacityReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Required @Logger 
	@ApiModelProperty("线路id")
	private Long lineId;
	@Required @Logger 
	@ApiModelProperty("仓库id")
	private Long warehouseId; 
	@ApiModelProperty("承运商名称-关键字  模糊查询专用") @Logger 
	private String keyword;
	@Required
	@ApiModelProperty("自配和统配标识")
	private Integer key;
	@ApiModelProperty("经销商名称")
	private String dealerName;
	@Required
	@ApiModelProperty("时间-时间戳")
	private Long time;
	
}
