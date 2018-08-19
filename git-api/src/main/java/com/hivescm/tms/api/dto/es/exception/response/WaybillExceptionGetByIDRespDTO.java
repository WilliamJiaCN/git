package com.hivescm.tms.api.dto.es.exception.response;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionAttachEsDTO;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionCloseEsDTO;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionEsDTO;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionProcessEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class WaybillExceptionGetByIDRespDTO implements Serializable {
	
	private static final long serialVersionUID = 8080247840629981737L;
	
	@Mapping
    @ApiModelProperty("异常主表信息")
	private WaybillExceptionEsDTO waybillExceptionEsDTO;

	@Mapping
    @ApiModelProperty("处理人信息")
    private List<WaybillExceptionProcessEsDTO> processList;
	
	@Mapping
    @ApiModelProperty("附件信息")
    private List<WaybillExceptionAttachEsDTO> attachList;

	@Mapping
    @ApiModelProperty("责任认定信息")
    private List<WaybillExceptionCloseEsDTO> closeList;
	
	
	

}
