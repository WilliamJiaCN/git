package com.hivescm.tms.api.dto.es.finance;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
/**
 * 收款方dto
 * @author lifan
 *
 * 2018年1月6日
 *
 */
public class PayeeObjectDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6191971337889864879L;

	@Mapping
	@ApiModelProperty("收款方")
	private Integer payeeObjectId;
	
	@Mapping
	@ApiModelProperty("收款方名称")
	private String payeeObjectName;
}
