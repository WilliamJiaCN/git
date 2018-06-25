package com.hivescm.tms.api.dto.es.line;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LineServiceEsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 根据班期进行分组
	 * @return
	 */
	public Integer groupByTimeType() {
		return this.timeType;
	}
	@Logger
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Logger
	@Mapping
	@ApiModelProperty("线路id")
    private Long lineId;
	@Logger
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
	@Mapping
	@ApiModelProperty("班期")
    private Integer timeType;
	@Mapping
	@ApiModelProperty("班期英文名")
    private String timeTypeEn;
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
	@ApiModelProperty("线路服务状态枚举名")
    private String lineServiceStatusEn;
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
	@Mapping
    @ApiModelProperty("承运商线路服务状态")
    private Integer carrierStatus;
	
	@Mapping
	@ApiModelProperty("服务名称")
    private String serviceName;
	@Mapping
	@ApiModelProperty("线路名称")
	private String lineName;
	@ApiModelProperty("创建人名称")
    private String createUserName;
	@Mapping
	@ApiModelProperty("修改人名称")
    private String updateUserName;
}
