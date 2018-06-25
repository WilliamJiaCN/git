package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.biz.waybill.SignStockDetailEunm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SearchBankWarehouseReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Mapping()
	@ApiModelProperty("查询开始时间")
	private Long startTime;

	@Mapping()
	@ApiModelProperty("查询结束时间")
	private Long endTime;

	@Mapping
	@ApiModelProperty("入库批次") @Logger
    private String storageBatchCode;
	
	@Mapping
    @ApiModelProperty("派车批次") @Logger
    private String batchCode;
	
	@Mapping
	@ApiModelProperty("反库类型")
    private SignStockDetailEunm backType;
	
	@Mapping
    @ApiModelProperty("运单号") @Logger
    private String waybillCode;

	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;

	@Mapping
	@ApiModelProperty("派车单明细Id")
	private Long dipatcherDetailId;

	@ApiModelProperty("分页 - 每页显示数")
	private int pageSize=10;

	@ApiModelProperty("页码")
	private int pageNumber=1;
}
