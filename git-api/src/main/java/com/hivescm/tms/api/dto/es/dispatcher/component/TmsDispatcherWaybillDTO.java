package com.hivescm.tms.api.dto.es.dispatcher.component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherGoodsEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherWaybillDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 待处理运单列表
 * @author Administrator
 *
 */

@Data
@ToString
public class TmsDispatcherWaybillDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8083356967577742878L;

	@ApiModelProperty("待处理运单列表")
	private List<DispatcherWaybillDTO> dispatcherWaybillList;
	
	@ApiModelProperty("总票数")
	private Integer totalWaybillNum;
	
	@ApiModelProperty("总商品件数")
	private Integer totalNum;
	@ApiModelProperty("总重量")
	private BigDecimal totalWeight;
	@ApiModelProperty("总体积")
	private BigDecimal totalVolume;
	@ApiModelProperty("总运费")
	private BigDecimal totalFee;
	
}
