package com.hivescm.tms.api.dto.print.transport;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 打印 配载运单信息
 * 
 * @author Administrator
 *
 */
@Data
@ToString
public class TransportWaybillPrintDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6497329597770823937L;
	
	@Mapping
    @ApiModelProperty("到货批次ID")
    private Long arrivalId;
	
	@Mapping
    @ApiModelProperty("发车批次ID")
    private Long transportId;

	@ApiModelProperty("运单ID")
	private Long waybillId;
	
	@ApiModelProperty("运单号")
	private String waybillCode;

	@ApiModelProperty("目的地名称")
	private String destName;

	@Mapping
	@ApiModelProperty("发车备注")
	private String departRemark;

	@Mapping
	@ApiModelProperty("货物名称")
	private String goodsName;

	@Mapping
	@ApiModelProperty("装车件数")
	private Integer loadAmount;

	@Mapping
	@ApiModelProperty(value = "装车重量", example = "111.11")
	private BigDecimal loadWeight;

	@Mapping
	@ApiModelProperty(value = "装车体积", example = "111.11")
	private BigDecimal loadVolume;

	@Mapping
	@ApiModelProperty(value = "分摊业务费", example = "111.11")
	private BigDecimal businessFee;

	@Mapping
	@ApiModelProperty(value = "运费", example = "111.11")
	private BigDecimal freight;

	@Mapping
	@ApiModelProperty("发货公司")
	private String invoiceCompany;

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
	@ApiModelProperty("发货人身份证号")
	private String invoiceIdentityCard;

	@Mapping
	@ApiModelProperty("发货人地址")
	private String invoiceAddress;

	@ApiModelProperty("回单要求")
	private String backType;

	@Mapping
	@ApiModelProperty("回单份数")
	private Integer backNum;

	@Mapping
	@ApiModelProperty("回单编号")
	private String backCode;

	@Mapping
	@ApiModelProperty("收货人公司")
	private String receiptCompany;

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
	@ApiModelProperty("收货人地址")
	private String receiptAddress;

	@Mapping
	@ApiModelProperty("配送方式名称")
	private String distributionTypeName;

	@ApiModelProperty("发货网点名称")
	private String invoiceOrgName;
	@Mapping
	@ApiModelProperty("到达网点名称")
	private String destOrgName;

	@Mapping
	@ApiModelProperty("运单备注")
	private String waybillRemark;

}
