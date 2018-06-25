package com.hivescm.tms.api.dto.es.finance;

import com.hivescm.framework.elasticsearch.annotation.Cascade;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class FinanceManageCashTransferEsDTO implements Serializable{
    private static final long serialVersionUID = 3019700568941282118L;

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
    @ApiModelProperty("转账类型 1.转账 2.汇款 3. 调拨")
    private Integer transferType;

    /**
     * 转账类型名称
     */
    @Mapping
    @ApiModelProperty("转账类型名称 1.转账 2.汇款 3. 调拨")
    private String transferTypeName;

    /**
     * 转账网点ID
     */
    @Mapping
    @ApiModelProperty("转账网点ID")
    private Integer transferNetworkId;

    /**
     * 转账网点名称
     */
    @Mapping
    @ApiModelProperty("转账网点名称")
    private String transferNetworkName;

    /**
     * 转账账号归属
     */
    @Mapping
    @ApiModelProperty("转账账号归属id")
    private Integer transferAccountBelongId;
    
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
    @ApiModelProperty("转账状态 1.未提交 2.已提交 3.已进账")
    private Integer transferStatus;
    
    /**
     * 转账状态名称
     */
    @Mapping
    @ApiModelProperty("转账状态 名称1.未提交 2.已提交 3.已进账")
    private String transferStatusName;

    /**
     * 进账网点ID
     */
    @Mapping
    @ApiModelProperty("进账网点ID")
    private Integer incomeNetworkId;

    /**
     * 进账网点名称
     */
    @Mapping
    @ApiModelProperty("进账网点名称")
    private String incomeNetworkName;

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
    @ApiModelProperty("进账账号归属id")
    private Integer incomeAccountBelongId;
    
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
     * 进账人姓名
     */
    @Mapping
    @ApiModelProperty("进账人姓名")
    private String incomeUserName;

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
     * 制单人姓名
     */
    @Mapping
    @ApiModelProperty("制单人姓名")
    private String createbillUserName;

    /**
     * 提交时间
     */
    @Mapping
    @ApiModelProperty("提交时间")
    private Long submitTime;

    /**
     * 转账人ID
     */
    @Mapping
    @ApiModelProperty("转账人ID")
    private Integer submitUserId;

    /**
     * 转账人
     */
    @Mapping
    @ApiModelProperty("转账人姓名")
    private String submitUserName;

    /**
     * 取消转账时间
     */
    @Mapping
    @ApiModelProperty("取消转账时间")
    private Long cancelSubmitTime;

    /**
     * 取消转账人ID
     */
    @Mapping
    @ApiModelProperty("取消转账人ID")
    private Integer cancelSubmitUserId;

    /**
     * 取消转账人姓名
     */
    @Mapping
    @ApiModelProperty("取消转账人姓名")
    private String cancelSubmitUserName;

    /**
     * 创建人ID
     */
    @Mapping
    @ApiModelProperty("创建人ID")
    private Integer createUserId;

    /**
     * 创建人姓名
     */
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;


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
     * 修改人姓名
     */
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

}
