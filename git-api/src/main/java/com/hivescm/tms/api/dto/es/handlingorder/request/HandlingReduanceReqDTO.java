package com.hivescm.tms.api.dto.es.handlingorder.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HandlingReduanceReqDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 392761748803475712L;

	private Boolean allowShipping;
}
