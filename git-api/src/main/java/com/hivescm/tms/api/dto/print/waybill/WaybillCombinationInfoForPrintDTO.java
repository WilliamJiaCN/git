package com.hivescm.tms.api.dto.print.waybill;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运单组合信息查询-打印（给马勇）
 * @author lifan
 *
 * 2017年12月29日
 *
 */
@Data
@ToString
public class WaybillCombinationInfoForPrintDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2896562918310337368L;
	
	private @Mapping  @ApiModelProperty("发货方名称")String invoiceCompany;
	
	private @Mapping  @ApiModelProperty("发货人")String invoiceUser;
	
	private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;
	
	private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;
	
	private @Mapping  @ApiModelProperty("收货方名称")String receiptCompany;
	
	private @Mapping  @ApiModelProperty("收货人")String receiptUser;
	
	private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
	
	private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;
	
	private @Mapping  @ApiModelProperty("商品种类")Integer goodsTypeNum = 0;
	
	private @Mapping  @ApiModelProperty("包裹总箱数")Integer totalBoxNum = 0;
	
	private @Mapping  @ApiModelProperty("商品总数量")Integer totalPackageNum = 0;
	
	private @Mapping  @ApiModelProperty("商品总重量")BigDecimal totalWeight = new BigDecimal(0);
	
	private @Mapping  @ApiModelProperty("商品总体积")BigDecimal totalVolume = new BigDecimal(0);
	
	private @Mapping  @ApiModelProperty("代收货款") BigDecimal deliveryFee = new BigDecimal(0);  //也是签收单的应收货款
	
	private @Mapping  @ApiModelProperty("签收件数") Integer signNumber = 0;
	
	private @Mapping  @ApiModelProperty("签收重量") BigDecimal signWeight = new BigDecimal(0);
	
	private @Mapping  @ApiModelProperty("签收体积") BigDecimal signVolume = new BigDecimal(0);
	
    private @Mapping  @ApiModelProperty("销退单类型") Integer returnType;
    
    private @Mapping  @ApiModelProperty("实收代收货款")BigDecimal goodsPayment;
	
	
	/********************************************以下字段暂时不用**************************************************/
	
//	private @Mapping  @ApiModelProperty("发货人国家名称")String invoiceCountryName;
//	
//	private @Mapping  @ApiModelProperty("发货人省名称")String invoiceProvName;
//
//	private @Mapping  @ApiModelProperty("发货人城市名称")String invoiceCityName;
//
//	private @Mapping  @ApiModelProperty("发货人地区名称")String invoiceDistrictName;
//
//	private @Mapping  @ApiModelProperty("发货人街道名称")String invoiceStreetName;

//	private @Mapping  @ApiModelProperty("发货人详细地址-上面所有发货地址的组合，反给前端")String invoiceAddressName;
	
//	private @Mapping  @ApiModelProperty("收货人国家名称")String receiptCountryName;
//	
//	private @Mapping  @ApiModelProperty("收货人省名称")String receiptProvName;
//	
//	private @Mapping  @ApiModelProperty("收货人城市名称")String receiptCityName;
//	
//	private @Mapping  @ApiModelProperty("收货人地区名称")String receiptDistrictName;
//	
//	private @Mapping  @ApiModelProperty("收货人街道名称")String receiptStreetName;
	
//	private @Mapping  @ApiModelProperty("收货人详细地址-上面所有收货地址的组合，反给前端")String receiptAddressName;
	
	
}
