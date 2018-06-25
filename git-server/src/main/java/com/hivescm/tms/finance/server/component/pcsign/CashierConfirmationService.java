package com.hivescm.tms.finance.server.component.pcsign;

import com.hivescm.tms.api.dto.es.finance.request.CapitalAccountReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.CashierConfirmationReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.WayBillCashierConfirmationReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.CapitalAccountResDTO;
import com.hivescm.tms.api.dto.es.finance.response.CashierConfirmationResDTO;

import java.util.List;

/**
 * 收银确认service
 * @author jisai
 * @Date 2018-04-10
 */
public interface CashierConfirmationService {

	/**
	 * 签收 -- 收银确认
	 * @param cashierConfirmationDTO
	 * @return
	 */
	Boolean cashierConfirmation(CashierConfirmationReqDTO cashierConfirmationDTO);

	/**
	 * 签收 -- 收银确认展示金额接口
	 * @param cashierConfirmationDTO
	 * @return
	 */
	List<CashierConfirmationResDTO> cashierConfirmationAmount(CashierConfirmationReqDTO cashierConfirmationDTO);

	/**
	 * 签收 -- 收银确认资金账户接口
	 * @param capitalAccountReqDTO
	 * @return
	 */
	List<CapitalAccountResDTO> capitalAccount(CapitalAccountReqDTO capitalAccountReqDTO);


	/**
	 * 财务收银后回写签收收银状态
	 * @param reqDTO
	 * 2018-06-10-zouhx
	 * @return
	 */
	boolean updateSignCashierStatus(WayBillCashierConfirmationReqDTO reqDTO);


}
