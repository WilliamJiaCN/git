package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单回收请求DTO
 * @author ke.huang
 * @date 2018年3月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptRecoveryReqDTO implements Serializable{
	private static final long serialVersionUID = 6603566859120101812L;
	@Required @Mapping
	@ApiModelProperty(value="回收时间",required=true)
	private Long recoveryTime;
	@ApiModelProperty(value="回收运单",required=true)
	private List<ReceiptRecoveryWaybillDTO> waybills;
	@Required @Mapping
	@ApiModelProperty(value="回收人ID",required=true)
	private Integer recoveryOperUserId;
	@Required @Mapping
	@ApiModelProperty(value="回收网点ID",required=true)
	private Long recoveryOrgId;
	@Required @Mapping
	@ApiModelProperty(value="回收网点名称",required=true)
	private String recoveryOrgName;
	@Required @Mapping
	@ApiModelProperty(value="回收人姓名",required=true)
	private String recoveryOperUserName;
	
	public Boolean isValid(){
		return null != this.waybills && this.waybills.size() != 0;
	}
	
}
