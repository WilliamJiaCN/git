package com.hivescm.tms.common.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RoutematrixAndCloudGCReq implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("始发地详细地址--例如陕西省西安市雁塔区大雁塔南广场")
	private CloudGCReq origins;
	@ApiModelProperty("目的地详细地址--例如陕西省西安市雁塔区大雁塔南广场")
	private CloudGCReq destinations;
}
