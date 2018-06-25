package com.hivescm.tms.finance.server.dao.entity.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/4 15:02
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FinanceManageCashSerialDO implements Serializable {

    private static final long serialVersionUID = 5882597061490153312L;

    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 应收/付外键
     */
    @Mapping
    @ApiModelProperty("应付应收外键")
    private Long financeId;

    /**
     * 类型
     */
    @Mapping
    @ApiModelProperty("1.应收 2.应付")
    private Integer type;

    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 来源单号
     */
    @Mapping
    @ApiModelProperty("来源单号")
    private String ordersourceId;

    /**
     * 单据类型
     */
    @Mapping
    @ApiModelProperty("单据类型")
    private Integer codType;

    /**
     * 费用类型
     */
    @Mapping
    @ApiModelProperty("费用类型")
    private String feeType;

    /**
     * 资金账号
     */
    @Mapping
    @ApiModelProperty("资金账号")
    private String fundAccount;

    /**
     * 收入
     */
    @Mapping
    @ApiModelProperty("收入")
    private BigDecimal receiptAmount;

    /**
     * 支出
     */
    @Mapping
    @ApiModelProperty("支出")
    private BigDecimal payAmount;

    /**
     * 结余
     */
    @Mapping
    @ApiModelProperty("结余")
    private BigDecimal surplus;

    /**
     * 收支网点ID
     */
    @Mapping
    @ApiModelProperty("收支网点ID")
    private Integer receiptPayNetworkId;

    /**
     * 核算网点ID
     */
    @Mapping
    @ApiModelProperty("核算网点ID")
    private Integer settleNetworkId;

    /**
     * 备注
     */
    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 付款手续费
     */
    @Mapping
    @ApiModelProperty("付款手续费")
    private BigDecimal serviceCharge;

    /**
     * 支付方式
     */
    @Mapping
    @ApiModelProperty("支付方式")
    private Integer paymentType;

    /**
     * 结算方式
     */
    @Mapping
    @ApiModelProperty("结算方式")
    private Integer settlementMethod;

    /**
     * 支付渠道
     */
    @Mapping
    @ApiModelProperty("支付渠道")
    private Integer payMethod;

    /**
     * 收款单号
     */
    @Mapping
    @ApiModelProperty("收款单号")
    private String receiptCode;

    /**
     * 付款单号
     */
    @Mapping
    @ApiModelProperty("付款单号")
    private String payCode;
    
    /**
     * 交账单号
     */
    @Mapping
    @ApiModelProperty("交账单号")
    private String submitBillCode;

    /**
     * 交账单号
     */
    @Mapping
    @ApiModelProperty("交账单号ID")
    private Long submitBillId;

    /**
     * 交账状态
     */
    @Mapping
    @ApiModelProperty("交账状态")
    private Integer submitBillState;

    /**
     * 制单时间
     */
    @Mapping
    @ApiModelProperty("制单时间")
    private Long createBillTime;

    /**
     * 交账时间
     */
    @Mapping
    @ApiModelProperty("交账时间")
    private Long submitBillTime;

    /**
     * 发生时间
     */
    @Mapping
    @ApiModelProperty("发生时间")
    private Long receiptPayTime;

    /**
     * 状态
     */
    @Mapping
    @ApiModelProperty("1.正常 2.作废")
    private Integer status;

    /**
     * 创建人ID
     *
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
