package com.hivescm.tms.finance.server.interfaces.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageListRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 财务管理-应付接口文档
 *
 * @author wenxiong.jia
 * @since  2018/4/27
 */
@Api(value = "FinanceManagePay", description = "财务管理应付")
public interface FinanceManagePayControllerDoc {

    @ApiOperation(value = "生成应付款信息", notes = "生成应付款信息")
    @ApiImplicitParam(name = "financeManagePayReqDto", value = "请求对象", required = true, dataType = "FinanceManagePayReqDTO", paramType = "body")
    DataResult<?> createPayInfo(@RequestBody FinanceManagePayReqDTO financeManagePayReqDto);

    @ApiOperation(value = "查询列表", notes = "查询列表")
    @ApiImplicitParam(name = "financeManageListReqDTO", value = "查询条件", required = true, dataType = "FinanceManageListReqDTO", paramType = "body")
    DataResult<FinanceManageListRespDTO> getEsList(@RequestBody FinanceManageListReqDTO financeManageListReqDTO);

    @ApiOperation(value = "查询新增付款列表", notes = "查询新增付款列表")
    @ApiImplicitParam(name = "financeManageListForPayReqDTO", value = "查询条件", required = true, dataType = "FinanceManageListForPayReqDTO", paramType = "body")
    DataResult<List<FinanceManagePayEsDTO>> getEsListForPay(@RequestBody FinanceManageListForPayReqDTO financeManageListForPayReqDTO);

    @ApiOperation(value = "查询来源单号", notes = "查询来源单号")
    @ApiImplicitParam(name = "financeSheetCodeReqDTO", value = "查询条件", required = true, dataType = "FinanceSheetCodeReqDTO", paramType = "body")
    DataResult<List<FinanceManagePayEsDTO>> getCodeForPay(@RequestBody FinanceSheetCodeReqDTO financeSheetCodeReqDTO);

    @ApiOperation(value = "通过id快速添加", notes = "通过id快速添加")
    @ApiImplicitParam(name = "id", value = "来源单号", required = true, dataType = "Long", paramType = "path")
    DataResult<List<FinanceManagePayEsDTO>> getEsBySheetCode(@PathVariable("id") Long id);
    
    @ApiOperation(value = "批量审核", notes = "批量审核")
    @ApiImplicitParam(name = "financeCheckReqDTO", value = "审核对象条件", required = true, dataType = "FinanceCheckReqDTO", paramType = "body")
    DataResult<Boolean> checkFee(@RequestBody FinanceCheckReqDTO financeCheckReqDTO);
    
    @ApiOperation(value = "批量过账", notes = "批量过账")
    @ApiImplicitParam(name = "financeCheckReqDTO", value = "过账对象条件", required = true, dataType = "FinanceCheckReqDTO", paramType = "body")
    DataResult<Boolean> postConfirme(@RequestBody FinanceCheckReqDTO financeCheckReqDTO);

    @ApiOperation(value = "批量取消审核", notes = "批量取消审核")
    @ApiImplicitParam(name = "financeCheckReqDTO", value = "审核对象条件", required = true, dataType = "FinanceCheckReqDTO", paramType = "body")
    DataResult<Boolean> cancleCheckFee(@RequestBody FinanceCheckReqDTO financeCheckReqDTO);

    @ApiOperation(value = "查询是否已审核", notes = "查询是否已审核")
    @ApiImplicitParam(name = "financeDeleteReqDTO", value = "来源单号", required = true, dataType = "FinanceDeleteReqDTO", paramType = "body")
    DataResult<Boolean> getPayStatusByCode(@RequestBody FinanceDeleteReqDTO financeDeleteReqDTO);

    @ApiOperation(value = "付款", notes = "付款")
    @ApiImplicitParam(name = "financeManagePayConfirmReqDto", value = "请求对象", required = true, dataType = "FinanceManagePayConfirmReqDTO", paramType = "body")
    DataResult<Boolean> payConfirm(@RequestBody FinanceManagePayConfirmReqDTO financeManagePayConfirmReqDto);

    @ApiOperation(value = "取消付款", notes = "取消付款")
    @ApiImplicitParam(name = "financeManagePayCancelReqDto", value = "请求对象", required = true, dataType = "FinanceManagePayCancelReqDTO", paramType = "body")
    DataResult<Boolean> cancelPay(@RequestBody FinanceManagePayCancelReqDTO financeManagePayCancelReqDto);

    @ApiOperation(value = "检查是否已审核", notes = "检查是否已审核")
    @ApiImplicitParam(name = "financeCancelReqDto", value = "请求对象", required = true, dataType = "FinanceCancelReqDTO", paramType = "body")
    DataResult<Boolean> checkPaymentStatusIsNoCheck(@RequestBody FinanceCancelReqDTO financeCancelReqDto);

    @ApiOperation(value = "根据批次号查询应付信息", notes = "根据批次号查询应付信息")
    @ApiImplicitParam(name = "financeQueryReqDTO", value = "请求对象", required = true, dataType = "FinanceQueryReqDTO", paramType = "body")
    DataResult<List<FinanceManagePayEsDTO>> getListByBatchCode(@RequestBody FinanceQueryReqDTO financeQueryReqDTO);
}
