package com.hivescm.tms.finance.server.service.pcsign;

import com.hivescm.tms.api.dto.es.sign.request.OutWareHousePrintReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.OutWareHousePrintRespDTO;

/**
 * 出库单Service
 * @author jisai
 * @Date 2018-04-10
 */
public interface OutboundOrderService {
	/**
	 * 打印出库单
	 * @param tmsSignEsDTO
	 * @return
	 */
	OutWareHousePrintRespDTO printOutboundOrder(OutWareHousePrintReqDTO outWareHousePrintReqDTO);
}
