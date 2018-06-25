package com.hivescm.tms.api.dto.es.regulation.resp;

import java.util.List;

import com.hivescm.tms.api.dto.es.regulation.RegulationInfoEsDTO;
import com.hivescm.tms.api.dto.es.regulation.request.QueryRegulationListReq;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class QueryRegulationListRespDTO {

	private List<RegulationInfoEsDTO> list;
	
	Integer count = 0;

}
