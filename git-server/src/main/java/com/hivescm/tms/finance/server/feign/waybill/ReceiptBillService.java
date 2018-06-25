/**
 * 
 */
package com.hivescm.tms.finance.server.feign.waybill;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.receipt.request.ReceiptStockSignReqDTO;
import com.hivescm.tms.api.dto.es.receipt.request.ReceiptStockSyncDTO;

/**
 * @author  boqiang.deng
 * @date    2018年4月12日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@FeignClient(value="${tms.bill.manager.application.name}",path="${tms.bill.manager.application.path}")
public interface ReceiptBillService {

	@PostMapping("/receipt/stock")
	public DataResult<Boolean> stock(@RequestBody ReceiptStockSyncDTO stockSync);
	
	@PostMapping("/receipt/signStatus")
	public DataResult<Boolean> signStatus(@RequestBody ReceiptStockSignReqDTO signReq);
}
