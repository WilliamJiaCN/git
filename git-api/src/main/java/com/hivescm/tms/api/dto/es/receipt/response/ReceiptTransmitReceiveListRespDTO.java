package com.hivescm.tms.api.dto.es.receipt.response;

import com.hivescm.tms.api.dto.es.receipt.ReceiptTransmitReceiveEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 回单寄出接收批次列表响应DTO
 * @author ke.huang
 * @date 2018年4月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptTransmitReceiveListRespDTO implements Serializable{
	private static final long serialVersionUID = -4428472148332774648L;
	@ApiModelProperty("列表数据")
	private List<ReceiptTransmitReceiveEsDTO> list;
	@ApiModelProperty("总数")
	private Integer count;
	
	public ReceiptTransmitReceiveListRespDTO(List<ReceiptTransmitReceiveEsDTO> list, Integer count) {
		super();
		this.list = list;
		this.count = count;
	}
	public ReceiptTransmitReceiveListRespDTO() {
		super();
	}
	
	
}
