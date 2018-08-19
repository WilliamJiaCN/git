package com.hivescm.tms.api.dto.es.transport;

import com.hivescm.framework.elasticsearch.annotation.Cascade;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运输批次基础信息
 *
 * @author 李洪春
 */
@Data
@ToString
@Cascade(value = "transportInfo",fields = {"driverName","departBranchName","departBatch"})
public class TransportInfoEsDTO implements Serializable {

    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 发车批次 ， 自动生成
     */
    @Mapping
    @ApiModelProperty("发车批次 ， 自动生成")
    private String departBatch;

    /**
     * 发车类型  1、干线 2、短途
     */
    @Mapping
    @ApiModelProperty("发车类型  1、短途  2、干线")
    private Integer departType;

    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

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
    @Mapping
    @ApiModelProperty("装载率 重量")
    private Integer loadFactorWeight;

    /**
     * 装载率 体积
     */
    @Mapping
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
     * 总运费
     */
    @Mapping
    @ApiModelProperty(value = "总运费",example = "12.12")
    private BigDecimal totalFreight;

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
     * 未发车、在途中、已到车、已到货
     */
    @Mapping
    @ApiModelProperty("未发车、在途中、已到车、已到货")
    private Integer status;

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


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>运输批次信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 司机姓名
     */
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;

    /**
     * 司机手机号码
     */
    @Mapping
    @ApiModelProperty("司机手机号码")
    private String driverPhoneNo;

    /**
     * 更新人姓名
     */
    @Mapping
    @ApiModelProperty("更新人姓名")
    private String updateUserName;

    /**
     * 创建人姓名
     */
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>起始网点、目标网点 begin<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    /**
     * 发车网点名称
     */
    @Mapping
    @ApiModelProperty("发车网点名称")
    private String departBranchName;

    /**
     * 目标网点名称
     */
    @Mapping
    @ApiModelProperty("目标网点名称")
    private String arrivalBranchName;

  

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>起始网点、目标网点   end<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>车辆信息 begin<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 车牌号码
     */
    @Mapping
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    /**
     * 承运商ID
     */
    @Mapping
    @ApiModelProperty("承运商ID")
    private Integer carrierId;

    /**
     * 承运商名称
     */
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carrierName;

    /**
     * 车辆类型名称
     */
    @Mapping
    @ApiModelProperty("车辆类型名称")
    private String vehicleTypeName;

    /**
     * 车辆皮重（KG）
     */
    @Mapping
    @ApiModelProperty("车辆皮重")
    private BigDecimal vehicleWeight;

    /**
     * 核载质量（KG）
     */
    @Mapping
    @ApiModelProperty("核载质量")
    private BigDecimal checkQuality;

    /**
     * 核载体积（M3）
     */
    @Mapping
    @ApiModelProperty("核载体积")
    private BigDecimal checkVolume;

    /**
     * 车型
     */
    @Mapping
    @ApiModelProperty("车型ID")
    private Integer vehicleModels;

    /**
     * 车型名称
     */
    @Mapping
    @ApiModelProperty("车型名称")
    private String vehicleModelName;

    /**
     * 车辆长度(M)
     */
    @Mapping
    @ApiModelProperty("车辆长度")
    private BigDecimal vehicleLength;

    /**
     * 车辆宽度(M)
     */
    @Mapping
    @ApiModelProperty("车辆宽度")
    private BigDecimal vehicleWidth;

    /**
     * 车辆高度(M)
     */
    @Mapping
    @ApiModelProperty("车辆高度")
    private BigDecimal vehicleHeight;

    /**
     * 挂车号码
     */
    @Mapping
    @ApiModelProperty("挂车号码")
    private String trailerNo;

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>车辆信息 end<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** ------------------------------2018年3月23日新增字段----------------------------------- **/
    /**
     * 发车时间
     */
    @Mapping
    @ApiModelProperty("发车时间 已用")
    private Long departTime;
    
    /**
     * 预计到达时间
     */
    @Mapping
    @ApiModelProperty("预计到达时间")
    private Long estimatedArrivalTime;
    
    /**
     * 到达时间
     */
    @Mapping
    @ApiModelProperty("到达时间")
    private Long arrivalTime;
    
    /**
     * 途径网点状态
     */
    @Mapping
    @ApiModelProperty("途径网点状态")
    private String lineStatus;

    
    /**
     * 铅封号
     */
    @Mapping
    @ApiModelProperty("铅封号")
    private String sealNo;
    
    /**
     * 配载员
     */
    @Mapping(value="stowagePlannerId")
    @ApiModelProperty("配载员")
    private Integer stowageId;
    
    /**
     * 运输线路
     */
    @Mapping
    @ApiModelProperty("运输线路")
    private String transportLine;
    
    /**
     * 发车确认人
     */
    @Mapping
    @ApiModelProperty("发车确认人")
    private Integer transportUser;
    
    /**
     * 发车确认人名称
     */
    @Mapping
    @ApiModelProperty("发车确认人名称")
    private String transportUserName;
    
    /**
     * 发车确认时间
     */
    @Mapping
    @ApiModelProperty("发车确认时间")
    private Long transportTime;
    
    /**
     * 费用分摊方式名称
     */
    @Mapping
    @ApiModelProperty("费用分摊方式名称")
    private String shareWayName;
    
    /** ------------------------------2018年3月29日新增冗余字段----------------------------------- **/
    /**
     * 到车确认操作网点
     */
    @Mapping
    @ApiModelProperty("到车确认操作网点ID")
    private Integer arriveBranchId;

    /**
     * 到车确认操作网点
     */
    @Mapping
    @ApiModelProperty("到车确认操作网点ID")
    private String arriveBranchName;
    
    /**
     * 配载员姓名
     */
    @Mapping
    @ApiModelProperty("配载员姓名")
    private String stowageName;
    /**
     * 
     */
    @ApiModelProperty("集团ID")
    private Long groupId;
    
    @ApiModelProperty("线路状态 1=未到达，2=已到达，3=已发出")
    private Integer branchStatus;

    /**************************************************冗余到货确认字段********************************************/

    @ApiModelProperty("批次明细是否全部卸货")
    private Boolean unloadALL;

    /**
     * 网点类型
     */
    @Mapping
    @ApiModelProperty("网点类型， 1、始发网点  2、途经网点  3、目的网点")
    private Integer branchType;
    
    @Mapping
    @ApiModelProperty("所有网点铅封号")
    private String branchSealNo;
    
    @Mapping
    @ApiModelProperty("所有网点备注信息")
    private String branchRemark;
    
    @Mapping
    @ApiModelProperty("所有网点配载员姓名")
    private String branchStowageName;

    /**
     * 操作方式
     */
    @Mapping
    @ApiModelProperty("操作方式 : 1=装卸，2=装，3,=卸")
    private Integer operateType;
}