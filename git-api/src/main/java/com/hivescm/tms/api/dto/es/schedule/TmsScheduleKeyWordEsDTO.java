package com.hivescm.tms.api.dto.es.schedule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 关键词ESDTO
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsScheduleKeyWordEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("关键词Id")
    private Long id;
    //添加时必填
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    //添加时必填
    @Logger
    @Mapping
    @ApiModelProperty("关键词")
    private String keyWord;
    //需要填
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
    //添加时必填
    @Mapping
    @ApiModelProperty("省Id")
    private String provinceId;
    //添加时必填
    @Mapping
    @ApiModelProperty("市Id")
    private String cityId;
    //添加时必填
    @Mapping
    @ApiModelProperty("县Id")
    private String countyId;
    //添加时必填
    @Mapping
    @ApiModelProperty("镇Id")
    private String townId;
    //添加时必填
    @Mapping
    @ApiModelProperty("省名称")
    private String provinceName;
    //添加时必填
    @Mapping
    @ApiModelProperty("市名称")
    private String cityName;
    //添加时必填
    @Mapping
    @ApiModelProperty("县名称")
    private String countyName;
    //添加时必填
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
    
    public Boolean isValid(){
        return null != this.provinceName && null != this.cityName && null != this.keyWord;
    }
}