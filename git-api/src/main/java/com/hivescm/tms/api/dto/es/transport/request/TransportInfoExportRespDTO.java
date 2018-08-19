package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class TransportInfoExportRespDTO  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3516786980954591631L;

	@Mapping
    @ApiModelProperty("发车批次 ， 自动生成")
    private String departBatch;
    
    @Mapping
    @ApiModelProperty("发车时间 已用")
    private Long departTime;

    @Mapping
    @ApiModelProperty("预计到达时间")
    private Long estimatedArrivalTime;
    
    
    @Mapping
    @ApiModelProperty("发车网点名称")
    private String departBranchName;
    
    @Mapping
    @ApiModelProperty("到达网点")
    private String arrivalBranchName;
    
    @Mapping
    @ApiModelProperty("未发车、在途中、已到车、已到货")
    private String statusName;
    
    @Mapping
    @ApiModelProperty("途径网点状态")
    private String lineStatus;
    
    @Mapping
    @ApiModelProperty("车辆类型名称")
    private String vehicleTypeName;
    
    @Mapping
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    @Mapping
    @ApiModelProperty("挂车号码")
    private String trailerNo;
    
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;

    @Mapping
    @ApiModelProperty("司机手机号码")
    private String driverPhoneNo;

    @Mapping
    @ApiModelProperty("承运商名称")
    private String carrierName;
 
    @Mapping
    @ApiModelProperty("运输线路")
    private String transportLine;
    
    @Mapping
    @ApiModelProperty("总票数")
    private Integer waybillAmount;
    
    @Mapping
    @ApiModelProperty(value = "总重量", example = "111.11")
    private BigDecimal totalWeight;

 
    @Mapping
    @ApiModelProperty(value = "总体积", example = "111.11")
    private BigDecimal totalVolume;
    
    @Mapping
    @ApiModelProperty(value = "总运费",example = "12.12")
    private BigDecimal totalFreight;
    
    @Mapping
    @ApiModelProperty(value = "总产值", example = "111.11")
    private BigDecimal outputValue;
    
    @Mapping
    @ApiModelProperty(value = "总业务费", example = "111.11")
    private BigDecimal businessFee;
    
    @Mapping
    @ApiModelProperty(value = "代收货款合计", example = "111.11")
    private BigDecimal collectPayment;
    
    @Mapping
    @ApiModelProperty(value = "发车成本", example = "111.11")
    private BigDecimal departCost;
    
    @Mapping
    @ApiModelProperty("费用分摊方式名称")
    private String shareWayName;
    
    @Mapping
    @ApiModelProperty("装载率 重量")
    private Integer loadFactorWeight;

    @Mapping
    @ApiModelProperty("装载率 体积")
    private Integer loadFactorVolume;
    
    @Mapping
    @ApiModelProperty("途经装货件数")
    private Integer loadAmount;

    @ApiModelProperty("所有网点铅封号")
    private String branchSealNo;
    
    @ApiModelProperty("所有网点配载员姓名")
    private String branchStowageName;
    
    @Mapping
    @ApiModelProperty(value = "保险费用", example = "111.11")
    private BigDecimal insurancePremium;

    @Mapping
    @ApiModelProperty("保险单号")
    private String policyNo;
    
    @Mapping
    @ApiModelProperty("到达时间")
    private Long arrivalTime;
    
    @Mapping
    @ApiModelProperty("所有网点备注信息")
    private String branchRemark;
    
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;
    
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新人姓名")
    private String updateUserName;
    
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;
    
    @Mapping
    @ApiModelProperty("发车确认人名称")
    private String transportUserName;
    
    @Mapping
    @ApiModelProperty("发车确认时间")
    private Long transportTime;
    
}
