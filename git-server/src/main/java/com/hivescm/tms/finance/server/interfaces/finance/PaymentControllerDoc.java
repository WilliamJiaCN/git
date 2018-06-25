package com.hivescm.tms.finance.server.interfaces.finance;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.component.FinancePaymentESDTO;
import com.hivescm.tms.api.dto.es.finance.request.PaymentReqDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
/**
 * 
* <p>Title:PaymentControllerDoc </p>
* <p>Description: 货款发放</p>
* <p>Company: 蜂网供应链（上海）有限公司</p> 
* @author 王小雪 
* @date 下午2:55:26
 */

@Api(value = "paymentService", description = "财务管理服务")
public interface PaymentControllerDoc {
	
	//收款单列表查询，多条件，分页
	@ApiOperation(value = "付款单列表查询接口", notes = "适用于多条件")
	@ApiImplicitParam(value = "付款单列表查询", name = "paymentReqDTO", required = true, paramType = "body", dataType = "PaymentReqDTO")
	DataResult<List<FinancePaymentESDTO>> getPaymentList(@RequestBody PaymentReqDTO paymentReqDTO);
	
	@ApiOperation(value = "未发放列表查询接口", notes = "适用于多条件")
	@ApiImplicitParam(value = "未发放列表查询查询", name = "paymentReqDTO", required = true, paramType = "body", dataType = "PaymentReqDTO")
	DataResult<List<FinancePaymentESDTO>> getNoPaymentList(@RequestBody PaymentReqDTO PaymentReqDTO);
	@ApiOperation(value = "已发放列表查询接口", notes = "适用于多条件")
	@ApiImplicitParam(value = "已发放列表查询", name = "paymentReqDTO", required = true, paramType = "body", dataType = "PaymentReqDTO")
	DataResult<List<FinancePaymentESDTO>> getAlreadyPaymentList(@RequestBody PaymentReqDTO paymentReqDTO);

	
}
