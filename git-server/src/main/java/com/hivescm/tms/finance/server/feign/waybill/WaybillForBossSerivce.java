package com.hivescm.tms.finance.server.feign.waybill;

import com.hivescm.tms.api.dto.bossfreight.BillingCalculateRep;
import com.hivescm.tms.api.dto.bossfreight.BillingRecordReq;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.waybill.request.WaybillTotalFee;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author 杨彭伟
 * @date 2018-02-26 15:59
 */
@FeignClient(value = "${tms.billcenter.application.name}", path = "${tms.billcenter.application.path}")
public interface WaybillForBossSerivce {
    @PostMapping("/deliveryCollect/calculateDeliveryCharge/{waybillId}")
    BillingCalculateRep calculateDeliveryCharge(@PathVariable("waybillId") Long waybillId,
                                                @RequestParam("goodsPayment") BigDecimal goodsPayment);

    @PostMapping("/deliveryCollect/sendWaybillInfoToBoss")
    Boolean sendWaybillInfoToBoss(@RequestBody BillingRecordReq req);

    @PostMapping("/deliveryCollect/calculateTotalFee")
    HashMap<String, Object> calculateTotalFee(@RequestBody WaybillTotalFee waybillTotalFee);
    
    @PostMapping("/deliveryCollect/sendFeeInfoToBoss")
    Boolean sendFeeInfoToBoss(@RequestBody List<FinanceManagePayEsDTO> financeList);
}
