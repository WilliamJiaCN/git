package com.hivescm.tms.api.dto.es.alteration.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.bill.alteration.ReceiveTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 自提改送货修改提交更改数据查询
 * @author ke.huang
 * @date 2018年5月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class AlterationDeliveryChangeUpdateRespDTO implements Serializable{
	private static final long serialVersionUID = 5625443633658369931L;

	private @ApiModelProperty("主键") Long id;

	private @ApiModelProperty("运单id") Long waybillId;
	
	private @ApiModelProperty("运单号") String waybillCode;

	private @ApiModelProperty("(新)收货人姓名") String newReceiptUser;

//	private @ApiModelProperty("(新)收货公司") String newReceiptCompany;
	private @ApiModelProperty("(新)收货人ID") Integer newReceiptUserId;

	private @ApiModelProperty("(新)收货人电话") String newReceiptConsignorTelNo;

	private @ApiModelProperty("(新)收货人手机") String newReceiptConsignorMobleNo;
	
	private @ApiModelProperty("(新)收货地址") String newReceiptAddress;
	
	private @ApiModelProperty("备注") String alterationRemark;
	
	private @ApiModelProperty("预约送货时间") Long neweDeliverySendTime;
	
	private @ApiModelProperty("收货人省ID") String receiptProvId;
	
	private @ApiModelProperty("收货人省名称") String receiptProvName;
	
	private @ApiModelProperty("收货人城市ID") String receiptCityId;
	
	private @ApiModelProperty("收货人城市名称") String receiptCityName;
	
	private @ApiModelProperty("收货人地区ID") String receiptDistrictId;
	
	private @ApiModelProperty("收货人地区名称") String receiptDistrictName;
	
	private @ApiModelProperty("收货人街道ID") String receiptStreetId;
	
	private @ApiModelProperty("收货人街道名称") String receiptStreetName;
	
    private @ApiModelProperty("送货费") BigDecimal deliveryFee;
    
    private @ApiModelProperty("到付送货费") BigDecimal toPayDeliveryFee;
	
	private @ApiModelProperty("付款方名称") String payWayName;
	
	private @ApiModelProperty("付款方式") Integer payWay;
	
	private @ApiModelProperty("收款方") String receiveWayName;
	
	private @ApiModelProperty("收款方") Integer receiveWay;
	
	private @ApiModelProperty("原收货人") String receiptUser;
	
	private @ApiModelProperty("原收货人ID") String receiptUserId;
	
	private @ApiModelProperty("原收货人电话") String receiptConsignorTelNo;
	
	private @ApiModelProperty("原收货人手机") String receiptConsignorMobleNo;
	
	private @ApiModelProperty("原收货公司") String receiptCompany;
	
	private @ApiModelProperty("原收货地址") String receiptAddress;
	
	private @ApiModelProperty("发货网点名称") String invoiceOrgName;
	
	private @ApiModelProperty("运输线路名称	") String lineName;
	
	private @ApiModelProperty("到达网点名称") String destOrgName;
	
	private @ApiModelProperty("发货公司") String invoiceCompany;
	
	private @ApiModelProperty("发货人") String invoiceUser;
	
	private @ApiModelProperty("发货人电话") String invoiceTelNo;
	
	private @ApiModelProperty("发货人手机号码") String invoiceMobleNo;
	
	private @ApiModelProperty("发货人详细地址") String invoiceAddress;
	
	private @ApiModelProperty("商品名称") String goodsName;
	
	private @ApiModelProperty("总体积") BigDecimal volume;
	
	private @ApiModelProperty("总重量") BigDecimal weight;
	
	private @ApiModelProperty("总件数") Integer totalNum;
	
	private @ApiModelProperty("运单备注") String remark;
	
	private @ApiModelProperty("付款方名称") String payUserName;
	
	private @ApiModelProperty("付款方类型") ReceiveTypeEnum receiveType;

	private @ApiModelProperty("到付") BigDecimal cod;

	private @ApiModelProperty("代收货款") BigDecimal orderGoodsPayment;

	@Mapping
	private @ApiModelProperty("配送方式") Integer distributionType;

	@Mapping
	private @ApiModelProperty("配送方式名称") String distributionTypeName;

}
