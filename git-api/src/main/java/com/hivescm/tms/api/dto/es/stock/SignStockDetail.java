package com.hivescm.tms.api.dto.es.stock;


import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignStockDetail {

    @Required
    @ApiModelProperty(value="库存明细ID")
    private Long stockDetailId; // waybillStockDetail.id

    @Required
    @ApiModelProperty(value="所需操作件数") // 明细解除件数
    private Integer signNumber;


}
