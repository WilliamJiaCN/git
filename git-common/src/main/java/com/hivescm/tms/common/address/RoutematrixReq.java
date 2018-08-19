package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class RoutematrixReq implements Serializable{

	private static final long serialVersionUID = 1L;

	private String origins;
	
	private String destinations;
	
	private String output;
	
	private String ak;
	
	private String accessUrl;
	
	
}
