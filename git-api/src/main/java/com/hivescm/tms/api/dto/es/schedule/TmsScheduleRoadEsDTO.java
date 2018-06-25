package com.hivescm.tms.api.dto.es.schedule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *  道路EsDTO
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleRoadEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("道路Id")
    private Long id;
    //插入时 需填项
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Mapping
    @ApiModelProperty("司机Id")
    private Integer driverId;
    //插入时 需填项修改时必填
    @Logger
    @Mapping
    @ApiModelProperty("线路Id")
    private Long routeId;

    /**
     * 线路类型 1：公司线路2：司机线路
     */
    @Mapping
    @ApiModelProperty("线路类型")
    private Integer type;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("道路名称")
    private String roadName;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("开始号码")
    private Integer startNum;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("结束号码")
    private Integer endNum;
    @Mapping
    @ApiModelProperty("覆盖区域Id")
    private String coverAreaId;
    @Mapping
    @ApiModelProperty("覆盖区域")
    private String coverArea;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("覆盖Id")
    private Long coverTypeId;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("覆盖类型")
    private String coverType;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("号码类型")
    private Long numberTypeId;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("号码类型")
    private String numberType;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;

    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("站点号")
    private String siteNum;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("省Id")
    private String provinceId;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("市Id")
    private String cityId;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("县Id")
    private String countyId;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("镇Id")
    private String townId;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("省名称")
    private String provinceName;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("市名称")
    private String cityName;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("县名称")
    private String countyName;
    //插入时 需填项
    @Mapping
    @ApiModelProperty("镇名称")
    private String townName;
}
