package com.hivescm.tms.api.dto.es.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
/**
 * 付款组织dto
 * @author lifan
 *
 * 2018年1月6日
 *
 */
public class PayOriginazationDTO implements Serializable {

	private static final long serialVersionUID = -3606117791140243793L;

	@Mapping
	@ApiModelProperty("付款组织")
	private String payOriginazationName;
	
	@Mapping
	@ApiModelProperty("付款组织id")
	private Integer payOriginazationId;
}
