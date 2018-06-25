package com.hivescm.tms.api.dto.es.finance;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 确认收银
 * @author 杨彭伟
 * @date 2017-11-24 14:05
 */
@Data
@ToString
public class FinanceReceiptConfirmDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司id", required = true)
    private Long companyId;
    @ApiModelProperty(value = "收款记录id", required = true)
    private List<Long> id;
    @ApiModelProperty(value = "收款账户", required = true)
    private String receiptBankAccount;
    @ApiModelProperty(value = "操作人id", required = true)
    private Integer operatingUserId;
    @ApiModelProperty(value = "操作人名称", required = true)
    private String operatingUser;


}
