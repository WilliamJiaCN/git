package com.hivescm.tms.api.dto.es.schedule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 电子围ESDTO
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsScheduleBlockEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("电子围栏id")
    private Long id;
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Mapping
    @ApiModelProperty("经度")
    private String lngs;
    @Mapping
    @ApiModelProperty("纬度")
    private String lats;
    @Mapping
    @ApiModelProperty("区块名称")
    private String blockName;
    /**
     * blockType 1:以司机为维度的电子围栏，2：区块管理
     */
    @Mapping
    @ApiModelProperty("区块类型")
    private Integer blockType;

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
    //添加时必填
    @Mapping
    @ApiModelProperty("覆盖区域Id")
    private String coverAreaId;
    //添加时必填
    @Mapping
    @ApiModelProperty("覆盖区域")
    private String coverArea;



}