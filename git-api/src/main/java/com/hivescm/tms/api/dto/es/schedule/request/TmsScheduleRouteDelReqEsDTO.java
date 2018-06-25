package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleRouteEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 删除公司线路请求封装类
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleRouteDelReqEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Logger
    @Required
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Required
    @ApiModelProperty("线路s")
    private List<TmsScheduleRouteEsDTO> list;
}
