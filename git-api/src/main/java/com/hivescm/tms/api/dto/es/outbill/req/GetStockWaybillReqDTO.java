package com.hivescm.tms.api.dto.es.outbill.req;

import java.io.Serializable;

import lombok.Data;
@Data
public class GetStockWaybillReqDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -13527259212986135L;
	
	private Integer branchId;
	
	private Integer pageSize;
	
	private Integer currentPage;

}
