package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单接收请求DTO
 * @author ke.huang
 * @date 2018年4月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptReceiveReqDTO implements Serializable{
	private static final long serialVersionUID = -2327424096056322139L;
	@Required @Logger
	@ApiModelProperty(value="寄出批次ID",required=true)
	private Long transmitId;
	@Required @Logger
	@ApiModelProperty(value="接收网点ID",required=true)
	private Long receiveOrgId;
	@Required
	@ApiModelProperty(value="接收网点名称",required=true)
	private String receiveOrgName;
	@Required
	@ApiModelProperty(value="接收人ID",required=true)
	private Long receiveUserId;
	@Required
	@ApiModelProperty(value="接收人姓名",required=true)
	private String receiveUserName;
	@Required
	@ApiModelProperty(value="接收时间",required=true)
	private Long receiveTime;
	@ApiModelProperty("到达备注")
	private String receiveRemark;
	@Required
	@ApiModelProperty(value="接收回单信息",required=true)
	private List<ReceiptReceiveWaybillDTO> details;
	
}
