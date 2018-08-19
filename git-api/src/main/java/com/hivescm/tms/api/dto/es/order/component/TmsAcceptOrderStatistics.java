package com.hivescm.tms.api.dto.es.order.component;

import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
@Data
@ToString
public class TmsAcceptOrderStatistics {

    @ApiModelProperty(value = "线路ID")
    private Long lineId;
    
    @ApiModelProperty(value = "单数")
    private Integer amount;
    
    @ApiModelProperty(value = "重量")
    private BigDecimal weight;
    
    @ApiModelProperty(value = "体积")
    private BigDecimal volume;
    
    @ApiModelProperty(value = "线路名称")
    private String lineName;
    
    @ApiModelProperty(value = "订单信息")
    private List<WaybillEsDTO> waybills;
    
    @ApiModelProperty(value = "总条数")
    private Integer count;
}
