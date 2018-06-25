package com.hivescm.tms.api.dto.es.handlingorder.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author sql
 * @Date 10:432018\5\23 0023
 */
@Data
@ToString
public class HandlingOrderDetailPrintReqDTO implements Serializable {

    private static final long serialVersionUID = -620815347916832509L;

    @Mapping
    @ApiModelProperty(required = true,name = "装卸批次ID")
    private Long handlingorderId;


}
