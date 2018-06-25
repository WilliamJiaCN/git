package com.hivescm.tms.finance.server.dao.entity.finance;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import lombok.Data;
import lombok.ToString;

/**
 * 财务管理-收款单
 * @author 杨彭伟
 * @data 2017/11/21
 */
@Data
@ToString
public class FinanceReceipt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收款记录id
     */
    @Mapping
    private Long id;

    /**
     * 收款单id boss
     */
    @Mapping
    private Long riId;

    /**
     * 收款单单编号 boss
     */
    @Mapping
    private String receiptCode;

    /**
     * 是否确认收银 1.未确认 2.已确认
     */
    @Mapping
    private Integer confirmReceipt;

    /**
     * 确认收银时间
     */
    @Mapping
    private Long confirmReceiptTime;

    /**
     * 确认收银人
     */
    @Mapping
    private String confirmReceiptUser;

    /**
     * 收款单类型
     */
    @Mapping
    private Integer receiptType;

    /**
     * 来源单据类型(签收单)
     */
    @Mapping
    private String sourceBillType;

    /**
     * 来源单号(签收单号)
     */
    @Mapping
    private String sourceNumber;

    /**
     * 状态(已审批 2)
     */
    @Mapping
    private Integer status;

    /**
     * 单据状态(推式生单 4)
     */
    @Mapping
    private Integer billState;

    /**
     * 单据日期(签收时间)
     */
    @Mapping
    private Long billDate;

    /**
     * 收款组织(承运商财务)
     */
    @Mapping
    private String receivablesOrganization;

    /**
     * 往来对象类型
     */
    @Mapping
    private Integer currentObjectType;

    /**
     * 业务方(商户在承运商集团中的客户ID)
     */
    @Mapping
    private String businessObject;

    /**
     * 付款方(商户在承运商集团中的客户ID)
     */
    @Mapping
    private String payObject;

    /**
     * 应付方(商户在承运商集团中的客户ID)
     */
    @Mapping
    private String payableObject;

    /**
     * 币种
     */
    @Mapping
    private Integer currency;

    /**
     * 收款金额(实收代收货款)
     */
    @Mapping
    private BigDecimal receivableMoney;

    /**
     * 收款手续费
     */
    @Mapping
    private BigDecimal brokerage;

    /**
     * 到账金额(实收代收货款+收款手续费)
     */
    @Mapping
    private BigDecimal arrivalAmountMoney;

    /**
     * 收款类型
     */
    @Mapping
    private String receivablesType;

    /**
     * 收款性质
     */
    @Mapping
    private String receivablesProperty;

    /**
     * 支付方式
     */
    @Mapping
    private String paymentMode;

    /**
     * 结算方式(现金、扫码、转账)
     */
    @Mapping
    private Integer settlementMode;

    /**
     * 收款账户
     */
    @Mapping
    private String receivablesBankAccount;

    /**
     * 付款账户
     */
    @Mapping
    private String payBankAccount;

    /**
     * 支付状态(支付成功 1)
     */
    @Mapping
    private Integer payStatus;

    /**
     * 业务组织(承运商网点)
     */
    @Mapping
    private String businessOrganization;

    /**
     * 应收组织(承运商财务)
     */
    @Mapping
    private Integer receivableOrganization;

    /**
     * 业务员
     */
    @Mapping
    private String salesman;

    /**
     * 业务部门
     */
    @Mapping
    private String department;

    /**
     * 源头单号
     */
    @Mapping
    private String originalBillNumber;

    /**
     * 备注
     */
    @Mapping
    private String comment;

    /**
     * 公司ID
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