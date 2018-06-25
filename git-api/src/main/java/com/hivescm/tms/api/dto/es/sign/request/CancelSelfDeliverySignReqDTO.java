package com.hivescm.tms.api.dto.es.sign.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class CancelSelfDeliverySignReqDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("当前要取消签收的签收单id") 
	@Logger
	@Required
	private List<Long> signIds;
	
	@ApiModelProperty("公司id")
	private Long companyId;
	
	@ApiModelProperty("更新人")
	private Integer updateUser;
	
	@ApiModelProperty("更新人名称")
	private String updateUserName;
	
}
