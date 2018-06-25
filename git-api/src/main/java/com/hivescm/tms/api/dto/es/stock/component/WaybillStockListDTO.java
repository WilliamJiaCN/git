package com.hivescm.tms.api.dto.es.stock.component;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.stock.response.TransferStockEsDTO;

public class WaybillStockListDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3274833551968253076L;
	
	private Integer totalNum;
	
	private List<TransferStockEsDTO> stocks;
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<TransferStockEsDTO> getStocks() {
		return stocks;
	}

	public void setStocks(List<TransferStockEsDTO> stocks) {
		this.stocks = stocks;
	}

	

}
