package com.hivescm.tms.finance.server.controller.pcsign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.CancelSelfDeliverySignReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.QuerySignBySignBatchNumberReq;
import com.hivescm.tms.api.dto.es.sign.request.SearchSignCodeReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SelfSignBatchReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignInitializeWayBillReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignListRespDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.pcsign.AddSignService;
import com.hivescm.tms.finance.server.component.pcsign.CancelSelfDeliverySignService;
import com.hivescm.tms.finance.server.component.pcsign.SelfBatchSignService;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.pcsign.SelfSignControllerDoc;
import com.hivescm.tms.finance.server.service.pcsign.SelfDeliverySignService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
/**
 * 自提签收Controller
 * @author boqiang.deng
 * @company 蜂网供应链(上海)管理有限公司
 * @version 2018年1月22日 下午3:51:40
 * 
 */
@RestController
@RequestMapping("/selfsign")
public class SelfSignController extends BaseController implements SelfSignControllerDoc{

	@Autowired
	private SelfDeliverySignService selfDeliverySignService;
	
	@Autowired
	private AddSignService addSelfDeliverySignService;
	
	@Autowired
	private CancelSelfDeliverySignService cancelSelfDeliverySignService;
	
	@Autowired
	private SelfBatchSignService selfBatchSignService;
	
	@Autowired
	private EsSignService esSignService;
	@Override
	@PostMapping("/getNoSign")
	public DataResult<SignListRespDTO> getNoSign(@RequestBody PCSignReq pcSignReq) {
		DataResult<SignListRespDTO> result = new DataResult<>();
		try {
			result.setResult(selfDeliverySignService.getNoSign(pcSignReq));
			return result;
		} catch (Exception e) {
			logger.error("查询未签收列表+pcSignReq"+ pcSignReq.toString());
			throw ExceptionFactory.ex(e);
		}
	}
	
	@Override
	@PostMapping("/getSignedAndBillPartialSignBill")
	public DataResult<SignListRespDTO> getSignedAndBillPartialSignBill(@RequestBody PCSignReq pcSignReq) {
		DataResult<SignListRespDTO> result = new DataResult<>();
		try {
			result.setResult(selfDeliverySignService.getSignedAndBillPartialSignBill(pcSignReq));
			return result;
		} catch (Exception e) {
			logger.error("取消签收失败+pcSignReq"+ pcSignReq.toString());
			throw ExceptionFactory.ex(e);
		}
		
	}

	@Override
	@PostMapping("/getAllBill")
	public DataResult<SignListRespDTO> getAllBill(@RequestBody PCSignReq pcSignReq) {
		DataResult<SignListRespDTO> result = new DataResult<>();
		try {
			result.setResult(selfDeliverySignService.getAllBill(pcSignReq));
			return result;
		} catch (Exception e) {
			logger.error("查询全部列表失败+pcSignReq"+ pcSignReq.toString());
			throw ExceptionFactory.ex(e);
		}
	}
	
	@Override
	@PostMapping("/getSignBill")
	public DataResult<TmsSignEsDTO> getSignBill(@RequestBody QuerySignBySignBatchNumberReq req) {
		try {
			DataResult<TmsSignEsDTO> result = new DataResult<>();
			result.setResult(selfDeliverySignService.getSignBill(req));
			return result;
		} catch (Exception e) {
			logger.error("查询签收明细失败+waybillCode"+req.toString());
			throw ExceptionFactory.ex(e);
		}
	}
	
	@Override
	@PostMapping("/addInitialize")
	public DataResult<TmsSignEsDTO> addInitialize(@RequestBody SignInitializeWayBillReqDTO req) {
		DataResult<TmsSignEsDTO> result = new DataResult<>();
		try {
			result.setResult(selfDeliverySignService.addInitialize(req));
			return result;
		} catch (Exception e) {
			logger.error("初始化签收信息失败+req"+req.toString());
			throw ExceptionFactory.ex(e);
		}
	}

	@Override
	@PostMapping("/add")
	public DataResult<Boolean> insertSign(@RequestBody TmsSignEsDTO tmsSignEsDTO) {
		DataResult<Boolean> result = new DataResult<>();
		try {
			result.setResult(addSelfDeliverySignService.insertSign(tmsSignEsDTO));
			return result;
		} catch (SystemException se) {
			logger.error(se.getMessage(), se);
			throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "运单正在被其他用户操作");
		} catch (Exception e) {
			logger.error("签收新增失败+tmsSignEsDTO"+tmsSignEsDTO.toString());
			throw ExceptionFactory.ex(e);
		}
	}

	@Override
	@PostMapping("/cancelSelfDeliverySign")
	public DataResult<Boolean> cancelSelfDeliverySign(@RequestBody CancelSelfDeliverySignReqDTO cancelSelfDeliverySignReqDTO) {
		DataResult<Boolean> result = new DataResult<>();
		try {
			result.setResult(cancelSelfDeliverySignService.cancelSelfDeliverySign(cancelSelfDeliverySignReqDTO));
			return result;
		} catch (SystemException se) {
			logger.error(se.getMessage(), se);
			throw se;
		} catch (Exception e) {
			logger.error("取消签收失败+cancelSelfDeliverySignReqDTO"+cancelSelfDeliverySignReqDTO.toString());
			throw ExceptionFactory.ex(e);
		}
	}

	@Override
	@PostMapping("/batchSign")
	public DataResult<Boolean> batchSign(@RequestBody SelfSignBatchReqDTO req) {
		DataResult<Boolean> result = new DataResult<>();
		try {
			result.setResult(selfBatchSignService.batchSign(req));
			return result;
		} catch (SystemException se) {
			logger.error(se.getMessage(), se);
			throw se;
		} catch (Exception e) {
			logger.error("自提批量签收失败+SelfSignBatchReqDTO"+req.toString());
			throw ExceptionFactory.ex(e);
		}
	}

	@Override
	@PostMapping("/querySignNumber")
	public DataResult<List<String>> querySignNumber(@RequestBody SearchSignCodeReqDTO req) {
		DataResult<List<String>> result = new DataResult<>();
		try {
			result.setResult(this.esSignService.querySignNumber(req));
			return result;
		} catch (Exception e) {
			logger.error("根据运单号查询签收批次失败+SearchSignCodeReqDTO"+req.toString());
			throw ExceptionFactory.ex(e);
		}
	}
}
