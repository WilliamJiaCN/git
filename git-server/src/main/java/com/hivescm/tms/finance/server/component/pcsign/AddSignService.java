package com.hivescm.tms.finance.server.component.pcsign;

import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;

public interface AddSignService {

	/**
	 * 自提签收  签收确认和拒签接口
	 * @param tmsSignEsDTO
	 * @return
	 */
	Boolean insertSign(TmsSignEsDTO tmsSignEsDTO);
	

}
