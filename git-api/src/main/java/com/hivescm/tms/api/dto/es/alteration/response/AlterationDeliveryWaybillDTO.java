package com.hivescm.tms.api.dto.es.alteration.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 查询运单信息
 * @author ke.huang
 * @date 2018年5月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class AlterationDeliveryWaybillDTO implements Serializable {
	private static final long serialVersionUID = -4004646839313523459L;

	private @ApiModelProperty("运单id") Long waybillId;
	private @ApiModelProperty("发货网点名称	") String invoiceOrgName;
	private @ApiModelProperty("运输线路名称	") String lineName;
	private @ApiModelProperty("目的网点名称")String destOrgName;
	private @ApiModelProperty("商品名称") String goodsName;
	private @ApiModelProperty("总体积") BigDecimal volume;
	private @ApiModelProperty("总重量") BigDecimal weight;
	private @ApiModelProperty("总件数") Integer totalNum;
	private @ApiModelProperty("付款方式名称") String payWayName;
    private @ApiModelProperty("代收货款") BigDecimal orderGoodsPayment;
	private @ApiModelProperty("送货费") BigDecimal deliveryFee;
	private @ApiModelProperty("发货公司名称")String invoiceCompany;
	private @ApiModelProperty("发货公司ID")Integer invoiceCustomerId;
	private @ApiModelProperty("发货人")String invoiceUser;
	private @ApiModelProperty("发货人id")Long invoiceUserId;
	private @ApiModelProperty("发货人电话")String invoiceTelNo;
	private @ApiModelProperty("发货人手机号码")String invoiceMobleNo;
	private @ApiModelProperty("发货人详细地址")String invoiceAddress;
	private @ApiModelProperty("收货公司ID") Integer receiptCustomerId;
	private @ApiModelProperty("收货公司名称")String receiptCompany;
	private @ApiModelProperty("收货人")String receiptUser;
	private @ApiModelProperty("收货人Id") Long receiptUserId; 
	private @ApiModelProperty("收货人电话")String receiptConsignorTelNo;
	private @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
	private @ApiModelProperty("收货人详细地址")String receiptAddress;
	private @ApiModelProperty("配送方式名称") String distributionTypeName;
	private @ApiModelProperty("运单备注")String remark;
	private @ApiModelProperty("到付运费")BigDecimal cod;
}