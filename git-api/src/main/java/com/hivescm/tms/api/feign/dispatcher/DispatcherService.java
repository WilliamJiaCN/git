package com.hivescm.tms.api.feign.dispatcher;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.dispatcher.towarehouse.Dispatcher2WarehouseDTO;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = FeignEnvironment.DISPATCHER_SERVER_NAME, path = FeignEnvironment.DISPATCHER_SERVER_PATH)
public interface DispatcherService {
	
	@PostMapping("/dispatcher/handleWms")
	public DataResult<Integer> handleByDispatcherId(@RequestBody Dispatcher2WarehouseDTO dispatcherDTO);
}
