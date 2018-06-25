package com.hivescm.tms.api.dto.es.sign;

import com.hivescm.framework.elasticsearch.annotation.Cascade;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Cascade(value = "deliverySign", fields = { "code", "receiptUser", "invoiceUser" })
public class SignEsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public long groupKeyByWaybillId() {
		return this.waybillId;
	}

	public int getSumBySignNumber() {
		if (signNumber == null)
			signNumber = 0;

		return this.signNumber;
	}
	
	public int getSumByRefuseSignNumber() {
		if (refuseNumber == null)
			refuseNumber = 0;

		return this.refuseNumber;
	}

	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;

	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;

	@Mapping
	@ApiModelProperty("派车单id")
	private Long dispatcherId;
	@ApiModelProperty("收款方式(1=现金支付,2=二维码支付)")
	private Integer settlementMethod;
	@Mapping
	@ApiModelProperty("派车单明细id")
	private Long dispatcherDetailId;

	@Mapping
	@ApiModelProperty("签收批次号")
	private String signBatchNumber;
	
	@Mapping
	@ApiModelProperty("订单号")
	private String orderCode;

	@Mapping
	@ApiModelProperty("收货人")
	private String signPeople;

	@Mapping({"ireceiver","isReceiver","SignDO.isReceiver"})
	@ApiModelProperty("是否同收货人")
	private Boolean ireceiver;


	@Mapping
	@ApiModelProperty("收货人手机号码")
	private String phoneNumber;

	@Mapping
	@ApiModelProperty("签收人身份证号")
	private String idCard;

	@Mapping
	@ApiModelProperty("签收时间")
	private Long signTime;

	@Mapping
	@ApiModelProperty("签收类型")
	private Integer signType;
	
	@Mapping
	@ApiModelProperty("签收类型名称")
	private String signTypeName;

	@Mapping
	@ApiModelProperty("签收说明")
	private String signingInstructions;

	@Mapping({"ibackConfirm","isBackConfirm","SignDO.isBackConfirm"})
	@ApiModelProperty("是否回单确认收回")
	private Boolean ibackConfirm;


	@Mapping({"sendmsgReceiver","isSendmsgReceiver","SignDO.isSendmsgReceiver"})
	@ApiModelProperty("是否发送短信给收货人")
	private Boolean sendmsgReceiver;

	@Mapping({"sendMsgInvoicer","isSendMsgInvoicer","SignDO.isSendMsgInvoicer"})
	@ApiModelProperty("是否发送短信给发货人")
	private Boolean sendMsgInvoicer;

	@Mapping
	@ApiModelProperty("签收图片")
	private String signPic;

	@Mapping
	@ApiModelProperty("经办人id")
	private Integer handler;
	
	@Mapping
	@ApiModelProperty("经办人名称")
	private String handlerName;

	@Mapping
	@ApiModelProperty("签收件数")
	private Integer signNumber;
	
	@Mapping
	@ApiModelProperty("签收重量")
	private BigDecimal signWeight;
	
	@Mapping
	@ApiModelProperty("签收体积")
	private BigDecimal signVolume;

	@Mapping
	@ApiModelProperty("拒签件数")
	private Integer refuseNumber;

	@ApiModelProperty("未签件数")
	private Integer unsignedNumber;

	@Mapping
	@ApiModelProperty("开单件数")
	private Integer createNumber;

	@Mapping
	@ApiModelProperty("应收合计")
	private BigDecimal totalReceivable;

	@Mapping
	@ApiModelProperty("派送失败原因")
	private String deliveryFailureReason;

	@Mapping({"ideliveryFailure","isDeliveryFailure","SignDO.isDeliveryFailure"})
	@ApiModelProperty("是否派送失败")
	private Boolean ideliveryFailure;

	@Mapping({"icashierConfirm","isCashierConfirm","SignDO.isCashierConfirm"})
	@ApiModelProperty("收银确认")
	private Boolean icashierConfirm;

	@Mapping
	@ApiModelProperty("派送类型(1 = 自提签收,2 = 送货签收,3 = 外发签收)")
	private Integer deliveryType;
	
	@ApiModelProperty("派送类型名称")
	private String deliveryTypeName;

	@Mapping
	@ApiModelProperty("签收状态")// NO_SIGN(0, 未签收), SIGNED(1,全部签收), PARTIAL_SIGN(2,部分签收), REFUSE_SIGN(3,全部拒签), CANCEL_SIGN(4,作废);
	private Integer signStatus;
	
	@Mapping
	@ApiModelProperty("签收状态名称")// NO_SIGN(0, 未签收), SIGNED(1,全部签收), PARTIAL_SIGN(2,部分签收), REFUSE_SIGN(3,全部拒签), CANCEL_SIGN(4,作废);
	private String signStatusName;

	@Mapping
	@ApiModelProperty("正常签收状态 NORMAL_SIGN(1,正常签收),ABNORMAL_SIGN(2,异常签收)")
	private Integer normalSignType;
	@Mapping
	@ApiModelProperty("正常签收状态名称")
	private String normalSignTypeName;

	@Mapping
	@ApiModelProperty("签收名称")
	private String signName;

	@Mapping
	@ApiModelProperty("签收网点id")
	private Integer signOrgId;
	
	@Mapping
	@ApiModelProperty("签收网点名称")
	private String signOrgName;

	/**
	 * 车牌号码
	 */
	@Mapping
	@ApiModelProperty("车牌号码")
	private String vehicleNo;

	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;
	
	@Mapping
	@ApiModelProperty("创建人名称")
	private  String createUserName;

	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;

	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;

	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	
	@Mapping
	@ApiModelProperty("修改人名称")
	private  String updateUserName;
	/********************************** 冗余运单字段 **********************************/

	private @Mapping @ApiModelProperty("商品名称") String goodsName;

	private @Mapping @ApiModelProperty("运单号，已作废") String waybillCode;

	private @Mapping @ApiModelProperty("运单号") String code;

	private @Mapping @ApiModelProperty("下单日期") Long orderDate;

	private @Mapping @ApiModelProperty("发货网点ID	") Integer invoiceOrgId;
	
	private @Mapping  @ApiModelProperty("发货网点名称")String invoiceOrgName;

	private @Mapping @ApiModelProperty("到达网点id") Integer destOrgId;
	
	private @Mapping  @ApiModelProperty("到达网点名称")String destOrgName;

	private @Mapping @ApiModelProperty("发货单位") String invoiceCompany;

	private @Mapping @ApiModelProperty("发货人	") String invoiceUser;

	private @Mapping @ApiModelProperty("发货人手机号码") String invoiceMobleNo;

	private @Mapping @ApiModelProperty("发货人详细地址") String invoiceAddress;

	private @Mapping  @ApiModelProperty("发货人完整地址")String invoiceFullAddress;

	private @Mapping @ApiModelProperty("收货人") String receiptUser;

	private @Mapping @ApiModelProperty("收货单位") String receiptCompany;

	private @Mapping @ApiModelProperty("收货人电话") String receiptConsignorTelNo;

	private @Mapping @ApiModelProperty("收货人手机号码") String receiptConsignorMobleNo;

	private @Mapping @ApiModelProperty("收货人详细地址") String receiptAddress;

	private @Mapping  @ApiModelProperty("收货人完整地址")String receiptFullAddress;

	private @Mapping @ApiModelProperty("回单份数") Integer backNum;

	private @Mapping @ApiModelProperty("回单编号") String backCode;

	private @Mapping @ApiModelProperty("回单要求") String backType;

	private @Mapping @ApiModelProperty("发货方Id") Integer invoiceCustomerId;

    private @Mapping @ApiModelProperty("司机手机号码") String phoneNo;
    
	@Mapping
	private @ApiModelProperty("收货人城市名称") String receiptCityName;
	
	@Mapping
	private @ApiModelProperty("发货人城市名称") String invoiceCityName;
	@Mapping
	private @ApiModelProperty("总件数") Integer totalNum;
	
	/**
	 * 累计签收件数（一个运单多次签收，签收件数总和）
	 */
	private @Mapping  @ApiModelProperty("累计签收件数")Integer totalSignNumber;
	
	/**
	 * 累计拒签件数（一个运单多次拒签，累计拒签件数）
	 */
	private @Mapping  @ApiModelProperty("累计拒签件数")Integer totalRefuseSignNumber;
    
    //********************************************冗余列表展示数据*************************************************************/

	private @Mapping @ApiModelProperty("到付") BigDecimal cod;

	@Mapping
    @ApiModelProperty("到站其他费用")
    private BigDecimal otherFeeStation;

    @Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal orderGoodsPayment;

	@Mapping
	@ApiModelProperty("二次派送费")
	private BigDecimal secondDeliveryFee;

	@Mapping
	@ApiModelProperty("派送费用")
	private BigDecimal deliveryFee;

	@Mapping
	@ApiModelProperty("到付送货费")
	private BigDecimal toPayDeliveryFee;
	@Mapping
	@ApiModelProperty("派车批次")
	private  String batchCode;
	/**
	 * 司机姓名
	 */
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	/**
	 * 发车时间
	 */
	@Mapping
	@ApiModelProperty("发车时间")
	private Long dispatcherTime;

	@Mapping
	@ApiModelProperty("装车件数")
	private Integer packageNum;
	/**
	 *  0 、null 城配单子
	 *  1 、2 零担单子
	 */
	@Mapping
	@ApiModelProperty("运单类型 0 OMS上游录入  1 手动创建 2 货主app ")
	private Integer waybillType;
	/**
	 * 接收上游单据数据
	 * @param dispatcherDetail
	 */
	public void acceptDispatcherDetail(DispatcherDetailEsDTO dispatcherDetail){
		if (dispatcherDetail==null){
			return;
		}
		//冗余上游单据的字段
		this.setCompanyId(dispatcherDetail.getCompanyId());
		this.setDispatcherTime(dispatcherDetail.getDispatcherTime());
		this.setDispatcherId(dispatcherDetail.getDispatcherId());
		this.setDispatcherDetailId(dispatcherDetail.getId());
		this.setInvoiceMobleNo(dispatcherDetail.getInvoiceMobleNo());
		this.setInvoiceCityName(dispatcherDetail.getInvoiceCityName());
		this.setInvoiceMobleNo(dispatcherDetail.getInvoiceMobleNo());
		this.setInvoiceCustomerId(dispatcherDetail.getReceiptCustomerId());
		this.setInvoiceOrgId(dispatcherDetail.getInvoiceOrgId());
		this.setInvoiceOrgName(dispatcherDetail.getInvoiceOrgName());
		this.setInvoiceCompany(dispatcherDetail.getInvoiceCompany());
		this.setCode(dispatcherDetail.getCode());
		this.setWaybillCode(dispatcherDetail.getCode());
		this.setWaybillId(dispatcherDetail.getWaybillId());
		this.setOrderCode(dispatcherDetail.getOrderCode());
		this.setOrderDate(dispatcherDetail.getOrderDate());

		this.setBatchCode(dispatcherDetail.getBatchCode());

		this.setDestOrgId(dispatcherDetail.getDestOrgId());
		this.setDestOrgName(dispatcherDetail.getDestOrgName());

		this.setDriverName(dispatcherDetail.getDriverName());

		this.setPhoneNo(dispatcherDetail.getPhoneNo());

		this.setReceiptConsignorMobleNo(dispatcherDetail.getReceiptConsignorMobleNo());
		this.setReceiptConsignorTelNo(dispatcherDetail.getReceiptConsignorTelNo());

		this.setBackNum(dispatcherDetail.getBackNum());
		this.setBackType(dispatcherDetail.getBackType());
		this.setBackCode(dispatcherDetail.getBackCode());

		this.setDeliveryType(WaybillDistributionTypeEnum.DELIVERY.getType());
		this.setDeliveryTypeName(WaybillDistributionTypeEnum.DELIVERY.getName());
		this.setIdeliveryFailure(false);

		this.setPackageNum(dispatcherDetail.getPackageNum()); //装箱件数
		this.setCreateNumber(dispatcherDetail.getTotalNum()); //开单件数
		this.setWaybillType(dispatcherDetail.getWaybillType());
		this.setTotalNum(dispatcherDetail.getTotalNum());     //运单总件数
		this.setOrderGoodsPayment(dispatcherDetail.getOrderGoodsPayment()); //应收代收货款

	}

}
