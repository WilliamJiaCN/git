/**
 * 
 */
package com.hivescm.tms.finance.server.interfaces.pcsign;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.component.OutboundCancelSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.component.OutboundSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.WaybillTrackRespDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author  boqiang.deng
 * @date    2018年4月10日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */

@Api(value="OtherServeService",description="其他服务所需要的接口")
public interface OtherServerControllerDoc {

	@ApiOperation(value = "运单跟踪查询签信息")
	DataResult<WaybillTrackRespDTO> getWaybillTrackInfo(@PathVariable("waybillId") Long waybillId);
	
	@ApiOperation(value = "外发签收新增接口")
	@ApiImplicitParam(value="外发签收新增请求实体",name = "outboundReq",required = true,paramType="body",dataType = "OutboundSignReqDTO")
	DataResult<Boolean> outboundSignInsert(@RequestBody List<OutboundSignReqDTO> outboundReq);
	
	@ApiOperation(value = "外发签收取消接口")
	@ApiImplicitParam(value="外发签收取消请求实体",name = "outboundCancelSignReqDTO",required = true,paramType="body",dataType = "OutboundCancelSignReqDTO")
	DataResult<Boolean> outboundCancelSign(@RequestBody OutboundCancelSignReqDTO outboundCancelSignReqDTO);
}
