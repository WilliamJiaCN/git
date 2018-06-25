package com.hivescm.tms.api.dto.es.outbill.req;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutBillListReqDTO {

	@ApiModelProperty("外发状态 1未外发、2已外发")
	private Integer status;
	
	@ApiModelProperty("开始时间")
	private Long startTime;
	
	@ApiModelProperty("结束时间")
	private Long endTime;
	
	@ApiModelProperty("网点列表")
	private List<Integer> branchIds;
	
	@ApiModelProperty("外发公司名称")
	private String companyName;
	
	@ApiModelProperty("外发批次号")
	private String outGoCode;
	
	@ApiModelProperty("每页条数")
	private Integer pageSize;
	
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
}
