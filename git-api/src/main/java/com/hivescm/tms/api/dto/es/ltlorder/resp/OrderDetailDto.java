package com.hivescm.tms.api.dto.es.ltlorder.resp;

import com.hivescm.tms.api.dto.es.ltlorder.UsuallySendGoodsEsDto;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class OrderDetailDto {
	//1-发、2-收
	@ApiModelProperty("收发标识:1-发、2-收")
	private Integer flag;
	@ApiModelProperty("物流ID")
	private Integer companyId;
	@ApiModelProperty("物流公司简称")
	private String companyName;
	@ApiModelProperty("状态")
	private Integer status;
	@ApiModelProperty("状态名称")
	private  String statusName;
	@ApiModelProperty("订单Id")
	private Long orderId;
	@ApiModelProperty("订单号")
	private String orderCode;
	@ApiModelProperty("运单Id")
	private Long waybillId;
	@ApiModelProperty("运单号")
	private String code;
	@ApiModelProperty("发货人姓名")
    private String invoiceUserName;
	@ApiModelProperty("发货人手机号")
    private String invoiceUserMobile;
	@ApiModelProperty("发货地址")
    private String invoiceAddress;
	@ApiModelProperty("收货人姓名")
	private String receiptUserName;
	@ApiModelProperty("收货人手机号")
    private String receiptUserMobile;
	@ApiModelProperty("收货地址")
    private String receiptAddress;
	@ApiModelProperty("预约发货时间")
	private Long appointDeliveryTime;
	@ApiModelProperty("发货方式")
	private Integer deliveryType;
	@ApiModelProperty("发货方式名称")
	private String deliveryTypeName;
	@ApiModelProperty("派送方式")
	private Integer distributionType;
	@ApiModelProperty("配送方式名称")
	private  String distributionTypeName;
	@ApiModelProperty("货物明细")
	private List<UsuallySendGoodsEsDto> goodsDetail;
	@ApiModelProperty("货主ID")
    private Integer createUserOrder;
	@ApiModelProperty("创建时间")
    private Long createTimeOrder;
	//未开单时为捎句话信息，开单后为汇总字段：等通知、开票、整车、加急等勾选信息
	@ApiModelProperty("备注")
	private String remark;	
	@ApiModelProperty("运费明细")
	private List<WaybillFeeEsDTO> waybillFee;
	//付款
	@ApiModelProperty("实际运费") 
	private Double realFee;
	@ApiModelProperty("总运费") 
	private BigDecimal totalFee;
	@ApiModelProperty("付款方式")
	private  Integer payWay;
	@ApiModelProperty("付款方式名称")
	private String payWayName;
	@ApiModelProperty("现付")
	private  BigDecimal cashPay;
	@ApiModelProperty("月结") 
	private BigDecimal monthlyPay;
	@ApiModelProperty("回单付") 
	private BigDecimal receiptPay;
	@ApiModelProperty("到付") 
	private BigDecimal cod;
	//额外服务
	@ApiModelProperty(value = "代收货款")
	private BigDecimal goodsPayment;
	@ApiModelProperty(value = "声明价值")
	private BigDecimal declareValue;
	@ApiModelProperty(value = "回单要求 1、签回单 2、签原单")
	private Integer receiptType;
	//网点ID
	@ApiModelProperty(value = "网点")
	private NetPointDto deliveryNetPoint;
	//提示语
	@ApiModelProperty(value = "提示语")
	private String markedWord;
	@ApiModelProperty("签收状态")
	private Integer signStatus;
	@ApiModelProperty("签收状态名称")
	private String signStatusName;
	@ApiModelProperty("签收详情")
	private String signDetail;
}
