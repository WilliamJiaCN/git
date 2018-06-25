package com.hivescm.tms.finance.server.dao.query;

import lombok.Data;
import lombok.ToString;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/24
 */

@Data
@ToString
public class SignWaybillDBQueryDTO {
    private Long companyId;
    private String signBatchNumber;
    private Long waybillId;
    private Long startTime;
    private Long endTime;
    private Integer signType;
    private Integer currIndex;
    private Integer pageSize;
}
