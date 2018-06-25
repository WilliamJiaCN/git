package com.hivescm.tms.api.dto.es.line.resp;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LineServiceRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键(原有数据传，新增数据不传)")
	private Long serviceCityId;
	@Mapping
    @ApiModelProperty("城市编码")
    private String cityCode;
	@Mapping
    @ApiModelProperty("服务类型STANDARD_SERVICE=标准服务,ADVANCED_SERVICES=高级服务")
    private Integer deliveryType;
	@Mapping
    @ApiModelProperty("服务编码(查数据字典dicCode=tms-cspslx)")
    private Integer serviceCode;
	@Mapping
    @ApiModelProperty("服务编码名称")
    private String cityServiceName;
	@Mapping
    @ApiModelProperty("派送开始时间")
    private String dispatchTimeBeign;
	@Mapping
    @ApiModelProperty("派送截止时间")
    private String dispatchTimeEnd;
	@Mapping
    @ApiModelProperty("提货开始时间")
    private String deliveryTimeBegin;
	@Mapping
    @ApiModelProperty("提货结束时间")
    private String deliveryTimeEnd;
	@Mapping
    @ApiModelProperty("截单开始时间")
    private String orderEndTimeBegin;
	@Mapping
    @ApiModelProperty("截单截止时间")
    private String orderEndTimeEnd;
	@Mapping
    @ApiModelProperty("状态")
    private Integer status;
	@Mapping
    @ApiModelProperty("下单开始时间")
    private String cityOrderTimeBegin;
	@Mapping
    @ApiModelProperty("下单截止时间")
    private String cityOrderTimeEnd;
	@Mapping
    @ApiModelProperty("配送时效")
    private BigDecimal cityEffectiveness;
	@Mapping
    @ApiModelProperty("备注")
    private String cityRemark;
	/*************************线路服务信息**************************/
	@Mapping
	@ApiModelProperty("线路服务主键")
	private Long serviceId;
	@Mapping
	@ApiModelProperty("线路id")
    private Long lineId;
	@Mapping
	@ApiModelProperty("城市服务id")
    private Long cityServiceId;
	@Mapping
	@ApiModelProperty("配送时间")
    private String dispatchTime;
	@Mapping
	@ApiModelProperty("提货时间")
    private String deliveryTime;
	@Mapping
	@ApiModelProperty("截单时间")
    private String orderEndTime;
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
	
	@ApiModelProperty("服务对象")
	private Integer serviceObject;
	@Mapping
	@ApiModelProperty("下单开始时间")
    private String orderTimeBegin;
	@Mapping
	@ApiModelProperty("下单截止时间")
    private String orderTimeEnd;
	@Mapping
	@ApiModelProperty("配送时效")
    private BigDecimal effectiveness;
	@Mapping
    @ApiModelProperty("承运商线路服务状态")
    private Integer carrierStatus;
	
	@Mapping
	@ApiModelProperty("服务名称")
    private String serviceName;
	@Mapping
	@ApiModelProperty("线路名称")
	private String lineName;
	/**
	 * 服务状态,此状态只是在查询时判断是否服务状态都为停用，或者为启用，前端最终展示是以此字段判断，更新跟新增不用管此字段
	 */
	@ApiModelProperty("服务状态,此状态只是在查询时判断是否服务状态都为停用，或者为启用，前端最终展示是以此字段判断")
	private Integer eableStatus;
	@Mapping
    @ApiModelProperty("是否选中(true = 选中，false = 选中)")
    private Boolean option;
	
	@Mapping
	@ApiModelProperty("线路服务状态枚举名")
    private String lineServiceStatusEn;
	
	@Mapping
	@ApiModelProperty("班期英文名")
    private String timeTypeEn;
}
