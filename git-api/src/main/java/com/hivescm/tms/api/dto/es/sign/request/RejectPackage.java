package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 拒收的包裹信息
 *
 * @author 杨彭伟
 * @since 2017/11/10 19:43
 */
@Data
@ToString
public class RejectPackage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * SKUID
     */
    @Mapping @Logger
    @ApiModelProperty(value = "SKUID", required = true)
    private String skuid;

    /**
     * 包裹id
     */
    @Mapping @Logger
    @ApiModelProperty(value = "包裹id", required = true)
    private Long packageId;

    /**
     * 数量
     */
    @Mapping
    @ApiModelProperty(value = "拒收的数量", required = true)
    private Integer amount;

    /**
     * 单位
     */
    @Mapping
    @ApiModelProperty(value = "单位", required = true)
    private String unit;

    /**
     * 拒收原因
     */
    @Mapping
    @ApiModelProperty(value = "拒收原因", required = true)
    private String refuseCause;
}
