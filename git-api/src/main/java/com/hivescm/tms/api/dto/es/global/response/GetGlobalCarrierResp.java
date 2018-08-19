package com.hivescm.tms.api.dto.es.global.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年12月12日 上午11:25:28
* 
*/
@Data
@ToString
public class GetGlobalCarrierResp implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("业务单元id")
	private Long unifyProviderDepartId;
	@ApiModelProperty("统一物流组织服务商id")
	private Long unifyProviderId;
}
