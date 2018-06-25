/**
 * 
 */
package com.hivescm.tms.finance.server.service.lock.impl;

import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.lock.DistributedLockService;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.framework.validation.annotation.Validated;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.IdempotencyConstants;
import com.hivescm.tms.finance.server.service.lock.SignLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  boqiang.deng
 * @date    2018年4月24日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Service
public class SignLockServiceImpl implements SignLockService{
	
	@Autowired
	private DistributedLockService lockService;

	@Validated
	public Boolean lockSign(@Required @Logger List<String> waybillCodes) {
		try {
			return this.lockService.lock(waybillCodes);
		} catch (Exception e) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SIGN_LOCK,e, "签收模块锁定运单失败，运单号:%s",waybillCodes.toString());
		}
	}
	
	@Validated
	public Boolean unlockSign(@Required @Logger List<String> waybillCodes) {
		try {
			return this.lockService.unlock( waybillCodes);
		} catch (Exception e) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SIGN_LOCK,e, "签收模块解锁运单失败，运单号:%s",waybillCodes);
		}
	}

	@Override
	public Boolean signRecommitLock(Object waybillId) {
		return this.lockService.lock( IdempotencyConstants.BIZ_TMS_SIGN_TYPE_RECOMMIT +waybillId.toString(),waybillId.toString());
	}

	@Override
	public Boolean signRecommitUnLock(Object waybillId) {


		return this.lockService.unlock(IdempotencyConstants.BIZ_TMS_SIGN_TYPE_RECOMMIT +waybillId.toString(),waybillId.toString());
	}
	@Override
	public Boolean deliverySignLock(Object DispatcherDetailId){
		return lockService.lock(IdempotencyConstants.BIZ_TMS_DELIVERY_DEMPOTENCY+DispatcherDetailId.toString());
	}
	@Override
	public Boolean deliverySignUnLock(Object DispatcherDetailId){
		return lockService.unlock(IdempotencyConstants.BIZ_TMS_DELIVERY_DEMPOTENCY+DispatcherDetailId.toString());
	}
	@Override
	public Boolean deliveryCancelSignLock(Object signID){
		return lockService.lock(IdempotencyConstants.BIZ_TMS_DELIVERY_CANCEL_SIGN_DEMPOTENCY+signID.toString());
	}
	@Override
	public Boolean deliveryCancelSignUnLock(Object signID){
		return lockService.unlock(IdempotencyConstants.BIZ_TMS_DELIVERY_CANCEL_SIGN_DEMPOTENCY+signID.toString());
	}


}
