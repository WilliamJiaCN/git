package com.hivescm.tms.finance.server.feign.dispatcher;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.base.request.EsLikeReqDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherGoodsEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.request.DispatcherDetailForSignReqDTO;
import com.hivescm.tms.api.dto.es.dispatcher.request.SignNoticeForDispatcherReqDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 杨彭伟
 * @date 2018-01-30 15:21
 */
@FeignClient(value = "${tms.dispatcher.application.name}", path = "${tms.dispatcher.application.path}")
public interface DispatcherService {
    @PostMapping("/dispatcher/queryDispatcherDetailByDispatcherId/{dispatcherId}")
    List<DispatcherDetailEsDTO> queryDispatcherDetailByDispatcherId(@PathVariable("dispatcherId") Long dispatcherId);

    @PostMapping("/dispatcher/queryDispatcherDetailByWaybillId")
    DispatcherDetailEsDTO queryDispatcherDetailByWaybillId(@RequestParam("waybillId") Long waybillId);

    @GetMapping("/dispatcher/queryDispatcherEsDTO/{dispatcherId}")
    DispatcherEsDTO queryDispatcherEsDTO(@PathVariable("dispatcherId") Long dispatcherId);

    @PostMapping("/dispatcher/updateDispatcherById")
    Boolean updateDispatcherById(@RequestBody DispatcherEsDTO dispatcherEsDTO);

    @GetMapping("/dispatcher/checkUpdateDispacher/{dispatcherId}/{waybillId}")
    Boolean checkUpdateDispacher(@PathVariable("dispatcherId") Long dispatcherId, @PathVariable("waybillId") Long waybillId);

    @PostMapping("/dispatcherTrans/updateDispatcherDetailById")
    Boolean updateDispatcherDetailById(@RequestBody DispatcherDetailEsDTO dispatcherDetailEsDTO);

    @PostMapping("/dispatcher/getDispatcherDetailTotalNum")
    Integer getDispatcherDetailTotalNum(@RequestBody DispatcherDetailForSignReqDTO signReqDTO);

    @PostMapping("/dispatcherTrans/getDispatcherDetailTotalNum")
    Integer getDeliveryDispatcherDetailTotalNum(@RequestBody DispatcherDetailForSignReqDTO signReqDTO);

    @PostMapping("/dispatcherTrans/getDispatcherDetailEsList")
    List<DispatcherDetailEsDTO> getDispatcherDetailEsList(@RequestBody DispatcherDetailForSignReqDTO dispatcherDetailForSignReqDTO);

    @PostMapping("/dispatcherTrans/queryDispatcherDetailById")
    DispatcherDetailEsDTO queryDispatcherDetailById(@RequestParam("id")  Long id);

    @PostMapping("/dispatcherTrans/getDispatcherGoodsByDetailId/{detailId}")
    List<DispatcherGoodsEsDTO> getDispatcherGoodsByDetailId(@PathVariable("detailId")  Long id);


    @GetMapping("/dispatcherTrans/queryDispatcherDetailsByWaybillCode/{waybillCode}")
    List<DispatcherDetailEsDTO> queryDispatcherDetailsByWaybillCode(@PathVariable("waybillCode")String waybillCode);

    @PostMapping("/dispatcherTrans/queryDispatcherDetailLike")
    PagedList<DispatcherDetailEsDTO> queryDispatcherDetailLike(@RequestBody EsLikeReqDTO esLikeReqDTO);
    /**
    * 同步派车单状态
    * @param notice
    * @return
    */
    @PostMapping("/dispatcherTrans/synecDispatcherStatus")
    DataResult<Boolean> synecDispatcherStatus(@RequestBody SignNoticeForDispatcherReqDTO notice);
}
