package com.hivescm.tms.api.dto.es.order.resp;

import com.google.common.collect.Lists;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.order.OrderEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class CapacityManualResp implements Serializable{

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("始发地")
	private String startCityName;
	@Mapping
	@ApiModelProperty("目的地")
	private String endCityName;
	@ApiModelProperty("单数")
	private Integer billNumber;
	@ApiModelProperty("总件数")
	private Integer totalCount;//冗余商品表总数
	@ApiModelProperty("总重量")
	private BigDecimal totalWeight;//冗余商品表总重量
	@ApiModelProperty("总体积")
	private BigDecimal totalVolume;//冗余商品表总体积
	@Mapping
	@ApiModelProperty("仓库id")
    private Long warehouseId;
	private List<OrderEsDTO> order;
	
	@Mapping
	@ApiModelProperty("运输线路ID")
    private Long lineId;
	@Mapping
	@ApiModelProperty("运输线路名称")
	private String lineName;
	@ApiModelProperty("自配和统配标识")
	private Integer keyword;
	@ApiModelProperty("经销商名称")
	private String dealerName;
	
	public CapacityManualResp sum(OrderEsDTO orderEsDTO) {
		if(this.billNumber == null) {
			this.billNumber =0;
		}
		if(this.totalCount==null) {
			this.totalCount =0;
		}
		if(this.totalWeight == null) {
			this.totalWeight = new BigDecimal(0);
		}
		if(this.totalVolume == null) {
			this.totalVolume = new BigDecimal(0);
		}
		if(this.order == null) {
			this.order =Lists.newArrayList();
		}
		this.billNumber++;
		this.totalCount+=orderEsDTO.getTotalCount();
		this.totalWeight=this.totalWeight.add(orderEsDTO.getTotalWeight());
		this.totalVolume=this.totalVolume.add(orderEsDTO.getTotalVolume());
		this.order.add(orderEsDTO);
		return this;
		
	}
}
