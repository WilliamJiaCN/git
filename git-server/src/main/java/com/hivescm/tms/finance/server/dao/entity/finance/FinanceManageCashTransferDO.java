package com.hivescm.tms.finance.server.dao.entity.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class FinanceManageCashTransferDO implements Serializable{
    private static final long serialVersionUID = 3544537162189489685L;
    /**
     * 主键ID
     */
    @Mapping
    @ApiModelProperty("主键ID")
    private Long id;


    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 转账单号
     */
    @Mapping
    @ApiModelProperty("转账单号")
    private String transferOrdersource;

    /**
     * 转账类型
     */
    @Mapping
    @ApiModelProperty("转账类型")
    private Integer transferType;

    /**
     * 转账网点ID
     */
    @Mapping
    @ApiModelProperty("转账网点ID")
    private Integer transferNetworkId;

    /**
     * 转账账号归属
     */
    @Mapping
    @ApiModelProperty("转账账号归属")
    private String transferAccountBelong;

    /**
     * 转账账号
     */
    @Mapping
    @ApiModelProperty("转账账号")
    private String transferAccount;

    /**
     * 转账金额
     */
    @Mapping
    @ApiModelProperty("转账金额")
    private BigDecimal transferAmount;

    /**
     * 转账状态
     */
    @Mapping
    @ApiModelProperty("转账状态")
    private Integer transferStatus;

    /**
     * 进账网点ID
     */
    @Mapping
    @ApiModelProperty("进账网点ID")
    private Integer incomeNetworkId;

    /**
     * 进账账号
     */
    @Mapping
    @ApiModelProperty("进账账号")
    private String incomeAccount;

    /**
     * 进账账号归属
     */
    @Mapping
    @ApiModelProperty("进账账号归属")
    private String incomeAccountBelong;

    /**
     * 进账时间
     */
    @Mapping
    @ApiModelProperty("进账时间")
    private Long incomeTime;

    /**
     * 进账人ID
     */
    @Mapping
    @ApiModelProperty("进账人ID")
    private Integer incomeUserId;

    /**
     * 备注
     */
    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 制单时间
     */
    @Mapping
    @ApiModelProperty("制单时间")
    private Long createbillTime;

    /**
     * 制单人ID
     */
    @Mapping
    @ApiModelProperty("制单人ID")
    private Integer createbillUserId;

    /**
     * 提交时间
     */
    @Mapping
    @ApiModelProperty("提交时间")
    private Long submitTime;

    /**
     * 提交人ID
     */
    @Mapping
    @ApiModelProperty("提交人ID")
    private Integer submitUserId;

    /**
     * 取消提交时间
     */
    @Mapping
    @ApiModelProperty("取消提交时间")
    private Long cancelSubmitTime;

    /**
     * 取消提交人ID
     */
    @Mapping
    @ApiModelProperty("取消提交人ID")
    private Integer cancelSubmitUserId;

    /**
     * 创建人ID
     */
    @Mapping
    @ApiModelProperty("创建人ID")
    private Integer createUserId;

    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 修改人ID
     */
    @Mapping
    @ApiModelProperty("修改人ID")
    private Integer updateUserId;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;


}