package com.hivescm.tms.api.dto.es.schedule.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 线路响应类 详情
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsRouteRespEsDTO implements Serializable {

    private static final long serialVersionUID = 242133372453919355L;
    @Mapping
    @ApiModelProperty("线路Id")
    private Long id;
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;

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
