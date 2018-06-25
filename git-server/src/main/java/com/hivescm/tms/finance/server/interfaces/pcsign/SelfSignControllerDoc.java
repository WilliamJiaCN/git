package com.hivescm.tms.finance.server.interfaces.pcsign;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.CancelSelfDeliverySignReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.QuerySignBySignBatchNumberReq;
import com.hivescm.tms.api.dto.es.sign.request.SearchSignCodeReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SelfSignBatchReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignInitializeWayBillReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignListRespDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
@Api(value="sign",description="签收服务")
public interface SelfSignControllerDoc {

	@ApiOperation(value = "自提签收未签收",notes = "适用于多条件")
	@ApiImplicitParam(value="未签收",name = "pcSignReq",required = true,paramType="body",dataType = "PCSignReq")
	DataResult<SignListRespDTO> getNoSign(@RequestBody PCSignReq pcSignReq);
	
	@ApiOperation(value = "自提签收已签收,部分签收列,拒签表查询",notes = "适用于多条件")
	@ApiImplicitParam(value="签收，拒签和部分签收请求体",name = "pcSignReq",required = true,paramType="body",dataType = "PCSignReq")
	DataResult<SignListRespDTO> getSignedAndBillPartialSignBill(@RequestBody PCSignReq pcSignReq);
	
	@ApiOperation(value = "自提签收查看")
	DataResult<TmsSignEsDTO> getSignBill(@RequestBody QuerySignBySignBatchNumberReq req);
	
	@ApiOperation(value = "自提签收新增初始化")
	@ApiImplicitParam(value="自提签收新增初始化实体",name = "req",required = true,paramType="body",dataType = "SignInitializeWayBillReqDTO")
	DataResult<TmsSignEsDTO> addInitialize(@RequestBody SignInitializeWayBillReqDTO req);
	
	@ApiOperation(value = "签收新增")
	@ApiImplicitParam(value="签收新增实体",name = "tmsSignEsDTO",required = true,paramType="body",dataType = "TmsSignEsDTO")
	DataResult<Boolean> insertSign(@RequestBody TmsSignEsDTO tmsSignEsDTO);
	
	@ApiOperation(value = "自提取消签收")
	@ApiImplicitParam(value="自提取消签收实体",name = "cancelSelfDeliverySignReqDTO",required = true,paramType="body",dataType = "CancelSelfDeliverySignReqDTO")
	DataResult<Boolean> cancelSelfDeliverySign(@RequestBody CancelSelfDeliverySignReqDTO cancelSelfDeliverySignReqDTO);
	
	@ApiOperation(value = "自提签收全部列表接口",notes = "适用于多条件")
	@ApiImplicitParam(value="全部列表收请求体",name = "pcSignReq",required = true,paramType="body",dataType = "PCSignReq")
	DataResult<SignListRespDTO> getAllBill(@RequestBody PCSignReq pcSignReq);

	@ApiOperation(value = "自提批量签收")
	@ApiImplicitParam(value="自提批量签收请求体",name = "req",required = true,paramType="body",dataType = "SelfSignBatchReqDTO")
	DataResult<Boolean>  batchSign(@RequestBody SelfSignBatchReqDTO req);
	
	@ApiOperation(value = "根据运单号查询签收批次")
	@ApiImplicitParam(value="根据运单号查询签收批次请求体",name = "req",required = true,paramType="body",dataType = "SearchSignCodeReqDTO")
	DataResult<List<String>> querySignNumber(@RequestBody SearchSignCodeReqDTO req);

}
