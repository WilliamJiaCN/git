package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.framework.message.service.MessageService;
import com.hivescm.tms.api.dto.es.finance.FinanceSendMqDTO;
import com.hivescm.tms.finance.server.service.finance.MsgService;
import com.hivescm.tms.utils.DTOToMapUtils;
import com.hivescm.tms.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MsgServiceImpl implements MsgService {

	@Autowired
	private MessageService messageService;

	@Value("${tms.finance.data.source}")
	private String queueName;

	@Override
	public void sendMsg(FinanceSendMqDTO financeSendMqDto) {
        HashMap<String, Object> ret = DTOToMapUtils.getMap(financeSendMqDto);
		messageService.send(queueName, JsonUtil.GsonString(ret));
	}
}
