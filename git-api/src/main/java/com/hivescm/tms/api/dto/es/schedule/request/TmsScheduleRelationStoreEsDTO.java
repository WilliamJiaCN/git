package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleStoreEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 关联门店请求类型
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleRelationStoreEsDTO implements Serializable {
    @Logger
    @Required
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Required
    @Mapping
    @ApiModelProperty("线路Id")
    private Long routeId;

    @ApiModelProperty("门店列表")
    List<TmsScheduleStoreEsDTO> list = new ArrayList<>();
}
