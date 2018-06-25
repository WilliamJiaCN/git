/**
 * 
 */
package com.hivescm.tms.finance.server.service.pcsign;

import com.hivescm.tms.api.dto.es.sign.response.WaybillTrackRespDTO;

/**
 * @author  boqiang.deng
 * @date    2018年4月10日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public interface OtherServerService {

	WaybillTrackRespDTO getWaybillTrackInfo(Long waybillId);
}
