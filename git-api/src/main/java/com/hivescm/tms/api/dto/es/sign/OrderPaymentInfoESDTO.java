package com.hivescm.tms.api.dto.es.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lhf 
 * 订单支付信息
 */
@Data
@ToString
public class OrderPaymentInfoESDTO implements Serializable {
	 private static final long serialVersionUID = 1L;
    /**
     * id
     */
	@Mapping
	@ApiModelProperty("主键")
    private Long id;

    /**
     * 支付平台订单号
     */
	@Mapping
	@ApiModelProperty("支付平台订单号")
    private String orderNo;

    /**
     * tms平台订单号
     */
	@Mapping
	@ApiModelProperty("tms平台订单号")
    private String businessOrderNo;

    /**
     * 业务平台编号
     */
	@Mapping
	@ApiModelProperty("业务平台编号")
    private String businessCode;

    /**
     * 订单金额(以分为单位)
     */
	@Mapping
	@ApiModelProperty("订单金额(以分为单位)")
    private BigDecimal amountMoney;

    /**
     * 付款用户id
     */
	@Mapping
	@ApiModelProperty("付款用户id")
    private String userId;

    /**
     * 用户编号
     */
	@Mapping
	@ApiModelProperty("用户编号")
    private String userCode;

    /**
     * 组织编号
     */
	@Mapping
	@ApiModelProperty("组织编号")
    private String organizationCode;

    /**
     * 支付方式
     */
	@Mapping
	@ApiModelProperty("支付方式")
    private String paymentMode;

    /**
     * 支付状态
     */
	@Mapping
	@ApiModelProperty("支付状态")
    private String orderStatus;

    /**
     * 请求支付返回的二维码路径
     */
	@Mapping
	@ApiModelProperty("请求支付返回的二维码路径")
    private String qrcodeurl;

    /**
     * 派车单id
     */
	@Mapping
	@ApiModelProperty("派车单id")
    private Long dispatcherId;

    /**
     * 运单id
     */
	@Mapping
	@ApiModelProperty("运单id")
    private Long waybillId;

    /**
     * 公司id
     */
	@Mapping
	@ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 司机id
     */
	@Mapping
	@ApiModelProperty("司机id")
    private Long driverId;

    /**
     * 创建时间
     */
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 订单状态返回时间
     */
	@Mapping
	@ApiModelProperty("订单状态返回时间")
    private Long orderStatusTime;

    /**
     * 收款人信息
     */
	@Mapping
	@ApiModelProperty("收款人信息")
    private String supplement;

   
/********************************************************************冗余字段********************************************************************************/
	/**
     * 运单号
     */
    @Mapping
    @ApiModelProperty("运单号")
    private String waybillCode;
    
    @Mapping  
    @ApiModelProperty("发货网点ID") 
	private Integer invoiceOrgId;
    @Mapping
    @ApiModelProperty("是否装车")
    private Boolean loaded;
    @Mapping
    @ApiModelProperty("签收类型")
    private Integer signType;

}