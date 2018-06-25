package com.hivescm.tms.api.dto.es.base.response;

import java.io.Serializable;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseDriverRespDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -9217436885624234243L;
	
	@ApiModelProperty("主键")
	private Integer id; 
	
    @ApiModelProperty("头像路径")
    private String   headUrl;

}
