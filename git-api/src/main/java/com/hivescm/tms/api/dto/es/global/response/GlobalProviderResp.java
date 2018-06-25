package com.hivescm.tms.api.dto.es.global.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年12月14日 上午10:44:22
* 
*/
@Data
@ToString
public class GlobalProviderResp implements Serializable{

	private static final long serialVersionUID = 1L;
	public Long distinct() {
		return this.id+this.warehouseId;
		
	}

	@ApiModelProperty("线路id")
	private Long id;
	
	@ApiModelProperty("线路名称")
	private String lineName;
	
	@ApiModelProperty("配送线路说明")
	private String lineDesc;
	
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	
	@ApiModelProperty("仓库名称")
	private String warehouseName;
}
