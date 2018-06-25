package com.hivescm.tms.api.dto.es.global.response;

import java.io.Serializable;

import com.hivescm.tms.api.dto.es.global.GlobalServiceProviderConfigEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GlobalServiceProviderQueryResp implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("统仓统配服务商配置信息")
	private GlobalServiceProviderConfigEsDTO globalServiceProviderConfig;
}
