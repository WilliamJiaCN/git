package com.hivescm.tms.api.dto.db.pre;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WarehouseServiceProviderRespDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("仓储服务商id")
	private Long warehousingServiceProviderId;
	
	@ApiModelProperty("仓储服务商名称")
	private String warehousingServiceProviderName;
	
	public WarehouseServiceProviderRespDTO(Long warehousingServiceProviderId,String warehousingServiceProviderName) {
		this.warehousingServiceProviderId = warehousingServiceProviderId;
		this.warehousingServiceProviderName = warehousingServiceProviderName;
	}

	public WarehouseServiceProviderRespDTO() {
	}
}
