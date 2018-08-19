package com.hivescm.tms.api.dto.es.receipt.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 同步外发公司信息请求DTO
 * @author ke.huang
 * @date 2018年4月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptSyncExternalReqDTO implements Serializable{
	private static final long serialVersionUID = -3511701294269942169L;
	
	@Required
	@ApiModelProperty("运单ID")
	private List<Long> waybillIds;
	@Required
	@ApiModelProperty("外发公司名称")
	private String externalCompanyName;
}
