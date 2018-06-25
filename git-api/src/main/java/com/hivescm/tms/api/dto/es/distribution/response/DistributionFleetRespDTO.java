package com.hivescm.tms.api.dto.es.distribution.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
@Data
@ToString
public class DistributionFleetRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;



	@ApiModelProperty("车队ID")
	private Integer fleetId;
	@ApiModelProperty("车队名称")
	private String fleetName;

	/***********************************************************
	 * 城配派车单信息
	 **********************************************************/
	@ApiModelProperty("车辆数")
	private Integer vehicleNum;
   

	
	
}
