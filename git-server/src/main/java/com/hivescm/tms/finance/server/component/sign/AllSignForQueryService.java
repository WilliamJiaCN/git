package com.hivescm.tms.finance.server.component.sign;

import com.hivescm.tms.api.dto.es.sign.request.SignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignQueryPageResponseDTO;

/**
 * 送货签收列表查询
 * @author 杨彭伟
 * @date 2017-12-07 13:59
 */
public interface AllSignForQueryService {

    /**
     * 送货签收列表查询
     * @param signQueryReqDTO
     * @return
     */
    SignQueryPageResponseDTO getSignPage(SignQueryReqDTO signQueryReqDTO);
}
