package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 省市县镇验证请求类
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/9/7 13:56
 */
@Data
@ToString
public class TmsScheduleValidRegionReqEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Logger
    @ApiModelProperty("公司id")
    private Long companyId;
    /**
     * 用于给司机新增区域时校验（整区覆盖）是否已存在
     */
    @Logger
    @Mapping
    @ApiModelProperty("司机Id")
    private Integer driverId;

    @ApiModelProperty("省Id")
    private String provinceId;

    @ApiModelProperty("市Id")
    private String cityId;

    @ApiModelProperty("县Id")
    private String countyId;

    @ApiModelProperty("镇Id")
    private String townId;

    @ApiModelProperty("覆盖Id")
    private Long coverTypeId;

}
