package com.hivescm.tms.finance.server.component.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.request.SignGetQRCodeReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignPaymentQueryStatusReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignGetQRCodeRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignPaymentQueryStatusRespDto;
import com.hivescm.tms.intranet.gateway.api.dto.pay.IPayNoticeVo;

public interface SignPaymentService {
	/**
	 * 获取支付二维码
	 * @param signGetQRCodeReqDTO
	 * @return  SignGetQRCodeRespDTO
	 */
	DataResult<SignGetQRCodeRespDTO> getPaymentQRCode(SignGetQRCodeReqDTO signGetQRCodeReqDTO);

	/**
	 * 查询扫码支付是否成功付款
	 * @param signPaymentQueryStatusReqDTO
	 * @return
	 */
	DataResult<SignPaymentQueryStatusRespDto> queryPaymentStatusByOrderNo(SignPaymentQueryStatusReqDTO signPaymentQueryStatusReqDTO);
	/**
	 * 接收支付结果异步通知服务
	 * @param payNoticeVo
	 * @return
	 */
	Boolean acceptPayResultNotify(IPayNoticeVo payNoticeVo);

	/**
	 * 该运单是否已经支付成功
     * @author 杨彭伟
     * @date 2018-1-9 17:32
	 * @param waybillId 运单id
	 * @return
	 */
    Boolean isPaySuccess(Long waybillId);
}
