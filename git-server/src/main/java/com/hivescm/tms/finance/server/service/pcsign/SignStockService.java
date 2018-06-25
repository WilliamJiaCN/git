/**
 * 
 */
package com.hivescm.tms.finance.server.service.pcsign;

import java.util.List;

import com.hivescm.tms.api.dto.es.stock.StockLockSyncDTO;

/**
 * @author  boqiang.deng
 * @date    2018年6月1日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public interface SignStockService {

	void sendMq(List<StockLockSyncDTO> stocks);
}
