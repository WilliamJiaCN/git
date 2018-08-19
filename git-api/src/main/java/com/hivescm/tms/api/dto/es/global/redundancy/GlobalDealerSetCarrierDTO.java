package com.hivescm.tms.api.dto.es.global.redundancy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 经销商设置承运商DTO
 * @author ke.huang
 * @date 2017年10月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalDealerSetCarrierDTO implements Serializable{
	private static final long serialVersionUID = -1422893349315892861L;
	@ApiModelProperty("承运商ID")
	private Long carrierId;
	@ApiModelProperty("承运商名称")
	private String carrierName;
	@ApiModelProperty("线路类型 1、全部 2、部分")
	private Integer lineType;
	
	@ApiModelProperty("线路列表")
	private List<DealerSetCarrierLineDTO> lines;
	
}
