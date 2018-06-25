package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.finance.component.FinanceReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceReceiptReqDTO;

/**
 *
 * @author 杨彭伟
 * @date 2018-01-03 17:09
 */
public interface EsReceiptService {

    PagedList<FinanceReceiptEsDTO> getReceiptList(FinanceReceiptReqDTO financeReceiptReqDTO);
}
