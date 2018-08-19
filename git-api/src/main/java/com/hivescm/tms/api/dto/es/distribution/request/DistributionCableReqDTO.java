package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 城配一键排线请求实体
 * @author ke.huang
 * @date 2017年9月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionCableReqDTO implements Serializable{
	private static final long serialVersionUID = -4890415480399378953L;
	@Mapping
	@ApiModelProperty(value="司机ID",required=true)
	private List<Integer> driverId;
	@Mapping
	@ApiModelProperty(value="运单ID",required=true)
	private List<Long> waybillId;
	@Mapping
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	
	public Boolean isValid(){
		return null != this.companyId && null != this.driverId && this.driverId.size() != 0 && null != this.waybillId && this.waybillId.size() != 0;
	}
}
