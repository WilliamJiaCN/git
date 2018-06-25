package com.hivescm.tms.common.address;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class Routematrix implements Serializable{

	private static final long serialVersionUID = 1L;

	private Distance distance;
	
	private Distance duration;
	
}
