package com.hivescm.tms.api.dto.es.waybill.response;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TmsWaybillRespDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("运单查询列表")
	private List<WaybillEsDTO> waybillList;
	
	@ApiModelProperty("运单查询总条数")
	private Integer count=0;
}
