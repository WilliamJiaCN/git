package com.hivescm.tms.api.dto.es.alteration;

import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.bill.alteration.AlterationProcessStatusEnum;
import com.hivescm.tms.api.enums.bill.alteration.ReceiveTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 自提改送货信息Es类
 * 原收货人ID
 * @author zhenming.du
 * @date 2017年10月9日
 * @company 蜂网供应链
 */
@Data
@ToString
public class AlterationDeliveryEsDTO implements Serializable,Cloneable {


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
	 * 运单id
	 */
	@Mapping
	private @ApiModelProperty("运单id") Long waybillId;
	/**
	 * 运单号
	 */
	private @ApiModelProperty("运单号") String waybillCode;
	
	/*********************更改内容*****************************************************/
	
	/**
	 * 当付款方为发货公司时，如果发货公司ID不为空，payWay = 发货公司ID，如果发货公司ID为空，生成发货公司
	 * 当付款方为发货人时，如果发货人ID不为空，payWay = 发货人ID，如果发货公司ID为空，生成发货人
	 * 当付款方为收货公司时，如果收货公司ID不为空，payWay = 收货公司ID，如果收货公司ID为空，生成收货公司
	 * 当付款方为收货人时，如果收货人ID不为空，payWay =收货人ID，如果收货人ID为空，生成收货人
	 */
	private @ApiModelProperty("付款方类型") ReceiveTypeEnum receiveType;
	
	@Mapping
	private @ApiModelProperty(value="GROUPID",hidden=true) Integer groupId;
	
	/**
	 * 处理状态
	 */
	private @ApiModelProperty("处理状态") AlterationProcessStatusEnum processingStatus;
	/**
	 * 配送方式
	 */
	@Mapping
	private @ApiModelProperty("配送方式") Integer distributionType;
	
	/**
	 * 配送方式名称
	 */
	@Mapping
	private @ApiModelProperty("配送方式名称") String distributionTypeName;
	 
	/**
	 * (新)收货人姓名
	 */
	@Mapping
	private @ApiModelProperty("(新)收货人姓名") String newReceiptUser;
	/**
	 * (新)收货公司
	 */
//	@Mapping
//	private @ApiModelProperty("(新)收货公司") String newReceiptCompany;
	/**
	 * (新)收货人ID
	 */
	@Mapping
	private @ApiModelProperty("(新)收货人ID") Integer newReceiptUserId;
	/**
	 * (新)收货人电话
	 */
	@Mapping
	private @ApiModelProperty("(新)收货人电话") String newReceiptConsignorTelNo;
	/**
	 *  (新)收货人手机
	 */
	@Mapping
	private @ApiModelProperty("(新)收货人手机") String newReceiptConsignorMobleNo;
	/**
	 * (新)收货地址
	 */
	@Mapping
	private @ApiModelProperty("(新)收货地址") String newReceiptAddress;
	/**
	 * 备注
	 */
	@Mapping
	private @ApiModelProperty("备注") String alterationRemark;
	/**
	 * 预约送货时间
	 */
	@Mapping
	private @ApiModelProperty("预约送货时间") Long neweDeliverySendTime;
	/**
	 * 收货人省ID
	 */
	@Mapping
	private @ApiModelProperty("收货人省ID") String receiptProvId;
	/**
	 * 收货人省名称
	 */
	@Mapping
	private @ApiModelProperty("收货人省名称") String receiptProvName;
	/**
	 * 收货人城市ID
	 */
	@Mapping
	private @ApiModelProperty("收货人城市ID") String receiptCityId;
	/**
	 * 收货人城市名称
	 */
	@Mapping
	private @ApiModelProperty("收货人城市名称") String receiptCityName;
	/**
	 * 收货人地区ID
	 */
	@Mapping
	private @ApiModelProperty("收货人地区ID") String receiptDistrictId;
	/**
	 * 收货人地区名称
	 */
	@Mapping
	private @ApiModelProperty("收货人地区名称") String receiptDistrictName;
	/**
	 * 收货人街道ID
	 */
	@Mapping
	private @ApiModelProperty("收货人街道ID") String receiptStreetId;
	/**
	 * 收货人街道名称
	 */
	@Mapping
	private @ApiModelProperty("收货人街道名称") String receiptStreetName;
	/**
	 * 到付送货费
	 */
	@Mapping
    private @ApiModelProperty("到付送货费") BigDecimal toPayDeliveryFee;
    
	 /**
	 * 付款方
	 */
	@Mapping
	private @ApiModelProperty("付款方名称") String payUserName;
	/**
	 * 付款方ID
	 */
	@Mapping
	private @ApiModelProperty("付款方ID") Integer payUser;
	/**
	 * 收款方
	 */
	@Mapping
	private @ApiModelProperty("收款方") String receiveWayName;
	/**
	 * 收款方ID
	 */
	@Mapping
	private @ApiModelProperty("收款方ID") Integer receiveWay;
	/**
	 * 申请人ID
	 */
	@Mapping
	private @ApiModelProperty("申请人") Integer applicantId;
	/**
	 * 申请人
	 */
	@Mapping
	private @ApiModelProperty("申请人姓名") String applicantName;
	/**
	 * 申请时间
	 */
	@Mapping
	private @ApiModelProperty("申请时间") Long applicantTime;
	/**
	 * 申请网点ID
	 */
	@Mapping
	private @ApiModelProperty("申请网点ID") Integer applicantNetwork;
	/**
	 * 申请网点
	 */
	@Mapping
	private @ApiModelProperty("申请网点") String applicantNetworkName;
	/**
	 * 修改人ID
	 */
	@Mapping
	private @ApiModelProperty("修改人ID") Integer updateUser;
	/**
	 * 修改人
	 */
	@Mapping
	private @ApiModelProperty("修改人姓名") String updateUserName;
	/**
	 * 审核状态
	 */
	@Mapping
	private @ApiModelProperty("审核状态") Integer checked;
	/**
	 * 修改时间
	 */
	@Mapping
	private @ApiModelProperty("修改时间") Long updateTime;
	/**
	 * 确认人ID
	 */
	@Mapping
	private @ApiModelProperty("确认人") Integer confirmUser;
	/**
	 * 确认人
	 */
	@Mapping
	private @ApiModelProperty("确认人") String confirmUserName;
	/**
	 * 确认时间
	 */
	@Mapping
	private @ApiModelProperty("确认时间") Long confirmTime;
	
