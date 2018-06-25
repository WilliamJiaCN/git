package com.hivescm.tms.api.dto.es.finance.request
  ;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 财务管理-应付表 数据传输类
 * 
 * @author wenxiong.jia
 * @date 2018/4/27
 */
@Data
@ToString
public class FinanceManagePayReqDTO implements Serializable{

	private static final long serialVersionUID = -6137866830193054016L;

	/**
	 * 来源单据ID
	 */
	@Mapping
	@ApiModelProperty(value = "单据ID")
	private Long sheetId;
	/**
	 * 来源单号
	 */
	@Mapping
	@ApiModelProperty(value = "来源单号")
	private String dataSourceSheetId;
	/**
	 * 公司ID
	 */
	@Mapping
	@ApiModelProperty(value = "公司ID")
	private Long companyId;
	/**
	 * 单据类型 1.运单 2.派车单 3.发车配载单 4.外发中转单 5 装货单 6 卸货单 7 到货确认单
	 */
	@Mapping
	@ApiModelProperty(value = "单据类型 1.运单 2.派车单 3.发车配载单 4.外发中转单 5 装货单 6 卸货单 7 到货确认单")
	private Integer sheetType;
	/**
	 *单据类型名称
	 */
	@Mapping
	@ApiModelProperty(value = "单据类型名称")
	private String sheetTypeName;

	/**
	 * 费用类型
	 */
	@Mapping
	@ApiModelProperty(value = "费用类型 ")
	private String feeType;
	/**
	 * 费用类型名称
	 */
	@Mapping
	@ApiModelProperty(value = "费用类型名称")
	private String feeTypeName;
	/**
	 * 支付方式 1、现付 2、月结 3、回单付 4、到付 5、多笔付 6、免费 7、货款扣
	 */
	@Mapping
	@ApiModelProperty(value = "支付方式 1、现付 2、月结 3、回单付 4、到付 5、多笔付 6、免费 7、货款扣")
	private Integer paymentType;
	/**
	 * 支付方式 1、现付 2、月结 3、回单付 4、到付 5、多笔付 6、免费 7、货款扣
	 */
	@Mapping
	@ApiModelProperty(value = "支付方式名称")
	private String paymentTypeName;
	/**
	 * 付款状态 1、未审核 2、未付款 3、部分付款 4、已付款
	 */
	@Mapping
	@ApiModelProperty(value = "付款状态 1、未审核 2、未付款 3、部分付款 4、已付款")
	private Integer paymentStatus;
	/**
	 * 付款状态名称
	 */
	@Mapping
	@ApiModelProperty(value = "付款状态名称")
	private String paymentStatusName;
	/**
	 * 业务日期
	 */
	@Mapping
	@ApiModelProperty(value = "业务日期")
	private Long businessDate;
	/**
	 * 业务网点ID
	 */
	@Mapping
	@ApiModelProperty(value = "业务网点ID")
	private Integer businessNetworkId;
	/**
	 * 业务网点名称
	 */
	@Mapping
	@ApiModelProperty(value = "业务网点名称")
	private String businessNetworkName;
	/**
	 * 付款网点ID
	 */
	@Mapping
	@ApiModelProperty(value = "付款网点ID")
	private Integer paymentNetworkId;
	/**
	 * 付款网点名称
	 */
	@Mapping
	@ApiModelProperty(value = "付款网点名称")
	private String paymentNetworkName;
	/**
	 * 收款方id
	 */
	@Mapping
	@ApiModelProperty(value = "收款方id")
	private Integer payeeId;
	/**
	 * 收款方
	 */
	@Mapping
	@ApiModelProperty(value = "收款方")
	private String payee;
	/**
	 * 费用备注
	 */
	@Mapping
	@ApiModelProperty(value = "费用备注")
	private String feeRemark;
	/**
	 * 应付金额
	 */
	@Mapping
	@ApiModelProperty(value = "应付金额")
	private BigDecimal payableAmount;
	/**
	 * 已付金额
	 */
	@Mapping
	@ApiModelProperty(value = "已付金额")
	private BigDecimal paidAmount;
	/**
	 * 未付金额
	 */
	@Mapping
	@ApiModelProperty(value = "未付金额")
	private BigDecimal unpaidAmount;
	/**
	 * 付款手续费
	 */
	@Mapping
	@ApiModelProperty(value = "付款手续费")
	private BigDecimal paymentFee;
	/**
	 * 实付金额
	 */
	@Mapping
	@ApiModelProperty(value = "实付金额")
	private BigDecimal actualPaidAmount;
	/**
	 * 业务员ID
	 */
	@Mapping
	@ApiModelProperty(value = "业务员ID")
	private Integer salesmanId;
	/**
	 * 业务员姓名
	 */
	@Mapping
	@ApiModelProperty(value = "业务员姓名")
	private String salesmanName;
	/**
	 * 发货方ID
	 */
	@Mapping
	@ApiModelProperty(value = "发货方ID")
	private Integer shipperId;


