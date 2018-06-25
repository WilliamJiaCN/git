package com.hivescm.tms.api.dto.es.regulation.component;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.regulation.RegulationInfoEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class TmsRegulationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8255331378831517481L;
	
	/**
     * 调拨基本信息
     */
    @ApiModelProperty("调拨基本信息")
    private RegulationInfoEsDTO regulationInfoEsDTO;
    
    /**
     * 调拨单明细信息
     */
    @ApiModelProperty("调拨单明细信息")
    private List<TmsRegulationDetailDTO> tmsRegulationDetailDTOList;
	
}
