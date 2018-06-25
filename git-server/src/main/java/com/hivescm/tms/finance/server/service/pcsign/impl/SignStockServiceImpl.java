/**
 * 
 */
package com.hivescm.tms.finance.server.service.pcsign.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hivescm.framework.message.service.MessageService;
import com.hivescm.tms.api.dto.es.stock.StockLockSyncDTO;
import com.hivescm.tms.finance.server.service.pcsign.SignStockService;
import com.hivescm.tms.utils.JsonUtil;

/**
 * @author  boqiang.deng
 * @date    2018年6月1日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Service
public class SignStockServiceImpl implements SignStockService{

	@Autowired
	private MessageService messageService;
	
	// 消息队列
	@Value(value = "${tms.waybill.stock}")
	private String queueName;

	@Override
	public void sendMq(List<StockLockSyncDTO> stocks) {
		messageService.send(queueName, JsonUtil.GsonString(stocks));
	}
}
