package com.hivescm.tms.finance.server.component.sign;

import com.hivescm.tms.api.dto.es.sign.response.SignStatusRespDTO;

/**
 * 查看签收状态
 *
 * @author m5itao
 * @since 2017/12/4
 */
public interface SignStatusService {

    /**
     * 查询签收状态
     * @param waybillId
     * @return
     */
    SignStatusRespDTO getSignStatus(Long waybillId);

}
