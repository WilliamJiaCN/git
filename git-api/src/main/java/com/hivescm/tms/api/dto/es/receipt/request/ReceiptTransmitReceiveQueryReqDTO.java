package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptTransmitReceiveListOrgTypeEnum;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptTransmitReceiveListTimeTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单寄出接收列表查询请求DTO
 * 回单寄出接收明细列表查询请求DTO
 * @author ke.huang
 * @date 2018年4月3日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptTransmitReceiveQueryReqDTO implements Serializable{
	private static final long serialVersionUID = 8732603592088373294L;
	
	@ApiModelProperty("时间类型")
	private ReceiptTransmitReceiveListTimeTypeEnum timeType;
	@ApiModelProperty("开始时间")
	private Long startTime;
	@ApiModelProperty("结束时间")
	private Long endTime;
	@ApiModelProperty("网点类型")
	private ReceiptTransmitReceiveListOrgTypeEnum orgType;
	@ApiModelProperty("网点ID")
	private List<Long> orgId;
	@ApiModelProperty("状态")
	private String status;
	@ApiModelProperty("寄出批次")
	private String transmitBatchCode;
	@ApiModelProperty("运单号")
	private String waybillCode;
	
	@Required
	@ApiModelProperty(value="当前页数",required=true)
	private Integer currentPage;
	@Required
	@ApiModelProperty(value="每页显示数",required=true)
	private Integer pageSize;
	
	public Boolean isValid(){
		boolean result = true;
		if (null != timeType || null != startTime || null != endTime) {
			result = isNotNullTimer();
		}
		if (result && (null != orgType || null != orgId)) {
			result = isNotNullOrg();
		}
		return result;
	}
	
	public Boolean isNotNullTimer(){
		return null != timeType && null != startTime && null != endTime;
	}
	
	public Boolean isNotNullOrg(){
		return null != orgType && null != orgId && orgId.size() != 0;
	}
}
