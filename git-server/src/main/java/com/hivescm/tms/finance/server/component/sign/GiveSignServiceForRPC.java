package com.hivescm.tms.finance.server.component.sign;

import com.hivescm.tms.api.dto.bossfreight.BillingRecordReq;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignForDetailsReqDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;

import java.math.BigDecimal;

/**
 * 送货签收远程调用服务
 * @author 杨彭伟
 * @date 2018-01-05 20:10
 */

public interface GiveSignServiceForRPC {
    void sendResultToOMS(SignFeeEsDTO fee, SignEsDTO signEsDTO, SignForDetailsReqDTO signForDetailsReqDTO,
                         WaybillEsDTO waybill);

    BillingRecordReq getDeliveryCharge(WaybillEsDTO waybillEsDTO, BigDecimal totalReceivable);

    void sendWaybillInfoToBoss(BillingRecordReq req);
}
