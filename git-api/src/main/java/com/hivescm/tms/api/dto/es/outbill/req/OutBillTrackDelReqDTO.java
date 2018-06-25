package com.hivescm.tms.api.dto.es.outbill.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutBillTrackDelReqDTO {

    private Long companyId;

    private Long trackId;
}
