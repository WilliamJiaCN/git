package com.hivescm.tms.api.dto.es.waybill.component;

import com.hivescm.tms.api.dto.es.transport.TransportWaybillDetailEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class TmsWaybillStockOnTheWayDto implements Serializable{
	
	private static final long serialVersionUID = 6044707437860034831L;

    @ApiModelProperty("在途库存信息")
    private List<TransportWaybillDetailEsDTO> transportWaybillDetails;

    @ApiModelProperty("查询总条数")
    private Integer count = 0;
}
