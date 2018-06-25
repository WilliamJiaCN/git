package com.hivescm.tms.api.dto.es.outbill.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class OutBillTrackRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("外发跟踪运单信息")
    private OutBillTrackWaybillEsDTO outBillTrackWaybillEsDTO;
    @ApiModelProperty("外发跟踪外发信息（跟踪信息）")
    private List<OutBillEsDTO> outBillEsDTOS;

}
