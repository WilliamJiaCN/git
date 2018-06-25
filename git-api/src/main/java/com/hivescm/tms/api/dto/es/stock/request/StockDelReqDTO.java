package com.hivescm.tms.api.dto.es.stock.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StockDelReqDTO {
    @ApiModelProperty("公司Id")
    private Long companyId;
    @ApiModelProperty("库存id")
    private Long stockId;

    public StockDelReqDTO(Long companyId, Long stockId) {
        this.companyId = companyId;
        this.stockId = stockId;
    }

    public StockDelReqDTO() {

    }
}
