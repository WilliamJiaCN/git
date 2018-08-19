package com.hivescm.tms.api.dto.es.finance.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class FinanceAccountReqDTO {

	@ApiModelProperty(value = "户名")
	private String accountName;
	
	@ApiModelProperty(value = "账号")
	private String accountSn;
	
	@ApiModelProperty(value = "所属用户")
	private String userName;
	
	@ApiModelProperty(value = "所属用户id")
	private Integer userId;
	
	@ApiModelProperty(value = "业务网点")
	private Integer orgId;
	
	@ApiModelProperty(value = "业务网点")
	private List<Integer> orgIds;
}
