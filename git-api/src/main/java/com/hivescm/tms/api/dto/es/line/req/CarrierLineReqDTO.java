package com.hivescm.tms.api.dto.es.line.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class CarrierLineReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("承运商id")
	private Long carrierId;
	@ApiModelProperty("城市编码")
	private String cityCode;
	@ApiModelProperty("关键词(模糊搜索时必填，其他时候为空)")
	private String keyWord;
}
