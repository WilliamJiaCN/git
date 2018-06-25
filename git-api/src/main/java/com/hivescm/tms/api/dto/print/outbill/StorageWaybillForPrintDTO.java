package com.hivescm.tms.api.dto.print.outbill;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StorageWaybillForPrintDTO {
	
	@Mapping
    @ApiModelProperty("入库单ID")
    private Long storageId;

	@Mapping
	@ApiModelProperty("运单号")
    private String waybillCode;
	
	@Mapping
    @ApiModelProperty("外发单号")
    private String outbillNum;
	
	@Mapping
	@ApiModelProperty("运单商品名称")
    private String waybillGoodsNames;
	
	@Mapping
	@ApiModelProperty("件数")
    private Integer packageNum;
	
	@Mapping
	@ApiModelProperty("实收件数")
    private Integer realPackageNum;
    
    @Mapping
	@ApiModelProperty("仓库名称")
    private String warehouseName;
    
    @Mapping
    @ApiModelProperty("入库类型名称")
    private String storageTypeName;
    
    @Mapping
    @ApiModelProperty("入库备注")
    private String storageRamark;
    
    @Mapping
	@ApiModelProperty("体积")
    private BigDecimal volume;
	
	@Mapping
	@ApiModelProperty("重量")
    private BigDecimal weight;
	

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
	
	@ApiModelProperty("发货人身份证号")
	@Mapping
	private String invoiceIdentityCard;

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
	/**
	 * 派送方式名称
	 */
	@Mapping
	private @ApiModelProperty("配送方式名称") String distributionTypeName;
}
