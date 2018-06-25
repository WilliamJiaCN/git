package com.hivescm.tms.api.dto.es.finance.query;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author sql
 * @Date 19:042018\6\1 0001
 */
@Data
@ToString
public class FinanceManageRecyleQueryDTO implements Serializable {
    private static final long serialVersionUID = 4684698498658503285L;

    /**
     * 运单号
     */
    private String waybillCode;
    /**
     * 运单ID
     */
    private Long waybillId;
    /**
     * 公司ID
     */
    private Long companyId;

}
