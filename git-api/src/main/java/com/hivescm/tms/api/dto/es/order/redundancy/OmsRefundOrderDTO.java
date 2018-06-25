package com.hivescm.tms.api.dto.es.order.redundancy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * OMS销退订单主表
 * @author ke.huang
 * @date 2017年10月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OmsRefundOrderDTO implements Serializable{
	private static final long serialVersionUID = -5777792620149644492L;
	
	@Required
	@ApiModelProperty("商品列表")
	private List<OmsRefundOrderGoodsDTO> goods;
	
	@Required
	@Mapping({"OrderEsDTO.orderCode"})
	@ApiModelProperty("订单编号")//OMS传
    private String orderCode;
	
	@Mapping({"OrderEsDTO.externalOrderCode"})
	@ApiModelProperty("外部订单编号")
	private String externalOrderCode;
	
	@Mapping({"OrderEsDTO.orderSource"})
	@ApiModelProperty("销售平台")
    private Integer orderSource;//OMS传  1=自动售货机，空值或其它值当普通订单处理
	
	@Required
	@Mapping({"OrderEsDTO.orderCreateTime"})
	@ApiModelProperty("下单时间")
    private Long orderCreateTime;//OMS传
	
	@Mapping({"OrderEsDTO.originalDriverId"})
	@ApiModelProperty("原司机ID")
    private Integer originalDriverId;//TMS销退时先传递给OMS  OMS下来的订单再传递过来   后续销退流程  OMS提供 
	
	@Mapping({"OrderEsDTO.originalDriverName"})
	@ApiModelProperty("原司机姓名")
    private String originalDriverName;//TMS销退时先传递给OMS  OMS下来的订单再传递过来   后续销退流程  OMS提供 
	
	@Mapping({"OrderEsDTO.originalCarrierId"})
	@ApiModelProperty("原承运商ID")
    private Long originalCarrierId; //TMS销退时先传递给OMS  OMS下来的订单再传递过来   后续销退流程  OMS提供 
	
	@Mapping({"OrderEsDTO.originalCarrierName"})
	@ApiModelProperty("原承运商名称")
    private String originalCarrierName; //TMS销退时先传递给OMS  OMS下来的订单再传递过来   后续销退流程  OMS提供 
	
	@Required
	@Mapping({"OrderEsDTO.returnType"})
	@ApiModelProperty("销退单类型") // 1=普通销退 2=拒收销退 3=取消销退
    private Integer returnType;//TMS销退时先传递给OMS  OMS下来的订单再传递过来   后续销退流程  OMS提供 
	
	@Mapping({"OrderEsDTO.originalOrderCode"})
	@ApiModelProperty("原订单号")
    private String originalOrderCode;// OMS传
	
	@Mapping({"OrderEsDTO.appointDate"})
	@ApiModelProperty("指定日期")
    private String appointDate;//TMS销退时先传递给OMS  OMS下来的订单再传递过来   后续销退流程  OMS提供 
	
	@Mapping({"OrderEsDTO.appointStartTime"})
	@ApiModelProperty("指定开始时间")
    private String appointStartTime;//TMS销退时先传递给OMS  OMS下来的订单再传递过来   后续销退流程  OMS提供 
	
	@Mapping({"OrderEsDTO.appointEndTime"})
	@ApiModelProperty("指定结束时间")
    private String appointEndTime;//TMS销退时先传递给OMS  OMS下来的订单再传递过来   后续销退流程  OMS提供 
	
	@Required @Mapping
	@ApiModelProperty("仓库ID")
    private Long warehouseId;// OMS传
	
	@Required
	@Mapping({"OrderEsDTO.startCityId"})
	@ApiModelProperty("始发城市ID")
    private String startCityId;// 门店所在市 OMS传
	
	@Required
	@Mapping({"OrderEsDTO.invoiceCompanyId"})
	@ApiModelProperty("发货公司ID")
    private Long invoiceCompanyId;//门店ID OMS传
	
	@Required
	@Mapping({"OrderEsDTO.invoiceCompanyName"})
	@ApiModelProperty("发货人公司名称")
	private String invoiceCompanyName;//冗余
	
	
	
	@Mapping({"OrderEsDTO.invoiceUserId"})
	@ApiModelProperty("发货人ID")
    private Integer invoiceUserId;//无
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserName"})
	@ApiModelProperty("发货人姓名")
    private String invoiceUserName;//手填  OMS传
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserMobile"})
	@ApiModelProperty("发货人手机号")
    private String invoiceUserMobile;//手填  OMS传
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserProvinceId"})
	@ApiModelProperty("发货人省ID")
    private String invoiceUserProvinceId;//门店所在省 OMS传
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserProvinceName"})
	@ApiModelProperty("发货人省名称")
	private String invoiceUserProvinceName;//门店所在省 OMS传
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserCityId"})
	@ApiModelProperty("发货人市ID")
    private String invoiceUserCityId;//门店所在市 OMS传
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserCityName"})
	@ApiModelProperty("发货人省名称")
	private String invoiceUserCityName;//门店所在省 OMS传
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserDistrictId"})
	@ApiModelProperty("发货人区ID")
    private String invoiceUserDistrictId;//同上
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserDistrictName"})
	@ApiModelProperty("发货人区名称")
	private String invoiceUserDistrictName;//同上
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserStreetId"})
	@ApiModelProperty("发货人街道ID")
    private String invoiceUserStreetId;//同上
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserStreetName"})
	@ApiModelProperty("发货人街道名称")
	private String invoiceUserStreetName;//同上
	
	@Required
	@Mapping({"OrderEsDTO.invoiceAddress"})
	@ApiModelProperty("发货地址")
    private String invoiceAddress;//门店地址 OMS传
	
	@Required
	@Mapping({"OrderEsDTO.receiptCompanyId"})
	@ApiModelProperty("收货公司ID")
    private Long receiptCompanyId;//仓储服务商ID OMS传
	
	@Mapping({"OrderEsDTO.receiptUserId"})
	@ApiModelProperty("收货人ID")
    private Integer receiptUserId;//业务员ID  OMS传
	
	@Required
	@Mapping({"OrderEsDTO.receiptUserName"})
	@ApiModelProperty("收货人姓名")
    private String receiptUserName;//业务员姓名  OMS传
	
	@Required
	@Mapping({"OrderEsDTO.receiptUserMobile"})
	@ApiModelProperty("收货人手机号")
    private String receiptUserMobile;//业务员手机号  OMS传
	
	@Required
	@Mapping({"OrderEsDTO.dealerId"})
	@ApiModelProperty("经销商ID")
    private Long dealerId;//OMS 传
	
	@Required
	@Mapping({"OrderEsDTO.dealerName"})
	@ApiModelProperty("经销商名称")
	private String dealerName;//OMS 传
	
	@Required
	@Mapping({"OrderEsDTO.merchandId"})
	@ApiModelProperty("商户ID")
	private Long merchandId;//OMS 传
	
	@Required
	@Mapping({"OrderEsDTO.merchandName"})
	@ApiModelProperty("商户名称")
	private String merchandName;//OMS 传
	
	@Required
	@Mapping({"OrderEsDTO.iloading"})
	@ApiModelProperty("是否已装货")
	private Boolean iloading;//OMS 传
	
	@Mapping({"OrderEsDTO.refuseAmount"})
	@ApiModelProperty("拒签金额")
	private BigDecimal refuseAmount;//OMS 传
	
//	@Mapping({"OrderEsDTO.merchandUnitId"})
//	@ApiModelProperty("商户业务单元ID")
//	private Long merchandUnitId;
	
	@Required
	@Mapping({"OrderEsDTO.merchandGlobalId"})
	@ApiModelProperty("商户全局客户ID")
	private Long merchandGlobalId;
	
	@Required
	@Mapping({"OrderEsDTO.warehouseServerGlobalId"})
	@ApiModelProperty("仓储服务商全局客户ID")
	private Long warehouseServerGlobalId;
	
	@Required
	@Mapping({"OrderEsDTO.dealerGlobalId"})
	@ApiModelProperty("经销商全局客户ID")
	private Long dealerGlobalId;
	
	@Required
	@Mapping({"OrderEsDTO.storeGlobalId"})
	@ApiModelProperty("门店全局客户ID")
	private Long storeGlobalId;

	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;//无ID
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;//OMS传
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;//无ID
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;//OMS传
	@Mapping
	@ApiModelProperty("订单配送类型 1,物流 2,快递 3,邮寄 4,自提")
	private Integer orderDeliveryType;
	@Mapping
	@ApiModelProperty("原运单号")
    private String originalWaybillCode;
	@Mapping
	@ApiModelProperty("备注")
	private String remark;//客户备注  OMS传
}
