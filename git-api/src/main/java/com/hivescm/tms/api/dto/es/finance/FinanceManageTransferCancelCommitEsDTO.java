package com.hivescm.tms.api.dto.es.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import lombok.Data;
import lombok.ToString;

/**
 * @Author sql
 * @Date 15:302018\5\17 0017
 */
@Data
@ToString
public class FinanceManageTransferCancelCommitEsDTO {
    @Mapping
    private Long id;
    @Mapping
    private Integer transferStatus;
    @Mapping
    private Long submitTime;
    @Mapping
    private Integer submitUserId;
    @Mapping
    private String submitUserName;
    @Mapping
    private Long cancelSubmitTime;
    @Mapping
    private Integer cancelSubmitUserId;
    @Mapping
    private String cancelSubmitUserName;
    @Mapping
    private String transferStatusName;

}
