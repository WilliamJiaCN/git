package com.hivescm.tms.api.dto.es.receipt.response;

import com.hivescm.tms.api.dto.es.receipt.ReceiptStockEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 回单库存列表响应DTO
 * @author ke.huang
 * @date 2018年4月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptStockListRespDTO implements Serializable{
	private static final long serialVersionUID = -8932443398753201930L;
	
	@ApiModelProperty("列表数据")
	private List<ReceiptStockEsDTO> stocks;
	@ApiModelProperty("总数")
	private Integer count;
	
	public ReceiptStockListRespDTO(List<ReceiptStockEsDTO> stocks, Integer count) {
		super();
		this.stocks = stocks;
		this.count = count;
	}

	public ReceiptStockListRespDTO() {
		super();
	}
	
	
}
