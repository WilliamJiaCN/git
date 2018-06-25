package com.hivescm.tms.finance.server.controller.pcsign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.tms.api.dto.es.sign.component.OutboundOrderDTO;
import com.hivescm.tms.api.dto.es.sign.request.OutWareHousePrintReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.OutWareHousePrintRespDTO;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.pcsign.OutboundOrderControllerDoc;
import com.hivescm.tms.finance.server.service.pcsign.OutboundOrderService;

/**
 * 出库单服务Controller
 * @author jisai
 * @Date 2018-04-10
 */
@RestController
@RequestMapping("/outboundOrder")
public class OutboundOrderController  extends BaseController implements OutboundOrderControllerDoc{

	@Autowired
	private OutboundOrderService outboundOrderService;

	@Override
	@PostMapping("/printOutboundOrder")
	public DataResult<OutWareHousePrintRespDTO> printOutboundOrder(@RequestBody OutWareHousePrintReqDTO outWareHousePrintReqDTO){
		DataResult<OutWareHousePrintRespDTO> result = new DataResult<>();
		try {
			result.setResult(outboundOrderService.printOutboundOrder(outWareHousePrintReqDTO));
			return result;
		} catch (Exception e) {
			logger.error("出库单打印失败+tmsSignEsDTO"+outWareHousePrintReqDTO.toString());
			throw ExceptionFactory.ex(e);
		}
	}
}
