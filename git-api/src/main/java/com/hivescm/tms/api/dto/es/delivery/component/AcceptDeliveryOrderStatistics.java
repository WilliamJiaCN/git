package com.hivescm.tms.api.dto.es.delivery.component;

import com.hivescm.tms.api.dto.es.order.OrderEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
@Data
@ToString
public class AcceptDeliveryOrderStatistics {

    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;
    
    @ApiModelProperty(value = "单数")
    private Integer amount;
    
    @ApiModelProperty(value = "重量")
    private BigDecimal weight;
    
    @ApiModelProperty(value = "体积")
    private BigDecimal volume;
    
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    
    @ApiModelProperty(value = "订单信息")
    private List<OrderEsDTO> orders;
    
    @ApiModelProperty(value = "总条数")
    private Integer count;
}
