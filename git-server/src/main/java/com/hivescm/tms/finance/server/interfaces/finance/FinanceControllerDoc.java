package com.hivescm.tms.finance.server.interfaces.finance;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.component.FinanceReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.ReceiptReqDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 
* <p>Title:FinanceControllerDoc </p>
* <p>Description:收款单 </p>
* <p>Company: 蜂网供应链（上海）有限公司</p> 
* @author 王小雪 
* @date 下午2:53:10
 */
@Api(value = "financeService", description = "财务管理服务")
public interface FinanceControllerDoc {
	
	//收款单列表查询，多条件，分页
	@ApiOperation(value = "收款单列表查询接口", notes = "适用于多条件")
	@ApiImplicitParam(value = "收款单列表查询", name = "receiptReqDTO", required = true, paramType = "body", dataType = "ReceiptReqDTO")
	DataResult<List<FinanceReceiptEsDTO>> getReceiptList(@RequestBody ReceiptReqDTO receiptReqDTO);
	
	@ApiOperation(value = "未收银列表查询接口", notes = "适用于多条件")
	@ApiImplicitParam(value = "未收银列表查询查询", name = "receiptReqDTO", required = true, paramType = "body", dataType = "ReceiptReqDTO")
	DataResult<List<FinanceReceiptEsDTO>> getNoReceiptList(@RequestBody ReceiptReqDTO receiptReqDTO);
	@ApiOperation(value = "已收银列表查询接口", notes = "适用于多条件")
	@ApiImplicitParam(value = "已收银列表查询", name = "receiptReqDTO", required = true, paramType = "body", dataType = "ReceiptReqDTO")
	DataResult<List<FinanceReceiptEsDTO>> getAlreadyReceiptList(@RequestBody ReceiptReqDTO receiptReqDTO);

	
}
