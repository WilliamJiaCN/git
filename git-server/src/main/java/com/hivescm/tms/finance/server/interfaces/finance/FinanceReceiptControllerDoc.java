package com.hivescm.tms.finance.server.interfaces.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptConfirmDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptCreateDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceReceiptReqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 收款记录接口文档
 *
 * @author 杨彭伟
 * @date 2017-11-26 16:37
 */
@Api(value = "FinanceReceipt", description = "收款记录")
public interface FinanceReceiptControllerDoc {

    @ApiOperation(value = "生成收款记录", notes = "生成收款记录")
    DataResult saveReceipt(@RequestBody FinanceReceiptCreateDTO financeReceiptCreateDTO);

    @ApiOperation(value = "确认收银", notes = "确认收银")
    DataResult confirm(@RequestBody FinanceReceiptConfirmDTO financeReceiptConfirmDTO);

    /**
     * 查询收款账户列表
     * @param userSn 承运商网点id
     * @return
     */
    @ApiOperation(value = "查询收款账户列表", notes = "查询收款账户列表，承运商网点id")
    DataResult getReceiptAccount(@PathVariable String userSn);

    @ApiOperation(value = "货款收银列表查询", notes = "货款收银列表查询")
    @ApiImplicitParam(value = "收款单列表查询", name = "financeReceiptReqDTO",
            required = true, paramType = "body", dataType = "FinanceReceiptReqDTO")
    DataResult list(@RequestBody FinanceReceiptReqDTO financeReceiptReqDTO);
    
    
    @ApiOperation(value = "修复财务不能生成货款发放", notes = "修复财务不能生成货款发放")
    public DataResult<List<Long>> resolvePaymentData(@PathVariable("receiptId")Long receiptId);

}
