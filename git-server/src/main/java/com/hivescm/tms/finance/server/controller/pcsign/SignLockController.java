/**
 * 
 */
package com.hivescm.tms.finance.server.controller.pcsign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.pcsign.SignLockControllerDoc;
import com.hivescm.tms.finance.server.service.lock.SignLockService;

/**
 * @author  boqiang.deng
 * @date    2018年4月24日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@RestController
@RequestMapping("/signLock")
public class SignLockController extends BaseController implements SignLockControllerDoc{

	@Autowired
	private SignLockService signLockService;
	@Override
	@PostMapping("/unlocksign")
	public DataResult<?> lockSign(@RequestBody List<String> waybillCodes) {
		Boolean result = false;
		try {
			result = this.signLockService.lockSign(waybillCodes);
		} catch (TmsBusinessException e) {
			logger.error("解锁运单记录失败，",e);
			throw ExceptionFactory.ex(e);
		}
		return DataResult.success(result);
	}

	@Override
	@PostMapping("/unlockSign")
	public DataResult<?> unlockSign(@RequestBody List<String> waybillCodes) {
		Boolean result = false;
		try {
			result = this.signLockService.unlockSign(waybillCodes);
		} catch (TmsBusinessException e) {
			logger.error("解锁运单记录失败，",e);
			throw ExceptionFactory.ex(e);
		}
		return DataResult.success(result);
	}

}
