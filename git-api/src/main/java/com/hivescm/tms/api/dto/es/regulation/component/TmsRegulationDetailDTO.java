package com.hivescm.tms.api.dto.es.regulation.component;

import com.hivescm.tms.api.dto.es.regulation.RegulationGoodsDetailEsDTO;
import com.hivescm.tms.api.dto.es.regulation.RegulationWaybillDetailEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class TmsRegulationDetailDTO {
	
	/**
     * 调拨运单信息
     */
    @ApiModelProperty("调拨运单信息")
    private RegulationWaybillDetailEsDTO regulationWaybillDetailEsDTO;
    /**
     * 调拨货物明细信息
     */
    @ApiModelProperty("调拨货物明细信息")
    private List<RegulationGoodsDetailEsDTO> regulationGoodsDetailEsDTOList;

}
