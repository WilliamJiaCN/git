package com.hivescm.tms.api.dto.es.finance.query;

import lombok.Data;

/**
 * @Author sql
 * @Date 14:402018\5\15 0015
 */
@Data
public class FinanceManageReceiptQueryDTO {

    /**
     * 运单号
     */
    private String waybillCode;
    /**
     * 是否只查到付和代收货款类型的数据
     */
    private Boolean isQueryPayWay;
    /**
     * 运单ID
     */
    private Long waybillId;
    /**
     * 公司ID
     */
    private Long companyId;

}
