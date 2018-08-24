package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class FinanceManageCashTransferDetailsRespDTO implements Serializable{

    @Mapping
    @ApiModelProperty("转账单号")
    private String transferOrdersource;

    @Mapping
    @ApiModelProperty("转账单号ID")
    private Long id;

    @Mapping
    @ApiModelProperty("转账类型 1.转账 2.汇款 3. 调拨")
    private Integer transferType;

    @Mapping
    @ApiModelProperty("转账类型名称")
    private String transferTypeName;

    @Mapping
    @ApiModelProperty("转账状态 1.未提交 2.已提交 3.已进账")
    private Integer transferStatus;

    @Mapping
    @ApiModelProperty("转账状态 名称1.未提交 2.已提交 3.已进账")
    private String transferStatusName;

    @Mapping
    @ApiModelProperty("转账账号")
    private String transferAccount;

    @Mapping
    @ApiModelProperty("进账账号")
    private String incomeAccount;

    @Mapping
    @ApiModelProperty("转账网点名称")
    private String transferNetworkName;

    @Mapping
    @ApiModelProperty("进账网点名称")
    private String incomeNetworkName;


    @Mapping
    @ApiModelProperty("进账账户所属")
    private Integer incomeAccountBelongId;

    @Mapping
    @ApiModelProperty("进账账户所属名称")
    private String incomeAccountBelong;

    @Mapping
    @ApiModelProperty("转账账号归属id")
    private Integer transferAccountBelongId;

    @Mapping
    @ApiModelProperty("转账金额")
    private BigDecimal transferAmount;

    @Mapping
    @ApiModelProperty("进账网点ID")
    private Integer incomeNetworkId;

    @Mapping
    @ApiModelProperty("转账网点ID")
    private Integer transferNetworkId;

    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    @Mapping
    @ApiModelProperty("提交时间")
    private Long submitTime;

    @Mapping
    @ApiModelProperty("取消转账时间")
    private Long cancelSubmitTime;

    @Mapping
    @ApiModelProperty("现金流水")
    private List<FinanceManageCashTransferAddRespDTO> financeManageCashTransferAddRespDTO;
}
