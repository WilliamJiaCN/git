package com.hivescm.tms.api.dto.es.handlingorder.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class HandlingReduanceReqDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 392761748803475712L;

	private Boolean allowShipping;
}
