package com.hivescm.tms.api.dto.es.bill.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * 单据取消领用请求DTO
 * @author ke.huang
 * @date 2017年10月13日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillReceivedCancelReqDTO implements Serializable{
	private static final long serialVersionUID = 1764161417057495189L;
	@ApiModelProperty("公司ID")
	private Long companyId;
	@ApiModelProperty("取消领用单据ID")
	private List<Long> billInfoIds;
	@Autowired
	private Integer operateUserId;
	@Autowired
	private String operateUserName;
	
	public Boolean isValid(){
		return null != this.companyId && null != this.billInfoIds && this.billInfoIds.size() != 0;
	}
}
