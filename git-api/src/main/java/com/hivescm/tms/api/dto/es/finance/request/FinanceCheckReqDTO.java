package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceCheckReqDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	@ApiModelProperty("状态修改ids")
	@Required
	private List<Long> ids;
	
	@ApiModelProperty("操作人")
	@Required
	private Integer user;
	
	@ApiModelProperty("操作人名字")
	@Required
	private String userName;
	
	@ApiModelProperty("付款状态")
	private Integer paymentStatus;
	
	@ApiModelProperty("操作时间")
	private Long time;
	
	@ApiModelProperty("公司id")
	@Required
	private Long companyId;
}
