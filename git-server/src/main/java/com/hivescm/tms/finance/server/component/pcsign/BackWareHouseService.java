package com.hivescm.tms.finance.server.component.pcsign;

import com.hivescm.tms.api.dto.es.sign.request.BackWarehouseReqDTO;

/**
 * 送货签收-返回入库服务
 * @author  zouhx
 * @date    2018年6月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface BackWareHouseService {

	/**
	 * 返回入库接口
	 * @param backWarehouseReqDTO
	 * @return
	 */
	Boolean addBackWareHouse(BackWarehouseReqDTO backWarehouseReqDTO);
}
