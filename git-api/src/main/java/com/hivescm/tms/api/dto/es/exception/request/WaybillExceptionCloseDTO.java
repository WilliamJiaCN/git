package com.hivescm.tms.api.dto.es.exception.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 异常办结
 */
@Data
@ToString
public class WaybillExceptionCloseDTO implements Serializable{

    private static final long serialVersionUID = -2300935544720045077L;
    /**
     * 序号
     */
    @Mapping
    @ApiModelProperty(value = "序号")
    private Integer closeSort;

    /**
     * 异常责任主体
     */
    @Mapping
    @ApiModelProperty(value = "对象 枚举 1.网点 2.车辆 3.承运商 4.装卸队")
    private Integer exceptionObject;

    /**
     * 异常责任主体名称
     */
    @Mapping
    @ApiModelProperty(value = "对象名称 枚举 1.网点 2.车辆 3.承运商 4.装卸队")
    private String exceptionObjectName;

    /**
     * 收款网点ID
     */
    @Mapping
    @ApiModelProperty("收款网点ID")
    private Integer receiveBranchId;

    /**
     * 收款网点名称
     */
    @Mapping
    @ApiModelProperty("收款网点名称")
    private String receiveBranchName;

    /**
     * 来源单号
     */
    @Mapping
    @ApiModelProperty(value = "来源单号")
    private String sourceCode;

    /**
     * 来源单ID
     */
    @Mapping
    @ApiModelProperty(value = "来源单号ID")
    private Long sourceId;

    /**
     * 车牌号码
     */
    @Mapping
    @ApiModelProperty(value = "车牌号码")
    private String vehicleNo;

    /**
     * 司机姓名ID
     */
    @Mapping
    @ApiModelProperty(value = "司机ID")
    private Integer driverId;

    /**
     * 司机姓名
     */
    @Mapping
    @ApiModelProperty(value = "司机姓名")
    private String driverName;

    /**
     * 承运商ID
     */
    @Mapping
    @ApiModelProperty(value = "承运商ID")
    private Integer carrierId;

    /**
     * 承运商名称
     */
    @Mapping
    @ApiModelProperty(value = "承运商名称")
    private String carrierName;

    /**
     * 装卸类型
     */
    @Mapping
    @ApiModelProperty(value = "装卸类型 ")
    private Integer handlingType;

    /**
     * 装卸类型名称
     */
    @Mapping
    @ApiModelProperty(value = "装卸类型名称")
    private String handlingTypeName;

    /**
     * 装卸队ID
     */
    @Mapping
    @ApiModelProperty(value = "装卸队ID")
    private Long handlingTeamId;

    /**
     * 装卸队名称
     */
    @Mapping
    @ApiModelProperty(value = "装卸队名称")
    private String handlingTeamName;

    /**
     * 罚款金额
     */
    @Mapping
    @ApiModelProperty(value = "罚款金额")
    private BigDecimal forfeitAmount;
}
