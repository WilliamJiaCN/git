package com.hivescm.tms.api.dto.es.exception.response;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillInfoRespDTO {

	@Mapping({ "waybillId", "id" })
	@ApiModelProperty("主键ID")
	private Long id;
	
	@Mapping
	@ApiModelProperty("运单号")
	private String code;

	@Mapping
	@ApiModelProperty("发货人id")
	private Long invoiceUserId;

	@Mapping
	@ApiModelProperty("发货人")
	private String invoiceUser;

	@Mapping
	@ApiModelProperty("收货人Id")
	private Long receiptUserId;

	@Mapping
	@ApiModelProperty("收货人")
	private String receiptUser;

	@Mapping
	@ApiModelProperty("录单时间")
	private Long createTime;

	@Mapping
	@ApiModelProperty("发货网点ID")
	private Integer invoiceOrgId;

	@Mapping
	@ApiModelProperty("发货网点名称")
	private String invoiceOrgName;

	@Mapping
	@ApiModelProperty("目的网点Id 运单发货网点名称")
	private Integer destOrgId;

	@Mapping
	@ApiModelProperty("目的网点名称")
	private String destOrgName;

	@Mapping
	@ApiModelProperty("目的地id  运单目的网点名称")
	private String destId;

	@Mapping
	@ApiModelProperty("目的地名称")
	private String destName;

	@Mapping
	@ApiModelProperty("运单状态")
	private Integer status;
	
	@Mapping
	@ApiModelProperty("运单状态名称") 
	private String statusName;

	@Mapping
	@ApiModelProperty("货物类型名称   冗余商品表类型，以‘/’间隔")
	private String goodsTypeName;

	@Mapping
	@ApiModelProperty("总件数")
	private Integer totalNum;

	@Mapping
	@ApiModelProperty("总重量")
	private BigDecimal weight;

	@Mapping
	@ApiModelProperty("总体积")
	private BigDecimal volume;
}
