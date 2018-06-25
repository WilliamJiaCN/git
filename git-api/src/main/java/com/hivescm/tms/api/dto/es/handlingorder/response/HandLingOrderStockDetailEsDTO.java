package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运单明细详情
 * <p>Title: HandLingOrderStockDetailEsDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-22-16:47
 */
@Data
@ToString
public class HandLingOrderStockDetailEsDTO implements Serializable {

    private static final long serialVersionUID = -5813150779923977910L;

    @Mapping @ApiModelProperty("货物名称") private String goodsName;        // handlingorderdetails
    @Mapping @ApiModelProperty("包装") private String packingName;        // handlingorderdetails
    @Mapping @ApiModelProperty("装卸件数") private Integer totalGoodsNum;  // handlingorder
    @Mapping @ApiModelProperty("装卸重量") private BigDecimal totalWeight; // handlingorder
    @Mapping @ApiModelProperty("装卸体积") private BigDecimal totalVolume; // handlingorder
    @Mapping @ApiModelProperty("总件数") private Integer totalNum;// waybill
    @Mapping @ApiModelProperty("总体积") private BigDecimal volume;// waybill
    @Mapping @ApiModelProperty("总重量") private BigDecimal weight;// waybill


}
