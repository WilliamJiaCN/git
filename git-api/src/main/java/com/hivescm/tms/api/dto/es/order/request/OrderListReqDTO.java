package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.capacity.order.OrderSearchTimerTypeEnum;
import com.hivescm.tms.api.enums.capacity.order.OrderStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 订单列表请求DTO
 * @author ke.huang
 * @date 2017年10月30日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderListReqDTO implements Serializable{
	private static final long serialVersionUID = -7275323801087270059L;
	@ApiModelProperty("时间类型")
	private OrderSearchTimerTypeEnum orderSearchTimerTypeEnum;
	@ApiModelProperty("开始时间")
	private Long startTime;
	@ApiModelProperty("结束时间")
	private Long endTime;
	@Logger
	@ApiModelProperty("订单编号")
	private String orderCode;
	@ApiModelProperty("订单状态")
	private OrderStatusEnum orderStatusEnum;
	@Logger
	@ApiModelProperty("运输线路")
	private String lineName;
	@Logger
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@ApiModelProperty(value="城市ID(权限)，订单列表必传")
	private String cityCode;
	@ApiModelProperty(value="承运商ID 承运商接单列表必传",hidden=true)
	private Long carrierId;
	@ApiModelProperty(value="承运商网点ID(权限)，承运商接单列表必传",hidden=true)
	private Long branchId;
	
	@Required
	@ApiModelProperty("当前页数")
	private Integer currentPage;
	@Required
	@ApiModelProperty("每页显示数")
	private Integer pageSize;
}
