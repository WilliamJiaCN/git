package com.hivescm.tms.api.dto.print.transport;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 发车配载打印发车信息
 * @author Administrator
 *
 */
@Data
@ToString
public class TransportInfoPrintDTO implements Serializable {

   
	private static final long serialVersionUID = -34171921714289720L;


    @Mapping
    @ApiModelProperty("主键")
    private Long id;

   
    @Mapping
    @ApiModelProperty("发车批次 ， 自动生成")
    private String departBatch;
    
   
    @ApiModelProperty("终端网点")
    private String arrivalBranchName;
    
    @Mapping
    @ApiModelProperty("发车时间")
    private Long departTime;
    
    @Mapping
    @ApiModelProperty("预计到达时间")
    private Long estimatedArrivalTime;
    
    @Mapping
    @ApiModelProperty("车辆类型名称")
    private String vehicleTypeName;

    @Mapping
    @ApiModelProperty("车牌号码")
    private String vehicleNo;
    
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    
    @Mapping
    @ApiModelProperty("司机手机号码")
    private String driverPhoneNo;
    
    @Mapping
    @ApiModelProperty("挂车号码")
    private String trailerNo;
    
    @Mapping
    @ApiModelProperty("保险单号")
    private String policyNo;
    
    @Mapping
    @ApiModelProperty(value = "保险费用", example = "111.11")
    private BigDecimal insurancePremium;
    
    @Mapping
    @ApiModelProperty("当前网点铅封号")
    private String sealNo;
    
    @ApiModelProperty("当前网点配载员姓名")
    private String stowageName;
    
    @Mapping
    @ApiModelProperty("当前网点备注信息")
    private String remark;
    
    @Mapping
    @ApiModelProperty("运输线路")
    private String transportLine;
    
    @Mapping
    @ApiModelProperty("装载率 重量")
    private Integer loadFactorWeight;

    @Mapping
    @ApiModelProperty("装载率 体积")
    private Integer loadFactorVolume;
    
	@Mapping
	@ApiModelProperty("发车网点")
    private String departBranchName;
	
	@Mapping
    @ApiModelProperty("总票数")
    private Integer waybillAmount;
	
	@Mapping
    @ApiModelProperty(value = "总重量")
    private BigDecimal totalWeight;
	
	@Mapping
    @ApiModelProperty(value = "总体积")
    private BigDecimal totalVolume;
	
	 @Mapping
	 @ApiModelProperty(value = "总运费")
	  private BigDecimal totalFreight;
	 
	 @Mapping
	 @ApiModelProperty(value = "总产值")
	  private BigDecimal outputValue;
	 
	  @Mapping
	  @ApiModelProperty(value = "总业务费")
	  private BigDecimal businessFee;
	  
	  @Mapping
	  @ApiModelProperty(value = "代收货款合计")
	  private BigDecimal collectPayment;
	  
	  @Mapping
	  @ApiModelProperty(value = "发车成本")
	   private BigDecimal departCost;
}