package com.hivescm.tms.api.dto.es.schedule.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 转移复制 resp DTO
 * @author qsk
 * @date 2017年11月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class TmsRegionTransferRespDTO implements Serializable{
	private static final long serialVersionUID = 498333084085492392L;
	@ApiModelProperty("司机Id")
	private Integer driverId;
	@ApiModelProperty("返回结果")
	private Boolean result;
}
