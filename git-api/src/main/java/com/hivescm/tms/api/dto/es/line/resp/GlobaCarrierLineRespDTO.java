package com.hivescm.tms.api.dto.es.line.resp;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class GlobaCarrierLineRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("线路id")
    private Long lineId;
	@Mapping
	@ApiModelProperty("承运商id")
    private Integer carrierId;
	@Mapping
	@ApiModelProperty("承运商网点id")
    private Integer branchId;
	@Mapping
	@ApiModelProperty("线路名称")
	private String lineName;
	@Mapping
	@ApiModelProperty("比例分配")
	private BigDecimal scale;
	@Mapping
	@ApiModelProperty("城市编码")
    private Integer cityCode;
}
