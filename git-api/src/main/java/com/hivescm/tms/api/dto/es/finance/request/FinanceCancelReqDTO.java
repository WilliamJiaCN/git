package com.hivescm.tms.api.dto.es.finance.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceCancelReqDTO implements Serializable{

	private static final long serialVersionUID = -4113091835091021082L;

	/**
	 * 费项明细ID列表
	 */
	@ApiModelProperty("费项明细ID列表")
	private List<Long> idList;

	/**
	 * 批次号，适用于派车单批次号、发车配载批次号
	 */
	@ApiModelProperty("批次号")
	private String batchCode;
}
