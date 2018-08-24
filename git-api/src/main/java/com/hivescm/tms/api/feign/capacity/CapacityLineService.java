package com.hivescm.tms.api.feign.capacity;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.global.request.GetGlobalCarrierReq;
import com.hivescm.tms.api.dto.es.global.request.GlobalProviderReq;
import com.hivescm.tms.api.dto.es.global.response.GetGlobalCarrierResp;
import com.hivescm.tms.api.dto.es.line.resp.LineListRespDTO;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = FeignEnvironment.CAPACITY_SERVER_NAME, path = FeignEnvironment.CAPACITY_SERVER_PATH)
public interface CapacityLineService {
	@PostMapping("/globalServiceProviderConfig/getUnifyProvider")
	DataResult<List<GetGlobalCarrierResp>> getUnifyProvider(@RequestBody GetGlobalCarrierReq getGlobalCarrierReq);
	
	@PostMapping("/globalServiceProviderConfig/getLineInfo")
	DataResult<List<LineListRespDTO>> getLineInfo(@RequestBody GlobalProviderReq globalProviderReq);
}
