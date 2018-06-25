package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptPickStockTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单回收、寄出、发放快速添加模糊检索回单库存请求DTO
 * @date 2018年6月7日
 * @company 蜂网供应链管理（上海）有限公司
 * @author ke.huang
 */
@Data
@ToString
public class ReceiptGetStockReqDTO implements Serializable{

	private static final long serialVersionUID = -1914793985843918158L;
	
	@Required @Logger
	@ApiModelProperty(value="网点ID",required=true)
	private Long orgId;
	
	@Required @Logger
	@ApiModelProperty(value="运单号",required=true)
	private String waybillCode;
	
	@Required @Logger
	@ApiModelProperty(value="查询类型",required=true)
	private ReceiptPickStockTypeEnum type;

}
