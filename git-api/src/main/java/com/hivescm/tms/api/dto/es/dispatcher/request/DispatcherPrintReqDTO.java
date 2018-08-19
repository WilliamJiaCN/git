package com.hivescm.tms.api.dto.es.dispatcher.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 派车单打印请求体
 * @author lifan
 *
 * 2017年11月18日
 *
 */
@Data
@ToString
public class DispatcherPrintReqDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4278690845765657261L;
	
	/**
	 * 派车单id
	 */
	private Long dispatcherId;

}
