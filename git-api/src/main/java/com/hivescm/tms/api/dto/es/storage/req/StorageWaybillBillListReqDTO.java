package com.hivescm.tms.api.dto.es.storage.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class StorageWaybillBillListReqDTO {

	@ApiModelProperty("入库状态 1未入库、2已入库")
	private Integer status;
	
	@ApiModelProperty("外发开始日期")
	private Long startTime;
	
	@ApiModelProperty("外发结束日期")
	private Long endTime;
	
	@ApiModelProperty("中转网点列表")
	private List<Integer> branchIds;
	
	@ApiModelProperty("入库网点列表")
	private Integer inBranchId;
	
	@ApiModelProperty("运单号")
	private String waybillCode;
	
	@ApiModelProperty("外发单号")
	private String outbillNum;
	
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	@ApiModelProperty("每页条数")
	private Integer pageSize;
	
}
