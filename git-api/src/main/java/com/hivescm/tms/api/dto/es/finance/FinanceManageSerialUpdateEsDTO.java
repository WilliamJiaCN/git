package com.hivescm.tms.api.dto.es.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author sql
 * @Date 15:112018\6\6 0006
 */
@Data
@ToString
public class FinanceManageSerialUpdateEsDTO implements Serializable {
    private static final long serialVersionUID = -3747852865331727159L;
    @Mapping
    private Long id;
    @Mapping
    private Long submitBillId;
    @Mapping
    private Long submitBillTime;
    @Mapping
    private Integer submitBillState;
    @Mapping
    private String submitBillStateName;
    @Mapping
    private String submitBillCode;
}
