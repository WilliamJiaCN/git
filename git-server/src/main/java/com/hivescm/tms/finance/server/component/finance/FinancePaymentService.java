package com.hivescm.tms.finance.server.component.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceReceiptConfirmDTO;
import com.hivescm.tms.api.dto.es.finance.PaymentGrantDTO;
import com.hivescm.tms.api.dto.es.finance.request.PaymentAccountReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.PaymentAccountListRespDTO;

import java.util.List;

/**
 * 财务管理-付款 
 * @author 杨彭伟
 * @date 2017-11-23 19:56
 */
public interface FinancePaymentService {
    /**
     * 生成付款记录
     * @return
     * @param financeReceiptConfirmDTO
     */
    List<Long> saveFinancePayment(FinanceReceiptConfirmDTO financeReceiptConfirmDTO);

    /**
     * 发放货款
     * @return
     * @param paymentGrantDTO
     */
    Boolean updatePaymentForGrant(PaymentGrantDTO paymentGrantDTO);

    PaymentAccountListRespDTO getAccountList(PaymentAccountReqDTO paymentAccountReqDTO);

}
