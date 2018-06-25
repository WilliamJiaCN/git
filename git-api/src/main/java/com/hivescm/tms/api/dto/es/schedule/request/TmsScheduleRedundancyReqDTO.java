package com.hivescm.tms.api.dto.es.schedule.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 冗余字段请求类
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsScheduleRedundancyReqDTO {
    @ApiModelProperty("覆盖区域Ids")
    private StringBuilder coverAreaIds = new StringBuilder();
    @ApiModelProperty("覆盖区域s")
    private StringBuilder coverAreas = new StringBuilder();
    @ApiModelProperty("覆盖类型Ids")
    private StringBuilder coverTypeIds = new StringBuilder();
    @ApiModelProperty("覆盖类型")
    private StringBuilder coverTypes = new StringBuilder();
    @ApiModelProperty("覆盖线路Ids")
    private StringBuilder coverRouteIds = new StringBuilder();
    @ApiModelProperty("覆盖线路")
    private StringBuilder coverRoutes = new StringBuilder();
    @ApiModelProperty("关键词Ids")
    private StringBuilder keyWordIds = new StringBuilder();
    @ApiModelProperty("关键词s")
    private StringBuilder keyWords = new StringBuilder();
    @ApiModelProperty("号码类型Ids")
    private StringBuilder numberTypeIds = new StringBuilder();
    @ApiModelProperty("号码类型s")
    private StringBuilder numberTypes = new StringBuilder();
    @ApiModelProperty("道路Ids")
    private StringBuilder roadIds = new StringBuilder();
    @ApiModelProperty("道路名称s")
    private StringBuilder roadNames = new StringBuilder();
    @ApiModelProperty("开始号码s")
    private StringBuilder startNums = new StringBuilder();
    @ApiModelProperty("结束号码s")
    private StringBuilder endNums = new StringBuilder();
    @ApiModelProperty("站点号s")
    private StringBuilder siteNums = new StringBuilder();
    @ApiModelProperty("电子围栏Ids")
    private StringBuilder blockIds = new StringBuilder();
    @ApiModelProperty("电子围栏s")
    private StringBuilder blockNames = new StringBuilder();

}
