/**
 * 
 */
package com.hivescm.tms.finance.server.service.pcsign.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.tms.api.dto.es.waybill.VehicleTailAfterEsDTO;
import com.hivescm.tms.finance.server.feign.track.IBillTrackingService;
import com.hivescm.tms.finance.server.service.pcsign.BillTrackingService;

/**
 * @author  boqiang.deng
 * @date    2018年5月9日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Service
public class BillTrackingServiceImpl implements BillTrackingService {

	@Autowired
	private IBillTrackingService iBillTrackingService;
	@Override
	public Boolean billTracking(VehicleTailAfterEsDTO record) {
		return iBillTrackingService.add(record).getResult();
	}

	@Override
	public Boolean billTracking(List<VehicleTailAfterEsDTO> record) {
		return iBillTrackingService.batchAdd(record).getResult();
	}

	
}
