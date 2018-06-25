package com.hivescm.tms.finance.server.interfaces.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageGrantListRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 货款管理-货款发货
 *
 * @author wenxiong.jia
 * @since 2018/5/7
 */
@Api(value = "FinanceManageGrant", description = "货款发放")
public interface FinanceManageGrantControllerDoc {
    //查询列表
    @ApiOperation(value = "查询列表", notes = "查询列表")
    @ApiImplicitParam(name = "financeManageGrantListReqDTO", value = "查询条件", required = true, dataType = "FinanceManageGrantListReqDTO", paramType = "body")
    DataResult<FinanceManageGrantListRespDTO> getEsList(@RequestBody FinanceManageGrantListReqDTO financeManageGrantListReqDTO);

    //发放确认
    @ApiOperation(value = "查询发放确认列表", notes = "查询发放确认列表")
    @ApiImplicitParam(name = "financeManageGrantConfirmQueryReqDTO", value = "查询条件", required = true, dataType = "FinanceManageGrantConfirmQueryReqDTO", paramType = "body")
    DataResult<List<FinanceManageGoodsGrantEsDTO>> getEsList(@RequestBody FinanceManageGrantConfirmQueryReqDTO financeManageGrantConfirmQueryReqDTO);

    //查询发放确认列表
    @ApiOperation(value = "查询发放确认列表", notes = "查询发放确认列表")
    @ApiImplicitParam(name = "financeManageGrantConfirmQueryReqDTO", value = "查询条件", required = true, dataType = "FinanceManageGrantConfirmQueryReqDTO", paramType = "body")
    DataResult<List<FinanceManageGoodsGrantEsDTO>> getEsForGrantConfirmList(@RequestBody FinanceManageGrantConfirmQueryReqDTO financeManageGrantConfirmQueryReqDTO);

    //查询运单号
    @ApiOperation(value = "查询运单号", notes = "查询运单号")
    @ApiImplicitParam(name = "financeManageGrantCodeReqDTO", value = "查询条件", required = true, dataType = "FinanceManageGrantCodeReqDTO", paramType = "body")
    DataResult<List<FinanceManageGoodsGrantEsDTO>> getCodeByGrant(@RequestBody FinanceManageGrantCodeReqDTO financeManageGrantCodeReqDTO);

    //根据id快速添加
    @ApiOperation(value = "根据id快速添加", notes = "根据id快速添加")
    @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Long", paramType = "path")
    DataResult<List<FinanceManageGoodsGrantEsDTO>> getEsByCodeForGrant(@PathVariable("id") Long id);

    //货款发放确认
    @ApiOperation(value = "发放确认", notes = "发放确认")
    @ApiImplicitParam(name = "financeManageGrantConfirmReqDto", value = "请求对象", required = true, dataType = "FinanceManageGrantConfirmReqDTO", paramType = "body")
    DataResult<Boolean> grantConfirm(@RequestBody FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto);

    //货款发放付款取消->取消付款
    @ApiOperation(value = "取消付款", notes = "取消付款")
    @ApiImplicitParam(name = "financeManagePayCancelReqDto", value = "请求对象", required = true, dataType = "FinanceManagePayCancelReqDTO", paramType = "body")
    DataResult<Boolean> cancelGrantPay(@RequestBody FinanceManagePayCancelReqDTO financeManagePayCancelReqDto);

    //货款手续费收款取消->取消收款
    @ApiOperation(value = "取消收款", notes = "取消收款")
    @ApiImplicitParam(name = "financeManagePayCancelReqDto", value = "请求对象", required = true, dataType = "FinanceManagePayCancelReqDTO", paramType = "body")
    DataResult<Boolean> cancelGoodsFeeReceipt(@RequestBody FinanceManagePayCancelReqDTO financeManagePayCancelReqDto);

    //收单确认
    @ApiOperation(value = "收单确认", notes = "收单确认")
    @ApiImplicitParam(name = "financeManageGrantCommonReqDto", value = "请求对象", required = true, dataType = "FinanceManageGrantCommonReqDTO", paramType = "body")
    DataResult<Boolean> receiveConfirm(@RequestBody FinanceManageGrantCommonReqDTO financeManageGrantCommonReqDto);

    //进账确认
    @ApiOperation(value = "进账确认", notes = "进账确认")
    @ApiImplicitParam(name = "financeManageGrantCommonReqDto", value = "请求对象", required = true, dataType = "FinanceManageGrantCommonReqDTO", paramType = "body")
    DataResult<Boolean> incomeConfirm(@RequestBody FinanceManageGrantCommonReqDTO financeManageGrantCommonReqDto);
}
