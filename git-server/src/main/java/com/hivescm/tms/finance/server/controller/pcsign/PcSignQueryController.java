package com.hivescm.tms.finance.server.controller.pcsign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCRefuseSignQueryReq;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.SignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.*;
import com.hivescm.tms.finance.server.interfaces.pcsign.PcSignQueryControllerDoc;
import com.hivescm.tms.finance.server.service.pcsign.QuerySignInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 送货签收-查询服务
 * @author  zouhx
 * @date    2018年6月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
@RestController
@RequestMapping("signInfo/query")
public class PcSignQueryController implements PcSignQueryControllerDoc {

    @Autowired
    private QuerySignInfoService querySignInfoService;

    @Override
    @GetMapping("waybillSignInfo/{waybillCode}/{companyId}")
    public DataResult<SignWaybillInfoRespDTO> getWaybillSignInfo(@PathVariable String waybillCode,@PathVariable Long companyId) {
        SignWaybillInfoRespDTO resp = querySignInfoService.getWaybillSignInfo(waybillCode,companyId);
        DataResult dataResult = new DataResult();
        dataResult.setResult(resp);
        return dataResult;

    }


    /**
     * 根据派车单明细,查询签收信息
     * @param dispatcherDetailId
     * @return
     */
    @Override
    @GetMapping("deliverySignInfo/{dispatcherDetailId}")
    public DataResult<SignRespDTO> getDeliverySignInfo(@PathVariable Long dispatcherDetailId){
        SignRespDTO signRespDTO= querySignInfoService.getDeliverySignInfoByDispatcherDetailId(dispatcherDetailId);
        DataResult dataResult = new DataResult();
        dataResult.setResult(signRespDTO);
       return dataResult;
    }

    /**
     * 送货签收-签收列表--已作废
     * @param pcSignReq
     * @return
     */
    @Override
    @PostMapping("signPages")
    public DataResult<PagedList<SignEsDTO>> getSignInfo(@RequestBody PCSignReq pcSignReq) {
        DataResult<PagedList<SignEsDTO>> result = new DataResult<>();
        result.setResult(querySignInfoService.getSignInfoPage(pcSignReq));
        return result;
    }

   /**
     * 送货签收 拒签列表
     * @param req
     * @return
     */
    @Override
    @PostMapping("refuseSignPages")
    public DataResult<PagedList<SignRefuseRespDTO>> refuseSignPages(@RequestBody PCRefuseSignQueryReq req) {
        DataResult<PagedList<SignRefuseRespDTO>> result = new DataResult<>();
        result.setResult(querySignInfoService.getRefuseSignInfoPage(req));
        return result;
    }

    /**
     * 送货签收  签收列表查询
     * 包含  未签收 全部签收  部分签收  全部拒签  派送失败
     * @param reqDTO
     * @return
     */
    @Override
    @PostMapping("queryDispatcherDetailList")
    public DataResult<SignQueryPageResponseDTO> queryDispatcherDetailList(@RequestBody SignQueryReqDTO reqDTO) throws Exception {
        DataResult<SignQueryPageResponseDTO> result = new DataResult<>();
        result.setResult(querySignInfoService.queryDispatcherDetailList(reqDTO));
        return result;
    }


    /**
     * 通过 签收单id  获取签收商品列表
     * @param signId
     * @return
     */
    @Override
    @GetMapping("signDetail/{signId}")
    public DataResult<List<SignDetailsEsDTO>> signDetail(@PathVariable Long signId){
        DataResult<List<SignDetailsEsDTO>> result = new DataResult<>();
        result.setResult(querySignInfoService.getSignDetail(signId));
        return result;
    }


    /**
     * 查询签收运单列表
     * @param reqDTO
     * @return
     */
    @Override
    @PostMapping("signWaybills")
    public DataResult<PagedList<SignWaybillElementDTO>> getSignWaybills(@RequestBody PCSignReq reqDTO) throws Exception {

        DataResult<PagedList<SignWaybillElementDTO>> result = new DataResult<>();
        PagedList<SignWaybillElementDTO> list =querySignInfoService.getSignWaybills(reqDTO);
        result.setResult(list);
        return result;

    }

}
