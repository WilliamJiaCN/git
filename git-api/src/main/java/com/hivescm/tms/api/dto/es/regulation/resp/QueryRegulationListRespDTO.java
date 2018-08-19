package com.hivescm.tms.api.dto.es.regulation.resp;

import com.hivescm.tms.api.dto.es.regulation.RegulationInfoEsDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class QueryRegulationListRespDTO {

	private List<RegulationInfoEsDTO> list;
	
	Integer count = 0;

}
