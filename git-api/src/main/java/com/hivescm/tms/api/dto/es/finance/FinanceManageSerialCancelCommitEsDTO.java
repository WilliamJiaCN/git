package com.hivescm.tms.api.dto.es.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import lombok.Data;
import lombok.ToString;

/**
 * @Author sql
 * @Date 15:402018\5\17 0017
 */
@Data
@ToString
public class FinanceManageSerialCancelCommitEsDTO {

    @Mapping
    private Long submitBillId;
    @Mapping
    private Long submitBillTime;
    @Mapping
    private Integer submitBillState;
    @Mapping
    private String submitBillStateName;


}
