/**
 * 
 */
package com.hivescm.tms.finance.server.controller.pcsign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.tms.api.dto.es.sign.component.OutboundCancelSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.component.OutboundSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.WaybillTrackRespDTO;
import com.hivescm.tms.finance.server.component.pcsign.OutboundSignService;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.pcsign.OtherServerControllerDoc;
import com.hivescm.tms.finance.server.service.pcsign.OtherServerService;

/**
 * @author boqiang.deng
 * @date 2018年4月10日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@RestController
@RequestMapping("/otherserver")
public class OtherServerController extends BaseController implements OtherServerControllerDoc {

	@Autowired
	private OtherServerService otherServerService;
	
	@Autowired
	private OutboundSignService outboundSignService;

	@Override
	@PostMapping("/getwaybilltrackinfo/{waybillId}")
	public DataResult<WaybillTrackRespDTO> getWaybillTrackInfo(@PathVariable("waybillId") Long waybillId) {

		DataResult<WaybillTrackRespDTO> result = new DataResult<>();
		try {
			result.setResult(otherServerService.getWaybillTrackInfo(waybillId));
			return result;
		} catch (Exception e) {
			logger.error("运单跟踪信息查询失败+waybillId" + waybillId);
			throw ExceptionFactory.ex(e);
		}
	}

	@Override
	@PostMapping("/outboundsigninsert")
	public DataResult<Boolean> outboundSignInsert(@RequestBody List<OutboundSignReqDTO> outboundReq) {
		DataResult<Boolean> result = new DataResult<>();
		try {
			result.setResult(outboundSignService.insert(outboundReq));
			return result;
		} catch (Exception e) {
			logger.error("外发签收新增失败" + outboundReq.toString());
			throw ExceptionFactory.ex(e);
		}
	}

	@Override
	@PostMapping("/outboundcancelsign")
	public DataResult<Boolean> outboundCancelSign(@RequestBody OutboundCancelSignReqDTO outboundCancelSignReqDTO) {
		DataResult<Boolean> result = new DataResult<>();
		try {
			result.setResult(outboundSignService.cancel(outboundCancelSignReqDTO));
			return result;
		} catch (Exception e) {
			logger.error("外发签收取消失败" + outboundCancelSignReqDTO.toString());
			throw ExceptionFactory.ex(e);
		}
	}
}
