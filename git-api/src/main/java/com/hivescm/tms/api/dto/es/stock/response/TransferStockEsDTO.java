package com.hivescm.tms.api.dto.es.stock.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.stock.WaybillStockDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.util.List;

/**
 * 库存新增时显示列表
 * <p>Title: TransferStockEsDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 * <p>Company:http://hivescm.com/ </p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-03-30-上午11:33
 */
@ToString
@Data
public class TransferStockEsDTO {

    @Mapping
    @ApiModelProperty("库存")
    private WaybillStockEsDTO waybillStockEsDTO;

    @Mapping
    @ApiModelProperty("库存明细")
    private List<WaybillStockDetailEsDTO> waybillStockDetailEsDTOList;
}
