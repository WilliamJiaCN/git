package com.hivescm.tms.api.dto.es.finance.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CashSerialRecordReqDTO implements Serializable{
	
	private static final long serialVersionUID = 4894934593215667742L;

	@ApiModelProperty("应付应收主键")
    private Long id;
	
	@ApiModelProperty("1.应收 2.应付 3 货款回收 4 货款发放")
    private Integer type;
	
	@ApiModelProperty("来源单号")
    private String ordersourceId;
	
	@ApiModelProperty("支付方式")
    private Integer payWay;
}
