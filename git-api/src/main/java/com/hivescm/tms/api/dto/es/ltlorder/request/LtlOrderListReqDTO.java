package com.hivescm.tms.api.dto.es.ltlorder.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.capacity.ltlorder.LtlOrderDeliveryTypeEnum;
import com.hivescm.tms.api.enums.capacity.ltlorder.LtlOrderSearchTimerTypeEnum;
import com.hivescm.tms.api.enums.capacity.ltlorder.LtlOrderStatusEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 订单列表请求DTO
 * @author wenxiong.jia
 * @date 2018/4/8
 */
@Data
@ToString
public class LtlOrderListReqDTO implements Serializable{
	private static final long serialVersionUID = -7139879401551837103L;
	@ApiModelProperty("时间类型")
	private LtlOrderSearchTimerTypeEnum ltlOrderSearchTimerTypeEnum;
	@ApiModelProperty("开始时间")
	private Long startTime;
	@ApiModelProperty("结束时间")
	private Long endTime;
	@ApiModelProperty("订单状态")
	private LtlOrderStatusEnum ltlOrderStatusEnum;
	@ApiModelProperty("发货人")
	private String invoiceUserName;
	@ApiModelProperty("订单编号")
	private List<String> orderCode;
	@ApiModelProperty("发货方式")
	private LtlOrderDeliveryTypeEnum ltlOrderDeliveryTypeEnum;
	@ApiModelProperty("目的地")
	private String endCityName;
	@ApiModelProperty("发货人手机号")
	private String invoiceUserMobile;
	
	@Required
	@ApiModelProperty("当前页数")
	private Integer currentPage;
	@Required
	@ApiModelProperty("每页显示数")
	private Integer pageSize;
}
