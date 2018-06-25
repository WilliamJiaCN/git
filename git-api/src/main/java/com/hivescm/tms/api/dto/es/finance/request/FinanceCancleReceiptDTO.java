package com.hivescm.tms.api.dto.es.finance.request;


import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceCancleReceiptDTO {

	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	
	@Mapping
	@ApiModelProperty("集团ID")
	private Integer groupId;
	
	@Mapping
	@ApiModelProperty("ID")
	private Long id;
	
	@Mapping
	@ApiModelProperty("1，应收 2应付 3 货款回收 4 货款发放")
	private Integer type;
	
	@Mapping
	@ApiModelProperty("操作用户ID")
	private Integer userId;
	
	@Mapping
	@ApiModelProperty("操作用户名称")
	private String userName;
}
