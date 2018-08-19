package com.hivescm.tms.api.dto.es.outbill.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class OutBatchSignReqDTO {
	
	@ApiModelProperty("外发单明细id")
	private List<Long> outBillDetailIdList;
	
	@ApiModelProperty("集团ID")
	private Integer groupId;
	
	@ApiModelProperty("公司名称")
	private String companyName;
	
	@ApiModelProperty("公司ID")
	private Long companyId;
	
	@ApiModelProperty("用户ID")
	private Integer userId;
	
	@ApiModelProperty("用户名称")
	private String userName;
	
}
