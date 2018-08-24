package com.hivescm.tms.api.dto.es.ltlorder.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 零担订单主表
 * @author wenxiong.jia
 * @date 2018/4/9
 */
@Data
@ToString
public class LtlOrderReceiptReqDTO implements Serializable{
	private static final long serialVersionUID = -5053002186813007989L;

	@Mapping
	@ApiModelProperty("收货方主键")
	private Long receiptId;
	
	@Mapping
	@ApiModelProperty("收货公司ID")
    private Long receiptCompanyId;
	
	@Mapping
	@ApiModelProperty("收货公司名称")
    private String receiptCompanyName;
	
	@Mapping
	@ApiModelProperty("收货人ID")
    private Integer receiptUserId;
	
	@Mapping
	@ApiModelProperty("收货人姓名")
    private String receiptUserName;
	
	@Mapping
	@ApiModelProperty("收货人电话")
    private String receiptUserTel;
	
	@Mapping
	@ApiModelProperty("收货人手机号")
    private String receiptUserMobile;

	@Mapping
	@ApiModelProperty("收货人省ID")
	private String receiptUserProvinceId;

	@Mapping
	@ApiModelProperty("收货人省名称")
	private String receiptUserProvinceName;

	@Mapping
	@ApiModelProperty("收货人市ID")
	private String receiptUserCityId;

	@Mapping
	@ApiModelProperty("收货人市名称")
	private String receiptUserCityName;

	@Mapping
	@ApiModelProperty("收货人区ID")
	private String receiptUserDistrictId;

	@Mapping
	@ApiModelProperty("收货人区名称")
	private String receiptUserDistrictName;

	@Mapping
	@ApiModelProperty("收货人街道ID")
	private String receiptUserStreetId;

	@Mapping
	@ApiModelProperty("收货人街道名称")
	private String receiptUserStreetName;

	@Mapping
	@ApiModelProperty("收货地址")
    private String receiptAddress;
}