package com.hivescm.tms.finance.server.service.finance;

import java.util.List;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.component.FinanceReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.ReceiptReqDTO;

public interface FinanceService {

	DataResult<List<FinanceReceiptEsDTO>> getReceiptList(ReceiptReqDTO receiptReqDTO);

	DataResult<List<FinanceReceiptEsDTO>> getNoReceiptList(ReceiptReqDTO receiptReqDTO);

	DataResult<List<FinanceReceiptEsDTO>> getAlreadyReceiptList(ReceiptReqDTO receiptReqDTO);

	FinanceReceiptEsDTO getByID(Long receiptId);
}
