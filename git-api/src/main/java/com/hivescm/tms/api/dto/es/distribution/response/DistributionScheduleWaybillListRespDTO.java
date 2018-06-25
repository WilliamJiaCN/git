package com.hivescm.tms.api.dto.es.distribution.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 城配智能调度运单列表+统计信息响应实体
 * @author ke.huang
 * @date 2017年9月1日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionScheduleWaybillListRespDTO implements Serializable{
	private static final long serialVersionUID = 2663474993397893616L;
	@ApiModelProperty("运单数")
	private Integer waybillNum;
	@ApiModelProperty("卸货数")
	private Integer unloadingNum;
	@ApiModelProperty("总重量")
	private Double totalWeight;
	@ApiModelProperty("总体积")
	private Double totalVolume;
	@ApiModelProperty("总件数")
	private Integer totalNum;
	@ApiModelProperty("运费合计")
	private BigDecimal totalFee;
	
	@ApiModelProperty("运单列表")
	private List<TmsWaybillEsDTO> tmsWaybillEsDTOs;
}
