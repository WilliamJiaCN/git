package com.hivescm.tms.api.dto.es.sitesearch.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 综合查询多条件检索单据请求实体
 * @author ke.huang
 * @date 2017年9月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SearchBillReqDTO implements Serializable{
	private static final long serialVersionUID = -8799510431565134192L;
	@ApiModelProperty("公司ID")
	private Long companyId;
	@ApiModelProperty("单据日期")
	private int billDateType;
	@ApiModelProperty("单据日期开始时间")
	private Long billStartDate;
	@ApiModelProperty("单据日期结束时间")
	private Long billEndDate;
	@ApiModelProperty("客户单号")
	private String customerOrderCode;
	@ApiModelProperty("运单号")
	private String waybillCode;
	@ApiModelProperty("条件类型")
	private int conditionType;
	@ApiModelProperty("条件表达式")
	private int conditionExpression;
	@ApiModelProperty("条件值")
	private Object conditionValue;
	
	public Boolean isValid(){
		return isValidWaybillCode() || isValidCustomerOrderCode() || isValidConditions();
	}
	
	public Boolean isValidWaybillCode(){
		return null != companyId && null != waybillCode && !waybillCode.equals("");
	}
	
	public Boolean isValidCustomerOrderCode(){
		return null != companyId && null != customerOrderCode && !customerOrderCode.equals("");
	}
	
	public Boolean isValidConditions(){
		return null != companyId && 0 != billDateType && null != billStartDate && null != billEndDate && (billEndDate - billStartDate) / 1000 / 60 / 60 / 24 <= 30;
	}
	
	public Boolean isValidConditionExpression(){
		return 0 != conditionType && 0 != conditionExpression && null != conditionValue && !conditionValue.equals("");
	}
}