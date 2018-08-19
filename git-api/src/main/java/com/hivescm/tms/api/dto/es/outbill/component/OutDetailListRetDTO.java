package com.hivescm.tms.api.dto.es.outbill.component;

import com.hivescm.tms.api.dto.es.outbill.OutBillDetailEsDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
public class OutDetailListRetDTO {
	
	@ApiModelProperty("总条数") 
	private Integer totalNum;
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public List<OutBillDetailEsDTO> getOutWaybillList() {
		return outDetailList;
	}

	public void setOutWaybillList(List<OutBillDetailEsDTO> outDetailList) {
		this.outDetailList = outDetailList;
	}
	private List<OutBillDetailEsDTO> outDetailList;
}
