package com.hivescm.tms.api.dto.es.handlingorder.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class TmsHandlingorderReqDTO implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4438430317303649883L;
    
    @ApiModelProperty(value = "公司ID", notes = "前端调用时不传")
    private Long companyId;
	
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;
	
    @Required
    @ApiModelProperty(value = "批次号",required = true)
    private String batch; 

}
