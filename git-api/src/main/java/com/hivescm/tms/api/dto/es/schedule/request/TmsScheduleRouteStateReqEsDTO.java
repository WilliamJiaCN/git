package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 公司线路状态DTO
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleRouteStateReqEsDTO implements Serializable {
    @Logger
    @Required
    @Mapping
    @ApiModelProperty(value = "公司id", required = true)
    private Long companyId;
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;
    @Required
    @Mapping
    @ApiModelProperty(value = "线路Id", required = true)
    private List<Long> routeIds;
    @Required
    @Mapping
    @ApiModelProperty(value = "线路状态", required = true)
    private Boolean routeState;
}
