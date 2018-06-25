package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 删除关键词请求类
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/9/7 13:56
 */
@Data
@ToString
public class TmsScheduleDeliveryDelReqEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("派送线路Id")
    private Long id;
    @Mapping
    @ApiModelProperty(value = "公司ID", required = true)
    private Long companyId;
    @Mapping
    @ApiModelProperty(value = "司机ID", required = true)
    private Integer driverId;
    @Mapping
    @ApiModelProperty("关键词Ids")
    private String keyWordIds;
    @Mapping
    @ApiModelProperty("电子围栏Ids")
    private String blockIds;
}
