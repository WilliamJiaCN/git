package com.hivescm.tms.api.dto.es.transport.redundancy;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运输批次商品到货信息
 *
 * @author 李洪春
 * @since 2017/9/18 下午10:09
 */
@Data
@ToString
public class TransportGoodsArriveEsDTO implements Serializable {


    private static final long serialVersionUID = 7907039862580505204L;
    /**
     * 到货件数
     */
    @Mapping
    @ApiModelProperty("到货件数")
    private Integer unloadAmount;

    /**
     * 到货重量
     */
    @Mapping
    @ApiModelProperty(value = "到货重量", example = "111.11")

    private BigDecimal unloadWeight;

    /**
     * 到货体积
     */
    @Mapping
    @ApiModelProperty(value = "到货体积", example = "111.11")
    private BigDecimal unloadVolume;
}
