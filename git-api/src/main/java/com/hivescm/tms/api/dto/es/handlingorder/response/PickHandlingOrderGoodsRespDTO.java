package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/25 14:16
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class PickHandlingOrderGoodsRespDTO implements Serializable{

    private static final long serialVersionUID = 2209667266849987194L;

    /**
     * 货物ID
     */
    @ApiModelProperty("货物ID")
    private Long goodsId;

    /**
     * 装车件数
     */
    @ApiModelProperty("装车件数")
    private Integer loadAmount;

    /**
     * 装车重量
     */
    @ApiModelProperty("装车重量")
    private BigDecimal loadWeight;

    /**
     * 装车体积
     */
    @ApiModelProperty("装车体积")
    private BigDecimal loadVolume;

    /**
     * 货物名称
     */
    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;


    /**
     * 货物库存ID
     */
    @Mapping
    @ApiModelProperty("库存ID")
    private Long waybillStockDetailId;
}
