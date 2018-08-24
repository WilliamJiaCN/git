package com.hivescm.tms.api.dto.es.stock.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 批量库存锁请求DTO
 * @date 2018年5月31日
 * @company 蜂网供应链管理（上海）有限公司
 * @author ke.huang
 */
@Data
@ToString
public class StockLockReqDTO implements Serializable{
	private static final long serialVersionUID = -4142411166756003615L;
	
	@Required
	@ApiModelProperty(value="网点ID",required=true)
	private Long orgId;
	@Required
	@ApiModelProperty(value="运单ID")
	private List<Long> waybillId;
}
