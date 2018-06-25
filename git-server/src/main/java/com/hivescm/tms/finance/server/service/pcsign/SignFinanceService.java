/**
 * 
 */
package com.hivescm.tms.finance.server.service.pcsign;

import java.util.List;

/**
 * @author  boqiang.deng
 * @date    2018年5月10日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public interface SignFinanceService {

	/**
	 * 
	 * @param ids 运单id
	 * @param status 签收状态
	 */
	void sendMq(List<Long> waybillIds,int status);
}
