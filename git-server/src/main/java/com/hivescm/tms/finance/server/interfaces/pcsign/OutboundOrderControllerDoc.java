package com.hivescm.tms.finance.server.interfaces.pcsign;

import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.request.OutWareHousePrintReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.OutWareHousePrintRespDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 出库单服务
 * @author jisai
 * @Date 2018-04-10
 */
@Api(value="outboundOrder",description="出库单服务")
public interface OutboundOrderControllerDoc {

	@ApiOperation(value = "打印出库单")
	@ApiImplicitParam(value="出库单实体",name = "outWareHousePrintReqDTO",required = true,paramType="body",dataType = "OutWareHousePrintReqDTO")
	DataResult<OutWareHousePrintRespDTO> printOutboundOrder(@RequestBody OutWareHousePrintReqDTO outWareHousePrintReqDTO);

}
