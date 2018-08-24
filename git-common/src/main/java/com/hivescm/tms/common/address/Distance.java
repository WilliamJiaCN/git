package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Distance implements Serializable{
	private static final long serialVersionUID = 1L;

	private String text;
	
	private double value;
}
