package com.hivescm.tms.api.dto.print.TransportArrival;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/6/5 15:24
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class TransportArrivalPrintDTO implements Serializable{


    private static final long serialVersionUID = -4336057627009518723L;


    @ApiModelProperty("到货批次ID")
    private Long id;

    @ApiModelProperty("到达网点名称")
    private String branchName;

    @Mapping
    @ApiModelProperty("到货批次")
    private String arrivalBatch;


    @ApiModelProperty("到货时间")
    private Long arrivalTime;

    @ApiModelProperty("到货成本")
    private BigDecimal arrivalCost;


    @ApiModelProperty("到货备注信息")
    private String remark;

    @ApiModelProperty("发车时间 已用")
    private Long departTime;

    /**
     * 发车批次
     */
    @ApiModelProperty("发车批次")
    private String departBatch;

    @ApiModelProperty("发车网点名称")
    private String departBranchName;

    /**
     * 司机姓名
     */
    @ApiModelProperty("司机姓名")
    private String driverName;

    /**
     * 司机手机号码
     */
    @ApiModelProperty("司机手机号码")
    private String driverPhoneNo;

    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    @ApiModelProperty("总票数")
    private Integer waybillnum;

    @ApiModelProperty("总件数")
    private Integer unloadAmount;

    @ApiModelProperty("总重量")
    private BigDecimal unloadWeight;

    @ApiModelProperty("总体积")
    private BigDecimal unloadVolume;

    @ApiModelProperty("总运费")
    private BigDecimal totalFee;

    @ApiModelProperty("总产值")
    private BigDecimal outputValue;

    @ApiModelProperty("总业务费")
    private BigDecimal businessFee;

}
