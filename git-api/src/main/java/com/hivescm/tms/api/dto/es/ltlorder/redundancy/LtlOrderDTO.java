package com.hivescm.tms.api.dto.es.ltlorder.redundancy;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 手动创建零担订单主表
 * @author wenxiong.jia
 * @date 2018/4/9
 */
@Data
@ToString
public class LtlOrderDTO implements Serializable{
	private static final long serialVersionUID = 8257543025464902915L;

	@Required
	@ApiModelProperty("商品列表")
	private List<LtlOrderGoodsDTO> goods;
	
	@Required
	@Mapping({"LtlOrderEsDTO.orderCode"})
	@ApiModelProperty("订单编号")
    private String orderCode;
	
	@Mapping({"LtlOrderEsDTO.deliveryNetWork"})
	@ApiModelProperty("接收网点")
    private String deliveryNetWork;
	
	@Mapping({"LtlOrderEsDTO.endCityId"})
	@ApiModelProperty("目的城市ID")
    private String endCityId;
	
	@Mapping({"LtlOrderEsDTO.endCityName"})
	@ApiModelProperty("目的城市名称")
    private String endCityName;
	
	@Mapping({"LtlOrderEsDTO.invoiceCompanyName"})
	@ApiModelProperty("发货公司名称")
    private Integer invoiceCompanyName;
	
	@Required
	@Mapping({"LtlOrderEsDTO.invoiceUserName"})
	@ApiModelProperty("发货人姓名")
    private String invoiceUserName;
	
	@Mapping({"LtlOrderEsDTO.invoiceUserTel"})
	@ApiModelProperty("发货人电话")
    private Integer invoiceUserTel;
	
	@Required 
	@Mapping({"LtlOrderEsDTO.invoiceUserMobile"})
	@ApiModelProperty("发货人手机号")
    private String invoiceUserMobile;
	
	@Required
	@Mapping({"LtlOrderEsDTO.invoiceAddress"})
	@ApiModelProperty("发货地址")
    private String invoiceAddress;
	
	@Mapping({"LtlOrderEsDTO.receiptCompanyName"})
	@ApiModelProperty("收货公司名称")
    private String receiptCompanyName;
	
	@Mapping({"LtlOrderEsDTO.receiptUserName"})
	@ApiModelProperty("收货人姓名")
    private String receiptUserName;
	
	@Mapping({"LtlOrderEsDTO.receiptUserTel"})
	@ApiModelProperty("收货人电话")
    private String receiptUserTel;
	
	@Mapping({"LtlOrderEsDTO.receiptUserMobile"})
	@ApiModelProperty("收货人手机号")
    private String receiptUserMobile;
	
	@Mapping({"LtlOrderEsDTO.receiptAddress"})
	@ApiModelProperty("收货地址")
    private String receiptAddress;
	
	@Mapping({"LtlOrderEsDTO.pickupFee"})
	@ApiModelProperty("提货费")
    private BigDecimal pickupFee;
	
	@Mapping({"LtlOrderEsDTO.goodsPayment"})
	@ApiModelProperty("代收货款")
    private BigDecimal goodsPayment;
	
	@Mapping({"LtlOrderEsDTO.declareValue"})
	@ApiModelProperty("声明价值")
    private BigDecimal declareValue;
	
	@Mapping({"LtlOrderEsDTO.estimateFee"})
	@ApiModelProperty("预计运费")
    private BigDecimal estimateFee;
	
	@Mapping({"LtlOrderEsDTO.paymentType"})
	@ApiModelProperty("支付方式")
	private Integer paymentType;
	
	@Mapping({"LtlOrderEsDTO.receiptType"})
	@ApiModelProperty("回单要求")
	private Integer receiptType;
	
	@Mapping({"LtlOrderEsDTO.deliveryType"})
	@ApiModelProperty("发货方式")
	private Integer deliveryType;
	
	@Mapping({"LtlOrderEsDTO.sendType"})
	@ApiModelProperty("派送方式")
	private Integer sendType;
	
	@Mapping({"LtlOrderEsDTO.appointDeliveryTime"})
	@ApiModelProperty("预约提货时间")
	private Long appointDeliveryTime;
	
	@Mapping({"LtlOrderEsDTO.iurgent"})
	@ApiModelProperty("是否加急")
	private Boolean iurgent;
	
	@Mapping({"LtlOrderEsDTO.remark"})
	@ApiModelProperty("备注")
	private String remark;
	
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;

}