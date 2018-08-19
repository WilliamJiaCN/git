package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 账户查询
 * @author 杨彭伟
 * @date 2018-01-06 20:28
 */
@Data
@ToString
public class PaymentAccountListRespDTO {
    @Mapping
    @ApiModelProperty("收款账户列表")
    private List<PaymentAccountRespTDO> receiptAccounts;
    @Mapping
    @ApiModelProperty("付款账户列表")
    private List<PaymentAccountRespTDO> payAccounts;

}
