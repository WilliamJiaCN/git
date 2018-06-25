package com.hivescm.tms.api.feign.waybill;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.waybill.response.VehicleTailAfterRespDTO;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * TMS运单跟踪服务
 * @author qsk
 * @date 2017年11月21日
 * @company 蜂网供应链管理（上海）有限公司
 */
@FeignClient(value = FeignEnvironment.BILL_TRCK_SERVER_NAME, path = FeignEnvironment.BILL_TRCK_SERVER_PATH)
public interface WaybillTrackService {
    /**
     * 根据运单Id 获取运单跟踪信息
     * @param waybillId
     * @return
     */
    @GetMapping(value = "/waybillTrack/getTrackInfo/{waybillId}")
    DataResult<VehicleTailAfterRespDTO> getTrackInfo(@PathVariable("waybillId") Long waybillId);
    /**
     * 根据运单Id 获取运单跟踪信息
     * @param waybillCode
     * @return
     */
    @GetMapping(value = "/waybillTrack/getTrackInfoByWaybillCode/{waybillCode}")
    DataResult<VehicleTailAfterRespDTO> getTrackInfoByWaybillCode(@PathVariable("waybillCode") String waybillCode);
}
