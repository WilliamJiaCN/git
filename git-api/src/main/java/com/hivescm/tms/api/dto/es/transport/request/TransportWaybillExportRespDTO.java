package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransportWaybillExportRespDTO  implements Serializable{
	
	
    @Mapping
    @ApiModelProperty("运单号")
    private String waybillCode;

    private @Mapping @ApiModelProperty("发车批次编码") String departBatch;

    @Mapping
    @ApiModelProperty("目的地")
    private String destName;
    
	private @Mapping @ApiModelProperty("录单时间") Long waybillCreateTime;

    @Mapping
    @ApiModelProperty("发货网点名称")
    private String invoiceOrgName;
    
    @Mapping
    @ApiModelProperty("到达网点名称")
    private String destOrgName;
    
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carrierName;
    
    @Mapping
    @ApiModelProperty("到货类型：正常到货、异常到货")
    private Integer arrivalType;
    
	private @Mapping @ApiModelProperty("商品名称") String goodsName;

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
    @ApiModelProperty(value = "运费", example = "111.11")
    private BigDecimal freight;
    
    @Mapping
    @ApiModelProperty(value = "分摊产值", example = "111.11")
    private BigDecimal outputValue;
    
    
    @Mapping
    @ApiModelProperty(value = "分摊业务费", example = "111.11")
    private BigDecimal businessFee;
    
    @Mapping
    @ApiModelProperty(value = "分摊成本", example = "111.11")
    private BigDecimal shareCost;
    
    @Mapping
    @ApiModelProperty("运单状态名称")
    private String statusName;
    
    @Mapping
    @ApiModelProperty("是否已经卸载")
    private Boolean unload;
    
    
    @Mapping
    @ApiModelProperty("装货网点名称")
    private String loadBranchName;
    
    @ApiModelProperty("装货批次编码")
    private String loadBatch;
    
    @Mapping
    @ApiModelProperty("装货时间")
    private Long loadTime;

    @Mapping
    @ApiModelProperty("到货网点名称")
    private String arriveBranchName;
    
    @Mapping
    @ApiModelProperty("到货批次编码")
    private String arrivalBatch;

    
    @ApiModelProperty("到货时间")
    private Long arriveTime;
    
	private @Mapping @ApiModelProperty("运单备注") String waybillRemark;


    @Mapping
    @ApiModelProperty("发车备注")
    private String departRemark;
    
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
    
    @Mapping
    @ApiModelProperty("代收货款")
    private  BigDecimal goodsPayment;
    
    private @Mapping @ApiModelProperty("回单要求") String backType;
           
    private @Mapping  @ApiModelProperty("回单份数") Integer backNum;
       
    private @Mapping  @ApiModelProperty("回单编号")String backCode;

}
