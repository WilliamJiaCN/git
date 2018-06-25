package com.hivescm.tms.api.dto.es.waybill.component;

import java.util.List;
import com.hivescm.tms.api.dto.es.waybill.WaybillChangeDistributionEsDTO;
import io.swagger.annotations.ApiModelProperty;
public class WaybillChangeDistributionListDTO {
	
	@ApiModelProperty("总条数") 
	private Integer totalNum;
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<WaybillChangeDistributionEsDTO> getChangeDistributionList() {
		return changeDistributionList;
	}

	public void setChangeDistributionList(List<WaybillChangeDistributionEsDTO> changeDistributionList) {
		this.changeDistributionList = changeDistributionList;
	}

	private List<WaybillChangeDistributionEsDTO> changeDistributionList;
}
