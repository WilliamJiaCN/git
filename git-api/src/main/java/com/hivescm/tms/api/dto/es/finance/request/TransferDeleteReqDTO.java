package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author sql
 * @Date 12:462018\5\22 0022
 */
@Data
public class TransferDeleteReqDTO {

    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;
    @Mapping
    @ApiModelProperty("转账单号ID")
    private Long submitBillId;

}
