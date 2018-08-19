package com.hivescm.tms.api.feign.waybill;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.packageinfo.request.CreatePackageInfoReqDTO;
import com.hivescm.tms.api.dto.es.wave.WaveInfoReqDTO;
import com.hivescm.tms.api.dto.es.waybill.request.AcceptChangeDispatcherResultReqDTO;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TMS运单服务
 * @author 
 * @date 2017年11月7日
 * @company 蜂网供应链管理（上海）有限公司
 */
@FeignClient(value = FeignEnvironment.BILLCENTER_SERVER_NAME, path = FeignEnvironment.BILLCENTER_SERVER_PATH)
public interface WaybillService {
    /**
     * 接收OMS运单波次信息
     * @param req
     * @return
     */
    @RequestMapping(value = "/waybill/acceptWaybillWaveInfo", method = RequestMethod.POST)
    DataResult<Boolean> acceptWaybillWaveInfo(@RequestBody WaveInfoReqDTO req);
    
    /**
     * 接收OMS运单包裹信息
     * @param req
     * @return
     */
    @RequestMapping(value = "/waybill/acceptWaybillPackageInfo", method = RequestMethod.POST)
    DataResult<Boolean> acceptWaybillPackageInfo(@RequestBody CreatePackageInfoReqDTO req);
    
    /**
     * 接收OMS改配审核结果
     * @param req
     * @return
     */
    @PostMapping("/changedistribution/acceptApplicateResult")
	DataResult<Boolean> acceptApplicateResult(@RequestBody AcceptChangeDispatcherResultReqDTO req);
}
