package com.hivescm.tms.api.feign.capacity;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.order.request.OmsCancelOrderReqDTO;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * TMS订单服务
 * @author 
 * @date 2017年11月6日
 * @company 蜂网供应链管理（上海）有限公司
 */
@FeignClient(value = FeignEnvironment.CAPACITY_SERVER_NAME, path = FeignEnvironment.CAPACITY_SERVER_PATH)
public interface OrderService {
    /**
     * OMS取消订单
     * @param omsCancelOrderReqDTO
     * @return
     */
    @PostMapping(value = "/order/cancel")
    DataResult<Boolean> cancel(@RequestBody OmsCancelOrderReqDTO omsCancelOrderReqDTO);
}
