package com.hivescm.tms.finance.server.controller.pcsign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.Status;
import com.hivescm.common.utils.Assert;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.tms.api.dto.es.finance.CashierConfirmationDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.query.FinanceManageReceiptQueryDTO;
import com.hivescm.tms.api.dto.es.finance.request.CapitalAccountReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.CashierConfirmationReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.WayBillCashierConfirmationReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.CapitalAccountResDTO;
import com.hivescm.tms.api.dto.es.finance.response.CashierConfirmationResDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.enums.ReturnCodeEnum;
import com.hivescm.tms.finance.server.component.pcsign.CashierConfirmationService;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.interfaces.pcsign.CashierConfirmationControllerDoc;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收银确认Controller
 * @author jisai
 * @Date 2018-04-10
 */
@RestController
@RequestMapping("/cashierConfirmation")
public class CashierConfirmationController extends BaseController implements CashierConfirmationControllerDoc {

	@Autowired
	private CashierConfirmationService cashierConfirmationService;
	@Autowired
	private WaybillService waybillService;
	@Override
	@PostMapping("/cashierConfirmation")
	public DataResult<Boolean> cashierConfirmation(@RequestBody CashierConfirmationReqDTO cashierConfirmationDTO){
		DataResult<Boolean> result = new DataResult<>();
		try {
			Boolean res = cashierConfirmationService.cashierConfirmation(cashierConfirmationDTO);
			result.setResult(res);
			return result;
		} catch (Exception e) {
			logger.error("收银确认失败: "+cashierConfirmationDTO.toString());
			throw ExceptionFactory.ex(e);
		}
	}

	@Override
	@PostMapping("/cashierConfirmationAmount")
	public DataResult<List<CashierConfirmationResDTO>> cashierConfirmationAmount(@RequestBody CashierConfirmationReqDTO cashierConfirmationDTO) {
		DataResult<List<CashierConfirmationResDTO>> result = new DataResult<>();
		List<CashierConfirmationResDTO> cashierConfirmationResDTOS = null;
		try {
			cashierConfirmationResDTOS = cashierConfirmationService.cashierConfirmationAmount(cashierConfirmationDTO);
		} catch (Exception e) {
			logger.error("cashierConfirmationAmount接口异常{}",e.getMessage(),e);
			throw ExceptionFactory.ex(e);
		}
		result.setResult(cashierConfirmationResDTOS);
		return result;
	}

	@Override
	@PostMapping("/capitalAccount")
	public DataResult<List<CapitalAccountResDTO>> capitalAccount(@RequestBody CapitalAccountReqDTO capitalAccountReqDTO) {
		DataResult<List<CapitalAccountResDTO>> result = new DataResult<>();
		List<CapitalAccountResDTO> capitalAccountResDTOs = null;
		try {
			capitalAccountResDTOs = cashierConfirmationService.capitalAccount(capitalAccountReqDTO);
		} catch (Exception e) {
			logger.error("capitalAccount接口异常{}",e.getMessage(),e);
			throw ExceptionFactory.ex(e);
		}
		result.setResult(capitalAccountResDTOs);
		return result;
	}

	@Override
	@PostMapping("/queryWaybill")
	public WaybillEsDTO queryWaybill(@RequestBody CashierConfirmationReqDTO cashierConfirmationDTO) {
		Long waybillId = cashierConfirmationDTO.getWaybillId();
		WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(waybillId);
		if (waybillEsDTO == null) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SIGN_NOT_EXIST, "运单ID：" + waybillId + " 未查询到运单信息！");
		}
		return waybillEsDTO;
	}

	/**
	 * --测试service接口
	 * @param waybillIds 运单ids
	 * @param companyId 公司id
	 * @param isCashier 是否收银
	 * 2018-06-10-zouhx
	 * @return
	 */

	@Override
	@PostMapping("/updateSignCashierStatus")
	public Boolean updateSignCashierStatus(@RequestBody WayBillCashierConfirmationReqDTO reqDTO){
		Boolean flag;
		try{

			flag= cashierConfirmationService.updateSignCashierStatus(reqDTO);

		} catch (Exception e){
			logger.error("修改收银状态接口异常{}",e.getMessage(),e);
			throw ExceptionFactory.ex(e);
		}
		return flag;
	}


}
