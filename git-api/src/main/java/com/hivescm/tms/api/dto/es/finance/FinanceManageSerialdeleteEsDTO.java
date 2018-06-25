package com.hivescm.tms.api.dto.es.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author sql
 * @Date 15:402018\5\18 0018
 */
@Data
public class FinanceManageSerialdeleteEsDTO implements Serializable {

    private static final long serialVersionUID = 6365521677056410097L;


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
