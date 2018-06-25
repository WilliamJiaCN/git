package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 派送线路列表请求类
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleDeliveryQueryReqEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;
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
    @ApiModelProperty("关键词")
    private String keyWord;

    @Mapping
    @ApiModelProperty("承运商名称")
    private String carryName;

    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    @Mapping
    @ApiModelProperty("每页大小")
    private Integer pageSize;
    @Mapping
    @ApiModelProperty("当前页")
    private Integer currentPage;


    //仅用于展示
    @Mapping
    @ApiModelProperty("省名称")
    private String provinceName;
    //仅用于展示
    @Mapping
    @ApiModelProperty("市名称")
    private String cityName;
    //仅用于展示
    @Mapping
    @ApiModelProperty("县名称")
    private String countyName;
    //仅用于展示
    @Mapping
    @ApiModelProperty("镇名称")
    private String townName;

    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize || pageSize == 0) {
            pageSize = 10;
        }
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
    }

}
