package com.hivescm.tms.finance.server.interfaces.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageRecycleListRespDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 货款管理-货款回收文档
 *
 * @author wangqianqian
 * @since  2018/5/7
 */
@Api(value = "FinanceManageGoodsRecycleController", description = "货款回收")
public interface FinanceManageGoodsRecycleControllerDoc {

	@ApiOperation(value = "确认回收",notes = "确认回收")
    @ApiImplicitParam(value = "确认回收", name = "financeSaveRecycleDTO", required = true, paramType = "body", dataType = "FinanceSaveRecycleDTO")
	DataResult<Boolean> saveRecycle(@RequestBody FinanceSaveRecycleDTO financeSaveRecycleDTO);
	
	@ApiOperation(value = "取消回收",notes = "取消回收")
	@ApiImplicitParam(value = "取消回收", name = "financeCancleReceiptDTO", required = true, paramType = "body", dataType = "FinanceCancleReceiptDTO")
	DataResult<Boolean> cancelRecycle(@RequestBody FinanceCancleReceiptDTO financeCancleReceiptDTO);
	//查询列表
	@ApiOperation(value = "查询列表", notes = "查询列表")
	@ApiImplicitParam(name = "financeManageRecycleListReqDTO", value = "查询条件", required = true, dataType = "FinanceManageRecycleListReqDTO", paramType = "body")
	DataResult<FinanceManageRecycleListRespDTO> getEsList(@RequestBody FinanceManageRecycleListReqDTO financeManageRecycleListReqDTO);
	//查询回收确认列表
	@ApiOperation(value = "查询回收确认列表", notes = "查询回收确认列表")
	@ApiImplicitParam(name = "financeManageRecycleConfirmQueryReqDTO", value = "查询条件", required = true, dataType = "FinanceManageRecycleConfirmQueryReqDTO", paramType = "body")
	DataResult<List<FinanceManageGoodsRecycleEsDTO>> getEsForRecycleConfirmList(@RequestBody FinanceManageRecycleConfirmQueryReqDTO financeManageRecycleConfirmQueryReqDTO);

	//查询运单号
	@ApiOperation(value = "查询运单号",notes = "查询运单号")
	@ApiImplicitParam(name="financeManageRecycleCodeReqDTO",value = "查询条件",required = true,dataType = "FinanceManageRecycleCodeReqDTO",paramType = "body")
	DataResult<List<FinanceManageGoodsRecycleEsDTO>> getCodeByRecycle(@RequestBody FinanceManageRecycleCodeReqDTO financeManageRecycleCodeReqDTO);

	//根据id快速添加
	@ApiOperation(value = "根据id快速添加",notes = "根据id快速添加")
	@ApiImplicitParam(name="id",value = "主键id",required = true,dataType = "Long",paramType = "path")
	DataResult<List<FinanceManageGoodsRecycleEsDTO>> getEsByCodeForRecycle(@PathVariable("id") Long id);

	@ApiOperation(value = "货款回收之汇款",notes = "货款回收之汇款")
	@ApiImplicitParam(name="financeManageRecycleRemitReqDTO",value = "查询条件",required = true,dataType = "FinanceManageRecycleRemitReqDTO",paramType = "body")
	DataResult<Boolean> financeManageRecycleRemit(@RequestBody FinanceManageRecycleRemitReqDTO financeManageRecycleRemitReqDTO);


}
