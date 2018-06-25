package com.hivescm.tms.api.dto.print.outbill;

import java.math.BigDecimal;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutWaybillForPrintDTO {
	
	@Mapping
	@ApiModelProperty("外发单id")
    private Long outbillId;
	@Mapping
	@ApiModelProperty("运单ID")
	private Long waybillId;
	@Mapping
	@ApiModelProperty("运单号")
	private String waybillCode;

	@Mapping
	@ApiModelProperty("外发单号")
	private String outbillNum;
	
	@Mapping
	@ApiModelProperty("外发备注")
    private String detailRemark;
	
	@Mapping
	@ApiModelProperty("中转运费")
	private BigDecimal transitFreight;

	@Mapping
	@ApiModelProperty("中转提货费")
	private BigDecimal transitTake;

	@Mapping
	@ApiModelProperty("中转送货费")
	private BigDecimal transitSend;

	@Mapping
	@ApiModelProperty("中转其他费用")
	private BigDecimal transitOther;
	
	@Mapping
	@ApiModelProperty("中转结算方式名称")
    private String transitSettlementTypeName;
	
	@Mapping
	@ApiModelProperty("到付")
	private BigDecimal toPay;

	@Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal pay;
	
	@Mapping
	@ApiModelProperty("应收")
	private BigDecimal accountsReceivable;

	@Mapping
	@ApiModelProperty("应付")
	private BigDecimal accountsCost;
	
	@Mapping
	@ApiModelProperty("件数")
	private Integer packageNum;

	@Mapping
	@ApiModelProperty("体积")
	private BigDecimal volume;

	@Mapping
	@ApiModelProperty("重量")
	private BigDecimal weight;
	
	@Mapping
	@ApiModelProperty("运单商品名称")
    private String waybillGoodsNames;
	
	
	@Mapping
	@ApiModelProperty("收货人城市名称")
	private String receiptCityName;

	@Mapping
	@ApiModelProperty("运单录单时间")
	private Long waybillCreateTime;

	@Mapping
	@ApiModelProperty("发货网点名称")
	private String invoiceOrgName;

	@Mapping
	@ApiModelProperty("到达网点名称")
	private String destOrgName;

	@Mapping
	@ApiModelProperty("运单备注")
	private String waybillRremark;

	@Mapping
	@ApiModelProperty("发货人")
	private String invoiceUser;

	@Mapping
	@ApiModelProperty("发货人电话")
	private String invoiceTelNo;

	@Mapping
	@ApiModelProperty("发货人手机号码")
	private String invoiceMobleNo;

	@Mapping
	@ApiModelProperty("发货人详细地址")
	private String invoiceAddress;
	
	/**
	 * 发货方名称		
	 */
	private @Mapping  @ApiModelProperty("发货公司")String invoiceCompany;

	@Mapping
	@ApiModelProperty("收货人")
	private String receiptUser;

	@Mapping
	@ApiModelProperty("收货人电话")
	private String receiptConsignorTelNo;

	@Mapping
	@ApiModelProperty("收货人手机号码")
	private String receiptConsignorMobleNo;

	@Mapping
	@ApiModelProperty("收货人详细地址")
	private String receiptAddress;
	
	private @Mapping  @ApiModelProperty("收货公司")String receiptCompany;
	
	private @Mapping @ApiModelProperty("回单要求") String backType;
	
	private @Mapping  @ApiModelProperty("回单份数") Integer backNum;
	
	private @Mapping  @ApiModelProperty("回单编号")String backCode;
	
   
}
