package com.hivescm.tms.api.dto.es.schedule.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleRoadEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 线路响应类 详情
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsScheduleRouteRespEsDTO {
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;

    /**
     * 用于修改公司线路
     */
    @Mapping
    @ApiModelProperty("线路Id")
    private Long routeId;
    @Mapping
    @ApiModelProperty("线路编号")
    private String routeCode;
    @Mapping
    @ApiModelProperty("线路名称")
    private String routeName;
    @Mapping
    @ApiModelProperty("线路状态")
    private Boolean routeState;
    @Mapping
    @ApiModelProperty("备注")
    private String remarks;
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("道路列表")
    private List<TmsScheduleRoadEsDTO> roads;
    @Mapping
    @ApiModelProperty("运力分配线路Id")
    private Long warehouseLineId;
    @Mapping
    @ApiModelProperty("运力分配线路名称")
    private String warehouseLineName;
    @Mapping
    @ApiModelProperty("关联门店数量")
    private Integer storeCount;
}
