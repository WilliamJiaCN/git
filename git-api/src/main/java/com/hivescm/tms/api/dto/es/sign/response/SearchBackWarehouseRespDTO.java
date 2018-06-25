package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class SearchBackWarehouseRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private WaybillEsDTO waybill;

    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;

    @Mapping
    @ApiModelProperty("派车单id")
    private Long dispatcherId;

    @Mapping
    @ApiModelProperty("派车单明细id")
    private Long dipatcherDetailId;

    @Mapping
    @ApiModelProperty("入库批次")
    private String storageBatchCode;

    @Mapping
    @ApiModelProperty("入库网点")
    private Integer storageDot;
    @Mapping
    @ApiModelProperty("入库网点名称")
    private String storageDotName;

    @Mapping
    @ApiModelProperty("入库时间")
    private Long storageTime;

    @Mapping
    @ApiModelProperty("仓库名称")
    private String storageName;

    @Mapping
    @ApiModelProperty("开单件数")
    private Integer totalNum;
    @Mapping
    @ApiModelProperty("送货件数")
    private Integer deliveriesNumber;
    @Mapping
    @ApiModelProperty("入库件数")
    private Integer storageNumber;

    @Mapping
    @ApiModelProperty("反库类型")
    private Integer backType;

    @Mapping
    @ApiModelProperty("返回后配送方式")
    private Integer backDeliveryType;

    @Mapping
    @ApiModelProperty("反库货物名称")
    private String goodsName;

    @Mapping
    @ApiModelProperty("反库说明")
    private String backInstructions;

    @Mapping
    @ApiModelProperty("签收单id")
    private Long signId;

    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;

    /*********************************冗余派车单数据*****************************/
    @Mapping
    @ApiModelProperty("派车批次")
    private String batchCode;

    /**
     * 派车网点ID
     */
    @Mapping
    @ApiModelProperty("派车网点ID")
    private Integer branchId;

    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    /**
     * 车型
     */
    @ApiModelProperty("车型ID")
    private Integer vehicleModels;

    /**
     * 车型名称
     */
    @ApiModelProperty("车型名称")
    private String vehicleModelName;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phoneNo;

    /*********************************冗余运单数据*****************************/
    @Mapping
    @ApiModelProperty("运单号")
    private String waybillCode;


    /**
     * 司机姓名
     */
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;

    @Mapping()
    @ApiModelProperty("司机ID")
    private Integer driverId;


    /**
     * 发车确认时间
     */
    @Mapping
    @ApiModelProperty("发车确认时间")
    private Long confirmTime;

    @ApiModelProperty("提货费")
    private BigDecimal pickupFee;

    @ApiModelProperty("基本运费")
    private BigDecimal baseFreight;

    @ApiModelProperty("送货费")
    private BigDecimal deliveryFee;

    @ApiModelProperty("上楼费")
    private BigDecimal upstairsFee;

    @ApiModelProperty("包装费")
    private BigDecimal packingCharges;

    @ApiModelProperty("声明价值")
    private BigDecimal declaredValue;

    @ApiModelProperty("信息费")
    private BigDecimal informationFee;

    @ApiModelProperty("加急费")
    private BigDecimal emergencyFee;

    @ApiModelProperty("垫付运费")
    private BigDecimal freightPayment;

    @ApiModelProperty("其他费用")
    private BigDecimal otherFee;

    @ApiModelProperty("业务费")
    private BigDecimal business;

    private @Mapping @ApiModelProperty("发货人")String invoiceUser;

    private @Mapping @ApiModelProperty("发货公司")String invoiceCompany;

    private @Mapping @ApiModelProperty("收货人")String receiptUser;

    private @Mapping  @ApiModelProperty("收货公司")String receiptCompany;

    @Mapping
    private @ApiModelProperty("回单要求名称") String backBillType; //运单-回单要求名称

    @Mapping
    private @ApiModelProperty("回单要求") Integer backBillTypeValue; //运单-回单要求

    /**
     * 回单份数
     */
    private @Mapping @ApiModelProperty("回单份数") Integer backNum;
    /**
     * 回单编号
     */
    private @Mapping  @ApiModelProperty("回单编号")String backCode;

}
