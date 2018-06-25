package com.hivescm.tms.api.dto.es.line.resp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class ComponentCarrierLineServiceRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("承运商网点id")
    private Integer branchId;
	@Mapping
	@ApiModelProperty("承运商线路id")
	private Long carrierLineId;
	@Mapping
	@ApiModelProperty("提货距离")
    private BigDecimal distance;
	@ApiModelProperty("组装的线路服务和承运商线路服务数据")
	private List<CarrierLineServiceRespDTO> carrierLineServiceResp;
}
