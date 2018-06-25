/**
 * 
 */
package com.hivescm.tms.finance.server.service.pcsign.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hivescm.tms.api.dto.bossfreight.BillingRecordReq;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.finance.OperateTypeEnum;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.pcsign.SignBillingFlowService;

/**
 * @author  boqiang.deng
 * @date    2018年5月14日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Service
public class SignBillingFlowServiceImpl implements SignBillingFlowService {
	private Logger logger = LoggerFactory.getLogger(SignBillingFlowServiceImpl.class);
	@Autowired
	private WaybillService waybillService;
	@Override
	public Boolean billingFlow(List<Long> waybillIds,Integer status) {
		if(CollectionUtils.isEmpty(waybillIds)){
			return true;
		}
		waybillIds.forEach(a->{
			BillingRecordReq billingRecordReq = new BillingRecordReq();
			billingRecordReq.setWaybillId(a);
			if(SignStatusEnum.CANCEL_SIGN.getType() == status.intValue()){
				billingRecordReq.setDiscardTime(System.currentTimeMillis());
				billingRecordReq.setOrderBussType(OperateTypeEnum.DELETE.getType());
			}else{
				billingRecordReq.setSignTime(System.currentTimeMillis());
				billingRecordReq.setOrderBussType(OperateTypeEnum.INSERT.getType());
			}
			waybillService.sendWaybillInfoToBossNew(billingRecordReq);
			logger.error("计费流水信息billingRecordReq"+billingRecordReq.toString());
		});
		return true;
	}
}
