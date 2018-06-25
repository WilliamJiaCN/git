package com.hivescm.tms.finance.server.feign.waybill;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherFeeEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.component.TmsDispatcherEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceSendMqDTO;
import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderFeeEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportArrivalCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.component.TmsTransportArriveDTO;
import com.hivescm.tms.api.dto.es.transport.request.ArrivalCostReqDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/10 15:09
 * @company 蜂网供应链管理（上海）有限公司
 */
@FeignClient(value="${tms.dispatcher.application.name}",path="${tms.dispatcher.application.path}")
public interface TransportArriveService {

    /**
     * 到货费用明细
     * @param id
     * @return
     */
    @GetMapping("/arrive/getArrivalInfoAndCostById/{id}")
    DataResult<TmsTransportArriveDTO> getArrivalInfoAndCostById(@PathVariable("id") Long id);

    /**
     * 发车配载费用明细
     * @param finance
     * @return
     */
    @PostMapping("/transport/getTransportCost")
    public DataResult<List<TransportCostDetailEsDTO>> getTransportCost(@RequestBody FinanceSendMqDTO finance);

    @GetMapping("/dispatcher/getDispatcher/{dispatcherId}")
    public DataResult<TmsDispatcherEsDTO> getDispatcher(@PathVariable("dispatcherId") Long dispatcherId);

    /**
     * 根据发车批次ID查询发车配载主表信息
     *
     * @param id
     * @return
     */
    @PostMapping("/transport/getTransportInfoById/{id}")
    DataResult<TransportInfoEsDTO> getTransportInfoById(@PathVariable("id") Long id);

    /**
     * 根据装卸单ID查询装卸单费用明细信息
     *
     * @param handlingId
     * @return
     */
    @PostMapping("/handlingorder/getHandlingFeeList")
    DataResult<List<HandlingorderFeeEsDTO>> getHandlingFeeList(@RequestParam("handlingId")  Long handlingId);

    /**
     * 到货单的费项
     * @param arrivalCostReqDTO
     * @return
     */
    @PostMapping("/arrive/getArrivalCostByIds")
    DataResult<List<TransportArrivalCostDetailEsDTO>> getArrivalCostByIds(@RequestBody ArrivalCostReqDTO arrivalCostReqDTO);

    /**
     * 根据费项ID查询派车单费项明细
     *
     * @param feeIds
     * @return
     */
    @PostMapping("/dispatcherTrans/getDispatcherFeeListByIds")
    DataResult<List<DispatcherFeeEsDTO>> getDispatcherFeeListByIds(@RequestBody List<Long> feeIds);
}
