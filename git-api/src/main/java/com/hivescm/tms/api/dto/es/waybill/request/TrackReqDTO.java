package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class TrackReqDTO implements Serializable {
    private static final long serialVersionUID = -1634013501494229091L;
    @Mapping
    @ApiModelProperty("公司ID")
    private Integer companyId;
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;
    @Mapping
    @ApiModelProperty("运单code")
    private String waybillCode;

    @ApiModelProperty("运单编号集合")
    private List<String> waybillCodes;

    @ApiModelProperty("订单编号集合")
    private List<String> orderCodes;

    public TrackReqDTO() {

    }

    public TrackReqDTO(Integer companyId, String waybillCode) {
        this.companyId = companyId;
        this.waybillCode = waybillCode;
    }
}
