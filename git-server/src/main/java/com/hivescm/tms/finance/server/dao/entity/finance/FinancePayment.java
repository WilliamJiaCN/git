package com.hivescm.tms.finance.server.dao.entity.finance;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import lombok.Data;
import lombok.ToString;

/**
 * 财务管理-付款记录
 * @author 杨彭伟
 * @date 2017-11-23 17:58
 */
@Data
@ToString
public class FinancePayment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 付款记录id
     */
    @Mapping
    private Long id;

    /**
     * 发放状态 1.未发放 2.已发放
     */
    @Mapping
    private Integer grantStatus;

    /**
     * 发放时间
     */
    @Mapping
    private Long grantTime;

    /**
     * 发放人
     */
    @Mapping
    private String grantUser;

    /**
     * 确认收银时间(来源收款单)
     */
    @Mapping
    private Long confirmReceiptTime;
    /**
     * 付款单id
     */
    @Mapping
    private Long piId;

    /**
     * 付款单编号
     */
    @Mapping
    private String payCode;

    /**
     * 付款单类型
     */
    @Mapping
    private Integer payBillType;

    /**
     * 付款单对应的收款记录id
     */
    @Mapping
    private Long receiptId;

    /**
     * 付款单对应的收款单id
     */
    @Mapping
    private Long riId;

    /**
     * 付款单对应的收款单单号
     */
    @Mapping
    private String receiptCode;

    /**
     * 来源单据类型（订单类型）
     */
    @Mapping
    private String sourceBillType;

    /**
     * 来源单号（运单号）
     */
    @Mapping
    private String sourceCode;

    /**
     * 单据状态
     */
    @Mapping
    private Integer billStatus;

    /**
     * 单据日期
     */
    @Mapping
    private Long billTime;

    /**
     * 付款组织
     */
    @Mapping
    private String payOriginazation;

    /**
     * 往来对象类型
     */
    @Mapping
    private Integer currentObjectType;

    /**
     * 收款方
     */
    @Mapping
    private Integer payeeObject;

    /**
     * 币种
     */
    @Mapping
    private Integer currency;

    /**
     * 付款金额
     */
    @Mapping
    private BigDecimal payMoney;

    /**
     * 付款手续费
     */
    @Mapping
    private BigDecimal serviceCharge;

    /**
     * 实付金额
     */
    @Mapping
    private BigDecimal amountPaid;

    /**
     * 付款类型
     */
    @Mapping
    private Integer payType;

    /**
     * 付款性质
     */
    @Mapping
    private String payCharacter;

    /**
     * 支付方式
     */
    @Mapping
    private String paymentMode;

    /**
     * 结算方式
     */
    @Mapping
    private Integer settlementMode;

    /**
     * 支付渠道
     */
    @Mapping
    private String payChannel;

    /**
     * 付款账户
     */
    @Mapping
    private String payBankAccount;

    /**
     * 收款账户
     */
    @Mapping
    private String receivableBankAccount;

    /**
     * 支付状态 (1.支付成功)
     */
    @Mapping
    private Integer payStatus;

    /**
     * 付款对账状态(对账成功)
     */
    @Mapping
    private Integer checkingStatus;

    /**
     * 业务组织
     */
    @Mapping
    private String businessGroup;

    /**
     * 应付组织
     */
    @Mapping
    private String payGroup;

    /**
     * 业务方
     */
    @Mapping
    private String orderSupplier;

    /**
     * 应收方
     */
    @Mapping
    private String paySupplier;

    /**
     * 业务员
     */
    @Mapping
    private String subordinateClert;

    /**
     * 业务部门
     */
    @Mapping
    private String department;

    /**
     * 源头单号（主订单编号）
     */
    @Mapping
    private String sourceBillNum;

    /**
     * 备注
     */
    @Mapping
    private String remark;

    /**
     * 公司id
     */
    @Mapping
    private Long companyId;

    /**
     * 创建人
     */
    @Mapping
    private Integer createUser;

    /**
     * 创建时间
     */
    @Mapping
    private Long createTime;

    /**
     * 修改人
     */
    @Mapping
    private Integer updateUser;

    /**
     * 修改时间
     */
    @Mapping
    private Long updateTime;
}