package com.hivescm.tms.finance.server.interfaces.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.request.SignGetQRCodeReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignPaymentQueryStatusReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignGetQRCodeRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignPaymentQueryStatusRespDto;
import com.hivescm.tms.intranet.gateway.api.dto.pay.IPayNoticeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "signPayment", description = "签收支付服务")
public interface SignPaymentControllerDoc {
	// 获取支付二维码
	@ApiOperation(value = "获取支付二维码")
	@ApiImplicitParam(value = "获取支付二维码", name = "signGetQRCodeReqDTO", required = true, paramType = "body", dataType = "SignGetQRCodeReqDTO")
	DataResult<SignGetQRCodeRespDTO> getPaymentQRCode(@RequestBody SignGetQRCodeReqDTO signGetQRCodeReqDTO);
	
	// 查询扫码支付状态如过二维码已失效需要重新生成支付二维码
	@ApiOperation(value = "查询扫码支付状态接口(如过二维码已失效需要重新生成支付二维码)")
	@ApiImplicitParam(value = "查询扫码支付状态", name = "signPaymentQueryStatusReqDTO", required = true, paramType = "body", dataType = "SignPaymentQueryStatusReqDTO")
	DataResult<SignPaymentQueryStatusRespDto> queryPaymentStatusByOrderNo(@RequestBody SignPaymentQueryStatusReqDTO signPaymentQueryStatusReqDTO);
	
	//接收支付结果异步通知
	@ApiOperation(value = "接收支付结果异步通知")
	@ApiImplicitParam(value = "接收支付结果异步通知", name = "payNoticeVo", required = true, paramType = "body", dataType = "PayNoticeVo")
	DataResult<?> acceptPayResultNotify(@RequestBody IPayNoticeVo payNoticeVo);
		
	
}
