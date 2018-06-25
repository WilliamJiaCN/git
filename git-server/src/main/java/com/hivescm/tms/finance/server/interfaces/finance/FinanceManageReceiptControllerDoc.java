package com.hivescm.tms.finance.server.interfaces.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptUpdateSingleDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageReceiptListRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value ="financeManageReceipt",description ="财务管理应收" )
public interface FinanceManageReceiptControllerDoc {

    //单条增
//    @ApiOperation(value = "应收添加",notes = "应收添加")
//    @ApiImplicitParam(value = "应收添加", name = "financeManageReceiptEsDTO", required = true, paramType = "body", dataType = "FinanceManageReceiptEsDTO")
//    DataResult<Boolean> insertSingle(@RequestBody FinanceManageReceiptEsDTO financeManageReceiptEsDTO);

    //批量增
    @ApiOperation(value = "应收批量添加",notes = "应收批量添加")
    @ApiImplicitParam(value = "应收批量添加", name = "saveFinanceManageReceiptReqDTO", required = true, paramType = "body", dataType = "SaveFinanceManageReceiptReqDTO")
    DataResult<Boolean> insertBatch(@RequestBody SaveFinanceManageReceiptReqDTO saveFinanceManageReceiptReqDTO);

    //批量修改
    @ApiOperation(value = "批量修改",notes = "批量修改")
    @ApiImplicitParam(value = "批量修改", name = "updateBatch", required = true, paramType = "body", dataType = "FinanceManageReceiptUpdateSingleDTO")
    DataResult updateBatch(@RequestBody FinanceManageReceiptUpdateSingleDTO financeManageReceiptUpdateSingleDTO);

    //查询列表
    @ApiOperation(value = "查询列表", notes = "查询列表")
    @ApiImplicitParam(name = "financeManageReceiptListReqDTO", value = "查询条件", required = true, dataType = "FinanceManageReceiptListReqDTO", paramType = "body")
    DataResult<FinanceManageReceiptListRespDTO> getEsList(@RequestBody FinanceManageReceiptListReqDTO financeManageReceiptListReqDTO);

    //查询新增收款列表
    @ApiOperation(value = "查询新增收款列表", notes = "查询新增收款列表")
    @ApiImplicitParam(name = "financeManageListForReceiptReqDTO", value = "查询新增收款列表", required = true, dataType = "FinanceManageListForReceiptReqDTO", paramType = "body")
    DataResult<List<FinanceManageReceiptEsDTO>> getEsForReceiptList(@RequestBody FinanceManageListForReceiptReqDTO financeManageListForReceiptReqDTO);

    //查询来源单号
    @ApiOperation(value = "查询来源单号",notes = "查询来源单号")
    @ApiImplicitParam(name="financeReceiptCodeReqDTO",value = "查询来源单号",required = true,dataType = "FinanceReceiptCodeReqDTO",paramType = "body")
    DataResult<List<FinanceManageReceiptEsDTO>> getCodeByReceipt(@RequestBody FinanceReceiptCodeReqDTO financeReceiptCodeReqDTO);

    //根据来源单号快速添加
    @ApiOperation(value = "根据id快速添加",notes = "根据id快速添加")
    @ApiImplicitParam(name="id",value = "根据id快速添加",required = true,dataType = "Long",paramType = "path")
    DataResult<List<FinanceManageReceiptEsDTO>> getEsByCodeForReceipt(@PathVariable("id") Long id);

    @ApiOperation(value = "查询单条信息", notes = "查询单条信息")
    @ApiImplicitParam(name = "financeManageReceiptReqDTO", value = "查询条件", required = true, dataType = "FinanceManageReceiptReqDTO", paramType = "body")
    DataResult<FinanceManageReceiptEsDTO> findFinanceManageReceipt(@RequestBody FinanceManageReceiptReqDTO financeManageReceiptReqDTO);

    @ApiOperation(value = "根据运单号或者中转单号删除", notes = "根据运单号或者中转单号删除")
    @ApiImplicitParam(name = "financeDeleteReqDTO", value = "删除条件", required = true, dataType = "FinanceDeleteReqDTO", paramType = "body")
    DataResult<Boolean> deleteByCode(@RequestBody FinanceDeleteReqDTO financeDeleteReqDTO);
    
    @ApiOperation(value = "根据单号或者中转单号查询是否有付款", notes = "根据单号或者中转单号查询是否有付款")
    @ApiImplicitParam(name = "financeDeleteReqDTO", value = "查询条件", required = true, dataType = "FinanceDeleteReqDTO", paramType = "body")
    DataResult<Boolean> getPayStatusByCode(@RequestBody FinanceDeleteReqDTO financeDeleteReqDTO);
    
  //单条增
    @ApiOperation(value = "审核",notes = "审核")
    @ApiImplicitParam(value = "审核", name = "verifyFinanceReceiptReqDTO", required = true, paramType = "body", dataType = "VerifyFinanceReceiptReqDTO")
    DataResult<Boolean> verify(@RequestBody VerifyFinanceReceiptReqDTO verifyFinanceReceiptReqDTO);
    
    @ApiOperation(value = "确认收银",notes = "确认收银")
    @ApiImplicitParam(value = "收银", name = "financeSaveReceiptDTO", required = true, paramType = "body", dataType = "FinanceSaveReceiptDTO")
    DataResult<Boolean> saveReceipt(@RequestBody FinanceSaveReceiptDTO financeSaveReceiptDTO);
    
    @ApiOperation(value = "取消收银",notes = "取消收银")
    @ApiImplicitParam(value = "取消收银", name = "financeCancleReceiptDTO", required = true, paramType = "body", dataType = "FinanceCancleReceiptDTO")
    DataResult<Boolean> cancleReceipt(@RequestBody FinanceCancleReceiptDTO financeCancleReceiptDTO);
    
}
