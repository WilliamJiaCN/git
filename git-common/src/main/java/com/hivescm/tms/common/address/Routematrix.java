package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class Routematrix implements Serializable{

	private static final long serialVersionUID = 1L;

	private Distance distance;
	
	private Distance duration;
	
}
