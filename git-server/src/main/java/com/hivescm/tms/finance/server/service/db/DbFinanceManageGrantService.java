package com.hivescm.tms.finance.server.service.db;

import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;

import java.util.List;

/**
 * 
 * @author wenxiong.jia
 * @since 2018/5/8
 */
public interface DbFinanceManageGrantService {

	/**
	 * 修改货款发放信息并记录资金流水
	 *
	 * @param financeManageGoodsGrantDo
	 * @param financeManageCashSerialDo
	 * @return
	 */
	Boolean oprGrantConfirmDB(FinanceManageGoodsGrantDO financeManageGoodsGrantDo, FinanceManageCashSerialDO financeManageCashSerialDo);

	/**
	 * 修改货款发放信息同时修改货款扣关联的应修信息并记录资金流水
	 *
	 * @param financeManageGoodsGrantDo
	 * @param financeManageReceiptDo
	 * @param financeManageCashSerialDo
	 * @return
	 */
	Boolean oprGrantConfirmDB(FinanceManageGoodsGrantDO financeManageGoodsGrantDo, FinanceManageReceiptDO financeManageReceiptDo, FinanceManageCashSerialDO financeManageCashSerialDo);

	/**
	 * 修改货款发放信息同时修改原资金流水并记录新资金流水
	 *
	 * @param financeManageGoodsGrantDo
	 * @param originalFinanceManageCashSerialDo
	 * @param newFinanceManageCashSerialDo
	 * @return
	 */
	Boolean oprGrantConfirmDB(FinanceManageGoodsGrantDO financeManageGoodsGrantDo, FinanceManageCashSerialDO originalFinanceManageCashSerialDo, FinanceManageCashSerialDO newFinanceManageCashSerialDo);

	/**
	 * 批量修改货款发放信息同时批量记录资金流水
	 *
	 * @param financeManageGoodsGrantDo
	 * @param financeManageCashSerialDoList
	 * @return
	 */
	Boolean oprIncomeConfirmDB(FinanceManageGoodsGrantDO financeManageGoodsGrantDo, List<FinanceManageCashSerialDO> financeManageCashSerialDoList);
}
