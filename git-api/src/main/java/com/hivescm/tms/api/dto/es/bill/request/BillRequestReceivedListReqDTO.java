package com.hivescm.tms.api.dto.es.bill.request;

import com.hivescm.tms.api.enums.biz.bill.BillTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 单据领用列表查询请求DTO
 * @author ke.huang
 * @date 2017年10月12日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillRequestReceivedListReqDTO implements Serializable{
	private static final long serialVersionUID = -6128318929784669579L;
	@ApiModelProperty("申领开始时间")
	private Long requestStartTime;
	@ApiModelProperty("申领结束时间")
	private Long requestEndTime;
	@ApiModelProperty("状态 1:申请网点   2：使用网点")
	private Integer orgType;
	@ApiModelProperty("使用网点")
	private List<Integer> orgId;
	@ApiModelProperty("单据类型")
	private BillTypeEnum billType;
	@ApiModelProperty("包含单号")
	private String billCode;
	@ApiModelProperty("公司ID")
	private Long  companyId;
	
	@ApiModelProperty("每页大小")
	private Integer pageSize;
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	public Boolean isValid(){
		if (null == this.companyId) {
			return false;
		}
		if (null != requestStartTime || null != requestEndTime) {
			return null != requestStartTime && null != requestEndTime;
		}
		return true;
	}
	
}
