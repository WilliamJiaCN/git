package com.hivescm.tms.api.dto.es.exception;

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
public class WaybillExceptionCloseEsDTO implements Serializable{
    private static final long serialVersionUID = 2075747840182171616L;

    @Mapping
    @ApiModelProperty("主键")
    private Long id;
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;
    @Mapping
    @ApiModelProperty("异常ID")
    private Long exceptionId;
    @Mapping
    @ApiModelProperty("序号")
    private Integer closeSort;
    @Mapping
    @ApiModelProperty("对象 枚举 1.网点 2.车辆 3.承运商 4.装卸队")
    private Integer exceptionObject;
    @Mapping
    @ApiModelProperty("对象名称 枚举 1.网点 2.车辆 3.承运商 4.装卸队")
    private String exceptionObjectName;
    @Mapping
    @ApiModelProperty("来源单号")
    private String sourceCode;
    @Mapping
    @ApiModelProperty("来源单号ID")
    private Long sourceId;
    @Mapping
    @ApiModelProperty("车牌号码")
    private String vehicleNo;
    @Mapping
    @ApiModelProperty("司机姓名ID")
    private Integer driverId;
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    @Mapping
    @ApiModelProperty("承运商ID")
    private Integer carrierId;
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carrierName;
    @Mapping
    @ApiModelProperty("装卸类型 ")
    private Integer handlingType;
    @Mapping
    @ApiModelProperty("装卸类型名称")
    private String handlingTypeName;
    @Mapping
    @ApiModelProperty("装卸队ID")
    private Integer handlingTeamId;
    @Mapping
    @ApiModelProperty("装卸队名称")
    private String handlingTeamName;
    @Mapping
    @ApiModelProperty("罚款金额")
    private BigDecimal forfeitAmount;
    @Mapping
    @ApiModelProperty("收款网点ID")
    private Integer receiveBranchId;
    @Mapping
    @ApiModelProperty("收款网点名称")
    private String receiveBranchName;
    @Mapping
    @ApiModelProperty("创建人ID")
    private Integer createUserId;
    @Mapping
    @ApiModelProperty("创建人名称")
    private String createUserName;
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;
    @Mapping
    @ApiModelProperty("修改人ID")
    private Integer updateUserId;
    @Mapping
    @ApiModelProperty("修改人名称")
    private String updateUserName;
    @Mapping
    @ApiModelProperty("修改人时间")
    private Long updateTime;
}
