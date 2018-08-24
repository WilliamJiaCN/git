package com.hivescm.tms.api.feign.address;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.common.address.CloudGCReq;
import com.hivescm.tms.common.address.CloudGCRes;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * TMS地址库GIS服务
 * @author 
 * @date 2017年11月6日
 * @company 蜂网供应链管理（上海）有限公司
 */
@FeignClient(value = FeignEnvironment.ADDRESS_SERVER_NAME, path = FeignEnvironment.ADDRESS_SERVER_PATH)
public interface CloudGcService {
    /**
     * 获取文本地址坐标
     * @param
     * @return
     */	
    @PostMapping(value = "/cloudGC/parseAddress")
    DataResult<CloudGCRes> parseAddress(@RequestBody CloudGCReq cloudGCReq);
}
