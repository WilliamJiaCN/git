package com.hivescm.tms.api.dto.es.schedule.redundancy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 用于修改司机派送线路信息
 */
@Data
@ToString
public class TmsScheduleInsertRegionEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("覆盖区域Id列表")
    private List<String> driverRoadDOS;


}
