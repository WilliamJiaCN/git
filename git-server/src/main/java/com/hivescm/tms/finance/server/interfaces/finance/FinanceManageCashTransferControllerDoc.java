package com.hivescm.tms.finance.server.interfaces.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "FinanceManageCashTransfer",description = "现金转账")
public interface FinanceManageCashTransferControllerDoc {
    //查询列表
    @ApiOperation(value = "查询现金转账列表", notes = "查询现金转账列表")
    @ApiImplicitParam(name = "financeManageCashTransferListReqDTO", value = "查询条件", required = true, dataType = "FinanceManageCashTransferListReqDTO", paramType = "body")
    DataResult<FinanceManageCashTransferListRespDTO> getEsList(@RequestBody FinanceManageCashTransferListReqDTO financeManageCashTransferListReqDTO);
    //查询回收确认列表
    @ApiOperation(value = "查询新增转账列表", notes = "查询新增转账列表")
    @ApiImplicitParam(name = "financeManageCashTransferAddReqDTO", value = "查询条件", required = true, dataType = "FinanceManageCashTransferAddReqDTO", paramType = "body")
    DataResult<List<FinanceManageCashTransferAddRespDTO>> getEsForTransferAddList(@RequestBody FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO);

    //查询运单号
    @ApiOperation(value = "查询来源单号",notes = "查询来源单号")
    @ApiImplicitParam(name="financeManageCashTransferCodeReqDTO",value = "查询条件",required = true,dataType = "FinanceManageCashTransferCodeReqDTO",paramType = "body")
    DataResult<List<FinanceManageCashTransferAddRespDTO>> getCodeByTransfer(@RequestBody FinanceManageCashTransferCodeReqDTO financeManageCashTransferCodeReqDTO);

    //根据id快速添加
    @ApiOperation(value = "根据id快速添加",notes = "根据id快速添加")
    @ApiImplicitParam(name="id",value = "主键id",required = true,dataType = "Long",paramType = "path")
    DataResult<List<FinanceManageCashTransferAddRespDTO>> getEsByCodeForTransfer(@PathVariable("id") Long id);

    //转账提交接口
    @ApiOperation(value = "转账提交接口",notes = "转账提交接口")
    @ApiImplicitParam(name="transferAccountsReqDTO",value = "转账提交入参",required = true,dataType = "FinanceManageCashTransferReqDTO",paramType = "body")
    DataResult<Boolean> commit(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO);

    //转账取消提交接口
    @ApiOperation(value = "转账取消提交接口",notes = "转账取消提交接口")
    @ApiImplicitParam(name="transferAccountsReqDTO",value = "转账提交入参",required = true,dataType = "FinanceManageCashTransferReqDTO",paramType = "body")
    DataResult<Boolean> cancelCommit(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO);

    //进账接口
    @ApiOperation(value = "进账接口",notes = "进账接口")
    @ApiImplicitParam(name="transferAccountsReqDTO",value = "转账提交入参",required = true,dataType = "FinanceManageCashTransferReqDTO",paramType = "body")
    DataResult<Boolean> intoAccount(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO);

    //删除接口
    @ApiOperation(value = "删除接口",notes = "删除接口")
    @ApiImplicitParam(name="transferAccountsReqDTO",value = "删除接口入参",required = true,dataType = "FinanceManageCashTransferReqDTO",paramType = "body")
    DataResult<Boolean> delete(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO);
    //修改接口
    @ApiOperation(value = "修改接口",notes = "修改接口")
    @ApiImplicitParam(name="transferAccountsReqDTO",value = "修改接口入参",required = true,dataType = "FinanceManageCashTransferReqDTO",paramType = "body")
    DataResult<Boolean> update(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO);
    //增加
    @ApiOperation(value = "增加",notes = "增加")
    @ApiImplicitParam(name="financeManageCashTransferInsertReqDTO",value = "增加",required = true,dataType = "FinanceManageCashTransferInsertReqDTO",paramType = "body")
    DataResult<Boolean> insertSingle(@RequestBody FinanceManageCashTransferInsertReqDTO financeManageCashTransferInsertReqDTO);

    //根据id详情查询
    @ApiOperation(value = "详情查询",notes = "详情查询")
    @ApiImplicitParam(name="id",value = "主键id",required = true,dataType = "Long",paramType = "path")
    DataResult<FinanceManageCashTransferDetailsRespDTO> getDetails(@PathVariable("id") Long id);

    //打印主表接口
    @ApiOperation(value = "打印主表",notes = "打印主表")
    @ApiImplicitParam(name="req",value = "打印主表",required = true,dataType = "FinanceManagePrintCash",paramType = "body")
    DataResult<FinanceManagePrintTransferRsepDTO> printCash(@RequestBody FinanceManagePrintCash req);

    //打印明细接口
    @ApiOperation(value = "打印明细",notes = "打印明细")
    @ApiImplicitParam(name="req",value = "打印明细",required = true,dataType = "FinanceManagePrintCash",paramType = "body")
    DataResult <List<FinanceManagePrintTransferDteailsRsepDTO>> printCashDetails(@RequestBody FinanceManagePrintCashDetails req);
}
