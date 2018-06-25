package com.hivescm.tms.api.dto.es.finance.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrgInfoDTO {
    /**
     * 核算组织ID
     */
    private Integer accountOrgId;
    /**
     * 核算组织名
     */
    private String accountOrgName;
}
