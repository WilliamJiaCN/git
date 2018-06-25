package com.hivescm.tms.api.dto.es.outbill.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutBillDetailListReqDTO {

	@ApiModelProperty("开始时间")
	private Long startTime;
	
	@ApiModelProperty("结束时间")
	private Long endTime;
	
	@ApiModelProperty("外发公司名称")
	private String companyName;
	
	@ApiModelProperty("外发批次号")
	private String outGoCode;
	
	@ApiModelProperty("运单号")
	private String waybillCode;
	
	@ApiModelProperty("每页条数")
	private Integer pageSize;
	
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	@ApiModelProperty("当前网点")
	private Integer branchId;
	
}
