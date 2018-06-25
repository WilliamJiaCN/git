package com.hivescm.tms.api.dto.es.regulation.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillCodesGetReq {
	
	 @ApiModelProperty(value = "库存网点ID")
	 private List<Integer> orgIds;
	 @ApiModelProperty(value = "运单号")
	 private String code;
	 @ApiModelProperty(value = "去除的运单号List")
	 private List<String> codeList;

}
