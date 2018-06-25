package com.hivescm.tms.api.dto.es.finance.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 查询可用来源单号条件
 * @author wangqianqian
 *
 */
@Data
@ToString
public class FinanceSheetCodeReqDTO implements Serializable {

	private static final long serialVersionUID = 7078532192768267457L;

	@ApiModelProperty(value = "当前网点")
	private Integer orgId;
	
	@ApiModelProperty(value = "来源单号")
	private String code;
	
	@ApiModelProperty(value = "剔除的来源单号")
	private List<Long> idList;
}
