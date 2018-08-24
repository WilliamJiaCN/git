package com.hivescm.tms.api.dto.es.stock.component;

import com.hivescm.tms.api.dto.es.stock.WaybillStockEsDTO;

import java.io.Serializable;
import java.util.List;

public class WaybillStockListRetDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3274833551968253076L;
	
	private Integer totalNum;
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<WaybillStockEsDTO> getStocks() {
		return stocks;
	}

	public void setStocks(List<WaybillStockEsDTO> stocks) {
		this.stocks = stocks;
	}

	private List<WaybillStockEsDTO> stocks;

}
