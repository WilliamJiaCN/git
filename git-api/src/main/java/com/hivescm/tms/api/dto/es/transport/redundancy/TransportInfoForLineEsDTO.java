package com.hivescm.tms.api.dto.es.transport.redundancy;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运输批次线路途经网点冗余运输批次信息
 *
 * @author 李洪春
 * @since 2017/9/5 16:05
 */
@Data
@ToString
public class TransportInfoForLineEsDTO implements Serializable {

    /**
     * 冗余字段
     */
    private static final long serialVersionUID = 2193831824446243531L;

    /**
     * 主键
     */
    @Mapping("transportId")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 发车批次 ， 自动生成
     */
    @Mapping("batchCode")
    @ApiModelProperty("发车批次 ， 自动生成")
    private String departBatch;

    /**
     * 发车类型  1、干线 2、短途
     */
    @Mapping
    @ApiModelProperty("发车类型  1、干线 2、短途")
    private Integer departType;

    /**
     * 发车网点id
     */
    @Mapping
    @ApiModelProperty("发车网点id")
    private Integer departBranchId;

    /**
     * 目标网点编码
     */
    @Mapping
    @ApiModelProperty("目标网点编码")
    private Integer arrivalBranchId;

    /**
     * 运输批次发车时间
     */
    @Mapping
    @ApiModelProperty("运输批次发车时间")
    private Long batchDepartTime;

    /**
     * 运输批次预计到达时间
     */
    @Mapping
    @ApiModelProperty("运输批次预计到达时间")
    private Long batchEstimatedArrivalTime;

    /**
     * 车辆类型
     */
    @Mapping
    @ApiModelProperty("车辆类型")
    private Integer vehicleType;

    /**
     * 车辆id
     */
    @Mapping
    @ApiModelProperty("车辆id")
    private Integer vehicleId;

    /**
     * 司机id
     */
    @Mapping
    @ApiModelProperty("司机id")
    private Integer driverId;

    /**
     * 保险单号
     */
    @Mapping
    @ApiModelProperty("保险单号")
    private String policyNo;

    /**
     * 保险费用
     */
    @Mapping
    @ApiModelProperty(value = "保险费用", example = "111.11")
    private BigDecimal insurancePremium;

    /**
     * 费用分摊方式
     */
    @Mapping
    @ApiModelProperty("费用分摊方式")
    private Integer shareWay;

    /**
     * 发车成本
     */
    @Mapping
    @ApiModelProperty(value = "发车成本", example = "111.11")
    private BigDecimal departCost;

    /**
     * 备注信息
     */
    @Mapping
    @ApiModelProperty("备注信息")
    private String remark;

    /**
     * 装载率 重量
     */
    @Mapping("transportLoadFactorWeight")
    @ApiModelProperty("装载率 重量")
    private Integer loadFactorWeight;

    /**
     * 装载率 体积
     */
    @Mapping("transportLoadFactorVolume")
    @ApiModelProperty("装载率 体积")
    private Integer loadFactorVolume;

    /**
     * 总票数
     */
    @Mapping
    @ApiModelProperty("总票数")
    private Integer waybillAmount;

    /**
     * 总重量
     */
    @Mapping
    @ApiModelProperty(value = "总重量", example = "111.11")
    private BigDecimal totalWeight;

    /**
     * 总体积
     */
    @Mapping
    @ApiModelProperty(value = "总体积", example = "111.11")
    private BigDecimal totalVolume;

    /**
     * 总件数
     */
    @Mapping
    @ApiModelProperty("总件数")
    private Integer goodsAmount;

    /**
     * 总产值
     */
    @Mapping
    @ApiModelProperty(value = "总产值", example = "111.11")
    private BigDecimal outputValue;

    /**
     * 总业务费
     */
    @Mapping
    @ApiModelProperty(value = "总业务费", example = "111.11")
    private BigDecimal businessFee;

    /**
     * 代收货款合计
     */
    @Mapping
    @ApiModelProperty(value = "代收货款合计", example = "111.11")
    private BigDecimal collectPayment;

    /**
     * 途经装货件数
     */
    @Mapping
    @ApiModelProperty("途经装货件数")
    private Integer loadAmount;

    /**
     * 途经卸货件数
     */
    @Mapping
    @ApiModelProperty("途经卸货件数")
    private Integer unloadAmount;

    /**
     * 发车确认人ID
     */
    @Mapping("transportDepartConfirmUserId")
    @ApiModelProperty("发车确认人ID")
    private Integer departConfirmUserId;

    /**
     * 发车确认时间
     */
    @Mapping("transportDepartConfirmTime")
    @ApiModelProperty("发车确认时间")
    private Long departConfirmTime;

    /**
     * 未发车、在途中、已到车、已到货
     */
    @Mapping("transportStatus")
    @ApiModelProperty("未发车、在途中、已到车、已到货")
    private Integer status;

    /**
     * 取消发车人
     */
    @Mapping("transportCancelDepartUserId")
    @ApiModelProperty("取消发车人")
    private Integer cancelDepartUserId;

    /**
     * 取消发车时间
     */
    @Mapping("transportCancelDepartTime")
    @ApiModelProperty("取消发车时间")
    private Long cancelDepartTime;


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>冗余字段<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>运输批次信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    /**
     * 发车网点名称
     */
    @ApiModelProperty("发车网点名称")
    private String departBranchName;

    /**
     * 目标网点名称
     */
    @ApiModelProperty("目标网点名称")
    private String arrivalBranchName;

    /**
     * 发车确认人名称
     */
    @Mapping("transportDepartConfirmUserName")
    @ApiModelProperty("发车确认人名称")
    private String departConfirmUserName;


    /**
     * 取消发车人姓名
     */
    @Mapping("transportCancelDepartUserName")
    @ApiModelProperty("取消发车人姓名")
    private String cancelDepartUserName;

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
     * 更新人姓名
     */
    @Mapping("transportUpdateUserName")
    @ApiModelProperty("更新人姓名")
    private String updateUserName;

    /**
     * 创建人姓名
     */
    @Mapping("transportCreateUserName")
    @ApiModelProperty("创建人姓名")
    private String createUserName;


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>车辆信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    /**
     * 承运商ID
     */
    @ApiModelProperty("承运商ID")
    private Integer carrierId;

    /**
     * 承运商名称
     */
    @ApiModelProperty("承运商名称")
    private Integer carrierName;


    /**
     * 车辆皮重（KG）
     */
    @ApiModelProperty("车辆皮重")
    private Integer vehicleWeight;

    /**
     * 核载质量（KG）
     */
    @ApiModelProperty("核载质量")
    private Integer checkQuality;

    /**
     * 核载体积（M3）
     */
    @ApiModelProperty("核载体积")
    private Integer checkVolume;

    /**
     * 车型
     */
    @ApiModelProperty("车型ID")
    private Integer vehicleModels;

    /**
     * 车型名称
     */
    @ApiModelProperty("车型名称")
    private Integer vehicleModelName;

    /**
     * 车辆长度(M)
     */
    @ApiModelProperty("车辆长度")
    private Integer vehicleLength;

    /**
     * 车辆宽度(M)
     */
    @ApiModelProperty("车辆宽度")
    private Integer vehicleWidth;

    /**
     * 车辆高度(M)
     */
    @ApiModelProperty("车辆高度")
    private Integer vehicleHeight;

}
