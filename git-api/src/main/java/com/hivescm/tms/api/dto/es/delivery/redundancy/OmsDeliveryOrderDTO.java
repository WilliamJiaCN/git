package com.hivescm.tms.api.dto.es.delivery.redundancy;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * OMS销售订单主表
 * @author ke.huang
 * @date 2017年10月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OmsDeliveryOrderDTO implements Serializable{
	private static final long serialVersionUID = -5777792620149644492L;
	
	@Required
	@ApiModelProperty("商品列表")
	private List<OmsDeliveryOrderGoodsDTO> goods;
	
	@Required
	@Mapping({"OrderEsDTO.orderCode"})
	@ApiModelProperty("订单编号")
    private String orderCode;//OMS传
	
	@Mapping({"OrderEsDTO.orderSource"})
	@ApiModelProperty("销售平台")
    private Integer orderSource;//OMS传 1=自动售货机，空值或其它值当普通订单处理
	
	@Mapping({"OrderEsDTO.externalOrderCode"})
	@ApiModelProperty("外部订单编号")
	private String externalOrderCode;//OMS传
	
	@Required
	@Mapping({"OrderEsDTO.mainOrderCode"})
	@ApiModelProperty("主订单编号")
	private String mainOrderCode;
	
	@Required
	@Mapping({"OrderEsDTO.orderCreateTime"})
	@ApiModelProperty("下单时间")
    private Long orderCreateTime;//OMS传
	
	@Mapping({"OrderEsDTO.appointDate"})
	@ApiModelProperty("指定日期")
    private String appointDate;//OMS传
	
	@Mapping({"OrderEsDTO.appointStartTime"})
	@ApiModelProperty("指定开始时间")
    private String appointStartTime;//OMS传
	
	@Mapping({"OrderEsDTO.appointEndTime"})
	@ApiModelProperty("指定结束时间")
    private String appointEndTime;//OMS传
	
	@Required @Mapping
	@ApiModelProperty("仓库ID")
    private Long warehouseId;//发货仓库ID  名称省市区地址TMS自己查
	
	@Required
	@Mapping({"OrderEsDTO.dealerId"})
	@ApiModelProperty("经销商ID")
    private Long dealerId;//OMS传
	
	@Required
	@Mapping({"OrderEsDTO.dealerName"})
	@ApiModelProperty("经销商名称")
	private String dealerName;
	
	@Mapping({"OrderEsDTO.invoiceUserId"})
	@ApiModelProperty("发货人ID")
    private Integer invoiceUserId;//业务员ID  OMS传
	
	@Required
	@Mapping({"OrderEsDTO.invoiceUserName"})
	@ApiModelProperty("发货人姓名")
    private String invoiceUserName;//业务员姓名  OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.invoiceUserMobile"})
	@ApiModelProperty("发货人手机号")
    private String invoiceUserMobile;//OMS传
	
	@Mapping({"OrderEsDTO.receiptCompanyId"})
	@ApiModelProperty("收货公司ID") // 门店ID OMS传
    private Long receiptCompanyId;
	
	@Mapping({"OrderEsDTO.receiptCompanyName"})
	@ApiModelProperty("收货公司名称")
    private String receiptCompanyName; // 门店名称 OMS传
	
	
	@Mapping({"OrderEsDTO.receiptUserId"})
	@ApiModelProperty("收货人ID")
    private Integer receiptUserId;//  无
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserName"})
	@ApiModelProperty("收货人姓名")
    private String receiptUserName;//手填  OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserMobile"})
	@ApiModelProperty("收货人手机号")
    private String receiptUserMobile;//手填  OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserProvinceId"})
	@ApiModelProperty("收货人省ID")
    private String receiptUserProvinceId;// OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserProvinceName"})
	@ApiModelProperty("收货人省名称")
    private String receiptUserProvinceName; //OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserCityId"})
	@ApiModelProperty("收货人市ID")
    private String receiptUserCityId;// OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserCityName"})
	@ApiModelProperty("收货人市名称")
    private String receiptUserCityName;// OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserDistrictId"})
	@ApiModelProperty("收货人区ID")
    private String receiptUserDistrictId; // OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserDistrictName"})
	@ApiModelProperty("收货人区名称")
    private String receiptUserDistrictName; // OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserStreetId"})
	@ApiModelProperty("收货人街道ID")
    private String receiptUserStreetId; // OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptUserStreetName"})
	@ApiModelProperty("收货人街道名称")
    private String receiptUserStreetName;//冗余
	
	@Mapping({"OrderEsDTO.goodsPayment"})
	@ApiModelProperty("代收货款")
    private BigDecimal goodsPayment; // OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.receiptAddress"})
	@ApiModelProperty("收货地址")
    private String receiptAddress; // OMS传
	
	@Mapping({"OrderEsDTO.remark"})
	@ApiModelProperty("备注")
	private String remark;//客户备注  OMS传
	
	@Required 
	@Mapping({"OrderEsDTO.merchandId"})
	@ApiModelProperty("商户ID")
	private Long merchandId;//OMS 传
	
	@Required 
	@Mapping({"OrderEsDTO.merchandName"})
	@ApiModelProperty("商户名称")
	private String merchandName;//OMS 传
	
	@Mapping({"OrderEsDTO.iurgent"})
	@ApiModelProperty("是否加急")
	private Boolean iurgent;//OMS 传
	
	@Required 
	@Mapping({"OrderEsDTO.paymentType"})
	@ApiModelProperty("支付方式")
	private Integer paymentType;//OMS 
	
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
	
	@Mapping({"OrderEsDTO.storeGlobalId"})
	@ApiModelProperty("门店全局客户ID")
	private Long storeGlobalId;
	
	@Mapping
	@ApiModelProperty("订单配送类型 1,物流 2,快递 3,邮寄")
	private Integer orderDeliveryType;
	
	@Mapping
	@ApiModelProperty("快件产品类型")
    private int expressType;
	
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
}