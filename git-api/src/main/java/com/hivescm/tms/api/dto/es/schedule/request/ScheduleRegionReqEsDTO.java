package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 区域详情请求类
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/9/7 13:56
 */
@Data
@ToString
public class ScheduleRegionReqEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public ScheduleRegionReqEsDTO(Long companyId, Integer driverId) {
		super();
		this.companyId = companyId;
		this.driverId = driverId;
	}

	public ScheduleRegionReqEsDTO() {
		super();
	}

	@Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    @Mapping
    @ApiModelProperty("司机Id")
    private Integer driverId;

    @ApiModelProperty("省Id")
    private Long provinceId;

    @ApiModelProperty("市Id")
    private Long cityId;

    @ApiModelProperty("县Id")
    private Long countyId;

    @ApiModelProperty("镇Id")
    private Long townId;


}
