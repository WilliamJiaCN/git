package com.hivescm.tms.finance.server.component.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.intranet.gateway.api.dto.boss.receipt.IReceiptInfoDTO;

/**
 * boss收款单 mock接口
 * @author 杨彭伟
 * @date 2017-11-30 16:29
 */
public interface ReceiptFeignService {
    /**
     * 生成收款单
     * @param dto
     * @return
     */
    DataResult<IReceiptInfoDTO> createReceipt(IReceiptInfoDTO dto);
}
