package com.hivescm.tms.api.dto.es.bill.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 单据批次详情列表请求DTO
 * @author ke.huang
 * @date 2017年10月12日
 * @company 蜂网供应链管理（上海）有限公司
 */
@ToString
@Data
public class BillInfoListReqDTO implements Serializable{
	private static final long serialVersionUID = 4769473331814535772L;
	@ApiModelProperty("申领批次ID")//当点击批次列表某条数据中的"领用明细"浮窗按钮时接收的值，优先级最高
	private Long billRequestReceivedId;
	@ApiModelProperty("公司ID")
	private Long companyId;
	@ApiModelProperty("发放开始日期")
	private Long grantStartTime;
	@ApiModelProperty("发放结束日期")
	private Long grantEndTime;
	@ApiModelProperty("使用网点")
	private List<Object> orgId;
	@ApiModelProperty("领用批次")
	private String billRequestReceivedBatchCode;
	@ApiModelProperty("单号")
	private String billCode;
	
	@ApiModelProperty("每页大小")
	private Integer pageSize;
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	public Boolean isValid(){
		if (null == this.companyId) {
			return false;
		}
		if (null != grantStartTime || null != grantEndTime) {
			return null != grantStartTime && null != grantEndTime;
		}
		return true;
	}
}
