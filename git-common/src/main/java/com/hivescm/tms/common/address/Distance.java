package com.hivescm.tms.common.address;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Distance implements Serializable{
	private static final long serialVersionUID = 1L;

	private String text;
	
	private double value;
}
