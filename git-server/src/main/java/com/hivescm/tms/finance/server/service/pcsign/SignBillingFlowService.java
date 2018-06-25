/**
 * 
 */
package com.hivescm.tms.finance.server.service.pcsign;

import java.util.List;

/**
 * @author  boqiang.deng
 * @date    2018年5月14日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public interface SignBillingFlowService {

	/**
	 * 计费流水接口
	 * 此接口的成功与失败不影响签收流程
	 * @param waybill
	 * @return
	 */
	Boolean billingFlow(List<Long> waybillids,Integer status);
}
