package com.hivescm.tms.finance.server.dao.entity.finance;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author 
 */
@Data
@ToString
public class AccountCheckingManagementDO implements Serializable {
    /**
     * 主键
     */
	@Mapping
	@ApiModelProperty("主键")
    private Long id;

    /**
     * 公司id
     */
	@Mapping
	@ApiModelProperty("公司id")
    private Long companyId;

    /**
     *  对账日期
     */
	@Mapping
	@ApiModelProperty("对账日期")
    private Long accountCheckingTime;

    /**
     * 运单号
     */
	@Mapping
	@ApiModelProperty("运单号")
    private String waybillCode;

    /**
     * 订单编号
     */
	@Mapping
	@ApiModelProperty("订单编号")
    private String orderCode;

    /**
     * 支付方式
     */
	@Mapping
	@ApiModelProperty("支付方式")
    private String payModel;

    /**
     * 交易流水号
     */
	@Mapping
	@ApiModelProperty("交易流水号")
    private String tradeSerialNo;

    /**
     * 交易类型
     */
	@Mapping
	@ApiModelProperty("交易类型")
    private String tradeModel;

    /**
     * 支付金额
     */
	@Mapping
	@ApiModelProperty("支付金额")
    private BigDecimal payFee;

    /**
     * 退款金额
     */
	@Mapping
	@ApiModelProperty("退款金额")
    private BigDecimal rebackFee;

    /**
     * 微信支付状态
     */
	@Mapping
	@ApiModelProperty("微信支付状态")
    private String wechatPayStatus;  //(1,成功    2，失败)

    /**
     * 支付平台交易状态
     */
	@Mapping
	@ApiModelProperty("支付平台交易状态")
    private String paymentPlatformPayModel;  //(1,成功    2，失败)

    /**
     * 调账结果
     */
	@Mapping
	@ApiModelProperty("调账结果")
    private String accountRegulationResult;  //(1,成功    2，失败)

    /**
     * 对账结果
     */
	@Mapping
	@ApiModelProperty("对账结果")
    private String accountCheckingResult;  //(1,正常    2，异常    3,挂账 )

    /**
     * 创建时间
     */
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	
	@Mapping
	@ApiModelProperty("平台支付时间")
	private Long tradeTime;
	
	@Mapping
	@ApiModelProperty("交易货币类型")
	private String tradeMoneyType = "RMB";

    private static final long serialVersionUID = 1L;

    
}