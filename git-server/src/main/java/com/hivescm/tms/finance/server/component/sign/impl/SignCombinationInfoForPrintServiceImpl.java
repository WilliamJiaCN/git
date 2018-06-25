package com.hivescm.tms.finance.server.component.sign.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.print.waybill.WaybillCombinationInfoForPrintDTO;
import com.hivescm.tms.finance.server.component.sign.SignCombinationInfoForPrintService;
import com.hivescm.tms.finance.server.component.sign.SignForService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;

@Service
public class SignCombinationInfoForPrintServiceImpl implements SignCombinationInfoForPrintService {
	
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private SignForService signForService;

	@Override
	public WaybillCombinationInfoForPrintDTO querySignCombinationInformationForPrint(Long waybillId) {
		WaybillCombinationInfoForPrintDTO combinationDTO = new WaybillCombinationInfoForPrintDTO();
		DataResult<WaybillCombinationInfoForPrintDTO> dataResult = waybillService.queryWaybillCombinationInformationForPrint(waybillId);
		if(null!=dataResult && null!=dataResult.getResult()){
			combinationDTO = dataResult.getResult();
		}
		//查询签收单信息
		SignEsDTO signEsDTO = signForService.querySignEsDTOByWaybillId(waybillId);
		if(null!=signEsDTO) {
			combinationDTO.setSignNumber(signEsDTO.getSignNumber());
			combinationDTO.setSignWeight(signEsDTO.getSignWeight()==null?new BigDecimal(0):signEsDTO.getSignWeight());
			combinationDTO.setSignVolume(signEsDTO.getSignVolume()==null?new BigDecimal(0):signEsDTO.getSignVolume());
		}
		return combinationDTO;
	}

}
