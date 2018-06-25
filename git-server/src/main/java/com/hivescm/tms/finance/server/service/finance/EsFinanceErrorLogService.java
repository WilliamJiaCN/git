package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceErrorLogDTO;

public interface EsFinanceErrorLogService {

	Boolean insert(FinanceErrorLogDTO financeErrorLogDTO);
}
