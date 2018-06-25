package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.request.FinancePaymentReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinancePaymentRespDTO;

public interface EsPaymentService {

	FinancePaymentRespDTO getPaymentList(FinancePaymentReqDTO financePaymentReqDTO);

}
