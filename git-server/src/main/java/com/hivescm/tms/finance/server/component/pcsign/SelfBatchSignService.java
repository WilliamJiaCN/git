package com.hivescm.tms.finance.server.component.pcsign;

import com.hivescm.tms.api.dto.es.sign.request.SelfSignBatchReqDTO;

/**
 * 自提批量签收
 * @author  boqiang.deng
 * @date    2018年3月30日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public interface SelfBatchSignService {

	/**
	 * 自提批量签收
	 * @param req
	 * @return
	 */
	Boolean batchSign(SelfSignBatchReqDTO req);
}
