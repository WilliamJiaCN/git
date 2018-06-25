package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 关键词请求类 用于新增关键词
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleKeyWordReqDTO implements Serializable {
    @Mapping
    @ApiModelProperty("覆盖区域Id")
    private String coverAreaId;
    @Mapping
    @ApiModelProperty("覆盖区域")
    private String coverArea;
    @Mapping
    @ApiModelProperty("关键词Ids")
    private String keyWordIds;
    @Mapping
    @ApiModelProperty("关键词s")
    private String keyWords;

    @Mapping
    @ApiModelProperty("省Id")
    private String provinceId;

    @Mapping
    @ApiModelProperty("市Id")
    private String cityId;

    @Mapping
    @ApiModelProperty("县Id")
    private String countyId;

    @Mapping
    @ApiModelProperty("镇Id")
    private String townId;

    @Mapping
    @ApiModelProperty("省名称")
    private String provinceName;

    @Mapping
    @ApiModelProperty("市名称")
    private String cityName;

    @Mapping
    @ApiModelProperty("县名称")
    private String countyName;

    @Mapping
    @ApiModelProperty("镇名称")
    private String townName;
}
