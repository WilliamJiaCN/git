package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 司机状态请求实体
 * @author ke.huang
 * @date 2017年9月1日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverStatausReqDTO implements Serializable {
	private static final long serialVersionUID = -2381713546394632752L;
	@Mapping 
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@Mapping
	@ApiModelProperty(value="司机ID",required=true)
	private Integer driverId;
	
	public Boolean isValid(){
		return null != this.companyId && null != this.driverId;
	}
}
