package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceSendMqDTO;

public interface MsgService {

	void sendMsg(FinanceSendMqDTO financeSendMqDto);

}
