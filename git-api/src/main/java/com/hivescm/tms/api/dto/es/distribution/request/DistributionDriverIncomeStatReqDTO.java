package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 司机收入统计信息请求封装类
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/19
 */
@Data
@ToString
public class DistributionDriverIncomeStatReqDTO implements Serializable {


    private static final long serialVersionUID = 6490281721721922695L;


    @Mapping
    @ApiModelProperty(value = "公司ID", required = true)
    private Long companyId;

    @Mapping
    @ApiModelProperty("司机ID")
    private Integer driverId;
    
    @Mapping
    @ApiModelProperty("返回的荣誉榜数量")
    private Integer returnCount;

}
