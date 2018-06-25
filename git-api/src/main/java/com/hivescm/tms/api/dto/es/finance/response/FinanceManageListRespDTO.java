package com.hivescm.tms.api.dto.es.finance.response;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 应付列表返回实体
 * @author wangqianqian
 *
 */
@Data
@ToString
public class FinanceManageListRespDTO implements Serializable{
	
	private static final long serialVersionUID = 3527238869061086713L;

	@ApiModelProperty("总条数")
	private Integer totalNum;
	@ApiModelProperty("应付费用列表")
	private List<FinanceManagePayEsDTO> list;
}
