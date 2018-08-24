package com.hivescm.tms.api.dto.es.receipt.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 接收回单库存运单信息
 * @author ke.huang
 * @date 2018年4月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptReceiveWaybillDTO implements Serializable{
	private static final long serialVersionUID = -3876717169271225408L;
	@Required
	@ApiModelProperty("运单ID")
	private Long waybillId;
	@ApiModelProperty("接收备注")
	private String receiveRemark;
}
