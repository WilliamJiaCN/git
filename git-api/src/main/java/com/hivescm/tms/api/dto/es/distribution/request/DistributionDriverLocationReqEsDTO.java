package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 司机定位请求DTO
 * @author ke.huang
 * @date 2017年8月30日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverLocationReqEsDTO implements Serializable{
	private static final long serialVersionUID = 4351533860216016376L;
	@Mapping
	@ApiModelProperty(value="司机ID",required=true)
	private Integer driverId;
	@Mapping
	@ApiModelProperty(value="经度",required=true)
	private BigDecimal lon;
	@Mapping
	@ApiModelProperty(value="纬度",required=true)
	private BigDecimal lat;
	@Mapping
	@ApiModelProperty(value="定位地址",required=true)
	private String locationAddress;
	@Mapping
	@ApiModelProperty(value="定位时间",required=true)
	private Long locationTime;
	
	public Boolean isValid(){
		return null != driverId && null != lon && null != lat && null != locationAddress && null != locationTime;
	}
}
