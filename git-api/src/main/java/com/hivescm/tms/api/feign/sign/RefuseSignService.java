package com.hivescm.tms.api.feign.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 拒收单开放接口
 * @author 杨彭伟
 * @date 2017-11-23 14:44
 */
@FeignClient(value = FeignEnvironment.FINANCE_SERVER_NAME, path = FeignEnvironment.FINANCE_SERVER_PATH)
public interface RefuseSignService {

    @GetMapping("/sign/refuse/cancel/{companyId}/{waybillCode}")
    DataResult<Boolean> cancelRefuseSign(@PathVariable("waybillCode") String waybillCode,
                                         @PathVariable("companyId") @Deprecated Long companyId);
}
