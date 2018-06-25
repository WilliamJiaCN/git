package com.hivescm.tms.finance.server.dao.entity.finance;

import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceManageCashBatchDO {


    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;
    /**
     * 交账单号ID
     */
    @Mapping
    @ApiModelProperty("交账单号Id")
    private Long submitBillId;
    
    /**
     * 交账单号
     */
    @Mapping
    @ApiModelProperty("交账单号")
    private String submitBillCode;

    /**
     * 交账状态
     */
    @Mapping
    @ApiModelProperty("交账状态")
    private Integer submitBillState;
    
    @ApiModelProperty("修改的ID")
    private List<Long> ids;
}
