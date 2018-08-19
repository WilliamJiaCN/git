package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class FinanceManageCashTransferReqDTO implements Serializable {

    private static final long serialVersionUID = 7687291422725017627L;
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;
    @Mapping
    @ApiModelProperty("操作用户ID")
    private Integer userId;
    @Mapping
    @ApiModelProperty("操作用户ID")
    private String userName;
    @Mapping
    @ApiModelProperty(value = "当前网点ID", required = true)
    private Long curentOrgId;

//    @ApiModelProperty(value = "转账网点ID", required = true)
//    private Integer transferCurentOrgId;
    @Mapping
    @ApiModelProperty(value = "转账网点ID", required = true)
    private Integer outCurentOrgId;

    @Mapping
    @ApiModelProperty(value = "转账网点名称", required = true)
    private String outCurentOrgName;

    @Mapping
    @ApiModelProperty(value = "进账网点ID", required = true)
    private Integer inCurentOrgId;

    @Mapping
    @ApiModelProperty(value = "进账网点名称", required = true)
    private String inCurentOrgName;

    @Mapping
    @ApiModelProperty("集团ID")
    private Long groupId;

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
    @ApiModelProperty("来源单号ID集合")
    private List<Long> serialIdList;
}
