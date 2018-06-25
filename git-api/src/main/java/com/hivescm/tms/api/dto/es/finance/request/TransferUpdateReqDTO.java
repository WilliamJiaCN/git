package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author sql
 * @Date 12:482018\5\22 0022
 */
@Data
public class TransferUpdateReqDTO {

    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;
    @Mapping
    @ApiModelProperty("转账单号")
    private String transferOrdersource;

    @Mapping
    @ApiModelProperty("转账单号ID")
    private Long submitBillId;

    @Mapping
    @ApiModelProperty("转账类型 1.转账 2.汇款 3. 调拨")
    private Integer transferType;

    @Mapping
    @ApiModelProperty("转账类型名称")
    private String transferTypeName;

    @Mapping
    @ApiModelProperty("转账账号")
    private String transferAccount;

    @Mapping
    @ApiModelProperty("进账账号")
    private String incomeAccount;

    @Mapping
    @ApiModelProperty("转账金额")
    private BigDecimal transferAmount;

    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    @Mapping
    @ApiModelProperty("来源单ID集合")
    private List<Long> serialIdList;


}
