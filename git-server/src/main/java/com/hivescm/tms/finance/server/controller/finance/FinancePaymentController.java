package com.hivescm.tms.finance.server.controller.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptConfirmDTO;
import com.hivescm.tms.api.dto.es.finance.PaymentGrantDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinancePaymentReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.PaymentAccountReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.PaymentAccountListRespDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.finance.FinancePaymentService;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.finance.FinancePaymentControlerDoc;
import com.hivescm.tms.finance.server.service.finance.EsPaymentService;

/**
 * @author 杨彭伟
 * @date 2017-11-23 21:17
 */
@RestController
@RequestMapping("/finance/payment")
public class FinancePaymentController extends BaseController implements FinancePaymentControlerDoc {
    @Autowired
    private FinancePaymentService financePaymentService;
    @Autowired
    private EsPaymentService paymentService;

    @Override
    @PostMapping("/save")
    public DataResult save(@RequestBody FinanceReceiptConfirmDTO financeReceiptConfirmDTO) {
        try {
            return DataResult.success(financePaymentService.saveFinancePayment(financeReceiptConfirmDTO));
        } catch (Exception e) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
        }
    }

    @Override
    @PostMapping("/account")
    public DataResult<PaymentAccountListRespDTO> getAccountList(@RequestBody PaymentAccountReqDTO paymentAccountReqDTO) {
        try {
            return DataResult.success(financePaymentService.getAccountList(paymentAccountReqDTO),PaymentAccountListRespDTO.class);
        }catch (SystemException e) {
            return DataResult.faild(e.getErrorCode(), e.getErrorReason());
        }catch (Exception e) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getMessage());
        }
    }

    @Override
    @PostMapping("/grant")
    public DataResult grant(@RequestBody PaymentGrantDTO paymentGrantDTO) {
        try {
            return DataResult.success(financePaymentService.updatePaymentForGrant(paymentGrantDTO));
        } catch (Exception e) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
        }
    }

    @Override
    @PostMapping("/list")
    public DataResult getList(@RequestBody FinancePaymentReqDTO financePaymentReqDTO) {
        try {
            return DataResult.success(paymentService.getPaymentList(financePaymentReqDTO));
        } catch (Exception e) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
        }
    }
}
