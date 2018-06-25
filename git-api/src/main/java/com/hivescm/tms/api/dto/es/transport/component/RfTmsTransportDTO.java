package com.hivescm.tms.api.dto.es.transport.component;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportLineEsDTO;
import com.hivescm.tms.api.dto.es.transport.redundancy.TransportWaybillTagEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class RfTmsTransportDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5367398505318721133L;
	
	private @ApiModelProperty("批次信息") TransportInfoEsDTO transportInfoEsDTO;
	
	private @ApiModelProperty("线路信息")TransportLineEsDTO transportLineEsDTO;

    private @ApiModelProperty("批次运单") List<TmsTransportDetailDTO> transportDetailDTOList; 
	
	private @ApiModelProperty("运单标签") List<TransportWaybillTagEsDTO> transportWaybillTagList;
	
	
	
}
