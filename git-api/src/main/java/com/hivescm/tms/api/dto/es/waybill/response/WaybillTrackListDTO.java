package com.hivescm.tms.api.dto.es.waybill.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/24 19:20
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillTrackListDTO implements Serializable {

    private static final long serialVersionUID = -5846689157772555321L;

    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 状态
     */
    @Mapping
    @ApiModelProperty("运单状态：1开单2揽收3派送4签收5发车6到达目的地")
    private String statusName;


    /**
     * 跟踪消息
     */
    @Mapping
    @ApiModelProperty("跟踪消息")
    private String informat;
}
