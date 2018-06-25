package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WaybillLablePrintReqDTO implements Serializable {

    private static final long serialVersionUID = -2886349666983206778L;

    /**
     * 运单标签ID
     */
    @Logger
    private Long id;

}
