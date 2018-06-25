package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.common.utils.StringUtils;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptListTimeTypeEnum;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptListTypeEnum;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptOrgTypeEnum;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptWaybillTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单总表列表请求DTO
 * @author ke.huang
 * @date 2018年3月26日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptListReqDTO implements Serializable{
	private static final long serialVersionUID = 2434490443058620974L;
	
	@ApiModelProperty(value="公司ID",required=true) @Logger @Required
	private Long companyId;
	@ApiModelProperty("时间类型")
	private ReceiptListTimeTypeEnum timeType;
	@ApiModelProperty("开始时间")
	private Long startTime;
	@ApiModelProperty("结束时间")
	private Long endTime;
	@ApiModelProperty("网点类型")
	private ReceiptOrgTypeEnum orgType;
	@ApiModelProperty("网点ID")
	private List<Long> orgId;
	@ApiModelProperty("回单状态")
	private String status;
	@ApiModelProperty("运单条件类型")
	private ReceiptWaybillTypeEnum waybillType;
	@ApiModelProperty("运单条件") @Logger
	private String waybillValue;
	@ApiModelProperty("列表类型")
	private ReceiptListTypeEnum listType;
	
	@Required
	@ApiModelProperty(value="当前页数",required=true)
	private Integer currentPage;
	@Required
	@ApiModelProperty(value="每页显示数",required=true)
	private Integer pageSize;
	
	public Boolean isValid(){
		if (null == listType) {
			return false;
		}
		if (null != timeType && (null == startTime || null == endTime)) {
			return false;
		}
		if (null != orgType && null == orgId) {
			return false;
		}
		if (null != waybillType && StringUtils.isBlank(waybillValue)) {
			return false;
		}
		return true;
	}
	
	public Boolean isValidTimeType(){
		return null != timeType && null != startTime && null != endTime;
	}
}
