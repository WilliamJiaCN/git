package com.hivescm.tms.api.dto.es.order;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单主表
 * @author ke.huang
 * @date 2017年10月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderEsDTO implements Serializable ,Cloneable{
	@Override  
    public OrderEsDTO clone() {  
		OrderEsDTO order = null;  
        try{  
        	order = (OrderEsDTO)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return order;  
    }  
	private static final long serialVersionUID = -5777792620149644492L;
	/**
	 * 筒仓统配
	 * @return
	 */
	public String groupByLineIdAndDispatcherTime() {
		return this.lineId + "_" +this.dispatchingTime;
	}
	/**
	 * 经销商自配分组
	 * @return
	 */
	public String groupBydealerIdAndLineIdAndDispatcherTime() {
		return this.dealerId+"_"+this.lineId + "_" +this.dispatchingTime;
	}
	@ApiModelProperty("商品列表")
	private List<OrderGoodsEsDTO> goods;
	
	/**
	 * 公共属性
	 */
	@Mapping @Required
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	@Mapping @Required
	@ApiModelProperty("仓库名称")
	private String warehouseName;//冗余
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;//冗余
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;//冗余
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
	/**
	 * 订单主表信息
	 */
	@Mapping({"OrderInfoDO.id","OrderInvoiceDO.orderId","OrderReceiptDO.orderId","OrderWaybillDO.orderId","OrderCapacityDO.orderId"})
	@ApiModelProperty("主键")
	private Long id;
	@Logger
	@Mapping({"OrderInfoDO.orderCode"})
	@ApiModelProperty("订单编号")
    private String orderCode; //当前订单编号
	@Mapping({"OrderInfoDO.externalOrderCode"})
	@ApiModelProperty("外部订单编号")
	private String externalOrderCode;//B2B或OMS导入订单编号
	@Mapping({"OrderInfoDO.mainOrderCode"})
	@ApiModelProperty("主订单编号")
	private String mainOrderCode;//销售单时，拆单订单的OMS生成的母订单编号，未拆单订单的订单编号
	@Mapping({"OrderInfoDO.transportType"})
	@ApiModelProperty("订单运输类型")
    private Integer transportType;
	@Mapping({"OrderInfoDO.orderSourceName"})
	@ApiModelProperty("订单来源名称")
    private String orderSourceName;//OMS传
	@Mapping({"OrderInfoDO.orderSource"})
	@ApiModelProperty("订单来源")
    private Integer orderSource;//OMS传
	@Mapping({"OrderInfoDO.transportTypeName"})
	@ApiModelProperty("订单运输类型名称")
	private String transportTypeName;//冗余
	@Mapping({"OrderInfoDO.orderType"})
	@ApiModelProperty("订单类型")
    private Integer orderType;
	@Mapping({"OrderInfoDO.orderTypeName"})
	@ApiModelProperty("订单类型名称")
	private String orderTypeName;//冗余
	@Mapping({"OrderInfoDO.status"})
	@ApiModelProperty("订单状态")
    private Integer status;
	@Mapping({"OrderInfoDO.statusName"})
	@ApiModelProperty("订单状态名称")
	private String statusName;//冗余
	@Mapping({"OrderInfoDO.lineId","OrderCapacityDO.lineId"})
	@ApiModelProperty("运输线路ID")
    private Long lineId;
	@Mapping({"OrderInfoDO.lineName"})
	@ApiModelProperty("运输线路名称")
	private String lineName;//冗余
	@Mapping({"OrderInfoDO.orderCreateTime"})
	@ApiModelProperty("下单时间")
    private Long orderCreateTime;
	@Mapping({"OrderInfoDO.serviceId","OrderCapacityDO.serviceId"})
	@ApiModelProperty("配送服务ID")
    private Integer serviceId;
	@Mapping({"OrderInfoDO.serviceName","OrderCapacityDO.serviceName"})
	@ApiModelProperty("配送服务名称")
	private String serviceName;//冗余
	@Mapping({"OrderInfoDO.platformAcceptTime"})
	@ApiModelProperty("接收时间")
    private Long platformAcceptTime;
	@Mapping({"OrderInfoDO.parentOrderId"})
	@ApiModelProperty("母订单ID")
    private Long parentOrderId;
	@Mapping({"OrderInfoDO.splitType"})
	@ApiModelProperty("拆分类型")
    private Integer splitType;
	@Mapping({"OrderInfoDO.splitTypeName"})
	@ApiModelProperty("拆分类型名称")
	private String splitTypeName;//冗余
	@Mapping({"OrderInfoDO.goodsPayment"})
	@ApiModelProperty("代收货款") 
    private BigDecimal goodsPayment;
	@Mapping({"OrderInfoDO.isDelete"})
	@ApiModelProperty("是否删除")
    private Boolean isDelete;
	@Mapping({"OrderInfoDO.appointDate"})
	@ApiModelProperty("指定日期")
    private String appointDate;
	@Mapping({"OrderInfoDO.appointStartTime"})
	@ApiModelProperty("指定开始时间")
    private String appointStartTime;
	@Mapping({"OrderInfoDO.appointEndTime"})
	@ApiModelProperty("指定结束时间")
    private String appointEndTime;
	@Mapping({"OrderInfoDO.dispatchingTime"})
	@ApiModelProperty("起配时间")
    private Long dispatchingTime;//yyyy-MM-dd HH:mm:ss 配送时间提取HH:mm:ss
	@Mapping({"OrderInfoDO.deliveryTime"})
	@ApiModelProperty("提货时间")
	private Long deliveryTime;//yyyy-MM-dd HH:mm:ss 提货时间提取HH:mm:ss
	@Mapping({"OrderInfoDO.startCityId"})
	@ApiModelProperty("始发城市ID")
    private String startCityId;
	@Mapping({"OrderInfoDO.startCityName"})
	@ApiModelProperty("始发城市名称")
	private String startCityName;//冗余
	@Mapping({"OrderInfoDO.endCityId"})
	@ApiModelProperty("目的城市ID")
    private String endCityId;
	@Mapping({"OrderInfoDO.endCityName"})
	@ApiModelProperty("目的城市名称")
	private String endCityName;//冗余
	@Mapping({"OrderInfoDO.originalCarrierId"})
	@ApiModelProperty("原承运商ID")
    private Long originalCarrierId;
	@Mapping({"OrderInfoDO.originalCarrierName"})
	@ApiModelProperty("原承运商名称")
	private String originalCarrierName;//冗余
	@Mapping({"OrderInfoDO.originalDriverId"})
	@ApiModelProperty("原司机ID")
    private Integer originalDriverId;
	@Mapping({"OrderInfoDO.originalDriverName"})
	@ApiModelProperty("原承司机名称")
	private String originalDriverName;//冗余
	@Mapping({"OrderInfoDO.originalOrderCode"})
	@ApiModelProperty("原订单号")
    private String originalOrderCode;//销退单对应的销售单订单编号
	@Mapping({"OrderInfoDO.returnType"})
	@ApiModelProperty("销退单类型")
    private Integer returnType;
	@Mapping({"OrderInfoDO.returnTypeName"})
	@ApiModelProperty("销退单类型名称")
	private String returnTypeName;//冗余
	@Mapping({"OrderInfoDO.warehouseServerId"})
	@ApiModelProperty("仓储服务商ID") @Required
    private Long warehouseServerId;
	@Mapping({"OrderInfoDO.warehouseServerName"})
	@ApiModelProperty("仓储服务商名称") @Required
	private String warehouseServerName;//冗余
	@Mapping({"OrderInfoDO.dealerId"})
	@ApiModelProperty("经销商ID") @Required
    private Long dealerId;
	@Mapping({"OrderInfoDO.dealerName"})
	@ApiModelProperty("经销商名称") @Required
	private String dealerName;//冗余
	@Mapping({"OrderInfoDO.serverType"})
	@ApiModelProperty("服务类型")
    private Integer serverType;
	@Mapping({"OrderInfoDO.serverTypeName"})
	@ApiModelProperty("服务类型名称")
	private String serverTypeName;//冗余
	@Mapping({"OrderInfoDO.capacityTime","OrderCapacityDO.capacityTime"})
	@ApiModelProperty("运力分配时间")
    private Long capacityTime;
	@Mapping({"OrderInfoDO.refuseTime","OrderCapacityDO.refuseTime"})
	@ApiModelProperty("拒接时间")
    private Long refuseTime;
	@Mapping({"OrderInfoDO.recallTime","OrderCapacityDO.retractTime"})
	@ApiModelProperty("撤回时间")
    private Long recallTime;
	@Mapping({"OrderInfoDO.remark"})
	@ApiModelProperty("备注")
	private String remark;//客户备注  OMS传
	@Mapping({"OrderInfoDO.merchandId"})
	@ApiModelProperty("商户ID") @Required
	private Long merchandId;
	@Mapping({"OrderInfoDO.merchandName"}) @Required
	@ApiModelProperty("商户名称")
	private String merchandName;//冗余
	@Mapping({"OrderInfoDO.isUrgent"})
	@ApiModelProperty("是否加急")
	private Boolean iurgent;
	@Mapping({"OrderInfoDO.paymentType"})
	@ApiModelProperty("支付方式")
	private Integer paymentType;
	@Mapping({"OrderInfoDO.paymentTypeName"})
	@ApiModelProperty("支付方式名称")
	private String paymentTypeName;
	@Mapping({"OrderInfoDO.isLoading"})
	@ApiModelProperty("是否已装货")
	private Boolean iloading;
	@Mapping({"OrderInfoDO.refuseAmount"})
	@ApiModelProperty("拒签金额")
	private BigDecimal refuseAmount;
//	@Mapping({"OrderInfoDO.merchandUnitId"})
//	@ApiModelProperty("商户业务单元ID")
//	private Long merchandUnitId;
	@Mapping({"OrderInfoDO.merchandGlobalId"})
	@ApiModelProperty("商户全局客户ID") @Required
	private Long merchandGlobalId;
	@Mapping({"OrderInfoDO.warehouseServerGlobalId"})
	@ApiModelProperty("仓储服务商全局客户ID")
	private Long warehouseServerGlobalId;
	@Mapping({"OrderInfoDO.dealerGlobalId"})
	@ApiModelProperty("经销商全局客户ID") @Required
	private Long dealerGlobalId;
	@Mapping({"OrderInfoDO.storeGlobalId"})
	@ApiModelProperty("门店全局客户ID") @Required
	private Long storeGlobalId;
	
	
	//商品冗余信息
	@ApiModelProperty("商品名称")//冗余商品表名称，以"/"间隔
	private String goodsNames;
	@ApiModelProperty("商品类型")//冗余商品表类型，以"/"间隔
	private String goodsTypes;
	@ApiModelProperty("总数")
	private Integer totalCount;//冗余商品表总数
	@ApiModelProperty("总重量")
	private BigDecimal totalWeight;//冗余商品表总重量
	@ApiModelProperty("总体积")
	private BigDecimal totalVolume;//冗余商品表总体积
	@ApiModelProperty("最大提货数")
	private Integer maxDelivery;
	@ApiModelProperty("SKUID")
	private String skuId;
	
	
	
	/**
	 * 发货人
	 */
	@Mapping({"OrderInvoiceDO.id"})
	@ApiModelProperty("发货人主键")
	private Long invoiceId;
	@Mapping({"OrderInvoiceDO.invoiceCompanyId"})
	@ApiModelProperty("发货公司ID")
    private Long invoiceCompanyId;
	@Mapping({"OrderInvoiceDO.invoiceCompanyName"})
	@ApiModelProperty("发货人公司名称")
	private String invoiceCompanyName;//冗余
	@Mapping({"OrderInvoiceDO.invoiceUserId"})
	@ApiModelProperty("发货人ID")
    private Integer invoiceUserId;
	@Mapping({"OrderInvoiceDO.invoiceUserName"})
	@ApiModelProperty("发货人姓名")
    private String invoiceUserName;
	@Mapping({"OrderInvoiceDO.invoiceUserMobile"})
	@ApiModelProperty("发货人手机号")
    private String invoiceUserMobile;
	@Mapping({"OrderInvoiceDO.invoiceUserTel"})
	@ApiModelProperty("发货人电话号")
    private String invoiceUserTel;
	@Mapping({"OrderInvoiceDO.invoiceUserProvinceId"})
	@ApiModelProperty("发货人省ID")
    private String invoiceUserProvinceId;
	@Mapping({"OrderInvoiceDO.invoiceUserProvinceName"})
	@ApiModelProperty("发货人省名称")
	private String invoiceUserProvinceName;//冗余
	@Mapping({"OrderInvoiceDO.invoiceUserCityId"})
	@ApiModelProperty("发货人市ID")
    private String invoiceUserCityId;
	@Mapping({"OrderInvoiceDO.invoiceUserCityName"})
	@ApiModelProperty("发货人省名称")
	private String invoiceUserCityName;//冗余
	@Mapping({"OrderInvoiceDO.invoiceUserDistrictId"})
	@ApiModelProperty("发货人区ID")
    private String invoiceUserDistrictId;
	@Mapping({"OrderInvoiceDO.invoiceUserDistrictName"})
	@ApiModelProperty("发货人区名称")
	private String invoiceUserDistrictName;//冗余
	@Mapping({"OrderInvoiceDO.invoiceUserStreetId"})
	@ApiModelProperty("发货人街道ID")
    private String invoiceUserStreetId;
	@Mapping({"OrderInvoiceDO.invoiceUserStreetName"})
	@ApiModelProperty("发货人街道名称")
	private String invoiceUserStreetName;//冗余
	@Mapping({"OrderInvoiceDO.invoiceAddress"})
	@ApiModelProperty("发货地址")
    private String invoiceAddress;
	
	/**
	 * 收货人
	 */
	@Mapping({"OrderReceiptDO.id"})
	@ApiModelProperty("收货人主键")
	private Long receiptId;
	@Mapping({"OrderReceiptDO.receiptCompanyId"})
	@ApiModelProperty("收货公司ID")
    private Long receiptCompanyId;
	@Mapping({"OrderReceiptDO.receiptCompanyName"})
	@ApiModelProperty("收货公司名称")
    private String receiptCompanyName;//冗余
	@Mapping({"OrderReceiptDO.receiptUserId"})
	@ApiModelProperty("收货人ID")
    private Integer receiptUserId;
	@Mapping({"OrderReceiptDO.receiptUserName"})
	@ApiModelProperty("收货人姓名")
    private String receiptUserName;//冗余
	@Mapping({"OrderReceiptDO.receiptUserMobile"})
	@ApiModelProperty("收货人手机号")
    private String receiptUserMobile;
	@Mapping({"OrderReceiptDO.receiptUserTel"})
	@ApiModelProperty("收货人电话号")
    private String receiptUserTel;
	@Mapping({"OrderReceiptDO.receiptUserProvinceId"})
	@ApiModelProperty("收货人省ID")
    private String receiptUserProvinceId;
	@Mapping({"OrderReceiptDO.receiptUserProvinceName"})
	@ApiModelProperty("收货人省名称")
    private String receiptUserProvinceName;//冗余
	@Mapping({"OrderReceiptDO.receiptUserCityId"})
	@ApiModelProperty("收货人市ID")
    private String receiptUserCityId;
	@Mapping({"OrderReceiptDO.receiptUserCityName"})
	@ApiModelProperty("收货人市名称")
    private String receiptUserCityName;//冗余
	@Mapping({"OrderReceiptDO.receiptUserDistrictId"})
	@ApiModelProperty("收货人区ID")
    private String receiptUserDistrictId;
	@Mapping({"OrderReceiptDO.receiptUserDistrictName"})
	@ApiModelProperty("收货人区名称")
    private String receiptUserDistrictName;//冗余
	@Mapping({"OrderReceiptDO.receiptUserStreetId"})
	@ApiModelProperty("收货人街道ID")
    private String receiptUserStreetId;
	@Mapping({"OrderReceiptDO.receiptUserStreetName"})
	@ApiModelProperty("收货人街道名称")
    private String receiptUserStreetName;//冗余
	@Mapping({"OrderReceiptDO.receiptAddress"})
	@ApiModelProperty("收货地址")
    private String receiptAddress;
	
	/**
	 * 订单转运单
	 */
	@Mapping({"OrderWaybillDO.id"})
	@ApiModelProperty("订单转运单主键")
	private Long orderWaybillId;
	@Mapping({"OrderWaybillDO.carrierId","OrderCapacityDO.carrierId"})
	@ApiModelProperty("承运商ID")
    private Integer carrierId;
	@Mapping({"OrderWaybillDO.carrierName","OrderCapacityDO.carrierName"})
	@ApiModelProperty("承运商名称")
    private String carrierName;//冗余
	@Mapping({"OrderWaybillDO.branchId","OrderCapacityDO.branchId"})
	@ApiModelProperty("发货网点ID")
    private Integer branchId;
	@Mapping({"OrderWaybillDO.branchName"})
	@ApiModelProperty("发货网点名称")
    private String branchName;//冗余
	@Mapping({"OrderWaybillDO.waybillCode"})
	@ApiModelProperty("运单编码")
    private String waybillCode;
	@Mapping({"OrderWaybillDO.freight"})
	@ApiModelProperty("运费")
    private BigDecimal freight;
	@Mapping({"OrderWaybillDO.effectiveness"})
	@ApiModelProperty("时效")
    private BigDecimal effectiveness;
	@Mapping({"OrderWaybillDO.waybillId"})
	@ApiModelProperty("运单ID")
    private Long waybillId;//冗余
	
	/**
	 * 订单分配信息  记录订单最后一次分配信息
	 */
	@Mapping({"OrderCapacityDO.id"})
	@ApiModelProperty("订单分配主键")
	private Long orderCapacityId;
	@Mapping({"OrderCapacityDO.capacityUser"})
	@ApiModelProperty("分配人ID")
    private Integer capacityUser;
	@Mapping({"OrderCapacityDO.capacityUserName"})
	@ApiModelProperty("分配人姓名")
    private String capacityUserName;//冗余
	@Mapping({"OrderCapacityDO.status"})
	@ApiModelProperty("订单分配信息状态")
    private Integer capacityStatus;
	@Mapping({"OrderCapacityDO.acceptTime"})
	@ApiModelProperty("接单时间")
    private Long acceptTime;
	@Mapping({"OrderCapacityDO.acceptUser"})
	@ApiModelProperty("接单人")
    private Integer acceptUser;
	@Mapping({"OrderCapacityDO.acceptUserName"})
	@ApiModelProperty("接单人姓名")
    private String acceptUserName;//冗余
	@ApiModelProperty("撤回人")
    private Integer retractUser;
	@Mapping({"OrderCapacityDO.retractUserName"})
	@ApiModelProperty("撤回人姓名")
    private String retractUserName;//冗余
	@Mapping({"OrderCapacityDO.refuseUser"})
	@ApiModelProperty("拒接人")
    private Integer refuseUser;
	@Mapping({"OrderCapacityDO.refuseUserName"})
	@ApiModelProperty("拒接人姓名")
    private String refuseUserName;//冗余
	@Mapping({"OrderCapacityDO.invalidTime"})
	@ApiModelProperty("接单失效时间")
    private Long invalidTime;
	@Mapping({"OrderCapacityDO.isAutoAccept"})
	@ApiModelProperty("超时是否自动接收")
    private Boolean isAutoAccept;
	
	@Mapping
	@ApiModelProperty("签收时间")
    private Long signTime;
	
	@Mapping
	@ApiModelProperty("取消时间")
    private Long cancelTime;
	
	@Mapping
	@ApiModelProperty("快递单号")
	private String deliveryCode;
	
	@Mapping
	@ApiModelProperty("订单配送类型 1,物流 2,快递 3,邮寄 4,自提")
	private Integer orderDeliveryType;
	
	@Mapping
	@ApiModelProperty("订单配送类型 1,物流 2,快递 3,邮寄 4,自提")
	private String orderDeliveryTypeName;
	
	@Mapping
	@ApiModelProperty("原运单号")
    private String originalWaybillCode;
	
	@Mapping
	@ApiModelProperty("快件产品类型")
    private int expressType;
	
/*****************************改配相关*********************************/
	
	@Mapping
	@ApiModelProperty("改配单号")
    private String changeDispatcherCode;
	
	@Mapping
	@ApiModelProperty("改配单类型")
    private Integer changeDispatcherType;
	
	@Mapping
	@ApiModelProperty("改配单类型名称")
    private String changeDispatcherTypeName;
	
}