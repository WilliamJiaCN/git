package com.hivescm.tms.finance.server.component.pcsign;

import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.CancelDeliverySignBatchReqDTO;

public interface DeliverySignService {


    /**
     * 送货签收 -- 签收
     * 签收类型为：全部签收、部分签收、全部拒签调用此接口
     * @param tmsSignEsDTO
     * @return
     */
    Boolean deliverySign(TmsSignEsDTO tmsSignEsDTO);

    /**
     * 送货签收 -- 派送失败(独立签收接口)
     * @param tmsSignEsDTO
     * @return
     */
    Boolean deliveryFailure(TmsSignEsDTO tmsSignEsDTO);

    /**
     * 送货签收 -- 取消签收
     * @param cancelDeliverySignBatchReqDTO
     * @return
     */
    Boolean cancelDeliverySign( CancelDeliverySignBatchReqDTO cancelDeliverySignBatchReqDTO);

}
