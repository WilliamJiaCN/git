package com.hivescm.tms.api.dto.es.exception;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExceptionStatusSendMqDTO implements Serializable {

	private static final long serialVersionUID = -2387498052666353397L;

	/**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id")
    private Long companyId;
    @ApiModelProperty(value = "主键IDs")
    private List<Long> ids;
    @ApiModelProperty(value = "异常状态 ")
    private Boolean status;
    @ApiModelProperty(value = "处理状态 true已处理 false未处理")
    private Boolean handleStatus;
}
