package com.hivescm.tms.api.dto.es.global.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GlobalServiceProviderQueryReq implements Serializable{

	private static final long serialVersionUID = 1L;
	@Logger 
	@ApiModelProperty("仓储服务商ID")
    private Long providerId;
	@Logger 
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	/**************************通过承运商Id 和仓库Id 获取网点ID name 专用参数*****************************/
	@Logger 
	@ApiModelProperty("承运商ID")
    private Long carrierId;
	@Logger 
	@ApiModelProperty("线路ID")
    private Long lineId;
	
}
