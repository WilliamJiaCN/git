package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

import com.hivescm.framework.validation.annotation.Required;

/**
 * 发车配载打印
 * @author Administrator
 *
 */
@Data
@ToString
public class TransportWaybillPrintReqDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 33056799353620214L;
	/**
	 * 发车批次ID
	 */
	@Required
    @ApiModelProperty(value = "批次ID",required=true)
	private Long id;


}
