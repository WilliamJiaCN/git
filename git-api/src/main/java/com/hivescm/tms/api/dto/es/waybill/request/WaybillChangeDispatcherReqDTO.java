package com.hivescm.tms.api.dto.es.waybill.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 承运商首页统计请求体 DTO
 * @company 蜂网供应链管理（上海）有限公司
 */
@ToString
@Data
public class WaybillChangeDispatcherReqDTO implements Serializable{
	
	private static final long serialVersionUID = -8623365026731916885L;
	
	@Mapping
	@ApiModelProperty("派车单明细ID")
	private Long dispatcherDetailId;
	
	@Mapping
	@ApiModelProperty("运单号")
	private String waybillCode;

	@Mapping
	@ApiModelProperty("订单号")
	private String orderCode;

	@Mapping
	@ApiModelProperty("是否退仓")
	private Integer returnWarehouse;
	
	@Mapping
	@ApiModelProperty("改配内容")
	private Integer changeDispatcherContent;

	@Mapping
	@ApiModelProperty("改配日期")
	private String changeDispatcherDate;

	@Mapping
	@ApiModelProperty("开始时间")
	private String startTime;

	@Mapping
	@ApiModelProperty("结束时间")
	private String endTime;

	@Mapping
	@ApiModelProperty("配送方式")
	private Integer distributionType ;

	@Mapping
	@ApiModelProperty("改配原因")
	private Integer changeDispatcherReason;
	
	@Mapping
	@ApiModelProperty("责任方")
    private Integer responsibleObject;
	
	@Mapping
	@ApiModelProperty("经销商业务单元ID")
    private Long dealerId;
	
	@Mapping
	@ApiModelProperty("网点ID")
    private Long branchId;
	
	@Mapping
	@ApiModelProperty("司机ID")
    private Integer driverId;
	
	@Mapping
	@ApiModelProperty("司机姓名")
    private String driverName;
	
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	
	
}
