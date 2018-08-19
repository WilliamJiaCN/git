package com.hivescm.tms.api.dto.es.distribution.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DistributionWaybillDetailsRespDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 发货人
     */
    private @Mapping @ApiModelProperty("发货人") String invoiceUser;
    /**
     * 发货人手机号码
     */
    private @Mapping @ApiModelProperty("发货人手机号码") String invoiceMobleNo;
    /**
     * 发货人详细地址
     */
    private @Mapping @ApiModelProperty("发货人详细地址") String invoiceAddress;
    /**
     * 收货人
     */
    private @Mapping @ApiModelProperty("收货人") String receiptUser;
    /**
     * 收货人手机号码
     */
    private @Mapping @ApiModelProperty("收货人手机号码") String receiptConsignorMobleNo;
    /**
     * 收货人详细地址
     */
    private @Mapping @ApiModelProperty("收货人详细地址") String receiptAddress;
    /**
     * 是否整车
     */
    private @Mapping @ApiModelProperty("是否整车") Boolean iTruckLoad;
    /**
     * 货物名称
     */
    private @Mapping @ApiModelProperty("货物名称") String goodsName;
    /**
     * 总体积
     */
    private @Mapping @ApiModelProperty("总体积") String volume;

    /**
     * 总重量
     */
    private @Mapping @ApiModelProperty("总重量") String weight;
    /**
     * 件数
     */
    private @Mapping("totalNum") @ApiModelProperty("件数") Integer packageNum;
    /**
     * 回单要求
     */
    private @Mapping @ApiModelProperty("回单要求") String backType;
    /**
     * 特殊要求
     */
    private @Mapping @ApiModelProperty("特殊要求") String specialRequire;
    /**
     * 总运费
     */
    private @Mapping @ApiModelProperty("总运费") String totalFee;
    /**
     * 到付
     */
    private @Mapping @ApiModelProperty("到付") String cod;
    /**
     * 代收货款
     */
    private @Mapping @ApiModelProperty("代收货款") String collectPayment;
    /**
     * 应收总金额
     */
    private @Mapping @ApiModelProperty("应收总金额") String aggregateAmount;
    /**
     * 运单号
     */
    private @Mapping @ApiModelProperty("运单号") String waybillCode;
    /**
     * 运单ID
     */
   
    private @Mapping("id") @ApiModelProperty("运单ID") Long id;
   
    /**
     * 预约提货时间
     */
    
	private @Mapping @ApiModelProperty("预约提货时间") Long deliveryPickTime;
    
    /**
     * 创建时间
     */
    private @Mapping @ApiModelProperty("创建时间") Long createTime;
    
    /**
     * 任务名称
     */
    private @Mapping @ApiModelProperty("任务名称") String taskName;

   
}
