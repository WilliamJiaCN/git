package com.hivescm.tms.api.dto.es.global.redundancy;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 全局承运商分配比例 线路列表 DTO
 * @author ke.huang
 * @date 2017年10月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalLineScaleConfigDTO implements Serializable{
	private static final long serialVersionUID = -337002305722279278L;
	@Logger 
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@Logger 
	@ApiModelProperty("线路ID")
	private Integer lineId;
	@ApiModelProperty("线路名称")
	private String lineName;
	@ApiModelProperty("当前线路承运商分配比例")
	List<CarrierLineScaleConfigDTO> carriers;
	
}
