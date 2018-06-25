/**
 * 
 */
package com.hivescm.tms.finance.server.interfaces.pcsign;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author  boqiang.deng
 * @date    2018年4月24日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Api(description = "签收锁定服务")
public interface SignLockControllerDoc {

	/**
	 * 签收模块锁定运单
	 * @param signIds
	 * @return
	 */
	@ApiOperation(value = "签收锁定运单", notes = "签收锁定运单")
	DataResult<?> lockSign(@RequestBody List<String> waybillCodes);
	
	/**
	 * 签收解锁运单
	 * @param signIds
	 * @return
	 */
	@ApiOperation(	value = "签收解锁运单", notes = "签收解锁运单")
	DataResult<?> unlockSign(@RequestBody List<String> waybillCodes);
}
