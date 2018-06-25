package com.hivescm.tms.finance.server.interfaces.pcsign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCRefuseSignQueryReq;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.SignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/10
 */
@Api(value="pcSignQuery",description="签收查询服务")
public interface PcSignQueryControllerDoc {
    @ApiOperation(value = "通过运单号查询运单签收详情")
    DataResult<SignWaybillInfoRespDTO> getWaybillSignInfo(@PathVariable String waybillCode,@PathVariable Long companyId);

    @ApiOperation(value = "根据派车单明细 查询签收信息")
    DataResult<SignRespDTO> getDeliverySignInfo(@PathVariable Long dispatcherDetailId);

    @ApiOperation(value = "签收列表")
    DataResult<PagedList<SignEsDTO>> getSignInfo(@RequestBody PCSignReq pcSignReq);

    @ApiOperation(value = "拒签列表")
    DataResult<PagedList<SignRefuseRespDTO>> refuseSignPages(@RequestBody PCRefuseSignQueryReq req);

    @ApiOperation(value = "全部签收  未签收  全部签收  部分签收  全部拒签  派送失败")
    DataResult<SignQueryPageResponseDTO> queryDispatcherDetailList(@RequestBody SignQueryReqDTO reqDTO) throws Exception;

    @ApiOperation(value = "通过 签收单id  获取签收商品列表")
    DataResult<List<SignDetailsEsDTO>> signDetail(@PathVariable Long signId);

    @ApiOperation(value = "查询签收运单列表")
    DataResult<PagedList<SignWaybillElementDTO>> getSignWaybills(@RequestBody PCSignReq reqDTO) throws Exception;
}
