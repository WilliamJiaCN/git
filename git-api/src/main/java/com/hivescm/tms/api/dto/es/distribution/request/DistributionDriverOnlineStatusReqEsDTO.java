package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 司机在线状态DTO
 * @author ke.huang
 * @date 2017年8月11日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverOnlineStatusReqEsDTO implements Serializable {
	private static final long serialVersionUID = 4169729285135683482L;
	private @Mapping @ApiModelProperty("司机ID") Integer driverId;
	private @Mapping @ApiModelProperty("是否在线") Boolean online;
	
	public Boolean isValid(){
		return null != this.driverId && null != this.online;
	}
	
}