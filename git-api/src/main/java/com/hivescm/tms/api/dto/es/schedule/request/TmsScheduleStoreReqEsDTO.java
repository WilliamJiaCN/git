package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 关联门店请求类型
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleStoreReqEsDTO implements Serializable {
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    //用于查询已关联门店和已关联门店明细
    @Logger
    @Mapping
    @ApiModelProperty("线路Id")
    private Long routeId;
    @Mapping
    @ApiModelProperty("线路覆盖区域Ids")
    private String coverAreaIds;
}
