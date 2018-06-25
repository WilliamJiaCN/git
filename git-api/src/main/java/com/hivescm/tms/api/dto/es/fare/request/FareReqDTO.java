package com.hivescm.tms.api.dto.es.fare.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class FareReqDTO<T> {
    @Logger
    @Required
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Required
    @ApiModelProperty("费用参数")
    private List<T> list;
}
