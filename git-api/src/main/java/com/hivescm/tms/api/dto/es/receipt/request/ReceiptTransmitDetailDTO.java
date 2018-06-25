package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单寄出添加运单明细
 * @author ke.huang
 * @date 2018年4月2日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptTransmitDetailDTO implements Serializable{
	private static final long serialVersionUID = 2765396276747508032L;
	@Mapping @Required
	@ApiModelProperty("库存ID")
	private Long stockId;
	@Mapping @Required
	@ApiModelProperty("运单号")
	private String waybillCode;
	@Mapping
	@ApiModelProperty("寄出备注")
	private String transmitRemark;
	@Mapping
	@ApiModelProperty("快递分摊费用")
    private BigDecimal expressFee;
}
