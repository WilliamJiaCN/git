package com.hivescm.tms.api.dto.es.ltlorder.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 零担订单主表
 * 
 * @author wenxiong.jia
 * @date 2018/4/9
 */
@Data
@ToString
public class LtlOrderReqDTO implements Serializable {
	private static final long serialVersionUID = 6393716235270379909L;

	@Required
	@ApiModelProperty("商品列表")
	private List<LtlOrderGoodsReqDTO> goods;

	@Required
	@ApiModelProperty("发货方")
	private LtlOrderInvoiceReqDTO ltlOrderInvoiceReqDTO;

	@Required
	@ApiModelProperty("收货方")
	private LtlOrderReceiptReqDTO ltlOrderReceiptReqDTO;

	@Mapping({ "LtlOrderEsDTO.id" })
	private Long id;

	@Mapping({ "LtlOrderEsDTO.userId" })
	private Integer userId;

	@Mapping({ "LtlOrderEsDTO.companyId" })
	@ApiModelProperty("公司ID")
	private Long companyId;

	@ApiModelProperty("集团ID")
	private Integer groupId;

	@Mapping({ "LtlOrderEsDTO.orderCode" })
	@ApiModelProperty("订单编号")
	private String orderCode;

	@Mapping({ "LtlOrderEsDTO.code" })
	private String code;

	@Mapping
	@ApiModelProperty("订单来源名称")
	private String orderSourceName;

	@Required
	@Mapping({ "LtlOrderEsDTO.orderSource" })
	@ApiModelProperty("订单来源")
	private Integer orderSource;

	@Mapping({ "LtlOrderEsDTO.receiveNetworkId" })
	@ApiModelProperty("接收网点ID")
	private Integer receiveNetworkId;

	@Mapping({ "LtlOrderEsDTO.receiveNetworkName" })
	@ApiModelProperty("接收网点名称")
	private String receiveNetworkName;

	@Mapping({ "LtlOrderEsDTO.deliveryNetworkId" })
	@ApiModelProperty("发货网点ID")
	private Integer deliveryNetworkId;

	@Mapping({ "LtlOrderEsDTO.deliveryNetworkName" })
	@ApiModelProperty("发货网点名称")
	private String deliveryNetworkName;

	@Mapping
	@ApiModelProperty("经度")
	private String lngs;
	@Mapping
	@ApiModelProperty("纬度")
	private String lats;

	@Mapping({ "LtlOrderEsDTO.salesmanId" })
	@ApiModelProperty("业务员ID")
	private Integer salesmanId;

	@Mapping({ "LtlOrderEsDTO.salesmanName" })
	@ApiModelProperty("业务员姓名")
	private String salesmanName;

	@Mapping({ "LtlOrderEsDTO.pickupFee" })
	@ApiModelProperty("提货费")
	private BigDecimal pickupFee;

	@Mapping({ "LtlOrderEsDTO.goodsPayment" })
	@ApiModelProperty("代收货款")
	private BigDecimal goodsPayment;

	@Mapping({ "LtlOrderEsDTO.declareValue" })
	@ApiModelProperty("声明价值")
	private BigDecimal declareValue;

	@Mapping({ "LtlOrderEsDTO.estimateFee" })
	@ApiModelProperty("预计运费")
	private BigDecimal estimateFee;

	@Mapping({ "LtlOrderEsDTO.paymentType" })
	@ApiModelProperty("支付方式")
	private Integer paymentType;

	@Mapping({ "LtlOrderEsDTO.paymentTypeName" })
	@ApiModelProperty("支付方式名称")
	private String paymentTypeName;

	@Mapping({ "LtlOrderEsDTO.receiptType" })
	@ApiModelProperty("回单要求")
	private Integer receiptType;

	@Mapping({ "LtlOrderEsDTO.receiptTypeName" })
	@ApiModelProperty("回单要求名称")
	private String receiptTypeName;

	@Mapping({ "LtlOrderEsDTO.deliveryType" })
	@ApiModelProperty("发货方式")
	private Integer deliveryType;

	@Mapping({ "LtlOrderEsDTO.deliveryTypeName" })
	@ApiModelProperty("发货方式")
	private String deliveryTypeName;

	@Mapping({ "LtlOrderEsDTO.sendType" })
	@ApiModelProperty("派送方式")
	private Integer sendType;

	@Mapping({ "LtlOrderEsDTO.sendTypeName" })
	@ApiModelProperty("派送方式名称")
	private String sendTypeName;

	@Mapping({ "LtlOrderEsDTO.appointDeliveryTime" })
	@ApiModelProperty("预约提货时间")
	private Long appointDeliveryTime;

	@Mapping({ "LtlOrderEsDTO.isUrgent" })
	@ApiModelProperty("是否加急")
	private Boolean isUrgent;

	@Mapping({ "LtlOrderEsDTO.status" })
	@ApiModelProperty("订单状态")
	private Integer status;

	@Mapping({ "LtlOrderEsDTO.statusName" })
	@ApiModelProperty("订单状态名称")
	private String statusName;

	@Mapping({ "LtlOrderEsDTO.batchCode" })
	@ApiModelProperty("派车批次")
	private String batchCode;

	@Mapping({ "LtlOrderEsDTO.isNotice" })
	@ApiModelProperty("等通知放货")
	private Boolean isNotice;

	@Mapping({ "LtlOrderEsDTO.remark" })
	@ApiModelProperty("备注")
	private String remark;

	/**
	 * 为前端回显目的地加的字段
	 */
	@Mapping
	@ApiModelProperty("目的地省id")
	private Long destProvId;

	/**
	 * 为前端回显目的地加的字段
	 */
	@Mapping
	@ApiModelProperty("目的地省名称")
	private String destProvName;

	@Mapping({ "LtlOrderEsDTO.endCityId" })
	@ApiModelProperty("目的城市ID")
	private String endCityId;

	@Mapping({ "LtlOrderEsDTO.endCityName" })
	@ApiModelProperty("目的城市名称")
	private String endCityName;

	/**
	 * 为前端回显目的地加的字段
	 */
	@Mapping
	@ApiModelProperty("目的地区id")
	private Long destDistrictId;

	/**
	 * 为前端回显目的地加的字段
	 */
	@Mapping
	@ApiModelProperty("目的地区名称")
	private String destDistrictName;

	@Mapping
	@ApiModelProperty("创建人ID")
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