	/**
	 * 发货方名称
	 */
	@Mapping
	@ApiModelProperty(value = "发货方名称")
	private String shipperName;

	/**
	 * 发货方客商档案ID/会员ID
	 */
	@Mapping
	@ApiModelProperty(value = "发货方客商档案ID/会员ID")
	private Long invoiceCustomerVipId;
	/**
	 * 发货公司ID
	 */
	@Mapping
	@ApiModelProperty(value = "发货公司ID")
	private Long invoiceCompanyId;
	/**
	 * 发货公司名称
	 */
	@Mapping
	@ApiModelProperty(value = "发货公司名称")
	private String invoiceCompanyName;
	/**
	 * 发货人ID
	 */
	@Mapping
	@ApiModelProperty(value = "发货人ID")
	private Integer invoiceUserId;
	/**
	 * 发货人姓名
	 */
	@Mapping
	@ApiModelProperty(value = "发货人姓名")
	private String invoiceUserName;
	/**
	 * 发货人手机号
	 */
	@Mapping
	@ApiModelProperty(value = "发货人手机号")
	private String invoiceUserMobile;
	/**
	 * 发货人电话
	 */
	@Mapping
	@ApiModelProperty(value = "发货人电话")
	private String invoiceUserTel;
	/**
	 * 收货方ID
	 */
	@Mapping
	@ApiModelProperty(value = "收货方ID")
	private Integer receiverId;
	/**
	 * 收货方名称
	 */
	@Mapping
	@ApiModelProperty(value = "收货方名称")
	private String receiverName;
	/**
	 * 收货公司ID
	 */
	@Mapping
	@ApiModelProperty(value = "收货公司ID")
	private Long receiptCompanyId;
	/**
	 * 收货公司名称
	 */
	@Mapping
	@ApiModelProperty(value = "收货公司名称")
	private String receiptCompanyName;
	/**
	 * 收货人ID
	 */
	@Mapping
	@ApiModelProperty(value = "收货人ID")
	private Integer receiptUserId;
	/**
	 * 收货人姓名
	 */
	@Mapping
	@ApiModelProperty(value = "收货人姓名")
	private String receiptUserName;
	/**
	 * 收货人手机号
	 */
	@Mapping
	@ApiModelProperty(value = "收货人手机号")
	private String receiptUserMobile;
	/**
	 * 收货人电话
	 */
	@Mapping
	@ApiModelProperty(value = "收货人电话")
	private String receiptUserTel;
	/**
	 * 车牌号码
	 */
	@Mapping
	@ApiModelProperty(value = "车牌号码")
	private String vehicleNo;
	/**
	 * 司机姓名
	 */
	@Mapping
	@ApiModelProperty(value = "司机姓名")
	private String driverName;
	/**
	 * 手机号码
	 */
	@Mapping
	@ApiModelProperty(value = "手机号码")
	private String phoneNo;
	/**
	 * 过账确认人
	 */
	@Mapping
	@ApiModelProperty(value = "过账确认人")
	private Integer postConfirmerId;
	/**
	 * 过账确认人姓名
	 */
	@Mapping
	@ApiModelProperty(value = "过账确认人姓名")
	private String postConfirmerName;
	/**
	 * 过账时间
	 */
	@Mapping
	@ApiModelProperty(value = "过账时间")
	private Long postTime;
	/**
	 * 创建人ID
	 */
	@Mapping
	@ApiModelProperty(value = "创建人ID")
	private Integer createUserId;
	/**
	 * 创建人姓名
	 */
	@Mapping
	@ApiModelProperty(value = "创建人姓名")
	private String createUserName;
	/**
	 * 创建时间
	 */
	@Mapping
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
	
	 /**
     * 中转id
     */
    @Mapping
    @ApiModelProperty("中转id")
    private Long transitBillId;
    
    /**
     * 中转单号
     */
    @Mapping
    @ApiModelProperty("中转单号")
    private String transitBillCode;
}

