package com.hivescm.tms.finance.server.service.db;

import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;

import java.util.List;

/**
 * 
 * @author wenxiong.jia
 * @since 2018/4/27
 */
public interface DbFinanceManagePayService {

	/**
	 * 批量新增
	 *
	 * @param financeManagePayDoList
	 * @return
	 */
	Boolean insertBatchPayInfo(List<FinanceManagePayDO> financeManagePayDoList);
	/**
	 * 批量修改应付记录信息
	 *
	 * @param ltlOrderInfoDoList
	 * @return
	 */
	Boolean updateBatchPayInfo(List<FinanceManagePayDO> financeManagePayDoList);

	/**
	 * 修改应付记录信息并记录资金流水
	 *
	 * @param financeManagePayDo
	 * @param financeManageCashSerialDo
	 * @return
	 */
	Boolean oprPayConfirmDB(FinanceManagePayDO financeManagePayDo, FinanceManageCashSerialDO financeManageCashSerialDo);
	/**
	 * 修改应付记录信息并修改原资金流水同时记录新的资金流水
	 *
	 * @param ltlOrderInfoDoList
	 * @return
	 */
	Boolean oprPayCancelDB(FinanceManagePayDO financeManagePayDo, FinanceManageCashSerialDO originalFinanceManageCashSerialDo, FinanceManageCashSerialDO newFinanceManageCashSerialDo);
	/**
	 * 根据来源单号删除应付
	 * @param financeDeleteReqDTO
	 * @return
	 */
	int deleteByCode(FinanceDeleteReqDTO financeDeleteReqDTO);
}
