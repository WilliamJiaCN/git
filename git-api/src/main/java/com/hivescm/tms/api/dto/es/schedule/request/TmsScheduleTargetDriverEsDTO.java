package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 目标司机类
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsScheduleTargetDriverEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //必填
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Mapping
    @ApiModelProperty("司机Id")
    private Integer driverId;
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    @Mapping
    @ApiModelProperty("手机号")
    private String phoneNo;
    @Mapping
    @ApiModelProperty("所属承运商")
    private Integer belongCarry;
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carryName;
}
