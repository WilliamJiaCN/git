package com.hivescm.tms.api.dto.es.wave;

import com.hivescm.framework.logger.api.annotation.Logger;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class WaveOrderInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6828461879148093857L;
    @Logger
	private String orderNum;
    @Logger
	private String waybillCode;
	
}
	