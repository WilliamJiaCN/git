package com.hivescm.tms.api.dto.es.line.resp;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class CarrierLineServiceRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("是否选中(在承运商线路处使用)")
	private Boolean option;
	
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("线路id")
    private Long lineId;
	@Mapping
	@ApiModelProperty("服务id")
    private Long serviceId;
	@Mapping
	@ApiModelProperty("配送时间")
    private Integer dispatchTime;
	@Mapping
	@ApiModelProperty("提货时间")
    private Integer deliveryTime;
	@Mapping
	@ApiModelProperty("截单时间")
    private Integer orderEndTime;
	@Mapping
	@ApiModelProperty("班期")
    private Integer timeType;
	@Mapping
	@ApiModelProperty("开始日期")
    private Long beginTime;
	@Mapping
	@ApiModelProperty("自定义日期")
    private String customDays;
	@Mapping
	@ApiModelProperty("间隔时间")
    private Integer intervalTime;
	@Mapping
	@ApiModelProperty("备注")
    private String remark;
	@Mapping
	@ApiModelProperty("线路服务状态")
    private Integer lineServiceStatus;
	@Mapping
	@ApiModelProperty("服务状态")
	private Integer serviceStatus;
	@Mapping
	@ApiModelProperty("下单开始时间")
    private Integer orderTimeBegin;
	@Mapping
	@ApiModelProperty("下单截止时间")
    private Integer orderTimeEnd;
	@Mapping
	@ApiModelProperty("配送时效")
    private BigDecimal effectiveness;
	/**
	 * 服务状态,此状态只是在查询时判断是否服务状态都为停用，或者为启用，前端最终展示是以此字段判断，跟新跟新增不用管此字段
	 */
	@ApiModelProperty("服务状态,此状态只是在查询时判断是否服务状态都为停用，或者为启用，前端最终展示是以此字段判断")
	private Integer status;
}
