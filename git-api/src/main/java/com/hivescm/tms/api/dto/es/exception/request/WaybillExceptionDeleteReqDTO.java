package com.hivescm.tms.api.dto.es.exception.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillExceptionDeleteReqDTO implements Serializable {

	private static final long serialVersionUID = 9037656633812838841L;

	@Mapping
    @ApiModelProperty("主键")
    private List<Long> id;

    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    @Mapping
    @ApiModelProperty("运单ID")
    private List<Long> waybillId;
}
