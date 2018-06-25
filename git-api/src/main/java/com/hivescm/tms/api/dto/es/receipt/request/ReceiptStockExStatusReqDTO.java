package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptExceptionStatusEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 同步异常状态请求DTO
 * @author ke.huang
 * @date 2018年3月31日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptStockExStatusReqDTO implements Serializable{
	private static final long serialVersionUID = 1351323224162414551L;
	@Required @Logger
	@ApiModelProperty("运单ID")
	private Long waybillId;
	@ApiModelProperty("异常状态")
	private ReceiptExceptionStatusEnum status;
}
