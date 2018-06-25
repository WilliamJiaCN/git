package com.hivescm.tms.api.dto.es.schedule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *  线路ESDTO
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleRouteEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("线路Id")
    private Long id;
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Mapping
    @ApiModelProperty("线路编号")
    private String routeCode;
    @Logger
    @Mapping
    @ApiModelProperty("线路名称")
    private String routeName;

    @Mapping
    @ApiModelProperty("线路状态")
    private Boolean routeState;

    @Mapping
    @ApiModelProperty("道路名称s")
    private String roadNames;

    @Mapping
    @ApiModelProperty("覆盖区域ids")
    private String coverAreaIds;
    @Mapping
    @ApiModelProperty("覆盖区域s")
    private String coverAreas;

    @Mapping
    @ApiModelProperty("覆盖类型s")
    private String coverTypes;

    @Mapping
    @ApiModelProperty("开始号码s")
    private String startNums;

    @Mapping
    @ApiModelProperty("结束号码s")
    private String endNums;

    @Mapping
    @ApiModelProperty("号码类型s")
    private String numberTypes;

    @Mapping
    @ApiModelProperty("站点号s")
    private String siteNums;

    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    @Mapping
    @ApiModelProperty("备注")
    private String remarks;
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
