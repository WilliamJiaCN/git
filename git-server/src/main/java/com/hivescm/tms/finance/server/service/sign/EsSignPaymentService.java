package com.hivescm.tms.finance.server.service.sign;

import com.hivescm.tms.api.dto.es.sign.OrderPaymentInfoESDTO;

/**
 * @author 杨彭伟
 * @date 2018-01-17 23:11
 */
public interface EsSignPaymentService {

    OrderPaymentInfoESDTO getSignPayment(Long waybillId);
}
