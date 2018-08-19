package com.hivescm.tms.api.dto.es.global.redundancy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 经销商设置承运商线路DTO
 * @author ke.huang
 * @date 2017年10月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DealerSetCarrierLineDTO implements Serializable{
	private static final long serialVersionUID = -6024903490077277017L;
	@ApiModelProperty("线路ID")
	private Long lineId;
	@ApiModelProperty("线路名称")
	private String lineName;
	@ApiModelProperty("线路分配比例 设置分配比例时字段")
	private BigDecimal portion;
}
