package com.hivescm.tms.api.dto.es.order.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ManualConfirmOrderReq implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@Logger
	@Mapping
	@ApiModelProperty("运输线路ID")
    private Long lineId;
	@Mapping
	@ApiModelProperty("运输线路名称")
	private String lineName;
	
	@Mapping({"OrderInfoDO.status"})
	@ApiModelProperty("订单状态")
    private Integer status;
	@Logger
	@Mapping
	@ApiModelProperty("配送服务ID")
    private Integer serviceId;
	@Mapping
	@ApiModelProperty("配送服务名称")
	private String serviceName;
	@Mapping
	@ApiModelProperty("指定配送时间")
    private Long dispatchingTime;//yyyy-MM-dd HH:mm:ss 配送时间提取HH:mm:ss
	@Mapping
	@ApiModelProperty("指定提货时间")
	private Long deliveryTime;//yyyy-MM-dd HH:mm:ss 提货时间提取HH:mm:ss
}
