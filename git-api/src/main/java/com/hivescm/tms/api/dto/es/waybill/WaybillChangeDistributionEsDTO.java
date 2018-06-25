package com.hivescm.tms.api.dto.es.waybill;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillChangeDistributionEsDTO {
	
	@Mapping
	@ApiModelProperty("主键id")
    private Long id;

	@Mapping
	@ApiModelProperty("运单号")
    private String waybillCode;

	@Mapping
	@ApiModelProperty("订单号")
    private String orderCode;

	@Mapping
	@ApiModelProperty("改配单号")
    private String changeDistributionCode;

	@Mapping
	@ApiModelProperty("是否反仓(1是、2否)")
    private Integer returnWarehouse;

	@Mapping
	@ApiModelProperty("改配内容")
    private Integer changeDispatcherContent;

	@Mapping
	@ApiModelProperty("改配内容名称")
    private String changeDispatcherContentName;

	@Mapping
	@ApiModelProperty("改配日期")
    private String changeDispatcherDate;

	@Mapping
	@ApiModelProperty("改配开始时间")
    private String startTime;

	@Mapping
	@ApiModelProperty("改配结束时间")
    private String endTime;

	@Mapping
	@ApiModelProperty("配送类型")
    private Integer distributionType;

	@Mapping
	@ApiModelProperty("配送类型名称")
    private String distributionTypeName;

	@Mapping
	@ApiModelProperty("改配原因")
    private Integer changeDispatcherReason;

	@Mapping
	@ApiModelProperty("改配原因说明")
    private String changeDispatcherReasonDetail;

	@Mapping
	@ApiModelProperty("责任方")
    private Integer responsibleObject;

	@Mapping
	@ApiModelProperty("责任方名称")
    private String responsibleObjectName;

	@Mapping
	@ApiModelProperty("审核状态")
    private Integer status;

	@Mapping
	@ApiModelProperty("审核状态名称")
    private String statusName;

	@Mapping
	@ApiModelProperty("审核时间")
    private Long auditTime;
	
	@Mapping
	@ApiModelProperty("司机ID")
    private Integer driverId;

	@Mapping
    private Integer createUser;

	@Mapping
    private Long createTime;

	@Mapping
    private Integer updateUser;

	@Mapping
    private Long updateTime;

}