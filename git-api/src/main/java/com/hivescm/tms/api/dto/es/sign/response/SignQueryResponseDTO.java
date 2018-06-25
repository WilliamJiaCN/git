package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherGoodsEsDTO;
import com.hivescm.tms.api.dto.es.sign.DeliveryFailureEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 送货签收列表查询响应对象
 * @author 杨彭伟
 * @date 2017-12-07 19:28
 */
@Data
@ToString
public class SignQueryResponseDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1244274286695080984L;

    /**
     * zouhx 新增返回费用对象
     */
    @ApiModelProperty("费用信息")
    private SignWaybillFeeRespDTO signWaybillFee;

    /**
     * 派车单明细id
     */
    @Mapping
    @ApiModelProperty("派车单明细id")
    private Long id;

    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 运单ID
     */
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;

    /**
     * 派车单ID
     */
    @Mapping
    @ApiModelProperty("派车单ID")
    private Long dispatcherId;
    /**
     * 派车备注
     */
    @Mapping(value = "assignRemarks")
    @ApiModelProperty("派车备注")
    private String remark;

    @Mapping()
    @ApiModelProperty("运单备注")
    private String waybillRemark;
    @Mapping
    @ApiModelProperty("经销商ID")
    private Long dealerId;

    @Mapping
    @ApiModelProperty("经销商名称")
    private String dealerName;


    /**
     * 总体积
     */
    private @Mapping @ApiModelProperty("总体积") BigDecimal volume;
    /**
     * 总重量
     */
    private @Mapping @ApiModelProperty("总重量") BigDecimal weight;
    /**
     * 总件数
     */
    private @Mapping @ApiModelProperty("总件数") Integer totalNum;

    @Mapping
    @ApiModelProperty("品种数")
    private Integer skuidCount;

    /**
     * 分摊成本(四种分摊成本总合)
     */
    @Mapping
    @ApiModelProperty("分摊成本(四种分摊成本总合)")
    private BigDecimal cost;

    /**
     * 签收时间
     */
    @Mapping
    @ApiModelProperty("签收时间 ")
    private Long signTime;

    @Mapping
    @ApiModelProperty("配送时间")
    private Long deliveryTime;

    @Mapping
    @ApiModelProperty("商户ID")
    private Long merchantId;
    @Mapping
    @ApiModelProperty("商户名称")
    private String merchantName;

    @Mapping()
    @ApiModelProperty("商户集团客户ID")
    private Long merchandGroupId;
    @Mapping()
    @ApiModelProperty("商户集团客户名称")
    private String merchandGroupName;

    @Mapping()
    @ApiModelProperty("仓储服务商集团客户ID")
    private Long warehouseServerGroupId;
    @Mapping()
    @ApiModelProperty("仓储服务商集团客户名称")
    private String warehouseServerGroupName;
    @Mapping
    @ApiModelProperty("最大提货数")
    private Integer maxDelivery;

    @Mapping
    @ApiModelProperty("门店ID")
    private Long storeId;

    @Mapping
    @ApiModelProperty("门店名称")
    private String storeName;
    /**
     * 实际运费
     */
    @Mapping("totalFee")
    @ApiModelProperty("总运费")
    private BigDecimal totalFee;

    @Mapping
    @ApiModelProperty("实际代收货款")
    private BigDecimal collectPayment;

    @ApiModelProperty("代收货款")
    private BigDecimal orderGoodsPayment;

    @Mapping
    @ApiModelProperty("声明价值")
    private BigDecimal collectVaule;
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
     * 代收货款手续费
     */
    private @Mapping @ApiModelProperty("代收货款手续费") BigDecimal deliveryCharge;
    private @Mapping  @ApiModelProperty("免费")BigDecimal forfree;

    private @Mapping  @ApiModelProperty("欠付")BigDecimal tardypay;

    private @Mapping  @ApiModelProperty("货款扣(运费)")BigDecimal goodsPaymentDeduction;

    /**
     * 业务费
     */
    @Mapping
    @ApiModelProperty("业务费")
    private String busFee;

    /**
     * 派车单明细状态
     */
    @Mapping
    @ApiModelProperty("派车单明细状态")
    private Integer status;

    /**
     * 运单是否拆单
     */
    @Mapping
    @ApiModelProperty("运单是否拆单")
    private Boolean waybillSplit;

    /**
     * 签收验证码
     */
    @Mapping
    @ApiModelProperty("签收验证码")
    private String signCode;

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

    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    /*********************************************** 冗余派车单信息 *******************************/
    /**
     * 司机ID
     *
     * @{link com.hivescm.tms.api.dto.base.BaseDriverDTO#id}
     */
    @Mapping
    @ApiModelProperty("司机ID")
    private Integer driverId;
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
     * 订单运输的类型，包括：销售订单、销退单；自动接收（非必填）
     */
    @Mapping
    @ApiModelProperty("订单类型")
    private Integer orderType;
    /**
     * 发车时间
     */
    @Mapping
    @ApiModelProperty("发车时间")
    private Long dispatcherTime;
    /**
     * 派车批次
     */
    @Mapping
    @ApiModelProperty("派车批次")
    private String batchCode;
    /**
     * 商品名称 ,“/”间隔
     */
    private @Mapping @ApiModelProperty("商品名称") String goodsName;

    @Mapping
    @ApiModelProperty("总箱数")
    private Integer boxNum;
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

    /**
     * 执行任务ID(数据字典)
     */
    @Mapping
    @ApiModelProperty("执行任务ID(数据字典)")
    private Integer taskId;
    @Mapping
    @ApiModelProperty("执行任务名称")
    private String taskName;
    /************************************************ 冗余签收单信息 *******************************/
    @Mapping
    @ApiModelProperty("签收单id")
    private Long signId;
    @Mapping
    @ApiModelProperty("签收批次号")
    private String signBatchNumber;

    @Mapping
    @ApiModelProperty("签收类型")
    private Integer signType;

    @Mapping
    @ApiModelProperty("签收状态")
    private Integer signStatus;

    @Mapping
    @ApiModelProperty("签收状态名称")
    private String signStatusName;

    @Mapping
    @ApiModelProperty("应收合计(代收货款)")
    private BigDecimal totalReceivable;

    @Mapping
    @ApiModelProperty("签收人")
    private String signPeople;

    @Mapping
    @ApiModelProperty("手机号")
    private String phoneNumber;

    @Mapping
    @ApiModelProperty("身份证号")
    private String idCard;

    @Mapping
    @ApiModelProperty("签收件数")
    private Integer signNumber;

    @Mapping
    @ApiModelProperty("拒签件数")
    private Integer refuseNumber;

    @ApiModelProperty("未签件数")
    private Integer unsignedNumber;

    @Mapping
    @ApiModelProperty("开单件数")
    private Integer createNumber;

    @Mapping
    @ApiModelProperty("签收图片")
    private String signPic;

    @Mapping
    @ApiModelProperty("签收说明")
    private String signingInstructions;
    @Mapping
    @ApiModelProperty("正常签收状态 NORMAL_SIGN(1,正常签收),ABNORMAL_SIGN(2,异常签收)")

     Integer normalSignType;

    private @Mapping @ApiModelProperty("到付") BigDecimal cod;

    @ApiModelProperty("到站其他费用")
    private BigDecimal otherFeeStation;

    @ApiModelProperty("二次派送费")
    private BigDecimal secondDeliveryFee;

    @ApiModelProperty("派送费用")
    private BigDecimal deliveryFee;


    /************************************************ 冗余运单信息 *******************************/

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
     * 目的地名称
     */
    @Mapping
    @ApiModelProperty("目的地名称")
    private String destName;
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
     * 运单号
     */
    @Mapping
    @ApiModelProperty("运单号")
    private String code;

    /**
     * 客户单号
     */
    @Mapping
    @ApiModelProperty("客户单号（订单号）")
    private String orderCode;

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
     * 发货人完整地址
     */
    private @Mapping  @ApiModelProperty("发货人完整地址")String invoiceFullAddress;


    /**
     * 收货 方ID
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

    /**
     * 收货人完整地址
     */
    private @Mapping  @ApiModelProperty("收货人完整地址")String receiptFullAddress;

    /**
     * 回单要求
     */
    @Mapping
    @ApiModelProperty("回单要求")
    private String backType;

    /**
     * 回单数量
     */
    @Mapping
    @ApiModelProperty("回单份数")
    private Integer backOrderNum;

    /**
     * 回单编号
     */
    @Mapping
    @ApiModelProperty("回单编号")
    private String backCode;
    /**
     * 付款方式
     */
    @Mapping
    @ApiModelProperty("付款方式")
    private Integer payWay;

    /**
     * TMS自动接单时判断是否装车自动赋值（非必填）
     */
    @Mapping
    @ApiModelProperty("是否装车")
    private Boolean loaded;

    /**
     * 付款方式名称
     */

    private @Mapping @ApiModelProperty("付款方式名称") String payWayName;

    /**
     * 派送方式名称
     */
    @Mapping
    private @ApiModelProperty("配送方式名称") String distributionTypeName;
    @Mapping
    @ApiModelProperty("仓库ID")
    private Long warehouseId;

    @Mapping
    @ApiModelProperty("仓库名称")
    private String warehouseName;

    /**
     * 累计签收件数（一个运单多次签收，签收件数总和）
     */
    private @Mapping  @ApiModelProperty("累计签收件数")Integer totalSignNumber;
    private @Mapping  @ApiModelProperty("累计拒签件数")Integer totalRefuseSignNumber;

    private @Mapping @ApiModelProperty("待签收数")Integer waitSignNumber;

    private @Mapping @ApiModelProperty("装车件数") Integer packageNum;
    @Mapping
    @ApiModelProperty("运单状态")
    private Integer waybillStatus;
    @Mapping
    @ApiModelProperty("运单状态")
    private String waybillStatusName;
    @Mapping
    @ApiModelProperty("录单时间")
    private Long waybillCreateTime;

    @ApiModelProperty("是否派送失败")
    private Boolean deliveryFailure;

    @Mapping
    @ApiModelProperty("派送失败原因")
    private String failureReason;

    @Mapping
    @ApiModelProperty("收银状态")
    private Boolean icashierConfirm;
    @Mapping
    @ApiModelProperty("是否返回入库 暂时不用")
    private Boolean istorage;
    @Mapping
    @ApiModelProperty("是否返回入库")
    private Boolean storage;
    /**
     * 回单份数
     */
    private @Mapping  @ApiModelProperty("回单份数") Integer backNum;

    public List<DispatcherGoodsEsDTO> dispatcherGoodsEsDTOList;

    public List<SignDetailsEsDTO> signDetailsEsDTOList;

    public void acceptWaybill(WaybillEsDTO waybillEsDTO){

        if (waybillEsDTO==null){
            return;
        }
        this.setTotalSignNumber(waybillEsDTO.getTotalSignNumber());
        this.setTotalRefuseSignNumber(waybillEsDTO.getTotalRefuseSignNumber());
        this.setTotalNum(waybillEsDTO.getTotalNum());
        this.setCollectPayment(waybillEsDTO.getOrderGoodsPayment());
        this.setBackCode(waybillEsDTO.getBackCode());
        this.setBackNum(waybillEsDTO.getBackNum());
        this.setBackOrderNum(waybillEsDTO.getBackOrderNum());
        this.setBackType(waybillEsDTO.getBackType());

        this.setInvoiceOrgName(waybillEsDTO.getInvoiceOrgName());
        this.setInvoiceMobleNo(waybillEsDTO.getInvoiceMobleNo());
        this.setInvoiceAddress(waybillEsDTO.getInvoiceAddress());
        this.setInvoiceFullAddress(waybillEsDTO.getInvoiceFullAddress());
        this.setInvoiceCompany(waybillEsDTO.getInvoiceCompany());
        this.setInvoiceUser(waybillEsDTO.getInvoiceUser());

        this.setReceiptAddress(waybillEsDTO.getReceiptAddress());
        this.setReceiptFullAddress(waybillEsDTO.getReceiptFullAddress());
        this.setReceiptCompany(waybillEsDTO.getReceiptCompany());
        this.setReceiptConsignorMobleNo(waybillEsDTO.getReceiptConsignorMobleNo());
        this.setReceiptConsignorTelNo(waybillEsDTO.getReceiptConsignorTelNo());
        this.setReceiptUser(waybillEsDTO.getReceiptUser());

        this.setPayWay(waybillEsDTO.getPayWay());
        this.setPayWayName(waybillEsDTO.getPayWayName());


        this.setDestName(waybillEsDTO.getDestName());
        this.setDestOrgName(waybillEsDTO.getDestOrgName());
        this.setDestOrgId(waybillEsDTO.getDestOrgId());

        this.setWarehouseName(waybillEsDTO.getWarehouseName());
        this.setWarehouseServerGroupName(waybillEsDTO.getWarehouseServerGroupName());
        this.setWarehouseId(waybillEsDTO.getWarehouseId());
        this.setWarehouseServerGroupId(waybillEsDTO.getWarehouseServerGroupId());
        this.setMerchantName(waybillEsDTO.getMerchantName());
        this.setMerchandGroupName(waybillEsDTO.getMerchandGroupName());
        this.setWaybillStatus(waybillEsDTO.getStatus());
        this.setWaybillStatusName(waybillEsDTO.getStatusName());

        this.setCod(waybillEsDTO.getCod());
        this.setOtherFeeStation(waybillEsDTO.getOtherFee());
        this.setOrderGoodsPayment(waybillEsDTO.getOrderGoodsPayment());
        this.setCod(waybillEsDTO.getCod());
        this.setDeliveryFee(waybillEsDTO.getDeliveryFee());
        //this.setSecondDeliveryFee(waybillEsDTO.getsen);

        this.setCashPay(waybillEsDTO.getCashPay());
        this.setMonthlyPay(waybillEsDTO.getMonthlyPay());
        this.setReceiptPay(waybillEsDTO.getReceiptPay());
        this.setGoodsPaymentDeduction(waybillEsDTO.getGoodsPaymentDeduction());
        this.setForfree(waybillEsDTO.getForfree());
        this.setTardypay(waybillEsDTO.getTardypay());

        //todo 暂时只有代收货款 和  到付 两个值
        this.setTotalReceivable(
                BigDecimal.ZERO
                        .add(waybillEsDTO.getOrderGoodsPayment()==null?BigDecimal.ZERO:waybillEsDTO.getOrderGoodsPayment())
                        .add(waybillEsDTO.getCod() == null ? BigDecimal.ZERO : waybillEsDTO.getCod())
                        .add(waybillEsDTO.getDeliveryFee() == null ? BigDecimal.ZERO : waybillEsDTO.getDeliveryFee())
                        .add(waybillEsDTO.getOtherFee() == null ? BigDecimal.ZERO : waybillEsDTO.getOtherFee())

        );


    }

    public void acceptDispatcherDetail(DispatcherDetailEsDTO dispatcherDetailEsDTO){

        if (dispatcherDetailEsDTO==null){
            return;
        }
        this.setId(dispatcherDetailEsDTO.getId());
        this.setCompanyId(dispatcherDetailEsDTO.getCompanyId());
        this.setWaybillId(dispatcherDetailEsDTO.getWaybillId());
        this.setVehicleModelName(dispatcherDetailEsDTO.getVehicleModelName());
        this.setDispatcherGoodsEsDTOList(dispatcherDetailEsDTO.getDispatcherGoodsEsDTOList());
        this.setVehicleNo(dispatcherDetailEsDTO.getVehicleNo());
        this.setVehicleId(dispatcherDetailEsDTO.getVehicleId());
        this.setDeliveryTime(dispatcherDetailEsDTO.getDeliveryTime());
        this.setDispatcherTime(dispatcherDetailEsDTO.getDispatcherTime());
        this.setBatchCode(dispatcherDetailEsDTO.getBatchCode());
        this.setDriverName(dispatcherDetailEsDTO.getDriverName());
        this.setPhoneNo(dispatcherDetailEsDTO.getPhoneNo());
        this.setTotalNum(dispatcherDetailEsDTO.getTotalNum());
        this.setDistributionTypeName(dispatcherDetailEsDTO.getDistributionTypeName());
        this.setBranchId(dispatcherDetailEsDTO.getBranchId());
        this.setBranchName(dispatcherDetailEsDTO.getBranchName());
        this.setGoodsName(dispatcherDetailEsDTO.getGoodsName());
        this.setWeight(dispatcherDetailEsDTO.getWeight());
        this.setVolume(dispatcherDetailEsDTO.getVolume());
        this.setStorage(dispatcherDetailEsDTO.getStorage());
        this.setIstorage(dispatcherDetailEsDTO.getIstorage());
        this.setPackageNum(dispatcherDetailEsDTO.getPackageNum());
        this.setWaitSignNumber(dispatcherDetailEsDTO.getPackageNum()); //待签收数等于装箱件数
        this.setBackCode(dispatcherDetailEsDTO.getBackCode());
        this.setBackNum(dispatcherDetailEsDTO.getBackNum());
        this.setBackOrderNum(dispatcherDetailEsDTO.getBackOrderNum());
        this.setBackType(dispatcherDetailEsDTO.getBackType());
        this.setCreateTime(dispatcherDetailEsDTO.getCreateTime());
        this.setCollectPayment(dispatcherDetailEsDTO.getCollectPayment());
        this.setOrderGoodsPayment(dispatcherDetailEsDTO.getOrderGoodsPayment());


    }

    public void acceptSign(SignEsDTO e){
        if (e==null){
            return;
        }
        this.setSignStatus(e.getSignStatus());
        this.setSignBatchNumber(e.getSignBatchNumber());
        this.setSignStatusName(e.getSignTypeName());
        this.setSignTime(e.getSignTime());
        this.setTotalReceivable(e.getTotalReceivable());
        this.setSignPic(e.getSignPic());
        this.setSignPeople(e.getSignPeople());
        this.setPhoneNumber(e.getPhoneNumber());
        this.setIdCard(e.getIdCard());
        this.setSignNumber(e.getSignNumber());
        this.setUnsignedNumber(e.getUnsignedNumber()); //未签数量
        this.setCreateNumber(e.getCreateNumber());
        this.setRefuseNumber(e.getRefuseNumber());
        this.setSigningInstructions(e.getSigningInstructions());
        this.setPhoneNo(e.getPhoneNo());
        this.setTotalNum(e.getTotalNum());
        this.setNormalSignType(e.getNormalSignType());
        this.setOtherFeeStation(e.getOtherFeeStation());

        this.setCod(e.getCod());
        this.setOrderGoodsPayment(e.getOrderGoodsPayment());
        this.setCod(e.getCod());
        this.setDeliveryFee(e.getDeliveryFee());

        this.setId(e.getDispatcherDetailId());
        this.setDispatcherId(e.getDispatcherId());
        this.setWaybillId(e.getWaybillId());
        this.setCode(e.getCode());
        this.setSignId(e.getId());
        this.setSignType(e.getSignType());
        this.setIcashierConfirm(e.getIcashierConfirm());
        this.setPackageNum(e.getPackageNum());
        this.setWaitSignNumber(this.getPackageNum());

    }

    public void acceptFail(DeliveryFailureEsDTO e){

        if (e==null){
            return;
        }

        EntityUtils.copyProperties(e,this);
        this.setDeliveryFailure(true);
        this.setFailureReason(e.getFailureReasion());
        this.setDriverName(e.getCarName());
        this.setVehicleNo(e.getCarNumber());
        this.setPhoneNo(e.getCarPhone());
        this.setCode(e.getWaybillCode());
        this.setInvoiceOrgName(e.getInvoicewayName());
        this.setDistributionTypeName(WaybillDistributionTypeEnum.DELIVERY.getName());
        this.setInvoiceOrgId(e.getInvoicewayId());
    }
}
