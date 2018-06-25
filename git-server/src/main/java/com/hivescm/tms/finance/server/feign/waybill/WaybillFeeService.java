package com.hivescm.tms.finance.server.feign.waybill;

import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Description:运单费用信息查询feign接口
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/12 13:50
 * @company 蜂网供应链管理（上海）有限公司
 */
@FeignClient(value = "${tms.billcenter.application.name}", path = "${tms.billcenter.application.path}")
public interface WaybillFeeService {
    @GetMapping("/waybillFee/getWaybillFeeByWaybillId/{waybillId}")
    List<WaybillFeeEsDTO> getWaybillFeeByWaybillId(@PathVariable("waybillId") Long waybillId);
}
