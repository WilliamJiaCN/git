package com.hivescm.tms.api.dto.es.transport;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运输批次详情
 *
 * @author 李洪春
 */
@Data
@ToString
public class TransportWaybillDetailEsDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    public TransportWaybillDetailEsDTO() {
    }

    public TransportWaybillDetailEsDTO(Long id, Long arrivalId) {
        this.id = id;
        this.arrivalId = arrivalId;
    }

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
     * 运单ID
     */
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;

    /**
     * 发车备注
     */
    @Mapping
    @ApiModelProperty("发车备注")
    private String departRemark;

    /**
     * 装车件数
     */
    @Mapping
    @ApiModelProperty("装车件数")
    private Integer loadAmount;

    /**
     * 装车重量
     */
    @Mapping
    @ApiModelProperty(value = "装车重量", example = "111.11")
    private BigDecimal loadWeight;

    /**
     * 装车体积
     */
    @Mapping
    @ApiModelProperty(value = "装车体积", example = "111.11")
    private BigDecimal loadVolume;

    /**
     *分摊 运费
     */
    @Mapping
    @ApiModelProperty(value = "运费", example = "111.11")
    private BigDecimal freight;

    /**
     * 分摊产值
     */
    @Mapping
    @ApiModelProperty(value = "分摊产值", example = "111.11")
    private BigDecimal outputValue;

    /**
     * 分摊业务费
     */
    @Mapping
    @ApiModelProperty(value = "分摊业务费", example = "111.11")
    private BigDecimal businessFee;

    /**
     * 分摊代收货款
     */
    @Mapping
    @ApiModelProperty("代收货款")
    private  BigDecimal goodsPayment;

    /**
     * 分摊成本
     */
    @Mapping
    @ApiModelProperty(value = "分摊成本", example = "111.11")
    private BigDecimal shareCost;

    /**
     * 是否已经卸载
     */
    @Mapping
    @ApiModelProperty("是否已经卸载")
    private Boolean unload;

    /**
     * 装货网点ID
     */
    @Mapping
    @ApiModelProperty("装货网点ID")
    private Integer loadBranchId;

    /**
     * 装货时间
     */
    @Mapping
    @ApiModelProperty("装货时间")
    private Long loadTime;

    /**
     * 装货批次
     */
    @Mapping
    @ApiModelProperty("装货批次")
    private Long loadBatchId;

    /**
     * 装货批次
     */
    @Mapping
    @ApiModelProperty("装货批次编码")
    private String loadBatch;
    /**
     * 到货批次
     */
    @Mapping
    @ApiModelProperty("到货批次ID")
    private Long arrivalId;
    
    @Mapping
    @ApiModelProperty("到货批次编码")
    private String arrivalBatch;



    /**
     * 状态信息
     */
    @Mapping
    @ApiModelProperty("状态信息 0 = 已装车 ；1 = 已发车 ；2 = 已到货")
    private Integer status;

    /**
     * 实收件数
     */
    @Mapping
    @ApiModelProperty("实收件数")
    private Integer actualAmount;

    @Mapping
    @ApiModelProperty("实收重量")
    private BigDecimal actualWeight;

    @Mapping
    @ApiModelProperty("实收体积")
    private BigDecimal actualVolume;

    /**
     * 到货仓库ID
     */
    @Mapping
    @ApiModelProperty("到货仓库ID")
    private Integer warehouseId;

    /**
     * 到货仓库名称
     */
    @Mapping
    @ApiModelProperty("到货仓库名称")
    private String warehouseName;

    /**
     * 到货类型：正常到货、异常到货
     */
    @Mapping
    @ApiModelProperty("到货类型：正常到货、异常到货")
    private Integer arrivalType;

    /**
     * 到货备注信息，手动录入
     */
    @Mapping
    @ApiModelProperty("到货备注信息，手动录入")
    private String arrivalRemark;

    /**
     * 到货分摊成本
     */
    @Mapping
    @ApiModelProperty(value = "到货分摊成本", example = "111.11")
    private BigDecimal arrivalShareCost;

    /**
     * 卸货 批次ID
     */
    @Mapping
    @ApiModelProperty("卸货 批次ID")
    private Long unloadBatchId;

    @Mapping
    @ApiModelProperty("卸货网点ID")
    private Integer unloadBranchId;

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

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>冗余基本信息字段<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 装货网点名称
     */
    @Mapping
    @ApiModelProperty("装货网点名称")
    private String loadBranchName;


    /**
     * 卸货网点名称
     */
    @Mapping
    @ApiModelProperty("卸货网点名称")
    private String unloadBranchName;

    /**
     * 创建人姓名
     */
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /**
     * 修改人姓名
     */
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>运单属性字段<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 发货网点ID
     */
    @Mapping
    @ApiModelProperty("发货网点ID")
    private Integer invoiceOrgId;

    /**
     * 发货网点名称
     */
    @Mapping
    @ApiModelProperty("发货网点名称")
    private String invoiceOrgName;
    /**
     * 目的地级别
     */
    @Mapping
    @ApiModelProperty("目的地级别")
    private Integer destLevel;
    /**
     * 目的地id
     */
    @Mapping
    @ApiModelProperty("目的地id")
    private String destId;
    /**
     * 目的地名称
     */
    @Mapping
    @ApiModelProperty("目的地名称")
    private String destName;
    /**
     * 运输线路id
     */
    @Mapping
    @ApiModelProperty("运输线路id")
    private Long lineId;
    /**
     * 运输线路名称
     */
    @Mapping
    @ApiModelProperty("运输线路名称")
    private String lineName;
    /**
     * 到达网点id
     */
    @Mapping
    @ApiModelProperty("到达网点id")
    private Integer destOrgId;

    /**
     * 到达网点名称
     */
    @Mapping
    @ApiModelProperty("到达网点名称")
    private String destOrgName;

    /**
     * 运单编码
     */
    @Mapping
    @ApiModelProperty("运单编码")
    private String waybillCode;

    /**
     * 客户单号
     */
    @Mapping
    @ApiModelProperty("客户单号")
    private String customerOrderCode;

    /**
     * 预约提货时间
     */
    @Mapping
    @ApiModelProperty("预约提货时间")
    private Long deliveryPickTime;
    /**
     * 预约送货时间
     */
    @Mapping
    @ApiModelProperty("预约送货时间")
    private Long deliverySendTime;

    /**
     * 发货人
     */
    @Mapping
    @ApiModelProperty("发货人")
    private String invoiceUser;
    /**
     * 发货单位
     */
    @Mapping
    @ApiModelProperty("发货公司")
    private String invoiceCompany;

    /**
     * 发货人微信号
     */
    @Mapping
    @ApiModelProperty("发货人微信号	")
    private String invoiceWxNo;
    
    /** 
     * 发货人身份证
     */
    @Mapping
    @ApiModelProperty("发货人身份证号")
	private String invoiceIdentityCard;
    /**
     * 发货人电话
     */
    @Mapping
    @ApiModelProperty("发货人电话")
    private String invoiceTelNo;
    /**
     * 发货人手机号码
     */
    @Mapping
    @ApiModelProperty("发货人手机号码")
    private String invoiceMobleNo;
    /**
     * 发货人国家ID
     */
    @Mapping
    @ApiModelProperty("发货人国家ID")
    private String invoiceCountryId;
    /**
     * 发货人国家名称
     */
    @Mapping
    @ApiModelProperty("发货人国家名称")
    private String invoiceCountryName;
    /**
     * 发货人省ID
     */
    @Mapping
    @ApiModelProperty("发货人省ID")
    private String invoiceProvId;
    /**
     * 发货人省名称
     */
    @Mapping
    @ApiModelProperty("发货人省名称")
    private String invoiceProvName;
    /**
     * 发货人城市ID
     */
    @Mapping
    @ApiModelProperty("发货人城市ID")
    private String invoiceCityId;
    /**
     * 发货人城市名称
     */
    @Mapping
    @ApiModelProperty("发货人城市名称")
    private String invoiceCityName;
    /**
     * 发货人地区ID
     */
    @Mapping
    @ApiModelProperty("发货人地区ID")
    private String invoiceDistrictId;
    /**
     * 发货人地区名称
     */
    @Mapping
    @ApiModelProperty("发货人地区名称")
    private String invoiceDistrictName;
    /**
     * 发货人街道ID
     */
    @Mapping
    @ApiModelProperty("发货人街道ID")
    private String invoiceStreetId;
    /**
     * 发货人街道名称
     */
    @Mapping
    @ApiModelProperty("发货人街道名称")
    private String invoiceStreetName;
    /**
     * 发货人详细地址
     */
    @Mapping
    @ApiModelProperty("发货人详细地址")
    private String invoiceAddress;
    /**
     * 收货方ID
     */
    @Mapping
    @ApiModelProperty("收货方ID")
    private Integer receiptCustomerId;
    /**
     * 收货人
     */
    @Mapping
    @ApiModelProperty("收货人")
    private String receiptUser;
    /**
     * 收货单位
     */
    @Mapping
    @ApiModelProperty("收货单位")
    private String receiptCompany;
    /**
     * 收货人身份证号码
     */
    @Mapping
    @ApiModelProperty("收货人身份证号码")
    private String receiptIdentityCard;
    /**
     * 收货人电话
     */
    @Mapping
    @ApiModelProperty("收货人电话")
    private String receiptConsignorTelNo;
    /**
     * 收货人手机号码
     */
    @Mapping
    @ApiModelProperty("收货人手机号码")
    private String receiptConsignorMobleNo;
    /**
     * 收货人国家ID
     */
    @Mapping
    @ApiModelProperty("收货人国家ID")
    private String receiptCountryId;
    /**
     * 收货人国家名称
     */
    @Mapping
    @ApiModelProperty("收货人国家名称")
    private String receiptCountryName;
    /**
     * 收货人省ID
     */
    @Mapping
    @ApiModelProperty("收货人省ID")
    private String receiptProvId;
    /**
     * 收货人省名称
     */
    @Mapping
    @ApiModelProperty("收货人省名称	")
    private String receiptProvName;
    /**
     * 收货人城市ID
     */
    @Mapping
    @ApiModelProperty("收货人城市ID")
    private String receiptCityId;
    /**
     * 收货人城市名称
     */
    @Mapping
    @ApiModelProperty("收货人城市名称")
    private String receiptCityName;
    /**
     * 收货人地区ID
     */
    @Mapping
    @ApiModelProperty("收货人地区ID")
    private String receiptDistrictId;
    /**
     * 收货人地区名称
     */
    @Mapping
    @ApiModelProperty("收货人地区名称")
    private String receiptDistrictName;
    /**
     * 收货人街道ID
     */
    @Mapping
    @ApiModelProperty("收货人街道ID")
    private String receiptStreetId;
    /**
     * 收货人街道名称
     */
    @Mapping
    @ApiModelProperty("收货人街道名称")
    private String receiptStreetName;
    /**
     * 收货人详细地址
     */
    @Mapping
    @ApiModelProperty("收货人详细地址")
    private String receiptAddress;

    /**
     * 业务日期
     */
    @Mapping
    @ApiModelProperty("业务日期")
    private Long bussTime;

    /**
     * 下单日期
     */
    @Mapping
    @ApiModelProperty("下单日期")
    private Long orderDate;

    /**
     * 接单时间
     */
    @Mapping
    @ApiModelProperty("接单日期")
    private Long acceptDate;


    /**
     * 运单状态
     */
    @Mapping
    @ApiModelProperty("运单状态")
    private Integer waybillStatus;
    
    @Mapping
    @ApiModelProperty("运单状态名称")
    private String statusName;
    
    
    private @Mapping @ApiModelProperty("回单要求") String backType;
    
	private @Mapping @ApiModelProperty("回单要求") Integer backTypeValue;
    
    private @Mapping  @ApiModelProperty("回单份数") Integer backNum;
    
    private @Mapping  @ApiModelProperty("回单编号")String backCode;
    
	private @Mapping @ApiModelProperty("商品名称") String goodsName;
	
    private @Mapping @ApiModelProperty("运费结算方式") Integer payWay;
	
	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;
	
	private @Mapping @ApiModelProperty("订单创建时间") Long waybillCreateTime;
	
	private @Mapping @ApiModelProperty("运单备注") String waybillRemark;
	
	private @Mapping @ApiModelProperty("代收货款") BigDecimal orderGoodsPayment;
	
    //>>>>>>>>>>>>>>>>>>>>>>>>>冗余运输批次信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,

  
    
    private @Mapping @ApiModelProperty("发车批次编码") String departBatch;
   
    @Mapping
    @ApiModelProperty("发车类型  1、短途 2、干线")
    private Integer departType;

    @Mapping
    @ApiModelProperty("运输批次发车时间")
    private Long batchDepartTime;
  
    @Mapping
    @ApiModelProperty("预计到达时间")
    private Long estimatedArrivalTime;
    
    @Mapping
    @ApiModelProperty("发车网点id")
    private Integer departBranchId;
    
    @ApiModelProperty("发车网点名称")
    private String departBranchName;

    @Mapping
    @ApiModelProperty("终端网点ID")
    private Integer arrivalBranchId;
    
    @Mapping
    @ApiModelProperty("终端网点网点名称")
    private String arrivalBranchName;

    @Mapping
    @ApiModelProperty("车辆id")
    private Integer vehicleId;
    
    @Mapping
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    @Mapping
    @ApiModelProperty("承运商ID")
    private Long carrierId;
    
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carrierName;

    @Mapping
    @ApiModelProperty("司机id")
    private Integer driverId;
    
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    
    @Mapping
    @ApiModelProperty("司机手机号码")
    private String driverPhoneNo;
    
    @Mapping
    @ApiModelProperty("发车确认时间")
    private Long departConfirmTime;
    
 /** ------------------------------2018年3月23日DO新增字段----------------------------------- **/
    
    /**
     * 分摊车费
     */
    @Mapping
    @ApiModelProperty("分摊车费")
    private BigDecimal shareFareFee;
    
    /**
     * 分摊装卸费
     */
    @Mapping
    @ApiModelProperty("分摊装卸费")
    private BigDecimal shareChargesFee;

    /**
     * 分摊叉车费
     */
    @Mapping
    @ApiModelProperty("分摊叉车费")
    private BigDecimal shareForkliftFee;
    
    /**
     * 分摊其他费
     */
    @Mapping
    @ApiModelProperty("分摊其他费")
    private BigDecimal shareOtherFee;


    /**************************************冗余到货批次信息*******************************************/

    @Mapping
    @ApiModelProperty("到货网点")
    private Integer arriveBranchId;

    @Mapping
    @ApiModelProperty("到货网点名称")
    private String arriveBranchName;
    /**
     * 到货时间
     */
    @Mapping
    @ApiModelProperty("到货时间")
    private Long arriveTime;

    @ApiModelProperty("在途时长")
    private String transportTime;


    private @Mapping @ApiModelProperty("包装名称") String packingName;
    /**
     * 派送方式名称
     */
    @Mapping
    private @ApiModelProperty("配送方式名称") String distributionTypeName;

    /**
     * 备注
     */
    private @Mapping({"DispatcherDetailPrintDTO.waybillRemark","TmsWaybillEsDTO.remark","DispatcherWaybillEsDTO.waybillRemark"}) @ApiModelProperty("备注")String remark;

    
  
    /***********************************类型******************************/
	@Mapping
	@ApiModelProperty("是否等通知")
	private Boolean iwaitNotice;

	@Mapping
	@ApiModelProperty("是否加急")
	private Boolean iemergency;

	@Mapping
	@ApiModelProperty("是否VIP客户")
	private Boolean vip;
	
	@Mapping
	@ApiModelProperty("是否为异常单")
	private  Boolean exception;
 /***********************************类型******************************/
	
    private @Mapping  @ApiModelProperty("总运费")BigDecimal totalFeeCost;
	 
	private @Mapping@ApiModelProperty("总产值")BigDecimal totalOutputValue;
	
	private  @Mapping@ApiModelProperty("总业务费")BigDecimal totalBusinessFee;
	
    private  @Mapping@ApiModelProperty("总代收货款")BigDecimal totalGoodsPayment;
    
    @Mapping
	private @ApiModelProperty("运输方式名称") String shippingTypeName;
    
    /**
	 * 发货人完整地址
	 */
	private @Mapping  @ApiModelProperty("发货人完整地址")String invoiceFullAddress;
	
	/**
	 * 收货人完整地址
	 */
	private @Mapping  @ApiModelProperty("收货人完整地址")String receiptFullAddress;
}