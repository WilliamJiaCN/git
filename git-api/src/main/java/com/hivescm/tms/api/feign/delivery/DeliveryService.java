package com.hivescm.tms.api.feign.delivery;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.delivery.DeliveryLogisticsInfoDTO;
import com.hivescm.tms.api.dto.es.delivery.OrderDeliveryCodeInfoDTO;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 运力分配快递服务
 * @author 
 * @date 2018年02月05日
 * @company 蜂网供应链管理（上海）有限公司
 */
@FeignClient(value = FeignEnvironment.CAPACITY_SERVER_NAME, path = FeignEnvironment.CAPACITY_SERVER_PATH)
public interface DeliveryService {
    
    /**
     * 查询快递单物流信息
     * @param 
     * @return
     */
    @GetMapping(value = "/deliveryService/getDeliveryAcceptLogisticsInfo/{orderId}")
    DataResult<List<DeliveryLogisticsInfoDTO>> getDeliveryAcceptLogisticsInfo(@PathVariable("orderId") String orderId);
    
    /**
     * 接收快递单号
     * @param 
     * @return
     */
    @PostMapping(value = "/deliveryService/acceptDeliveryCode")
    DataResult<Boolean> acceptDeliveryCode(@RequestBody OrderDeliveryCodeInfoDTO req);
}
