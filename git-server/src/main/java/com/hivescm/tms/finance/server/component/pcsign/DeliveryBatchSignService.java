package com.hivescm.tms.finance.server.component.pcsign;

import com.hivescm.tms.api.dto.es.sign.request.DeliverySignBatchReqDTO;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/3
 */
public interface DeliveryBatchSignService {
    Boolean batchSign(DeliverySignBatchReqDTO reqDTO);
}
