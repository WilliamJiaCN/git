package com.hivescm.tms.api.dto.es.storage.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class StorageGetStockReqDTO {

	@ApiModelProperty("入库网点ID")
	private Integer storageBranchId;
	
	@ApiModelProperty("运单号")
	private String waybillCode ;
	
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	@ApiModelProperty("每页条数")
	private Integer pageSize;
	
	@ApiModelProperty("目的地")
	private String destName ;
	
	@ApiModelProperty("到达网点")
	private List<Integer> destOrgIds;
	
}
