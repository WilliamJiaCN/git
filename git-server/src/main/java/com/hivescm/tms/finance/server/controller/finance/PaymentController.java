package com.hivescm.tms.finance.server.controller.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.component.FinancePaymentESDTO;
import com.hivescm.tms.api.dto.es.finance.request.PaymentReqDTO;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.finance.PaymentControllerDoc;
import com.hivescm.tms.finance.server.service.finance.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseController implements PaymentControllerDoc{
	@Autowired
	private PaymentService paymentService;
	
	@Override
	@PostMapping("/getPaymentList")
	public DataResult<List<FinancePaymentESDTO>> getPaymentList(@RequestBody PaymentReqDTO paymentReqDTO) {
		return paymentService.getPaymentList(paymentReqDTO);
	}

	@Override
	@PostMapping("/getNoPaymentList")
	public DataResult<List<FinancePaymentESDTO>> getNoPaymentList(@RequestBody PaymentReqDTO PaymentReqDTO) {
		return paymentService.getNoPaymentList(PaymentReqDTO);
	}

	@Override
	@PostMapping("/getAlreadyPaymentList")
	public DataResult<List<FinancePaymentESDTO>> getAlreadyPaymentList(@RequestBody PaymentReqDTO paymentReqDTO) {
		return paymentService.getAlreadyPaymentList(paymentReqDTO);
	}

	
}
