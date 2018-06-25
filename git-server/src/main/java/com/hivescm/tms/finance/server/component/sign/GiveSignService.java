package com.hivescm.tms.finance.server.component.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.request.SignForDetailsReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignBillEsRespDTO;

/**
 * @author 杨彭伟
 * @date 2018-01-30 15:19
 */
public interface GiveSignService {
    SignBillEsRespDTO insertSign(SignForDetailsReqDTO signForDetailsReqDTO);

    DataResult<Boolean> reCommitToOMS(Long waybillId, Integer commitType);
}
