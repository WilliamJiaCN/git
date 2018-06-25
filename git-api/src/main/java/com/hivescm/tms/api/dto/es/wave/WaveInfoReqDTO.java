package com.hivescm.tms.api.dto.es.wave;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaveInfoReqDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7941710256848734298L;
	
	@Logger
	private String waveCode;
	
	private Long waveId;
	
	private Long carrierGlobalId;
	
	private Long waveTime;
	
	private List<WaveOrderInfoDTO> list;
	
	
}
