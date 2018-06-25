package com.hivescm.tms.finance.server.component.pcsign;

import java.util.List;

import com.hivescm.tms.api.dto.es.sign.component.OutboundCancelSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.component.OutboundSignReqDTO;

/**
 * 外发签收
 * @author  boqiang.deng
 * @date    2018年4月17日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public interface OutboundSignService {

	/**
	 * 外发签收保存数据
	 * @param outboundReq
	 * @return
	 */
	Boolean insert(List<OutboundSignReqDTO> outboundReq);
	
	/**
	 * 外发签收保存数据
	 * @param outboundCancelSignReqDTO
	 * @return
	 */
	Boolean cancel(OutboundCancelSignReqDTO outboundCancelSignReqDTO);
}
