package com.hivescm.tms.api.dto.es.receipt.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 回单回收运单信息
 * @author ke.huang
 * @date 2018年3月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptRecoveryWaybillDTO implements Serializable{
	private static final long serialVersionUID = -8822108843258393442L;
	@Required @Logger
	@ApiModelProperty("运单ID")
	private Long waybillId;
	@Required @Logger
	@ApiModelProperty("运单号")
	private String waybillCode;
	@ApiModelProperty("回收备注")
	private String remark;
}
