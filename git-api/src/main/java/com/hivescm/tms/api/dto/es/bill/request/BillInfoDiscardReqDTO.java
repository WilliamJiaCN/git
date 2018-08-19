package com.hivescm.tms.api.dto.es.bill.request;

import com.hivescm.tms.api.enums.biz.bill.BillDiscardTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 单据作废请求DTO
 * @author ke.huang
 * @date 2017年10月13日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillInfoDiscardReqDTO implements Serializable{
	private static final long serialVersionUID = -5971931702390086387L;
	@ApiModelProperty("公司ID")
	private Long companyId;
	@ApiModelProperty("单号")
	private List<Long> billInfoIds;
	@ApiModelProperty("作废类型")
	private BillDiscardTypeEnum discardType;
	@ApiModelProperty("作废原因")
	private String discardReason;
	@ApiModelProperty("操作人ID")
	private Integer operateUserId;
	@ApiModelProperty("操作人姓名")
	private String operateUserName;
	
	public Boolean isValid(){
		return null != this.companyId && null != this.billInfoIds && this.billInfoIds.size() != 0 && null != discardType;
	}
}
