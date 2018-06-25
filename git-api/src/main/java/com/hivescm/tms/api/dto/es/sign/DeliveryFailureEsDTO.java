package com.hivescm.tms.api.dto.es.sign;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherGoodsEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
//@Cascade(value = "deliverySign",fields = {"","",""})
public class DeliveryFailureEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping
    @ApiModelProperty("派送失败id，主键")
    private Long id;
    @Mapping
    @ApiModelProperty("派送失败单号")
    private String failureCode;
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;
    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;
    @Mapping
    @ApiModelProperty("运单明细id")
    private Long waybillDetailId;
    @Mapping
    @ApiModelProperty("当前网点id")
    private Integer orgId;
    @Mapping
    @ApiModelProperty("运单号")
    private String waybillCode;
    @Mapping
    @ApiModelProperty("运单号")
    private String code;
    @Mapping
    @ApiModelProperty("派车单id")
    private Long dispatcherId;
    @Mapping
    @ApiModelProperty("派车单明细id")
    private Long dispatcherDetailId;
    @Mapping
    @ApiModelProperty("派车批次")
    private String dispatcherBatchNumber;
    @Mapping
    @ApiModelProperty("运单派送的批次id")
    private Long dispacherCode;
    @Mapping
    @ApiModelProperty("派送类型")
    private Integer deliveryType;
    @Mapping
    @ApiModelProperty("派送失败原因类型")
    private Integer failureReasionType;
    @Mapping
    @ApiModelProperty("派送失败原因")
    private String failureReasion;
    @Mapping
    @ApiModelProperty("发车网点 运单派送的网点名称")
    private String invoicewayName;
    @Mapping
    @ApiModelProperty("发车网点 运单派送的网点id")
    private Integer invoicewayId;
    @Mapping
    @ApiModelProperty("当前网点名称")
    private String orgName;
    @Mapping
    @ApiModelProperty("司机名称")
    private String carName;
    @Mapping
    @ApiModelProperty("司机手机号码")
    private String carPhone;
    @Mapping
    @ApiModelProperty("派送运单的车辆车牌号码")
    private String carNumber;
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    @Mapping
    @ApiModelProperty("派送时间")
    private Long dispatcherTime;

    @Mapping
    @ApiModelProperty("订单号")
    private String orderCode;

    /**
     * 派车网点ID
     */
    @Mapping
    @ApiModelProperty("派车网点ID")
    private Integer branchId;
    /**
     * 派车网点名称
     */
    @Mapping
    @ApiModelProperty("派车网点名称")
    private String branchName;

    /**
     * 商品名称 ,“/”间隔
     */
    private @Mapping
    @ApiModelProperty("商品名称")
    String goodsName;

    /**
     * 车型名称
     */
    @Mapping
    @ApiModelProperty("车型名称")
    private Integer vehicleModelName;
    @Mapping
    @ApiModelProperty("车辆ID")
    private Integer vehicleId;
    @Mapping
    @ApiModelProperty("车牌号码")
    private String vehicleNo;
    @Mapping
    @ApiModelProperty("承运商ID")
    private Integer carrierId;
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carrierName;

    @Mapping
    @ApiModelProperty("配送时间")
    private Long sendTime;

    @Mapping
    @ApiModelProperty("回单要求")
    private String backType;

    @Mapping
    @ApiModelProperty("回单份数")
    private Integer backOrderNum;
    @Mapping
    @ApiModelProperty("回单份数")
    private Integer backNum;

    @Mapping
    @ApiModelProperty("回单编号")
    private String backCode;
    @ApiModelProperty("到站其他费用")
    private BigDecimal otherFeeStation;

    private @Mapping
    @ApiModelProperty("付款方式名称")
    String payWayName;
    /**
     * 实际运费
     */
    @Mapping("totalFee")
    @ApiModelProperty("总运费")
    private BigDecimal totalFee;

    @Mapping
    @ApiModelProperty("代收货款")
    private BigDecimal collectPayment;


    private @Mapping
    @ApiModelProperty("总体积")
    BigDecimal volume;

    private @Mapping
    @ApiModelProperty("总重量")
    BigDecimal weight;
    /**
     * 装车件数(派车单总件数)
     */
    @Mapping
    @ApiModelProperty("装车件数(派车单总件数)")
    private Integer packageNum;

    /**
     * 装车重量(派车单总重量)
     */
    @Mapping
    @ApiModelProperty("装车重量(派车单总重量)")
    private BigDecimal packageWeight;

    /**
     * 装车体积(派车单总体积)
     */
    @Mapping
    @ApiModelProperty("装车体积(派车单总体积)")
    private BigDecimal packageVolume;

    private @Mapping
    @ApiModelProperty("总件数")
    Integer totalNum;
    @Mapping
    @ApiModelProperty("商户名称")
    private String merchantName;
    @Mapping
    @ApiModelProperty("仓储服务商名称")
    private String warehouseServerName;
    @Mapping
    @ApiModelProperty("门店名称")
    private String storeName;

    @Mapping
    @ApiModelProperty("代收货款")
    private BigDecimal orderGoodsPayment;
    /**
     * 司机姓名
     */
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    /**
     * 司机姓名
     */
    @Mapping
    @ApiModelProperty("司机手机号码")
    private String phoneNo;

    /**
     * 派车批次
     */
    @Mapping
    @ApiModelProperty("派车批次")
    private String batchCode;
    /**
     * 发货网点名称
     */
    @Mapping
    @ApiModelProperty("发货网点名称")
    private String invoiceOrgName;

    /**
     * 目的地名称
     */
    @Mapping
    @ApiModelProperty("目的地名称")
    private String destName;

    /**
     * 运单运输线路名称
     */
    @Mapping
    @ApiModelProperty("运单运输线路名称")
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
     * 发货人详细地址
     */
    @Mapping
    @ApiModelProperty("发货人详细地址")
    private String invoiceAddress;

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
    @ApiModelProperty("收货公司")
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
     * 收货人详细地址
     */
    @Mapping
    @ApiModelProperty("收货人详细地址")
    private String receiptAddress;
    @Mapping
    @ApiModelProperty("是否反库")
    private Boolean storage;

    /**
     * 现付
     */
    private @Mapping  @ApiModelProperty("现付")BigDecimal cashPay;
    /**
     * 月结
     */
    private @Mapping  @ApiModelProperty("月结")BigDecimal monthlyPay;
    /**
     * 回单付
     */
    private @Mapping  @ApiModelProperty("回单付")BigDecimal receiptPay;
    /**
     * 到付
     */
    private @Mapping  @ApiModelProperty("到付")BigDecimal cod;
    /**
     * 回单付
     */
    private @Mapping  @ApiModelProperty("免费")BigDecimal forfree;
    /**
     * 到付
     */
    private @Mapping  @ApiModelProperty("欠付")BigDecimal tardypay;
    /**
     * 货款扣(运费)
     */
    private @Mapping  @ApiModelProperty("货款扣(运费)")BigDecimal goodsPaymentDeduction;

    /**
     * 累计签收件数（一个运单多次签收，签收件数总和）
     */
    private @Mapping  @ApiModelProperty("累计签收件数")Integer totalSignNumber;

    /**
     * 累计拒签件数（一个运单多次拒签，累计拒签件数）
     */
    private @Mapping  @ApiModelProperty("累计拒签件数")Integer totalRefuseSignNumber;

    @Mapping
    @ApiModelProperty("待签件数")
    private Integer waitSignNumber;


    @Mapping
    @ApiModelProperty("仓库名称")
    private String warehouseName;//冗余
    @Mapping()
    @ApiModelProperty("仓储服务商集团客户名称")
    private String warehouseServerGroupName;

    @Mapping()
    @ApiModelProperty("商户集团客户名称")
    private String merchandGroupName;
    @ApiModelProperty("派车单明细货物")
    public List<DispatcherGoodsEsDTO> dispatcherGoodsEsDTOList;

    public DeliveryFailureEsDTO acceptDispatcherDetail(DispatcherDetailEsDTO dispatcherDetailEsDTO){

        EntityUtils.copyProperties(dispatcherDetailEsDTO,this);

        this.setDispatcherBatchNumber(dispatcherDetailEsDTO.getBatchCode());
        this.setCarName(dispatcherDetailEsDTO.getDriverName());
        this.setCarNumber(dispatcherDetailEsDTO.getVehicleNo());
        this.setCarPhone(dispatcherDetailEsDTO.getPhoneNo());
        this.setWaybillCode(dispatcherDetailEsDTO.getCode());
        this.setInvoicewayName(dispatcherDetailEsDTO.getInvoiceOrgName());
        this.setDeliveryType(WaybillDistributionTypeEnum.DELIVERY.getType());
        this.setInvoicewayId(dispatcherDetailEsDTO.getInvoiceOrgId());
        this.setDispatcherBatchNumber(dispatcherDetailEsDTO.getBatchCode());

        packageNum=dispatcherDetailEsDTO.getPackageNum();
        packageVolume=dispatcherDetailEsDTO.getPackageVolume();
        packageWeight=dispatcherDetailEsDTO.getPackageWeight();
        dispatcherGoodsEsDTOList=dispatcherDetailEsDTO.getDispatcherGoodsEsDTOList();
        waitSignNumber=dispatcherDetailEsDTO.getPackageNum();
        return this;
    }
    public DeliveryFailureEsDTO init(){
        storage=false;
        createTime=System.currentTimeMillis();
        return this;
    }

    public DeliveryFailureEsDTO acceptWaybill(WaybillEsDTO waybillEsDTO){

        if (waybillEsDTO==null){
           return this;
        }
        this.setTotalSignNumber(waybillEsDTO.getTotalSignNumber());
        this.setTotalRefuseSignNumber(waybillEsDTO.getTotalRefuseSignNumber());
        this.setTotalNum(waybillEsDTO.getTotalNum());

        this.setBackCode(waybillEsDTO.getBackCode());
        this.setBackNum(waybillEsDTO.getBackNum());
        this.setBackOrderNum(waybillEsDTO.getBackOrderNum());
        this.setBackType(waybillEsDTO.getBackType());
        this.setCod(waybillEsDTO.getCod());
        this.setCollectPayment(waybillEsDTO.getOrderGoodsPayment());

        this.setInvoiceOrgName(waybillEsDTO.getInvoiceOrgName());
        this.setInvoiceMobleNo(waybillEsDTO.getInvoiceMobleNo());
        this.setInvoiceAddress(waybillEsDTO.getInvoiceAddress());
        this.setInvoiceCompany(waybillEsDTO.getInvoiceCompany());
        this.setInvoiceUser(waybillEsDTO.getInvoiceUser());

        this.setReceiptAddress(waybillEsDTO.getReceiptAddress());
        this.setReceiptCompany(waybillEsDTO.getReceiptCompany());
        this.setReceiptConsignorMobleNo(waybillEsDTO.getReceiptConsignorMobleNo());
        this.setReceiptConsignorTelNo(waybillEsDTO.getReceiptConsignorTelNo());
        this.setReceiptUser(waybillEsDTO.getReceiptUser());

        this.setPayWayName(waybillEsDTO.getPayWayName());


        this.setDestName(waybillEsDTO.getDestName());
        this.setDestOrgName(waybillEsDTO.getDestOrgName());
        this.setDestOrgId(waybillEsDTO.getDestOrgId());

        this.setWarehouseName(waybillEsDTO.getWarehouseName());
        this.setWarehouseServerGroupName(waybillEsDTO.getWarehouseServerGroupName());
        this.setMerchantName(waybillEsDTO.getMerchantName());
        this.setMerchandGroupName(waybillEsDTO.getMerchandGroupName());

        this.setOtherFeeStation(waybillEsDTO.getOtherFee());

        this.setCashPay(waybillEsDTO.getCashPay());
        this.setMonthlyPay(waybillEsDTO.getMonthlyPay());
        this.setReceiptPay(waybillEsDTO.getReceiptPay());


        return this;
    }
}
