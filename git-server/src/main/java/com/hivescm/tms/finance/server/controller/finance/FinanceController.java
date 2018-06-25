package com.hivescm.tms.finance.server.controller.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.component.FinanceReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.ReceiptReqDTO;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.finance.FinanceControllerDoc;
import com.hivescm.tms.finance.server.service.finance.FinanceService;

@RestController
@RequestMapping("/finance")
public class FinanceController extends BaseController implements  FinanceControllerDoc{

	@Autowired
	private FinanceService financeService;
	@Override
	@PostMapping("/getReceiptList")
	public DataResult<List<FinanceReceiptEsDTO>> getReceiptList(@RequestBody ReceiptReqDTO receiptReqDTO) {
		return financeService.getReceiptList(receiptReqDTO);
	}
	@Override
	@PostMapping("/getNoReceiptList")
	public DataResult<List<FinanceReceiptEsDTO>> getNoReceiptList(@RequestBody ReceiptReqDTO receiptReqDTO) {
		return financeService.getNoReceiptList(receiptReqDTO);
	}
	@Override
	@PostMapping("/getAlreadyReceiptList")
	public DataResult<List<FinanceReceiptEsDTO>> getAlreadyReceiptList(@RequestBody ReceiptReqDTO receiptReqDTO) {
		return financeService.getAlreadyReceiptList(receiptReqDTO);
	}

}
