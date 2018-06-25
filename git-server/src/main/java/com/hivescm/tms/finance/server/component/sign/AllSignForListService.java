package com.hivescm.tms.finance.server.component.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;

import java.util.List;

public interface AllSignForListService {
	

	/**
	 * 查询签收单商品详情
	 * @param signId
	 * @return
	 */
	DataResult<List<SignDetailsEsDTO>> getSignGoodsDetails(Long signId);

	/**
	 * 送货签收商品详情
	 * @param type
	 * @param signId
	 * @return
	 */
    DataResult<List<SignDetailsEsDTO>> getGoodsDetails(Integer type, Long signId);
}
