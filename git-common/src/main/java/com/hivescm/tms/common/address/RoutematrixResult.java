package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class RoutematrixResult implements  Serializable {

	private static final long serialVersionUID = 1L;

	private int status;
	
	private String message;
	
	private Routematrix[] result;
	
}
