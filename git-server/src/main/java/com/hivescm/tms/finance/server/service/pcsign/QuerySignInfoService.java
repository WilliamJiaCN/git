package com.hivescm.tms.finance.server.service.pcsign;

import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.AppQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCRefuseSignQueryReq;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.SignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.*;

import java.util.List;

/**
 *
 * 签收信息查询接口
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/8
 */
public interface QuerySignInfoService {
    SignWaybillInfoRespDTO getWaybillSignInfo(String waybillCode,Long companyId);

    PagedList<SignEsDTO> getSignInfoPage(PCSignReq req);

    SignRespDTO getDeliverySignInfoByDispatcherDetailId(Long dispatcherDetailId);

    PagedList<SignRefuseRespDTO> getRefuseSignInfoPage(PCRefuseSignQueryReq req);

    List<SignDetailsEsDTO> getSignDetail(Long signId);

    List<SignDetailsEsDTO> getSignDetailByDispatcherDetailId(Long dispatcherDetailId);

    SignQueryPageResponseDTO queryDispatcherDetailList(SignQueryReqDTO reqDTO) throws Exception;

    PagedList<SignWaybillElementDTO> getSignWaybills(PCSignReq reqDTO) throws Exception;

    PagedList<DispatcherDetailEsDTO> getDispatcherDetailLike(AppQueryReqDTO appQueryReqDTO);

    //List<DispatcherDetailPairRespDTO> getDispatcherBatchCodeListByWaybillCode(String waybillCode, Long companyId);
}
