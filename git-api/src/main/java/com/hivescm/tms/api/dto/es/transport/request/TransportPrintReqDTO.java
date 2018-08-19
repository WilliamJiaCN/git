package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 发车配载打印
 * @author Administrator
 *
 */
@Data
@ToString
public class TransportPrintReqDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1798720409278656018L;
	/**
	 * 发车批次ID
	 */
    @ApiModelProperty(value = "批次ID")
	private Long transportId;

	/**
	 * 网点ID
	 */
    @ApiModelProperty(value = "网点ID")
	private Integer branchId;
}
