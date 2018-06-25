package com.hivescm.tms.finance.server.component.pcsign;


import com.hivescm.tms.api.dto.es.sign.request.CancelSelfDeliverySignReqDTO;

public interface CancelSelfDeliverySignService {

	/**
	 * 取消签收
	 * @param cancelSelfDeliverySignReqDTO
	 * @return
	 */
	Boolean cancelSelfDeliverySign(CancelSelfDeliverySignReqDTO cancelSelfDeliverySignReqDTO);

}
