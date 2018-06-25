package com.hivescm.tms.api.dto.es.storage.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StorageListReqDTO {

	@ApiModelProperty("入库类型")
	private Integer storageType;
	
	@ApiModelProperty("开始时间")
	private Long startTime;
	
	@ApiModelProperty("结束时间")
	private Long endTime;
	
	@ApiModelProperty("入库批次号")
	private String storageCode;
	
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	@ApiModelProperty("每页条数")
	private Integer pageSize;
	
	@ApiModelProperty("当前网点")
	private Integer branchId;
	
}