	/**
	 * 创建人Id
	 */
	@Mapping
	private @ApiModelProperty("创建人Id") Integer createUser;
	/**
	 * 创建人姓名 
	 */
	@Mapping
	private @ApiModelProperty("创建人姓名") String createUserName;
	/**
	 * 创建时间
	 */
	@Mapping
	private @ApiModelProperty("创建时间") Long createTime;
	/**
	 * 是否删除
	 */
	@Mapping("isDelete")
	private @ApiModelProperty("是否删除") Boolean idelete;
	
	/*********************冗余运单*****************************************************/
	/**
	 * 送货费
	 */
	@Mapping
    private @ApiModelProperty("送货费") BigDecimal deliveryFee;
	/**
	 * 付款式方
	 */
	@Mapping
	private @ApiModelProperty("付款方式名称") String payWayName;
	/**
	 * 付款方式ID
	 */
	@Mapping
	private @ApiModelProperty("付款方式ID") Integer payWay;
	/**
	 * （原）收货人
	 */
	@Mapping
	private @ApiModelProperty("原收货人") String receiptUser;
	/**
	 * （原）收货人ID
	 */
	@Mapping
	private @ApiModelProperty("原收货人ID") Long receiptUserId;
	/**
	 *（原） 收货人电话
	 */
	@Mapping
	private @ApiModelProperty("原收货人电话") String receiptConsignorTelNo;
	/**
	 *（原） 收货人手机
	 */
	@Mapping
	private @ApiModelProperty("原收货人手机") String receiptConsignorMobleNo;
	/**
	 * (原)收货公司
	 */
	@Mapping
	private @ApiModelProperty("原收货公司") String receiptCompany;
	/**
	 * （原）收货公司ID
	 */
	@Mapping
	private @ApiModelProperty("收货方ID") Integer receiptCustomerId;
	/**
	 * (原)收货地址
	 */
	@Mapping
	private @ApiModelProperty("原收货地址") String receiptAddress;
	/**
	 * 发货网点ID
	 */
	@Mapping
	private @ApiModelProperty("发货网点ID	") Integer invoiceOrgId;
	/**
	 * 发货网点名称
	 */
	@Mapping
	private @ApiModelProperty("发货网点名称") String invoiceOrgName;
	/**
	 * 运输线路名称
	 */
	@Mapping
	private @ApiModelProperty("运输线路名称	") String lineName;
	/**
	 * 到达网点id
	 */
	@Mapping
	private @ApiModelProperty("到达网点id") Integer destOrgId;
	/**
	 * 到达网点名称
	 */
	@Mapping
	private @ApiModelProperty("到达网点名称") String destOrgName;
	/**
	 * 发货公司
	 */
	@Mapping
	private @ApiModelProperty("发货公司") String invoiceCompany;
	/**
	 * 发货公司ID
	 */
	@Mapping
	private @ApiModelProperty("发货公司ID")Integer invoiceCustomerId;
	/**
	 * 发货人
	 */
	@Mapping
	private @ApiModelProperty("发货人") String invoiceUser;
	/**
	 * 发货人电话
	 */
	@Mapping
	private @ApiModelProperty("发货人电话") String invoiceTelNo;
	/**
	 * 发货人手机号码
	 */
	@Mapping
	private @ApiModelProperty("发货人手机号码") String invoiceMobleNo;
	/**
	 * 发货地址
	 */
	@Mapping
	private @ApiModelProperty("发货人详细地址") String invoiceAddress;
	/**
	 * 商品名称 ,“/”间隔
	 */
	@Mapping
	private @ApiModelProperty("商品名称") String goodsName;
	/**
	 * 包装名称,“/”间隔
	 */
	private @Mapping @ApiModelProperty("包装名称") String packingName;
	/**
	 * 总体积
	 */
	@Mapping
	private @ApiModelProperty("总体积") BigDecimal volume;
	/**
	 * 总重量
	 */
	@Mapping
	private @ApiModelProperty("总重量") BigDecimal weight;
	/**
	 * 总件数
	 */
	@Mapping
	private @ApiModelProperty("总件数") Integer totalNum;
	/**
	 * 运单备注
	 */
	@Mapping
	private @ApiModelProperty("运单备注") String remark;
	/**
	 * 录单员姓名
	 */
	@Mapping
	private @ApiModelProperty("录单员姓名") String userName;
	/**
	 * 录单时间
	 */
	@Mapping
	private @ApiModelProperty("录单时间") Long operTime;

	@Mapping
	private @ApiModelProperty("到付") BigDecimal cod;

	@Mapping
	private @ApiModelProperty("代收货款") BigDecimal orderGoodsPayment;

	@Override
	public AlterationDeliveryEsDTO clone() throws CloneNotSupportedException {
		try {
			AlterationDeliveryEsDTO alteration = (AlterationDeliveryEsDTO)super.clone();
			alteration.setProcessingStatus(this.getProcessingStatus());
			return alteration;
		} catch (CloneNotSupportedException e) {
			throw ExceptionFactory.ex(e);
		}
	}
	
	
}