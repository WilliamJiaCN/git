package com.hivescm.tms.finance.server.interfaces.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptConfirmDTO;
import com.hivescm.tms.api.dto.es.finance.PaymentGrantDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinancePaymentReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.PaymentAccountReqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 付款记录接口文档
 *
 * @author 杨彭伟
 * @date 2017-11-26 16:37
 */
@Api(value = "FinancePayment", description = "付款记录")
public interface FinancePaymentControlerDoc {

    @ApiOperation(value = "生成付款记录", notes = "生成付款记录")
    DataResult save(@RequestBody FinanceReceiptConfirmDTO financeReceiptConfirmDTO);

    @ApiOperation(value = "查询付款记录", notes = "查询付款记录")
    DataResult getList(@RequestBody FinancePaymentReqDTO financePaymentReqDTO);

    @ApiOperation(value = "查询付款单账户", notes = "查询付款单账户")
    DataResult getAccountList(@RequestBody PaymentAccountReqDTO paymentAccountReqDTO);

    @ApiOperation(value = "发放货款", notes = "发放货款")
    DataResult grant(@RequestBody PaymentGrantDTO paymentGrantDTO);
}
