package com.hivescm.tms.api.dto.es.waybill.component;

import com.hivescm.tms.api.dto.es.stock.WaybillStockEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class TmsWaybillStockInTheLibraryDto implements Serializable{
	
	private static final long serialVersionUID = 19888137588131203L;

	@ApiModelProperty("在库运单库存信息")
	private List<WaybillStockEsDTO> waybillStockDTOs;

    @ApiModelProperty("查询总条数")
    private Integer count = 0;

}
