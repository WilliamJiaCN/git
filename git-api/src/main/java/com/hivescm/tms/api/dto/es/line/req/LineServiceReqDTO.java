package com.hivescm.tms.api.dto.es.line.req;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class LineServiceReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Logger
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Logger
	@Mapping
	@ApiModelProperty("线路id")
    private Long lineId;
	@Mapping
	@ApiModelProperty("服务id")
    private Long serviceId;
	@Mapping
	@ApiModelProperty("配送时间")
    private String dispatchTime;
	@Mapping
	@ApiModelProperty("提货时间")
    private String deliveryTime;
	@Mapping
	@ApiModelProperty("截单时间")
    private String orderEndTime;
	@ApiModelProperty("班期DAILY_ALLOCATION=每日配=1 ISOLATION_ALLOCATION=隔日配=2 USER_DEFINED=自定义=3")
    private Integer timeType;
	@Mapping
	@ApiModelProperty("开始日期(隔日配必传)")
    private Long beginTime;
	@Mapping
	@ApiModelProperty("自定义日期-以逗号隔开")
    private String customDays;
	@Mapping
	@ApiModelProperty("间隔时间(隔日配必传)")
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
	@ApiModelProperty("下单开始时间(高级服务必传)")
    private String orderTimeBegin;
	@Mapping
	@ApiModelProperty("下单截止时间(高级服务必传)")
    private String orderTimeEnd;
	@Mapping
	@ApiModelProperty("配送时效")
    private BigDecimal effectiveness;
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
	/**
	 * 服务状态,此状态只是在查询时判断是否服务状态都为停用，或者为启用，前端最终展示是以此字段判断，跟新跟新增不用管此字段
	 */
	@ApiModelProperty("服务状态,此状态只是在查询时判断是否服务状态都为停用，或者为启用，前端最终展示是以此字段判断")
	private Integer status;
	@Mapping
	@ApiModelProperty("服务名称")
    private String serviceName;
	@Mapping
	@ApiModelProperty("线路名称")
	private String lineName;
	
	@Mapping
	@ApiModelProperty("线路服务状态枚举名")
    private String lineServiceStatusEn;
	
	@Mapping
	@ApiModelProperty("班期英文名")
    private String timeTypeEn;
}
