/**
 * 
 */
package com.hivescm.tms.finance.server.feign.track;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.waybill.VehicleTailAfterEsDTO;

/**
 * @author  boqiang.deng
 * @date    2018年5月9日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@FeignClient(value="${tms.bill.track.server.application.name}",path="${tms.bill.track.server.application.path}")
public interface IBillTrackingService {
	@PostMapping("/waybillTrack/add")
	DataResult<Boolean> add(@RequestBody VehicleTailAfterEsDTO record);
	@PostMapping("/waybillTrack/batchAdd")
	DataResult<Boolean> batchAdd(@RequestBody List<VehicleTailAfterEsDTO> record);
}
