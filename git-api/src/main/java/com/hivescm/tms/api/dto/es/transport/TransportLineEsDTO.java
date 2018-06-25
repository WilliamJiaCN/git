package com.hivescm.tms.api.dto.es.transport;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运输批次途经网点信息
 *
 * @author
 */
@Data
@ToString
public class TransportLineEsDTO implements Serializable {

    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 运输单ID
     */
    @Mapping
    @ApiModelProperty("运输单ID")
    private Long transportId;

    /**
     * 途经网点ID
     */
    @Mapping
    @ApiModelProperty("途经网点ID")
    private Integer branchId;

    /**
     * 网点类型
     */
    @Mapping
    @ApiModelProperty("网点类型， 1、始发网点  2、途经网点  3、目的网点")
    private Integer branchType;

    /**
     * 当前状态
     */
   @Mapping
   @ApiModelProperty("当前状态，1=未到达，2=已到达，3=已发出")
    private Integer status;

    /**
     * 预计到达时间
     */
    @Mapping
    @ApiModelProperty("预计到达时间")
    private Long estimatedArrivalTime;

    /**
     * 操作方式
     */
    @Mapping
    @ApiModelProperty("操作方式 : 1=装卸，2=装，3,=卸")
    private Integer operateType;

    /**
     * 铅封号
     */
    @Mapping
    @ApiModelProperty("铅封号")
    private String sealNo;

    /**
     * 配载员ID
     */
    @Mapping
    @ApiModelProperty("配载员ID")
    private Integer stowagePlannerId;

    /**
     * 途经网点顺序
     */
    @Mapping
    @ApiModelProperty("途经网点顺序")
    private Integer sort;

    /**
     * 网点装车备注信息
     */
    @Mapping
    @ApiModelProperty("网点装车备注信息")
    private String loadRemark;

    /**
     * 网点发车装载率 重量
     */
    @Mapping
    @ApiModelProperty("网点发车装载率 重量")
    private Integer loadFactorWeight;

    /**
     * 网点发车装载率 体积
     */
    @Mapping
    @ApiModelProperty("网点发车装载率 体积")
    private Integer loadFactorVolume;

    /**
     * 到车确认时间
     */
    @Mapping
    @ApiModelProperty("到车确认时间")
    private Long vehicleArrivedTime;

    /**
     * 到车确认人ID
     */
    @Mapping
    @ApiModelProperty("到车确认人ID")
    private Integer vehicleArrivedUserId;

    /**
     * 发车确认人ID
     */
    @Mapping
    @ApiModelProperty("发车确认人ID")
    private Integer departConfirmUserId;

    /**
     * 发车确认时间
     */
    @Mapping
    @ApiModelProperty("发车确认时间")
    private Long departConfirmTime;

    /**
     * 取消发车人
     */
    @Mapping
    @ApiModelProperty("取消发车人")
    private Integer cancelDepartUserId;

    /**
     * 取消发车时间
     */
    @Mapping
    @ApiModelProperty("取消发车时间")
    private Long cancelDepartTime;

    /**
     * 创建人
     */
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 修改人
     */
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    private static final long serialVersionUID = 1L;

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>冗余字段<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 是否可为下一网点
     */
    @Mapping
    @ApiModelProperty("是否可以为下一网点")
    private Boolean canBeNext;

    /**
     * 途经网点名称
     */
    @Mapping
    @ApiModelProperty("网点名称")
    private String branchName;

    /**
     * 配载员姓名
     */
    @ApiModelProperty("配载员姓名")
    private String stowagePlannerName;

    /**
     * 创建人姓名
     */
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /**
     * 修改人姓名
     */
    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    /**
     * 发车确认人姓名
     */
    @Mapping
    @ApiModelProperty("发车确认人姓名")
    private String departConfirmUserName;

    /**
     * 取消发车人姓名
     */
    @Mapping
    @ApiModelProperty("取消发车人姓名")
    private String cancelDepartUserName;

    /**
     * 到车确认人姓名
     */
    @ApiModelProperty("到车确认人姓名")
    private String vehicleArrivedUserName;


    
    
 /** ------------------------------2018年3月23日DO新增字段----------------------------------- **/
    
    /**
     * 途径网点状态
     */
    @Mapping
    @ApiModelProperty("途径网点状态")
    private String lineStatus;
    
