package com.hivescm.tms.api.dto.es.sign.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 签收状态返回结果
 * @author m5itao
 * @date 2017/12/4
 */
@Data
@ToString
public class SignStatusRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("签收状态")
    private Integer status;

    @ApiModelProperty("运单ID")
    private Long waybillId;

    @ApiModelProperty("运单编号")
    private String code;

    @ApiModelProperty("派车单ID")
    private Long dispatcherId;

    @ApiModelProperty("签收用户名")
    private String receiptUser;

    @ApiModelProperty("签收用户手机号")
    private String receiptConsignorMobleNo;

    @ApiModelProperty("签收类型")
    private Integer signType;

    @ApiModelProperty("支付类型")
    private Integer paymentMethod;

    @ApiModelProperty("签收金额")
    private BigDecimal signAmount;

    @ApiModelProperty("拒收金额")
    private BigDecimal rejectAmount;

    @ApiModelProperty("运费")
    private BigDecimal totalFee;
    
    @ApiModelProperty("代收货款")
    private BigDecimal collectPayment;
    
    @ApiModelProperty("订单号")
    private String  orderCode;
    
    @ApiModelProperty("付款方式")
    private Integer  payWay;

    //解决重复提交问题  03-22

    /**
     *
     * todo
     * ticket 会根据运单id/code 订单号 进行计算
     *
     * ticket 会被带到  签收提交 / 拒收提交 ,后台会检验ticket
     *
     * 有时效性
     */
    @ApiModelProperty("预提交票据")
    private String ticket;

}
