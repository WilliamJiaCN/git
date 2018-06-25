package com.hivescm.tms.api.dto.excel;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * excel实体
 *
 * @author zhenming.du
 * @since 2017/9/7 
 */
@Data
@ToString
public class ExcelKeyWordDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    @Mapping
    @ApiModelProperty("省名称")
    private String provinceName;
    @Mapping
    @ApiModelProperty("市名称")
    private String cityName;
    @Mapping
    @ApiModelProperty("县名称")
    private String countryName;
    @Mapping
    @ApiModelProperty("镇名称")
    private String townName;
    @Mapping
    @ApiModelProperty("关键词")
    private String keyWord;
}