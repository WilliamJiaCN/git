package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 转移记录列表请求类
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleRecordQueryReqEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Mapping
    @ApiModelProperty("原司机姓名")
    private String driverName;
    @Logger
    @Mapping
    @ApiModelProperty("目标司机姓名")
    private String targetDriverName;
    @Mapping
    @ApiModelProperty("操作类型")
    private Integer type;
    @Mapping
    @ApiModelProperty("每页大小")
    private Integer pageSize;
    @Mapping
    @ApiModelProperty("当前页")
    private Integer currentPage;

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
