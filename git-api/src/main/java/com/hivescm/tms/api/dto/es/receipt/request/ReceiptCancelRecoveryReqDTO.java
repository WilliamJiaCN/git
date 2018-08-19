package com.hivescm.tms.api.dto.es.receipt.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 回单取消回收请求DTO
 * @author ke.huang
 * @date 2018年3月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptCancelRecoveryReqDTO implements Serializable{
	private static final long serialVersionUID = 6603566859120101812L;
	@Required @Mapping
	@ApiModelProperty(value="取消回收时间",required=true)
	private Long cancelRecoveryTime;
	@ApiModelProperty(value="取消回收运单ID",required=true)
	private List<Long> waybills;
	@Required @Mapping
	@ApiModelProperty(value="取消回收人ID",required=true)
	private Integer cancelRecoveryUserId;
	@Required @Mapping
	@ApiModelProperty(value="回收人姓名",required=true)
	private String cancelRecoveryUserName;
	
	public Boolean isValid(){
		return null != this.waybills && this.waybills.size() != 0;
	}
	
}
