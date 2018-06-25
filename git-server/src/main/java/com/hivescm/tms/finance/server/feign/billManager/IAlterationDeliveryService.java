package com.hivescm.tms.finance.server.feign.billManager;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.alteration.response.AlterationDeliveryChangeInfoRespDTO;
import com.hivescm.tms.api.dto.es.exception.response.WaybillExceptionGetByIDRespDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "${tms.bill.manager.application.name}", path = "${tms.bill.manager.application.path}")
public interface IAlterationDeliveryService {

	@GetMapping("/alterationDelivery/getChangeInfo/{id}")
	public DataResult<AlterationDeliveryChangeInfoRespDTO> getChangeInfo(@PathVariable("id")Long id);

	@PostMapping("/WayBillException/getByID/{id}")
	DataResult<WaybillExceptionGetByIDRespDTO> getByID(@PathVariable("id") Long id);
}
