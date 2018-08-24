package com.hivescm.tms.api.dto.es.distribution.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
public class DistributionIncomeDetailRespDTO {

	@Mapping
	@ApiModelProperty("签收时间")
	private Long signTime;
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;
	@Mapping
	@ApiModelProperty("单据类型")
	private Integer billType;
	
	@Mapping
	@ApiModelProperty("单据类型名称")
	private Integer billTypeName;
	@Mapping
	@ApiModelProperty("派车单成本")
	private String cost;
}

