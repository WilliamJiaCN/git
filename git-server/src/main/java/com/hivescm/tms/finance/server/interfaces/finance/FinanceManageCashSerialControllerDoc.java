package com.hivescm.tms.finance.server.interfaces.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.CashSerialRecordReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageCashSerialListReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashSerialListRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value ="financeManageCashSerial",description ="财务管理现金流水" )
public interface FinanceManageCashSerialControllerDoc {

    @ApiOperation(value = "现金流水修改",notes = "现金流水修改")
    @ApiImplicitParam(value = "现金流水修改", name = "financeManageCashSerialEsDTO", required = true, paramType = "path", dataType = "Integer")
    DataResult<Boolean> updateEs(@PathVariable("id") Integer id);

    /**
     * 查询资金记录
     * @param cashSerialRecordReqDTO
     * @return
     */
    @ApiOperation(value = "查询资金记录",notes = "查询资金记录")
    @ApiImplicitParam(name = "cashSerialRecordReqDTO", value = "查询条件", required = true, dataType = "CashSerialRecordReqDTO", paramType = "body")
    DataResult<List<FinanceManageCashSerialEsDTO>> checkFundRecord(@RequestBody CashSerialRecordReqDTO cashSerialRecordReqDTO);

    /**
     * 查询现金流水列表
     * @param financeManageCashSerialListReqDTO
     * @return
     */
    @ApiOperation(value = "查询现金流水列表", notes = "查询现金流水列表")
    @ApiImplicitParam(name = "financeManageCashSerialListReqDTO", value = "查询条件", required = true, dataType = "FinanceManageCashSerialListReqDTO", paramType = "body")
    DataResult<FinanceManageCashSerialListRespDTO> getCashSerialEsList(@RequestBody FinanceManageCashSerialListReqDTO financeManageCashSerialListReqDTO);
}
