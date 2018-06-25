package com.hivescm.tms.api.dto.es.ltlorder.resp;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class OrderBriefDto {
	//1-发、2-收
	@ApiModelProperty("收发标识:1-发、2-收")
	private Integer flag;
	@ApiModelProperty("货主ID")
	private Integer createUser;
	@ApiModelProperty("logo")
	private String logoUrl;
	@ApiModelProperty("物流ID")
	private Integer companyId;
	@ApiModelProperty("物流公司简称")
	private String companyName;
	@ApiModelProperty("订单Id")
	private Long orderId;
	@ApiModelProperty("订单号")
	private String orderCode;
	@ApiModelProperty("运单Id")
	private Long waybillId;
	@ApiModelProperty("运单号")
	private String code;
	@ApiModelProperty("运输线路id")
	private  Long lineId;
	@ApiModelProperty("运输线路名称	")
	private  String lineName;
}
