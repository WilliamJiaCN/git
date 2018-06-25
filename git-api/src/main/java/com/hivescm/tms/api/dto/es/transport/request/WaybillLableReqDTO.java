package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class WaybillLableReqDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4742864612429285146L;

	@ApiModelProperty("标签ID")
	private List<Long> lableList;
	
	@ApiModelProperty(value = "true=装货，false=卸货", notes = "操作方式")
	private Boolean load;
	
	@ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
	private Long companyId;
}
