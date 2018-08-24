package com.hivescm.tms.api.dto.es.global.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.global.redundancy.CarrierLineScaleConfigDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 全局配置承运商比例分配请求DTO
 * @author ke.huang
 * @date 2017年10月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalLineScaleConfigRespDTO implements Serializable{
	private static final long serialVersionUID = -337002305722279278L;
	@Mapping
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@Mapping
	@ApiModelProperty("线路ID")
	private Integer lineId;
	@Mapping
	@ApiModelProperty("线路名称")
	private String lineName;
	@Mapping
	@ApiModelProperty("当前线路承运商分配比例")
	List<CarrierLineScaleConfigDTO> carriers;
	
}
