package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author sql
 * @Date 11:202018\5\23 0023
 */
@Data
@ToString
public class HandlingOrderPrintRespDTO implements Serializable {
    private static final long serialVersionUID = 4949458202826618573L;

    @Mapping
    @ApiModelProperty("装卸批次")
    private String handlingOrderCode;

    @Mapping
    @ApiModelProperty("类型")
    private String handlingTypeName;

    @Mapping
    @ApiModelProperty("装卸时间")
    private Long shippingTime;

    @Mapping
    @ApiModelProperty("装卸队名称")
    private String handlingTeamName;

    @Mapping
    @ApiModelProperty("负责人名称")
    private String handlingTeamLeaderName;

    @Mapping
    @ApiModelProperty("负责人电话")
    private String teamLeaderPhoneNo;

    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    @Mapping
    @ApiModelProperty("装卸成本")
    private BigDecimal cost;

    private List<HandlingOrderDetailPrintRespDTO> handlingOrderDetailPrintRespDTOList;
}
