package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 关键词详情请求类
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleDetailDeliveryReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;
    @Logger
    @Mapping
    @ApiModelProperty("派送线路编号")
    private String deliveryCode;
    //仅用于导出 根据司机Id获取司机下的关键词
    @Logger
    @Mapping
    @ApiModelProperty("派送线路编号")
    private Integer driverId;
    //关键词模糊检索
    @Mapping
    @ApiModelProperty("关键词")
    private String keyWord;
    //关键词省Id
    @Mapping
    @ApiModelProperty("省Id")
    private String kProvinceId;
    //关键词市Id
    @Mapping
    @ApiModelProperty("市Id")
    private String kCityId;
    //关键词县Id
    @Mapping
    @ApiModelProperty("县Id")
    private String kCountyId;
    //关键词镇Id
    @Mapping
    @ApiModelProperty("镇Id")
    private String kTownId;
    //电子围栏省id
    @Mapping
    @ApiModelProperty("省Id")
    private String bProvinceId;
    //电子围栏市Id
    @Mapping
    @ApiModelProperty("市Id")
    private String bCityId;
    //电子围栏县Id
    @Mapping
    @ApiModelProperty("县Id")
    private String bCountyId;
    //电子围栏镇Idd
    @Mapping
    @ApiModelProperty("镇Id")
    private String bTownId;
}
