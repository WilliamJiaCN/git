package com.hivescm.tms.api.dto.es.outbill.component;

import com.hivescm.tms.api.dto.es.storage.StorageInfoEsDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
public class StorageListRetDTO {
	
	@ApiModelProperty("总条数") 
	private Integer totalNum;
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<StorageInfoEsDTO> getStorageList() {
		return storageList;
	}

	public void setStorageList(List<StorageInfoEsDTO> storageList) {
		this.storageList = storageList;
	}

	private List<StorageInfoEsDTO> storageList;
}
