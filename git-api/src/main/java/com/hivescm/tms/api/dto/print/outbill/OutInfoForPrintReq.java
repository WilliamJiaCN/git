package com.hivescm.tms.api.dto.print.outbill;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutInfoForPrintReq {
	
	@ApiModelProperty("外发单id")
    private Long outbillId;
}
