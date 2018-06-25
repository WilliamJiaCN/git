package com.hivescm.tms.api.dto.es.delivery.req;

import java.io.Serializable;

import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 订单列表请求DTO
 * @author ZHANGWENLONG
 * @date 2018年01月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderDeliveryListReqDTO implements Serializable{
	private static final long serialVersionUID = -7275323801087270059L;
	@ApiModelProperty("时间类型:1 接单,2 签收")
	private Integer timeType;
	@ApiModelProperty("开始时间")
	private Long startTime;
	@ApiModelProperty("结束时间")
	private Long endTime;
	@ApiModelProperty("订单编号")
	private String orderCode;
	@ApiModelProperty("快递单号")
	private String deliveryCode;
	@ApiModelProperty("订单状态")
	private Integer status;
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@ApiModelProperty(value="城市ID(权限)，订单列表必传")
	private String cityCode;
	@ApiModelProperty(value="承运商ID 承运商接单列表必传")
	private Long carrierId;
	
	@Required
	@ApiModelProperty("当前页数")
	private Integer currentPage;
	@Required
	@ApiModelProperty("每页显示数")
	private Integer pageSize;
}
