package com.hivescm.tms.api.dto.common;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class CalculateWaybillFreightResultDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1450349466378592248L;
	//成功数量
	private Integer success;
	//失败数量
	private Integer failed;
	
	//失败的运单号
	private List<String> waybillCodes;

}
