package com.hivescm.tms.api.dto.es.order.resp;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CapacityManualServiceRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("线路id")
	private Long lineId;
	@Mapping
	@ApiModelProperty("提货时间")
	private Long deliveryTime;
	@Mapping
	@ApiModelProperty("派车时间")
	private Long dispatcherTime;
	@Mapping
	@ApiModelProperty("服务名称")
	private String serviceName;
	@Mapping
	@ApiModelProperty("服务id")
	private Long id;
}
