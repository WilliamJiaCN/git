package com.hivescm.tms.api.dto.es.finance.component;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 杨彭伟
 * @date 2018-01-12 21:00
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SettleOrgDTO {
    @Mapping
    private Integer settleOrgId;
    @Mapping
    private String settleOrgName;
}
