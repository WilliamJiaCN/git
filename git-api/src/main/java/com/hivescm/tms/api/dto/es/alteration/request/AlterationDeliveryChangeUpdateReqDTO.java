package com.hivescm.tms.api.dto.es.alteration.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.alteration.ReceiveTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 自提改配送变更修改请求DTO
 * @author ke.huang
 * @date 2018年5月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class AlterationDeliveryChangeUpdateReqDTO implements Serializable{
	private static final long serialVersionUID = 5284304160281191431L;
	@Mapping @Required
	private @ApiModelProperty(value="主键",required=true) Long id;
	@Mapping @Required
	private @ApiModelProperty(value="公司id",hidden=true,required=true) Long companyId;
	@Mapping @Required
	private @ApiModelProperty(value="公司名称",hidden=true) String companyName;
	@Mapping @Required
	private @ApiModelProperty(value="GROUPID",hidden=true) Integer groupId;
	@Mapping @Required
	private @ApiModelProperty(value="运单id",required=true) Long waybillId;
	@Mapping @Required
	private @ApiModelProperty(value="运单号",required=true) String waybillCode;
	@Mapping @Required
	private @ApiModelProperty(value="申请人ID",hidden=true,required=true) Integer applicantId;
	@Mapping @Required
	private @ApiModelProperty(value="申请人姓名",hidden=true,required=true) String applicantName;
	@Mapping @Required
	private @ApiModelProperty(value="申请时间",hidden=true,required=true) Long applicantTime;
	@Mapping @Required
	private @ApiModelProperty(value="申请网点ID",hidden=true,required=true) Integer applicantNetwork;
	@Mapping @Required
	private @ApiModelProperty(value="申请网点名称",hidden=true,required=true) String applicantNetworkName;
	@Mapping @Required
	private @ApiModelProperty(value="修改人Id",hidden=true,required=true) Integer updateUser;
	@Mapping @Required
	private @ApiModelProperty(value="修改人姓名",hidden=true,required=true) String updateUserName;
	@Mapping @Required
	private @ApiModelProperty(value="修改时间",hidden=true,required=true) Long updateTime;
	@Mapping @Required
	private @ApiModelProperty(value="预约送货时间",required=true) Long neweDeliverySendTime;
	@Mapping
	private @ApiModelProperty("(新)收货人姓名") String newReceiptUser;
//	@Mapping
//	private @ApiModelProperty("(新)收货公司") String newReceiptCompany;
	@Mapping
	private @ApiModelProperty("(新)收货人ID") Integer newReceiptUserId;
	@Mapping
	private @ApiModelProperty("(新)收货人电话") String newReceiptConsignorTelNo;
	@Mapping
	private @ApiModelProperty("(新)收货人手机") String newReceiptConsignorMobleNo;
	@Mapping
	private @ApiModelProperty("(新)收货地址") String newReceiptAddress;
	@Mapping
	private @ApiModelProperty("收货人省ID") String receiptProvId;
	@Mapping
	private @ApiModelProperty("收货人省名称") String receiptProvName;
	@Mapping
	private @ApiModelProperty("收货人城市ID") String receiptCityId;
	@Mapping
	private @ApiModelProperty("收货人城市名称") String receiptCityName;
	@Mapping
	private @ApiModelProperty("收货人地区ID") String receiptDistrictId;
	@Mapping
	private @ApiModelProperty("收货人地区名称") String receiptDistrictName;
	@Mapping
	private @ApiModelProperty("收货人街道ID") String receiptStreetId;
	@Mapping
	private @ApiModelProperty("收货人街道名称") String receiptStreetName;
	@Mapping
    private @ApiModelProperty("到付送货费") BigDecimal toPayDeliveryFee;
	@Mapping
	private @ApiModelProperty("付款方名称") String payUserName;
//	@Mapping
//	private @ApiModelProperty("付款方ID") Integer payWay;
	@Mapping
	private @ApiModelProperty("收款方名称") String receiveWayName;
	@Mapping
	private @ApiModelProperty("收款方") Integer receiveWay;
	@Mapping
	private @ApiModelProperty("备注") String alterationRemark;
	/**
	 * 当付款方为发货公司时，如果发货公司ID不为空，payWay = 发货公司ID，如果发货公司ID为空，生成发货公司
	 * 当付款方为发货人时，如果发货人ID不为空，payWay = 发货人ID，如果发货公司ID为空，生成发货人
	 * 当付款方为收货公司时，如果收货公司ID不为空，payWay = 收货公司ID，如果收货公司ID为空，生成收货公司
	 * 当付款方为收货人时，如果收货人ID不为空，payWay =收货人ID，如果收货人ID为空，生成收货人
	 */
	private @ApiModelProperty("付款方类型") ReceiveTypeEnum receiveType;


}
