package com.hivescm.tms.finance.server.feign.outbill;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.outbill.req.OutBillAddReqDTO;


@FeignClient(value = "${tms.dispatcher.manager.server.application.name}", path = "${tms.dispatcher.manager.server.application.path}")
public interface OutbillService {
	
	@GetMapping("/outBill/queryOutBill/{outbillId}")
	public DataResult<OutBillAddReqDTO> queryOutBill(@PathVariable("outbillId") Long outbillId);

}