    /**
     * 到达时间
     */
    @Mapping
    @ApiModelProperty("到达时间")
    private Long arrivalTime;
    
    /**
     * 说明
     */
    @Mapping
    @ApiModelProperty("说明")
    private String explains;
    
    /** ------------------------------2018年3月30日ES新增字段----------------------------------- **/

    /**
     * 是否已装货
     */
    @ApiModelProperty("是否已装货")
    private Boolean load;
    
    /**
     * 是否已卸货
     */
    @ApiModelProperty("是否已卸货")
    private Boolean unLoad;
    
    
    /**
     * 途径装货总票数
     */
    @Mapping
    @ApiModelProperty("网点发车总票数")
    private Integer loadWaybillAmount;

    /**
     * 途径装货总重量
     */
    @Mapping
    @ApiModelProperty(value = "网点发车总重量", example = "111.11")
    private BigDecimal loadTotalWeight;

    /**
     * 途径装货总体积
     */
    @Mapping
    @ApiModelProperty(value = "网点发车总体积", example = "111.11")
    private BigDecimal loadTotalVolume;

    /**
     * 途径装货总件数
     */
    @Mapping
    @ApiModelProperty("网点发车总件数")
    private Integer loadGoodsAmount;

    /**
     * 途径装货总产值
     */
    @Mapping
    @ApiModelProperty(value = "网点发车总产值", example = "111.11")
    private BigDecimal loadOutputValue;

    /**
     * 途径装货总业务费
     */
    @Mapping
    @ApiModelProperty(value = "网点发车总业务费", example = "111.11")
    private BigDecimal loadBusinessFee;


    /**
     * 途径装货总运费
     */
    @Mapping
    @ApiModelProperty(value = "网点发车总运费",example = "12.12")
    private BigDecimal loadTotalFreight;


    /**
     * 代收货款合计
     */
    @Mapping
    @ApiModelProperty(value = "代收货款合计", example = "111.11")
    private BigDecimal loadCollectPayment;

    
    
    
    
    
    
    
  //>>>>>>>>>>>>>>>>>>>>>>>冗余运输批次信息<<<<<<<<<<<<<<<<<<<<<<<

