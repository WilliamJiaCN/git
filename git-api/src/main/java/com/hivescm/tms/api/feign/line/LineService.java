package com.hivescm.tms.api.feign.line;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.line.req.LineListReqDTO;
import com.hivescm.tms.api.dto.es.line.resp.LineListRespDTO;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = FeignEnvironment.LINE_SERVER_NAME, path = FeignEnvironment.LINE_SERVER_PATH)
public interface LineService {
	@PostMapping("/lineDelivery/getLine")
	DataResult<List<LineListRespDTO>> getLine(@RequestBody LineListReqDTO lineListReqDTO);
}
