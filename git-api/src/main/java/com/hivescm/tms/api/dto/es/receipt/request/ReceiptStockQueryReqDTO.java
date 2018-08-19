package com.hivescm.tms.api.dto.es.receipt.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptPickStockTypeEnum;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptStockQueryTimeTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 回单库存列表查询请求DTO
 * @author ke.huang
 * @date 2018年4月2日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptStockQueryReqDTO implements Serializable{
	private static final long serialVersionUID = -7657562142356315861L;
	@ApiModelProperty("日期类型")
	private ReceiptStockQueryTimeTypeEnum timeType;
	@ApiModelProperty("开始时间")
	private Long startTime;
	@ApiModelProperty("结束时间")
	private Long endTime;
	@ApiModelProperty("运单号")
	private String waybillCode;
	@Required @Logger
	@ApiModelProperty(value="操作网点ID",required=true)
	private Long orgId;
	@Required @Logger
	@ApiModelProperty("提取类型，回收、寄出、发放")
	private ReceiptPickStockTypeEnum pickType;
	
	public Boolean isValid(){
		if (null != timeType || null != startTime || null != endTime) {
			return isNotNull();
		}
		return true;
	}
	
	public Boolean isNotNull(){
		return null != timeType && null != startTime && null != endTime;
	}
}
