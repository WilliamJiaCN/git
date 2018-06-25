package com.hivescm.tms.api.feign.address;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.common.address.CityLineShortestReq;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * TMS地址库GIS服务
 *
 * 查询最短路径
 * @author 
 * @date 2017年11月22日
 * @company 蜂网供应链管理（上海）有限公司
 */
@FeignClient(value = FeignEnvironment.ADDRESS_SERVER_NAME, path = FeignEnvironment.ADDRESS_SERVER_PATH)
public interface CityLineShortestService {
    /**
     * 城市线路最短回路计算
     * @param cityLineShortestReq
     * @return
     */	
    @PostMapping(value = "/city/line/shortest")
    DataResult<?> shortest(@RequestBody CityLineShortestReq cityLineShortestReq);
}
