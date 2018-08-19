package com.hivescm.tms.api.dto.es.outbill.component;

import com.hivescm.tms.api.dto.es.outbill.resp.OutBillWaybillDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
public class OutWaybillListRetDTO {
	
	@ApiModelProperty("总条数") 
	private Integer totalNum;
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public List<OutBillWaybillDTO> getOutWaybillList() {
		return outWaybillList;
	}

	public void setOutWaybillList(List<OutBillWaybillDTO> outWaybillList) {
		this.outWaybillList = outWaybillList;
	}
	private List<OutBillWaybillDTO> outWaybillList;
}