   /* *//**
     * 发车批次 ， 自动生成
     *//*
    @ApiModelProperty("发车批次 ， 自动生成")
    private String departBatch;

    *//**
     * 发车类型  1、干线 2、短途
     *//*
    @ApiModelProperty("发车类型  1、干线 2、短途")
    private Integer departType;

    *//**
     * 发车网点id
     *//*
    @ApiModelProperty("发车网点id")
    private Integer departBranchId;

    *//**
     * 目标网点编码
     *//*
    @ApiModelProperty("目标网点编码")
    private Integer arrivalBranchId;

    *//**
     * 运输批次发车时间
     *//*
    @ApiModelProperty("运输批次发车时间")
    private Long batchDepartTime;

    *//**
     * 运输批次预计到达时间
     *//*
    @ApiModelProperty("运输批次预计到达时间")
    private Long batchEstimatedArrivalTime;

    *//**
     * 车辆类型
     *//*
    @ApiModelProperty("车辆类型")
    private Integer vehicleType;

    *//**
     * 车辆id
     *//*
    @ApiModelProperty("车辆id")
    private Integer vehicleId;

    *//**
     * 司机id
     *//*
    @ApiModelProperty("司机id")
    private Integer driverId;

    *//**
     * 保险单号
     *//*
    @ApiModelProperty("保险单号")
    private String policyNo;

    *//**
     * 保险费用
     *//*
    @ApiModelProperty(value = "保险费用", example = "111.11")
    private BigDecimal insurancePremium;

    *//**
     * 费用分摊方式
     *//*
    @ApiModelProperty("费用分摊方式")
    private Integer shareWay;

    *//**
     * 发车成本
     *//*
    @ApiModelProperty(value = "发车成本", example = "111.11")
    private BigDecimal departCost;

    *//**
     * 备注信息
     *//*
    @ApiModelProperty("备注信息")
    private String remark;

    *//**
     * 运输批次装载率 重量
     *//*
    @ApiModelProperty("装载率 重量")
    private Integer transportLoadFactorWeight;

    *//**
     * 装载率 体积
     *//*
    @ApiModelProperty("装载率 体积")
    private Integer transportLoadFactorVolume;

    *//**
     * 总票数
     *//*
    @ApiModelProperty("总票数")
    private Integer waybillAmount;

    *//**
     * 总重量
     *//*
    @ApiModelProperty(value = "总重量", example = "111.11")
    private BigDecimal totalWeight;

    *//**
     * 总体积
     *//*
    @ApiModelProperty(value = "总体积", example = "111.11")
    private BigDecimal totalVolume;

    *//**
     * 总件数
     *//*
    @ApiModelProperty("总件数")
    private Integer goodsAmount;

    *//**
     * 总产值
     *//*
    @ApiModelProperty(value = "总产值", example = "111.11")
    private BigDecimal outputValue;

    *//**
     * 总业务费
     *//*
    @ApiModelProperty(value = "总业务费", example = "111.11")
    private BigDecimal businessFee;

    *//**
     * 代收货款合计
     *//*
    @ApiModelProperty(value = "代收货款合计", example = "111.11")
    private BigDecimal collectPayment;

    *//**
     * 途经装货件数
     *//*
    @ApiModelProperty("途经装货件数")
    private Integer loadAmount;

    *//**
     * 途经卸货件数
     *//*
    @ApiModelProperty("途经卸货件数")
    private Integer unloadAmount;

    *//**
     * 发车确认人ID
     *//*
    @ApiModelProperty("发车确认人ID")
    private Integer transportDepartConfirmUserId;

    *//**
     * 发车确认时间
     *//*
    @ApiModelProperty("发车确认时间")
    private Long transportDepartConfirmTime;

    *//**
     * 未发车、在途中、已到车、已到货
     *//*
    @ApiModelProperty("未发车、在途中、已到车、已到货")
    private Integer transportStatus;

    *//**
     * 取消发车人
     *//*
    @ApiModelProperty("取消发车人")
    private Integer transportCancelDepartUserId;

    *//**
     * 取消发车时间
     *//*
    @ApiModelProperty("取消发车时间")
    private Long transportCancelDepartTime;


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>冗余字段<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>运输批次信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    *//**
     * 发车网点名称
     *//*
    @ApiModelProperty("发车网点名称")
    private String departBranchName;

    *//**
     * 目标网点名称
     *//*
    @ApiModelProperty("目标网点名称")
    private String arrivalBranchName;

    *//**
     * 发车确认人名称
     *//*
    @ApiModelProperty("发车确认人名称")
    private String transportDepartConfirmUserName;


    *//**
     * 取消发车人姓名
     *//*
    @ApiModelProperty("取消发车人姓名")
    private String transportCancelDepartUserName;

    *//**
     * 司机姓名
     *//*
    @ApiModelProperty("司机姓名")
    private String driverName;

    *//**
     * 司机手机号码
     *//*
    @ApiModelProperty("司机手机号码")
    private String driverPhoneNo;

    *//**
     * 更新人姓名
     *//*
    @ApiModelProperty("更新人姓名")
    private String transportUpdateUserName;

    *//**
     * 创建人姓名
     *//*
    @ApiModelProperty("创建人姓名")
    private String transportCreateUserName;


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>车辆信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    *//**
     * 车牌号码
     *//*
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    *//**
     * 承运商ID
     *//*
    @ApiModelProperty("承运商ID")
    private Integer carrierId;

    *//**
     * 承运商名称
     *//*
    @ApiModelProperty("承运商名称")
    private Integer carrierName;


    *//**
     * 车辆皮重（KG）
     *//*
    @ApiModelProperty("车辆皮重")
    private Integer vehicleWeight;

    *//**
     * 核载质量（KG）
     *//*
    @ApiModelProperty("核载质量")
    private Integer checkQuality;

    *//**
     * 核载体积（M3）
     *//*
    @ApiModelProperty("核载体积")
    private Integer checkVolume;

    *//**
     * 车型
     *//*
    @ApiModelProperty("车型ID")
    private Integer vehicleModels;

    *//**
     * 车型名称
     *//*
    @ApiModelProperty("车型名称")
    private Integer vehicleModelName;

    *//**
     * 车辆长度(M)
     *//*
    @ApiModelProperty("车辆长度")
    private Integer vehicleLength;

    *//**
     * 车辆宽度(M)
     *//*
    @ApiModelProperty("车辆宽度")
    private Integer vehicleWidth;

    *//**
     * 车辆高度(M)
     *//*
    @ApiModelProperty("车辆高度")
    private Integer vehicleHeight;*/



}