package com.hivescm.tms.finance.server.service.finance;

import java.util.List;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.component.FinancePaymentESDTO;
import com.hivescm.tms.api.dto.es.finance.request.PaymentReqDTO;

public interface PaymentService {

	DataResult<List<FinancePaymentESDTO>> getPaymentList(PaymentReqDTO paymentReqDTO);

	DataResult<List<FinancePaymentESDTO>> getNoPaymentList(PaymentReqDTO paymentReqDTO);

	DataResult<List<FinancePaymentESDTO>> getAlreadyPaymentList(PaymentReqDTO paymentReqDTO);

}
