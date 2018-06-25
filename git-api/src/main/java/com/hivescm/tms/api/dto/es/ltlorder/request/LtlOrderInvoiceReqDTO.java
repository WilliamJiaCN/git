package com.hivescm.tms.api.dto.es.ltlorder.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.ltlorder.redundancy.LtlOrderGoodsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 零担订单发货方信息
 * @author wenxiong.jia
 * @date 2018/4/14
 */
@Data
@ToString
public class LtlOrderInvoiceReqDTO implements Serializable{
	private static final long serialVersionUID = -5248772512090610394L;

	@Mapping
	@ApiModelProperty("发货方主键")
	private Long invoiceId;
	
	@Mapping
	@ApiModelProperty("发货公司ID")
    private Long invoiceCompanyId;
	
	@Mapping
	@ApiModelProperty("发货公司名称")
    private String invoiceCompanyName;
	
	@Mapping
	@ApiModelProperty("发货人ID")
    private Integer invoiceUserId;
	
	@Required
	@Mapping
	@ApiModelProperty("发货人姓名")
    private String invoiceUserName;
	
	@Mapping
	@ApiModelProperty("发货人电话")
    private String invoiceUserTel;
	
	@Required 
	@Mapping
	@ApiModelProperty("发货人手机号")
    private String invoiceUserMobile;

	@Mapping
	@ApiModelProperty("发货人省ID")
	private String invoiceUserProvinceId;

	@Mapping
	@ApiModelProperty("发货人省名称")
	private String invoiceUserProvinceName;

	@Mapping
	@ApiModelProperty("发货人市ID")
	private String invoiceUserCityId;

	@Mapping
	@ApiModelProperty("发货人市名称")
	private String invoiceUserCityName;

	@Mapping
	@ApiModelProperty("发货人区ID")
	private String invoiceUserDistrictId;

	@Mapping
	@ApiModelProperty("发货人区名称")
	private String invoiceUserDistrictName;

	@Mapping
	@ApiModelProperty("发货人街道ID")
	private String invoiceUserStreetId;

	@Mapping
	@ApiModelProperty("发货人街道名称")
	private String invoiceUserStreetName;

	@Required
	@Mapping
	@ApiModelProperty("发货地址")
    private String invoiceAddress;
}