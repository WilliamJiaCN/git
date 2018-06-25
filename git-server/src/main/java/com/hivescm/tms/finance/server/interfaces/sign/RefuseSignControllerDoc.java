package com.hivescm.tms.finance.server.interfaces.sign;


import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsGoodsDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseForSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseSignNoticeReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseSignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.RefuseSignResultRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 拒绝签收服务
 *
 * @author 杨彭伟
 * @since 2017/11/8 15:28
 */
@Api(value = "insertRefuseForSign", description = "拒绝签收服务")
public interface RefuseSignControllerDoc {

    @ApiOperation(value = "录入拒绝签收信息(部分签收、拒收)")
    @ApiImplicitParam(value = "拒绝签收请求实体", name = "refuseForSignReqDTO",
            required = true, paramType = "body", dataType = "RefuseForSignReqDTO")
    DataResult saveRefuseSign(RefuseForSignReqDTO refuseForSignReqDTO);

    @ApiOperation(value = "通知拒绝签收信息")
    DataResult noticeRefuseSign(RefuseSignNoticeReqDTO refuseSignNoticeReqDTO);

    @ApiOperation(value = "获取审核结果 result ->-1.审核中,其它 拒收单金额")
    @ApiImplicitParam(value = "获取审核结果", name = "refuseId",
            required = true, paramType = "path", dataType = "Long")
    DataResult<RefuseSignResultRespDTO> getRefuseSignAmount(Long refuseId);

    @ApiOperation(value = "查询拒收单列表")
    DataResult<PagedList<SignRefuseEsDTO>> getRefuseSignList(RefuseSignQueryReqDTO refuseSignQueryReqDTO);

    @ApiOperation(value = "查询拒收单商品详情")
    @ApiImplicitParam(value = "拒收单商品详情查询条件", name = "refuseId",
            required = true, paramType = "path", dataType = "Long")
    DataResult<List<TmsGoodsDetailsEsDTO>> getRefuseSignGoodsDetails(Long refuseId);

    @ApiOperation(value = "作废拒收单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "拒收单的订单编号", name = "orderCode", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(value = "公司id", name = "companyId", required = true, paramType = "path", dataType = "Long")
    })
    DataResult<Boolean> cancelRefuseSign(String orderCode,@Deprecated Long companyId);

}
