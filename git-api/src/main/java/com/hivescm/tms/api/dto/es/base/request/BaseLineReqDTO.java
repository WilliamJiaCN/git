package com.hivescm.tms.api.dto.es.base.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseLineReqDTO extends PageQurey{
	
		private static final long serialVersionUID = 483479926751823733L;
	    @ApiModelProperty("线路Id")
	    private Long lineId;
		@ApiModelProperty("时间开始范围")
		private Long startTime;
		@ApiModelProperty("截止时间范围")
		private Long endTime;
		@ApiModelProperty("线路名称")
	  	private String lineName;
		@ApiModelProperty("是否默认")
	  	private Boolean iDefault;
		@ApiModelProperty("始发网点")
	  	private Integer startOrgId;
		@ApiModelProperty("到达网点")
	  	private Integer arriveOrgId;
		@ApiModelProperty("是否启用")
	  	private Boolean status;
		@ApiModelProperty("总里程数")
	  	private Long totalMileage;
		@Required
		@ApiModelProperty("公司")
		private Long companyId;
		
	    

}
