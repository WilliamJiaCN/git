/**
 * 
 */
package com.hivescm.tms.finance.server.feign.stock;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.stock.request.StockLockReqDTO;


/**
 * @author  boqiang.deng
 * @date    2018年6月1日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@FeignClient(value = "${tms.bill.stock.server.application.name}", path = "${tms.bill.stock.server.application.path}")
public interface IStockLockService {

	@GetMapping("/stocklock/lock/{orgId}/{waybillId}")
	DataResult<Boolean> lock(@PathVariable("orgId") Long orgId,@PathVariable("waybillId") Long waybillId);
	@GetMapping("/stocklock/unlock/{orgId}/{waybillId}")
	DataResult<Boolean> unlock( @PathVariable("orgId") Long orgId, @PathVariable("waybillId") Long waybillId);
	@PostMapping("/stocklock/locks")
	DataResult<Boolean> locks(@RequestBody StockLockReqDTO req);
	@PostMapping("/stocklock/unlocks")
	DataResult<Boolean> unlocks(@RequestBody StockLockReqDTO req);
}
