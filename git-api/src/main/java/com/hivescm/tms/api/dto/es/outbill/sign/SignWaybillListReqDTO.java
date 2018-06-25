package com.hivescm.tms.api.dto.es.outbill.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignWaybillListReqDTO {

	@ApiModelProperty("签收状态")
	private Integer status;
	
	@ApiModelProperty("开始时间")
	private Long startTime;
	
	@ApiModelProperty("结束时间")
	private Long endTime;
	
	@ApiModelProperty("运单号")
	private String waybillCode;
	
	@ApiModelProperty("外发公司名称")
	private String companyName;
	
	@ApiModelProperty("外发批次号")
	private String outbillCode;
	
	@Mapping
    @ApiModelProperty("外发单号")
    private String outbillNum;
	
	@Mapping
	@ApiModelProperty("签收单号")
    private String signNum;
	
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	@ApiModelProperty("每页条数")
	private Integer pageSize;
	
	@ApiModelProperty("当前网点")
	private Integer branchId;
	
}
