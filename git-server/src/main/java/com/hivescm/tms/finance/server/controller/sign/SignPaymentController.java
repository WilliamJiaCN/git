package com.hivescm.tms.finance.server.controller.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.api.dto.es.sign.request.SignGetQRCodeReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignPaymentQueryStatusReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignGetQRCodeRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignPaymentQueryStatusRespDto;
import com.hivescm.tms.finance.server.component.sign.SignPaymentService;
import com.hivescm.tms.finance.server.interfaces.sign.SignPaymentControllerDoc;
import com.hivescm.tms.intranet.gateway.api.dto.pay.IPayNoticeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单支付Controller
 * 获取二维码,查询支付结果,异步接收支付结果
 *
 * @author lhf
 */
@Slf4j
@RestController
@RequestMapping("/signPayment")
public class SignPaymentController implements SignPaymentControllerDoc {
    @Autowired
    private SignPaymentService signPaymentService;

    /**
     * 签收时获取的支付二维码
     */
    @Override
    @PostMapping("/getPaymentQRCode")
    public DataResult<SignGetQRCodeRespDTO> getPaymentQRCode(@RequestBody SignGetQRCodeReqDTO signGetQRCodeReqDTO) {
        log.debug("获取签收支付二维码，请求参数:{}", signGetQRCodeReqDTO);
        if (signGetQRCodeReqDTO.getAmountMoney() == null) {
            return DataResult.faild(-1, "参数错误，金额不可为空");
        }
        try {
            return this.signPaymentService.getPaymentQRCode(signGetQRCodeReqDTO);
        } catch (SystemException e) {
            log.error("获取签收支付二维码失败", e);
            return DataResult.faild(e.getErrorCode(), e.getErrorReason());
        }
    }

    /**
     * 查询扫码支付是否成功付款
     */
    @Override
    @PostMapping("/queryPaymentStatusByOrderNo")
    public DataResult<SignPaymentQueryStatusRespDto> queryPaymentStatusByOrderNo(@RequestBody SignPaymentQueryStatusReqDTO signPaymentQueryStatusReqDTO) {
        log.debug("查询扫码支付是否成功付款，请求参数:{}", signPaymentQueryStatusReqDTO);
        try {
            return this.signPaymentService.queryPaymentStatusByOrderNo(signPaymentQueryStatusReqDTO);
        } catch (SystemException e) {
            log.error("查询扫码支付是否成功付款失败", e);
            return DataResult.faild(e.getErrorCode(), e.getErrorReason());
        }
    }

    /**
     * 接收支付结果通知
     */
    @Override
    @PostMapping("/payResultNotify")
    public DataResult<?> acceptPayResultNotify(@RequestBody IPayNoticeVo payNoticeVo) {
        log.info("接收支付结果通知，请求参数:{}", payNoticeVo);
        try {
            return DataResult.success(signPaymentService.acceptPayResultNotify(payNoticeVo));
        } catch (SystemException e) {
            log.error("接收支付结果通知失败, {}", e.getErrorReason());
            return DataResult.faild(8000, e.getErrorReason());
        }
    }
}
