package com.hivescm.tms.api.dto.es.stock;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillStockDTO;
import com.hivescm.tms.api.enums.biz.stock.StockLockTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class StockAsynDTO implements Serializable {
    private static final long serialVersionUID = -8324148951144264512L;
    @Required
    @ApiModelProperty(value = "业务类型", required = true)
    private StockLockTypeEnum stockLockType;
    @ApiModelProperty("单票添加请求参数")
    private StockSignleVoteAddEsDTO stockSignleVoteAddEsDTO;
    @ApiModelProperty("到货入库请求参数")
    private List<TmsWaybillStockDTO> waybillStockDTOS;
    @ApiModelProperty("解除签收件数请求参数")
    private SignStockOperating signStockOperating;
    @ApiModelProperty("生成修改库存参数")
    private WaybillStockEsDTO waybillStockEsDTO;
    @ApiModelProperty("生成修改库存参数")
    private List<WaybillStockDetailEsDTO> stockDetailEsDTOS;
    @ApiModelProperty("库存修改参数")
    private Long stockId;
}
