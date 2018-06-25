package com.hivescm.tms.api.dto.es.handlingorder.request;

import java.io.Serializable;

import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 审核 取消审核
 * @author Administrator
 *
 */
@Data
@ToString
public class HandlingorderCheckReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;

    @ApiModelProperty("公司id")
    private Long companyId;

    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;
   
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;
  
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;

    @Required
	@ApiModelProperty(value = "装卸单ID" ,required = true)
	private Long handlingorderId;
	
}
