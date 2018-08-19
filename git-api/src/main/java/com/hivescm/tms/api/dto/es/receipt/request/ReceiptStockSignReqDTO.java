package com.hivescm.tms.api.dto.es.receipt.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptSignStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 回单签收信息同步请求DTO
 * @author ke.huang
 * @date 2018年4月2日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptStockSignReqDTO implements Serializable{
	private static final long serialVersionUID = -2151190381329559284L;
	@ApiModelProperty(value="签收状态",required=true)
	private ReceiptSignStatusEnum signStatus;
	@Required
	@ApiModelProperty(value="签收经办人",required=true)
    private Long signHandleUserId;
	@Required
	@ApiModelProperty(value="签收经办人姓名",required=true)
    private String signHandleUserName;
	@Required
	@ApiModelProperty(value="签收时间",required=true)
    private Long signTime;
	@Required @Logger
	@ApiModelProperty(value="运单ID",required=true)
	private Long waybillId;
}
