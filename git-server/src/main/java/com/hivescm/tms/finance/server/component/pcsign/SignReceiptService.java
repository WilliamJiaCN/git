/**
 * 
 */
package com.hivescm.tms.finance.server.component.pcsign;

import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;

/**
 * 签收调用回单服务
 * @author  boqiang.deng
 * @date    2018年4月12日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public interface SignReceiptService {

	Boolean signReceipt(TmsSignEsDTO tmsSignEsDTO);
}
