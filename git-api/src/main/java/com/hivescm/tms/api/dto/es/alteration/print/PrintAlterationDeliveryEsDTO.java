package com.hivescm.tms.api.dto.es.alteration.print;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 自提改送货信息打印Es类
 * @author zhenming.du
 * @date 2017年10月9日
 * @company 蜂网供应链
 */
@Data
@ToString
public class PrintAlterationDeliveryEsDTO implements Serializable {


	private static final long serialVersionUID = -4004646839313523459L;
	/**
	 * 主键
	 */
	@Mapping
	private @ApiModelProperty("主键") Long id;
	/**
	 * 公司id
	 */
	@Mapping
	private @ApiModelProperty("公司id") Long companyId;
	/**
	 * 公司名称
	 */
	@Mapping
	private @ApiModelProperty("公司名称") String companyName;
	/**
	 * 更改批次
	 */
	@Mapping
	private @ApiModelProperty("更改批次") String chengeBatchCode;
	/**
	 * 处理状态
	 */
	@Mapping
	private @ApiModelProperty("处理状态") Integer processingStatus;

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>冗余运单信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**
	 * 运单id
	 */
	@Mapping
	private @ApiModelProperty("运单id") Long waybillId;
	/**
	 * 运单号
	 */
	private @ApiModelProperty("运单号") String waybillCode;
	/**
	 * 预约配送时间
	 */
	@Mapping
	private @ApiModelProperty("预约配送时间") Long deliverySendTime;
	/**
	 * (原)收货单位
	 */
	private @ApiModelProperty("收货单位") String receiptCompany;
	/**
	 * 收货人
	 */
	@Mapping
	private @ApiModelProperty("收货人") String receiptUser;
	/**
	 * 收货人电话
	 */
	@Mapping({"receiptConsignorTelNo","consignorTelNo"})
	private @ApiModelProperty("收货人电话") String receiptConsignorTelNo;
	/**
	 * 收货人手机
	 */
	@Mapping({"receiptConsignorMobleNo","consignorMobleNo"})
	private @ApiModelProperty("收货人手机") String receiptConsignorMobleNo;
	/**
	 * 收货地址
	 */
	@Mapping({"receiptAddress","address"})
	private @ApiModelProperty("收货地址") String receiptAddress;
	/**
	 * 配送方式
	 */
	@Mapping
	private @ApiModelProperty("配送方式") Integer distributionType;
	/**
	 * 送货费
	 */
	@Mapping
	private @ApiModelProperty("送货费") BigDecimal deliveryFee;
	/**
	 * 代收货款
	 */
	private @ApiModelProperty("代收货款") BigDecimal agencyFund;
	/**
	 * 到付
	 */
	private @ApiModelProperty("到付") String cod;
	/**
	 * 付款方
	 */
	@Mapping
	private @ApiModelProperty("付款方") String payer;
	/**
	 * 收款方
	 */
	@Mapping
	private @ApiModelProperty("收款方") String payee;
	/**
	 * 发货网点ID
	 */
	private @ApiModelProperty("发货网点ID	") Integer invoiceOrgId;
	/**
	 * 发货网点名称
	 */
	private @ApiModelProperty("发货网点名称	") String invoiceOrgName;
	/**
	 * 运输线路id
	 */
	private @ApiModelProperty("运输线路id") Long lineId;
	/**
	 * 运输线路名称
	 */
	private @ApiModelProperty("运输线路名称	") String lineName;
	/**
	 * 到达网点id
	 */
	private @ApiModelProperty("到达网点id") Integer destOrgId;
	/**
	 * 到达网点名称
	 */
	private @ApiModelProperty("到达网点名称") String destOrgName;
	/**
	 * 发货方Id
	 */
	private @ApiModelProperty("发货方Id") Integer invoiceCustomerId;
	/**
	 * 发货单位
	 */
	private @ApiModelProperty("发货单位") String invoiceCompany;
	/**
	 * 发货人
	 */
	private @ApiModelProperty("发货人") String invoiceUser;
	/**
	 * 发货人电话
	 */
	private @ApiModelProperty("发货人电话") String invoiceTelNo;
	/**
	 * 发货人手机号码
	 */
	private @ApiModelProperty("发货人手机号码") String invoiceMobleNo;
	/**
	 * 商品名称 ,“/”间隔
	 */
	private @ApiModelProperty("商品名称") String goodsName;
	/**
	 * 包装名称,“/”间隔
	 */
	private @ApiModelProperty("包装名称") String packingName;
	/**
	 * 总体积
	 */
	private @ApiModelProperty("总体积") String volume;
	/**
	 * 总重量
	 */
	private @ApiModelProperty("总重量") String weight;
	/**
	 * 总件数
	 */
	private @ApiModelProperty("总件数") Integer totalNum;
	/**
	 * 运单备注
	 */
	private @ApiModelProperty("运单备注") String remark;
	/**
	 * 下单日期(应为录单日期)
	 */
	private @ApiModelProperty("下单日期") Long orderDate;
	/**
	 * 录单员ID
	 */
	private @ApiModelProperty("录单员ID") Integer createUserId;
	/**
	 * 录单员姓名
	 */
	private @ApiModelProperty("录单员姓名") String userName;

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>end<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**
	 * 备注
	 */
	@Mapping({"alterationRemark","remark"})
	private @ApiModelProperty("备注") String alterationRemark;
	/**
	 * 申请人
	 */
	@Mapping
	private @ApiModelProperty("申请人") String applicant;
	/**
	 * 申请时间
	 */
	@Mapping
	private @ApiModelProperty("申请时间") Long applicantTime;
	/**
	 * 申请网点
	 */
	@Mapping
	private @ApiModelProperty("申请网点") Integer applicantNetwork;
	/**
	 * 审核状态
	 */
	@Mapping
	private @ApiModelProperty("审核状态") Integer checked;
	/**
	 * 确认人
	 */
	@Mapping
	private @ApiModelProperty("确认人") String confirm;
	/**
	 * 确认时间
	 */
	@Mapping
	private @ApiModelProperty("确认时间") Long confirmTime;
	/**
	 * 创建人
	 */
	@Mapping
	private @ApiModelProperty(hidden = true) Integer createUser;
	/**
	 * 创建时间
	 */
	@Mapping
	private @ApiModelProperty(hidden = true) Long createTime;
	/**
	 * 打印人
	 */
	@Mapping
	private @ApiModelProperty(hidden = true) Integer printUser;
	/**
	 * 打印时间
	 */
	@Mapping
	private @ApiModelProperty(hidden = true) Long printTime;

}