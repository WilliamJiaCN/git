package com.hivescm.tms.api.dto.es.handlingorder.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/26
 */
@Data
@ToString
public class HandlingOrderDispatcherReqDTO {
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    @Mapping
    @ApiModelProperty("当前网点Id")
    private Long curOrgId;

    @Mapping
    @ApiModelProperty("装卸单批号")
    private String handlingOrderCode;

    @Mapping
    @ApiModelProperty("装卸单id")
    private Long handlingOrderId;



}
