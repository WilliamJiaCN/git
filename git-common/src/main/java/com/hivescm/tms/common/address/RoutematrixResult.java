package com.hivescm.tms.common.address;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class RoutematrixResult implements  Serializable {

	private static final long serialVersionUID = 1L;

	private int status;
	
	private String message;
	
	private Routematrix[] result;
	
}
