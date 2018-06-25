package com.hivescm.tms.api.dto.es.global.redundancy;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 承运商线路比例配置DTO
 * @author ke.huang
 * @date 2017年10月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class CarrierLineScaleConfigDTO implements Serializable{
	private static final long serialVersionUID = -9075443428538237289L;
	@Mapping
	@ApiModelProperty("承运商ID")
	private Long carrierId;
	@Mapping
	@ApiModelProperty("承运商姓名")
	private String carrierName;
	@Mapping
	@ApiModelProperty("是否有当前线路权限")
	private Boolean iAuthor;
	@Mapping
	@ApiModelProperty("比例分配")
	private BigDecimal scale;
}
