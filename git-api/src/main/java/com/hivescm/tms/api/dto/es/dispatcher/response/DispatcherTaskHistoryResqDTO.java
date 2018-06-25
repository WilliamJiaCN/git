package com.hivescm.tms.api.dto.es.dispatcher.response;

import java.math.BigDecimal;
import java.util.List;

import com.hivescm.tms.api.dto.es.dispatcher.DispatcherFeeEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class DispatcherTaskHistoryResqDTO {

	/**
	 * 派车单费用列表
	 */
	@ApiModelProperty("历史列表")
	private List<DispatcherTaskRespDTO> hisrotyList;
	
	
	@ApiModelProperty("已运输件数")
	private Integer totalNum;
	
	@ApiModelProperty("已取消件数")
	private Integer cancelNum;
	
	@ApiModelProperty("总金额")
	private BigDecimal totalFee;
	
}
