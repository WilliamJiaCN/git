package com.hivescm.tms.finance.server.service.pcsign;

import java.util.List;

import com.hivescm.tms.api.dto.es.waybill.VehicleTailAfterEsDTO;

/**
 * @author  boqiang.deng
 * @date    2018年5月9日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public interface BillTrackingService {

	Boolean billTracking(VehicleTailAfterEsDTO record);
	
	Boolean billTracking(List<VehicleTailAfterEsDTO> record);
}
