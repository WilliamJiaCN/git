package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author sql
 * @Date 16:082018\5\12 0012
 */
@Data
@ToString
public class CapitalAccountReqDTO {

    @Mapping
    @ApiModelProperty("运单号")
    private String waybillCode;
    @ApiModelProperty("集团ID")
    private Integer groupId;
    @ApiModelProperty("业务单元ID")
    private Integer buId;
    @ApiModelProperty("状态(1为启用；2启用；3停用；null 所有)")
    private Integer status;

}
