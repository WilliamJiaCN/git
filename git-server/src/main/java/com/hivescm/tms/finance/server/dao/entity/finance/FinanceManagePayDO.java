package com.hivescm.tms.finance.server.dao.entity.finance
  ;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 财务管理-应付表 数据传输类
 * 
 * @author wenxiong.jia
 * @date 2018/4/27
 */
@Data
@ToString
public class FinanceManagePayDO implements Serializable{

    private static final long serialVersionUID = -1524798219854L;
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * 来源单号ID
	 */
	@ApiModelProperty(value = "来源单号ID")
	private Long sheetId;
	/**
	 * 来源单号
	 */
	@ApiModelProperty(value = "来源单号")
	private String dataSourceSheetId;
	/**
	 * 公司ID
	 */
	@ApiModelProperty(value = "公司ID")
	private Long companyId;
	/**
	 * 单据类型 1.运单 2.派车单 3.发车配载单 4.外发中转单 5 装货单 6 卸货单 7 到货确认单
	 */
	@ApiModelProperty(value = "单据类型 1.运单 2.派车单 3.发车配载单 4.外发中转单 5 装货单 6 卸货单 7 到货确认单")
	private Integer sheetType;
	/**
	 * 费用类型
	 */
	@ApiModelProperty(value = "费用类型")
	private String feeType;
	/**
	 * 支付方式 1、现付 2、月结 3、回单付 4、到付 5、多笔付 6、免费 7、货款扣
	 */
	@ApiModelProperty(value = "支付方式 1、现付 2、月结 3、回单付 4、到付 5、多笔付 6、免费 7、货款扣")
	private Integer paymentType;
	/**
	 * 付款状态 1、未审核 2、未付款 3、部分付款 4、已付款
	 */
	@ApiModelProperty(value = "付款状态 1、未审核 2、未付款 3、部分付款 4、已付款")
	private Integer paymentStatus;
	/**
	 * 业务日期
	 */
	@ApiModelProperty(value = "业务日期")
	private Long businessDate;
	/**
	 * 业务网点ID
	 */
	@ApiModelProperty(value = "业务网点ID")
	private Integer businessNetworkId;
	/**
	 * 付款网点ID
	 */
	@ApiModelProperty(value = "付款网点ID")
	private Integer paymentNetworkId;
	/**
	 * 收款方
	 */
	@ApiModelProperty(value = "收款方")
	private String payee;
	/**
	 * 费用备注
	 */
	@ApiModelProperty(value = "费用备注")
	private String feeRemark;
	/**
	 * 应付金额
	 */
	@ApiModelProperty(value = "应付金额")
	private BigDecimal payableAmount;
	/**
	 * 已付金额
	 */
	@ApiModelProperty(value = "已付金额")
	private BigDecimal paidAmount;
	/**
	 * 未付金额
	 */
	@ApiModelProperty(value = "未付金额")
	private BigDecimal unpaidAmount;
	/**
	 * 付款手续费
	 */
	@ApiModelProperty(value = "付款手续费")
	private BigDecimal paymentFee;
	/**
	 * 实付金额
	 */
	@ApiModelProperty(value = "实付金额")
	private BigDecimal actualPaidAmount;
	/**
	 * 业务员ID
	 */
	@ApiModelProperty(value = "业务员ID")
	private Integer salesmanId;
	/**
	 * 发货方客商档案ID/会员ID
	 */
	@ApiModelProperty(value = "发货方客商档案ID/会员ID")
	private Long invoiceCustomerVipId;
	/**
	 * 发货公司ID
	 */
	@ApiModelProperty(value = "发货公司ID")
	private Long invoiceCompanyId;
	/**
	 * 发货人ID
	 */
	@ApiModelProperty(value = "发货人ID")
	private Integer invoiceUserId;
	/**
	 * 发货人手机号
	 */
	@ApiModelProperty(value = "发货人手机号")
	private String invoiceUserMobile;
	/**
	 * 收货公司ID
	 */
	@ApiModelProperty(value = "收货公司ID")
	private Long receiptCompanyId;
	/**
	 * 收货人ID
	 */
	@ApiModelProperty(value = "收货人ID")
	private Integer receiptUserId;
	/**
	 * 收货人手机号
	 */
	@ApiModelProperty(value = "收货人手机号")
	private String receiptUserMobile;
	/**
	 * 车牌号码
	 */
	@ApiModelProperty(value = "车牌号码")
	private String vehicleNo;
	/**
	 * 司机姓名
	 */
	@ApiModelProperty(value = "司机姓名")
	private String driverName;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	private String phoneNo;
	/**
	 * 过账确认人
	 */
	@ApiModelProperty(value = "过账确认人")
	private Integer postConfirmerId;
	/**
	 * 过账时间
	 */
	@ApiModelProperty(value = "过账时间")
	private Long postTime;
	/**
	 * 审核人ID
	 */
	@ApiModelProperty(value = "审核人ID")
	private Integer checkUserId;
	/**
	 * 审核时间
	 */
	@ApiModelProperty(value = "审核时间")
	private Long checkTime;
	/**
	 * 取消审核员ID
	 */
	@ApiModelProperty(value = "取消审核员ID")
	private Integer cancelCheckUserId;
	/**
	 * 取消审核时间
	 */
	@ApiModelProperty(value = "取消审核时间")
	private Long cancelCheckTime;
	/**
	 * 创建人ID
	 */
	@ApiModelProperty(value = "创建人ID")
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
	/**
	 * 修改人ID
	 */
	@ApiModelProperty(value = "修改人ID")
	private Integer updateUserId;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Long updateTime;


}

