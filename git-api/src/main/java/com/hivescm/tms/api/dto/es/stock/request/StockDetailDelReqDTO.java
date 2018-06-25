package com.hivescm.tms.api.dto.es.stock.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class StockDetailDelReqDTO {
    @ApiModelProperty("公司Id")
    private Long companyId;
    @ApiModelProperty("库存详情id")
    private List<Long> detailIds;
}
