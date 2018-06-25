package com.hivescm.tms.api.dto.es.distribution.response;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionIncomeRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("收入明细列表信息")
	@Mapping
	private List<DistributionIncomeDetailRespDTO> distributionIncomeDetailRespDTOs;
	@ApiModelProperty("收入总金额")
	@Mapping
	private Double costSum;

}

