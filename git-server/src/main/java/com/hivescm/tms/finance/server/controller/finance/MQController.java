package com.hivescm.tms.finance.server.controller.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceSendMqDTO;
import com.hivescm.tms.finance.server.service.finance.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class MQController {

	@Autowired
	private MsgService msgService;


	@PostMapping("/sendMsg")
	public void getReceiptList(@RequestBody FinanceSendMqDTO financeSendMqDto) {
		msgService.sendMsg(financeSendMqDto);
	}
}
