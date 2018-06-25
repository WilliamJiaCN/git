package com.hivescm.tms.finance.server.component.finance;

import com.hivescm.tms.api.dto.es.finance.request.FinanceManagePayCancelReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManagePayConfirmReqDTO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;
import com.mogujie.distributed.transction.RouteParam;


/*
 * 财务管理-应付表的Service接口
 * 
 * @author wenxiong.jia
 * @date 2018/4/27
 */
public interface FinanceManagePayComponentService {

	/**
	 * 付款确认
	 * @param financeManagePayConfirmReqDto
	 * @return
	 */
	Boolean payConfirm(FinanceManagePayConfirmReqDTO financeManagePayConfirmReqDto);
	/**
	 * 取消付款
	 * @param
	 * @return
	 */
	Boolean cancelPay(FinanceManagePayDO financeManagePayDo, FinanceManageCashSerialDO financeManageCashSerialDo, FinanceManageCashSerialDO newFinanceManageCashSerialDo, FinanceManagePayCancelReqDTO originalFinanceManagePayCancelReqDto);
}
