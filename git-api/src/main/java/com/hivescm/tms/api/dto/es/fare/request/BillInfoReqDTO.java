package com.hivescm.tms.api.dto.es.fare.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BillInfoReqDTO {
    @ApiModelProperty("单据类型（2派车单3配载单7到货确认单）")
    private @Logger
    @Required
    Integer billType;

    @Mapping
    @ApiModelProperty(value = "公司id",hidden = true)
    private Long companyId;

    @ApiModelProperty("单据批次号")
    private @Logger
    @Required
    String billCode;

    @ApiModelProperty("改费记录主表id")
    private Long changId;
}
