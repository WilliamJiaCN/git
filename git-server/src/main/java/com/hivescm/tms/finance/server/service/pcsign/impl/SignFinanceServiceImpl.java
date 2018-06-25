/**
 * 
 */
package com.hivescm.tms.finance.server.service.pcsign.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hivescm.framework.message.service.MessageService;
import com.hivescm.tms.api.dto.es.finance.FinanceSendMqDTO;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.finance.CalculationFeeEnum;
import com.hivescm.tms.api.enums.finance.OperateTypeEnum;
import com.hivescm.tms.finance.server.service.pcsign.SignFinanceService;
import com.hivescm.tms.utils.JsonUtil;

/**
 * @author  boqiang.deng
 * @date    2018年5月10日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Service
public class SignFinanceServiceImpl implements SignFinanceService{
	private Logger logger = LoggerFactory.getLogger(SignFinanceServiceImpl.class);

	@Autowired
	private MessageService messageService;
	
	// 消息队列
	@Value(value = "${tms.finance.data.source}")
	private String queueName;
	
	public void sendMq(List<Long> waybillIds,int status){
		FinanceSendMqDTO financeSendMqDTO = new FinanceSendMqDTO();
		financeSendMqDTO.setIds(waybillIds);
 		financeSendMqDTO.setBusinessType(CalculationFeeEnum.SIGN.getType());
 		// 签收逻辑
 		if(SignStatusEnum.SIGNED.getType() == status){
 			financeSendMqDTO.setOperateType(OperateTypeEnum.INSERT.getType());
 		}
 		// 取消签收逻辑
 		if(SignStatusEnum.CANCEL_SIGN.getType() == status){
 			financeSendMqDTO.setOperateType(OperateTypeEnum.DELETE.getType());
 		}
 		messageService.send(queueName, JsonUtil.GsonString(financeSendMqDTO));
 		logger.error("应收mq消息内容billingRecordReq"+financeSendMqDTO.toString());
	}
}
