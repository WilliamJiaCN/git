package com.hivescm.tms.finance.server.dao.entity.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@ToString
public class SignFeeDO {

    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    @Mapping
    @ApiModelProperty("签收主表id")
    private Long signId;

    @Mapping
    @ApiModelProperty("到付运费")
    private BigDecimal toPay;

    @Mapping
    @ApiModelProperty("代收货款")
    private BigDecimal collectPayment;

    @Mapping
    @ApiModelProperty("到付送货费")
    private BigDecimal deliveryFee;

    @Mapping
    @ApiModelProperty("二次派送费")
    private BigDecimal secondDeliveryFee;

    @Mapping
    @ApiModelProperty("结算方式")
    private Integer settlementMethod;

    @Mapping
    @ApiModelProperty("月结客户")
    private Integer monthlyCustomer;
    
    @Mapping
	@ApiModelProperty("到站其他费用")
    private BigDecimal otherFeeStation;

    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("支付渠道")
    private Integer payMethod;

}