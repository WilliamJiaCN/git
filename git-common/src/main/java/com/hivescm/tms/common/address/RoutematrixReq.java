package com.hivescm.tms.common.address;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
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
