package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单取消发放请求DTO
 * @author ke.huang
 * @date 2018年3月30日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptCancelGrantReqDTO implements Serializable{
	private static final long serialVersionUID = -5663003144546056203L;
	@Mapping @Required
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@Required
	@ApiModelProperty(value="网点ID",required=true)
	private Long orgId;
	@Required
	@ApiModelProperty(value="网点名称",required=true)
	private String orgName;
	@Logger @Required
	@ApiModelProperty(value="运单ID",required=true)
	private List<Long> waybillIds;
	@Required
	@ApiModelProperty(value="取消发放人ID",required=true)
	private Long cancelGrantUserId;
	@Required
	@ApiModelProperty(value="取消发放人姓名",required=true)
	private String cancelGrantUserName;
	
}
