package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleRoadEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
/**
 * 线路请求类 新增和修改
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsScheduleRouteReqEsDTO {
    @Logger
    @Required
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;

    /**
     * 用于修改公司线路
     */
    @Logger
    @Mapping
    @ApiModelProperty("线路Id")
    private Long routeId;
    @Logger
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
    @ApiModelProperty("创建人姓名")
    private String createUserName;
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;
    @Required
    @Mapping
    @ApiModelProperty("道路列表")
    private List<TmsScheduleRoadEsDTO> roads;

    @Mapping
    @ApiModelProperty("运力分配线路Id")
    private Long warehouseLineId;

    @Mapping
    @ApiModelProperty("运力分配线路名称")
    private String warehouseLineName;
}
