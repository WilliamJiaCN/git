package com.hivescm.tms.api.dto.es.schedule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 派送线路表ESDTO
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleDeliveryEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("派送线路Id")
    private Long id;
    @Logger
    @Mapping
    @ApiModelProperty("派送线路编号")
    private String deliveryCode;
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Mapping
    @ApiModelProperty("司机Id")
    private Integer driverId;
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    @Mapping
    @ApiModelProperty("手机号")
    private String phoneNo;

    /**
     * 启用状态 true 启用 false 停用
     */
    @Mapping
    @ApiModelProperty("启用状态")
    private Boolean used;
    @Mapping
    @ApiModelProperty("所属承运商")
    private Integer belongCarry;
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carryName;
    @Mapping
    @ApiModelProperty("覆盖区域ids")
    private String coverAreaIds;
    @Mapping
    @ApiModelProperty("覆盖区域s")
    private String coverAreas;
    @Mapping
    @ApiModelProperty("覆盖类型Ids")
    private String coverTypeIds;
    @Mapping
    @ApiModelProperty("覆盖类型")
    private String coverTypes;
    @Mapping
    @ApiModelProperty("覆盖线路Ids")
    private String coverRouteIds;
    @Mapping
    @ApiModelProperty("覆盖线路s")
    private String coverRoutes;
    @Mapping
    @ApiModelProperty("关键词Ids")
    private String keyWordIds;
    @Mapping
    @ApiModelProperty("关键词s")
    private String keyWords;

    @Mapping
    @ApiModelProperty("电子围栏Ids")
    private String blockIds;
    @Mapping
    @ApiModelProperty("电子围栏名称")
    private String blockNames;
    @Mapping
    @ApiModelProperty("备注")
    private String remarks;

    @Mapping
    @ApiModelProperty("号码类型Ids")
    private String numberTypeIds;
    @Mapping
    @ApiModelProperty("号码类型s")
    private String numberTypes;
    @Mapping
    @ApiModelProperty("道路Ids")
    private String roadIds;
    @Mapping
    @ApiModelProperty("道路名称s")
    private String roadNames;
    @Mapping
    @ApiModelProperty("开始号码s")
    private String startNums;
    @Mapping
    @ApiModelProperty("结束号码s")
    private String endNums;
    @Mapping
    @ApiModelProperty("站点号s")
    private String siteNums;



    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("创建人")
    private String createUserName;
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("修改人")
    private String updateUserName;
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;
}
