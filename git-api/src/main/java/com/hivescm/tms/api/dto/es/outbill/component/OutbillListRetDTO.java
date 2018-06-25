package com.hivescm.tms.api.dto.es.outbill.component;

import java.util.List;

import com.hivescm.tms.api.dto.es.outbill.OutBillInfoEsDTO;
import io.swagger.annotations.ApiModelProperty;
public class OutbillListRetDTO {
	
	@ApiModelProperty("总条数") 
	private Integer totalNum;
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<OutBillInfoEsDTO> getOutbillList() {
		return outbillList;
	}

	public void setOutbillList(List<OutBillInfoEsDTO> outbillList) {
		this.outbillList = outbillList;
	}

	private List<OutBillInfoEsDTO> outbillList;
}
